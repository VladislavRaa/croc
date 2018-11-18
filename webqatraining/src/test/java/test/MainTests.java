package test;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MainTests extends BaseRunner {

    private class Select {
        private WebElement element;

        Select(String name) {
            new WebDriverWait(driver, 5).until(d -> driver.findElements(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::div[@data-qa-file='UIDropdownField']")).size() != 0);
            element = driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::div[@data-qa-file='UIDropdownField']"));
        }

        private List<WebElement> getAllItems() {
            return element.findElements(By.xpath(".//div[@class='ui-dropdown-field-list__item-view ui-select__option_with-subtext_right-side']"));
        }

        void setItem(String text) {
            element.click();
            new WebDriverWait(driver, 5).until(d ->
                    {
                        List<WebElement> els = getAllItems();
                        for (WebElement el : els) {
                            if (el.getText().contains(text)) {
                                el.click();
                                return true;
                            }
                        }
                        return false;
                    }
            );
        }
    }

    private class CheckBox {
        private String name;

        CheckBox(String text) {
            name = text;
        }

        private WebElement getCheckBox() {
            return driver.findElement(By.xpath("//span[contains(text(),'" + name + "')]/ancestor::label[@data-qa-file='UICheckbox']"));
        }

        boolean isChecked() {
            return getCheckBox().getAttribute("class").contains("ui-checkbox_checked");
        }

        String getBoxText() {
            return getCheckBox().getText();
        }

        void setActive(boolean status) {
            if (isChecked() != status) {
                getCheckBox().click();
            }
        }
    }

    private class InputFields {
        private WebElement element;

        InputFields(String name) {
            element = driver.findElement(By.xpath("//*[@class='ui-input__label_placeholder-text' and contains(text(), '" + name + "')]/ancestor::div[@class='ui-input__column']//input[not(@disabled)]"));
        }

        String getText() {
            return element.getAttribute("value");
        }

        void setText(String text) {
            new Actions(driver).moveToElement(element)
                    .click()
                    .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                    //.keyDown(Keys.COMMAND + "A")
                    .sendKeys(Keys.DELETE)
                    //.keyDown(Keys.DELETE)
                    .sendKeys(Keys.END)
                    //.keyDown(Keys.DELETE)
                    .sendKeys(text)
                    //.keyDown(text)
                    .perform();
        }
    }

    private class Button {
        private WebElement element;

        Button(String name) {
            element = driver.findElement(By.xpath("//button[contains(string(),'" + name + "')]"));
        }

        public boolean isEnable() {
            return element.isEnabled();
        }

        public void click() {
            element.click();
        }
    }

    private void ChangeRegion(String region) {
        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_3WFCP']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'" + region + "')]")).click();
    }

    private String getTotalPrice() {
        return driver.findElement(By.xpath("//h3[@data-qa-file='UITitle'][contains(@class,'6kgKn')]")).getText();
    }

    public MainTests() {
        baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    }

    @Test
    public void tabSwitching() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.get("https://www.google.ru/");
        driver.findElement(By.name("q")).sendKeys("мобайл тинькофф тарифы");
        driver.findElements(By.xpath("//ul[@role='listbox']/li"));
        wait
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Что-то пошло не так...")
                .pollingEvery(Duration.ofMillis(500))
                .until(d -> {
                    By listItems = By.xpath("//ul[@role='listbox']/li[@role='presentation' and .//*[@role='option']]");
                    List<WebElement> elements = driver.findElements(listItems);
                    for (WebElement el : elements) {
                        if (el.getText().equals("мобайл тинькофф тарифы")) el.click();
                        break;
                    }
                    return d.getTitle().equals("мобайл тинькофф тарифы - Поиск в Google");
                });
        wait.until(d -> driver.findElements(By.cssSelector("a[href*='https://www.tinkoff.ru/mobile-operator/tariffs/']")).size() > 0);
        driver.findElement(By.cssSelector("a[href*='https://www.tinkoff.ru/mobile-operator/tariffs/']")).click();
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        assertEquals("Тарифы Тинькофф Мобайл", driver.findElement(By.cssSelector("span[data-qa-file=\"DangerouslyHTML\"][class=\"DangerouslyHTML__box_2ds8q\"]")).getText());
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        wait.until(d -> driver.getCurrentUrl().equals("https://www.tinkoff.ru/mobile-operator/tariffs/"));
    }

    @Test
    public void changeOfRegion() {
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("span.MvnoRegionConfirmation__option_3mrvz.MvnoRegionConfirmation__optionAgreement_3M5qT")).click();
        assertEquals("Москва и Московская область", driver.findElement(By.cssSelector("div.MvnoRegionConfirmation__title_3WFCP")).getText());
        driver.navigate().refresh();
        assertEquals("Москва и Московская область", driver.findElement(By.cssSelector("div.MvnoRegionConfirmation__title_3WFCP")).getText());

        String priceMsk = getTotalPrice();
        ChangeRegion("Краснодар");
        driver.navigate().refresh();
        String priceKrasnodar = getTotalPrice();
        assertNotEquals(priceKrasnodar, priceMsk);

        new Select("Интернет").setItem("Безлимитны");
        new Select("Звонки").setItem("Безлимитны");
        new CheckBox("Режим модема").setActive(true);
        new CheckBox("SMS").setActive(true);
        String maxPriceKrasnodar = getTotalPrice();
        ChangeRegion("Москва");
        new Select("Интернет").setItem("Безлимитны");
        new Select("Звонки").setItem("Безлимитны");
        new CheckBox("Режим модема").setActive(true);
        new CheckBox("SMS").setActive(true);
        String maxPriceMsk = getTotalPrice();
        assertEquals(maxPriceMsk, maxPriceKrasnodar);
    }

    @Test
    public void notActiveButton() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        new Select("Интернет").setItem("0 ГБ");
        new Select("Звонки").setItem("0 минут");
        new CheckBox("Мессенджеры").setActive(false);
        new CheckBox("Социальные сети").setActive(false);
        new CheckBox("Музыка").setActive(false);
        new CheckBox("Видео").setActive(false);
        new CheckBox("SMS").setActive(false);
        assertEquals(getTotalPrice(), "Общая цена: 0 \u20BD");
        assertTrue(new Button("Далее").isEnable());
    }
}

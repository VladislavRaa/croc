package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TinkoffMobileOperatorPage extends Page {
    public String baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";

    public TinkoffMobileOperatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public TinkoffMobileOperatorPage open() {
        driver.get(baseUrl);
        logger.info("Открытие страницы: www.tinkoff.ru/mobile-operator/tariffs/");
        return this;
    }

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

    public TinkoffMobileOperatorPage changeRegion(String region) {
        driver.findElement(By.xpath("//div[@class='MvnoRegionConfirmation__title_3WFCP']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'" + region + "')]")).click();
        logger.info(String.format("Смена региона на \"%s\"", region));
        return this;
    }

    public String getTotalPrice() {
        logger.info("Получение общей стоимости");
        return driver.findElement(By.xpath("//h3[@data-qa-file='UITitle'][contains(@class,'6kgKn')]")).getText();
    }

    public TinkoffMobileOperatorPage assertRegion(String region) {
        assertTrue(driver.findElement(By.cssSelector("div.MvnoRegionConfirmation__title_3WFCP")).getText().contains(region));
        logger.info(String.format("Проверить, что установлен регион \"%s\"", region));
        return this;
    }

    public TinkoffMobileOperatorPage acceptRegion(){
        logger.info("Ваш регион - Москва и Московская область");
        driver.findElement(By.xpath("//*[contains(text(),'Да')][@data-qa-file='MvnoRegionConfirmation']")).click();
        return this;
    }

    public boolean ButtonIsEnable(String value) {
        new Button(value).isEnable();
        logger.info(String.format("Состояние кнопки \"%s\"", value));
        return new Button(value).isEnable();
    }

    public TinkoffMobileOperatorPage setInternet(String value) {
        new Select("Интернет").setItem(value);
        logger.info(String.format("Выбран Интернет \"%s\"", value));
        return this;
    }

    public TinkoffMobileOperatorPage setMinutes(String value) {
        new Select("Звонки").setItem(value);
        logger.info(String.format("Выбраны звонки \"%s\"", value));
        return this;
    }

    public TinkoffMobileOperatorPage setUnlimitedMessages(boolean value) {
        new CheckBox("Мессенджеры").setActive(value);
        logger.info(String.format("Выбраны мессенджеры \"%s\"", value));
        return this;
    }

    public TinkoffMobileOperatorPage setUnlimitedSocialNetworks(boolean value) {
        new CheckBox("Социальные сети").setActive(value);
        logger.info(String.format("Выбраны социальные сети \"%s\"", value));
        return this;
    }

    public TinkoffMobileOperatorPage setUnlimitedMusic(boolean value) {
        new CheckBox("Музыка").setActive(value);
        logger.info(String.format("Выбрана музыка \"%s\"", value));
        return this;
    }

    public TinkoffMobileOperatorPage setUnlimitedVideo(boolean value) {
        new CheckBox("Видео").setActive(value);
        logger.info(String.format("Выбрано видео \"%s\"", value));
        return this;
    }

    public TinkoffMobileOperatorPage setUnlimitedSMS(boolean value) {
        new CheckBox("SMS").setActive(value);
        logger.info(String.format("Выбраны SMS \"%s\"", value));
        return this;
    }

    public TinkoffMobileOperatorPage setModem(boolean value) {
        new CheckBox("Режим модема").setActive(value);
        logger.info(String.format("Выбран режим модема \"%s\"", value));
        return this;
    }
}

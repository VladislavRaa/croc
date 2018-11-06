import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;



public class SecondTest extends BaseRunner {
    private List<WebElement> xpathSearcherByText(String searchText) {
        String xpath = String.format("//*[contains(text(),'%s')]", searchText);
        return driver.findElements(By.xpath(xpath));
    }

    @Test
    public void testEmptyFields() {
        driver.get("https://moscow-job.tinkoff.ru/");
        driver.findElement(By.xpath("//*[@name='fio']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@data-qa=\"fio\"]/../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
        driver.findElement(By.xpath("//*[@name='phone']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@data-qa=\"email\"]/following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
        driver.findElement(By.xpath("//*[@name='city']")).click();
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("//*[contains(text(),'Необходимо указать номер телефона')]")).getText());
        driver.findElement(By.xpath("//*[contains(text(),'Выберите вакансию')]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@name=\"city\"]/../../../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
        driver.findElement(By.xpath("//*[@name='city']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@role=\"listbox\"]/../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
    }

    @Test
    public void testInvalidValues() {
        driver.get("https://moscow-job.tinkoff.ru/");
        driver.findElement(By.cssSelector("[name=\"fio\"]")).sendKeys("fail_fio");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys("fail_mail");
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.cssSelector("div.Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.cssSelector("[name=\"phone\"]")).sendKeys("+7 (111) 111-11-11");
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) > .FormField__field_1iwkM:nth-child(1) > .Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.cssSelector("[name=\"city\"]")).sendKeys("fa1l");
        assertEquals("Код города/оператора должен начинаться с цифры 3, 4, 5, 6, 8, 9", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(2) > .FormField__field_1iwkM:nth-child(2) > .Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.cssSelector("div.SelectWrap__root_35mlc")).click();
        assertEquals("Допустимо использовать только буквы русского, латинского алфавита и дефис", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(3) > .FormField__field_1iwkM:nth-child(1) > .Error__errorMessage_q8BBY")).getText());
        driver.findElement(By.cssSelector("[name=\"city\"]")).click();
        assertEquals("Поле обязательное", driver.findElement(By.cssSelector(".Row__row_AjrJL:nth-child(4) > .FormField__field_1iwkM:nth-child(1) > .Error__errorMessage_q8BBY")).getText());
    }

    @Test
    public void case1() {
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
    public void case2() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.cssSelector("span.MvnoRegionConfirmation__option_3mrvz.MvnoRegionConfirmation__optionAgreement_3M5qT")).click();
        assertEquals("Москва и Московская область",driver.findElement(By.cssSelector("div.MvnoRegionConfirmation__title_3WFCP")).getText());
        driver.navigate().refresh();
        assertEquals("Москва и Московская область",driver.findElement(By.cssSelector("div.MvnoRegionConfirmation__title_3WFCP")).getText());
        String priceMsk = driver.findElement(By.cssSelector("[data-qa-file=\'UITitle\'][class=\'ui-title ui-title_form ui-title_center ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn\']")).getText();
        driver.findElement(By.cssSelector("[data-qa-file=\'MvnoRegionConfirmation\'][class=\'MvnoRegionConfirmation__wrapper_3Qh_H MvnoRegionConfirmation__wrapperSelected_1X_zH\']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Краснодар')]")).click();
        driver.navigate().refresh();
        String priceKrasnodar = driver.findElement(By.cssSelector("[data-qa-file=\'UITitle\'][class=\'ui-title ui-title_form ui-title_center ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn\']")).getText();
        assertNotEquals(priceKrasnodar, priceMsk);

        driver.findElement(By.cssSelector("[data-qa-file=\'UISelectTitle\'][class=\'ui-select__title ui-select__title_columned\']")).click();
        driver.findElement(By.cssSelector(".ui-form__row:nth-child(1) .ui-dropdown-field-list__item:nth-child(6) .ui-dropdown-field-list__item-text")).click();
        driver.findElement(By.xpath("//div[@id='form-application']/div[2]/div/form/div/div[3]/div/div[2]/div/div/div/div/div/div/div[2]")).click();
        driver.findElement(By.cssSelector(".ui-form__row:nth-child(2) .ui-dropdown-field-list__item:nth-child(6) .ui-dropdown-field-list__item-text")).click();
        driver.findElement(By.cssSelector("[data-qa-file=\'UICheckbox\'][class=\'ui-checkbox \']")).click();
        driver.findElement(By.xpath("//div[@id='form-application']/div[2]/div/form/div/div[5]/div/div[2]/div/div/div/div/label")).click();
        String maxPriceKrasnodar = driver.findElement(By.cssSelector("[data-qa-file=\'UITitle\'][class=\'ui-title ui-title_form ui-title_center ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn\']")).getText();
        //System.out.println(maxPriceKrasnodar);

        driver.findElement(By.cssSelector("[data-qa-file=\'MvnoRegionConfirmation\'][class=\'MvnoRegionConfirmation__wrapper_3Qh_H MvnoRegionConfirmation__wrapperSelected_1X_zH\']")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Москва')]")).click();

        driver.findElement(By.cssSelector("[data-qa-file=\'UISelectTitle\'][class=\'ui-select__title ui-select__title_columned\']")).click();
        driver.findElement(By.cssSelector(".ui-form__row:nth-child(1) .ui-dropdown-field-list__item:nth-child(6) .ui-dropdown-field-list__item-text")).click();
        driver.findElement(By.xpath("//div[@id='form-application']/div[2]/div/form/div/div[3]/div/div[2]/div/div/div/div/div/div/div[2]")).click();
        driver.findElement(By.cssSelector(".ui-form__row:nth-child(2) .ui-dropdown-field-list__item:nth-child(6) .ui-dropdown-field-list__item-text")).click();
        driver.findElement(By.cssSelector("[data-qa-file=\'UICheckbox\'][class=\'ui-checkbox \']")).click();
        driver.findElement(By.xpath("//div[@id='form-application']/div[2]/div/form/div/div[5]/div/div[2]/div/div/div/div/label")).click();
        String maxPriceMsk = driver.findElement(By.cssSelector("[data-qa-file=\'UITitle\'][class=\'ui-title ui-title_form ui-title_center ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn\']")).getText();
        //driver.findElement(By.cssSelector("[data-qa-file=\'UISelectTitle\'][class=\'ui-select__title ui-select__title_columned\']")).click();

        //System.out.println(maxPriceMsk);
        assertEquals(maxPriceMsk, maxPriceKrasnodar);
    }

    @Test
    public void case3() {
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        driver.findElement(By.cssSelector("span.ui-checkbox__text-wrapper.ui-checkbox__description-wrapper")).click();
        driver.findElement(By.cssSelector(".ui-form__row:nth-child(2) > .ui-form__field .ui-checkbox__text-wrapper")).click();

        driver.findElement(By.cssSelector("[data-qa-file=\'UISelectTitle\'][class=\'ui-select__title ui-select__title_columned\']")).click();
        driver.findElement(By.cssSelector(".ui-form__row:nth-child(1) .ui-dropdown-field-list__item:nth-child(1) .ui-dropdown-field-list__item-text")).click();

        driver.findElement(By.xpath("//div[@id='form-application']/div[2]/div/form/div/div[3]/div/div[2]/div/div/div/div/div/div/div[2]")).click();
        driver.findElement(By.cssSelector(".ui-form__row:nth-child(2) .ui-dropdown-field-list__item:nth-child(1) .ui-dropdown-field-list__item-text")).click();

        String lowPric = driver.findElement(By.cssSelector("[data-qa-file=\'UITitle\'][class=\'ui-title ui-title_form ui-title_center ui-title_size_s MobileOperatorFormFieldTitle__title_2awZp mobileOperatorProductCalculatorSchema__amountTitle_6kgKn\']")).getText();
        assertEquals(lowPric, "Общая цена: 0 \u20BD");
        assertFalse(driver.findElement(By.cssSelector("[class=\'Button__button_ZsAp- BlockingButton-theme__button_1mhBY Button__button_relative_3aJsS Button__button_color_black_3jFBq Button__button_disabled_19Ety Button__button_rounded_1Eg4W Button__button_size_xxl_1_2X-\']")).isEnabled());
    }
}

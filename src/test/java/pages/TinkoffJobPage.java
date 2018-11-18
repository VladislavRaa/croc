package pages;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class TinkoffJobPage extends Page {
    public TinkoffJobPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public TinkoffJobPage openPage() {
        driver.get("https://moscow-job.tinkoff.ru/");
        logger.info("Открытие страницы: moscow-job.tinkoff.ru");
        return this;
    }
    public TinkoffJobPage typeNameField(String value){
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("fio")).click();
                    driver.findElement(By.name("fio")).clear();
                    driver.findElement(By.name("fio")).sendKeys(value);
                    logger.info(String.format("Заполнение поля 'Фамилия, имя и отчество': \"%s\"", value));
                    return true;
                });
        return this;
    }
    public TinkoffJobPage typeEmailField(String value){
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("email")).click();
                    driver.findElement(By.name("email")).clear();
                    driver.findElement(By.name("email")).sendKeys(value);
                    logger.info(String.format("Заполнение поля 'Электронная почта': \"%s\"", value));
                    return true;
                });
        return this;
    }
    public TinkoffJobPage typePhoneField(String value){
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("phone")).click();
                    driver.findElement(By.name("phone")).clear();
                    driver.findElement(By.name("phone")).sendKeys(value);
                    logger.info(String.format("Заполнение поля 'Номер телефона': \"%s\"", value));
                    return true;
                });
        return this;
    }
    public TinkoffJobPage typeCityField(String value) {
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.name("city")).click();
                    driver.findElement(By.name("city")).clear();
                    driver.findElement(By.name("city")).sendKeys(value);
                    logger.info(String.format("Заполнение поля 'Город': \"%s\"", value));
                    return true;
                });
        return this;
    }
    public TinkoffJobPage typeVacancyField() {
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class)
                .until(d -> {
                    driver.findElement(By.cssSelector("div.SelectWrap__root_35mlc")).click();
                    logger.info("Заполнение поля 'Выберите вакансию'");
                    return true;
                });
        return this;
    }
    public TinkoffJobPage checkErrorEmptyNameField(){
        driver.findElement(By.xpath("//*[@name='fio']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@data-qa=\"fio\"]/../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
        logger.info("Пустое поле 'Фамилия, имя и отчество'");
        return this;
    }
    public TinkoffJobPage checkErrorEmptyEmailField(){
        driver.findElement(By.xpath("//*[@name='email']")).click();
        driver.findElement(By.xpath("//*[@name='fio']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@data-qa=\"email\"]/following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
        logger.info("Пустое поле 'Электронная почта'");
        return this;
    }
    public TinkoffJobPage checkErrorEmptyPhoneField(){
        driver.findElement(By.xpath("//*[@name='phone']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("//*[contains(text(),'Необходимо указать номер телефона')]")).getText());
        logger.info("Пустое поле 'Номер телефона'");
        return this;
    }
    public TinkoffJobPage checkErrorEmptyCityField(){
        driver.findElement(By.xpath("//*[@name='city']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@name='city']/ancestor::div[@class='Row__row_AjrJL']//div[@class='Error__errorMessage_q8BBY']")).getText());
        logger.info("Пустое поле 'Город'");
        return this;
    }
    public TinkoffJobPage checkEmptyVacancyField() {
        driver.findElement(By.xpath("//*[contains(text(),'Выберите вакансию')]")).click();
        driver.findElement(By.xpath("//*[@name='email']")).click();
        assertEquals("Поле обязательное", driver.findElement(By.xpath("//*[@role=\"listbox\"]/../following-sibling::div[@class='Error__errorMessage_q8BBY']")).getText());
        logger.info("Пустое поле 'Выберите вакансию'");
        return this;
    }
    public void checkPopularNameRequest(String surname) {
        wait.until(d-> {
            boolean match = false;
            String messageResponse;
            String response;
            List<String> responseList = new ArrayList<>();
            for (LogEntry entry : driver.manage().logs().get(LogType.PERFORMANCE)) {
                messageResponse = entry.getMessage();
                if (messageResponse.contains("get_popular_names") && messageResponse.contains("requestWillBeSent")) {
                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(messageResponse);
                        response = jsonObject.getJSONObject("message").getJSONObject("params").getJSONObject("request").get("postData").toString();
                        response = URLDecoder.decode(response, "UTF-8");
                        logger.info(response);
                        responseList.add(response);
                    } catch (JSONException e) {
                        logger.error("JSONException: Ошибка преобразования JSON объекта, для получения параметров операции", e);
                    } catch (UnsupportedEncodingException e) {
                        logger.error("UnsupportedEncodingException: Ошибка преобразования строки в utf-8", e);
                    } catch (NullPointerException e) {
                        logger.error("NullPointerException: Метод get_popular_names не выполнился", e);
                    }
                }
            }
            for (String res : responseList) {
                if (res.contains(surname)) {
                    match = true;
                    break;
                }
            }
            return match;
        });
    }
}

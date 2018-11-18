package test;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;

public class StarterTest extends BaseRunner {
    public StarterTest() {
        baseUrl = "https://moscow-job.tinkoff.ru/";
    }

    @Test
    public void testEmptyFields() {
        driver.get(baseUrl);
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
        driver.get(baseUrl);
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
}

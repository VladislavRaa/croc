package pages;

import app.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultPage extends Page {
    public GoogleResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickSearchResultsByLinkContains(String link){
        wait.until(d -> xpathSearcherByText(link).size() > 0);
        xpathSearcherByText(link).get(0).click();
    }

    public void inputBySearchField(String value){
        By searchField = By.name("q");
        String testText = "Проба ввода текста";

        /*демонстрация Actions
        на поисковое поле устанавливаетя фокус
        выполняется клик
        с помощью сочетания клавиш CTRL+A+DELETE очищается содержимое поля
        делее, заполняется поле новым значением
         */
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(searchField))
                .click()
                .sendKeys(Keys.CONTROL + "a" + Keys.DELETE)
                .sendKeys(testText)
                .sendKeys(Keys.ENTER)
                .perform();

        //с полем input getText()  не сработает, значение нужно явно забрать из атрибута value
        wait.until(d -> d.findElement(searchField)
                .getAttribute("value")
                .equals(testText));
    }
}

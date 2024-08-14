package pages;

import factory.DriverFactory;
import io.cucumber.java.et.Ja;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    WebDriver webDriver;
    public HomePage(WebDriver driver){
        this.webDriver = driver;
    }
    @FindBy(xpath="//span[@class='logged-in'][1]")
    WebElement welcomeElement;

    @FindBy(id="ui-id-4")
    WebElement womenTabElement;

    @FindBy(id="ui-id-9")
    WebElement topsTabElement;

    @FindBy(id="ui-id-11")
    WebElement jacketTabElement;

    public String getUsername(){
        return welcomeElement.getText().split(",")[1].trim().replace(" ", "");
    }

    public JacketPage clickJacket(){

        Actions actions = new Actions(webDriver);
        actions.moveToElement(womenTabElement)
                .moveToElement(topsTabElement)
                .moveToElement(jacketTabElement)
                .click()
                .build()
                .perform();
        return new JacketPage(webDriver);
    }

}

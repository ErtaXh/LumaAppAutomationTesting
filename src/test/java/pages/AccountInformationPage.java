package pages;


import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountInformationPage extends BasePage {

    WebDriver driver;
    public  AccountInformationPage(WebDriver driver){
        this.driver = driver;
    }
    @FindBy(xpath = "//div[contains(@data-bind, 'prepareMessageForHtml')]")
    public WebElement successfulTextElement;

    @FindBy(xpath="//button[@class='action switch'][1]")
    WebElement dropdownElement;

    @FindBy(xpath="//a[contains(text(),'Sign Out ')][1]")
    WebElement signOutElement;

    public MainPage clickSignOut(){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));
        dropdownElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(signOutElement));
        signOutElement.click();
        return new MainPage(driver);
    }

    public boolean isRegistrationTextDisplayed(){
        return successfulTextElement.isDisplayed();
    }

}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.ConfigReader;

public class LoginPage extends BasePage{

    WebDriver driver;

    @FindBy(id="email")
    WebElement emailElement;

    @FindBy(id="pass")
    WebElement passwordElement;

    @FindBy(id="send2")
    WebElement sigInButtonElement;


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }


    public String getTitle(){
        return driver.getTitle();
    }

    public HomePage login(){
        ConfigReader configReader = new ConfigReader();
        emailElement.sendKeys(configReader.getProperty("email"));
        passwordElement.sendKeys(configReader.getProperty("password"));
        sigInButtonElement.click();
        return new HomePage(driver);
    }


}

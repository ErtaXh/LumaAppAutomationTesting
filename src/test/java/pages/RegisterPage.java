package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.ConfigReader;

import java.util.Random;

public class RegisterPage extends BasePage {

    WebDriver driver;

    @FindBy(id="firstname")
    public WebElement firstNameElement;

    @FindBy(id="lastname")
    public WebElement lastNameElement;

    @FindBy(id="email_address")
    public WebElement emailElement;

    @FindBy(id="password")
    public WebElement passwordElement;

    @FindBy(id="password-confirmation")
    public WebElement passwordConfirmationElement;

    @FindBy(css="button[class='action submit primary']")
    public WebElement submitButtonElement;
    public  RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillForm(String firstName,String lastName,String password){
        ConfigReader configReader = new ConfigReader();
        String email = firstName+lastName+ new Random().nextInt()+"@gmail.com";
        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);
        passwordConfirmationElement.sendKeys(password);

        configReader.setProperty("username",firstName+lastName);
        configReader.setProperty("email",email);
        configReader.setProperty("password",password);

    }

    public AccountInformationPage submit(){
       submitButtonElement.click();
        return new AccountInformationPage(driver);
    }

}

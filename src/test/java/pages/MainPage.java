package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static factory.DriverFactory.getDriver;

public class MainPage extends BasePage {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Create an Account')]")
    public WebElement createAccountLink;

    @FindBy(xpath = "//a[contains(text(), 'Sign In')][1]")
    public WebElement signInElement;


    public  MainPage(WebDriver driver){
        this.driver = driver;
    }

    public RegisterPage clickCreateAccountLink(){

        createAccountLink.click();

        return new RegisterPage(driver);
    }

    public String getHomePageTitle(){
     return   driver.getTitle();
    }

    public boolean isSignInLinkDisplayed(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInElement));
        return signInElement.isDisplayed();
    }

    public LoginPage clickLoginLink(){
        signInElement.click();
        return new LoginPage(driver);
    }

}

package pages;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishListPage extends BasePage{

    private final WebDriver webDriver;

    @FindBy(xpath="//div[contains(@class, 'message-success') and contains(@class, 'success')]")
    WebElement messageElement;

    @FindBy(xpath="//button[@class='action switch'][1]")
    WebElement dropdownElement;

    @FindBy(xpath="//span[@class='counter qty'][1]")
    WebElement wishListItemCountElement;


    public WishListPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }


    public boolean isMessageDisplayed(){
        return messageElement.isDisplayed();
    }

    public int getWishListCount(){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));
        dropdownElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(wishListItemCountElement));
       return Integer.parseInt(wishListItemCountElement.getText().split(" ")[0]);

    }
}

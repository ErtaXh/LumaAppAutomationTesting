package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static factory.DriverFactory.getDriver;

public class ShoppingCartPage extends BasePage{
    WebDriver webDriver;

    @FindBy(css=".grand .amount .price")
    WebElement orderTotalPriceElement;

    @FindBy(css=".subtotal .price-excluding-tax .cart-price  .price")
    List<WebElement> itemPriceElements;

    @FindBy(xpath="//a[@class='action action-delete']")
    List<WebElement> deleteItemElements;

    @FindBy(xpath="//tr[@class='item-info']")
    List<WebElement> rowElements;

    @FindBy(css="div .cart-empty")
    WebElement emptyCartTextElement;

    public ShoppingCartPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getTitle(){
        return webDriver.getTitle();
    }

    public double getOrderTotalPrice(){

        return Double.parseDouble(orderTotalPriceElement.getText().replace("$",""));
    }

    public double calculatePrice(){
       double price = 0;
        for(WebElement priceElement:itemPriceElements){
          price +=  Double.parseDouble(priceElement.getText().replace("$",""));
        }

        return price;
    }

    public void deleteFirstElement(){
        deleteItemElements.get(0).click();
    }

    public int getNumberOfItems(){
        return rowElements.size();
    }

    public void deleteAllTheItems() {
        int size = itemPriceElements.size();


        for (int i = 0; i < size; i++) {
            List<WebElement> deleteButtons = getDriver().findElements(By.xpath("//a[@class='action action-delete']"));

            deleteButtons.get(0).click();

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
            wait.until(ExpectedConditions.stalenessOf(deleteButtons.get(0))); // Wait for the page to refresh
        }
    }

    public boolean isCartEmpty(){
        return emptyCartTextElement.isDisplayed();
    }
}

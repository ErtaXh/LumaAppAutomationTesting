package pages;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class JacketPage extends BasePage{

    WebDriver webDriver;

   public JacketPage(WebDriver webDriver){
       this.webDriver = webDriver;
   }

    @FindBy(xpath = "//div[contains(text() , 'Color' )]")
    WebElement colorDropdownElement;

    @FindBy(xpath = "//div[contains(text() , 'Size' )]")
    WebElement sizeDropdownElement;

    @FindBy(xpath = "//div[contains(text() , 'Price' )]")
    WebElement priceDropdownElement;

    @FindBy(xpath = "//ol[@class='items']/li[1]/a[span[contains(text(),'$50.00')]]")
    WebElement priceItemElement;

   @FindBy(xpath = "//div[@class='swatch-option color ' and @option-label='Red']")
   WebElement colorElement;

    @FindBy(xpath = "//div[@class='swatch-option text ' and @option-label='XS']")
    WebElement sizeElement;

   @FindBy(xpath="//div[@class='swatch-option color selected']")
    List<WebElement> jacketColorElement;

   @FindBy(xpath="//span[@class='price']")
   List<WebElement> priceElements;

   @FindBy(xpath="//a[@title='Remove Price $50.00 - $59.99']")
   WebElement removePriceElement;

   @FindBy(xpath = "//p[@class='toolbar-amount']//span[@class='toolbar-number'][1]")
   WebElement itemsNumberElement;

   @FindBy(xpath = "//a[@class='action towishlist']")
   List<WebElement> wishListElement;

    @FindBy(xpath = "//form/button[@title='Add to Cart']")
    List<WebElement> addToCartElements;

   @FindBy(xpath="//div[@class='product-item-info']")
   List<WebElement> productDetailsElements;


    @FindBy(xpath="//div[@class='message-success success message']")
    WebElement messageElement;

    @FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/checkout/cart/' and text()='shopping cart']")
    WebElement shoppingCartLink;


   public void chooseColor(){
       WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
       colorDropdownElement.click();
       wait.until(ExpectedConditions.elementToBeClickable(colorElement));
       colorElement.click();

   }

    public void chooseSize(){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
        sizeDropdownElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(sizeElement));
        sizeElement.click();

    }

   public  boolean areAllBlack(){


       for(WebElement color:jacketColorElement){

          if (!color.getAttribute("aria-checked").equals("true"))
         return false;

       }

return true;
   }

   public void selectPriceRange(){
        priceDropdownElement.click();
       WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(priceItemElement));
       priceItemElement.click();


   }

   public boolean checkPrices(double minimum, double maximum){
       for(WebElement priceElement:priceElements){
        double price =  Double.parseDouble( priceElement.getText().replace("$",""));
        if(price<minimum || price > maximum)
         return false;
       }
       return true;
   }

   public void removePriceFilter(){
       removePriceElement.click();
   }

   public int getNumberOfItems(){

       return Integer.parseInt(itemsNumberElement.getText());
   }

   public WishListPage addItemsInWishList(int itemsNumber){

       Actions actions = new Actions(webDriver);
       for(int i=1; i<itemsNumber;i++){
           WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
           wait.until(ExpectedConditions.elementToBeClickable(productDetailsElements.get(i-1)));
           actions.moveToElement(productDetailsElements.get(i-1)).moveToElement(wishListElement.get(i-1)).click().build().perform();
           DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
           DriverFactory.getDriver().navigate().back();

       }
       actions.moveToElement(productDetailsElements.get(itemsNumber-1)).moveToElement(wishListElement.get(itemsNumber-1)).click().build().perform();
       return new WishListPage(webDriver);
   }

   public void addToCart(int size)  {

       Actions actions = new Actions(webDriver);

       for (int i = 1; i <= size; i++) {
           WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
           wait.until(ExpectedConditions.elementToBeClickable(productDetailsElements.get(i - 1)));
           actions.moveToElement(productDetailsElements.get(i - 1)).moveToElement(addToCartElements.get(i-1)).click().build().perform();
            wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
       }
   }

   public boolean isMessageDisplayed(){

       return messageElement.isDisplayed();
   }

   public ShoppingCartPage clickShoppingCartLink()  {
       shoppingCartLink.click();
       return new ShoppingCartPage(webDriver);
   }
}

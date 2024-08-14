package definitions;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.JacketPage;
import pages.ShoppingCartPage;

public class ShoppingCartSteps {

    JacketPage jacketPage = new JacketPage(DriverFactory.getDriver());
    ShoppingCartPage shoppingCartPage;

    HomePage homePage = new HomePage(DriverFactory.getDriver());

    @When("user adds all displayed items to the Shopping Cart")
    public void user_adds_all_displayed_items_to_the_shopping_cart()  {
        jacketPage.chooseSize();
       int items = jacketPage.getNumberOfItems();
        jacketPage.addToCart(items);
    }
    @Then("the success Add to Cart message is displayed")
    public void the_success_add_to_cart_message_is_displayed() {
        Assert.assertTrue(jacketPage.isMessageDisplayed());
    }


    @Given("the user has added all the items in the cart")
    public void the_user_has_added_all_the_items_in_the_cart() {
        jacketPage =  homePage.clickJacket();
        jacketPage.chooseColor();
        jacketPage.selectPriceRange();
        jacketPage.chooseSize();
        int size = jacketPage.getNumberOfItems();

        jacketPage.addToCart(size);
    }
    @When("the user clicks on the Shopping Cart link")
    public void theUserClicksOnTheShoppingCartLink() {
        shoppingCartPage =    jacketPage.clickShoppingCartLink();
    }


    @Then("the user is navigated to the {string} Page")
    public void theUserIsNavigatedToThePage(String title) {
        Assert.assertEquals(title,shoppingCartPage.getTitle());
    }
    @Then("the Order Total price is correct")
    public void the_order_total_price_is_correct() {


        Assert.assertTrue(shoppingCartPage.getOrderTotalPrice()==shoppingCartPage.calculatePrice());
    }


}

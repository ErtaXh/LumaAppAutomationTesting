package definitions;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.JacketPage;
import pages.ShoppingCartPage;

public class EmptyCartSteps {
    HomePage homePage = new HomePage(DriverFactory.getDriver());
    JacketPage jacketPage;

    ShoppingCartPage shoppingCartPage;
    int size;



    @Given("the user is in Shopping cart with all items added")
    public void the_user_is_in_shopping_cart_with_all_items_added() {
        jacketPage =  homePage.clickJacket();
        jacketPage.chooseColor();
        jacketPage.selectPriceRange();
         size = jacketPage.getNumberOfItems();
        jacketPage.chooseSize();
        jacketPage.addToCart(size);
      shoppingCartPage =  jacketPage.clickShoppingCartLink();
    }
    @When("user deletes the first item on shopping cart")
    public void user_deletes_the_first_item_on_shopping_cart() {
 shoppingCartPage.deleteFirstElement();
    }
    @Then("the number of elements in the Shopping Cart table is decreased by one")
    public void the_number_of_elements_in_the_shopping_cart_table_is_decreased_by_one() {
        Assert.assertTrue(size==shoppingCartPage.getNumberOfItems()+1);
    }

    @When("the user deletes one by one the items added")
    public void the_user_deletes_one_by_one_the_items_added() {
     shoppingCartPage.deleteAllTheItems();
    }
    @Then("the Shopping cart number should be empty")
    public void the_shopping_cart_number_should_be_empty() {

        Assert.assertTrue(shoppingCartPage.isCartEmpty());
    }
}

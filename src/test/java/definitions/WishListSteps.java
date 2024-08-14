package definitions;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.JacketPage;
import pages.WishListPage;


public class WishListSteps {

    HomePage homePage = new HomePage(DriverFactory.getDriver());


    int filterAppliedItemCount, noFilterItemCount;
    JacketPage jacketPage;

    WishListPage wishListPage;
    @Given("the user is on Jacket Page with filters applied")
    public void the_user_is_on_jacket_page_with_filters_applied() {
        jacketPage =  homePage.clickJacket();
        jacketPage.chooseColor();
        jacketPage.selectPriceRange();
    }
    @When("the user removes the Price filter")
    public void the_user_removes_the_price_filter() {
        filterAppliedItemCount = jacketPage.getNumberOfItems();
        jacketPage.removePriceFilter();
        noFilterItemCount = jacketPage.getNumberOfItems();

    }
    @Then("the number of displayed items should increase")
    public void the_number_of_displayed_items_should_increase() {
        Assert.assertTrue(filterAppliedItemCount<=noFilterItemCount);
    }

    @Given("the user is on Jacket Page with color filter applied")
    public void the_user_is_on_jacket_page_with_color_filter_applied() {
        jacketPage =  homePage.clickJacket();
        jacketPage.chooseColor();
        jacketPage.selectPriceRange();
        jacketPage.removePriceFilter();
    }

    @When("the user adds the {int} items in the Wish List")
    public void theUserAddsTheItemsInTheWishList(int items) {
     wishListPage =   jacketPage.addItemsInWishList(items);
    }

    @Then("the success message is displayed")
    public void the_success_message_is_displayed() {
        Assert.assertTrue( wishListPage.isMessageDisplayed());
    }

    @And("on User Profile is displayed the correct number of items {int}")
    public void onUserProfileIsDisplayedTheCorrectNumberOfItems(int itemsCount) {

        Assert.assertTrue(wishListPage.getkWishListCount() == itemsCount);
    }
}

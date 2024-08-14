package definitions;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.List;
import java.util.Map;

public class FiltersStep {

    RegisterPage registerPage;
    MainPage mainPage = new MainPage(DriverFactory.getDriver());
    LoginPage loginPage;

    HomePage homePage;

    AccountInformationPage accountInformationPage;

    JacketPage jacketPage;


    @Given("the user has already logged in to the application")
    public void the_user_has_already_logged_in_to_the_application(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String firstname = credentials.get(0).get("firstName");
        String lastname = credentials.get(0).get("lastName");
        String password = credentials.get(0).get("password");
        DriverFactory.getDriver().navigate().to("https://magento.softwaretestingboard.com/");
        registerPage = mainPage.clickCreateAccountLink();
        registerPage.fillForm(firstname,lastname,password);
        accountInformationPage =  registerPage.submit();
        mainPage =  accountInformationPage.clickSignOut();
        loginPage = mainPage.clickLoginLink();
        homePage=loginPage.login();
    }
    @When("the user navigates to the Women section, hovers over the Tops dropdown, and clicks on Jacket")
    public void the_user_navigates_to_the_women_section_hovers_over_the_tops_dropdown_and_clicks_on_jacket() {
      jacketPage =  homePage.clickJacket();
    }
    @And("the user selects a color from the Color dropdown")
    public void the_user_selects_a_color_from_the_color_dropdown() {
            jacketPage.chooseColor();
    }
    @Then("all displayed products should have the selected color bordered in red")
    public void all_displayed_products_should_have_the_selected_color_bordered_in_red() {
        Assert.assertTrue(jacketPage.areAllBlack());
    }

    @When("the user is on the jackets page with color filter applied")
    public void the_user_is_on_the_jackets_page_with_color_filter_applied() {
        jacketPage =  homePage.clickJacket();
        jacketPage.chooseColor();

    }



    @And("the user selects the first price range from the Price dropdown")
    public void theUserSelectsTheFirstPriceRangeFromThePriceDropdown() {
        jacketPage.selectPriceRange();
    }

    @Then("each displayed product should have a price within the selected range")
    public void each_displayed_product_should_have_a_price_within_the_selected_range() {
Assert.assertTrue(jacketPage.checkPrices(50.00,59.99));
    }
}

package definitions;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AccountInformationPage;
import pages.HomePage;
import pages.MainPage;
import pages.RegisterPage;

import java.util.List;
import java.util.Map;

public class RegisterPageSteps {

    private MainPage mainPage = new MainPage(DriverFactory.getDriver());
    private RegisterPage registerPage;

    private HomePage homePage;

    private AccountInformationPage accountInformationPage;

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        DriverFactory.getDriver().navigate().to("https://magento.softwaretestingboard.com/");
    }

    @When("the user clicks on Create an Account link")
    public void the_user_clicks_on_create_an_account_link() {
      registerPage =  mainPage.clickCreateAccountLink();    }

    @Then("the {string} page is displayed")
    public void the_page_is_displayed(String expectedTitle) {
        Assert.assertTrue(mainPage.getHomePageTitle().contains(expectedTitle));
    }

    @Given("the user is on the Create New Customer Account page")
    public void the_user_is_on_the_create_new_customer_account_page() {
        DriverFactory.getDriver().navigate().to("https://magento.softwaretestingboard.com/");
        registerPage = mainPage.clickCreateAccountLink();
    }
    @When("the user enters valid account details")
    public void the_user_enters_valid_account_details(DataTable dataTable) {

        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);


            String firstname = credentials.get(0).get("firstName").trim();
            String lastname = credentials.get(0).get("lastName").trim();
            String password = credentials.get(0).get("password").trim();

            System.out.println(firstname);
            System.out.println(lastname);
            System.out.println(password);
           registerPage.fillForm(firstname,lastname,password);

    }

    @When("the user submits the account creation form")
    public void the_user_submits_the_account_creation_form() {
       accountInformationPage = registerPage.submit();
    }


    @Then("a success message is displayed")
    public void aSuccessMessageIsDisplayed() {
        Assert.assertTrue(accountInformationPage.isRegistrationTextDisplayed());
    }

    @Given("the user has successfully created an account")
    public void the_user_has_successfully_created_an_account() {
        DriverFactory.getDriver().navigate().to("https://magento.softwaretestingboard.com/");
        registerPage = mainPage.clickCreateAccountLink();
        registerPage.fillForm("erta","lastname","Password123!");
        accountInformationPage = registerPage.submit();
    }
    @When("the user clicks on Sign Out button in User profile")
    public void the_user_clicks_on_sign_out_button_in_user_profile() {

      mainPage =  accountInformationPage.clickSignOut();
    }


    @Then("the user is logged out")
    public void the_user_is_logged_out() {
        Assert.assertTrue(mainPage.isSignInLinkDisplayed());
    }

}

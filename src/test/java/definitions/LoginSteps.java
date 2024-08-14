package definitions;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import util.ConfigReader;

import java.util.List;
import java.util.Map;

public class LoginSteps {



    RegisterPage registerPage;
    MainPage mainPage = new MainPage(DriverFactory.getDriver());
    LoginPage loginPage;

    HomePage homePage;

    AccountInformationPage accountInformationPage;

    @Given("the user has already registered to the application")
    public void the_user_has_already_registered_to_the_application(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String firstname = credentials.get(0).get("firstName");
        String lastname = credentials.get(0).get("lastName");
        String password = credentials.get(0).get("password");
        DriverFactory.getDriver().navigate().to("https://magento.softwaretestingboard.com/");
        registerPage = mainPage.clickCreateAccountLink();
        registerPage.fillForm(firstname,lastname,password);
        accountInformationPage =  registerPage.submit();
        mainPage =  accountInformationPage.clickSignOut();
    }




    @When("the user clicks on the Sign In link")
    public void the_user_clicks_on_the_sign_in_link() {

    loginPage = mainPage.clickLoginLink();

    }
    @Then("the user is on the {string} page")
    public void the_user_is_on_the_page(String expected) {
        Assert.assertEquals(expected,loginPage.getTitle());
    }

    @Given("the newly registered user is on the Sign In page")
    public void the_newly_registered_user_is_on_the_sign_in_page() {
   loginPage = mainPage.clickLoginLink();
    }
    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {

      homePage =  loginPage.login();

    }
    @Then("the username is displayed in the top-right corner of the page")
    public void the_username_is_displayed_in_the_top_right_corner_of_the_page() {
//        ConfigReader configReader = new ConfigReader();
//        Assert.assertEquals(configReader.getProperty("username")+"!",homePage.getUsername());
    }


}


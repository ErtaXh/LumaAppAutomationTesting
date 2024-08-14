package hooks;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.Scenario;
import util.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class AppHooks {

    private DriverFactory driverFactory;
    private ConfigReader configReader;
    Properties properties;


    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        properties = configReader.initProperties();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = properties.getProperty("browser");
        driverFactory = new DriverFactory();
        driverFactory.initDriver(browserName);
    }

    @After(order = 0)
    public void quitBrowser() {
        DriverFactory.getDriver().quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);

            File screenshotFile = new File("screenshots/" + screenshotName + ".png");
            try {
                FileUtils.writeByteArrayToFile(screenshotFile, sourcePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

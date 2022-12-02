package baseTest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseTests {

    /*
     Make the web driver static so that the instance will always be certain
     */
    protected static WebDriver driver;
    private String currentMethodName = null;

    /*
        Children should answer if we should capture screenshots on success.
     */
    abstract boolean shouldCaptureScreenshot();

    protected HomePage homePage; /* access modifier */

    /*
        Set the test name before execution so we have a reference to it for saving
        screenshots manually.
     */
    @BeforeMethod
    public void setTestName(Method method)
    {
        currentMethodName = method.getName();
    }

    @BeforeSuite
    public void setupBeforeAnyTestsExecute(){

        // to run in headless
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        // set up chrome driver
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver(options); /* added options */
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }

    @BeforeClass
    public void setupForEachTest(){
        // Because the driver property has been instantiated and is static, it is guaranteed
        // to have a reference without having to instantiate it again
        homePage = new HomePage(driver);
    }

    /*
       Captures a screenshot based on the shouldCaptureScreenshot method defined.
       The code will however always capture a screenshot in the event of failure.
     */
    @AfterMethod
    public void captureScreenShot(ITestResult testResult){

        // if test status is a fail
        if(ITestResult.FAILURE  == testResult.getStatus()){

            TakesScreenshot screenshot = (TakesScreenshot) driver; /* take a screenshot */
            File source = screenshot.getScreenshotAs(OutputType.FILE); /*get the screenshot as a file */

            // store unique screen short according to the test name plus time
            File destination = new File(System.getProperty("user.dir") + "/resources/screenshots/failed/" + testResult.getName()+ "_" + testResult.getStartMillis() + ".png");

            try {
                FileHandler.copy(source,destination);/*hover and more actions then select try catch */
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    /* Manually captures a screenshot as the navigation takes place BEFORE the @AfterMethod execution */
    protected void TakeManualScreenshot() {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);

        Date today = Calendar.getInstance().getTime();
        Format dateFormat = new SimpleDateFormat("HH.mm.ss.SSS");

        File destination = new File(System.getProperty("user.dir") + "/resources/screenshots/passed/" + currentMethodName + dateFormat.format(today) + ".png");

        try {
            FileHandler.copy(source,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterSuite
    public void tearDown(){driver.quit();} /* to quit session */

}

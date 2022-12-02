package baseTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTests {

    /* Implements contract as defined by abstract base class */
    boolean shouldCaptureScreenshot() {
        return true;
    }

    @Test(priority = 1, dataProvider = "LoginData")
    public void PerformLogin(String UserName, String PassWord){

      LoginPage loginPage = homePage.clickMakeApp();
      loginPage.setUserName(UserName);
      loginPage.setPassWord(PassWord);

      TakeManualScreenshot();
      loginPage.clickLogin();

        // assert you've logged into the appointment page
        String logPage = "https://katalon-demo-cura.herokuapp.com/#appointment";
        String curPage = driver.getCurrentUrl();
        Assert.assertEquals(logPage,curPage);

    }

    @DataProvider
    public Object[][] LoginData(){

        Object[][] data = new Object[1][2];
        data[0][0] = "John Doe";
        data[0][1] = "ThisIsNotAPassword";

        return data;
    }
}

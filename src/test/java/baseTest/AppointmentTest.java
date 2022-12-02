package baseTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AppointmentConfPage;
import pages.AppointmentPage;

public class AppointmentTest extends BaseTests {

    /* Implements contract as defined by abstract base class */
    boolean shouldCaptureScreenshot() {
        return false;
    }

    @Test(priority = 2, dataProvider = "AppData")
    public void bookAppTest(String Facility, String VisitDate, String Comment, Integer ProgInt){

        AppointmentPage appointmentPage = new AppointmentPage(driver);

        appointmentPage.setFacility(Facility);
        appointmentPage.clickHospReAdd();
        appointmentPage.clickPrograms(ProgInt);
        appointmentPage.setVisitDate(VisitDate);
        appointmentPage.setComment(Comment);
        appointmentPage.clickBookApp();

        // assert you're on the confirmation page
        String logPage = "https://katalon-demo-cura.herokuapp.com/appointment.php#summary";
        String curPage = driver.getCurrentUrl();
        Assert.assertEquals(logPage,curPage);

        // check data, take screenshot and click the Go to Homepage button
        AppointmentConfPage appointmentConfPage = new AppointmentConfPage(driver);
        String facilityChk = driver.findElement(appointmentConfPage.facilityConf).getText();
        Assert.assertEquals(facilityChk, Facility);
        String visitDateChk = driver.findElement(appointmentConfPage.visitDateConf).getText();
        Assert.assertEquals(visitDateChk, VisitDate);
        String commentChk = driver.findElement(appointmentConfPage.commentConf).getText();
        Assert.assertEquals(commentChk, Comment);

        TakeManualScreenshot();
        appointmentConfPage.clickGoToHomepage();

        // logout once all data loaded using data from last object
        if(VisitDate == "22/02/2023"){
            appointmentConfPage.clickMenuToggle();
            appointmentConfPage.clickLogout();
        }

    }

    @DataProvider
    public Object[][] AppData(){

        Object[][] data = new Object[4][4];
        data[0][0] = "Tokyo CURA Healthcare Center";
        data[0][1] = "01/11/2022";
        data[0][2] = "Comment for the first booking.";
        data[0][3] = 0;
        data[1][0] = "Hongkong CURA Healthcare Center";
        data[1][1] = "14/12/2022";
        data[1][2] = "Comment for the second booking.";
        data[1][3] = 1;
        data[2][0] = "Seoul CURA Healthcare Center";
        data[2][1] = "18/01/2023";
        data[2][2] = "Comment for the third booking.";
        data[2][3] = 2;
        data[3][0] = "Hongkong CURA Healthcare Center";
        data[3][1] = "22/02/2023";
        data[3][2] = "Comment for the fourth booking.";
        data[3][3] = 0;

        return data;
    };

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentConfPage {

    // accept the driver
    private WebDriver driver;

    // set up fields
    public By facilityConf = By.id("facility");
    public By visitDateConf = By.id("visit_date");
    public By commentConf = By.id("comment");
    private By logout = By.xpath(" //*[@id=\"sidebar-wrapper\"]/ul/li[5]/a");
    private By menuToggle = By.id("menu-toggle");
    private By goToHomepage = By.xpath("//*[@id=\"summary\"]/div/div/div[7]/p/a");


    // create constructor
    public AppointmentConfPage(WebDriver driver){this.driver = driver;}

    // input methods

    //public void setFacilityconf(String facilityconfin){driver.findElement(facilityconf).sendKeys(facilityconfin);}

    public AppointmentPage clickGoToHomepage(){
        driver.findElement(goToHomepage).click();
        return new AppointmentPage(driver);
    }

    public AppointmentPage clickMenuToggle(){
        driver.findElement(menuToggle).click();
        return new AppointmentPage(driver);
    }

    public AppointmentPage clickLogout(){
        driver.findElement(logout).click();
        return new AppointmentPage(driver);
    }

}

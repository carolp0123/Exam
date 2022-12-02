package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    // accept the driver
    private WebDriver driver; /* create driver variable */

    // set up the fields
    public By appointmentBtn = By.id("btn-make-appointment");

    // create the constructor
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    // click on the Make Appointment Button will prompt the user to login
    public LoginPage clickMakeApp(){
        driver.findElement(appointmentBtn).click();
        return new LoginPage(driver);
    }

    // click on the Make Appointment Button once logged in
    public AppointmentPage clickMakeAppoint(){
        driver.findElement(appointmentBtn).click();
        return new AppointmentPage(driver);
    }

}

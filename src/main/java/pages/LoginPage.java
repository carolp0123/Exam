package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // accept the driver
    private WebDriver driver; /* create driver variable */

    // set up fields
    private By userName = By.name("username");
    private By passWord = By.name("password");
    private By loginBtn = By.id("btn-login");


    // set up the constructor
    public LoginPage(WebDriver driver){this.driver = driver;}

    // input methods
    public void setUserName(String userNamein){ driver.findElement(userName).sendKeys(userNamein);}
    public void setPassWord(String passWordin){ driver.findElement(passWord).sendKeys(passWordin);}

    // click on login button
    public AppointmentPage clickLogin(){
        driver.findElement(loginBtn).click();
        return new AppointmentPage(driver);
    }
}

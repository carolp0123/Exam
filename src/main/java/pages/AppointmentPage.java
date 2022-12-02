package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppointmentPage {

    // accept the driver
    private WebDriver driver;

    // set up fields
    private By facility = By.cssSelector("#combo_facility");
    private By hospReAdd = By.xpath("//*[@id=\"appointment\"]/div/div/form/div[2]/div/label");
    private By programs = By.name("programs");
    private By visitDate = By.name("visit_date");
    private By comment = By.name("comment");
    private By bookAppBtn = By.id("btn-book-appointment");



    // create constructor
    public AppointmentPage(WebDriver driver){this.driver = driver;}

    // input methods
    public void setFacility(String facilityin){driver.findElement(facility).sendKeys(facilityin);}
    public void clickHospReAdd(){driver.findElement(hospReAdd).click();}
    public void clickPrograms(Integer NumPrograms){driver.findElements(programs).get(NumPrograms.intValue()).click();}
    public void setVisitDate(String visitDatein){driver.findElement(visitDate).sendKeys(visitDatein);}
    public void setComment(String commentin){driver.findElement(comment).sendKeys(commentin);}

    public AppointmentConfPage clickBookApp(){
        driver.findElement(bookAppBtn).click();
        return new AppointmentConfPage(driver);
    }

}

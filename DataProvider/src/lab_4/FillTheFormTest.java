package lab_4;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.security.SecureRandom;
import java.util.List;


public class FillTheFormTest {
	WebDriver driver;
	SecureRandom randomNum = new SecureRandom();

    @BeforeClass
    public void openWebPage() {
        System.setProperty("webdriver.gecko.driver", "src\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.get("http://demoqa.com/registration/");
    }

    @AfterClass
    public void submitAndCloseWebPage() throws InterruptedException {
        Thread.sleep(5_000);
        driver.findElement(By.name("pie_submit")).submit();
        Thread.sleep(5_000);
        driver.quit();
    }

    @Test
    public void enterFirstNameAndLastName() {
        WebElement firstName = driver.findElement(By.name("first_name"));
        firstName.sendKeys("Alexey");
        WebElement lastName = driver.findElement(By.name("last_name"));
        lastName.sendKeys("Georgienko");
    }
    
    @Test
    public void chooseMartialStatus(){
    	List<WebElement>martStatus = driver.findElements(By.xpath("//*[@class='input_fields  radio_fields']"));
    	martStatus.get(randomNum.nextInt(3)).click();
    }

    @Test
    public void chooseHobby() {
        WebElement reading = driver.findElement(By.xpath("//*[@value='reading']"));
        reading.click();
        WebElement dance = driver.findElement(By.xpath("//*[@value='dance']"));
        dance.click();
        WebElement cricket = driver.findElement(By.xpath("//*[@value='cricket ']"));
        cricket.click();
    }
    @Test
    public void chooseCountry() {
        Select country = new Select(driver.findElement(By.id("dropdown_7")));
        country.selectByIndex(randomNum.nextInt(100)+1);
    }
    
    @Test
    public void setBirthDate(){
    	Select month = new Select(driver.findElement(By.id("mm_date_8")));
        month.selectByIndex(randomNum.nextInt(12)+1);

        Select day = new Select(driver.findElement(By.id("dd_date_8")));
        day.selectByIndex(randomNum.nextInt(31)+1);

        Select year = new Select(driver.findElement(By.id("yy_date_8")));
        year.selectByValue("1995");
    }

    @Test
    public void choosePicture() {
        driver.findElement(By.id("profile_pic_10")).sendKeys("D:\\white_bell_flowers-t3.jpg");
    }

    @Test
    public void fillOtherFields() {
        WebElement phoneNumber = driver.findElement(By.id("phone_9"));
        phoneNumber.sendKeys("373111222333");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("alex");
        WebElement eMail = driver.findElement(By.id("email_1"));
        eMail.sendKeys("alex"+randomNum.nextInt(100)+"@mail.ru");
        WebElement text = driver.findElement(By.name("description"));
        text.sendKeys("It's just the test!");
        WebElement password = driver.findElement(By.id("password_2"));
        password.sendKeys("fjh785ui");
        WebElement confirmPassword = driver.findElement(By.id("confirm_password_password_2"));
        confirmPassword.sendKeys("fjh785ui");
    }
}

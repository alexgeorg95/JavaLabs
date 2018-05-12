package lab_4;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FillWithDataProvider {
	WebDriver driver;

	@DataProvider(name="test1")
	public Object[][] dateAndTime() {
	    return new Object[][] {{"06:00","06/17/2014","07:00","07/08/2014"},{"11:00","11/24/2015","09:00","12/25/2015"},{"08:00","04/28/2017","10:00","08/10/2017"}};
	  }
	
  @Test(dataProvider = "test1")
  public void fillInputFields(String enTime, String enDate,String exTime,String exDate) throws InterruptedException {
	  WebElement entryTime = driver.findElement(By.id("EntryTime"));
	  entryTime.clear();
      entryTime.sendKeys(enTime);
      WebElement entryDate = driver.findElement(By.id("EntryDate"));
      entryDate.clear();
      entryDate.sendKeys(enDate);
      WebElement exitTime = driver.findElement(By.id("ExitTime"));
      exitTime.clear();
      exitTime.sendKeys(exTime);
      WebElement exitDate = driver.findElement(By.id("ExitDate"));
      exitDate.clear();
      exitDate.sendKeys(exDate);
      List<WebElement> radioButton = driver.findElements(By.xpath("//*[@value='PM']"));
	  for(WebElement r:radioButton){
		  r.click();
	  }
	  Select select = new Select(driver.findElement(By.id("Lot")));
	  select.selectByVisibleText("Economy Parking");
      driver.findElement(By.name("Submit")).click();
      Thread.sleep(5_000);
	  
  }

  
  @BeforeClass
  public void openWebPage() {
	  System.setProperty("webdriver.gecko.driver", "src\\geckodriver.exe");
      driver = new FirefoxDriver();
      driver.get("http://adam.goucher.ca/parkcalc/index.php");
  }

  @AfterClass
  public void calculateAndCloseWebPage() throws InterruptedException {
     Thread.sleep(5_000);
     driver.quit();
  }

}

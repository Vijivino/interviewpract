package week2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeafGroundWaits {

	WebDriver driver;
	
	@Test
	public void checkWaits() throws InterruptedException {
		
	driver=new ChromeDriver();
	driver.get("https://leafground.com/waits.xhtml");
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
	
	//check visible 
	driver.findElement(By.xpath("//h5[text()='Wait for Visibility (1 - 10 Sec)']/following-sibling::div//button")).click();
	//wait untill the text visible
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
	WebElement visibletext = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='I am here']")));
	boolean visibleBoo = visibletext.isDisplayed();
	Assert.assertTrue(visibleBoo);
	System.out.println("Button visible "+visibleBoo);
	
	//check invisible
	driver.findElement(By.xpath("//h5[contains(text(),'Invisibility ')]//following-sibling::div//span[text()='Click']")).click();
	WebElement invisible = driver.findElement(By.xpath("//span[contains(text(),'hide')]"));
	Boolean until = wait.until(ExpectedConditions.invisibilityOf(invisible));//it will return the invisiblity boolean value
	System.out.println("Button invisible "+until);
	Assert.assertTrue(until);
	
	//check clickability
	driver.findElement(By.xpath("//h5[contains(text(),'Clickability')]/following-sibling::div//span[text()='Click First Button']")).click();
    Thread.sleep(500);
    //EITHER-- wait for the meassge disappear
    WebElement message = driver.findElement(By.xpath("//span[text()='Message 1']"));
    wait.until(ExpectedConditions.invisibilityOf(message));
	System.out.println("Message disappered");
	//OR-- wait for elemnt to be clickable
	WebElement secondButton = driver.findElement(By.xpath("//h5[contains(text(),'Clickability')]/following-sibling::div//span[text()='Click Second']"));
	WebElement secondBtnClick = wait.until(ExpectedConditions.elementToBeClickable(secondButton));
	System.out.println("Button clickable "+secondBtnClick.isDisplayed());
	secondButton.click();
	
	//check textchange
	driver.findElement(By.xpath("//h5[contains(text(),'Text')]/following-sibling::div//span[text()='Click']")).click();
	//wait for text change in the button
	WebElement textButton = driver.findElement(By.xpath("//h5[contains(text(),'Text')]/following-sibling::div//span[contains(text(),'going')] "));
	Boolean until2 = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h5[contains(text(),'Text')]/following-sibling::div//span[contains(text(),'going')] "), "Did you notice?"));
	Assert.assertTrue(until2);
	System.out.println("Text changed "+until2);
	
	

	
	
	}
}

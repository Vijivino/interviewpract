package week2;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeafGroundAlerts {

	WebDriver driver;
	
	@Test
	public void CheckAlerts() throws InterruptedException {
		driver=new ChromeDriver();
		driver.get("https://leafground.com/alert.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//simple Alert - gives some information
		driver.findElement(By.xpath("//h5[text()=' Alert (Simple Dialog)']/following-sibling::button")).click();
		driver.switchTo().alert().accept();
		//validation
		String simple = driver.findElement(By.id("simple_result")).getText();
		Assert.assertTrue(simple.contains("successfully"));
		System.out.println("Simple alert done "+simple);
		
		//confirm Alert - asks us to give confirmation 
		driver.findElement(By.xpath("//h5[text()=' Alert (Confirm Dialog)']/following-sibling::button")).click();
		driver.switchTo().alert().dismiss();
		//validation
		String confirm = driver.findElement(By.id("result")).getText();
		Assert.assertTrue(confirm.contains("Cancel"));
		System.out.println("Confirm alert done by dismiss"+confirm);
		
		//sweet Modal alert - can be inspectable
		driver.findElement(By.xpath("//h5[text()='Sweet Modal Dialog']/following-sibling::button")).click();
		//validation
		WebElement modal = driver.findElement(By.xpath("//h5[text()='Sweet Modal Dialog']/following-sibling::div//div[contains(@class,'dialog-content')]"));
		Assert.assertTrue(modal.isDisplayed());
		System.out.println("sweet Modal alert done and the content is  "+modal.getText());
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Modal Dialog (Sweet Alert)']/following-sibling::a[@aria-label='Close']")).click();
		 
		//prompt Alert - asking us to give inputs
		driver.findElement(By.xpath("//h5[text()=' Alert (Prompt Dialog)']/following-sibling::button")).click();
		Alert alert= driver.switchTo().alert();
		alert.sendKeys("viji");
		alert.accept();
		//validation
		String confirm1 = driver.findElement(By.id("confirm_result")).getText();
		Assert.assertTrue(confirm1.contains("viji"));
		System.out.println("prompt alert done by ok "+confirm1);
		
		//minimize and maximize
		driver.findElement(By.xpath("//h5[text()='Minimize and Maximize']/following-sibling::button")).click();
		Point before = driver.findElement(By.xpath("//h5[text()='Minimize and Maximize']/following-sibling::div")).getLocation();
		System.out.println("Before minimize location "+before);
		System.out.println(driver.findElement(By.xpath("//h5[text()='Minimize and Maximize']/following-sibling::div")).getSize());
        driver.findElement(By.xpath("//h5[text()='Minimize and Maximize']/following-sibling::div/div/a[contains(@class,'minimize ')]")).click();
		Thread.sleep(2000);
        Point mini = driver.findElement(By.xpath("//div[@class='ui-dialog-docking-zone']//div[contains(@class,'dialog-content')]")).getLocation();
		System.out.println("After minimize location "+mini);
		System.out.println(driver.findElement(By.xpath("//div[@class='ui-dialog-docking-zone']//div[contains(@class,'dialog-content')]")).getSize());
		Assert.assertNotEquals(before, mini);
		System.out.println("Content widget is minimized");
		//maximize
		driver.findElement(By.xpath("//span[text()='Min and Max']/following-sibling::a[2]")).click();
		Point maxi = driver.findElement(By.xpath("//h5[text()='Minimize and Maximize']/following-sibling::div/div[2]")).getLocation();
		System.out.println("After maximize size "+maxi);
		System.out.println(driver.findElement(By.xpath("//h5[text()='Minimize and Maximize']/following-sibling::div/div[2]")).getSize());
		System.out.println("Content widget is minimized");
	}
}

package week2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeafGroundCalendar {
	
	WebDriver driver;
	
	@Test
	public void CheckCalendar() throws InterruptedException {
		driver=new ChromeDriver();
		driver.get("https://leafground.com/calendar.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		String expected="July 2023";
		
		while(true) {
			String current = driver.findElement(By.xpath("//h5[text()='Calendar']/following-sibling::div//h2")).getText();
			if(expected.contains(current)) {
				break;
			}else {
				driver.findElement(By.xpath("//h5[text()='Calendar']/following-sibling::div//button[text()='Next']")).click();
			}
		}
		
		driver.findElement(By.xpath("//tbody/tr/td[@data-date='2023-07-26']")).click();
		Thread.sleep(3000);
		//sending data into dialog box
		driver.findElement(By.xpath("//label[text()='Title']/following-sibling::input")).sendKeys("Demo meeting");
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		
		//validation
		String text = driver.findElement(By.xpath("//tbody/tr/td[@data-date='2023-07-26']/div//a/div[contains(@class,'title')]")).getText();
		Assert.assertEquals(text, "Demo meeting");
		System.out.println("Meeting event created !");
	}

}

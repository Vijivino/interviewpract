package week3;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Amazon {

	WebDriver driver;

	@Test
	public void checkAmazon() {

		driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


		driver.findElement(By.xpath("//div[@id='nav-tools']/a/span/span[@class='nav-line-2']")).click();


		List<WebElement> options=driver.findElements(By.xpath("//div[@class='a-row a-spacing-mini']"));

		for(int i=0;i<options.size();i++){
			String text=options.get(i).getText();
			if(text.contains("HI")){
				options.get(i).click();      
				}
		}


		SoftAssert sas=new SoftAssert();
		sas.assertEquals("done", "done");
		sas.assertAll();


	}

}

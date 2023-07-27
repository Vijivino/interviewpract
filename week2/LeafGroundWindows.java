package week2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LeafGroundWindows {
	
	WebDriver driver;
	
	@Test
	public void checkWindows() {
	driver=new ChromeDriver();
	driver.get("https://leafground.com/window.xhtml");
	driver.manage().window().maximize();
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	//wait till the child window open using explicit wait 
	driver.findElement(By.xpath("//h5[text()='Wait for 2 new tabs to open']/following-sibling::button")).click();
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
    wait.until(ExpectedConditions.numberOfWindowsToBe(3));
	
    Set<String> handles = driver.getWindowHandles();
    List<String> list=new ArrayList<String>(handles);
    System.out.println(list.size());
	//closing all child windows
    driver.switchTo().window(list.get(0));
    System.out.println(driver.getTitle());
    driver.switchTo().window(list.get(1));
    System.out.println(driver.getTitle());
    driver.switchTo().window(list.get(2));
    System.out.println(driver.getTitle());
    for(int i=1;i<list.size();i++) {
		driver.switchTo().window(list.get(i));
		driver.close();
	}
	
	
	}
}

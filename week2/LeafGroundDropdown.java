package week2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class LeafGroundDropdown {
	
WebDriver driver;
	
	@Test
	public void Checkdropdown() throws InterruptedException {
		driver=new ChromeDriver();
		driver.get("https://leafground.com/select.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		Select sel=new Select(driver.findElement(By.xpath("//h5[contains(text(),'favorite ')]/following-sibling::div//select")));
		List<WebElement> options = sel.getOptions();
		List<String> list=new ArrayList<String>();
		//add the dropdown element's text into list
		for(int i=0;i<options.size();i++) {
		 list.add(options.get(i).getText());
			
		}
		
		System.out.println("List added "+list);
		
		//store it in temp list before sorting list
		List<String> listbefore=new ArrayList<String>();
		//add the values again in other list
		for(int i=0;i<options.size();i++) {
		 listbefore.add(options.get(i).getText());
			
		}
		System.out.println("List before sorting "+listbefore);
		
		Collections.sort(list);
		System.out.println("List after sorting "+list);
		//System.out.println(listbefore);
		
		//validate
		if(listbefore.equals(list)) {
			System.out.println("List is sorted");
		}else {
			System.out.println("List is not sorted");
		}
			
	}

}

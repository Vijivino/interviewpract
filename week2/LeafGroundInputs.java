package week2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeafGroundInputs {
	
	WebDriver driver;
	
	
	@Test
	public void handleInput() throws InterruptedException {
		driver=new ChromeDriver();
		driver.get("https://leafground.com/input.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//name
		driver.findElement(By.xpath("//input[@placeholder='Babu Manickam']")).sendKeys("vijayalaxmi");
		//city
		driver.findElement(By.xpath("//input[@value='Chennai']")).sendKeys("India");
		
		//disable
		String disable = driver.findElement(By.xpath("//input[@placeholder='Disabled']")).getAttribute("aria-disabled");
		Assert.assertEquals(disable, "true");
		System.out.println("TextBox is disabled");
		
		//clear
		driver.findElement(By.xpath("//input[contains(@value,'clear')]")).clear();
		String clear = driver.findElement(By.xpath("//input[contains(@value,'clear')]")).getAttribute("value");
		boolean empty = clear.isEmpty();
		Assert.assertTrue(empty);
	    System.out.println("Input is cleared");
	    
	    //Retrieve the text
	    String value = driver.findElement(By.xpath("//input[contains(@value,'superb')]")).getAttribute("value");
	    System.out.println("The text is retrieved - "+value);
	    
	    //type email and press tab
	    driver.findElement(By.xpath("//input[@placeholder='Your email and tab']")).sendKeys("viji@gmail.com",Keys.TAB);
	    //validate focus moved to next becoz of tab
	    String classname = driver.findElement(By.xpath("//textarea[@placeholder='About yourself']")).getAttribute("class");
	    Assert.assertTrue(classname.contains("focus"));
	    System.out.println("Control is moved because of tab - "+classname);
	    
	    //textarea *****xpath is unique here*****
	    driver.findElement(By.xpath("//div[contains(@class,'ql-editor') and not(@data-placeholder='Enter your content')]")).sendKeys("Hello");
	    Thread.sleep(2000);
	    //click x2 sub
	    driver.findElement(By.xpath("//button[@class='ql-script' and @value='sub']//*[name()='svg']")).click();
	    driver.findElement(By.xpath("//div[contains(@class,'ql-editor') and not(@data-placeholder='Enter your content')]")).sendKeys("down");
	    Thread.sleep(3000);
	    //validate
	    boolean displayed = driver.findElement(By.tagName("sub")).isDisplayed();
	    Assert.assertTrue(displayed);
	    String subtext = driver.findElement(By.tagName("sub")).getText();
	    System.out.println("Sub Text is verified and retrieved - "+subtext);
	    
	    //attach video URl
	    driver.findElement(By.xpath("//button[@class='ql-video']//*[name()='svg']")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//input[@placeholder='Embed URL']")).sendKeys("https://www.youtube.com/");
	   // driver.findElement(By.xpath("//a[@class='ql-action']")).click();
	    Thread.sleep(1000);
	    //using Js
	    JavascriptExecutor js= (JavascriptExecutor)driver;
	    js.executeScript("document.querySelector('a.ql-action','::after').click()");
	    Thread.sleep(7000);
	    //validate
	    boolean displayed4 = driver.findElement(By.xpath("//iframe[@class='ql-video']")).isDisplayed();
	    Assert.assertTrue(displayed4);
	    System.out.println("Video content is attached ");
	    
	    //enter and check error mesge
	    driver.findElement(By.xpath("//input[contains(@id,'thisform')]")).sendKeys(Keys.ENTER);
        //validate
	    boolean displayed2 = driver.findElement(By.xpath("//span[@class='ui-message-error-detail']")).isDisplayed();
	    System.out.println("Error message page displayed");
	    Thread.sleep(2000);
	    
	    //check floating input
	    Point location = driver.findElement(By.xpath("//label[@id='j_idt106:j_idt113']")).getLocation();
	    System.out.println("Label username position before "+location);
	    driver.findElement(By.xpath("//input[contains(@id,'float-input')]")).click();
	    Point location1 = driver.findElement(By.xpath("//label[@id='j_idt106:j_idt113']")).getLocation();
	    System.out.println("Label username position after "+location1);
	    Assert.assertNotEquals(location, location1);
	    System.out.println("Label position changed"); 
	    
	    //type name and choose 3rd value
	    driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("viji");
	    driver.findElement(By.xpath("//li[@data-item-value='viji2']")).click();
	    //validate
	    String name = driver.findElement(By.xpath("//span[@class='ui-autocomplete-token-label']")).getText();
	    Assert.assertEquals(name, "viji2");
	    System.out.println("Name with third option verified "+name);
	    
	    //enter dob
	    driver.findElement(By.xpath("//span[contains(@class,'datepicker')]/input")).sendKeys("03/19/1994");
	    //validate
	    driver.findElement(By.xpath("//button[contains(@class,'datepicker')]")).click();
	    String date = driver.findElement(By.xpath("//a[text()='19']")).getAttribute("class");
	    Assert.assertTrue(date.contains("active"));
	    driver.findElement(By.xpath("//a[text()='19']")).click();
	    System.out.println("Date is selected correctly");
	    
	    //enter input and check spinner
        driver.findElement(By.xpath("//input[contains(@class,'spinner')]")).sendKeys("100");
        driver.findElement(By.xpath("//a[contains(@class,'spinner-up')]")).click();
        //validate
        String increment = driver.findElement(By.xpath("//input[contains(@class,'spinner')]")).getAttribute("aria-valuenow");
	    Assert.assertEquals(increment, "101");
	    System.out.println("Spinner is verified - "+increment);
	    
	    //check slider
	    driver.findElement(By.xpath("//input[contains(@id,'slider')]")).sendKeys("50");
	    Thread.sleep(2000);
	    //validate
	    String slid = driver.findElement(By.xpath("//div[contains(@class,'slider-range')]")).getAttribute("style");
	    System.out.println(slid);
	    Assert.assertTrue(slid.contains("50"));
	    System.out.println("Slider is validated "+slid);
	    
	    //Keyboard
	    driver.findElement(By.xpath("//input[contains(@class,'keyboard-input')]")).click();
	    boolean displayed3 = driver.findElement(By.xpath("//div[contains(@class,'keypad-popup')]")).isDisplayed();
	    Assert.assertTrue(displayed3);
	    System.out.println("Keyboard is displayed ");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//button[text()='Close']")).click();
	    
	    //textarea
	    driver.findElement(By.xpath("//div[contains(@class,'ql-editor') and @data-placeholder='Enter your content']")).sendKeys("Hello");
	    //change font size
	    driver.findElement(By.xpath("(//span[contains(@class,'ql-size')])[2]")).click();
	    driver.findElement(By.xpath("(//span[@data-value='huge'])[2]")).click();
	    driver.findElement(By.xpath("//div[contains(@class,'ql-editor') and @data-placeholder='Enter your content']")).sendKeys("big");
	    //validate
	    String bigtext = driver.findElement(By.xpath("//span[text()='big']")).getAttribute("class");
	    Assert.assertTrue(bigtext.contains("huge"));
	    
	    //change font style
	    Actions act=new Actions (driver);
	    act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
	   // driver.findElement(By.xpath("(//span[contains(@class,'ql-font')])[2]")).click();
	    //used relative locator to fetch unique value
	    WebElement customlabel = driver.findElement(By.xpath("//h5[text()='Custom Toolbar']"));
	    //can use sibling xpath also --- //h5[text()='Custom Toolbar']/following-sibling::div//span[contains(@class,'font')]
	    driver.findElement(RelativeLocator.with(By.xpath("//span[contains(@class,'ql-font')]")).below(customlabel)).click();
	    driver.findElement(RelativeLocator.with(By.xpath("//span[@data-value='serif']")).below(customlabel)).click();
	  
	    
	}

}

package week2;

import java.io.File;
import java.time.Duration;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class LeafGroundCharts {

WebDriver driver;
	
	@Test
	public void CheckCharts() throws InterruptedException {
		driver=new ChromeDriver();
		driver.get("https://leafground.com/charts.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement canvas = driver.findElement(By.xpath("//h5[text()='Line Chart']/following-sibling::div/canvas"));
		Dimension canvasSize = canvas.getSize();
		System.out.println(canvasSize);
		int widthX = canvasSize.getWidth()/2;
		System.out.println(widthX);//center of x axis
		int heightY = canvasSize.getHeight()/2;
		System.out.println(heightY);//center of y axis
		
		System.out.println(canvas.getRect().getX());
		System.out.println(canvas.getRect().getY());
		
		Thread.sleep(3000);
		
		Actions act=new Actions(driver);
		act.moveToElement(canvas, 400, 0).click().perform();//369,0-march,400-april 
		//System.out.println(canvas.getAttribute("title"));
		
/*		// extract canvas element's contents
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		Object base64 = js.executeScript("return arguments[0].toDataURL('image/png').substring(21);", canvas);
		// # decode from the base64 format, get the image binary data
		 byte[] binarydata = Base64.decodeBase64((byte[]) base64);
		
		 File desfile=new File("./src/test/resuorces/saved_canvas.png");
		File.join("./src/test/resuorces"), "..", "tmp", "saved_canvas.png");
		 File.
				 fio = File.open(dest_file, "wb")  # b indicates binary file
				 fio.write(canvas_png)
				 fio.flush
				 fio.close
				 expect(File.exists?(dest_file)).to be_truthy
	*/	
		
		
		
		
	
		
		
		
		
	}
}

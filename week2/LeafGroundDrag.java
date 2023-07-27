package week2;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class LeafGroundDrag {

	WebDriver driver;

	@Test
	public void CheckDrag() throws InterruptedException, IOException, AWTException {
		driver=new ChromeDriver();
		driver.get("https://leafground.com/drag.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//drag and drop
		WebElement drag = driver.findElement(By.xpath("//span[text()='Drag and Drop']"));
		Actions act=new Actions(driver);
		org.openqa.selenium.Rectangle rect = drag.getRect();
		System.out.println(rect.getX());//115
		System.out.println(rect.getY());//196
		System.out.println(rect.getHeight());//18
		System.out.println(rect.getWidth());//88
		System.out.println(rect.getPoint());//115,196
		act.dragAndDropBy(drag, 300, 0).perform();

		//drag in between rows- bottom to top
		WebElement tshirt = driver.findElement(By.xpath("//*[@id=\"form:j_idt111_data\"]/tr[4]"));
		WebElement watch = driver.findElement(By.xpath("//*[@id=\"form:j_idt111_data\"]/tr[1]"));
		//act.dragAndDrop(watch, tshirt).pause(Duration.ofSeconds(30)).perform();
		act.clickAndHold(tshirt).moveToElement(watch).release().perform();
		Thread.sleep(1000);

		//screenshot to pdf
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File desti = new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\PractiseJune\\src\\test\\resources\\snap.png");
		FileUtils.copyFile(source, desti);

		//Creating PDF document object ***need pdfbox dependency
		PDDocument document = new PDDocument();     
		PDPage page=new PDPage();
		document.addPage(page);
		//Creating PDImageXObject object
		PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\PractiseJune\\src\\test\\resources\\snap.png",document);
		//creating the PDPageContentStream object
		PDPageContentStream contents = new PDPageContentStream(document, page);
		//Drawing the image in the PDF document
		contents.drawImage(pdImage,100,100,200,200);
		System.out.println("Image inserted");
		contents.close();        
		document.save(new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\PractiseJune\\src\\test\\resources\\snap2.pdf"));
		document.close();
		Thread.sleep(1000);

		//scroll down to view the grid
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)");
		//get the rows grid size
		org.openqa.selenium.Rectangle rect2 = driver.findElement(By.xpath("//h5[text()='Draggable Rows']/parent::div")).getRect();
		System.out.println(rect2.x +" " +rect2.getY() +" "+rect2.getHeight() +" " +rect2.width);
        //screenshot of rows grid using robot
		Robot robo=new Robot();
		Rectangle rect1=new Rectangle(663,468,585,429); //size of the row grid
		BufferedImage srcImage=robo.createScreenCapture(rect1);
		ImageIO.write(srcImage,"PNG",new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\PractiseJune\\src\\test\\resources\\gridSnap.png"));

		//screenshot of webelement row grid
		WebElement grid = driver.findElement(By.xpath("//h5[text()='Draggable Rows']/parent::div"));
		File so=grid.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(so, new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\PractiseJune\\src\\test\\resources\\rowsSnap.png"));

		




	}
}

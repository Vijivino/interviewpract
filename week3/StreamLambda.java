package week3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class StreamLambda {

	WebDriver driver;
	@Test
	public void checkList() {
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Register.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.id("msdd")).click();
		
		//using stream class get the text of each webelemnt from the options list
		List<String> OptionsName=new ArrayList<String>();

		// Locating all options names at home page
		/*
		 * We do not need to store list of web elements as well. We can get the stream of found web elements
		 * and apply aggregate function forEach(). Logic behind forEach is to get the text of each web element
		 * and add to list. We are using lambda expression inside forEach.  
		 */
      //1.
//		driver.findElements(By.xpath("//ul/li[@class='ng-scope']"))
//		.stream()
//		.forEach(product -> OptionsName.add(product.getText()));
//		
//		OptionsName.forEach(name -> System.out.println(name));
	 //2.
//		driver.findElements(By.xpath("//ul/li[@class='ng-scope']"))
//		.stream()
//		.forEach(ele -> System.out.println(ele.getText()));
	//3.
		List<String> Options = driver.findElements(By.xpath("//ul/li[@class='ng-scope']"))
		.stream()
		.map(ele ->ele.getText()) //add the names using map
		.collect(Collectors.toList());
		
		Options.stream().forEach(text -> System.out.println(text));
		//to ptint frist value
		Optional<String> findFirst = Options.stream().findFirst();
		System.out.println(findFirst);
		//filter language german
       boolean anyMatch = Options.stream().anyMatch(textG -> textG.equals("German"));
		System.out.println(anyMatch);
			
	}
}

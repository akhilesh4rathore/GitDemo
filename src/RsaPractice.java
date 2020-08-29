import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RsaPractice {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium Web Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); // Implicit wait
		String singleItem = "Pista";
		String[] requiredItemsArray = { "Tomato", "Pista", "Pears", "Beans", "Capsicum" };

		addToCart(driver, requiredItemsArray);

//Checkout with coupon
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
		System.out.println(driver.findElement(By.xpath("//span[@class='promoInfo']")).getText());

	}

//addToCart() - Add items to cart when displayed text is dynamic in nature. Example ADD TO CART changes to ADDED for few seconds.
	public static void addToCart(WebDriver greenKart, String[] requiredProductNames) {
		greenKart.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		List<WebElement> productNames = greenKart.findElements(By.cssSelector("h4[class='product-name']"));
		List<String> requiredProductNamesList = Arrays.asList(requiredProductNames);
		int breakPoint = 0;
		System.out.println(requiredProductNames.length);
		for (int i = 0; i < productNames.size(); i++) {
			String formattedProductName = "";
			if (productNames.get(i).getText().contains("-")) {
				String extractedProductName = productNames.get(i).getText();
				String[] extractedProductNameArray = extractedProductName.split("-");
				formattedProductName = extractedProductNameArray[0].trim();
				System.out.println("Product name fetched for index = " + i + " is " + productNames.get(i).getText());
				System.out.println("Product name after splitting = " + extractedProductNameArray[0] + "+"
						+ extractedProductNameArray[1]);
				System.out.println("Product name after formatting = " + formattedProductName + "\n");
			} else {
				formattedProductName = productNames.get(i).getText().trim();
				System.out.println("Product name after formatting = " + formattedProductName + "\n");
			}

			if (requiredProductNamesList.contains(formattedProductName)) {
				breakPoint++;
				greenKart.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (breakPoint == requiredProductNames.length)
					break;
			}
		}
		System.out.println("addToCart() function ended.\n");
	}

//addToCart2() - Add items to cart using array list, displayed text
	public static void addToCart2(WebDriver greenKart, String[] requiredProductNames) throws InterruptedException {
		greenKart.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		Thread.sleep(2000);
		List<WebElement> productNames = greenKart.findElements(By.cssSelector("h4[class='product-name']"));
		List<String> requiredProductNamesList = Arrays.asList(requiredProductNames);
		int breakPoint = 0;
		System.out.println(requiredProductNames.length);

		for (int i = 0; i < productNames.size(); i++) {

			String formattedProductName = "formattedProductName before next iteration of FOR loop.";
			if (productNames.get(i).getText().contains("-")) {
				String extractedProductName = productNames.get(i).getText();
				String[] extractedProductNameArray = extractedProductName.split("-");
				formattedProductName = extractedProductNameArray[0].trim();
				System.out.println("Product name fetched for index = " + i + " is " + productNames.get(i).getText());
				System.out.println("Product name after splitting = " + extractedProductNameArray[0] + "AND" + extractedProductNameArray[1]);
				System.out.println("Product name after formatting = " + formattedProductName + "\n");
			} else {
				formattedProductName = productNames.get(i).getText().trim();
				System.out.println("Product name after formatting = " + formattedProductName + "\n");
			}

			if (requiredProductNamesList.contains(formattedProductName)) {
				breakPoint++;
				greenKart.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
				Thread.sleep(10000); // Wait till ADDED changes back to ADD to CART
				if (breakPoint == requiredProductNames.length)
					break;
			}
		}
		System.out.println("addToCart2() function ended.\n");
	}

//addToCart3() - Add single item to cart
	public static void addToCart3(WebDriver greenKart, String singleProduct) throws InterruptedException {
		greenKart.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		Thread.sleep(2000);
		List<WebElement> productName = greenKart.findElements(By.cssSelector("h4[class='product-name']"));
		System.out.println(productName.size());
		for (int i = 0; i <= productName.size(); i++) {
			String extractedProductName = productName.get(i).getText();
			if (extractedProductName.contains(singleProduct)) {
				System.out.println(i);
				greenKart.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
				break;
			}
		}
		System.out.println("addToCart3() function ended.\n");
	}

//alertsHandling() - Switch to alerts example
	public static void alertsHandling(WebDriver AutomationPractise2) throws InterruptedException {
		AutomationPractise2.get("https://rahulshettyacademy.com/AutomationPractice/");
		Thread.sleep(2000);
		String inputString = "Akhilesh";
		System.out.println(AutomationPractise2.getCurrentUrl());
		AutomationPractise2.findElement(By.id("name")).sendKeys(inputString);
		Thread.sleep(2000);
		AutomationPractise2.findElement(By.id("alertbtn")).click();
		System.out.println(AutomationPractise2.switchTo().alert().getText());
		Thread.sleep(2000);
		AutomationPractise2.switchTo().alert().accept();
		AutomationPractise2.findElement(By.id("name")).sendKeys(inputString);
		Thread.sleep(2000);
		AutomationPractise2.findElement(By.id("confirmbtn")).click();
		System.out.println(AutomationPractise2.switchTo().alert().getText());
		Thread.sleep(2000);
		AutomationPractise2.switchTo().alert().dismiss();
		System.out.println("alertsHandling() function ended.\n");
	}
}

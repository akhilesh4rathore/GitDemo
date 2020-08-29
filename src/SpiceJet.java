import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SpiceJet {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium Web Drivers\\chromedriver.exe");
		WebDriver spicejet = new ChromeDriver();
		spicejet.manage().window().maximize();
		spicejet.get("https://www.spicejet.com/");
		Thread.sleep(7000);

//Round Trip-RETURN DATE validation
		spicejet.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click(); // Enable Round Trip button
		if (spicejet.findElement(By.id("Div1")).getAttribute("style").contains("opacity: 1")) {
			System.out.println("RETURN DATE is ENABLED for ROUND TRIP button.");
		}
		else {
			System.out.println("RETURN DATE is DISABLED for ROUND TRIP button.");
			Assert.assertTrue(false);
		}

//One Way-RETURN DATE validation
		spicejet.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click(); // Enable One Way button
		if (spicejet.findElement(By.id("Div1")).getAttribute("style").contains("opacity: 1")) {
			System.out.println("RETURN DATE is ENABLED for ONE WAY button.");
			Assert.assertTrue(false);
		}
		else {
			System.out.println("RETURN DATE is DISABLED for ONE WAY button.");
		}

//From
		Thread.sleep(1000);
		spicejet.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		spicejet.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //a[@value='BLR']")).click();

//To-by keys, also using webElement as variable
		WebElement to = spicejet.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
		to.click();
		to.clear();
		to.sendKeys("mu");
		Thread.sleep(1000);
		to.sendKeys(Keys.ARROW_RIGHT);
		spicejet.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXTaction")).click();
		Thread.sleep(2000);

//To-by click
		spicejet.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		spicejet.findElement(By.xpath("(//a[@value='BHO'])[2]")).click();
		Thread.sleep(1000);

//Date 
		WebElement currentDepartureDate = spicejet.findElement(By.cssSelector(".ui-state-default.ui-state-highlight.ui-state-active"));
		currentDepartureDate.click();
		Thread.sleep(3000);

//Passengers
		spicejet.findElement(By.id("divpaxinfo")).click(); // Clicks on passenger tab
		Thread.sleep(1000);
//Adults
		Select adults = new Select(spicejet.findElement(By.id("ctl00_mainContent_ddl_Adult")));
		adults.selectByVisibleText("2");
		Thread.sleep(1000);
//Child
		Select child = new Select(spicejet.findElement(By.id("ctl00_mainContent_ddl_Child")));
		child.selectByIndex(3);
		Thread.sleep(1000);
//Infant
		Select infant = new Select(spicejet.findElement(By.id("ctl00_mainContent_ddl_Infant")));
		infant.selectByValue("1");
		Thread.sleep(1000);
		System.out.println("Passengers = " + spicejet.findElement(By.id("divpaxinfo")).getText()); // Passengers

//Currency 
		Select currency = new Select(spicejet.findElement(By.cssSelector("#ctl00_mainContent_DropDownListCurrency")));
		currency.selectByValue("USD");
		System.out.println("Currency = " + spicejet.findElement(By.id("ctl00_mainContent_DropDownListCurrency")).getText()); // Currency DOUBT : How to print selected value instead of all?

//Number of checkboxes on homepage
		System.out.println("Number of checkboxes on homepage = "+spicejet.findElements(By.cssSelector("[type='checkbox']")).size());

//////DOUBT : How to iterate to next element using indexes? 
////		for (int i=spicejet.findElements(By.cssSelector("[type='checkbox']")).size();i>=0;i--) {
////		System.out.println(spicejet.findElement(By.xpath("(//*[@type='checkbox']).[get(i)]")).isSelected()); 
////		}

//Select Checkbox and assertion
		WebElement armyCheckbox = spicejet.findElement(By.xpath("//*[@id='ctl00_mainContent_chk_IndArm']"));
		armyCheckbox.click();
		Assert.assertTrue(armyCheckbox.isSelected());

//Status of checkboxes on homepage using isSelected()
		System.out.println("Status of checkboxes on homepage:");
		System.out.println("Family and Friends = "+spicejet.findElement(By.xpath("//*[@id='ctl00_mainContent_chk_friendsandfamily']")).isSelected()); // Family and Friends
		System.out.println("Senior Citizen = "+spicejet.findElement(By.xpath("//*[@id='ctl00_mainContent_chk_SeniorCitizenDiscount']")).isSelected()); // Senior																												// Citizen
		System.out.println("Armed Forces = "+spicejet.findElement(By.xpath("//*[@id='ctl00_mainContent_chk_IndArm']")).isSelected()); // Armed Forces
		System.out.println("Student = "+spicejet.findElement(By.xpath("//*[@id='ctl00_mainContent_chk_StudentDiscount']")).isSelected()); // Student
		System.out.println("Unaccompanied Minor = "+spicejet.findElement(By.xpath("//*[@id='ctl00_mainContent_chk_Unmr']")).isSelected()); // UnaccompaniedMinor

	}
}

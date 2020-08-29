import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OnChrome {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium Web Drivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.spicejet.com/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		driver.manage().window().maximize();
		System.out.println("Master Project");
		System.out.println("Lines added in cloned project.");
		System.out.println("Lines added in cloned project.");
		System.out.println("Lines added on branch_1 in original project.");
		
		//FROM
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_originStation1_CTNR'] //*[@value='BLR']")).click();		
	}

}

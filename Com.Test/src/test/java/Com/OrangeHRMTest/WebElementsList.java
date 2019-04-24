package Com.OrangeHRMTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WebElementsList {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.toolsqa.com/automation-practice-form/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		List<WebElement> elementlist = driver.findElements(By.name("continents"));
		System.out.println(elementlist.size());

		for (int i = 0; i < elementlist.size(); i++) {
			WebElement item = elementlist.get(i);
			System.out.println(item.getText());

		}

		Select select = new Select(driver.findElement(By.id("continents")));
		select.selectByVisibleText("Europe");
		List<WebElement> l = select.getOptions();
		l.size();
		Thread.sleep(3000);
		driver.close();
	}

}

package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class OperaDriverBuilder {

	public WebDriver initializeWebDriver() {
		System.setProperty("webdriver.opera.driver", "D:\\Libraries\\Webdriver\\operadriver.exe");
		WebDriver driver = new OperaDriver();
		return driver;
	}

}

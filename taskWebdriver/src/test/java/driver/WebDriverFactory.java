package driver;


import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;



public class WebDriverFactory {

	public WebDriver driver;
	
		  
	  private enum TypeDriver {
			FIREFOX, OPERA, CHROME, IE
			}
			public WebDriver createWebdriver(String typeDriver) throws MalformedURLException {
			TypeDriver type = TypeDriver.valueOf(typeDriver.toUpperCase());
			switch (type) {
			case FIREFOX:
			return new FireFoxDriverBuilder().initializeWebDriver();
			case OPERA:
			return new OperaDriverBuilder().initializeWebDriver();
			case CHROME:
			return new ChromeDriverBuilder().initializeWebDriver();
			case IE:
			return new IEDriverBuilder().initializeWebDriver();
			default:
			throw new EnumConstantNotPresentException(
			type.getDeclaringClass(), type.name());
			}
			}

}

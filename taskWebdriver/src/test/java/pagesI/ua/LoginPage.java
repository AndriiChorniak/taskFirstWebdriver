package pagesI.ua;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Anypage {
	@FindBy(name = "login")
	public WebElement loginField;
	
	@FindBy(name = "pass")
	public WebElement passwordField;
	
	@FindBy(xpath = "//p/input[@type='submit']")
	public WebElement inputButton;
	

}

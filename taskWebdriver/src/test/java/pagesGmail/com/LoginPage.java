package pagesGmail.com;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Anypage {
	@FindBy(id = "Email")
	public WebElement loginField;
	
	@FindBy(id = "next")
	public WebElement buttonNext;
	
	@FindBy(name = "Passwd")
	public WebElement passwordField;
	
	@FindBy(id = "signIn")
	public WebElement inputButton;
	
	

}

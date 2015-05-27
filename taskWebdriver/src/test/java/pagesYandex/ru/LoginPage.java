package pagesYandex.ru;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Anypage {
	@FindBy(name = "login")
	public WebElement loginField;
	
	@FindBy(name = "passwd")
	public WebElement passwordField;
	
	@FindBy(xpath = "//div[@class='auth__button']/button")
	public WebElement inputButton;
	

}

package pagesYandex.ru;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SendedPage extends Anypage {
	
	private static final String SUBJECT = "it is test";
	
	@FindBy(xpath = "//span[text()='" + SUBJECT + "']")
	public WebElement subject;

}

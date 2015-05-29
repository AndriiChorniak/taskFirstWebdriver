package pagesYandex.ru;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftedLetterPage extends Anypage {
	private static final String BODY = "some text";
	
	@FindBy(xpath = "//textarea[@id='compose-send']")
	public WebElement textInBody;
	
	@FindBy(xpath = "//button[@id='compose-submit']")
	public WebElement buttonSend;
	
	

}

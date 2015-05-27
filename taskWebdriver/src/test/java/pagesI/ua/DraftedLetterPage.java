package pagesI.ua;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftedLetterPage extends Anypage {
	private static final String BODY = "some text";
	
	@FindBy(xpath = "//textarea[text()='" + BODY+ "']")
	public WebElement textInBody;
	
	@FindBy(xpath = "//p[@class='send_container clear']/input[1]")
	public WebElement buttonSend;

}

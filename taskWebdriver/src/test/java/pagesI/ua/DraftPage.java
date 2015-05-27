package pagesI.ua;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftPage extends Anypage {

	private static final String SUBJECT = "it is test";

	@FindBy(xpath = "//span[text()='" + SUBJECT + "']")
	public WebElement subject;

	@FindBy(xpath = "//div[@class='row new']/a")
	public WebElement rowInformationAboutDraftLetter;

}

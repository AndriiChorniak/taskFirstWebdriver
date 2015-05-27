package pagesI.ua;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewLetterPage extends Anypage {
	@FindBy(id = "to")
	public WebElement addresseeField;
	
	@FindBy(name = "subject")
	public WebElement subjectField;
	
	@FindBy(id = "text")
	public WebElement bodyField;
	
	@FindBy(name = "save_in_drafts")
	public WebElement saveInDraftsButton;

}

package pagesYandex.ru;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewLetterPage extends Anypage {
	@FindBy(xpath = "//*[@class='b-yabble b-yabble_focused']/descendant-or-self::input")
	public WebElement addresseeField;
	
	@FindBy(xpath = "//input[@id='compose-subj']")
	public WebElement subjectField;
	
	@FindBy(xpath = "//textarea[@id='compose-send']")
	public WebElement bodyField;
	
	@FindBy(xpath = "//button[@id='nb-22']")
	public WebElement saveInDraftsButton;
	
	@FindBy(xpath = "//div[@class='b-popup__confirm']/button[1]")
	public WebElement buttonSaveInAppearedWindow;

}

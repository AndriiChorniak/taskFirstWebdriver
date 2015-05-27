package pagesYandex.ru;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends Anypage{
	private static final String SUBJECT = "it is test";
	
	@FindBy(xpath = "//div[@class='b-toolbar__i']/div[2]/a[2]")
	public WebElement createNewLetterButton;
	
	@FindBy(xpath = "//a[@href='#draft']")
	public WebElement draftButton;
	
	@FindBy(xpath = "//a[@href='#sent']")
	public WebElement sendedButton;
	
	@FindBy(xpath = "//span[text()='" + SUBJECT + "']")
	WebElement subject;
	
	@FindBy(xpath = "//a[@id='nb-1']")
	public WebElement settingsButton;
	
	@FindBy(xpath = "//div[@class='b-user-dropdown-content']/div[8]/a")
	public WebElement exitButton;
	
	@FindBy(className = "sn_menu_title")
	public WebElement titlename;
	
	@FindBy(xpath = "//div[@class='b-messages']/div[@class='b-messages__placeholder']")
	public WebElement verifyRow;

}

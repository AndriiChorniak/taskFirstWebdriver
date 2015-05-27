package pagesGmail.com;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends Anypage{
	private static final String SUBJECT = "it is test";
	private static final String BODY = "some text";
	
	@FindBy(xpath = "//div[@class='z0']/div[@class='T-I J-J5-Ji T-I-KE L3']")
	public WebElement createNewLetterButton;
	
	@FindBy(xpath = "//a[@href='https://mail.google.com/mail/#drafts']")
	public WebElement draftButton;
	
	@FindBy(xpath = "//a[@href='https://mail.google.com/mail/#sent']")
	public WebElement sendedButton;
	
	@FindBy(xpath = "//span[text()='" + SUBJECT + "']")
	WebElement subject;
	
	@FindBy(xpath = "//a[@href='https://profiles.google.com/?hl=ru&tab=mX']")
	public WebElement settingsButton;
	
	@FindBy(xpath = "//a[@href='https://mail.google.com/mail/logout?hl=ru']")
	public WebElement exitButton;
	
	@FindBy(className = "sn_menu_title")
	public WebElement titlename;
	
	@FindBy(xpath = "//td[@class='Hm']/img[3]")
	public WebElement saveInDraftsButton;
	
	@FindBy(xpath = "//div[@class='oj']/div[@class='wO nr l1']/textarea")
	public WebElement addresseeField;
	
	@FindBy(xpath = "//input[@name='subjectbox']")
	public WebElement subjectField;
	
	@FindBy(xpath = "//div[@aria-label='Тело письма']")
	public WebElement bodyField;

	@FindBy(xpath = "//tr[@class='zA yO']/td[4]")
	public WebElement rowInformationAboutDraftLetter;
	
	@FindBy(xpath = "//textarea[text()='" + BODY+ "']")
	public WebElement textInBody;
	
	@FindBy(xpath = "//tr[@class='n1tfz']/td/div/div[2]")
	public WebElement buttonSend;

}

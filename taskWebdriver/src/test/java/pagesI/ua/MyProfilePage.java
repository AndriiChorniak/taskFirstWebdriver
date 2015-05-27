package pagesI.ua;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends Anypage{
	private static final String SUBJECT = "it is test";
	
	@FindBy(xpath = "//div[@class='b-toolbar__block b-toolbar__block_chevron']/a[2]")
	public WebElement createNewLetterButton;
	
	@FindBy(xpath = "//ul[@class='list_underlined']/li[3]/a")
	public WebElement draftButton;
	
	@FindBy(xpath = "//ul[@class='list_underlined']/li[2]/a")
	public WebElement sendedButton;
	
	@FindBy(xpath = "//span[text()='" + SUBJECT + "']")
	WebElement subject;
	
	@FindBy(xpath = "//ul[@class='ho_menu ho_menu-account']/li[3]/span")
	public WebElement settingsButton;
	
	@FindBy(xpath = "//ul[@class='ho_popup_menu']/li[7]/a")
	public WebElement exitButton;
	
	@FindBy(className = "sn_menu_title")
	public WebElement titlename;

}

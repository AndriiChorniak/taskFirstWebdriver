
package testMail;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pagesYandex.ru.CreateNewLetterPage;
import pagesYandex.ru.DraftPage;
import pagesYandex.ru.DraftedLetterPage;
import pagesYandex.ru.LoginPage;
import pagesYandex.ru.MyProfilePage;
import pagesYandex.ru.SendedPage;







import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import driver.WebDriverFactory;

public class TestYandex {
	private static final String START_URL = "http://www.yandex.ua/";
	private static final String LOGIN = "andrii.chorniak@yandex.ru";
	private static final String PASSWORD = "12345am";
	private static final String ADDRESSEE = "andrii.chorniak@i.ua";
	private static final String SUBJECT = "it is test";
	private static final String BODY = "some text";
	
	private WebDriver driver;
	private WebDriverFactory webDriverFactory = new WebDriverFactory();
	private LoginPage loginPage;
	private MyProfilePage mypropilepage;
	private CreateNewLetterPage createnewletterpage;
	private DraftPage draftpage;
	private DraftedLetterPage draftedletterrow;
	private SendedPage sendedpage;
	
	@BeforeClass(description = "Start browser")
	public void startBrowser() {
		try {
			driver = webDriverFactory.createWebdriver("firefox");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(START_URL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		goToMainPage();
	}

	@AfterClass
	public void afterClass() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
	}

	@Test(description = "Login")
	public void login() {
		
		doLogin(LOGIN, PASSWORD);
		assertTrue(isElementPresent(isLogined()));
	}

	private void goToMainPage() {
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		mypropilepage = PageFactory.initElements(driver,MyProfilePage.class );
		
		
	}

	private By isLogined() {
		//mypropilepage.titlename;
		return By.xpath("//span[@class='js-messages-title-dropdown-name']");
		
	}

	@Test(description = "Cretae new letter", dependsOnMethods = { "login" })
	public void createNewLetter() {
		clickOnButtonCreateNewLetter();
		formFilling(ADDRESSEE, SUBJECT, BODY);
		saveInDraft();
		clickOnButtonSaveInAppearedWindow();
		clickOnButtonDraft();
		assertTrue(isElementPresent(isSubjectPresent()));
	}

	private void clickOnButtonSaveInAppearedWindow() {
		createnewletterpage.buttonSaveInAppearedWindow.click();
		
	}

	private void clickOnButtonCreateNewLetter() {
		mypropilepage.createNewLetterButton.click();
	}

	private By isSubjectPresent() {
		return By.xpath("//span[text()='" + SUBJECT + "']");
	}

	private void clickOnButtonDraft() {
		draftpage = PageFactory.initElements(driver, DraftPage.class);
		mypropilepage.draftButton.click();
	}

	@Test(description = "Verify body of letter", dependsOnMethods = { "createNewLetter" })
	public void verifyBodyOfLetter() {
		verifyBody();
		//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(draftedletterrow.textInBody));
		assertEquals(returnBody(), BODY);
		clickOnButtonSend();
	}

	private String returnBody() {
		return draftedletterrow.textInBody.getAttribute("value");
	}

	
	

	@Test(description = "Verify draft is disappear", dependsOnMethods = { "verifyBodyOfLetter" })
	public void verifyDraftIsDisappear() {
		clickOnButtonDraft();
		//new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(mypropilepage.verifyRow));
		new WebDriverWait(driver, 5, 5000).until(ExpectedConditions.titleContains("Черновики"));
		assertTrue(isElementPresent(isSubjectPresent()));
	}

	@Test(description = "Verify sended letters", dependsOnMethods = { "verifyDraftIsDisappear" })
	public void verifySendedLetters() {
		goToSendedLetters();
		assertTrue(isElementPresent(isSubjectPresent()));
	}

	@Test(description = "Logout", dependsOnMethods = { "verifySendedLetters" })
	public void logout() {
		doLogout();
		assertTrue(isElementPresent(isButtonInputPresent()));
	}

	private By isButtonInputPresent() {
		return By.xpath("//div[@class='auth__button']/button");
	}

	private void doLogout() {
		mypropilepage.settingsButton.click();
		mypropilepage.exitButton.click();
	}

	private void goToSendedLetters() {
		mypropilepage.sendedButton.click();
		sendedpage = PageFactory.initElements(driver, SendedPage.class);
	}

	private void verifyBody() {
		draftpage.rowInformationAboutDraftLetter.click();
		draftedletterrow = PageFactory.initElements(driver, DraftedLetterPage.class);
	}

	private void clickOnButtonSend() {
		draftedletterrow.buttonSend.click();
	}

	private void formFilling(String addressee, String subject, String body) {
		createnewletterpage = PageFactory.initElements(driver, CreateNewLetterPage.class);
		createnewletterpage.addresseeField.clear();
		createnewletterpage.addresseeField.sendKeys(addressee);
		createnewletterpage.subjectField.clear();
		createnewletterpage.subjectField.sendKeys(subject);
		createnewletterpage.bodyField.clear();
		createnewletterpage.bodyField.sendKeys(body);
	}

	private void saveInDraft() {
		createnewletterpage.saveInDraftsButton.click();
	}

	private void doLogin(String login, String password) {
		loginPage.loginField.clear();
		loginPage.loginField.sendKeys(login);
		loginPage.passwordField.clear();
		loginPage.passwordField.sendKeys(password);
		loginPage.inputButton.click();
	}

	private boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}

}

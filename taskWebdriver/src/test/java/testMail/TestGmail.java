package testMail;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
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








import pagesGmail.com.LoginPage;
import pagesGmail.com.MyProfilePage;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import driver.WebDriverFactory;

public class TestGmail {
	private static final String START_URL = "https://gmail.com";
	private static final String LOGIN = "andrii.chorniak@gmail.com";
	private static final String PASSWORD = "123456am";
	private static final String ADDRESSEE = "andrii.chorniak@yandex.ru";
	private static final String SUBJECT = "it is test";
	private static final String BODY = "some text";
	
	private WebDriver driver;
	private WebDriverFactory webDriverFactory = new WebDriverFactory();
	private LoginPage loginPage;
	private MyProfilePage mypropilepage;
		
	@BeforeClass(description = "Start browser")
	public void startBrowser() {
		try {
			driver = webDriverFactory.createWebdriver("firefox");
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		driver.manage().window().maximize();
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
		mypropilepage = PageFactory.initElements(driver, MyProfilePage.class );
		
		
	}

	private By isLogined() {
		return By.xpath("//div[@class='gb_fa gb_s gb_0c gb_r']/a");
	}

	@Test(description = "Cretae new letter", dependsOnMethods = { "login" })
	public void createNewLetter() {
		clickOnButtonCreateNewLetter();
		formFilling(ADDRESSEE, SUBJECT, BODY);
		saveInDraft();
		clickOnButtonDraft();
		assertTrue(isElementPresent(isSubjectPresent()));
	}

	private void clickOnButtonCreateNewLetter() {
		mypropilepage.createNewLetterButton.click();
	}

	private By isSubjectPresent() {
		return By.xpath("//span[text()='" + SUBJECT + "']");
	}

	private void clickOnButtonDraft() {
		mypropilepage.draftButton.click();
	}

	@Test(description = "Verify body of letter", dependsOnMethods = { "createNewLetter" })
	public void verifyBodyOfLetter() {
		verifyBody();
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(bodyWait()));
		assertEquals(driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).getText(), BODY);
		clickOnButtonSend();
	}
	
	

	private By bodyWait() {
		return By.xpath("//div[@class='Am Al editable LW-avf']");
	}

	@Test(description = "Verify draft is disappear", dependsOnMethods = { "verifyBodyOfLetter" })
	public void verifyDraftIsDisappear() {
		clickOnButtonDraft();
		assertTrue(isElementPresent(isSubjectPresent()));
	}


	@Test(description = "Verify sended letters", dependsOnMethods = { "verifyDraftIsDisappear" })
	public void verifySendedLetters() {
		goToSendedLetters();
		new WebDriverWait(driver, 5, 5000).until(ExpectedConditions.titleContains("Отправленные"));
		assertTrue(isElementPresent(isSubjectPresent()));
	}

	@Test(description = "Logout", dependsOnMethods = { "verifySendedLetters" })
	public void logout() {
		doLogout();
		assertTrue(isElementPresent(isButtonInputPresent()));
	}

	private By isButtonInputPresent() {
		return By.id("Email");
	}

	private void doLogout() {
		mypropilepage.settingsButton.click();
		mypropilepage.exitButton.click();
	}

	private void goToSendedLetters() {
		mypropilepage.sendedButton.click();
	
	}

	private void verifyBody() {
		mypropilepage.rowInformationAboutDraftLetter.click();
		
	}

	private void clickOnButtonSend() {
		mypropilepage.buttonSend.click();
	}

	private void formFilling(String addressee, String subject, String body) {
		
		mypropilepage.addresseeField.clear();
		mypropilepage.addresseeField.sendKeys(addressee);
		mypropilepage.subjectField.clear();
		mypropilepage.subjectField.sendKeys(subject);
		mypropilepage.bodyField.clear();
		mypropilepage.bodyField.sendKeys(body);
	}

	private void saveInDraft() {
		mypropilepage.saveInDraftsButton.click();
	}

	private void doLogin(String login, String password) {
		loginPage.loginField.clear();
		loginPage.loginField.sendKeys(login);
		loginPage.buttonNext.click();
		loginPage.passwordField.clear();
		loginPage.passwordField.sendKeys(password);
		loginPage.inputButton.click();
	}

	private boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}

}

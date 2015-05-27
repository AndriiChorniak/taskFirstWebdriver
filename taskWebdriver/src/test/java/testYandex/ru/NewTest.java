package testYandex.ru;

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
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

public class NewTest {
	private static final String START_URL = "http://yandex.ua/";
	private static final String LOGIN = "andrii.chorniak@yandex.ru";
	private static final String PASSWORD = "12345am";
	private static final String ADDRESSEE = "Andrii_Chorniak@epam.com";
	private static final String SUBJECT = "it is test";
	private static final String BODY = "some text";
	private WebDriver driver;

	@BeforeClass(description = "Start browser")
	public void startBrowser() {
		driver = new FirefoxDriver();
		driver.get(START_URL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {

	}

	@Test(description = "Login")
	public void login() {
		MainPage main = new MainPage(driver);
		doLogin(LOGIN, PASSWORD);
		assertTrue(isElementPresent(By.xpath(".//*[@id='nb-1']/span[1]")));
	}

	@Test(description = "Cretae new letter", dependsOnMethods = { "login"})
	public void createNewLetter() {
		driver.findElement(
				By.xpath(".//*[@id='js-page']//a[@href='#compose']"))
				.click();
		formFilling(ADDRESSEE, SUBJECT, BODY);
		driver.findElement(
				By.xpath("//div[@class='b-folders__i']/div[5]/span[@class='b-folders__folder__name']/a"))
				.click();
		assertTrue(isElementPresent(By
				.xpath("//span[@tittle='Andrii_Chorniak@epam.com']")));
	}
	@Test(description = "Verify that letter contain all fields", dependsOnMethods = { "createNewLetter" })
	public void verifyLetterContainAllFields(){
		
	}

	private void formFilling(String addressee, String subject, String body) {
		WebElement addresseeInput = driver
				.findElement(By
						.xpath(".//*[@id='js-page']/div/div[5]/div/div[3]/div/div[3]/div/div/div/div[2]/div/div/form/table/tbody/tr[3]/td[2]/div[2]"));
		addresseeInput.clear();
		addresseeInput.sendKeys(addressee);
		WebElement subjectInput = driver.findElement(By.id("compose-subj"));
		subjectInput.clear();
		subjectInput.sendKeys(subject);
		WebElement bodyInput = driver.findElement(By.id("compose-send"));
		bodyInput.clear();
		bodyInput.sendKeys(body);

		driver.findElement(By.id("nb-22")).click();

	}

	private void doLogin(String login, String password) {
		WebElement loginInput = driver.findElement(By.name("login"));
		loginInput.clear();
		loginInput.sendKeys(login);
		WebElement passwordInput = driver.findElement(By.name("passwd"));
		passwordInput.clear();
		passwordInput.sendKeys(password);
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();

	}

	private boolean isElementPresent(By by) {
		return !driver.findElements(by).isEmpty();
	}

}

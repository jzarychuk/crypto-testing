package stepDefinitions;

//import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.Collections;

public class FileEncryptionAndDecryptionTest {

	private WebDriver browser;
	private WebDriverWait wait;
	private String key;

	@Before
	public void setUp() {
		// Setup the ChromeDriver using WebDriverManager, see the pom.xml file
		WebDriverManager.chromedriver().setup();
		// Define Chrome options for the browser
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		// Initialize the WebDriver with ChromeDriver and the defined options
		browser = new ChromeDriver(chromeOptions);
		// Maximize the browser window
		browser.manage().window().maximize();
		// Initialize WebDriverWait with a timeout of 30 seconds
		wait = new WebDriverWait(browser, Duration.ofSeconds(30));
	}

	@After
	public void cleanUp() {
		browser.quit();
	}

	@Given("The user visits the app's homepage")
	public void The_user_visits_the_homepage() {
		browser.get("http://localhost/#/index");
	}

	@When("The user clicks on login button")
	public void the_user_clisk_on_the_login_button() throws InterruptedException {
		Thread.sleep(2000);
		WebElement login = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".btn-outline-secondary")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", login);
	}

	@When("The user logs in using their email address and password")
	public void the_user_logins_with_correct_email_address_and_password() {

		String email = AccountCreationAndApprovalTest.email_temp;
		String password = AccountCreationAndApprovalTest.password_temp;

		WebElement email_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[1]/input")));
		email_box.click();
		email_box.sendKeys(email);

		WebElement password_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[2]/input")));
		password_box.click();
		password_box.sendKeys(password);

		WebElement login_click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[3]/button")));
		login_click.click();
	}

	@When("The user cliks on the Try Encryption button on homepage")
	public void user_clicks_try_encryption() throws InterruptedException {
		Thread.sleep(2000);
		WebElement encryption_button = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/div/app-home/div[1]/div[1]/div/div/div[1]/div/div/a[1]")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", encryption_button);
	}

	@When("The user cliks on Choose File button and selects a files to upload")
	public void user_selects_a_file_to_upload() throws InterruptedException {
		Thread.sleep(2000);
		WebElement choose_file_button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"body > app-root > app-layout > div > app-encryption > div > div > div > div:nth-child(1) > form > div > div.card-body > div > p-fileupload > div > div.p-fileupload-buttonbar > span")));
		choose_file_button.sendKeys("C:\\Users\\basha\\Downloads\\joins-sql.png");

	}

	@When("The user selects encryption type, and auto generate encryptio key")
	public void user_selects_enryptio_type() throws InterruptedException {

		Select ecryption_types_menu = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[1]/select"))));
		ecryption_types_menu.selectByIndex(0);

		WebElement auto_generate_key = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[1]/select")));

		auto_generate_key.click();

		Thread.sleep(1000);

		WebElement key_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[2]/div/textarea")));

		key = key_box.getAttribute("value");
	}

	@Then("The user clicks the Encrypt button to encrypt the file")
	public void user_clicks_enrypt_and_recieves_encrypted_file() {
		WebElement encrypt_key = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[2]/button/svg/path")));

		encrypt_key.click();

		WebElement finished_status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/span[2]")));

		Assert.assertTrue(finished_status.isDisplayed());

	}

	// Scenario: User wants to decrypt an uploaded file

	@When("The user cliks on the Try Decryption button on homepage")
	public void The_user_cliks_on_the_Try_Decryption_button_on_homepage() throws InterruptedException {
		Thread.sleep(2000);
		WebElement decryption_button = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("body > app-root > app-layout > div > app-home > div.home > div.land.d-flex.align-items-center > div > div > div.col-md-7.d-flex.align-items-center > div > div > a:nth-child(2)")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", decryption_button);
	}

	@When("The user selects encryption type, and inputs the key used for encryption")
	public void The_user_selects_encryption_type_and_inputs_the_key_used_for_encryption() throws InterruptedException {

		Select ecryption_types_menu = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[1]/select"))));
		ecryption_types_menu.selectByIndex(0);

		WebElement key_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[2]/div/textarea")));

		key_box.sendKeys(key);

		Thread.sleep(500);

	}

	@Then("The user clicks the Decrypt button to decrypt the file")
	public void The_user_clicks_the_Decrypt_button_to_encrypt_the_file() throws InterruptedException {

		WebElement decrypt_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[3]/button")));

		decrypt_button.click();

		WebElement finished_status_1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("Finished")));
		WebElement finished_status_2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("Finished")));

		if (finished_status_1.isDisplayed() || finished_status_2.isDisplayed()) {

			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

}

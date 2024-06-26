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

	private final String email = "bashar.nexus@gmail.com";
	private final String password = "basha97";

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
	public void the_user_clisk_on_the_login_button() {

		WebElement login = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", login);
	}

	@When("The user logs in using their email address and password")
	public void the_user_logins_with_correct_email_address_and_password() {

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
	public void user_clicks_try_encryption() {
		WebElement encryption_button = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/div/app-home/div[1]/div[1]/div/div/div[1]/div/div/a[1]")));
		encryption_button.click();
	}

	@When("The user cliks on Choose File button and selects a files to upload for Encryption")
	public void user_selects_a_file_to_upload_for_Encryption() {

		WebElement file_upload_container = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/p-fileupload")));

		WebElement file_input = file_upload_container.findElement(By.xpath(".//input[@type='file']"));
		file_input.sendKeys("C:\\Users\\basha\\Downloads\\joins-sql.png");

	}

	@When("The user cliks on Choose File button and selects a files to upload for Decryption")
	public void user_selects_a_file_to_upload_for_Decryption() {

		WebElement file_upload_container = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/p-fileupload/div/div[1]/span")));

		WebElement file_input = file_upload_container.findElement(By.xpath(".//input[@type='file']"));
		file_input.sendKeys("C:\\Users\\basha\\Downloads\\joins-sql (1).png");

	}

	@When("The user selects encryption type, and auto generate encryptio key")
	public void user_selects_enryptio_type() {

		Select ecryption_types_menu = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[1]/select"))));
		ecryption_types_menu.selectByIndex(0);

		WebElement key_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[2]/div/textarea")));

		key_box.sendKeys("2b7e151628aed2a6abf7158809cf4f3c");
	}

	@Then("The user clicks the Encrypt button to encrypt the file")
	public void user_clicks_enrypt_and_recieves_encrypted_file() {
		WebElement encrypt_key = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[2]/button")));

		encrypt_key.click();

		WebElement finished_status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html/body/app-root/app-layout/div/app-encryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/span[2]")));

		Assert.assertTrue(finished_status.isDisplayed());

	}

	@When("The user cliks on the Try Decryption button on homepage")
	public void The_user_cliks_on_the_Try_Decryption_button_on_homepage() {

		WebElement decryption_button = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/div/app-home/div[1]/div[1]/div/div/div[1]/div/div/a[2]")));
		decryption_button.click();
	}

	@When("The user selects encryption type, and inputs the key used for encryption")
	public void The_user_selects_encryption_type_and_inputs_the_key_used_for_encryption() {

		Select encryption_types_menu = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[1]/select"))));
		encryption_types_menu.selectByIndex(0);

		Select encryption_key_choice = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[2]/select"))));
		encryption_key_choice.selectByIndex(0);

		WebElement key_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[2]/div/textarea")));

		key_box.sendKeys("2b7e151628aed2a6abf7158809cf4f3c");

	}

	@When("The user selects encryption type, and inputs the wrong key used for encryption")
	public void The_user_selects_encryption_type_and_inputs_the_wrong_key_used_for_encryption() {

		Select encryption_types_menu = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[1]/select"))));
		encryption_types_menu.selectByIndex(0);

		Select encryption_key_choice = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[1]/div[2]/select"))));
		encryption_key_choice.selectByIndex(0);

		WebElement key_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[2]/div/textarea")));

		key_box.sendKeys("12345678bbbb34223bbbaaazzz78888");
	}

	@Then("The user clicks the Decrypt button to decrypt the file correctly")
	public void The_user_clicks_the_Decrypt_button_to_encrypt_the_file_1() throws InterruptedException {

		Thread.sleep(5000);

		WebElement decrypt_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[3]/button")));

		decrypt_button.click();

		WebElement finished_status = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"body > app-root > app-layout > div > app-decryption > div > div > div > div:nth-child(1) > form > div > div.card-body > div > div.p-3.d-flex.align-items-center.justify-content-between.border-bottom > span.status")));

		Assert.assertEquals("finished", finished_status.getText().toLowerCase());

	}

	@When("The user clicks the Decrypt button to decrypt the file incorrectly")
	public void The_user_clicks_the_Decrypt_button_to_encrypt_the_file_2() {

		WebElement decrypt_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[3]/button")));

		decrypt_button.click();

	}

	@Then("The user is presented with an error message that the inputted encryption key is invalid")
	public void The_user_is_presented_with_an_error_message_that_the_inputted_encryption_key_is_invalid()
			throws InterruptedException {

		Thread.sleep(5000);

		WebElement decrypt_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-decryption/div/div/div/div[1]/form/div/div[2]/div/div[2]/div[3]/button")));

		decrypt_button.click();

		WebElement error_message = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("body > app-root > app-layout > div > p-toast > div > p-toastitem > div > div")));

		Assert.assertTrue(error_message.getText().toLowerCase().contains("is not a valid key"));

	}

}

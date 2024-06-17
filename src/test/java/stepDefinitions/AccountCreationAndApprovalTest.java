package stepDefinitions;

//import static org.junit.Assert.assertTrue;
import java.time.Duration;
import java.util.Collections;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;
import org.testng.Assert;

public class AccountCreationAndApprovalTest {

	private WebDriver browser;
	private WebDriverWait wait;

	private static final Faker faker = new Faker();

	String name = faker.name().fullName();

	private static final String email = faker.internet().emailAddress();

	public static final String email_temp = email;

	private static final String password = faker.number().digits(8);

	public static final String password_temp = password;

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

	@Given("The user visits homepage")
	public void the_user_visits_homepage() {

		browser.get("http://localhost/#/index");

	}

	@When("The user clicks on Sign Up button")
	public void User_clicks_on_Sign_Up() {

		WebElement signup = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/div/a[1]")));
		signup.click();

	}

	@When("The user inputs name, email, password, confirm password, and selects a role")
	public void User_inputs_name_email_password_confirm_password_and_selects_a_role() {

		WebElement name_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[1]/div/app-sign-up-page/div/div[2]/form/div[1]/input")));
		name_box.click();
		name_box.sendKeys(name);

		WebElement email_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[1]/div/app-sign-up-page/div/div[2]/form/div[2]/input")));
		email_box.click();
		email_box.sendKeys(email);

		WebElement password_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[1]/div/app-sign-up-page/div/div[2]/form/div[3]/input")));
		password_box.click();
		password_box.sendKeys(password);

		WebElement confirm_password_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[1]/div/app-sign-up-page/div/div[2]/form/div[4]/input")));
		confirm_password_box.click();
		confirm_password_box.sendKeys(password);

		WebElement role_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[1]/div/app-sign-up-page/div/div[2]/form/div[5]/select")));
		role_box.click();

		Select dropdown = new Select(wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[1]/div/app-sign-up-page/div/div[2]/form/div[5]/select"))));
		dropdown.selectByIndex(1);

	}

	@When("The user clicks sign up button")
	public void The_user_clicks_sign_up_button() {

		WebElement sign_up_click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[1]/div/app-sign-up-page/div/div[2]/form/div[6]/button")));

		sign_up_click.click();

	}

	@Then("The user receives a sign up success message")
	public void User_receives_a_sign_up_success_message() {

		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));
		Assert.assertEquals(successMessage.getText(),
				"Registerd successfully, please wait for admin approval to login!");
	}

	@Given("The admin visits the app's homepage")
	public void Admin_visits_home_page_to_approve_a_user() {

		browser.get("http://localhost/#/index");
	}

	@When("The admin clicks on login button")
	public void Admin_clicks_on_login_button_to_approve_a_user() {
		WebElement login = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", login);
	}

	@When("The admin inputs their correct email address and password")
	public void Admin_logins_with_email_and_password_to_approve_a_user() {

		String admin_email = "admin@uranus.com";
		String admin_password = "g8rD%+";

		WebElement email_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[1]/input")));
		email_box.click();
		email_box.sendKeys(admin_email);

		WebElement password_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[2]/input")));
		password_box.click();
		password_box.sendKeys(admin_password);

		WebElement login_click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[3]/button")));

		login_click.click();
	}

	@Then("The admin should be signed and access the website functionalities")
	public void Admin_is_signed_in() {
		WebElement adminPortal = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/ul/li[8]/a")));
		Assert.assertTrue(adminPortal.isDisplayed());
	}

	@When("The admin visits Admin Panel to approve a user")
	public void Admin_visits_Admin_Panel_to_approve_a_user() {
		WebElement adminPortal = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/ul/li[8]/a")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", adminPortal);
	}

	@Then("The admin clicks on the New Accounts tab and approves the first new user")
	public void Admin_clicks_on_New_Accounts_tab_and_approves_user() throws InterruptedException {
		WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[1]/app-accounts/div/div[1]/app-new-accounts/p-table/div/div/table/tbody/tr/td[6]/div/button[2]")));
		approveButton.click();

		Thread.sleep(5000);
		
		WebElement users_number = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[1]/app-accounts/ul/li[1]/button/span")));
		Assert.assertEquals("0", users_number.getText().toString().toLowerCase());
	}

	// User tries to sign in with correct credentials

	@When("The user clicks on the log in button")
	public void User_clicks_on_log_in() {

		WebElement login_button = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", login_button);

	}

	@When("The user logins with correct email address and password")
	public void The_user_logins_with_correct_email_address_and_password() {

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

	@Then("The user should be signed in and access the website functionalities")
	public void The_user_should_be_signed_and_access_the_website_functionalities() {

		try {
			WebElement home = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-layout/div/p-toast/div/p-toastitem/div/div/div/div[2]")));
			Assert.assertTrue(home.getText().toLowerCase().contains("successfully"));
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	// User tries to sign in with incorrect email address
	@When("The user logins with incorrect email address and correct password")
	public void The_user_logins_with_incorrect_email_address_and_correct_password() {

		WebElement email_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[1]/input")));
		email_box.click();
		email_box.sendKeys(email + ".com");

		WebElement password_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[2]/input")));
		password_box.click();
		password_box.sendKeys(password);

		WebElement login_click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[3]/button")));

		login_click.click();
	}

	@Then("The user should not be signed in and presented with an error message")
	public void The_user_should_not_be_signed_and_presented_with_an_error_message() {
		try {
			WebElement failMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("/html/body/app-root/app-layout/div/p-toast/div/p-toastitem/div/div/div/div[2]")));
			Assert.assertTrue(failMessage.getText().toLowerCase().contains("invalid"));
			
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	// User tries to sign in with incorrect password
	@When("The user logins with correct email address and incorrect password")
	public void The_user_logins_with_correct_email_address_and_incorrect_password() {

		WebElement email_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[1]/input")));
		email_box.click();
		email_box.sendKeys(email);

		WebElement password_box = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[2]/input")));
		password_box.click();
		password_box.sendKeys(password + "ggGg");

		WebElement login_click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/app-header/div[2]/div/app-login-page/div/div[2]/form/div[3]/button")));

		login_click.click();
	}
}

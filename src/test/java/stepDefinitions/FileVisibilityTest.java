package stepDefinitions;

import java.time.Duration;
import java.util.Collections;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FileVisibilityTest {

	private WebDriver browser;
	private WebDriverWait wait;

	private final String email = "bashar.nexus@gmail.com";
	private final String password = "bash97";

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

	// Scenario: Admin changes the role of a registered user

	@Given("The admin visits the homepage")
	public void Admin_visits_homepage() {
		browser.get("http://localhost/#/index");

	}

	@When("The admin presses the login button")
	public void Admin_clicks_on_login_button() {

		WebElement login = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", login);
	}

	@When("The admin logins with email and password")
	public void Admin_logins_with_emailand_password() {

		String email = "admin@uranus.com";
		String password = "g8rD%+";

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

	@When("The admin clicks on the Admin Panel tab")
	public void Admin_visits_Admin_Panel() {
		WebElement adminPortal = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/ul/li[8]/a")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", adminPortal);
	}

	@When("The admin clicks on the Resources category")
	public void The_admin_clicks_on_the_Resources_category() {
		WebElement resources = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[1]/ul/li[3]/button")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", resources);

	}

	@When("The admin clicks on the Choose button to select a file for upload")
	public void The_admin_clicks_on_the_Choose_button_to_select_a_file_for_upload() {
		WebElement choose_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[3]/app-resources/div[1]/div/div/p-fileupload/div/div[1]/span")));
		WebElement file_input = choose_button.findElement(By.xpath(".//input[@type='file']"));
		file_input.sendKeys("C:\\Users\\basha\\Downloads\\joins-sql.png");

	}

	@When("The admin clicks the Upload button")
	public void The_admin_clicks_the_Upload_button() {
		WebElement upload_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[3]/app-resources/div[1]/div/div/p-fileupload/div/div[1]/p-button[1]/button/span[2]")));
		upload_button.click();

	}

	@Then("The file is uploaded to the app")
	public void The_file_is_uploaded_to_the_app() {

		WebElement download_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[3]/app-resources/div[2]/div/div/button/span/span")));
		Assert.assertTrue(download_button.isDisplayed());
	}

	@When("The admin logins as user with email and password")
	public void The_admin_logins_as_user_with_email_and_password() {

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

	@When("The admin clicks on the username option and accesses My Profile page")
	public void The_admin_clicks_on_the_username_option_and_accesses_My_Profile_page() {

		WebElement user_options = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/div/ul/li/a")));

		((JavascriptExecutor) browser).executeScript("arguments[0].click();", user_options);

		WebElement my_profile = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/div/ul/li/div/a[1]")));

		((JavascriptExecutor) browser).executeScript("arguments[0].click();", my_profile);

	}

	@Then("The system shouldn't display the Resources category")
	public void The_system_shouldnt_display_the_Resources_category() throws InterruptedException {
		
		Thread.sleep(3000);
		
		try {
			WebElement resources = browser.findElement(By.name("Resources"));
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
}

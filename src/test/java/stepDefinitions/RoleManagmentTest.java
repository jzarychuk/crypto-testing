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

public class RoleManagmentTest {

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

	@Given("Admin visits homepage")
	public void Admin_visits_homepage() {
		browser.get("http://localhost/#/index");

	}

	@When("Admin clicks on login button")
	public void Admin_clicks_on_login_button() {

		WebElement login = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", login);
	}

	@When("Admin logins with email and password")
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

	@When("Admin visits Admin Panel")
	public void Admin_visits_Admin_Panel() {
		WebElement adminPortal = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/ul/li[8]/a")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", adminPortal);
	}

	@When("Admin clicks on Registered Accounts tab")
	public void Admin_clicks_on_Registered_Accounts_tab() {
		WebElement reg_accounts_tab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#reg-tab")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", reg_accounts_tab);

	}

	@Then("Admin changes the first user role to Employee")
	public void Admin_changes_the_first_user_role_to_Employee() throws InterruptedException {
		WebElement reg_accounts_tab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#reg-tab")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", reg_accounts_tab);
		
		Thread.sleep(2000);
		
		WebElement edit_role_button = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("#pr_id_5-table > tbody > tr > td:nth-child(6) > div > button")));
		edit_role_button.click();

		try {
			Select roles_menu = new Select(browser.findElement(By.xpath(
					"/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[1]/app-accounts/div/div[3]/app-user-account/p-table/div/div/table/tbody/tr[1]/td[4]/p-celleditor/select")));
			roles_menu.selectByValue("Employee");
		} catch (Exception e) {
			e.printStackTrace();
		}
		WebElement approve_button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
				"#pr_id_5-table > tbody > tr:nth-child(1) > td:nth-child(6) > div > button.p-element.p-button-rounded.p-button-text.p-button-success.mr-2.p-button.p-component.p-button-icon-only.ng-star-inserted > span")));
		approve_button.click();
		
		Thread.sleep(2000);
		
		WebElement success_message = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector("body > app-root > app-layout > div > p-toast > div > p-toastitem > div > div > div")));
		Assert.assertTrue(success_message.getText().toLowerCase().contains("successfully"));

	}

	@Given("The user visits the homepage")
	public void The_user_visits_the_homepage() {
		browser.get("http://localhost/#/index");
	}

	@When("The user clicks on the login button")
	public void the_user_clisk_on_the_login_button() {
		WebElement login = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
		((JavascriptExecutor) browser).executeScript("arguments[0].click();", login);
	}

	@When("The user logins with their email address and password")
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

	@Then("The user should not have an admin panel to access and update other users roles")
	public void The_user_should_not_have_an_admin_panel_ro_access_update_other_users_roles() throws InterruptedException {
		
		Thread.sleep(3000);
		
		try {

			WebElement adminPortal = browser.findElement(
					By.xpath("/html/body/app-root/app-layout/app-header/header/nav/div/div/ul/li[8]/a"));
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

}

package stepDefinitions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.github.javafaker.Faker;
import java.time.Duration;
import java.util.Collections;

// Define the SignUpTest class which contains all the test methods
public class SignUpTest {

    // Declare WebDriver and WebDriverWait objects
    private WebDriver driver;
    private WebDriverWait wait;

    // Initialize a Faker object for generating random data
    private static final Faker faker = new Faker();
    // Generate a random full name and store it in the name variable
    String name = faker.name().fullName();
    // Generate a random email address and store it in the email variable
    private static final String email = faker.internet().emailAddress();
    // Generate a random 8-digit number and store it in the password variable
    String password = faker.number().digits(8);

    // This method will be run before each test method to set up the test environment
    @Before
    public void setup() {
        // Setup the ChromeDriver using WebDriverManager, see the pom.xml file
        WebDriverManager.chromedriver().setup();
        // Define Chrome options for the browser
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 
        // Initialize the WebDriver with ChromeDriver and the defined options
        driver = new ChromeDriver(chromeOptions);
        // Maximize the browser window
        driver.manage().window().maximize();
        // Initialize WebDriverWait with a timeout of 30 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // This method will be run after each test method to clean up the test environment
    @After
    public void tearDown() {
        // Check if the driver is not null and then quit the driver to close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    // Given step: User visits the home page to sign up
    @Given("User visits home page to sign up")
    public void user_visits_on_the_homepage() {
        // Navigate to the home page URL
        driver.get("http://localhost"); // your app should run on local host
    }

    // When step: User clicks on the Sign Up button
    @When("User clicks on Sign Up Button")
    public void user_clicks_on_sign_up_button() {
        // Wait for the Sign Up button to be clickable and then click it
        WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#collapsibleNavId div a:first-child")));
        signUpButton.click();
    }

    // When step: User inputs name, email, password, confirm password, and selects a role
    @When("User inputs name, email, password, confirm password and selects a role")
    public void user_inputs_name_email_password_and_confirm_password() {
        // Wait for the name field to be clickable and then input the generated name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("signUpName")));
        nameField.sendKeys(name);

        // Wait for the email field to be clickable and then input the generated email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginEmail")));
        emailField.sendKeys(email);

        // Wait for the password field to be clickable and then input the generated password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginPassword")));
        passwordField.sendKeys(password);

        // Wait for the confirm password field to be clickable and then input the generated password again
        WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("signUpConfirmPassword")));
        confirmPasswordField.sendKeys(password);

        // Wait for the role dropdown to be visible, scroll into view, and select the "USER" role
        WebElement roleDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signUpRole")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", roleDropdown);
        new Select(roleDropdown).selectByValue("USER");
    }

    // When step: User clicks the submit sign up button
    @When("User clicks submit sign up button")
    public void user_clicks_submit_sign_up_button() {
        // Wait for the submit button to be clickable and then click it
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-sign-up-page form .btn-submit")));
        submitButton.click();
    }

    // Then step: User receives a sign-up success message
    @Then("User receives a sign up success message")
    public void user_receives_a_sign_up_success_message() {
        // Wait for the success message to be visible and then assert its text
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast-detail")));
        Assert.assertEquals(successMessage.getText(), "Registerd successfully, please wait for admin approval to login!");
    }

    // Given step: Admin visits the home page to approve a user
    @Given("Admin visits home page to approve a user")
    public void admin_visits_home_page_to_approve_a_user() {
        // Navigate to the home page URL
        driver.get("http://localhost");
    }

    // When step: Admin clicks on the login button to approve a user
    @When("Admin clicks on login button to approve a user")
    public void admin_clicks_on_login_button_to_approve_a_user() {
        // Wait for the login button to be clickable and then click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
        loginButton.click();
    }

    // When step: Admin logs in with email and password to approve a user
    @When("Admin logins with email and password to approve a user")
    public void admin_logins_with_email_and_password_to_approve_a_user() {
        // Wait for the email field to be clickable and then input the admin email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-header #signUp app-login-page .auth-form #loginEmail")));
        emailField.sendKeys("admin@uranus.com");  // change it to your admin email if it is other than this

        // Wait for the password field to be clickable and then input the admin password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-header #signUp app-login-page .auth-form #loginPassword")));
        passwordField.sendKeys("g8rD%+"); // change it to your admin password if it is other than this

        // Wait for the login submit button to be clickable and then click it
        WebElement loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-header #signUp app-login-page .auth-form button")));
        loginSubmitButton.click();
    }

    // When step: Admin visits the Admin Panel to approve a user
    @When("Admin visits Admin Panel to approve a user")
    public void admin_visits_admin_panel_to_approve_a_user() {
        // Wait for the message toast cross button to be clickable and then click it
        WebElement messageToastCrossButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-toast-icon-close")));
        messageToastCrossButton.click();

        // Wait for the admin module to be clickable and then click it
        WebElement adminModule = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div #collapsibleNavId ul li:nth-child(8) a")));
        adminModule.click();
    }

    // When step: Admin clicks on the New Accounts tab
    @When("Admin clicks on New Accounts tab")
    public void admin_clicks_on_new_accounts_tab() {
        // Wait for the new accounts tab to be clickable and then click it
        WebElement newAccountsTab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#new-tab")));
        newAccountsTab.click();
    }

    // Then step: Admin approves the first new user
    @Then("Admin approves the first new user")
    public void admin_approves_the_new_user() {
        // Wait for the user placeholder to be visible and then assert its text matches the user's email
        WebElement userPlaceHolder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#new .p-element > .p-component.p-datatable.p-datatable-striped table[role='table'] .ng-star-inserted.p-element > td:nth-of-type(2) > .ng-star-inserted")));
        Assert.assertEquals(userPlaceHolder.getText(), email);

        // If the user placeholder text matches the user's email, approve the user
        if (userPlaceHolder.getText().compareTo(email) == 0) {
 
                try {
                    // Wait for the approve button to be clickable
                    WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("tr:nth-of-type(1) > td:nth-of-type(6) > .align-items-center.d-flex.justify-content-center > .action.approve.p-button.p-component.p-element")));
                    
                    // Debug information to check if the button is found and clickable
                    System.out.println("Approve button is found and is clickable.");

                    // Click the approve button
                    approveButton.click();
                } catch (Exception e) {
                    // Debug information for any exceptions
                    System.out.println("Exception occurred: " + e.getMessage());
                }
        }
    }
}

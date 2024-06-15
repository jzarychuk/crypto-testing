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

public class adminPanelSteps {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp(){

        // set up the ChromeDriver using WebDriverManager (see the pom.xml file)
        WebDriverManager.chromedriver().setup();

        // define Chrome options for the browser
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        // initialize the WebDriver with ChromeDriver and the defined options
        driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }
    @After
    public void closeWindow(){
        driver.quit();
    }

    @Given("I am logged in as an admin")
    public void i_am_logged_in_as_an_admin() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        // navigate to application
        driver.get("http://localhost");

        // login screen
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-outline-secondary")));
        loginButton.click();

        // enter email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-header #signUp app-login-page .auth-form #loginEmail")));
        emailField.sendKeys("admin@uranus.com");

        // enter password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-header #signUp app-login-page .auth-form #loginPassword")));
        passwordField.sendKeys("g8rD%+");

        // login
        WebElement loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("app-header #signUp app-login-page .auth-form button")));
        loginSubmitButton.click();

    }

    @When("I open the admin panel")
    public void i_open_the_admin_panel() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        // close the message toast cross button
        WebElement messageToastCrossButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-toast-icon-close")));
        messageToastCrossButton.click();

        // navigate to admin panel
        WebElement adminPanel = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div #collapsibleNavId ul li:nth-child(8) a")));
        adminPanel.click();

    }

    @Then("I should see the admin panel title as {string}")
    public void i_should_see_the_admin_panel_title_as(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        // confirm the admin panel title is displayed
        WebElement adminPanelTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[1]/h4")));
        Assert.assertEquals(string, adminPanelTitle.getText());

    }

    @When("I edit the role of the first user to {string}")
    public void i_edit_the_role_of_the_first_user_to(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        // navigate to tab for employee accounts, since only makes sense to change role of user that is not already {string}
        WebElement staffAccountsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[1]/app-accounts/ul/li[2]/button")));
        staffAccountsTab.click();

        // find button to edit role of first user in list
        WebElement editRoleButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#pr_id_4-table > tbody > tr:nth-child(1) > td:nth-child(6) > div > button")));
        editRoleButton.click();

        // select the "User" role from the role dropdown
        Select roleSelectDropdown = new Select(driver.findElement(By.xpath(
                "/html/body/app-root/app-layout/div/app-admin-layout/div/div/div[2]/div/div[1]/app-accounts/div/div[2]/app-staff-account/p-table/div/div/table/tbody/tr[1]/td[4]/p-celleditor/select")));
        roleSelectDropdown.selectByValue(string);

        // apply change
        WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "#pr_id_4-table > tbody > tr:nth-child(1) > td:nth-child(6) > div > button.p-element.p-button-rounded.p-button-text.p-button-success.mr-2.p-button.p-component.p-button-icon-only.ng-star-inserted > span")));
        approveButton.click();

    }

    @Then("I should see a success message {string}")
    public void i_should_see_a_success_message(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        WebElement messageBanner = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "/html/body/app-root/app-layout/div/p-toast/div/p-toastitem/div/div/div/div[2]")));
        Assert.assertEquals(string, messageBanner.getText());

    }

}
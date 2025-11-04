import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static org.testng.Assert.assertTrue;

public class LoginValidationTest {
    static String scenario = "01_Login and Out";
    static int screenshotCounter = 0;
    static String filePath;
    static File logFile;
    static WebDriver driver;

    @BeforeClass
    public void setup() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-gpu", "--disable-dev-shm-usage", "--window-size=1920,1080");
        driver = new ChromeDriver(options);
        setupFoldersAndLog();
    }

    @Test
    public void loginAndValidate() throws Exception {
        driver.get("https://todoist.com/login");
        Thread.sleep(2000);
        takeScreenshot(driver, "Login Page");
    
        // Invalid login using XPaths
        driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys("LetsCreateAnErrorMessage@gmail.com");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("LetsCreateAnErrorMessage");
        driver.findElement(By.cssSelector("button[type='submit']")).click(); // Keep CSS for button (or use XPath if needed)
    
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Wrong email or password.\"]")));
        String errorText = errorElement.getText().trim();
        takeScreenshot(driver, "Invalid Login Error Message");
        assertTrue(errorText.equals("Wrong email or password."), "Invalid login message mismatch");
    
        System.out.println("VALIDATION 01 PASSED");
        appendToLog("\n\t\t- Validation 01: Invalid Login Error Message Returns as Expected \n");
    
        // Clear and enter valid credentials
        WebElement emailField = driver.findElement(By.xpath("//input[@type=\"email\"]"));
        emailField.click();
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        WebElement passwordField = driver.findElement(By.xpath("//input[@type=\"password\"]"));
        passwordField.click();
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    
        driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys("elgin.ethan@gmail.com");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("thisIsMyPassword");
        takeScreenshot(driver, "Corrected Login Info");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='Settings']")));
        Thread.sleep(2000);
        takeScreenshot(driver, "Home Page");
    
        driver.findElement(By.xpath("//button[@aria-label='Settings']")).click();
        Thread.sleep(1000);
        takeScreenshot(driver, "Account Dropdown");
    
        WebElement userElement = driver.findElement(By.xpath("(//div[@role=\"menuitem\"]/span/span)[1]"));
        String loginUserName = userElement.getText().trim();
        appendToLog("\n\tUser: " + loginUserName + "\n");
    
        driver.findElement(By.xpath("//span[text()=\"Log out\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Log in')]")));
        takeScreenshot(driver, "Logout Screen");
    
        String titleText = driver.findElement(By.xpath("//h1[contains(text(), 'Log in')]")).getText();
        assertTrue(titleText.contains("Log in"), "Logout failed");
    
        System.out.println("VALIDATION 02 PASSED");
        appendToLog("\n\t\t- Validation 02: The User Successfully Logged Out \n");
        appendToLog("\n============================== [ End of Automation Execution ] ==============================\n\n");
        System.out.println("FULL SCRIPT EXECUTED SUCCESSFULLY!");
        System.out.println("Check folder: " + filePath);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }

    static void setupFoldersAndLog() throws Exception {
        String today = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
        String time = new SimpleDateFormat("hhmm a").format(new Date());
        filePath = System.getProperty("user.home") + "/KyperianDemo/" + today + "/" + scenario + "/Chrome/" + time;
        new File(filePath).mkdirs();
        logFile = new File(filePath + "/Execution_Log.txt");

        try (FileWriter writer = new FileWriter(logFile)) {
            writer.write("\n============================== [ Automation Execution of " + scenario + " ] ==============================\n");
            writer.write("\n\tScript Purpose: Login and logout. Validations will be made to ensure proper screens display.\n");
        }
    }

    static void takeScreenshot(WebDriver driver, String name) throws Exception {
        ++screenshotCounter;
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(filePath + "/Pic " + screenshotCounter + " - " + name + ".jpg");
        src.renameTo(dest);
    }

    static void appendToLog(String text) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

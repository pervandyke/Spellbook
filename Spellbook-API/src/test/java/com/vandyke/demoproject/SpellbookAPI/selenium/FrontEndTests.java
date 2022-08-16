package com.vandyke.demoproject.SpellbookAPI.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class FrontEndTests {
    
    WebDriver driver;

    String user;
    String password = "TestPass";

    String BASE_URL = "http://localhost:8080/";

    @BeforeClass
    public void init() {
        user = "TestUser" + System.currentTimeMillis();
    }
    
    @BeforeTest
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\per.van.dyke\\SeleniumDrivers\\chromedriver.exe");
        
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn() {
        driver.get(BASE_URL);

        WebElement registerNav = driver.findElement(By.id("registerNav"));

        new Actions(driver)
            .moveToElement(registerNav)
            .click()
            .perform();

        sleep(1000l);
        
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(1000l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(1000l);

        String pageHeader = driver.findElement(By.id("userWelcome")).getText();
        assertEquals(user+"'s Spellbook", pageHeader);
        sleep(1000l);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenExistingUsername_whenRegisterButtonClicked_errorMessageDisplayed() {
        driver.get(BASE_URL);

        WebElement registerNav = driver.findElement(By.id("registerNav"));

        new Actions(driver)
            .moveToElement(registerNav)
            .click()
            .perform();

        sleep(1000l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(1000l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(1000l);

        String error = driver.findElement(By.id("error")).getText();
        assertEquals("User Already Exists", error);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenValidInput_whenLoginButtonClicked_userIsLoggedIn() {
        driver.get(BASE_URL);

        WebElement loginNav = driver.findElement(By.id("loginNav"));

        new Actions(driver)
            .moveToElement(loginNav)
            .click()
            .perform();

        sleep(1000l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(1000l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(1000l);

        String pageHeader = driver.findElement(By.id("userWelcome")).getText();
        assertEquals(user+"'s Spellbook", pageHeader);
        sleep(1000l);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenWrongPassword_whenLoginButtonClicked_errorMessageIsDisplayed() {
        driver.get(BASE_URL);

        WebElement loginNav = driver.findElement(By.id("loginNav"));

        new Actions(driver)
            .moveToElement(loginNav)
            .click()
            .perform();

        sleep(1000l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys("wrongPassword");

        sleep(1000l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(1000l);

        String error = driver.findElement(By.id("error")).getText();
        assertEquals("Password Incorrect", error);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenInvalidUsername_whenLoginButtonClicked_errorMessageIsDisplayed() {
        driver.get(BASE_URL);

        WebElement loginNav = driver.findElement(By.id("loginNav"));

        new Actions(driver)
            .moveToElement(loginNav)
            .click()
            .perform();

        sleep(1000l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys("ImaginaryUser");
        passwordField.sendKeys(password);

        sleep(1000l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(1000l);

        String error = driver.findElement(By.id("error")).getText();
        assertEquals("Username not found", error);
    }

    private void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

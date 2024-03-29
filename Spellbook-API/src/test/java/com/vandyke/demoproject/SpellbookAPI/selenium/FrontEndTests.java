package com.vandyke.demoproject.SpellbookAPI.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    JavascriptExecutor js;

    String user;
    String password = "TestPass";

    String BASE_URL = "http://localhost:8080/";

    @BeforeClass
    public void init() {
        user = "TestUser" + System.currentTimeMillis();
    }
    
    @BeforeTest
    public void setUp() {
        driver = WebDriverManager.chromedriver().create();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void cleanUp() {
        driver.quit();
    }

    /* REGISTER FORM TESTS */

    @Test
    public void givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn() {
        driver.get(BASE_URL);

        WebElement registerNav = driver.findElement(By.id("registerNav"));

        new Actions(driver)
            .moveToElement(registerNav)
            .click()
            .perform();

        sleep(500l);
        
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        String pageHeader = driver.findElement(By.id("userWelcome")).getText();
        assertEquals(user+"'s Spellbook", pageHeader);
        sleep(500l);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenExistingUsername_whenRegisterButtonClicked_errorMessageDisplayed() {
        driver.get(BASE_URL);

        WebElement registerNav = driver.findElement(By.id("registerNav"));

        new Actions(driver)
            .moveToElement(registerNav)
            .click()
            .perform();

        sleep(500l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        String error = driver.findElement(By.id("error")).getText();
        assertEquals("User Already Exists", error);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenNoEnteredPassword_whenRegisterButtonClicked_nothingShouldHappen() {
        driver.get(BASE_URL + "register-page");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        WebElement form = driver.findElement(By.id("registerForm"));
        assertTrue(form.isEnabled());
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenNoEnteredUsername_whenRegisterButtonClicked_nothingShouldHappen() {
        driver.get(BASE_URL + "register-page");

        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        WebElement form = driver.findElement(By.id("registerForm"));
        assertTrue(form.isEnabled());
    }

    /* LOGIN FORM TESTS */

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenValidInput_whenLoginButtonClicked_userIsLoggedIn() {
        driver.get(BASE_URL);

        WebElement loginNav = driver.findElement(By.id("loginNav"));

        new Actions(driver)
            .moveToElement(loginNav)
            .click()
            .perform();

        sleep(500l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        String pageHeader = driver.findElement(By.id("userWelcome")).getText();
        assertEquals(user+"'s Spellbook", pageHeader);
        sleep(500l);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenWrongPassword_whenLoginButtonClicked_errorMessageIsDisplayed() {
        driver.get(BASE_URL);

        WebElement loginNav = driver.findElement(By.id("loginNav"));

        new Actions(driver)
            .moveToElement(loginNav)
            .click()
            .perform();

        sleep(500l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys("wrongPassword");

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

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

        sleep(500l);

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys("ImaginaryUser");
        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        String error = driver.findElement(By.id("error")).getText();
        assertEquals("Username not found", error);
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenNoEnteredPassword_whenLoginButtonClicked_nothingShouldHappen() {
        driver.get(BASE_URL + "login-page");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        WebElement form = driver.findElement(By.id("loginForm"));
        assertTrue(form.isEnabled());
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenNoEnteredUsername_whenLoginButtonClicked_nothingShouldHappen() {
        driver.get(BASE_URL + "login-page");

        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(500l);

        WebElement form = driver.findElement(By.id("loginForm"));
        assertTrue(form.isEnabled());
    }

    /* SPELL FORM TESTS */

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenValidInput_whenAddSpellButtonClicked_spellIsCreated() {
        driver.get(BASE_URL + "login-page");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement levelField = driver.findElement(By.id("level"));
        WebElement timeField = driver.findElement(By.id("time"));
        WebElement rangeField = driver.findElement(By.id("range"));
        WebElement componentField = driver.findElement(By.id("components"));
        WebElement durationField = driver.findElement(By.id("duration"));
        WebElement addSpell = driver.findElement(By.id("addSpell"));

        nameField.sendKeys("Test Spell");
        levelField.sendKeys("0");
        timeField.sendKeys("1 Action");
        rangeField.sendKeys("100ft");
        componentField.sendKeys("V,S");
        durationField.sendKeys("Instantaneous");

        js.executeScript("arguments[0].scrollIntoView();", addSpell);

        sleep(500l);

        new Actions(driver)
            .moveToElement(addSpell)
            .click().perform();

        //Confirm spell is on the page

        WebElement spellName = driver.findElement(By.xpath("//*[text()='Test Spell']"));;
        assertEquals("Test Spell", spellName.getText());

        sleep(500l);
         
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenFullInput_whenAddSpellButtonClicked_spellIsCreated() {
        driver.get(BASE_URL + "login-page");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement levelField = driver.findElement(By.id("level"));
        WebElement damageToggle = driver.findElement(By.id("damageToggle"));
        WebElement timeField = driver.findElement(By.id("time"));
        WebElement rangeField = driver.findElement(By.id("range"));
        WebElement componentField = driver.findElement(By.id("components"));
        WebElement durationField = driver.findElement(By.id("duration"));
        WebElement saveToggle = driver.findElement(By.id("saveToggle"));
        WebElement descriptionField = driver.findElement(By.id("description"));
        WebElement addSpell = driver.findElement(By.id("addSpell"));

        nameField.sendKeys("Full Test Spell");
        levelField.sendKeys("0");
        
        new Actions(driver)
            .moveToElement(damageToggle)
            .click().perform();
        
        WebElement damageTypeField = driver.findElement(By.id("damageType"));
        WebElement damageAmountField = driver.findElement(By.id("damage"));
        damageTypeField.sendKeys("Fire");
        damageAmountField.sendKeys("1d8");

        js.executeScript("arguments[0].scrollIntoView();", addSpell);
        sleep(500l);

        timeField.sendKeys("1 Action");
        rangeField.sendKeys("100ft");
        componentField.sendKeys("V,S");
        durationField.sendKeys("Instantaneous");

        new Actions(driver)
            .moveToElement(saveToggle)
            .click().perform();

        WebElement saveField = driver.findElement(By.id("save"));
        saveField.sendKeys("DEX");
        descriptionField.sendKeys("Test Fire Spell");

        js.executeScript("arguments[0].scrollIntoView();", addSpell);
        sleep(500l);

        new Actions(driver)
            .moveToElement(addSpell)
            .click().perform();

        //Confirm spell is on the page

        WebElement spellName = driver.findElement(By.xpath("//*[text()='Full Test Spell']"));;
        assertEquals("Full Test Spell", spellName.getText());

        sleep(500l);
         
    }

    @Test(dependsOnMethods = {"givenValidInput_whenRegisterButtonClicked_userIsCreatedAndLoggedIn"})
    public void givenEmptySpellInput_addSpellButtonShouldBeDisabed() {
        driver.get(BASE_URL + "login-page");

        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

        sleep(500l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();

        WebElement addSpell = driver.findElement(By.id("addSpell"));

        assertFalse(addSpell.isEnabled());

        sleep(500l);

    }

    private void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

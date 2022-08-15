package com.vandyke.demoproject.SpellbookAPI.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.apache.tomcat.jni.Time;
import org.h2.command.ddl.DropAggregate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class FrontEndTests {
    
    WebDriver driver;

    String BASE_URL = "http://localhost:8080/";

    @BeforeTest
    public void init() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\per.van.dyke\\SeleniumDrivers\\chromedriver.exe");
        
        driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void cleanUp() {
        //driver.quit();
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
        
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("submit"));

        String user = "TestUser" + System.currentTimeMillis();

        username.sendKeys(user);
        password.sendKeys("TestPass");

        sleep(1000l);

        new Actions(driver)
            .moveToElement(submit)
            .click().perform();
        
        sleep(1000l);

        String pageHeader = driver.findElement(By.id("userWelcome")).getText();
        assertEquals(user+"'s Spellbook", pageHeader);
    }

    private void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}

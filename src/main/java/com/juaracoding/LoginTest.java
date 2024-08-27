package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = "https://www.saucedemo.com/";
        driver.get(url);
        System.out.println("Open Browser URL");

        // Scenario Test Valid Login Custom Assert
        System.out.println("Test Username & Password Benar");
        String username = "standard_user";
        String password = "secret_sauce";

        login(driver, username, password);

        String actual = driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        customAssertEquals(actual, expected);

        delay(3);

        // Scenario Test invalid Login Custom Assert 1
        System.out.println("Test Username Benar & Password Salah");
        username = "standard_user";
        password = "wrong_password";

        driver.get(url);
        login(driver, username, password);

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        customAssertEquals(actualErrorMessage, expectedErrorMessage);

        delay(3);

        // Scenario Test invalid Login Custom Assert 2
        System.out.println("Test Username Salah & Password Benar");
        username = "standar";
        password = "secret_sauce";

        driver.get(url);
        login(driver, username, password);

        customAssertEquals(actualErrorMessage, expectedErrorMessage);

        delay(3);

        // Scenario Test invalid Login Custom Assert 3
        System.out.println("Test Username Isi & Password Kosong");
        username = "standard_user";
        password = "";

        driver.get(url);
        login(driver, username, password);

        WebElement errorEmpty1 = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualErrorEmpty1 = errorEmpty1.getText();
        String expectedErrorEmpty1 = "Epic sadface: Password is required";
        customAssertEquals(actualErrorEmpty1, expectedErrorEmpty1);

        delay(3);

        // Scenario Test invalid Login Custom Assert 4
        System.out.println("Test Username Kosong & Password Isi");
        username = "";
        password = "secret_sauce";

        driver.get(url);
        login(driver, username, password);

        WebElement errorEmpty2 = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualErrorEmpty2 = errorEmpty2.getText();
        String expectedErrorEmpty2 = "Epic sadface: Username is required";
        customAssertEquals(actualErrorEmpty2, expectedErrorEmpty2);

        delay(3);

        // Scenario Test invalid Login Custom Assert 5
        System.out.println("Test Username & Password Kosong");
        username = "";
        password = "";

        driver.get(url); // Go back to the login page
        login(driver, username, password);

        // Custom assert for invalid login 2
        WebElement errorEmpty = driver.findElement(By.xpath("//h3[@data-test='error']"));
        String actualErrorEmpty = errorEmpty.getText();
        String expectedErrorEmpty = "Epic sadface: Username is required";
        customAssertEquals(actualErrorEmpty, expectedErrorEmpty);

        delay(3);

        driver.quit();
        System.out.println("Exit Browser");
    }

    public static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    public static void customAssertEquals(String actual, String expected) {
        System.out.println("Test Result: ");
        if(actual.equals(expected)){
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
        }
    }

    public static void delay(long detik) {
        try {
            Thread.sleep(detik * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

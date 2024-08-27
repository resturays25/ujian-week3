import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.juaracoding.LoginTest.delay;

public class AddToCart {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String url = "https://www.saucedemo.com/";
        driver.get(url);
        System.out.println("Open Browser URL");


        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Login
        System.out.println("Test Menambahkan Produk Setelah Login");
        usernameInput.sendKeys("standard_user");
        passwordInput.sendKeys("secret_sauce");
        loginButton.click();

        // Assert Login
        System.out.println("Login");
        if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            System.out.println("Login: Test passed.");
        } else {
            System.out.println("Login: Test failed.");
        }

        // Menambahkan Produk Ke Keranjang
        System.out.println("Menambahkan Produk Ke Keranjang setelah login");
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();

        // Assert Menambahkan Produk Ke Keranjang
        WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
        if (cart.getText().equals("1")) {
            System.out.println("Produk Dimasukkan ke keranjang: Test passed.");
        } else {
            System.out.println("Produk dimasukkan ke keranjang: Test failed.");
        }

        delay(4);

        // Log out
        System.out.println("Logout");
        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
        menuButton.click();
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
        if (driver.getCurrentUrl().equals("https://www.saucedemo.com/")) {
            System.out.println("Logout: Test passed.");
        } else {
            System.out.println("Logout: Test failed.");
        }

        delay(3);
        // Negative Scenario: Menambahkan Produk Tanpa Login
        System.out.println("Menambahkan Produk Ke Keranjang Tanpa login");
        driver.get("https://www.saucedemo.com/inventory.html");

        try {
            WebElement addToCartButtonWithoutLogin = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            addToCartButtonWithoutLogin.click();
            System.out.println("Test failed: Produk ditambahkan pada keranjang.");
        } catch (Exception e) {
            System.out.println("Test passed: Produk tidak dapat ditambahkan pada keranjang.");
        }

        // Close the browser
        driver.quit();
        System.out.println("Exit Browser");
    }
}

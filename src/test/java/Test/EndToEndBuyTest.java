package Test;

import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.time.Duration;
import java.util.Collections;

public class EndToEndBuyTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CheckoutComplete checkoutComplete;
    CheckoutPage checkout;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();

        // --- Matikan Password Manager via Prefs ---
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // --- "Exclude Switches" (Penting!) ---
        // Ini menyembunyikan bar "Chrome is being controlled by automated software"
        // Seringkali pop-up password nempel di fitur automation ini.
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // --- Mode Incognito & Disable Fitur ---
        options.addArguments("--incognito"); // Browser mode penyamaran (Pasti bersih)
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Matikan deteksi password bocor
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-save-password-bubble");

        // --- START BROWSER ---
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void checkOut(){
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkout = new CheckoutPage(driver);
        checkoutComplete = new CheckoutComplete(driver);

        loginPage.loginToApp("standard_user", "secret_sauce");

        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.clickCartIcon();

        checkout.clickCheckout();

        checkout.fillInformation("Ryan", "Satrio", "12345");

        checkout.clickFinish();

        String statusText = checkoutComplete.getTextStatus();
        Assert.assertEquals(statusText,"Checkout: Complete!","Checkout Gagal!");
        System.out.println("Checkout Berhasil");

        checkoutComplete.clickBackHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        System.out.println("Kemabali Ke Halaman Utama");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

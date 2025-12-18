package Test;

import Pages.BurgerMenu;
import Pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LoginTest {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();

        // --- Matikan Password Manager via Prefs ---
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // ---  "Exclude Switches"  ---
        // Ini menyembunyikan bar "Chrome is being controlled by automated software"
        // Seringkali pop-up password nempel di fitur automation ini.
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // ---  Mode Incognito & Disable Fitur ---
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
    public void testLoginUserTerkunci() {

        LoginPage loginPage = new LoginPage(driver);

        // 1. Input user yang dikunci (locked_out_user)
        loginPage.loginToApp("locked_out_user", "secret_sauce");

        // 2. Ambil pesan error yang muncul
        String actualError = loginPage.getErrorText();

        // 3. Validasi: Harusnya error mengandung kata "locked out"
        Assert.assertTrue(actualError.contains("locked out"),
                "Gagal: Pesan error 'Locked Out' tidak muncul!");

        System.out.println("Berhasil mendeteksi error text :" + actualError);
    }

    @Test
    public void testLoginPasswordSalah() {
        LoginPage loginPage = new LoginPage(driver);

        // 1. Input password ngawur
        loginPage.loginToApp("standard_user", "password_salah_123");

        // 2. Validasi error umum
        String actualError = loginPage.getErrorText();
        Assert.assertTrue(actualError.contains("Username and password do not match"),
                "Gagal: Pesan error password salah tidak sesuai!");
        System.out.println("Berhasil mendeteksi password salah: " + actualError);
    }

    @Test
    public void testLoginUsernameKosong() {
        LoginPage loginPage = new LoginPage(driver);

        // 1. Input password ngawur
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLogin();

        // 2. Validasi error umum
        String actualError = loginPage.getErrorText();
        Assert.assertTrue(actualError.contains("Username is required"),
                "Gagal: Pesan error username kosong tidak sesuai!");
        System.out.println("Berhasil mendeteksi username kosong: " + actualError);
    }

    @Test
    public void testLoginPasswordKosong() {
        LoginPage loginPage = new LoginPage(driver);

        // 1. Input password ngawur
        loginPage.inputUsername("standard_user");
        loginPage.clickLogin();

        // 2. Validasi error umum
        String actualError = loginPage.getErrorText();
        Assert.assertTrue(actualError.contains("Password is required"),
                "Gagal: Pesan error password kosong tidak sesuai!");
        System.out.println("Berhasil mendeteksi password kosong: " + actualError);
    }

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        BurgerMenu burgerMenu = new BurgerMenu(driver);

        // 1.Login
        loginPage.loginToApp("standard_user", "secret_sauce");

        // 2.Logout
        burgerMenu.clickBurgerMenu();
        burgerMenu.clickLogut();

        //3. Cek URL
        boolean isLoginBtnVisible = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertTrue(isLoginBtnVisible, "Halaman login harusnya tampil!");
        System.out.println("Logout berhasil!");
    }

    @Test
    public void testLogoutAndBack(){
        LoginPage loginPage = new LoginPage(driver);
        BurgerMenu burgerMenu = new BurgerMenu(driver);

        // 1.Login
        loginPage.loginToApp("standard_user", "secret_sauce");

        // 2.Logout
        burgerMenu.clickBurgerMenu();
        burgerMenu.clickLogut();

        //3. Cek URL
        boolean isLoginBtnVisible = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertTrue(isLoginBtnVisible, "Halaman login harusnya tampil!");
        System.out.println("Logout berhasil!");

        //4. Klik tombol Back
        driver.navigate().back();

        // D. Verifikasi (Assertion)
        // Kita cek URL saat ini. Harusnya tetap di halaman login (index), bukan inventory.
        String currentUrl = driver.getCurrentUrl();

        // Jika URL mengandung kata 'inventory' atau 'dashboard', berarti tes GAGAL (Bug Security)
        Assert.assertFalse(currentUrl.contains("inventory"),
                "BAHAYA: User bisa kembali ke dashboard setelah logout!");

        // ATAU cek apakah tombol login terlihat lagi
        boolean isLoginButtonVisible = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertTrue(isLoginButtonVisible, "Halaman login seharusnya muncul kembali.");

        System.out.println("Test Berhasil");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

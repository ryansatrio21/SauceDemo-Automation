package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    By firstNameInput = By.id("first-name");
    By lastNameInput = By.id("last-name");
    By postalCodeInput = By.id("postal-code");
    By cancelBtn = By.id("cancel");
    By continueBtn = By.id("continue");
    By checkoutBtn = By.id("checkout");
    By errorMassage = By.className("error-massage-container");
    By finishBtn = By.id("finish");
    By successHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void inputFirstName(String firstName){
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode){
        driver.findElement(postalCodeInput).sendKeys(postalCode);
    }

    public String getErrorMassage(){
        return driver.findElement(errorMassage).getText();
    }

    public void clickContinue(){
        driver.findElement(continueBtn).click();
    }

    public void clickCancel(){
        driver.findElement(cancelBtn);
    }

    public void fillInformation(String first, String last, String zip) {
        // 3. TUNGGU DULU: Pastikan kolom input sudah muncul sebelum ngetik
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));

        driver.findElement(firstNameInput).sendKeys(first);
        driver.findElement(lastNameInput).sendKeys(last);
        driver.findElement(postalCodeInput).sendKeys(zip);
        driver.findElement(continueBtn).click();
    }

    public void clickCheckout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        driver.findElement(checkoutBtn).click();
    }
    public void clickFinish() {
        // Tunggu tombol finish bisa diklik
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn));
        driver.findElement(finishBtn).click();
    }

    public String getSuccessMessage() {
        // Tunggu tulisan sukses muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader));
        return driver.findElement(successHeader).getText();
    }

}

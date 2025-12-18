package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BurgerMenu {
    WebDriver driver;
    WebDriverWait wait;

    By burgerMenuBtn = By.className("bm-burger-button");
    By bmMenuWrap = By.id("");
    By logoutBtn = By.id("logout_sidebar_link");
    public BurgerMenu(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickBurgerMenu(){
        driver.findElement(burgerMenuBtn).click();
    }

    public void clickLogut(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtn));

        driver.findElement(logoutBtn).click();
    }
}

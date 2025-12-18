package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutComplete {
    WebDriver driver;

    By backHomeBtn = By.id("back-to-products");
    By statusMassage = By.className("title");

    public CheckoutComplete (WebDriver driver){
        this.driver = driver;
    }

    public void clickBackHome(){
        driver.findElement(backHomeBtn).click();
    }

    public String getTextStatus(){
        return driver.findElement(statusMassage).getText();
    }
}

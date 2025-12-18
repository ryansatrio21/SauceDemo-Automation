package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InventoryPage {
    WebDriver driver;
    WebDriverWait wait;

    By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    By cartButton = By.className("shopping_cart_link");
    By removeBackpack = By.id("remove-sauce-labs-backpak");
    By burgerMenuBtn = By.id("react-burger-menu-btn");
    By logoutLink    = By.id("logout_sidebar_link");
    By aboutLink     = By.id("about_sidebar_link");
    By sortButton  = By.className("product_sort_container");

    public InventoryPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addItemToCart(String namaBarang){
        String xpathDinamis = "//div[text()='" + namaBarang + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpathDinamis)).click();
    }
    public void clickCartIcon(){
        driver.findElement(cartButton).click();
    }

    public void clickBurgerMenu(){
        driver.findElement(burgerMenuBtn).click();
    }

    public void clickLogout(){
        driver.findElement(burgerMenuBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        driver.findElement(logoutLink).click();
    }

    public void clickAbout(){
        driver.findElement(burgerMenuBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(aboutLink));
        driver.findElement(aboutLink).click();
    }

    public void selectSort(String type){
        WebElement dropDownElement = driver.findElement(sortButton);
        Select selectUrutan = new Select(dropDownElement);
        selectUrutan.selectByValue(type);
    }
}

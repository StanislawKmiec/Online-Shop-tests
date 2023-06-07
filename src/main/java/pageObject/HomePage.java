package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Basic {

    private By contactEmailLocator = By.xpath("//ul[@class='pull-left noo-topbar-left']/li[1]/a[1]");
    private By contactNumberLocator = By.xpath("//ul[@class='pull-left noo-topbar-left']/li[2]/a[1]");
    private By cartPriceLocator = By.xpath("//span[@class='cart-item']/span/span/bdi");
    private By cartQuantityLocator = By.xpath("//span[@class='cart-name-and-total']");


    public HomePage(WebDriver browserDriver) {
        super(browserDriver);
    }

    public String[] getContactInfo() {
        WebElement getContactEmail = browser.findElement(contactEmailLocator);
        WebElement getContactNumber = browser.findElement(contactNumberLocator);
        String emailOnPage = getContactEmail.getText();
        String phoneOnPage = getContactNumber.getText();
        return new String[]{emailOnPage, phoneOnPage};
    }

    public String checkCartPrice() {
        WebElement getCartAmount = browser.findElement(cartPriceLocator);
        return getCartAmount.getText().substring(1);
    }

    public String checkCartAmount() {
        WebElement getCartQuantity = browser.findElement(cartQuantityLocator);
        return getCartQuantity.getText().substring(5, 6);
    }
}

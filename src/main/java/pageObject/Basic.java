package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Basic {

    public WebDriver browser;


    private By dismissButton = By.xpath("//a[@class='woocommerce-store-notice__dismiss-link']");
    private By homeLinkButton = By.xpath("//a[@class='custom-logo-link']");
    private By wishListButton = By.xpath("//ul[@class='pull-right noo-topbar-right']/li[1]/a");
    private By myAccountButton = By.xpath("//ul[@class='pull-right noo-topbar-right']/li[2]/a");
    private By checkoutButton = By.xpath("//ul[@class='pull-right noo-topbar-right']/li[3]/a");
    private By searchButton = By.xpath("//a[@class='noo-search']");
    private By searchField = By.xpath("//input[@type='search']");
    private By scrollUpButton = By.xpath("//a[@class='go-to-top hidden-print on']");
    private By previewEnterButton = By.xpath("//span[@class='noo-quick-view icon_zoom-in_alt']");
    private By previewExitButton = By.xpath("//button[@class='quickview-close']");
    public By previewModal = By.xpath("//div[@class='quick-content woocommerce eff']");

    public Basic(WebDriver browserDriver) {
        browser = browserDriver;
    }

    public HomePage dismissBanner() {
        WebElement dismissButtonClick = browser.findElement(dismissButton);
        dismissButtonClick.click();
        return new HomePage(browser);
    }

    public HomePage navigateToHomePage() {
        WebElement homeLink = browser.findElement(homeLinkButton);
        homeLink.click();
        return new HomePage(browser);
    }

    public MyAccountPage navigateToMyAccountPage() {
        WebElement myAccountLink = browser.findElement(myAccountButton);
        myAccountLink.click();
        return new MyAccountPage(browser);
    }

    public CheckoutPage navigateToCheckoutPage() {
        WebElement checkoutLink = browser.findElement(checkoutButton);
        checkoutLink.click();
        return new CheckoutPage(browser);
    }

    public MyWishListPage navigateToMyWishlistPage() {
        WebElement myWishListLink = browser.findElement(wishListButton);
        myWishListLink.click();
        return new MyWishListPage(browser);
    }

    public ProductListingPage searchFieldTypeIn(String searchInput) {
        WebElement searchFieldClick = browser.findElement(searchButton);
        WebElement searchFieldType = browser.findElement(searchField);
        searchFieldClick.click();
        searchFieldType.clear();
        searchFieldType.sendKeys(searchInput);
        searchFieldType.sendKeys(Keys.RETURN);
        return new ProductListingPage(browser);
    }

    public HomePage useScrollUpPage() {
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement scrollUpClick = browser.findElement(scrollUpButton);
        scrollUpClick.click();
        WebDriverWait explicit = new WebDriverWait(browser, 3);
        explicit.until(ExpectedConditions.invisibilityOf(scrollUpClick));
        return new HomePage(browser);
    }

    public HomePage openPreview() {
        WebElement previewOpenClick = browser.findElement(previewEnterButton);
        previewOpenClick.click();
        return new HomePage(browser);
    }

    public HomePage closePreview() {
        WebElement previewCloseClick = browser.findElement(previewExitButton);
        previewCloseClick.click();
        return new HomePage(browser);
    }

}

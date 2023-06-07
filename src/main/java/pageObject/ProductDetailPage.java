package pageObject;

import dataProvider.ColorsValues;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.ExplicitWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailPage extends Basic {

    public ProductDetailPage(WebDriver browserDriver) {
        super(browserDriver);
    }

    private By colorSelectLocator = By.id("pa_color");
    private By sizeSelectLocator = By.id("pa_size");
    public By addToCartButtonNotConfiguredLocator = By.xpath("//button[@class='single_add_to_cart_button button alt wp-element-button disabled wc-variation-selection-needed']");
    public By addToCartButtonConfiguredLocator = By.xpath("//button[@class='single_add_to_cart_button button alt wp-element-button']");
    private By viewCartButtonLocator = By.xpath("//a[@class='button wc-forward wp-element-button']");
    private By alertInfoLocator = By.xpath("//div[@class='woocommerce-message']");
    private By productTitleLocator = By.xpath("//div[@class='summary entry-summary']/h1");
    private By clearProductVariationLocator = By.xpath("//a[@class='reset_variations']");


    public ProductDetailPage configureProduct(ColorsValues colors, Object sizes) {
        WebElement colorSelect = browser.findElement(colorSelectLocator);
        WebElement sizeSelect = browser.findElement(sizeSelectLocator);
        colorSelect.click();
        Select colorDropDown = new Select(colorSelect);
        colorDropDown.selectByValue(String.valueOf(colors));
        sizeSelect.click();
        Select sizeDropDown = new Select(sizeSelect);
        sizeDropDown.selectByValue(String.valueOf(sizes));
        return new ProductDetailPage(browser);
    }

    public ProductDetailPage addToCart(By addToCartType) {
        WebElement addToCart = browser.findElement(addToCartType);
        ExplicitWait wait = new ExplicitWait(browser, 3);
        wait.waitForVisibilityOfElementLocated(addToCart);
        addToCart.click();
        return new ProductDetailPage(browser);
    }

    public Object getInfoAfterProductAdded() {
        WebElement viewCartButton = browser.findElement(viewCartButtonLocator);
        WebElement alertInfo = browser.findElement(alertInfoLocator);
        WebElement productTitle = browser.findElement(productTitleLocator);
        List<Object> itemsList = new ArrayList<Object>();
        itemsList.add(viewCartButton);
        itemsList.add(alertInfo.getText().substring(9));
        itemsList.add(productTitle.getText().toLowerCase());
        return itemsList;
    }

    public ProductDetailPage clickClearProductVariation() {
        WebElement clearProductVariationButton = browser.findElement(clearProductVariationLocator);
        clearProductVariationButton.click();
        return new ProductDetailPage(browser);
    }

    public boolean isClearProductVariationVisible() {
        WebElement clearProductVariationButton = browser.findElement(clearProductVariationLocator);
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(clearProductVariationLocator));
        boolean clearProductVariationButtonVisibility = clearProductVariationButton.isDisplayed();
        return clearProductVariationButtonVisibility;
    }

    public String[] verifyProductConfiguration() {
        WebElement colorSelect = browser.findElement(colorSelectLocator);
        WebElement sizeSelect = browser.findElement(sizeSelectLocator);
        Select colorDropDown = new Select(colorSelect);
        String emptyColorSelect = colorDropDown.getFirstSelectedOption().getText();
        Select sizeDropDown = new Select(sizeSelect);
        String emptySizeSelect = sizeDropDown.getFirstSelectedOption().getText();
        String[] emptyColorAndSizeSelect = new String[2];
        emptyColorAndSizeSelect[0] = emptyColorSelect;
        emptyColorAndSizeSelect[1] = emptySizeSelect;
        return emptyColorAndSizeSelect;
    }

    public CartPage clickViewCartButton() {
        WebElement viewCartButton = browser.findElement(viewCartButtonLocator);
        viewCartButton.click();
        return new CartPage(browser);
    }

}

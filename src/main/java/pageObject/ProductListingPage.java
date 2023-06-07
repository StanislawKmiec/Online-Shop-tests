package pageObject;

import dataProvider.Products;
import dataProvider.PropertiesFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import waits.ExplicitWait;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListingPage extends Basic {

    private By productCategoryLocator = By.xpath("//span/a");
    private By productTitleLocator = By.xpath("//h3/a");
    private By noProductsInfoLocator = By.xpath("//p[@class='woocommerce-info woocommerce-no-products-found']");


    PropertiesFileReader propertiesFileReader;

    public ProductListingPage(WebDriver browserDriver) {
        super(browserDriver);
    }

    public List<String> getSearchResultsProductCategory() {
        List<WebElement> productsCategories = browser.findElements(productCategoryLocator);
        List<String> productCategoriesList = productsCategories.stream().map(productCategory -> productCategory.getText()).collect(Collectors.toList());
        return productCategoriesList;
    }

    public List<String> getSearchResultsProductTitle() {
        List<WebElement> productsTitles = browser.findElements(productTitleLocator);
        List<String> productTitlesList = productsTitles.stream().map(productTitle -> productTitle.getText()).collect(Collectors.toList());
        return productTitlesList;
    }

    public String returnNoResultsInfo() {
        WebElement noResults = browser.findElement(noProductsInfoLocator);
        return noResults.getText();
    }

    public Boolean compareSearchResultsToSearchTerm(List<String> list) throws IOException {
        propertiesFileReader = new PropertiesFileReader();
        Object[] categoryArray = this.getSearchResultsProductCategory().toArray();
        boolean positive = false;
        for (int i = 0; i < categoryArray.length; i++) {
            if (categoryArray[i].toString().toLowerCase().contains(Products.SHIRT.toString())) ;
            {
                positive = true;
            }
        }
        return positive;
    }

    public ProductDetailPage selectProduct() {
        List<WebElement> productsTitles = browser.findElements(productTitleLocator);
        ExplicitWait wait = new ExplicitWait(browser, 3);
        wait.waitForVisibilityOfElementLocated(productsTitles.get(0));
        productsTitles.get(0).click();
        return new ProductDetailPage(browser);
    }
}

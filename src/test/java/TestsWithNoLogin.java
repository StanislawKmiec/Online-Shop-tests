import dataProvider.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.*;
import waits.ExplicitWait;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestsWithNoLogin {

    private WebDriver browser;
    private PropertiesFileReader propertiesFileReader;
    UUID randomUUID = UUID.randomUUID();

    @BeforeMethod(alwaysRun = true)
    public void initialization() throws IOException {
        propertiesFileReader = new PropertiesFileReader();
        System.setProperty("webdriver.chrome.driver", propertiesFileReader.getDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        browser = new ChromeDriver(options);
        browser.get(propertiesFileReader.getSiteUrl());
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(propertiesFileReader.getImplicitTime(), TimeUnit.SECONDS);
    }


    @AfterMethod(alwaysRun = true)
    public void finish() {
        browser.close();
        browser.quit();
    }

    @Test
    @Description("Comparing page title with correct one")
    @Severity(SeverityLevel.NORMAL)
    public void verifyHomePageTitle() {
        browser.get(propertiesFileReader.getSiteUrl());
        Assert.assertEquals(browser.getTitle(), propertiesFileReader.getPageTitle());
    }

    @Test
    @Description("Comparing contact info with correct one")
    @Severity(SeverityLevel.NORMAL)
    public void verifyContactInfo() {
        HomePage homePage = new HomePage(browser);
        homePage.dismissBanner();
        List<String> valuesFromPage = Arrays.stream(homePage.getContactInfo()).collect(Collectors.toList());
        Assert.assertEquals(valuesFromPage.get(0), propertiesFileReader.getContactEmail());
        Assert.assertEquals(valuesFromPage.get(1), propertiesFileReader.getContactNumber());
    }

    @Test
    @Description("Check if scroll up button transfers user to the top of the page")
    @Severity(SeverityLevel.MINOR)
    public void checkScrollUpButton() throws InterruptedException {
        HomePage homePage = new HomePage(browser);
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        homePage.useScrollUpPage();
        Long heightAfterScrollUp = (Long) executor.executeScript("return window.pageYOffset;");
        Assert.assertEquals(heightAfterScrollUp, 0);
    }

    @Test(groups = {"Regression"})
    @Description("Verify if empty mini card is empty and has 0 price in it")
    @Severity(SeverityLevel.CRITICAL)
    public void checkEmptyMiniCardAmountAndPrice() {
        HomePage homePage = new HomePage(browser);
        Assert.assertEquals(homePage.checkCartPrice(), "0.00");
        Assert.assertEquals(homePage.checkCartAmount(), "0");
    }

    @Test(groups = {"Regression"})
    @Description("Checking basic search for shirts ")
    @Severity(SeverityLevel.BLOCKER)
    public void searchWithResults() {
        HomePage homePage = new HomePage(browser);
        homePage.searchFieldTypeIn(Products.SHIRT.toString());
        ProductListingPage productListingPage = new ProductListingPage(browser);
        Assert.assertFalse(productListingPage.getSearchResultsProductCategory().isEmpty(), "No search results something is wrong");
    }

    @Test(groups = {"Regression"})
    @Description("Check if search with no results returns correct message ")
    @Severity(SeverityLevel.CRITICAL)
    public void searchWithNoResults() {
        HomePage homePage = new HomePage(browser);
        Assert.assertEquals(homePage.searchFieldTypeIn(randomUUID.toString()).returnNoResultsInfo(), propertiesFileReader.getNoResultsInfo());
    }

    @Test(groups = {"Regression"})
    @Description("Check accuracy of the search results")
    @Severity(SeverityLevel.NORMAL)
    public void verifySearchResults() throws IOException {
        HomePage homePage = new HomePage(browser);
        homePage.searchFieldTypeIn(Products.SHIRT.toString());
        ProductListingPage productListingPage = new ProductListingPage(browser);
        Boolean value = productListingPage.compareSearchResultsToSearchTerm(productListingPage.getSearchResultsProductCategory());
        Assert.assertEquals(value, true);
    }

    @Test(groups = {"Regression"})
    @Description("Displaying product preview just by clicking it")
    @Severity(SeverityLevel.NORMAL)
    public void displayProductAndClosePreview() {
        HomePage homePage = new HomePage(browser);
        homePage.openPreview();
        Basic basic = new Basic(browser);
        WebElement displayedPreview = browser.findElement(basic.previewModal);
        ExplicitWait wait = new ExplicitWait(browser, 5);
        wait.waitForVisibilityOfElementLocated(displayedPreview);
        Assert.assertTrue(displayedPreview.isDisplayed(), "Product preview isn't open");
        homePage.closePreview();
        List<WebElement> displayedPreview2 = browser.findElements(basic.previewModal);
        Assert.assertEquals(displayedPreview2.size(), 0);
    }

    @Test(groups = {"Regression"})
    @Description("Checking mini cart after adding one product to it")
    @Severity(SeverityLevel.BLOCKER)
    public void addProductToCartAndVerifyAmountMiniCart() {
        HomePage homePage = new HomePage(browser);
        ProductDetailPage productDetailPage = new ProductDetailPage(browser);
        SizeValues sizes = SizeValues.valueOf("THREESIX");
        homePage.searchFieldTypeIn(Products.SHIRT.toString()).selectProduct()
                .configureProduct(ColorsValues.pink, sizes.getSize()).addToCart(productDetailPage.addToCartButtonConfiguredLocator);
        Object result = productDetailPage.getInfoAfterProductAdded();
        List<Object> itemsList = (List<Object>) result;
        String alertInfo = (String) itemsList.get(1);
        String productTitle = (String) itemsList.get(2);
        WebElement viewCartButton = (WebElement) itemsList.get(0);
        Assert.assertTrue(viewCartButton.isDisplayed(), "View cart button isn't visible");
        Assert.assertTrue(alertInfo.contains(productTitle), "Something is wrong");
        Assert.assertEquals(homePage.checkCartAmount(), "1");
    }

    @Test(groups = {"Regression"})
    @Description("Check clear product configuration button on product page")
    @Severity(SeverityLevel.MINOR)
    public void verifyClearProductVariation() {
        HomePage homePage = new HomePage(browser);
        ProductDetailPage productDetailPage = new ProductDetailPage(browser);
        SizeValues sizes = SizeValues.valueOf("SMALL");
        homePage.searchFieldTypeIn(Products.DRESS.getName()).selectProduct()
                .configureProduct(ColorsValues.white, sizes.getSize())
                .clickClearProductVariation();
        Assert.assertFalse(productDetailPage.isClearProductVariationVisible(), "Clear button wasn't clicked");
        Assert.assertEquals(Arrays.stream(productDetailPage.verifyProductConfiguration()).toList().get(0), propertiesFileReader.getChooseAnOption());
        Assert.assertEquals(Arrays.stream(productDetailPage.verifyProductConfiguration()).toList().get(1), propertiesFileReader.getChooseAnOption());
    }

    @Test(groups = {"Regression"})
    @Description("Check if after adding not configured product correct message is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void addNotConfiguredProductToCart() {
        HomePage homePage = new HomePage(browser);
        ProductDetailPage productDetailPage = new ProductDetailPage(browser);
        homePage.searchFieldTypeIn(Products.DRESS.getName()).selectProduct()
                .addToCart(productDetailPage.addToCartButtonNotConfiguredLocator);
        boolean presenceOfAlert;
        Alert alert = browser.switchTo().alert();
        ExplicitWait wait = new ExplicitWait(browser, 3);
        wait.waitForAlertToBePresent();
        Assert.assertEquals(alert.getText(), propertiesFileReader.getProductConfigMessage());
        alert.accept();
        try {
            alert.getText();
            presenceOfAlert = true;
        } catch (NoAlertPresentException e) {
            presenceOfAlert = false;
        }
        Assert.assertFalse(presenceOfAlert, "Alert is still visible");
    }

    @Test(groups = {"Login"})
    @Description("Login with no data in the inputs")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithEmptyData() {
        HomePage homePage = new HomePage(browser);
        String errorMessageEmpty = homePage.dismissBanner().navigateToMyAccountPage().clickLoginButton()
                .getLoginErrorMessage();
        Assert.assertEquals(errorMessageEmpty, propertiesFileReader.getEmptyErrorLoginMessage());
    }

    @Test(groups = {"Login"})
    @Description("Login with invalid data")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithInvalidCredentials() {
        HomePage homePage = new HomePage(browser);
        String errorMessageWrong = homePage.dismissBanner().navigateToMyAccountPage().enterUserName(randomUUID.toString()).enterPassword(randomUUID.toString())
                .clickLoginButton().getLoginErrorMessage();
        Assert.assertEquals(errorMessageWrong, propertiesFileReader.getWrongCredentialsErrorLoginMessage());
    }

    @Test(groups = {"Login"})
    @Description("Correct login using username - this one will always file due to bug on the page")
    @Severity(SeverityLevel.CRITICAL)
    public void correctLoginWithUserName() {
        HomePage homePage = new HomePage(browser);
        String name = homePage.dismissBanner().navigateToMyAccountPage().enterUserName(propertiesFileReader.getCorrectLoginName())
                .enterPassword(propertiesFileReader.getCorrectPassword()).clickLoginButton().getUserNameAfterLogin();
        Assert.assertEquals(name, propertiesFileReader.getCorrectLoginName());
    }

    @Test(groups = {"Login"})
    @Description("Correct login using email ")
    @Severity(SeverityLevel.CRITICAL)
    public void correctLoginWithEmail() {
        HomePage homePage = new HomePage(browser);
        String name = homePage.dismissBanner().navigateToMyAccountPage().enterUserName(propertiesFileReader.getCorrectLoginEmail()).
                enterPassword(propertiesFileReader.getCorrectPassword()).clickLoginButton().getUserNameAfterLogin();
        Assert.assertEquals(name, propertiesFileReader.getCorrectLoginName());
    }

    @Test(groups = {"Login"})
    @Description("Trying to reset password with empty email field")
    @Severity(SeverityLevel.CRITICAL)
    public void resetPasswordWithEmpty() {
        HomePage homePage = new HomePage(browser);
        String errorMessage = homePage.dismissBanner().navigateToMyAccountPage().clickLostPassword().clickResetPassword().getResetPasswordErrorMessage();
        Assert.assertEquals(errorMessage, propertiesFileReader.getEmptyResetPasswordErrorMessage());
    }

    @Test
    public void addProductToWishList() {

    }

}

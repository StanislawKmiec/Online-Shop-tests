import dataProvider.AccountDetailReader;
import dataProvider.AccountDetailsForm;
import dataProvider.BillingAddressForm;
import dataProvider.PropertiesFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.HomePage;
import pageObject.MyAccountPage;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TestsWithLoginRequired {

    private WebDriver browser;
    private PropertiesFileReader propertiesFileReader;
    private AccountDetailReader accountDetailReader;

    @BeforeMethod(alwaysRun = true)
    public void initialization() throws IOException {
        propertiesFileReader = new PropertiesFileReader();
        accountDetailReader = new AccountDetailReader();
        System.setProperty("webdriver.chrome.driver", propertiesFileReader.getDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        browser = new ChromeDriver(options);
        browser.get(propertiesFileReader.getSiteUrl());
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(propertiesFileReader.getImplicitTime(), TimeUnit.SECONDS);
        HomePage homePage = new HomePage(browser);
        homePage.dismissBanner().navigateToMyAccountPage().enterUserName(propertiesFileReader.getCorrectLoginEmail())
                .enterPassword(propertiesFileReader.getCorrectPassword()).clickLoginButton();
    }


    @AfterMethod(alwaysRun = true)
    public void finish() {
        browser.quit();
    }

    @Test(priority = 1, groups = {"Logged"})
    public void logout() throws IOException {
        MyAccountPage myAccountPage = new MyAccountPage(browser);
        myAccountPage.selectFromAccountMenu("Logout");
        Assert.assertTrue(browser.findElement(myAccountPage.userNameLocator).isDisplayed(), "User isn't logged out");
        Assert.assertTrue(browser.findElement(myAccountPage.passwordLocator).isDisplayed(), "User isn't logged out");

    }

    @Test(priority = 2, groups = {"Logged"})
    public void editUserDetails() {
        MyAccountPage myAccountPage = new MyAccountPage(browser);
        AccountDetailsForm accountDetails = new AccountDetailsForm(AccountDetailReader.getProperty("firstName"), AccountDetailReader.getProperty("lastName"),
                AccountDetailReader.getProperty("displayName"), AccountDetailReader.getProperty("emailAddress"));
        myAccountPage.selectFromAccountMenu("Account details").fillInAccountDetails(accountDetails);
        myAccountPage.getAllAccountDetails();
        SoftAssert softAssert = new SoftAssert();
        WebElement alertMessage = browser.findElement(myAccountPage.alertMessageLocator);
        Assert.assertEquals(alertMessage.getText(), propertiesFileReader.getAccountDetailsSuccessfully());
        softAssert.assertEquals(myAccountPage.getAllAccountDetails().get(0), AccountDetailReader.getProperty("firstName"));
        softAssert.assertEquals(myAccountPage.getAllAccountDetails().get(1), AccountDetailReader.getProperty("lastName"));
        softAssert.assertEquals(myAccountPage.getAllAccountDetails().get(2), AccountDetailReader.getProperty("displayName"));
        softAssert.assertEquals(myAccountPage.getAllAccountDetails().get(3), AccountDetailReader.getProperty("emailAddress"));
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"Logged"})
    public void editBillingAddress() {
        MyAccountPage myAccountPage = new MyAccountPage(browser);
        BillingAddressForm billingAddressForm = new BillingAddressForm("Antio", "Bantio", "Ifirmius", "Palkowska 23",
                "Fabryczna", "98-954", "Warszawa", "789456123", "test@test.com");
        myAccountPage.selectFromAccountMenu("Addresses").fillInBillingAddress(billingAddressForm);
        WebElement alertMessage = browser.findElement(myAccountPage.alertMessageLocator);
        Assert.assertEquals(alertMessage.getText(), propertiesFileReader.getAddressDetailsSuccessfully());
    }

}

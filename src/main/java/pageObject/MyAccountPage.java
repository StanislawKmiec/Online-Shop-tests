package pageObject;

import dataProvider.AccountDetailsForm;
import dataProvider.BillingAddressForm;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class MyAccountPage extends Basic {

    public By userNameLocator = By.xpath("//input[@id='username']");
    public By passwordLocator = By.xpath("//input[@id='password']");
    private By loginButtonLocator = By.xpath("//button[@name='login']");
    private By loginErrorMessageLocator = By.xpath("//ul[@class='woocommerce-error']/li");
    private By loggedInNameLocator = By.xpath("//p/strong[1]");
    private By lostPasswordLocator = By.xpath("//p[@class='woocommerce-LostPassword lost_password']/a");
    private By resetPasswordLocator = By.xpath("//button[@class='woocommerce-Button button wp-element-button']");
    private By resetPasswordErrorMessageLocator = By.xpath("//div[@class='woocommerce-notices-wrapper']/ul/li");
    private By accountMenuOptionsLocator = By.xpath("//nav[@class='woocommerce-MyAccount-navigation']/ul/li/a");
    private By accountFirstNameLocator = By.id("account_first_name");
    private By accountLastNameLocator = By.id("account_last_name");
    private By accountDisplayNameLocator = By.id("account_display_name");
    private By accountEmailLocator = By.id("account_email");
    private By saveChangesButtonLocator = By.xpath("//p/button[@type='submit']");
    public By alertMessageLocator = By.xpath("//div[@class='woocommerce-message']");
    private By billingAddressEditLocator = By.xpath("//h3[contains(text(), 'Billing address')]//following-sibling::a");
    private By billingAddressFirstNameLocator = By.id("billing_first_name");
    private By billingAddressLastNameLocator = By.id("billing_last_name");
    private By billingCompanyNameLocator = By.id("billing_company");
    private By billingStreetAddressLocator = By.id("billing_address_1");
    private By billingStreetAddress2Locator = By.id("billing_address_2");
    private By billingPostCodeLocator = By.id("billing_postcode");
    private By billingCityLocator = By.id("billing_city");
    private By billingPhoneLocator = By.id("billing_phone");
    private By billingEmailAddressLocator = By.id("billing_email");
    private By allInputsBillingAddressLocator = By.xpath("//span/input");


    public MyAccountPage(WebDriver browserDriver) {
        super(browserDriver);
    }

    public MyAccountPage enterUserName(String userName) {
        WebElement userNameField = browser.findElement(userNameLocator);
        userNameField.click();
        userNameField.sendKeys(userName);
        return new MyAccountPage(browser);
    }

    public MyAccountPage enterPassword(String password) {
        WebElement passwordField = browser.findElement(passwordLocator);
        passwordField.click();
        passwordField.sendKeys(password);
        return new MyAccountPage(browser);
    }

    public MyAccountPage clickLoginButton() {
        WebElement loginButton = browser.findElement(loginButtonLocator);
        loginButton.click();
        return new MyAccountPage(browser);
    }

    public String getLoginErrorMessage() {
        WebElement loginErrorMessage = browser.findElement(loginErrorMessageLocator);
        return loginErrorMessage.getText();
    }

    public String getUserNameAfterLogin() {
        WebElement loggedInName = browser.findElement(loggedInNameLocator);
        return loggedInName.getText();
    }

    public MyAccountPage clickLostPassword() {
        WebElement lostPassword = browser.findElement(lostPasswordLocator);
        lostPassword.click();
        return new MyAccountPage(browser);
    }

    public MyAccountPage clickResetPassword() {
        WebElement resetPasswordButton = browser.findElement(resetPasswordLocator);
        resetPasswordButton.click();
        return new MyAccountPage(browser);
    }

    public String getResetPasswordErrorMessage() {
        WebElement resetPasswordErrorMessage = browser.findElement(resetPasswordErrorMessageLocator);
        return resetPasswordErrorMessage.getText();
    }

    public MyAccountPage selectFromAccountMenu(String optionName) {
        List<WebElement> accountMenuOptions = browser.findElements(accountMenuOptionsLocator);

        for (int i = 0; i < accountMenuOptions.size(); i++) {
            accountMenuOptions = browser.findElements(accountMenuOptionsLocator);
            if (accountMenuOptions.get(i).getText().equals(optionName)) {
                ;
                accountMenuOptions.get(i).click();
            }
        }
        return new MyAccountPage(browser);
    }

    public MyAccountPage fillInAccountDetails(AccountDetailsForm accountDetails) {
        WebElement firstName = browser.findElement(accountFirstNameLocator);
        WebElement lastName = browser.findElement(accountLastNameLocator);
        WebElement displayName = browser.findElement(accountDisplayNameLocator);
        WebElement email = browser.findElement(accountEmailLocator);
        WebElement saveChangesButton = browser.findElement(saveChangesButtonLocator);
        firstName.clear();
        firstName.sendKeys(accountDetails.getFirstName());
        lastName.clear();
        lastName.sendKeys(accountDetails.getLastName());
        displayName.clear();
        displayName.sendKeys(accountDetails.getDisplayName());
        email.clear();
        email.sendKeys(accountDetails.getEmailAddress());
        saveChangesButton.click();

        return new MyAccountPage(browser);
    }

    public List<String> getAllAccountDetails() {
        WebElement firstName = browser.findElement(accountFirstNameLocator);
        WebElement lastName = browser.findElement(accountLastNameLocator);
        WebElement displayName = browser.findElement(accountDisplayNameLocator);
        WebElement email = browser.findElement(accountEmailLocator);
        List<String> userData = new ArrayList<>();
        userData.add(firstName.getAttribute("value"));
        userData.add(lastName.getAttribute("value"));
        userData.add(displayName.getAttribute("value"));
        userData.add(email.getAttribute("value"));

        return userData;
    }

    public MyAccountPage fillInBillingAddress(BillingAddressForm billingAddressForm) {
        WebElement billingAddressEdit = browser.findElement(billingAddressEditLocator);
        billingAddressEdit.click();
        List<WebElement> allInputs = browser.findElements(allInputsBillingAddressLocator);
        WebElement billingAddressFirstName = browser.findElement(billingAddressFirstNameLocator);
        WebElement billingAddressLastName = browser.findElement(billingAddressLastNameLocator);
        WebElement billingCompanyName = browser.findElement(billingCompanyNameLocator);
        WebElement billingStreetAddress = browser.findElement(billingStreetAddressLocator);
        WebElement billingStreetAddress2 = browser.findElement(billingStreetAddress2Locator);
        WebElement billingPostCode = browser.findElement(billingPostCodeLocator);
        WebElement billingCity = browser.findElement(billingCityLocator);
        WebElement billingPhone = browser.findElement(billingPhoneLocator);
        WebElement billingEmailAddress = browser.findElement(billingEmailAddressLocator);
        WebElement saveChangesButton = browser.findElement(saveChangesButtonLocator);
        for (int i = 0; i < allInputs.size(); i++) {
            if (allInputs.get(i).isDisplayed()) {
                allInputs.get(i).sendKeys(Keys.chord(Keys.CONTROL, "a"));
                allInputs.get(i).sendKeys(Keys.DELETE);
            }
        }
        billingAddressFirstName.sendKeys(billingAddressForm.getFirstName());
        billingAddressLastName.sendKeys(billingAddressForm.getLastName());
        billingCompanyName.sendKeys(billingAddressForm.getCompanyName());
        billingStreetAddress.sendKeys(billingAddressForm.getStreetAddress());
        billingStreetAddress2.sendKeys(billingAddressForm.getStreetAddress2());
        billingPostCode.sendKeys(billingAddressForm.getPostCode());
        billingCity.sendKeys(billingAddressForm.getCity());
        billingPhone.clear();
        billingPhone.sendKeys(billingAddressForm.getPhoneNumber());
        billingEmailAddress.clear();
        billingEmailAddress.sendKeys(billingAddressForm.getEmailAddress());
        saveChangesButton.click();

        return new MyAccountPage(browser);
    }

}

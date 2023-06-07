package dataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

    private Properties configProperties;
    private Properties messagesProperties;

    private final String configFilePath = "C:\\Users\\User\\Desktop\\Sellenium\\ToolsQA_project\\src\\main\\java\\resources\\config.properties";
    private final String messagesFilePath = "C:\\Users\\User\\Desktop\\Sellenium\\ToolsQA_project\\src\\main\\java\\resources\\messages.properties";

    public PropertiesFileReader() throws IOException {
        BufferedReader reader;
        BufferedReader reader2;
        reader = new BufferedReader(new FileReader(configFilePath));
        reader2 = new BufferedReader(new FileReader(messagesFilePath));
        configProperties = new Properties();
        messagesProperties = new Properties();
        configProperties.load(reader);
        messagesProperties.load(reader2);
    }

    public String getDriverPath() {
        String driverPath = configProperties.getProperty("driverPath");
        return driverPath;
    }

    public String getSiteUrl() {
        String url = configProperties.getProperty("url");
        return url;
    }

    public int getImplicitTime() {
        String implicitTime = configProperties.getProperty("implicitWait");
        return Integer.parseInt(implicitTime);
    }

    public String getPageTitle() {
        String title = configProperties.getProperty("pageTitle");
        return title;
    }

    public String getContactEmail() {
        String emailAddress = configProperties.getProperty("contactEmail");
        return emailAddress;
    }

    public String getContactNumber() {
        String phoneNumber = configProperties.getProperty("contactNumber");
        return phoneNumber;
    }

    public String getNoResultsInfo() {
        String noResultsMessage = messagesProperties.getProperty("noProductsMessage");
        return noResultsMessage;
    }

    public String getProductConfigMessage() {
        String productConfigMessage = messagesProperties.getProperty("productConfigMessage");
        return productConfigMessage;
    }

    public String getEmptyErrorLoginMessage() {
        String emptyErrorLoginMessage = messagesProperties.getProperty("emptyLoginErrorMessage");
        return emptyErrorLoginMessage;

    }

    public String getWrongCredentialsErrorLoginMessage() {
        String wrongCredentialsErrorLoginMessage = messagesProperties.getProperty("wrongCredentialsErrorMessage");
        return wrongCredentialsErrorLoginMessage;
    }

    public String getCorrectLoginName() {
        String correctLoginName = configProperties.getProperty("userName");
        return correctLoginName;
    }

    public String getCorrectLoginEmail() {
        String correctLoginEmail = configProperties.getProperty("email");
        return correctLoginEmail;
    }

    public String getCorrectPassword() {
        String correctPassword = configProperties.getProperty("password");
        return correctPassword;
    }

    public String getChooseAnOption() {
        String chooseAnOption = messagesProperties.getProperty("chooseAnOption");
        return chooseAnOption;
    }

    public String getEmptyResetPasswordErrorMessage() {
        String emptyResetPasswordErrorMessage = messagesProperties.getProperty("emptyResetPasswordErrorMessage");
        return emptyResetPasswordErrorMessage;
    }

    public String getAccountDetailsSuccessfully() {
        String accountDetailsSuccessfully = messagesProperties.getProperty("accountDetailsSuccessfully");
        return accountDetailsSuccessfully;
    }

    public String getAddressDetailsSuccessfully() {
        String addressDetailsSuccessfully = messagesProperties.getProperty("addressDetailsSuccessfully");
        return addressDetailsSuccessfully;
    }
}



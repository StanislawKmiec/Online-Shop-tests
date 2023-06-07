package dataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AccountDetailReader {
    private static Properties accountDetails;

    static {
        accountDetails = new Properties();
        try {
            final String userDetailsFilePath = "C:\\Users\\User\\Desktop\\Sellenium\\ToolsQA_project\\src\\main\\java\\resources\\userDetails.properties";
            accountDetails.load(new FileInputStream(userDetailsFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return accountDetails.getProperty(key);
    }
}

package waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {

    public WebDriver browser;
    private final int timeoutInSeconds;

    public ExplicitWait(WebDriver browser, int timeoutInSeconds) {
        this.browser = browser;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public WebElement waitForVisibilityOfElementLocated(WebElement test) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(test));
    }

    public void waitForAlertToBePresent() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}



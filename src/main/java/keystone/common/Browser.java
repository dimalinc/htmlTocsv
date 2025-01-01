package keystone.common;

import keystone.utils.ConfigManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Browser {
    private static Browser instanceOfWebDriver = null;
    private WebDriver driver;
    private static String originalWindow;
    private static Logger log = Logger.getLogger(Browser.class.getName());

    private Browser() {
        try {
            driver = new BrowserFactory().getDriver("firefox"/*ConfigManager.getConfigProperty("DRIVER")*/);
        } catch (RuntimeException exception) {
            log.log(Level.SEVERE, exception.getMessage());
        }
    }

    public static Browser getInstanceOfWebDriver() {
        if (instanceOfWebDriver == null) {
            instanceOfWebDriver = new Browser();
        }
        return instanceOfWebDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void webDriverQuit() {
        log.log(Level.INFO, "WebDriver quit");
        getDriver().quit();
        log.log(Level.INFO, "instanceOfWebDriver = null WebDriver not Instans");
        instanceOfWebDriver = null;
    }

    public void openUrl(String url) {
        getDriver().get(url);
        originalWindow = getWindowHandle();
    }

    public void brawserWindowSize(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    public void browserWindowMaximize() {
        getDriver().manage().window().maximize();
    }

    public void browserWindowMinimize() {
        getDriver().manage().window().minimize();
    }

    public void browserWindowFullScreen() {
        getDriver().manage().window().fullscreen();
    }

    public void windowScale(String scale) throws RuntimeException {
        switch (scale.toLowerCase()) {
            case "minimize": {
                browserWindowMinimize();
                break;
            }
            case "maximize": {
                browserWindowMaximize();
                break;
            }
            case "fullScreen": {
                browserWindowFullScreen();
                break;
            }
            default:
                throw new RuntimeException("Incorrect Scale window");
        }
    }

    public static void tabClose() {
        getInstanceOfWebDriver().getDriver().close();
        getInstanceOfWebDriver().getDriver().switchTo().window(originalWindow);
    }

    public static String getWindowHandle() {
        return getInstanceOfWebDriver().getDriver().getWindowHandle();
    }

    public static void switchToNewTab() {
        for (String windowHandle : getInstanceOfWebDriver().getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                getInstanceOfWebDriver().getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }

    public static void switchToOriginalTab() {
        getInstanceOfWebDriver().getDriver().switchTo().window(originalWindow);
    }
}

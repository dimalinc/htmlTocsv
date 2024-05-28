package keystone.scenarios.old;

import keystone.pages.ItemPage;
import keystone.pages.login_page;
import org.openqa.selenium.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class keystone_login_scenario {

    // private static WebDriver driver = new BrowserFactory().getDriver(ConfigManager.getConfigProperty("DRIVER"));
    private static WebDriver driver = keystone.common.Browser.getInstanceOfWebDriver().getDriver();

    public static void main(String[] args) {
        login_page loginPage= new login_page();
        driver.get("https://wwwsc.ekeystone.com/Login?returnUrl=%2f");

        loginPage.populateNameANdPass();

       // if (!loginPage.checkAcceptAllCoockiesButton())
        loginPage.wait(700);
        loginPage.clickAcceptAllCoockiesButton();

        if (!loginPage.checkSignInButtonClickable()) {
            loginPage.wait(1000);
        }
        loginPage.clickSignInButton();

        /////////
        loginPage.wait(1000);

        ItemPage itemPage = new ItemPage();
        driver.get("https://wwwsc.ekeystone.com/search/detail?pid=SKYH7060");

        itemPage.divThisFitsClick();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        for(WebElement spanCarApplicationTitle:itemPage.spanCarApplicationTitleListInit() ) {
         //   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", spanCarApplicationTitle);
           // System.out.println("itemPage.listWebElementsInit(By.xpath(\"//span[@class='expand-collapse-icon fa fa-plus-square']\") size = " + itemPage.listWebElementsInit(By.xpath("//span[@class='expand-collapse-icon fa fa-plus-square']").size()));
            spanCarApplicationTitle.click();
        }



    }




}

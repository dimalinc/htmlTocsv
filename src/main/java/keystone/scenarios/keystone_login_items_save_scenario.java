package keystone.scenarios;

import keystone.pages.ItemPage;
import keystone.pages.login_page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.ArrayList;

public class keystone_login_items_save_scenario {
    static final int stopNumber = 400;
    static final int delay = 1350;
    //  static String linksFilePath = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\Draw-Tite_key\\draw-tite_key_links.txt";
      static String linksFilePath = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\rancho\\rancho_links.txt";
    //  static String savedHtmlDir = "D:\\savedHtml\\savedHtml_Draw-Tite_key" + "\\";
     static String savedHtmlDir = "D:\\savedHtml\\savedHtml_Rancho_key" + "\\";
    static ArrayList<String> linksList = new ArrayList<>();
    // private static WebDriver driver = new BrowserFctory().getDriver(ConfigManager.getConfigProperty("DRIVER"));
    private static WebDriver driver = keystone.common.Browser.getInstanceOfWebDriver().getDriver();

    public static void main(String[] args) {
        login_page loginPage = new login_page();
        driver.get("https://wwwsc.ekeystone.com/Login?returnUrl=%2f");
        loginPage.populateNameANdPass();
       //  if (!loginPage.checkAcceptAllCoockiesButton())
        loginPage.wait(15000);
        loginPage.clickAcceptAllCoockiesButton();

        if (!loginPage.checkSignInButtonClickable()) { loginPage.wait(1000); }
        loginPage.clickSignInButton();
        loginPage.wait(15000);

        readLinksFromFile();
        int n = 1;
        for (String linkString : linksList) {

            long start = System.currentTimeMillis();

            String fileName = linkString.split("=")[linkString.split("=").length - 1];

            ItemPage itemPage = new ItemPage();
            driver.get(linkString);
            sleep(delay*3);

            if (itemPage.getDivThisFits().isDisplayed()) {
                itemPage.divThisFitsClick();

                sleep(delay*5);

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,350)", "");

                for (WebElement spanCarApplicationTitle : itemPage.spanCarApplicationTitleListInit()) {
                    //   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", spanCarApplicationTitle);
                    // System.out.println("itemPage.listWebElementsInit(By.xpath(\"//span[@class='expand-collapse-icon fa fa-plus-square']\") size = " + itemPage.listWebElementsInit(By.xpath("//span[@class='expand-collapse-icon fa fa-plus-square']").size()));
                    spanCarApplicationTitle.click();
                    sleep(getRandomNumber(75,145));
                }
            }

            sleep(delay);
            writeToFile(fileName,driver);

            System.out.println(n++ + " " + fileName + " finished " + (System.currentTimeMillis() - start) /*/ 1000*/ + "miliseconds"
                    /*+ "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"*/);
          // sleep(delay);
            if ( ( stopNumber>0) && (n > stopNumber) ) break;
        }

        driver.quit();

    }

    static void writeToFile(String fileName, WebDriver driver) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(savedHtmlDir + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.write(driver.getPageSource());
            System.out.println("html Length = " + driver.getPageSource().length());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sleep(int delay) {
        //System.out.print("Sleep start _____ ");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.print(" " + delay + " ");
        //System.out.println("Sleep finished");
    }

    static void readLinksFromFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(linksFilePath));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

        String currentLine;
        try {
            //  System.out.println(currentLine);
            while ((currentLine = reader.readLine()) != null) {
                linksList.add(currentLine);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println(linksList);
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

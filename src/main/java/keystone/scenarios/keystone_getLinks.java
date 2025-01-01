package keystone.scenarios;

import keystone.pages.login_page;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class keystone_getLinks {

    static final int delay = 3000;
    //final static String manufacturerIRL = "https://wwwsc.ekeystone.com/Search?mt=Supplier:T29";
    final static String manufacturerIRL = "https://wwwsc.ekeystone.com/Search?mt=Supplier:R49";
    static String linksFilePath = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\carli\\carli.txt";
    static String savedHtmlDir = "D:\\savedHtml\\savedHtml_carli_2024" + "\\";
    static ArrayList<String> linksList = new ArrayList<>();
    private static WebDriver driver = keystone.common.Browser.getInstanceOfWebDriver().getDriver();
   //   private static WebDriver driver = new FirefoxDriver();
    private static ArrayList<String> linksStringsList = new ArrayList<>();



    public static void main(String[] args) {
        //readLinksFromFile();
        int n = 1;

        login_page loginPage = new login_page();
        driver.get("https://wwwsc.ekeystone.com/Login?returnUrl=%2f");
        loginPage.populateNameANdPass();
        //  if (!loginPage.checkAcceptAllCoockiesButton())
        loginPage.wait(delay * 5);
        loginPage.clickAcceptAllCoockiesButton();

        if (!loginPage.checkSignInButtonClickable()) {
            loginPage.wait(1000);
        }
        loginPage.clickSignInButton();
        loginPage.wait(delay * 2);

        driver.get(manufacturerIRL);
        sleep(delay * 4);

        // click48
        WebElement element_48 = driver.findElement(By.xpath("//div[@class='page-size-options']/ul/li/a[contains(text(),'48')]"));
        System.out.println("Clicking 48");
        element_48.click();
        sleep(delay * 5);

        int numberOfPages = driver.findElements(By.xpath("//div[@class='results-pager']/ul/li")).size();
        System.out.println("numberOfPages = " + numberOfPages);
        sleep(delay * 2);
       // WebElement webElementNext = driver.findElement(By.xpath("//div[@class='results-pager']/ul/li/a[contains(text(),'Next')]"));

        for (int i = 0; i <= numberOfPages - 2; i++) {

            System.out.println("page number = " + i);//  long start = System.currentTimeMillis();


            List<WebElement> productLinksWebElementsList = driver.findElements(By.xpath("//a[@class='detail-link']"));
            for (WebElement productLink_webElement : productLinksWebElementsList) {
                linksStringsList.add(productLink_webElement.getAttribute("href"));
                System.out.print(productLink_webElement.getText().length());
            }
            System.out.println();
            WebElement webElementNext;
            try {
                 webElementNext = driver.findElement(By.xpath("//a[@class='pager-link'][contains(text(),'Next')]"));
            } catch (NoSuchElementException e) {break;}
            sleep(delay * 3);
            System.out.println("clicking webElementNext.getText() = " + webElementNext.getText());
            webElementNext.click();
            sleep(delay * 5);

            System.out.println("linksStringsList.size() " + linksStringsList.size());
        }


        driver.quit();

        for (String linkString : linksStringsList) {
            System.out.println(linkString);
        }

       /*   writeToFile(fileName,driver);
            System.out.println(n++ + " " + fileName + " finished " + (System.currentTimeMillis() - start)  1000 + "miliseconds"
         + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"*);
            sleep(delay);*/

    }


    static void writeLinksFile(String fileName, WebDriver driver) {
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
        System.out.print("Sleep start _____ ");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(" " + delay + " ");
        System.out.println("Sleep finished");
    }


    static void readLinksFromFile() {
        File linksFile = new File(linksFilePath);
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

}

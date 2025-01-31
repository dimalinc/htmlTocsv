import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class downloadSelenium {

    static final int delay = 32750;
    static String linksFilePath = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\ads\\ads_links.txt";
    static String savedHtmlDir = "C:\\savedHtml\\savedHtml_ads" + "\\";
    static ArrayList<String> linksList = new ArrayList<>();

    public static void main(String[] args) {
        readLinksFromFile();
        int n = 1;

      //  WebDriver driver = new FirefoxDriver();
      /*  driver.get("https://eibach.com/product/E80-23-032-01-22");
        sleep(delay*20);
        driver.get("https://eibach.com/product/E80-23-032-01-22");*/



        /*System.setProperty("webdriver.opera.driver", "C:/operadriver_win64/operadriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");*/

        for (String linkString : linksList) {
            WebDriver driver = new FirefoxDriver();

            long start = System.currentTimeMillis();
            /*ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver(opt);*/


            String fileName = linkString.split("/")[linkString.split("/").length - 1];
          //  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
            driver.get(linkString);
            driver.manage().window().maximize();
            // TODO: remove for other except Timbren
            /*JavascriptExecutor js = (JavascriptExecutor) driver;
            // js.executeScript("document.body.style.zoom = '0.25'");
            js.executeScript("window.scrollBy(0,500)", "");
            try {
                driver.findElement(By.xpath("//button[@aria-label='Accept']")).click();
            } catch (NoSuchElementException ex) {
                ex.printStackTrace();
            }
            try {
                WebElement buttonDescription = driver.findElement(By.xpath("//button[@data-accordion-trigger='description-main']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonDescription);
                js.executeScript("window.scrollBy(0,250)", "");
                buttonDescription.click();
            } catch (NoSuchElementException ex) {
                ex.printStackTrace();
            } catch (ElementClickInterceptedException ex) {
                ex.printStackTrace();
                js.executeScript("window.scrollBy(0,250)", "");
                WebElement buttonDescription = driver.findElement(By.xpath("//button[@data-accordion-trigger='description-main']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonDescription);
                js.executeScript("window.scrollBy(0,250)", "");
                buttonDescription.click();
            }
            try {
                WebElement buttonFitment = driver.findElement(By.xpath("//button[@data-accordion-trigger='description-3']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonFitment);
                js.executeScript("window.scrollBy(0,320)", "");
                buttonFitment = driver.findElement(By.xpath("//button[@data-accordion-trigger='description-3']"));
                driver.manage().window().fullscreen();
                buttonFitment.click();
            } catch (NoSuchElementException ex) {
                ex.printStackTrace();
            } catch (ElementClickInterceptedException ex) {
                ex.printStackTrace();
                js.executeScript("window.scrollBy(0,250)", "");
                WebElement buttonFitment = driver.findElement(By.xpath("//button[@data-accordion-trigger='description-3']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonFitment);
                buttonFitment = driver.findElement(By.xpath("//button[@data-accordion-trigger='description-3']"));
                buttonFitment.click();
            }*/


            writeToFile(fileName,driver);

            System.out.println(n++ + " " + fileName + " finished " + (System.currentTimeMillis() - start) /*/ 1000*/ + "miliseconds"
                    /*+ "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"*/);
            sleep(delay);

            driver.quit();
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

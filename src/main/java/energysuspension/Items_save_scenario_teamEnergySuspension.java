package energysuspension;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.ArrayList;

public class Items_save_scenario_teamEnergySuspension {
    static final int stopNumber = 0;
    static final int delay = 1450;
    static String linksFilePath =
            "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\skyjacker\\com\\skyjackerCom_links.txt";
    static String savedHtmlDir = "D:\\savedHtml\\skyjacker.com" + "\\";
    static ArrayList<String> linksList = new ArrayList<>();
    // private static WebDriver driver = new BrowserFactory().getDriver(ConfigManager.getConfigProperty("DRIVER"));
    private static WebDriver driver = keystone.common.Browser.getInstanceOfWebDriver().getDriver();

    public static void main(String[] args) {


        readLinksFromFile();
        int n = 1;
        for (String linkString : linksList) {

            long start = System.currentTimeMillis();

            String fileName = linkString.split("/")[linkString.split("/").length - 1];
            System.out.println(fileName);

            driver.get(linkString);
            sleep(delay*5);

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,350)", "");

            sleep(delay);
            writeToFile(fileName,driver);

            System.out.println(n++ + " " + fileName + " finished " + (System.currentTimeMillis() - start) /*/ 1000*/ + "miliseconds"
                    /*+ "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"*/);
            sleep(delay);
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

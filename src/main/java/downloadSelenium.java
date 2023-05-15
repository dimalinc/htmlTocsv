import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;

public class downloadSelenium {

    static final int delay = 15750;
    static String linksFilePath = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\energysuspension\\energysuspension_links.txt";
    static String savedHtmlDir = "D:\\savedHtml\\savedHtml_energysuspension\\";
    static ArrayList<String> linksList = new ArrayList<>();

    public static void main(String[] args) {
        readLinksFromFile();
        int n=1;
        for (String linkString : linksList) {
            long start = System.currentTimeMillis();
            WebDriver driver = new ChromeDriver();
            String fileName = linkString.split("/")[linkString.split("/").length - 1];
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
            driver.get(linkString);
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(savedHtmlDir + fileName));
            } catch (IOException e) { e.printStackTrace(); }
            try {
                writer.write(driver.getPageSource());
                System.out.println("html Length = " + driver.getPageSource().length());
                writer.close();
            } catch (IOException e) { e.printStackTrace(); }
            driver.quit();
            System.out.println(n++ + " " + fileName + " finished " + (System.currentTimeMillis() - start) /*/ 1000*/ + "miliseconds"
                    /*+ "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"*/);
            sleep(delay);
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

    void writeToFile() {

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

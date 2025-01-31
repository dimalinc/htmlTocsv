package FCS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class downloadSelenium_FCS_rockauto {

    static final int delay = 2750;
    static String linksFilePath = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\src\\main\\java\\FCS\\FCD_partnumbers.txt";
    static String savedHtmlDir = "D:\\savedHtml\\savedHtml_fcs_rockauto" + "\\";
    static ArrayList<String> linksList = new ArrayList<>();

    public static void main(String[] args) {
        readLinksFromFile();
        int n = 1;

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        /*driver.get("https://camburg.com/shop/accessories/acc-lighting/baja-designs-chevy-1500-14-pro-a-pillar-light-kit/");
        System.out.print(" FIRST ");
        sleep(delay * 14);
        driver.get("https://camburg.com/shop/accessories/acc-lighting/baja-designs-chevy-1500-14-pro-a-pillar-light-kit/");*/





      /*  System.setProperty("webdriver.opera.driver", "C:/operadriver_win64/operadriver.exe");
        WebDriver driver = operaDriver;
        driver.get("https://duckduckgo.com/");*/

        for (String linkString : linksList) {

            long start = System.currentTimeMillis();
            /*ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--remote-allow-origins=*");
            WebDriver driver = new ChromeDriver(opt);*/


            String partNumber = linkString.split("/")[linkString.split("/").length - 1];
            //  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
            driver.get(linkString);
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

            // only for camburg options
           /* WebElement dropdownSelectWebElement = null;

            try {
                sleep(500);
                dropdownSelectWebElement = driver.findElement(By.xpath("//div[@class='select-wrapper']/select"));

                Select select = new Select(dropdownSelectWebElement);
               // dropdownSelectWebElement.click();

                List<WebElement> optionList = select.getOptions();
                int nn=0;
                for (WebElement child : optionList) {
                    select.selectByIndex(nn);
                  //  child.click();
                    sleep(300);

                    writeToFile(fileName + "_" + nn++, driver);

                    // WebElement parent = child.findElement(By.xpath("./.."));
                    //  WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript( "return arguments[0].parentNode;", child);
                    WebElement parent = driver.findElement(By.xpath(Item_Camburg.xpathStringsList.get(5)));
                    parent.click();

                }
            } catch (NoSuchElementException e) { e.printStackTrace(); }
            if (dropdownSelectWebElement == null)*/


            try {
                Files.createDirectories(Paths.get(savedHtmlDir + partNumber));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                driver.findElement(By.xpath("//span[@class='listing-final-partnumber  as-link-if-js']")).click();
            } catch (Exception e) {
                System.out.println(partNumber + " ");
                e.printStackTrace();
                continue;
            }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        writeToFile(savedHtmlDir + partNumber, partNumber + ".html", driver);
            try {
                driver.findElement(By.xpath("//a[contains(text(),'Info')]")).click();
        } catch (Exception e) {
            System.out.println(partNumber + " ");
            e.printStackTrace();
        }

        try {Thread.sleep(1500);} catch (InterruptedException e) {throw new RuntimeException(e);}


        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        writeToFile(savedHtmlDir + partNumber, partNumber + "_2" + ".html", driver);
        driver.close();
        driver.switchTo().window((String) windowHandles[0]);



            /*Robot r = null;
            try {r = new Robot();
            } catch (AWTException e) {throw new RuntimeException(e);}
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_W);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_W);*/


        System.out.println(n++ + " " + partNumber + " finished " + (System.currentTimeMillis() - start) /*/ 1000*/ + "miliseconds"
                /*+ "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes"*/);
        sleep(delay);
    }

        driver.quit();

}

static void writeToFile(String dir, String fileName, WebDriver driver) {
    BufferedWriter writer = null;
    try {
        writer = new BufferedWriter(new FileWriter(dir + "\\" + fileName));
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

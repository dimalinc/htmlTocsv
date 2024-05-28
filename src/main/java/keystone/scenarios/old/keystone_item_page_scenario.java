package keystone.scenarios.old;

import keystone.pages.ItemPage;
import org.openqa.selenium.WebDriver;

public class keystone_item_page_scenario {

    private static WebDriver driver = keystone.common.Browser.getInstanceOfWebDriver().getDriver();

    public static void main(String[] args) {
        ItemPage itemPage = new ItemPage();
        driver.get("https://wwwsc.ekeystone.com/search/detail?pid=SKYH7060");


        itemPage.plusFitmentButtonClick();




    }

}

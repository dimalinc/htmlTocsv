package energysuspension;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_teamEnergySuspension {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        xpathStringsList.add("//ul[@class='breadcrumbs fixclear bread-style--minimal']");
        xpathStringsList.add("(//div[@class='kw-details-desc'])[1]"); //short desc
        xpathStringsList.add("//span[@class='sku']");
        xpathStringsList.add("//h1"); // title
        xpathStringsList.add("//span[@class='posted_in']"); // tags, categories
        xpathStringsList.add("//table[@class='table-appsum']"); // fitment table
        xpathStringsList.add("//div[@class='woocommerce-Tabs-panel woocommerce-Tabs-panel--description panel entry-content wc-tab']"); // long desc
        xpathStringsList.add("//img[@class='wp-post-image']/@src");

        return xpathStringsList;
    }

}

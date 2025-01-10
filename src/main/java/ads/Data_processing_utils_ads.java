package ads;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_ads {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();

        xpathStringsList.add("//*[@class='breadcrumb']");
        xpathStringsList.add("//div[@class='product-brand']");
        xpathStringsList.add("//h1[@class='product-name']");
        xpathStringsList.add("//p[@class='short-description']");
        xpathStringsList.add("//span[@id='partnumber']");
        xpathStringsList.add("//div[@itemprop='price']");
        xpathStringsList.add("//div[@class='alt_product_images']/a/@href");
        xpathStringsList.add("//div[@class='full_description']");
        xpathStringsList.add("//h3");
        xpathStringsList.add("//ul[@class='productpage_list']");
        xpathStringsList.add("//table[@class='attributes-table']");
        xpathStringsList.add("//div[@class='widget_info tech_resource_links']/a/@href");
        xpathStringsList.add("//div[@class='widget_text installation_notes product_info']");

        return xpathStringsList;
    }

}

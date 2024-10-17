package kingshocks;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_Kingshocks {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        /*xpathStringsList.add("//h1");
        xpathStringsList.add("//img/@src");
        xpathStringsList.add("//div[@id='panel2v']/table");
        xpathStringsList.add("//div[@id='buyers-guide-data']/table");
        xpathStringsList.add("//div[@id='panel6v']/*");*/

        xpathStringsList.add("//h1");
        xpathStringsList.add("//span[@itemprop='sku']");
        xpathStringsList.add("//div[@class='wsm-prod-summary']");
        xpathStringsList.add("//div[@id='wsm-prod-tab-1']");
        xpathStringsList.add("//div[@id='wsm-prod-tab-details']");
        xpathStringsList.add("//*[@class='wsm_product_details_tags2']");
        xpathStringsList.add("//*[@class='wsm_product_details_tags2']/a");
        xpathStringsList.add("//*[@class='wsm_product_details_tags2']/label[contains(text(), 'Year')]/following-sibling::a");
        xpathStringsList.add("//div[@id='wsm-prod-tab-shipping']");
        xpathStringsList.add("//*[@class='wsm-file-pdf-small']/a/@href");
        xpathStringsList.add("//img[@itemprop='image']/@src");

       /* xpathStringsList.add("h1");
        xpathStringsList.add("//span[@class='breadcrumbs home']");
        xpathStringsList.add("//span[@class='tab-product-app']");
        xpathStringsList.add("//span[@class='featuredBlock']");
        xpathStringsList.add("//div[@class='product-image']/img/@src");
        xpathStringsList.add("//div[@class='product-code']/span");*/

        return xpathStringsList;
    }

}

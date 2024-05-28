package super_springs;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_SuperSprings {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        /*xpathStringsList.add("//h1");
        xpathStringsList.add("//img/@src");
        xpathStringsList.add("//div[@id='panel2v']/table");
        xpathStringsList.add("//div[@id='buyers-guide-data']/table");
        xpathStringsList.add("//div[@id='panel6v']/*");*/

        xpathStringsList.add("//h3[@class='product_slug']");
        xpathStringsList.add("//span[@class='woocommerce-Price-amount amount']/bdi");
        xpathStringsList.add("//div[@class='thumbnails Addify_Product_Videos-thumbnails afpv-pro-thumb columns-4 ']/a/@href");
        xpathStringsList.add("//a[@class='instructions']/@href");
        xpathStringsList.add("//div[@class='woocommerce-product-details__short-description']");
        xpathStringsList.add("//table[@class='woocommerce-product-attributes shop_attributes']");
        xpathStringsList.add("//tr[@class='woocommerce-product-attributes-item woocommerce-product-attributes-item--attribute_applications-summary']/td[@class='woocommerce-product-attributes-item__value']");

        return xpathStringsList;
    }

}

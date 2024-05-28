package motonsuspensionusa;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Moton_data_processing_utils {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        xpathStringsList.add("//ul[@class='breadcrumbs__links']");
        xpathStringsList.add("//h1[@class='product-detail__title']");
        xpathStringsList.add("//a[@class='plain-link']");
        xpathStringsList.add("//span[@class='type-wrapper__type']");
        xpathStringsList.add("//span[@class='sku-wrapper__sku']");
        xpathStringsList.add("//div[@class='rte rte--expanded-images clearfix row tab-content tab-content--active ']");
        xpathStringsList.add("//*[@id='tab1']/p[1]");
        xpathStringsList.add("//*[@id='tab1']/p[2]");
       // xpathStringsList.add("//*[@id='tab1']/*/text()");
        xpathStringsList.add("//div[@id=\"tab1\"]/descendant::*");
        xpathStringsList.add("//h3[contains(text(),'Features')]/following-sibling::ul[1]");
        xpathStringsList.add("//h3[contains(text(),'Installation Information')]/following-sibling::p");
        xpathStringsList.add("//h3[contains(text(),'Product Specifications')]/following-sibling::ul[1]");
        xpathStringsList.add("//h3[contains(text(),'Vehicle Fitment')]/following-sibling::ul[1]");
        xpathStringsList.add("//img[contains(@class,'rimage__image')]/@src");
        xpathStringsList.add("(//span[@class='theme-money'])[1]");

        return xpathStringsList;
    }

}

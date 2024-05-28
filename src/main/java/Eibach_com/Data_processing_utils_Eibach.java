package Eibach_com;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_Eibach {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        /*xpathStringsList.add("//h1");
        xpathStringsList.add("//img/@src");
        xpathStringsList.add("//div[@id='panel2v']/table");
        xpathStringsList.add("//div[@id='buyers-guide-data']/table");
        xpathStringsList.add("//div[@id='panel6v']/*");*/

        xpathStringsList.add("//nav[@class='breadcrumb']");
        xpathStringsList.add("(//h1/div)[1]");
        xpathStringsList.add("(//h1/div)[2]/strong");
        xpathStringsList.add("(//h1/div)[2]/span");
        xpathStringsList.add("//div[@id='part_number']");
        xpathStringsList.add("//div[@id='short-description']");
        xpathStringsList.add("//div[@class='product-attributes']");
        xpathStringsList.add("//div[@class='item-price']");
        xpathStringsList.add("//div[@id='product-features']");
        xpathStringsList.add("//div[@id='product-warranty']");
        xpathStringsList.add("//h2[contains(text(),'Instructions')]/following-sibling::ul[@class='list-unstyled']/li/a/@href");
        xpathStringsList.add("//img[@class='thumbswap']/@src");
        xpathStringsList.add("//div[contains(@class, 'recommendation')]/a/@href");
        xpathStringsList.add("//div[contains(@class, 'recommendation')]/p");

        /*xpathStringsList.add("//div[@class='application-list']/a/@href");
        xpathStringsList.add("//div[@class='collapsible application-list']/a/@href");*/

        return xpathStringsList;
    }

}

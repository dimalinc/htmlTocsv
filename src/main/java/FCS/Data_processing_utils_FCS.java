package FCS;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_FCS {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        /*xpathStringsList.add("//h1");
        xpathStringsList.add("//img/@src");
        xpathStringsList.add("//div[@id='panel2v']/table");
        xpathStringsList.add("//div[@id='buyers-guide-data']/table");
        xpathStringsList.add("//div[@id='panel6v']/*");*/

        xpathStringsList.add("//span[@class='listing-final-partnumber  as-link-if-js']");
        xpathStringsList.add("//span[@class='listing-footnote-text']");
        xpathStringsList.add("//img[@class='listing-inline-image listing-inline-image-thumb listing-inline-image-border']/@src");
        xpathStringsList.add("(//table[@class='nobmp'])[1]");

       /* xpathStringsList.add("h1");
        xpathStringsList.add("//span[@class='breadcrumbs home']");
        xpathStringsList.add("//span[@class='tab-product-app']");
        xpathStringsList.add("//span[@class='featuredBlock']");
        xpathStringsList.add("//div[@class='product-image']/img/@src");
        xpathStringsList.add("//div[@class='product-code']/span");*/

        return xpathStringsList;
    }

}

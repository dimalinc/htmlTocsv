package teraflex;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Teraflex_data_processing_utils {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        xpathStringsList.add("//span[@itemprop='name']");
        xpathStringsList.add("//div[@itemprop='sku']");
        xpathStringsList.add("//div[@itemprop='description']");
        xpathStringsList.add("//img[@class='fotorama__img']/@src"); //
        xpathStringsList.add("//div[@id='custom-1']");
        xpathStringsList.add("//div[@id='custom-2']");
        xpathStringsList.add("//div[@class='product attribute description']/div[@class='value']");
        xpathStringsList.add("(//span[@class='price'])[1]");
        xpathStringsList.add("//img[@class='zoomImg']/@src");
        xpathStringsList.add("//img[@class='gallery-placeholder__image']/@src");

        return xpathStringsList;
    }

}

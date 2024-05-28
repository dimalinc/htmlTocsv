package monroe_drivparts;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_Monroe {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        /*xpathStringsList.add("//h1");
        xpathStringsList.add("//img/@src");
        xpathStringsList.add("//div[@id='panel2v']/table");
        xpathStringsList.add("//div[@id='buyers-guide-data']/table");
        xpathStringsList.add("//div[@id='panel6v']/*");*/

        xpathStringsList.add("//p[@class='part-detail-header-part-name']");
        xpathStringsList.add("//p[@class='part-detail-header-part-number']");
        xpathStringsList.add("//img[@data-v-e4caeaf8=\"\"]/@src");
        xpathStringsList.add("//div[@class='driv-part-detail-page-specifications-container']");
        xpathStringsList.add("//div[@class='applications-subtab tab-table']");
        xpathStringsList.add("//div[@class='media-container']/a/@href");
        xpathStringsList.add("//p[@class='part-detail-content-characteristic_title']");
        xpathStringsList.add("//ul[@class='part-detail-content-characteristic_list']");


        /*xpathStringsList.add("//div[@class='application-list']/a/@href");
        xpathStringsList.add("//div[@class='collapsible application-list']/a/@href");*/

        return xpathStringsList;
    }

}

package monroe_drivparts;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_Monroe {
    static ArrayList<Element> jsoupElementsList = new ArrayList<>();
    static String lineSeparator = System.lineSeparator();
    static ArrayList<String> itemFields_XpathStringsList = new ArrayList<>();
    static ArrayList<String> specifications_XpathStringsList = new ArrayList<>();
    static ArrayList<String> applications_XpathStringsList = new ArrayList<>();

    public static ArrayList<String> itemFields_xpathStringsListInit() {


        /*itemFields_XpathStringsList.add("//h1");
        itemFields_XpathStringsList.add("//img/@src");
        itemFields_XpathStringsList.add("//div[@id='panel2v']/table");
        itemFields_XpathStringsList.add("//div[@id='buyers-guide-data']/table");
        itemFields_XpathStringsList.add("//div[@id='panel6v']/*");*/

        itemFields_XpathStringsList.add("//p[@class='part-detail-header-part-name']");
        itemFields_XpathStringsList.add("//p[@class='part-detail-header-part-number']");
        itemFields_XpathStringsList.add("//img[@data-v-e4caeaf8=\"\"]/@src");
        itemFields_XpathStringsList.add("//div[@class='driv-part-detail-page-specifications-container']");
        itemFields_XpathStringsList.add("//div[@class='applications-subtab tab-table']");
        itemFields_XpathStringsList.add("//div[@class='media-container']/a/@href");
        itemFields_XpathStringsList.add("//p[@class='part-detail-content-characteristic_title']");
        itemFields_XpathStringsList.add("//ul[@class='part-detail-content-characteristic_list']");


        /*itemFields_XpathStringsList.add("//div[@class='application-list']/a/@href");
        itemFields_XpathStringsList.add("//div[@class='collapsible application-list']/a/@href");*/

        return itemFields_XpathStringsList;
    }







}

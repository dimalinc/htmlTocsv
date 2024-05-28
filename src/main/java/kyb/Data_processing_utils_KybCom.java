package kyb;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class Data_processing_utils_KybCom {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        xpathStringsList.add("//h1");
        xpathStringsList.add("//img/@src");
        xpathStringsList.add("//div[@id='panel2v']/table");
        xpathStringsList.add("//div[@id='buyers-guide-data']/table");
        xpathStringsList.add("//div[@id='panel6v']/*");


        return xpathStringsList;
    }

}

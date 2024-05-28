package crown;

import classes.utils.data_processing_utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Item_Crown {

    static ArrayList<String> xpathStringsList = Data_processing_utils_Crown.xpathStringsListInit();

    static ArrayList<Element> jsoupElementsList = new ArrayList<>();

    protected File inputFile;
    Document doc;
    String urlString;     String urlStringSKU;
    String breadcrumbs;
    String imgLinks;     String imgLinksURL;
    String title;
    String sku;
    String specs; String specsHTML;
    String application; String applicationHTML;



    public Item_Crown(File file, String domain) {
        this.inputFile = file;
        try {
            doc = Jsoup.parse(inputFile, "UTF-8", domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            fieldsInit();
        }
    }

    public void fieldsInit() {
        urlString = "https://www.crownautomotive.net/" + inputFile.getName();
        urlStringSKU =  inputFile.getName().replace(".html","");

        title = generateCellContent(xpathStringsList.get(0), doc);
        breadcrumbs = generateCellContent(xpathStringsList.get(1), doc);


        application = generateCellContent(xpathStringsList.get(2), doc);
        applicationHTML = generateCellContentInnerHTML(xpathStringsList.get(2), doc);

        specs = generateCellContent(xpathStringsList.get(3), doc);
        specsHTML = generateCellContentInnerHTML(xpathStringsList.get(3), doc);

        imgLinks = generateCellContentForPicUrl(xpathStringsList.get(4), doc, "; ");
        imgLinksURL="https://www.crownautomotive.net/mm5/"+imgLinks.replace(";","");
        sku = generateCellContent(xpathStringsList.get(5), doc);

    }

    public static String generateCellContent(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) || (xpath.endsWith("data-thumb"))  )
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList)
        //csv
        {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(Data_processing_utils_Crown.lineSeparator);
        }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }

    public static String generateCellContentFor_i_number(String xpath, Document doc, int i) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
        else jsoupElementsList = doc.selectXpath(xpath);

        String returnString=null;
        if (jsoupElementsList.size()>i)
        {
            returnString = jsoupElementsList.get(i).text();
            if ((xpath.endsWith("href"))) returnString = jsoupElementsList.get(i).attr("href");
            if ((xpath.endsWith("src"))) returnString = jsoupElementsList.get(i).attr("src");
        }

        return returnString;
    }

    public static String generateCellContentForPicUrl(String xpath, Document doc, String lineSep) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList)
        //csv
        {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element, xpath))/*.append("\"")*/.append(lineSep);
        }
        if (sb.lastIndexOf(lineSep) > 0)
            sb.delete(sb.lastIndexOf(lineSep), sb.lastIndexOf(lineSep.substring(lineSep.length())));

        // System.out.println(sb.toString());

        return sb.toString();
    }

    public static String generateCellContentInnerHTML(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath/*.substring(0,xpath.lastIndexOf("/"))*/);
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList) {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementInnerHTML(element, xpath))/*.append("\"")*/.append(System.lineSeparator());
        }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }

    public static String generateCellContentOuterHTML(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ((xpath.endsWith("href")) || (xpath.endsWith("src")))
            jsoupElementsList = doc.selectXpath(xpath/*.substring(0,xpath.lastIndexOf("/"))*/);
        else jsoupElementsList = doc.selectXpath(xpath);

        StringBuilder sb = new StringBuilder();/*sb.append("=");*/
        for (Element element : jsoupElementsList) {
            sb./*append("\"").*/append(data_processing_utils.generateWebElementOuterHTML(element, xpath))/*.append("\"")*/.append(System.lineSeparator());
        }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }


}

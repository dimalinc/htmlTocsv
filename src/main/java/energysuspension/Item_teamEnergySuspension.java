package energysuspension;

import classes.utils.data_processing_utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Item_teamEnergySuspension {

   static ArrayList<String> xpathStringsList = Data_processing_utils_teamEnergySuspension.xpathStringsListInit();

    static ArrayList<Element> jsoupElementsList=new ArrayList<>();

    protected File inputFile;
    Document doc;
    String urlString;
    String breadCrumbs;
    String shortDesc;     String longDesc;
    String sku; String title; String categoriesTags;
    String itemFitmentTable; String itemFitmentTableHTML;

    String imgLink;



    public Item_teamEnergySuspension(File file, String domain) {
        this.inputFile = file;
        try { doc = Jsoup.parse(inputFile, "UTF-8", domain); } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) { fieldsInit(); }
    }

    public void fieldsInit() {
         urlString="https://teamenergysuspension.com/"+inputFile.getName();
         breadCrumbs =generateCellContent(xpathStringsList.get(0), doc);
         shortDesc =generateCellContent(xpathStringsList.get(1), doc);
         sku =generateCellContent(xpathStringsList.get(2), doc);
         title =generateCellContent(xpathStringsList.get(3), doc);
         categoriesTags =generateCellContent(xpathStringsList.get(4), doc);

        itemFitmentTable =generateCellContent(xpathStringsList.get(5), doc);
        if (itemFitmentTable.length()>2) System.out.println("itemFitmentTable = "+itemFitmentTable);
        itemFitmentTableHTML =generateCellContentHTML(xpathStringsList.get(5), doc);

         longDesc =generateCellContent(xpathStringsList.get(6), doc);
         imgLink =generateCellContentForPicUrl(xpathStringsList.get(7), doc,", ");

    }

    public static String generateCellContent(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) )
            jsoupElementsList=doc.selectXpath(xpath.substring(0,xpath.lastIndexOf("/")));
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();/*sb.append("=");*/
        for (Element element:jsoupElementsList)
        //csv
        { sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element,xpath))/*.append("\"")*/.append(Data_processing_utils_teamEnergySuspension.lineSeparator); }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }

    public static String generateCellContentFor_i_number(String xpath,Document doc,int i) {
        // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) )
            jsoupElementsList=doc.selectXpath(xpath.substring(0,xpath.lastIndexOf("/")));
        else  jsoupElementsList=doc.selectXpath(xpath);

        String returnString=jsoupElementsList.get(i).text();
        if ( (xpath.endsWith("href"))  ) returnString=jsoupElementsList.get(i).attr("href");
        if ( (xpath.endsWith("src"))  ) returnString=jsoupElementsList.get(i).attr("src");

        return returnString;
    }

    public static String generateCellContentForPicUrl(String xpath,Document doc,String lineSep) {
        // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) )
            jsoupElementsList=doc.selectXpath(xpath.substring(0,xpath.lastIndexOf("/")));
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();/*sb.append("=");*/
        for (Element element:jsoupElementsList)
        //csv
        { sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element,xpath))/*.append("\"")*/.append(lineSep); }
        if (sb.lastIndexOf(lineSep)>0)  sb.delete(sb.lastIndexOf(lineSep),sb.lastIndexOf(lineSep.substring(lineSep.length())));

        // System.out.println(sb.toString());

        return sb.toString();
    }

    public static String generateCellContentHTML(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) )
            jsoupElementsList=doc.selectXpath(xpath/*.substring(0,xpath.lastIndexOf("/"))*/);
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();/*sb.append("=");*/
        for (Element element:jsoupElementsList)
        { sb./*append("\"").*/append(data_processing_utils.generateWebElementInnerHTML(element,xpath))/*.append("\"")*/.append(System.lineSeparator()); }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }


}

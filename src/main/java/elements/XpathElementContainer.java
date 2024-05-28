package elements;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class XpathElementContainer {

    protected int i;
    protected static String lineSeparator ="&CHAR(10)&";
    protected String xpath;
    protected Document doc;
    ArrayList<Element> jsoupElementsList=new ArrayList<>();
    protected String webElementValue=null;
    protected String csvCellValue=null;

    public static String getLineSeparator() {
        return lineSeparator;
    }

    public XpathElementContainer(String xpath, Document doc, int i) {
        this.xpath = xpath;
        this.doc=doc;
        csvValueInit(i);
    }

    public String getWebElementValue() {
        return webElementValue;
    }

    protected String generateWebElementValue(Element element) {
        if (xpath.contains("href"))
           return element.attr("href");
        else if (xpath.contains("src"))
            return element.attr("src");
        else return element.text();
    }

    protected void csvValueInit(int i) {
        if ( (i==3)||(i==9) ) csvValueInitChar10();
                else csvValueInitSystemLineSeparator();

    }

    protected void csvValueInitChar10() {
        if ( (xpath.contains("href")) || (xpath.contains("src")) )
            jsoupElementsList=doc.selectXpath(xpath.substring(0,xpath.lastIndexOf("/")));
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();
        sb.append("=");
        for (Element element:jsoupElementsList)
        //csv
        { sb.append("\"").append(generateWebElementValue(element)).append("\"").append(lineSeparator); }
        if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));


        //xls
       /* {
            sb.append("\"").append(generateWebElementValue(element)).append("\"").append(";CHAR(10);");
        }*/
        // sb.delete(sb.lastIndexOf(",CHAR(10),"),sb.lastIndexOf(","));
        /*if (sb.lastIndexOf(";")>0) sb.deleteCharAt(sb.lastIndexOf(";"));
        sb.append(")");*/

        if (sb.toString().length() >2 )
        csvCellValue= sb.toString().replace(";","++")/*.substring(0,sb.toString().lastIndexOf("line.separator"))*/;
        else    csvCellValue="";
    }

    protected void csvValueInitSystemLineSeparator() {
        if ( (xpath.contains("href")) || (xpath.contains("src")) )
            jsoupElementsList=doc.selectXpath(xpath.substring(0,xpath.lastIndexOf("/")));
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();
        for (Element element:jsoupElementsList)
            sb.append(generateWebElementValue(element)).append(System.lineSeparator());
        if (sb.toString().length() >2 )
        csvCellValue= sb.toString()/*.substring(0,sb.toString().lastIndexOf(System.lineSeparator()))*/;
        else    csvCellValue="";
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public String getCsvCellValue() {
        return csvCellValue;
    }

    public void setCsvCellValue(String csvCellValue) {
        this.csvCellValue = csvCellValue;
    }
}
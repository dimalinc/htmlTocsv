package classes.utils;

import classes.Car;
import classes.Item;
import classes.PrestaAttribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import static classes.PrestaAttribute.prestaAttributesListCreate;

public class data_processing_utils {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        xpathStringsList.add("//div[@class='product-detail-primary-identifier']/span[@class='row-value']"); //SKU
        xpathStringsList.add("//h1[@class='title']"); // Skyjacker Suspensions H7060
        xpathStringsList.add("//span[@class='description']"); // SHORT Shock Absorber; Hydro 7000; Hydraulic; Limited Lifetime Warranty; Non-Adjustable; Includes Dust Boot; Single
        // xpathStringsList.add("//span[@class='title']"); // carline
        xpathStringsList.add("//div[@class='product-detail-attributes']"); //Attributes from description (product info tab)
        xpathStringsList.add("//div[@class='product-detail-description']");
        xpathStringsList.add("//div[@class='product-detail-features']");
        xpathStringsList.add("//ul[@class='product-detail-application-summaries']"); // fitment
        xpathStringsList.add("//li[@class='product-detail-application-summary']"); // fitment
        //  xpathStringsList.add("//li[contains(@class, 'application')]/a");  // color - engines, cabins - why breaks?

        //  xpathStringsList.add("//li[@class='application']");   // Year+Make+Model+position, lift
        //  xpathStringsList.add("//li[@class='application-attribute']");   // position, lift
        // xpathStringsList.add("//li[@class='application-footnote']");
        // xpathStringsList.add("//div[@class='product-detail-primary-identifier']/span[@class='row-value']");
        xpathStringsList.add("//div[@class='product-detail-documents']/ul/li/a/@href");
        xpathStringsList.add("//div[@class='galleria-image']/img/@src");

        xpathStringsList.add("//span[@class='result-title']"); // kit contents
        xpathStringsList.add("//span[@class='result-title']/ancestor::a[@class='detail-link']/@href"); // detail link
        //xpathStringsList.add("//ul[@class='kit-component-results']"); // kit components
        xpathStringsList.add("//ul[@class='kit-component-results']//div[@class='search-result-detail result-view-rows']"); // kit components


        return xpathStringsList;
    }

    public String removeUnwantedSymbols(String s){
        String result;
        ArrayList<Character> unwantedCharsArrayList=new ArrayList<>();
        char[] arrChars="/ \\ $ & % < > * # ' \" ` ~ ( ) [ ] { } | = ".toCharArray();
        for (int i = 0; i < arrChars.length; i++) {
            unwantedCharsArrayList.add(arrChars[i]); }

        for (int i = 0; i < unwantedCharsArrayList.size(); i++) {
            s=s.replace(unwantedCharsArrayList.get(i), '-'); }
        result=s;
        return result   ;
    }

    public static String generateCellContentText(String xpath, Document doc) {
       // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) )
            jsoupElementsList=doc.selectXpath(xpath.substring(0,xpath.lastIndexOf("/")));
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();/*sb.append("=");*/
        for (Element element:jsoupElementsList)
        //csv
        { sb./*append("\"").*/append(data_processing_utils.generateWebElementValue(element,xpath))/*.append("\"")*/.append(lineSeparator); }
       /* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*/
        return sb.toString();
    }

    public static String generateCellContentHTML(String xpath, Document doc) {
        // jsoupElementsList=new ArrayList<>();
        if ( (xpath.endsWith("href")) || (xpath.endsWith("src")) )
            jsoupElementsList=doc.selectXpath(xpath/*.substring(0,xpath.lastIndexOf("/"))*/);
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();/*sb.append("=");*/
        for (Element element:jsoupElementsList)
        { sb./*append("\"").*/append(data_processing_utils.generateWebElementInnerHTML(element,xpath))/*.append("\"")*/.append(lineSeparator); }
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

    public static String generateWebElementValue(Element element, String xpath) {
        if (xpath.endsWith("href")) { return element.attr("href"); }
        else if (xpath.endsWith("src")) { return element.attr("src"); }
        else if (xpath.endsWith("data-srcset")) { return element.attr("data-srcset"); }
        else if (xpath.endsWith("data-thumb")) { return element.attr("data-thumb"); }
        else return element.text();
    }

    public static String generateWebElementInnerHTML(Element element, String xpath) {
        /*if (xpath.endsWith("href")) { return element.attr("href"); }
        else if (xpath.endsWith("src")) { return element.attr("src"); }
        else if (xpath.endsWith("data-srcset")) { return element.attr("data-srcset"); }
        else*/ return element.html();
    }

    public static String generateWebElementOuterHTML(Element element, String xpath) {
        /*if (xpath.endsWith("href")) { return element.attr("href"); }
        else if (xpath.endsWith("src")) { return element.attr("src"); }
        else if (xpath.endsWith("data-srcset")) { return element.attr("data-srcset"); }
        else*/ return element.outerHtml();
    }

    public static Double[] makeDoubleArray(String s) {
        Double[] result =new Double[2];
        result[0]=-100.0;         result[1]=-100.0;

        // TODO: check how removie word INch influenced on leveling kits TU713ST 1 Inch to 3 Inch Lift	0.0	0.0
        s=s.replace("With ","");
        //System.out.println(s);

        if ( (s.contains(" To ")) || (s.contains(" to ")) || (s.contains(" TO ")) ) {
            //  System.out.println("s.contains( To )) || (s.contains( to ))");
            String firstWord = s.split(" ")[0];
          //  System.out.println(" firstWord = "+firstWord);
            if (firstWord.contains("/")) { //frac processing
                if (firstWord.contains("-")) {
                    result[0] = Double.parseDouble(firstWord.split("-")[0]) + frac(firstWord.split("-")[1]);
                } else {result[0] = frac(firstWord);  }
            } else {result[0] = Double.parseDouble(firstWord);
            //    System.out.println(" result[0] = "+result[0]);
                }

            String thirdWord = s.replace("Inch ","").split(" ")[2];
          //  System.out.println(" thirdWord = "+thirdWord);
            if (thirdWord.contains("/")) { //frac processing
                if (thirdWord.contains("-")) {
                    result[1] = Double.parseDouble(thirdWord.split("-")[0]) + frac(thirdWord.split("-")[1]);
                } else {result[1] = frac(thirdWord); }
            } else {
                //   System.out.println("thirdWord ="+thirdWord);
                result[1] = Double.parseDouble(thirdWord);
            }
            //   System.out.println(" result[1] = "+result[1]);
            return result; }

        else {
        //    System.out.println("NOT s.contains( To )) || (s.contains( to ))");
                String firstWord = s.split(" ")[0];
                if (firstWord.contains("/")) { //frac processing
                    if (firstWord.contains("-")) {
                        result[0] = Double.parseDouble(firstWord.split("-")[0]) + frac(firstWord.split("-")[1]);
                        result[1] = result[0];
                    } else {
                        result[0] = frac(firstWord);
                        result[1] = result[0];
                    }
                } else {
                    result[0] = Double.parseDouble(firstWord);
                    result[1] = result[0];
                }

                return result; }
    }

    public static double stringToDoubleLengthDecoding(String inputString) {
        // TODO: сделать смарт-инициализацию double
        double result;

        try {
            if (inputString.contains("/")) {
                if (inputString.contains("-")) {
                    result = Double.parseDouble(inputString.split("-")[0]) + frac(inputString.split("-")[1]);
                } else {
                    result = frac(inputString);
                }
            } else {
                result = Double.parseDouble(inputString);
            }
        }catch (Exception e) {
           // System.out.println("inputString =" + inputString);
           // e.printStackTrace();
            result=0;}
            return result;
    }

    public static double frac(String inputString) {
        double num,den;
        String[] frac = inputString.split("/");
        num = Double.parseDouble(frac[0]);
        if (frac.length<2) return num;
        else {
            den = Double.parseDouble(frac[1]);
            return (num / den);
        }
    }

    public static String generateCarLineString(Object o) {
        String carLineString=null;
        if (o instanceof Item)  carLineString="";
        if (o instanceof Car) carLineString= ((Car) o).getCarLineString();
        return carLineString;
    }


    public static void addToPrestaAttArrayList(ArrayList<PrestaAttribute> prestaAttArrayList, String prestaAttName, Object o, String s) {
        if (s!=null)
            prestaAttArrayList.add(new PrestaAttribute(prestaAttName,generateCarLineString(o),s));
    }

    public static void addToPrestaAttArrayList(ArrayList<PrestaAttribute> itemPrestaAttArrayList,String prestaAttName,Object o,double d) {
        if (d != -100.0)
            itemPrestaAttArrayList.add(new PrestaAttribute(prestaAttName, generateCarLineString(o), String.valueOf(d)));
    }

    public static void addToPrestaAttArrayList(ArrayList<PrestaAttribute> itemPrestaAttArrayList,String prestaAttName,Object o,
                                 ArrayList<String> stringValuesArrayList ) {
        if ( (stringValuesArrayList != null) && (stringValuesArrayList.size()>0) )
            itemPrestaAttArrayList.addAll(prestaAttributesListCreate(prestaAttName, generateCarLineString(o), stringValuesArrayList));
    }

    public static void addToPrestaAttArrayList(ArrayList<PrestaAttribute> itemPrestaAttArrayList,String prestaAttName,Object o,
                                               List<String> doubleValuesArrayList ) {
        if ( (doubleValuesArrayList != null) && (doubleValuesArrayList.size()>0) )
            itemPrestaAttArrayList.addAll(prestaAttributesListCreate(prestaAttName, generateCarLineString(o), doubleValuesArrayList));
    }

    public static List<Double> integerToDoubleList(List<Integer> list) {
        List<Double> doubleList = new ArrayList<Double>();
        for (Integer val : list) {
            doubleList.add(val.doubleValue());
        }
        return doubleList;
    }

    public static List<String> doubleToStringListForInt(List<Double> list) {
        List<String> stringList = new ArrayList<String>();
        for (double val : list) { stringList.add( String.valueOf( (int)val ) ); }
        return stringList;
    }

    public static List<String> doubleToStringListWithDecimals(List<Double> list) {
        List<String> stringList = new ArrayList<String>();
        for (double val : list) { stringList.add( String.valueOf( val ) ); }
        return stringList;
    }


}

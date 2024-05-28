package maxtrac;

import com.opencsv.CSVWriter;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import us.codecraft.xsoup.Xsoup;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class maxtrac {


    static ArrayList<String> breadcrumdsList;
    static String make;
    static List<String> modelsList;
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();
    static ArrayList<String[]> xpathStringsList = new ArrayList<>();
    static String[] elementsStringArrayOneRow = new String[11];
    static StringBuilder cellContents;
    static File input = null;
    static int filesCount = 0;

    private static ArrayList<String[]> xpathStringsListInit() {
        String[] stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//h1";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        //div[@class='item-cont']/img/@src
        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//div[@id=\"ProductBreadcrumb\"]//li";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//em[@itemprop=\"price\"]";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//div[@class=\"DetailRow RetailPrice\"]";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//span[@class=\"VariationProductSKU\"]";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//span[@class=\"VariationProductWeight\"]";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//div[@class=\"DetailRow\"]/div[@class=\"Value\"]";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//span[@itemprop=\"description\"]";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//div[@class=\"ProductWarrantyContainer prodAccordionContent\"]";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//img[@id='TinyImage_0']";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);

        stringXpathArray2 = new String[2];
        stringXpathArray2[0] = "//div[@class='TinyOuterDiv']/div/a/img";
        stringXpathArray2[1] = "0";
        xpathStringsList.add(stringXpathArray2);
        // xpathStringsList.add("//div[@id='apptab']/ul/li");
        // xpathStringsList.add("//div[@id='instructionstab']/a/@href");
        // xpathStringsList.add("//div[@class='data item content']");
        //xpathStringsList.add("");

        System.out.println("xpathStringsList.size()" + xpathStringsList.size());
        return xpathStringsList;
    }

    static String filesFolder = "D:\\savedHtml\\savedHtml_maxtracstore" + "\\";
    static String domain = "https://maxtracstore.com/";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_MaxtracStore.csv";
    static CSVWriter csvWriter;
    static Document doc = null;
    static ArrayList<ArrayList<Element>> jsoup_listOfListsOfElements;

    public static ArrayList<String> listFilesUsingJavaIO(String dir) {
        return (ArrayList<String>) Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private static CSVWriter csvWriterInit() {
        CSVWriter csvWriter = null;
        try {
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName/*,true*/));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvWriter;
    }

    private static Document docInit(File input) {
        try {
            long jsoupParseStart = System.currentTimeMillis();
            doc = Jsoup.parse(input, "UTF-8", domain);
            System.out.println((System.currentTimeMillis() - jsoupParseStart) + " mili_seconds" + " Jsoup.parsed in ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void writeAll(String[] args) throws IOException {

        List<String[]> entries = new ArrayList<>();
        entries.addAll(Collections.singleton(args));

        try (var fos = new FileOutputStream(writeAllCSV_fileName);
             var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             var writer = new CSVWriter(osw)) {
            writer.writeAll(entries);
        }
    }

    public static void main(String[] args) {

        xpathStringsList = xpathStringsListInit();
        long start = System.currentTimeMillis();
        // reading files from folder into List<String>
        ArrayList<String> filesStringsListInDir = listFilesUsingJavaIO(filesFolder);
        /*System.out.println("filesStringsListInDir " + filesStringsListInDir.size());
        System.out.println("--------");*/

        csvWriter = csvWriterInit();

        for (String fileString : filesStringsListInDir) {
          /*  for (int ti = 0; ti < 1; ti++) {

                MultithreadingDemo object
                        = new MultithreadingDemo(fileString);
                object.start();

                try {
                    object.join(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

          //  if (filesCount > 10) break;
            System.out.println("fileString = " + fileString);
            filesCount = filesCount + 1;
            input = new File(filesFolder + fileString);
            System.out.println();
            System.out.println(filesCount + "___" + fileString);
            doc = docInit(input);

            //jsoup elements list init
            jsoup_listOfListsOfElements = new ArrayList<>();
            for (String[] xpathString : xpathStringsList) {
                ArrayList<Element> jsoupElementsList = new ArrayList<>();
              /*  jsoupElementsList = Xsoup.compile(xpathString[0]).evaluate(doc).getElements();
                System.out.println("jsoupElementsList parsed size" + jsoupElementsList.size());*/
                /*if ((Integer.parseInt(xpathString[1]) == 0))
                    jsoupElementsList = Xsoup.compile(xpathString[0]).evaluate(doc).getElements();*/
                jsoupElementsList = doc.selectXpath(xpathString[0]);
               /* else
                    jsoupElementsList.add(Xsoup.compile(xpathString[0]).evaluate(doc).getElements().get(Integer.parseInt(xpathString[1])));
               */
                jsoup_listOfListsOfElements.add(jsoupElementsList);
            }
           /* ArrayList<Element> jsoupElementsList9 = Xsoup.compile("//h1").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList9.size(); i++) {
                //  System.out.println("------------");
                //  System.out.println(jsoupElementsList9.get(i).text());
                //  System.out.println("------------");
            }
            listOfListsOfElements.add(jsoupElementsList9);
            ArrayList<Element> jsoupElementsList0 = Xsoup.compile("//span[@class='price']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList0.size(); i++) {
                // System.out.println(jsoupElementsList0.get(i).text());
                // System.out.println("-----");
            }
            listOfListsOfElements.add(jsoupElementsList0);

            ArrayList<Element> jsoupElementsList1 = Xsoup.compile("//span[contains(@class, 'amstockstatus')]").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList1.size(); i++) {
                //   System.out.println(jsoupElementsList1.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList1);
            ArrayList<Element> jsoupElementsList2 = Xsoup.compile("//div[@itemprop='sku']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList2.size(); i++) {
                //  System.out.println(jsoupElementsList2.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList2);
            ArrayList<Element> jsoupElementsList3 = Xsoup.compile("//img[starts-with(@src,'https://trail-gear.com/media/catalog/')]").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList3.size(); i++) {
                //   System.out.println(jsoupElementsList3.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList3);
            ArrayList<Element> jsoupElementsList4 = Xsoup.compile("//div[@class='product attribute description']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList4.size(); i++) {
                //  System.out.println(jsoupElementsList4.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList4);
            //
            ////h2[contains(text(),'Specifications')]
            ArrayList<Element> jsoupElementsList5 = Xsoup.compile("//div[@id='specstab']/ul/li").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList5.size(); i++) {
                //   System.out.println(jsoupElementsList5.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList5);
            //[contains(text(),'Application Data')]
            ArrayList<Element> jsoupElementsList6 = Xsoup.compile("//div[@id='apptab']/ul/li").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList6.size(); i++) {
                //   System.out.println(jsoupElementsList6.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList6);
            ArrayList<Element> jsoupElementsList7 = Xsoup.compile("//div[@id='instructionstab']/a/@href").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList7.size(); i++) {
                //   System.out.println(jsoupElementsList7.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList7);
            ArrayList<Element> jsoupElementsList8 = Xsoup.compile("//div[@class='data item content']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList8.size(); i++) {
                // System.out.println(jsoupElementsList8.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList8);*/
            String[] elementsStringArrayOneRow = new String[jsoup_listOfListsOfElements.size() + 3];
            //page link
            elementsStringArrayOneRow[0] = domain + fileString;
            for (int i = 0; i < jsoup_listOfListsOfElements.size(); i++) {
                cellContents = new StringBuilder();
                for (Element element : jsoup_listOfListsOfElements.get(i)) {
                    // img links
                    if (element.attr("src").length() > 0)
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    //if (cellContents.length()==0)
                    cellContents.append(element.text()).append(System.getProperty("line.separator"));


                    }

                //TODO
                // 1= second coloumn
                if (i == 1) {
                    breadcrumdsList = new ArrayList<>();
                    String[] cellContentsStringArray = cellContents.toString().split(System.getProperty("line.separator"));
                    System.out.println("cellContentsStringArray.length " + cellContentsStringArray.length);
                    breadcrumdsList.addAll(Arrays.asList(cellContentsStringArray));
                    System.out.println("breadcrumdsList "+breadcrumdsList);
                    System.out.println(breadcrumdsList.size());
                    for (String s : breadcrumdsList) {
                        if ((!s.contains("Home")) && (!s.contains(" ")) ) make = s;
                    }
                    int indexOfMake = breadcrumdsList.indexOf(make);
                    if (indexOfMake>0) {
                        System.out.println("indexOfMake = " + indexOfMake);
                        System.out.println("breadcrumdsList.size() = " + breadcrumdsList.size());
                        modelsList = breadcrumdsList.subList(indexOfMake+1, breadcrumdsList.size() - 1);
                        System.out.println("modelsList  = " + modelsList);
                    }
                    /*if ((i == 1) && (element.attr("src").length() > 0)) {
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }*/
                    //  else cellContents.append("_").append(System.getProperty("line.separator"));

                    /*if (((i != 1) *//*|| (i != 8)*//*) *//*&& (element.text().length() > 0)*//*) {
                        //  System.out.println(element.text());
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }*/
                    //  if ( (i!=4) && (element.text().length() == 0) ) cellContents.append("_").append(System.getProperty("line.separator"));

                   /* if ((i == 8) && (element.attr("href").length() > 0)) {
                        //  System.out.println("+++ 8th_element href =" + element.attr("href"));
                        cellContents = new StringBuilder();
                        cellContents.append(element.attr("href")).append(System.getProperty("line.separator"));
                    }*/
                }
                //TODO: img links size and qty replacement
                elementsStringArrayOneRow[i + 1] = cellContents.toString().replace(".100.100.jpg?c=2", ".500.500.jpg")
                        .replace(".100.100.png?c=2", ".500.500.png");
            }
            elementsStringArrayOneRow[12] = make;
            StringBuilder modelStringBuilder = null;
            for (String model:modelsList) {
                 modelStringBuilder = new StringBuilder();
                modelStringBuilder.append(model).append(System.getProperty("line.separator"));
            }
            if (!(modelStringBuilder ==null))
            elementsStringArrayOneRow[13] = modelStringBuilder.toString();
            arrayListOfAllStringsForCSV.add(elementsStringArrayOneRow);
            //  System.out.println("Added line: " + Arrays.toString(elementsStringArrayOneRow));
            //  if (filesCount > 10) break;
        }

        // System.out.println("arrayListOfAllStringsForCSV SIZE= " + arrayListOfAllStringsForCSV.size());
        System.out.println("CSV writing STARTED " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");

/*
        for (String[] stringArray:arrayListOfAllStringsForCSV ) {
            for (int i = 0; i <stringArray.length; i++) {
                System.out.print(stringArray[i] + " /// ");
            }
            System.out.println("----------");
        }
*/

        csvWriter.writeAll(arrayListOfAllStringsForCSV, false);
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* try {
            writeAll(elementsStringArray);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //generate 1row for each item + /n


        //write all rows to csv at once

       /* for(String[] elementsStringArrayOneRow:arrayListOfAllStringsForCSV)
            try {
                writeAll(elementsStringArrayOneRow);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        System.out.println("CSV writing finished " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");
    }


}

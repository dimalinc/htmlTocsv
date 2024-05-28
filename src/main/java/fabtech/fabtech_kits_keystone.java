package fabtech;

import com.opencsv.CSVWriter;
import elements.XpathElementContainer;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class fabtech_kits_keystone {

    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();
    static ArrayList<String> xpathStringsList = new ArrayList<>();
    static String[] elementsStringArrayOneRow = new String[11];
    static StringBuilder cellContents;
    static File input = null;
    static int filesCount = 0;

    private static ArrayList<String> xpathStringsListInit() {
        // xpathStringsList.add(" ");
        xpathStringsList.add("//h1[@class='title']");
        xpathStringsList.add("//span[@class='description']");
        xpathStringsList.add("//span[@class='title']"); // carline
        xpathStringsList.add("//div[@class='product-detail-attributes']");
        xpathStringsList.add("//div[@class='product-detail-description']");
        xpathStringsList.add("//div[@class='product-detail-features']");
        xpathStringsList.add("//ul[@class='product-detail-application-summaries']");
        xpathStringsList.add("//li[@class='product-detail-application-summary']");
        xpathStringsList.add("//li[contains(@class, 'application')]/a");  // color - engines, cabins - why breaks?

        xpathStringsList.add("//li[@class='application']");   // Year+Make+Model+position, lift
        xpathStringsList.add("//li[@class='application-attribute']");   // position, lift
        xpathStringsList.add("//li[@class='application-footnote']");
        xpathStringsList.add("//div[@class='product-detail-primary-identifier']/span[@class='row-value']");
        xpathStringsList.add("//div[@class='product-detail-documents']/ul/li/a/@href");

        xpathStringsList.add("//div[@class='galleria-image']/img/@src");
        /*xpathStringsList.add("//div[@class='wsm-prod-summary']");
        xpathStringsList.add("//span[@class='wsm-cat-ship-remarks-value']");
        xpathStringsList.add("//div[@itemprop='description']");
        xpathStringsList.add("//div[@id='wsm-prod-tab-details']/*");
        xpathStringsList.add("//li[@class='wsm-file-pdf-small']/a/@href");
        xpathStringsList.add("//img[@itemprop='image']/@src");
        xpathStringsList.add("//img[@class='wsm_product_thumb_zoom']/@src");*/
        //xpathStringsList.add("");
        return xpathStringsList;
    }

    static String brand = "timberen_KEYSTON";
    static String filesFolder = "D:\\savedHtml\\savedHtml_timberen_KEYSTONE" + "\\";
    /*static String brand = "Fabtech_kits_KEYSTONE";
    static String filesFolder = "D:\\savedHtml\\savedHtml_fabtech_kits_KEYSTONE" + "\\";*/
    static String domain = "";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_" + brand;
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
            //  csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName/*,true*/));
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName + ".csv"), '^');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvWriter;
    }

    private static Document docInit(File input) {
        try {
            long jsoupParseStart = System.currentTimeMillis();
            doc = Jsoup.parse(input, "UTF-8", domain);
            System.out.println("Jsoup.parsed in " + (System.currentTimeMillis() - jsoupParseStart) + " mili_seconds");
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


        int n = 0;
        for (String fileString : filesStringsListInDir) {
            n++;

            System.out.println("fileString = " + fileString);
            filesCount = filesCount + 1;
            input = new File(filesFolder + fileString);
            System.out.println(filesCount + "___" + fileString);
            doc = docInit(input);

            //jsoup elements list init
            jsoup_listOfListsOfElements = new ArrayList<>();
            /*for (String xpathString : xpathStringsList) {
                ArrayList<Element> jsoupElementsList=doc.selectXpath(xpathString);
                jsoup_listOfListsOfElements.add(jsoupElementsList);
            }*/

            /*String[] elementsStringArrayOneRow = new String[jsoup_listOfListsOfElements.size()+1];
            //page link
            elementsStringArrayOneRow[0] = (domain +"/" +fileString).replace(".html","");*/
            /*for (int i = 0; i < jsoup_listOfListsOfElements.size(); i++) {
                cellContents = new StringBuilder();
                for (Element element : jsoup_listOfListsOfElements.get(i)) {
                    cellContents.append(element.text()).append(System.getProperty("line.separator"));

                   *//* if ((i == 4) && (element.attr("src").length() > 0)) {
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }

                    if (((i != 4) || (i != 8)) *//**//*&& (element.text().length() > 0)*//**//*) {
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }*//*

                    if ((i == 5) && (element.attr("href").length() > 0)) {
                        //      System.out.println("+++ 5th_element href =" + element.attr("href"));
                        cellContents = new StringBuilder();
                        cellContents.append(domain).append(element.attr("href")).append(System.getProperty("line.separator"));
                    }
                    if ((i == 6) && (element.attr("src").length() > 0)) {
                        //      System.out.println("+++ 6th_element src =" + element.attr("src"));
                        //   cellContents = new StringBuilder();
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }
                    if ((i == 7) && (element.attr("src").length() > 0)) {
                        //    System.out.println("+++ 7th_element src =" + element.attr("src"));
                        //  cellContents = new StringBuilder();
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }
                }
                elementsStringArrayOneRow[i + 1] = cellContents.toString();
            }*/

            String[] elementsStringArrayOneRow = new String[xpathStringsList.size() + 1];
            //page link
            elementsStringArrayOneRow[0] = (domain + "/" + fileString).replace(".html", "");
            int i = 1;
            for (String xpath : xpathStringsList) {
                XpathElementContainer xpathElementContainer = new XpathElementContainer(xpath, doc, i);
                String cell = xpathElementContainer.getCsvCellValue().replace("\n", "");
                elementsStringArrayOneRow[i] = cell;
                i++;
            }
            arrayListOfAllStringsForCSV.add(elementsStringArrayOneRow);
            //  System.out.println("Added line: " + Arrays.toString(elementsStringArrayOneRow));

            // car lines extracting
            String carLines = elementsStringArrayOneRow[3];
            String[] carLine = carLines.split("[&]*[&]");
            for (String eachCarLine : carLine) {
                eachCarLine.replaceAll("[\"]", "");
                if (!eachCarLine.equals("CHAR(10)")) {
                    String[] eachCarLineStringArray = new String[10];
                    eachCarLineStringArray[0] = "";
                    eachCarLineStringArray[1] = "";
                    eachCarLineStringArray[2] = "";
                    if (!eachCarLine.contains("=")) eachCarLine = "=" + eachCarLine;
                    eachCarLineStringArray[3] = eachCarLine;
                    if (eachCarLine.length() > 1) { // Years, make, Model
                        eachCarLineStringArray[4] = eachCarLine.substring(2, 6); //YearStart
                        if (eachCarLine.length() > 11)
                            eachCarLineStringArray[5] = eachCarLine.substring(7, 12); //YearFinish
                        else eachCarLineStringArray[5] = eachCarLine.substring(7);

                        eachCarLineStringArray[6] = eachCarLine.split(" ")[1]; //Make
                        StringBuilder stringBuilder = new StringBuilder(); //Model
                        for (int j = 2; j < eachCarLine.split(" ").length; j++) {
                            stringBuilder.append(eachCarLine.split(" ")[j]).append(" ");
                        }
                        if (stringBuilder.length()>1)
                        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                        eachCarLineStringArray[7] = stringBuilder.toString(); //Model
                    }
                    // drive
                    String carAttributes = elementsStringArrayOneRow[11];
                    String[] carAttributeStringsArray = carAttributes.split("[&]*[&]");
                    ArrayList<String> carAttriburesStringsArrayList = new ArrayList<>(Arrays.asList(carAttributeStringsArray));
                    for (String s : carAttriburesStringsArrayList) {
                        // System.out.println(s);
                        String containsString = eachCarLineStringArray[5] + eachCarLineStringArray[6] + " " + eachCarLineStringArray[7];
                        System.out.println("containsString = " + containsString);
                        if (s.contains(containsString))
                            if (s.contains("Wheel Drive")) {
                                s = s.substring(0, s.indexOf(" Wheel Drive"));
                                // eachCarLineStringArray[8] = s.substring(s.replace(" Wheel Drive", "").lastIndexOf(" ") + 1, s.length() - 1);
                                eachCarLineStringArray[8] = s.substring(s.lastIndexOf(" ") + 1, s.length());
                            }
                        if (s.contains("Position On Vehicle: ")) {
                            //search for Position++ - search for ; ?
                            // s= s.substring(s.indexOf("Position On Vehicle: ")+21,s.indexOf(";",s.indexOf("Position On Vehicle: ")+23));
                            s = s.substring(s.indexOf("Position On Vehicle: ") + 21/*,s.indexOf(";",s.indexOf("Position On Vehicle: ")+23)*/);
                            eachCarLineStringArray[9] = s.substring(s.lastIndexOf(" ") + 1, s.length());
                        }
                        System.out.println("eachCarLineStringArray[9] =" + eachCarLineStringArray[9]);


                    }

                    arrayListOfAllStringsForCSV.add(eachCarLineStringArray);
                }
            }

            if (n > 50) break;

        }

        // System.out.println("arrayListOfAllStringsForCSV SIZE= " + arrayListOfAllStringsForCSV.size());
        System.out.println(" writing STARTED " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");

        /*for (String[] stringArray:arrayListOfAllStringsForCSV ) {
            for (int i = 0; i <stringArray.length; i++) {
                System.out.print(stringArray[i] + " /// ");
            }
            System.out.println("----------");
        }*/

        // write to EXCEL
        try {
            writeExcel(arrayListOfAllStringsForCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // write to CSV
        csvWriter = csvWriterInit();
        csvWriter.writeAll(arrayListOfAllStringsForCSV, true);
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

        System.out.println("Table writing finished " + (System.currentTimeMillis() - start) / 1000 + " seconds"
                + "__ OR __" + ((System.currentTimeMillis() - start) / 1000 / 60) + "minutes");
    }

    public static void writeExcel(List<String[]> allLines) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = /*path.substring(0, path.length() - 1) +*/ writeAllCSV_fileName + ".xlsx";

        try (OutputStream os = Files.newOutputStream(Paths.get(fileLocation)); Workbook wb = new Workbook(os, "MyApplication", "1.0")) {
            Worksheet ws = wb.newWorksheet("Sheet 1");
            ws.width(0, 25);
            ws.width(1, 15);
            // header
            /*ws.range(0, 0, 0, 1).style().fontName("Arial").fontSize(16).bold().fillColor("3366FF").set();
            ws.value(0, 0, "Name");
            ws.value(0, 1, "Age");*/

            {
                StringBuilder sb = new StringBuilder(90024);
                int rowNumber = 0;
                for (String[] line : allLines) {
                    rowNumber++;
                    for (int i = 0; i < line.length; i++) {
                        ws.value(rowNumber, i, line[i]);
                    }
                    sb.setLength(0);
                }

            }
            // writing test line content
            /*ws.range(2, 0, 2, 1).style().wrapText(true).set();
            ws.value(2, 0, "John Smith");
            ws.value(2, 1, 20L);*/
        }
    }

}

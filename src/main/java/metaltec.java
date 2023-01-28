
import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.xsoup.XElements;
import us.codecraft.xsoup.Xsoup;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;

public class metaltec {

    // getting list of files from folder
    public static Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static void writeAll(String[] args) throws IOException {

        /*String[] items1 = {"book", "coin", "pencil"};
        String[] items2 = {"pen", "chair", "lamp"};
        String[] items3 = {"ball", "bowl", "spectacles"};*/

        List<String[]> entries = new ArrayList<>();
        entries.addAll(Collections.singleton(args));
        /*entries.add(items1);
        entries.add(items2);
        entries.add(items3);*/


        try (var fos = new FileOutputStream(writeAllCSV_fileName);
             var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             var writer = new CSVWriter(osw)) {

            writer.writeAll(entries);
        }
    }

    public static Element getElementByXPath(String xpath, Document doc) {

        String[] tags = xpath.split("/");
        Element clt = doc.body();

        int index;
        int divc = 0;
        boolean done = false;
        for (String tag : tags) {
            if (tag.startsWith("div"))
                divc++;//  ww w  .java  2 s. c o m

            index = 0;
            int bindex = tag.indexOf('[');
            int eindex = tag.indexOf(']');
            if (bindex != -1) {
                index = Integer.parseInt(tag.substring(bindex + 1, eindex)) - 1;
                tag = tag.substring(0, bindex);
            }

            try {
                clt = clt.select(">" + tag).get(index);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(xpath + " is not valid.");
                clt = null;
                break;
            }

            if (tag.equals("table"))
                clt = clt.select(">tbody").get(0);

        }

        return clt;
    }

    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll_METALTEC.csv";
    static String metaltec_html_folder = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedHtml_METALTEC\\";
    private static URI uri = URI.create("https://metaltech4x4.com/ford-bronco/");
    private static final String FILE_URL_SAVE_WITH_STREAM =
            "https://metaltech4x4.com/ford-bronco/";
    private static final String FILE_NAME_SAVE_FILE_WITH_STREAM = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedFileWstream.html";
    private static final String JSOUP_GET_URL =
            "https://metaltech4x4.com/metal-tech-5th-gen-4runner-trd-pro-2014-2019-fortress-front-bumper/";
    private static final String FILE_NAME_RESPONSE_BODY = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedResponse.html";
    private static final String FILE_NAME_JSOUP_DOC_HTML = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\JSOUP_DOC_HTML.html";

    public static void main(String[] args) {

        long start = System.currentTimeMillis();


       /* HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // file save via httpclient
        String responseBody = response.body();
        // System.out.println(responseBody);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_RESPONSE_BODY));
            writer.write(responseBody);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // file save via stream w/o httpclient
      /* try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL_SAVE_WITH_STREAM).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME_SAVE_FILE_WITH_STREAM)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        /*Document doc = null;
        try {
            doc = Jsoup.connect(JSOUP_GET_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        // reading links from file
       /* File linksFile = new File("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\links.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\linksMetaltechBackup.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String currentLine = "1";
        ArrayList<String> linksList = new ArrayList<>();
        try {
          //  System.out.println(currentLine);
            while ((currentLine=reader.readLine())!=null) {
                linksList.add(currentLine);}
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(linksList);*/

        // file save via stream w/o httpclient
     /*   for(String linkString:linksList) {

            try (BufferedInputStream in = new BufferedInputStream(new URL(linkString).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(metaltec_html_folder
                         +linkString.split("/")[linkString.split("/").length-1]*//*+".html"*//*);
             )
            {
                System.out.println(metaltec_html_folder
                        +linkString.split("/")[linkString.split("/").length-1]) ;
                byte dataBuffer[] = new byte[8096];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 8096)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/



        // reading files from folder into Set<String>
        Set<String> filesStringsListInDir = listFilesUsingJavaIO(metaltec_html_folder);
        System.out.println(filesStringsListInDir);
        System.out.println("--------");

        ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<>();
        int n=0;
        for (String fileString : filesStringsListInDir) {
            n = n + 1;
            File input = new File(metaltec_html_folder + fileString);
            Document doc = null;
            try {
                long jsoupParseStart = System.currentTimeMillis();
                doc = Jsoup.parse(input, "UTF-8", "https://metaltech4x4.com/");
                System.out.println(n + " " + fileString + " Jsoup.parsed in " + (System.currentTimeMillis() - jsoupParseStart) + " mili_seconds");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements links = doc.select("a");
            for (Element element : links) {
                // System.out.println(element.html());
                // System.out.println(element.attr("href"));
                //System.out.println(element.attr("src"));
            }

            // get Jsoup element by xpath = XSoup
            // XElements xElements =  Xsoup.compile("//*/h1").evaluate(doc);

            ArrayList<ArrayList<Element>> listOfListsOfElements = new ArrayList<>();

            ArrayList<Element> jsoupElementsList0 = Xsoup.compile("//*[@class='breadcrumb-label']/@text").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList0.size(); i++) {
                // System.out.println(jsoupElementsList0.get(i).text());
                // System.out.println("-----");
            }
            listOfListsOfElements.add(jsoupElementsList0);

            ArrayList<Element> jsoupElementsList1 = Xsoup.compile("//*[@id='tab-description']/@text").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList1.size(); i++) {
                //   System.out.println(jsoupElementsList1.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList1);
            ArrayList<Element> jsoupElementsList2 = Xsoup.compile("//*/h1/@text").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList2.size(); i++) {
                //  System.out.println(jsoupElementsList2.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList2);
            ArrayList<Element> jsoupElementsList3 = Xsoup.compile("//*/h2[@class='productView-brand']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList3.size(); i++) {
                //   System.out.println(jsoupElementsList3.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList3);
            //span[class='price-now-label']/span[@class='price price--withoutTax']
            //*[@class='price price--withoutTax']/@text
            ArrayList<Element> jsoupElementsList4 = Xsoup.compile("//span[@class='price price--withoutTax']").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList4.size(); i++) {
                //  System.out.println(jsoupElementsList4.get(i).text());
            }
            // TODO: first price element
            ArrayList<Element> priceArrayListOfFirsTelemt =  new ArrayList<Element>();
            priceArrayListOfFirsTelemt.add(jsoupElementsList4.get(0));
            listOfListsOfElements.add(priceArrayListOfFirsTelemt);
            ArrayList<Element> jsoupElementsList5 = Xsoup.compile("//*[@itemprop='sku']/@text").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList5.size(); i++) {
                //   System.out.println(jsoupElementsList5.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList5);
            ArrayList<Element> jsoupElementsList6 = Xsoup.compile("//*/a/img[contains(@src,'products')]/@src").evaluate(doc).getElements();
            //System.out.println("jsoupElementsList6.size() = " + jsoupElementsList6.size());
            for (int i = 0; i < jsoupElementsList6.size(); i++) {
             //     System.out.println(jsoupElementsList6.get(i).attr("src"));
            }
           // System.out.println("-----");
            listOfListsOfElements.add(jsoupElementsList6);
            ArrayList<Element> jsoupElementsList7 = Xsoup.compile("//*[@id='tab-description']/@text").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList7.size(); i++) {
                //  System.out.println(jsoupElementsList7.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList7);
            ArrayList<Element> jsoupElementsList8 = Xsoup.compile("//*[@id='tab-additional-information']/@text").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList8.size(); i++) {
                // System.out.println(jsoupElementsList8.get(i).text());
            }
            listOfListsOfElements.add(jsoupElementsList8);
            ArrayList<Element> jsoupElementsList9 = Xsoup.compile("//*[@class='form']/@text").evaluate(doc).getElements();
            for (int i = 0; i < jsoupElementsList9.size(); i++) {
          //  System.out.println("------------");
          //  System.out.println(jsoupElementsList9.get(i).text());
          //  System.out.println("------------");
            }
            listOfListsOfElements.add(jsoupElementsList9);


        /* for (int i = 0; i <jsoupElementsList1.size() ; i++) {
            System.out.println(jsoupElementsList1.size());
            System.out.println(jsoupElementsList1.get(i).text());
        }*/
//*[@

       /* ArrayList<Element> jsoupElementsList = Xsoup.compile("//*[@class="+'\"'+"woocommerce-breadcrumb"+'\"'+"]").evaluate(doc).getElements();
        System.out.println(jsoupElementsList.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList2 = Xsoup.compile("//*[@class=\"product_title entry-title\"]/text()").evaluate(doc).getElements();
        System.out.println(jsoupElementsList2.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList3 = Xsoup.compile("//bdi").evaluate(doc).getElements();
        System.out.println(jsoupElementsList3.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList4 = Xsoup.compile("//*[@class=\"sku\"]").evaluate(doc).getElements();
        System.out.println(jsoupElementsList4.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList5 = Xsoup.compile("//*[@class=\"woocommerce-product-details__short-description\"]").evaluate(doc).getElements();
        System.out.println(jsoupElementsList5.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList6 = Xsoup.compile("//*[@class=\"woocommerce-product-gallery__image\"]/a/@href").evaluate(doc).getElements();
        System.out.println(jsoupElementsList6.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList7 = Xsoup.compile("//*[@id=\"tab-description\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList7.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList8 = Xsoup.compile("//*[@id=\"tab-fitment\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList8.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList9 = Xsoup.compile("//*[@id=\"tab-optional-add-ons\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList9.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList10 = Xsoup.compile("//*[@id=\"tab-instructions-info\"]/h2/following-sibling").evaluate(doc).getElements();
        System.out.println(jsoupElementsList10.size()*//*.get(0).text()*//*);
        ArrayList<Element> jsoupElementsList11 = Xsoup.compile("//*[@id=\"tab-instructions-info\"]/h2/following-sibling/a/@href").evaluate(doc).getElements();
        System.out.println(jsoupElementsList11.size()*//*.get(0).text()*//*);*/

            // saving file via Jsoup.get
       /* String docHtml = doc.html();
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_JSOUP_DOC_HTML));
            writer.write(docHtml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

            // doc = Jsoup.parse(responseBody);

            // write one row - string array to csv
            // String[] elementsStringArray = new String[jsoupElementsList9.size()];

// для каждого элемента из подсписка получать текст
            String[] elementsStringArrayOneRow = new String[11];
            //page link
            elementsStringArrayOneRow[0]="https://metaltech4x4.com/"+fileString;
            for (int i = 0; i < listOfListsOfElements.size(); i++) {
                StringBuilder cellContents = new StringBuilder();
                for (Element element : listOfListsOfElements.get(i)) {
                    // img links
                    if ( (i==6) && (element.attr("src").length()>0) )  {
                       // System.out.println(element.attr("src"));
                        cellContents.append(element.attr("src")).append(System.getProperty("line.separator"));
                    }

                    if ( (i!=6) && (element.text().length() > 0) ) {
                       // System.out.println(element.text());
                        cellContents.append(element.text()).append(System.getProperty("line.separator"));
                    }
                   //   else if ( /*(i!=6) &&*/ (element.text().length() == 0) ) cellContents.append("_").append(System.getProperty("line.separator"));
                }
                elementsStringArrayOneRow[i+1] = cellContents.toString();
                // remove 2 last empty rows
                elementsStringArrayOneRow[i+1]=elementsStringArrayOneRow[i+1].replace(System.getProperty("line.separator")+System.getProperty("line.separator"),"");
            }
            arrayListOfAllStringsForCSV.add(elementsStringArrayOneRow);
          //  System.out.println("Added line: " + Arrays.toString(elementsStringArrayOneRow));
            if (n>10) break;
        }

        CSVWriter csvWriter = null;
        try {
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Create record

       /* for (String[] stringArray : arrayListOfAllStringsForCSV) {
            //Write the record to file
            csvWriter.writeNext(stringArray, false);
        }*/
        csvWriter.writeAll(arrayListOfAllStringsForCSV, false);
        //close the writer
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

        System.out.println((System.currentTimeMillis() - start)/ 1000 + " seconds");

    }
}


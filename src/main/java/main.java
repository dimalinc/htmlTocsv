
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

public class main {

    public static void writeAll(String[] args) throws IOException {

        /*String[] items1 = {"book", "coin", "pencil"};
        String[] items2 = {"pen", "chair", "lamp"};
        String[] items3 = {"ball", "bowl", "spectacles"};*/

        List<String[]> entries = new ArrayList<>();
        entries.addAll(Collections.singleton(args));
        /*entries.add(items1);
        entries.add(items2);
        entries.add(items3);*/

        String fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\writeAll.csv";

        try (var fos = new FileOutputStream(fileName);
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

    private static URI uri = URI.create("https://metaltech4x4.com/ford-bronco/");
    private static final String FILE_URL_SAVE_WITH_STREAM =
            "https://metaltech4x4.com/ford-bronco/";
    private static final String FILE_NAME_SAVE_FILE_WITH_STREAM = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedFileWstream.html";
    private static final String JSOUP_GET_URL =
            "https://metaltech4x4.com/metal-tech-5th-gen-4runner-trd-pro-2014-2019-fortress-front-bumper/";
    private static final String FILE_NAME_RESPONSE_BODY = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedResponse.html";
    private static final String FILE_NAME_JSOUP_DOC_HTML = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\JSOUP_DOC_HTML.html";

    public static void main(String[] args) {

        HttpClient client = HttpClient.newHttpClient();
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
        }

        // file save via stream w/o httpclient
        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL_SAVE_WITH_STREAM).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME_SAVE_FILE_WITH_STREAM)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Document doc = null;
        try {
            doc = Jsoup.connect(JSOUP_GET_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        // reading links from file
        File linksFile = new File("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\links.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\links.txt"));
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
        System.out.println(linksList);


        File input = new File("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\JSOUP_DOC_HTML.html");
        Document doc = null;
        try {
            doc = Jsoup.parse(input, "UTF-8", "https://metaltech4x4.com/");
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

        ArrayList<Element> jsoupElementsList1 = Xsoup.compile("//*[@id='tab-description']").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList1.size(); i++) {
            //   System.out.println(jsoupElementsList1.get(i).text());
        }
        ArrayList<Element> jsoupElementsList2 = Xsoup.compile("//*/h1").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList2.size(); i++) {
            //  System.out.println(jsoupElementsList2.get(i).text());
        }
        ArrayList<Element> jsoupElementsList3 = Xsoup.compile("//*/h2").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList3.size(); i++) {
            //   System.out.println(jsoupElementsList3.get(i).text());
        }
        ArrayList<Element> jsoupElementsList4 = Xsoup.compile("//*[@class='price price--withoutTax']").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList4.size(); i++) {
            //  System.out.println(jsoupElementsList4.get(i).text());
        }
        ArrayList<Element> jsoupElementsList5 = Xsoup.compile("//*[@itemprop='sku']").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList5.size(); i++) {
            //   System.out.println(jsoupElementsList5.get(i).text());
        }
        ArrayList<Element> jsoupElementsList6 = Xsoup.compile("//*/a/img/@src").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList6.size(); i++) {
            //  System.out.println(jsoupElementsList6.get(i).text());
        }
        ArrayList<Element> jsoupElementsList7 = Xsoup.compile("//*[@id='tab-description']").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList7.size(); i++) {
            //  System.out.println(jsoupElementsList7.get(i).text());
        }
        ArrayList<Element> jsoupElementsList8 = Xsoup.compile("//*[@id='tab-additional-information']").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList8.size(); i++) {
            // System.out.println(jsoupElementsList8.get(i).text());
        }
        ArrayList<Element> jsoupElementsList9 = Xsoup.compile("//*[@class='form']").evaluate(doc).getElements();
        for (int i = 0; i < jsoupElementsList9.size(); i++) {
          /*  System.out.println("------------");
            System.out.println(jsoupElementsList9.get(i).text());
            System.out.println("------------");*/
        }


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
        String[] elementsStringArray = new String[jsoupElementsList9.size()];
        for (int i = 0; i < jsoupElementsList9.size(); i++) {
            elementsStringArray[i] = jsoupElementsList9.get(i).text();
        }
        try {
            writeAll(elementsStringArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

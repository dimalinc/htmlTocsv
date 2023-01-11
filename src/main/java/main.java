
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.time.*;

import static java.time.temporal.ChronoUnit.SECONDS;

public class main {

    private static URI uri = URI.create("https://metaltech4x4.com/ford-bronco/");
    private static final String FILE_URL_SAVE_WITH_STREAM =
            "https://metaltech4x4.com/ford-bronco/";
    private static final String FILE_NAME_SAVE_FILE_WITH_STREAM = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\savedFileWstream.html";
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
        System.out.println(responseBody);
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

        // saving file via Jsoup.get
        String blogUrl =
                "https://metaltech4x4.com/metal-tech-5th-gen-4runner-trd-pro-2014-2019-fortress-front-bumper/";
        Document doc = null;
        try {
            doc = Jsoup.connect(blogUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String docHtml = doc.html();
        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME_JSOUP_DOC_HTML));
            writer.write(docHtml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // doc = Jsoup.parse(responseBody);

    }
}

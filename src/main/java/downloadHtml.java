import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class downloadHtml {

    public static void main(String[] args) {
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

        for(String linkString:linksList) {
            // file save via stream w/o httpclient

            try (BufferedInputStream in = new BufferedInputStream(new URL(linkString).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\"+"savedHtml\\"
                         +linkString.split("/")[linkString.split("/").length-1]+".html");
            )
            {
                System.out.println("C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\"+"savedHtml\\"
                        +linkString.split("/")[linkString.split("/").length-1].replace('|','_')  ) ;
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

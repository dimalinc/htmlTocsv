package teraflex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class teraflex_main {
    static String brand = "TERAFLEX";
    static String brandForXls = "TERAFLEX";
    static String dir = "D:\\savedHtml\\savedHtml_Teraflex" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String domain = "https://teraflex.com/";
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();





    public static void main(String inputBrand, String inputBrandForXls,String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls =inputBrandForXls; dir=inputDir; writeAllCSV_fileName=inputWriteAllCSV_fileName; domain=inputDomain;

        Long start = System.currentTimeMillis();

        filesArrayList = listFilesUsingJavaIO(dir);
        System.out.println(filesArrayList);
        System.out.println("filesArrayList.size() = "+filesArrayList.size());

        int n = 1;
        for (String fileString : filesArrayList) {

            if ( (inputFilesNumberStart!=0) && (n<inputFilesNumberStart) ) { n++; continue; }

            Long startItem = System.currentTimeMillis();
            File newFile= new File(dir + fileString);
            System.out.println(newFile.getName());
            Item_Teraflex teraflexItem = new Item_Teraflex(newFile,domain);
            ArrayList<String> listingStringsArrayList = new ArrayList<>();

            listingStringsArrayList.add(teraflexItem.urlString);
            listingStringsArrayList.add(teraflexItem.name);
            listingStringsArrayList.add(teraflexItem.sku);
            listingStringsArrayList.add(teraflexItem.description);
            listingStringsArrayList.add(teraflexItem.features);
            listingStringsArrayList.add(teraflexItem.fitment);
            listingStringsArrayList.add(teraflexItem.productNotes);
            listingStringsArrayList.add(teraflexItem.price);
            listingStringsArrayList.add("");
            listingStringsArrayList.add(teraflexItem.bigImgLink);
            listingStringsArrayList.add("");
            listingStringsArrayList.add(teraflexItem.smallImgLink);
            listingStringsArrayList.add("");
            listingStringsArrayList.add(teraflexItem.galleryPlaceholderImgLink);

            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ + "  processed for mS=" + ((finishItem - startItem) ));
            arrayListOfAllStringsForCSV.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));

            if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }
        // write to EXCEL
        try { writeExcel(arrayListOfAllStringsForCSV, "Teraflex_"+brand); }
        catch (IOException e) { e.printStackTrace(); }

        }






}

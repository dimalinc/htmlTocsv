package crown;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class Crown_main {
    static String brand ;
    static String brandForXls ;
    static String dir ;
    static String writeAllCSV_fileName ;
    static String domain;
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();





    public static void main(String inputBrand, String inputBrandForXls,String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls =inputBrandForXls; dir=inputDir; writeAllCSV_fileName=inputWriteAllCSV_fileName; domain=inputDomain;

        writeAllCSV_fileName = "D:\\savedHtml\\savedHtml_Camburg.com_options" + brand;
        domain = "https://camburg.com/";

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
            Item_Crown crownItem = new Item_Crown(newFile,domain);
            ArrayList<String> listingStringsArrayList = new ArrayList<>();
            listingStringsArrayList.add(crownItem.urlString);
            listingStringsArrayList.add(crownItem.urlStringSKU);
            listingStringsArrayList.add(crownItem.title);
            listingStringsArrayList.add(crownItem.breadcrumbs);
            listingStringsArrayList.add(crownItem.imgLinks);
            listingStringsArrayList.add(crownItem.imgLinksURL);

            listingStringsArrayList.add(crownItem.specs);
            listingStringsArrayList.add(crownItem.specsHTML);
            listingStringsArrayList.add(crownItem.application);
            listingStringsArrayList.add(crownItem.applicationHTML);

           // System.out.println("Price = "+ crownItem.price);
            listingStringsArrayList.add(crownItem.sku);




            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ + "  processed for mS=" + ((finishItem - startItem) ));
            arrayListOfAllStringsForCSV.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));

            if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }
        // write to EXCEL
        try { writeExcel(arrayListOfAllStringsForCSV, "_"+brand); }
        catch (IOException e) { e.printStackTrace(); }

        }






}

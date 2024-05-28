package motonsuspensionusa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class moton_main {
    static String brand = "Moton";
    static String brandForXls = "Moton";
    static String dir = "D:\\savedHtml\\motonsuspensionusa" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String domain = "https://motonsuspensionusa.com/";
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
            Item_Moton item_Moton = new Item_Moton(newFile,domain);
            ArrayList<String> listingStringsArrayList = new ArrayList<>();

            listingStringsArrayList.add(item_Moton.urlString);
            listingStringsArrayList.add(item_Moton.breadcrumbs);
            listingStringsArrayList.add(item_Moton.itemTitle);
            listingStringsArrayList.add(item_Moton.manufacturer);
            listingStringsArrayList.add(item_Moton.itemType);
            listingStringsArrayList.add(item_Moton.sku);
            listingStringsArrayList.add(item_Moton.fullDesc);
            listingStringsArrayList.add(item_Moton.descGeneralAbstract1);
            listingStringsArrayList.add(item_Moton.descGeneralAbstract2);
            listingStringsArrayList.add(item_Moton.fullTextDescription);
            listingStringsArrayList.add(item_Moton.features);
            listingStringsArrayList.add(item_Moton.installationInfo);
            listingStringsArrayList.add(item_Moton.productSpecs);
            listingStringsArrayList.add(item_Moton.vehicleFitment);
            listingStringsArrayList.add(item_Moton.imgLinks);
            listingStringsArrayList.add(item_Moton.price);


            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ + "  processed for mS=" + ((finishItem - startItem) ));
            arrayListOfAllStringsForCSV.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));

            if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }
        // write to EXCEL
        try { writeExcel(arrayListOfAllStringsForCSV, "Moton"+brand); }
        catch (IOException e) { e.printStackTrace(); }

        }






}

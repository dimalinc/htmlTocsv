package kingshocks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class Kingshocks_main {
    static String brand ;
    static String brandForXls ;
    static String dir ;
    static String writeAllCSV_fileName ;
     static String domain ="https://kingshocks.com/";
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();





    public static void main(String inputBrand, String inputBrandForXls,String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls =inputBrandForXls; dir=inputDir; writeAllCSV_fileName=inputWriteAllCSV_fileName; domain=inputDomain;

        writeAllCSV_fileName = "" + brand;

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
            Item_Kingshocks item_kingshocks = new Item_Kingshocks(newFile,domain);
            ArrayList<String> listingStringsArrayList = new ArrayList<>();
            listingStringsArrayList.add(item_kingshocks.urlString);
            listingStringsArrayList.add(item_kingshocks.urlStringSKU);
            listingStringsArrayList.add(item_kingshocks.title);
            listingStringsArrayList.add(item_kingshocks.sku);
            listingStringsArrayList.add(item_kingshocks.shortDesc);
            listingStringsArrayList.add(item_kingshocks.features);
            listingStringsArrayList.add(item_kingshocks.featuresHTML);
            listingStringsArrayList.add(item_kingshocks.details);
            listingStringsArrayList.add(item_kingshocks.detailsHTML);
            listingStringsArrayList.add(item_kingshocks.details2);
            listingStringsArrayList.add(item_kingshocks.details2HTML);
            listingStringsArrayList.add(item_kingshocks.details3);
            listingStringsArrayList.add(item_kingshocks.details3HTML);
            listingStringsArrayList.add(item_kingshocks.details4);
            listingStringsArrayList.add(item_kingshocks.details4HTML);
            listingStringsArrayList.add(item_kingshocks.shipping);
            listingStringsArrayList.add(item_kingshocks.shippingHTML);
            listingStringsArrayList.add(item_kingshocks.shippingHTML);

            listingStringsArrayList.add(item_kingshocks.instructions_pdf_Link);

            listingStringsArrayList.add(item_kingshocks.imgLinks);
            listingStringsArrayList.add(item_kingshocks.imgLinksURL);

           // System.out.println("Price = "+ crownItem.price);

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

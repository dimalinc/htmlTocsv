package ads;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class ads_main {
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
            Item_ads item_ads = new Item_ads(newFile,domain);
            ArrayList<String> listingStringsArrayList = new ArrayList<>();
            listingStringsArrayList.add(item_ads.urlString);
            listingStringsArrayList.add(item_ads.urlStringSKU);
            listingStringsArrayList.add(item_ads.title);
            listingStringsArrayList.add(item_ads.sku);
            listingStringsArrayList.add(item_ads.shortDesc);
            listingStringsArrayList.add(item_ads.features);
            listingStringsArrayList.add(item_ads.featuresHTML);
            listingStringsArrayList.add(item_ads.details);
            listingStringsArrayList.add(item_ads.detailsHTML);
            listingStringsArrayList.add(item_ads.details2);
            listingStringsArrayList.add(item_ads.details2HTML);
            listingStringsArrayList.add(item_ads.details3);
            listingStringsArrayList.add(item_ads.details3HTML);
            listingStringsArrayList.add(item_ads.details4);
            listingStringsArrayList.add(item_ads.details4HTML);
            listingStringsArrayList.add(item_ads.shipping);
            listingStringsArrayList.add(item_ads.shippingHTML);
            listingStringsArrayList.add(item_ads.shippingHTML);

            listingStringsArrayList.add(item_ads.instructions_pdf_Link);

            listingStringsArrayList.add(item_ads.imgLinks);
            listingStringsArrayList.add(item_ads.imgLinksURL);

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

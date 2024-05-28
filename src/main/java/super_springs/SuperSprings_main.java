package super_springs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class SuperSprings_main {
    static String brand ;
    static String brandForXls ;
    static String dir ;
    static String writeAllCSV_fileName ;
    static String domain;
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();





    public static void main(String inputBrand, String inputBrandForXls,String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls =inputBrandForXls; dir=inputDir; writeAllCSV_fileName=inputWriteAllCSV_fileName; domain=inputDomain;

        writeAllCSV_fileName = "D:\\savedHtml\\savedHtml_.superspringsinternational.com" + brand;
        domain = "https://www.superspringsinternational.com/";

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
            Item_SuperSprings superSprings_Item = new Item_SuperSprings(newFile,domain);
            ArrayList<String> listingStringsArrayList = new ArrayList<>();
            listingStringsArrayList.add(superSprings_Item.urlString);
            listingStringsArrayList.add(superSprings_Item.urlStringSKU);

            listingStringsArrayList.add(superSprings_Item.sku);
            listingStringsArrayList.add(superSprings_Item.price);

            listingStringsArrayList.add(superSprings_Item.imgLinks);
            listingStringsArrayList.add(superSprings_Item.imgLinksURL);
            listingStringsArrayList.add(superSprings_Item.pdfLinks);
            listingStringsArrayList.add(superSprings_Item.pdfLinksURL);
            System.out.println(superSprings_Item.pdfLinksURL);

            listingStringsArrayList.add(superSprings_Item.shortDesc);
            listingStringsArrayList.add(superSprings_Item.shortDescHTML);
            listingStringsArrayList.add(superSprings_Item.specsTable);
            listingStringsArrayList.add(superSprings_Item.specsTableHTML);
            listingStringsArrayList.add(superSprings_Item.application);
            listingStringsArrayList.add(superSprings_Item.applicationHTML);

            listingStringsArrayList.add(superSprings_Item.sku);

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

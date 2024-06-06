package Eibach_com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class Eibach_main {
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
            Item_XpathParsed_Eibach item_xpathParsed_eibach = new Item_XpathParsed_Eibach(newFile,domain);
            Item_DataProcssed_Eibach item_dataProcssed_eibach= new Item_DataProcssed_Eibach(item_xpathParsed_eibach);

            ArrayList<String> listingStringsArrayList = new ArrayList<>();
            /*listingStringsArrayList.add(eibachItem.urlString);
            listingStringsArrayList.add(eibachItem.urlStringSKU);
            listingStringsArrayList.add(eibachItem.title);
            listingStringsArrayList.add(eibachItem.breadcrumbs);
            listingStringsArrayList.add(eibachItem.imgLinks);
            listingStringsArrayList.add(eibachItem.imgLinksURL);

            listingStringsArrayList.add(eibachItem.specs);
            listingStringsArrayList.add(eibachItem.specsHTML);
            listingStringsArrayList.add(eibachItem.application);
            listingStringsArrayList.add(eibachItem.applicationHTML);
             listingStringsArrayList.add(eibachItem.sku);
*/
            listingStringsArrayList.add(item_xpathParsed_eibach.urlString);
            listingStringsArrayList.add(item_xpathParsed_eibach.urlStringSKU);
            listingStringsArrayList.add(item_xpathParsed_eibach.sku);
            listingStringsArrayList.add(item_xpathParsed_eibach.title);
            listingStringsArrayList.add(item_xpathParsed_eibach.breadcrumbs);
            listingStringsArrayList.add(item_xpathParsed_eibach.carInfo);
            listingStringsArrayList.add(item_xpathParsed_eibach.carDetails);
            listingStringsArrayList.add(item_xpathParsed_eibach.shortDesc);
            listingStringsArrayList.add(item_xpathParsed_eibach.liftAndFitmentAttribs);
            listingStringsArrayList.add(item_xpathParsed_eibach.price);
            listingStringsArrayList.add(item_xpathParsed_eibach.longDesc);
            listingStringsArrayList.add(item_xpathParsed_eibach.longDescHTML);
            listingStringsArrayList.add(item_xpathParsed_eibach.warranty);
            listingStringsArrayList.add(item_xpathParsed_eibach.pdfManualLinks);
            listingStringsArrayList.add(item_xpathParsed_eibach.imgLinks);
            listingStringsArrayList.add(item_xpathParsed_eibach.imgLinksURL);
            listingStringsArrayList.add(item_xpathParsed_eibach.recommendedItemLinks);
            listingStringsArrayList.add(item_xpathParsed_eibach.recommendedItemNames);

            /*listingStringsArrayList.add(eibachItem.application_list_href);
            listingStringsArrayList.add(eibachItem.collapsable_application_list_href);*/

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

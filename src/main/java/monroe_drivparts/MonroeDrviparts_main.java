package monroe_drivparts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;
import static monroe_drivparts.Data_processing_utils_Monroe.itemFields_xpathStringsListInit;

public class MonroeDrviparts_main {
    static String brand;
    static String brandForXls;
    static String dir;
    static String writeAllCSV_fileName;
    static String domain;
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();


    public static void main(String inputBrand, String inputBrandForXls, String inputDir, String inputWriteAllCSV_fileName, String inputDomain, int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand = inputBrand;
        brandForXls = inputBrandForXls;
        dir = inputDir;
        writeAllCSV_fileName = inputWriteAllCSV_fileName;
        domain = inputDomain;

        writeAllCSV_fileName = "D:\\savedHtml\\savedHtml_drvi_" + brand;
        domain = "https://www.drivparts.com/";

        Long start = System.currentTimeMillis();

        itemFields_xpathStringsListInit();

        filesArrayList = listFilesUsingJavaIO(dir);
        System.out.println(filesArrayList);
        System.out.println("filesArrayList.size() = " + filesArrayList.size());

        int n = 1;
        for (String fileString : filesArrayList) {

            if ((inputFilesNumberStart != 0) && (n < inputFilesNumberStart)) {
                n++;
                continue;
            }

            Long startItem = System.currentTimeMillis();
            File newFile = new File(dir + fileString);
            System.out.println(newFile.getName());

            Xpath_Item_MonroeDrviparts monroeDrvipartsItem_Xpath = new Xpath_Item_MonroeDrviparts(newFile, domain);
            Item_Processed_Monroe_Drviparts item_processed_monroe_drviparts = new Item_Processed_Monroe_Drviparts(monroeDrvipartsItem_Xpath);

            /*     Eibach
            ArrayList<String> listingStringsArrayList = new ArrayList<>();
            listingStringsArrayList.add(eibachItem.urlString);
            listingStringsArrayList.add(eibachItem.urlStringSKU);
            listingStringsArrayList.add(eibachItem.title);
            listingStringsArrayList.add(eibachItem.breadcrumbs);
            listingStringsArrayList.add(eibachItem.imgLinks);
            listingStringsArrayList.add(eibachItem.imgLinksURL);
            listingStringsArrayList.add(eibachItem.specs);
            listingStringsArrayList.add(eibachItem.specsHTML);
            listingStringsArrayList.add(eibachItem.application);
            listingStringsArrayList.add(eibachItem.applicationHTML);
             listingStringsArrayList.add(eibachItem.sku);*/

         /*  ArrayList<String> listingStringsArrayList = new ArrayList<>();
          listingStringsArrayList.add(monroeDrvipartsItem_Xpath.urlString);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.urlStringSKU);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.name);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.sku);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.imgLinks);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.imgLinksURL);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.specifications);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.specificationsHTML);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.applications);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.applicationsHTML);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.shortDesc);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.longDesc);
            listingStringsArrayList.add(monroeDrvipartsItem_Xpath.longDescHTML);*/
            //   listingStringsArrayList.add(monroeDrvipartsItem_Xpath.otherMedia);


            // for each car
            for (Car_Item_Monroe_Drviparts car_item_monroe_drviparts : item_processed_monroe_drviparts.car_item_monroe_drviparts_ArrayList) {
                ArrayList<String> listingStringsArrayList = new ArrayList<>();
                listingStringsArrayList.add(car_item_monroe_drviparts.car_Item_title);
                listingStringsArrayList.add(car_item_monroe_drviparts.car_Item_short_desc);
                listingStringsArrayList.add(car_item_monroe_drviparts.car_Item_long_desc);
                listingStringsArrayList.add(car_item_monroe_drviparts.appObject.getallOtherCarParams() );

                Long finishItem = System.currentTimeMillis();
                System.out.println(n++ + "  processed for mS=" + ((finishItem - startItem)));
                arrayListOfAllStringsForCSV.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));
            }
            if ((inputFilesNumberFinish != 0) && (n > inputFilesNumberFinish)) break;
        }
        // write to EXCEL
        try {
            writeExcel(arrayListOfAllStringsForCSV, "_" + brand);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

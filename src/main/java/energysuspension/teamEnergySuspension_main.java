package energysuspension;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class teamEnergySuspension_main {
    static String brand ;
    static String brandForXls ;
    static String dir ;
    static String writeAllCSV_fileName ;
    static String domain;
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();





    public static void main(String inputBrand, String inputBrandForXls,String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls =inputBrandForXls; dir=inputDir; writeAllCSV_fileName=inputWriteAllCSV_fileName; domain=inputDomain;

        writeAllCSV_fileName = "D:\\savedHtml\\savedHtml_energysuspension" + brand;
        domain = "https://teamenergysuspension.com/";

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
            Item_teamEnergySuspension energyItem = new Item_teamEnergySuspension(newFile,domain);
            ArrayList<String> listingStringsArrayList = new ArrayList<>();

            listingStringsArrayList.add(energyItem.urlString);
            listingStringsArrayList.add(energyItem.breadCrumbs);
            listingStringsArrayList.add(energyItem.shortDesc);
            listingStringsArrayList.add(energyItem.sku);
            listingStringsArrayList.add(energyItem.title);

            listingStringsArrayList.add(energyItem.itemFitmentTable);
            listingStringsArrayList.add(energyItem.itemFitmentTableHTML);

            listingStringsArrayList.add(energyItem.longDesc);
            listingStringsArrayList.add(energyItem.imgLink);

            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ + "  processed for mS=" + ((finishItem - startItem) ));
            arrayListOfAllStringsForCSV.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));

            if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }
        // write to EXCEL
        try { writeExcel(arrayListOfAllStringsForCSV, "EnergySuspension_"+brand); }
        catch (IOException e) { e.printStackTrace(); }

        }






}

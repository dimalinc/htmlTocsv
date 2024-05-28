package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.*;

public class TuffCountry_key {
    // static int filesNumberStart=412, filesNumberFinish=418;
     static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "TuffCountry"+"_1row";
    static String brandForXls = "TuffCountry";
    static String dir="D:\\savedHtml\\savedHtml_TuffCountry_key" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        writeToXls_Base_Scenario_EGOR.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
         //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_PRESTA_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);

    }
}

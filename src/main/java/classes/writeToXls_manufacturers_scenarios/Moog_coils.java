package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.*;

public class Moog_coils {
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "Moog_coils"+"_1row";
    static String brandForXls = "Moog";
    static String dir="D:\\savedHtml\\savedHtml_Moog_coils" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String domain = "";

    public static void main(String[] args) {
         writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario_.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);

    }
}

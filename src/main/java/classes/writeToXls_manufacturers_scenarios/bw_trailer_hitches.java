package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.writeToXls_PRESTA_CsvAtts_Scenario;
import classes.scenarios.old_for_multithread.*;

public class bw_trailer_hitches implements Runnable{
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "B&W_Trailer_Hitches"+"_1row";
    static String brandForXls = "B&W_Trailer_Hitches";
    static String dir="D:\\savedHtml\\savedHtml_bw_trailer_hitches" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brand;
    static String domain = "";

    public static void main(String[] args) {
         writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }

    public void run() {
        writeToXls_PRESTA_CsvAtts_Scenario_bw_trailer_hitches.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}

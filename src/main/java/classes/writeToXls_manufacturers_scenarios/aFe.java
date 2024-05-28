package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.*;
import classes.scenarios.old_for_multithread.*;

public class aFe implements Runnable{
    static int filesNumberStart=0, filesNumberFinish=0;
    static String brand = "aFe"+"_1row";
    static String brandForXls = "aFe";
    static String dir="D:\\savedHtml\\savedhtml_aFe_key" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brand;
    static String writeAllCSV_fileName_Runnable = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\Runnable" + brand;
    static String domain = "";

    public static void main(String[] args) {
        //   writeToXls_Base_Scenario_EGOR.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
       writeToXls_PRESTA_CsvAtts_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }

    @Override
    public void run() {
        writeToXls_PRESTA_CsvAtts_Scenario_aFe.main(brand,brandForXls,dir,writeAllCSV_fileName_Runnable,domain,filesNumberStart,filesNumberFinish);
    }
}

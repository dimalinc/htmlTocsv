package classes.writeToXls_manufacturers_scenarios;

import classes.scenarios.*;

public class sky_Leveling_kits {
    // static int filesNumberStart=62, filesNumberFinish=66;
      static int filesNumberStart=0, filesNumberFinish=61;
    // static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "sky_Leveling_kits_prestaAtt"+"_1row";
    static String brandForXls = "Skyjacker";
    static String dir="D:\\savedHtml\\2_savedHtml_sky_lev_kits_66" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        writeToXls_Base_Scenario_EGOR.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        // writeToXls_Base_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //   writeToXls_PRESTA_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
    }
}
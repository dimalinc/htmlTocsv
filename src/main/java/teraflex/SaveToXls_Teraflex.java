package teraflex;

public class SaveToXls_Teraflex {
    //  static int filesNumberStart=412, filesNumberFinish=418;
    static int filesNumberStart = 0, filesNumberFinish = 0;
    static String brandForFilename = "Teraflex" ;
    static String brandForXls = "Teraflex";
    static String dir = "D:\\savedHtml\\savedHtml_Teraflex" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        teraflex_main.main(brandForFilename, brandForXls, dir, writeAllCSV_fileName, domain, filesNumberStart, filesNumberFinish);
    }
}


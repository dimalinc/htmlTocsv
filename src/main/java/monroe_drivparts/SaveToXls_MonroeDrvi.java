package monroe_drivparts;

public class SaveToXls_MonroeDrvi {
    //  static int filesNumberStart=160, filesNumberFinish=170;
     static int filesNumberStart=0, filesNumberFinish=0;
    static String brandForFilename = "monroe_drivparts.com" ;
    static String brandForXls = "Monroe";
    static String dir = "D:\\savedHtml\\savedHtml_Monroe_drivparts" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String domain = "https://www.drivparts.com/";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        MonroeDrviparts_main.main(brandForFilename, brandForXls, dir, writeAllCSV_fileName, domain, filesNumberStart, filesNumberFinish);
    }
}
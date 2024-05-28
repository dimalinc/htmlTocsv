package motonsuspensionusa;

public class SaveToXls_Moton {
    //  static int filesNumberStart=412, filesNumberFinish=418;
    static int filesNumberStart = 0, filesNumberFinish = 0;
    static String brandForFilename = "Moton" ;
    static String brandForXls = "Moton";
    static String dir = "D:\\savedHtml\\motonsuspensionusa" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\" + brandForFilename;
    static String domain = "";

    public static void main(String[] args) {
        //  writeToXls_Base_Scenario.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_CsvAtts_Scenario.main(brandForFilename,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        //  writeToXls_Base_Scenario_ItemList.main(brand,brandForXls,dir,writeAllCSV_fileName,domain,filesNumberStart,filesNumberFinish);
        moton_main.main(brandForFilename, brandForXls, dir, writeAllCSV_fileName, domain, filesNumberStart, filesNumberFinish);
    }
}


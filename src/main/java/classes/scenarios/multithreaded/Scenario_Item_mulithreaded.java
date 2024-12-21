package classes.scenarios.multithreaded;

import classes.Item;

import static classes.utils.file_utils.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;


public class Scenario_Item_mulithreaded {
    final static int numberOfThreads = 5;
    static ArrayList<Manufacturer_DTO> manufacturerDtosArrayList = new ArrayList<>();


    static {
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\"));
        //  manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",300,900));
       // manufacturerDtosArrayList.add(new Manufacturer_DTO("Skyjacker_2522", "Skyjacker", "D:\\savedHtml\\savedHtml_skyALL_BIG" + "\\"));

        manufacturerDtosArrayList.add(new Manufacturer_DTO("Rancho_418", "Rancho", "D:\\savedHtml\\savedHtml_Rancho_key" + "\\"));

      /* manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",0,100));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",101,200));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",201,300));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",301,500));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",501,700));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",701,900));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",901,1002));
    */    //  manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",331,660));
        //   manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",661,990));
        //  manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1003", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\",991,1003));

     /*   manufacturerDtosArrayList.add(new Manufacturer_DTO("Bilstein_1000", "Bilstein", "D:\\savedHtml\\savedHtml_Bilstein_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Ironmann_19", "Ironmann", "D:\\savedHtml\\savedHtml_Ironmann_key" + "\\"));

        manufacturerDtosArrayList.add(new Manufacturer_DTO("ProcompCoils_39", "Procomp", "D:\\savedHtml\\savedHtml_Procomp_coils" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("ProComp_shocks_209", "ProComp", "D:\\savedHtml\\savedHtml_Procomp_shocks" + "\\"));

        manufacturerDtosArrayList.add(new Manufacturer_DTO("SuperSprings_281", "SuperSprings", "D:\\savedHtml\\savedHtml_supersprings_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("H&R_306", "H&R", "D:\\savedHtml\\savedHtml_H-n-r_spring" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("sky_lev_kits_66", "Skyjacker", "D:\\savedHtml\\2_savedHtml_sky_lev_kits_66" + "\\"));

        manufacturerDtosArrayList.add(new Manufacturer_DTO("KYB_754", "KYB", "D:\\savedHtml\\savedHtml_KYB_shocks" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Eibach_1521", "Eibach", "D:\\savedHtml\\savedHtml_eibach" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Daystar_1450", "Daystar", "D:\\savedHtml\\savedHtml_Daystar_key" + "\\"));
       */
        manufacturerDtosArrayList.add(new Manufacturer_DTO("FOX_1012", "FOX", "D:\\savedHtml\\savedHtml_Fox_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("ReadyLift_734", "ReadyLift", "D:\\savedHtml\\readyLIFT" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Fabtech_kits_1058", "Fabtech", "D:\\savedHtml\\savedHtml_fabtech_kits_KEYSTONE" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Fabtech_other_2035", "Fabtech", "D:\\savedHtml\\savedHtml_fabtech_others" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("TuffCountry_2694", "TuffCountry", "D:\\savedHtml\\savedHtml_TuffCountry_key" + "\\"));


        manufacturerDtosArrayList.add(new Manufacturer_DTO("Skyjacker_2522", "Skyjacker", "D:\\savedHtml\\savedHtml_skyALL_BIG" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("sky_lift_kits_2031", "Skyjacker", "D:\\savedHtml\\2_savedHtml_sky_liftkits" + "\\"));

        manufacturerDtosArrayList.add(new Manufacturer_DTO("Monroe_2167", "Monroe", "D:\\savedHtml\\savedHtml_Monroe_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Moog_3597", "Moog", "D:\\savedHtml\\savedhtml_moog_all" + "\\"));
        // manufacturerDtosArrayList.add(new Manufacturer_DTO("Airlift_key_732", "AirLift", "D:\\savedHtml\\airLift_key" + "\\"));
        //   manufacturerDtosArrayList.add(new Manufacturer_DTO("GoRhino_2066", "GoRhino", "D:\\savedHtml\\savedHtml_GoRhino_key" + "\\"));
        //   manufacturerDtosArrayList.add(new Manufacturer_DTO("Retrax_869", "Retrax", "D:\\savedHtml\\savedHtml_Retrax_key" + "\\"));



   /*     manufacturerDtosArrayList.add(new Manufacturer_DTO("aFe_3140", "aFe", "D:\\savedHtml\\savedhtml_aFe_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("TrailFX_Liftkits_68", "TrailFX", "D:\\savedHtml\\savedHtml_TrailFX_liftkits_68" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("B&W_trailer_hitches_179", "B&W", "D:\\savedHtml\\savedHtml_bw_trailer_hitches" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Trico_30", "Trico", "D:\\savedHtml\\savedHtml_Trico_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Gen-Y_415", "Gen-Y", "D:\\savedHtml\\savedHtml_Gen-Y_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("N-Fab_857", "N-Fab", "D:\\savedHtml\\savedHtml_n-fab_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Timberen_472", "Timberen", "D:\\savedHtml\\savedHtml_timberen_KEYSTONE" + "\\"));
*/

 /*       manufacturerDtosArrayList.add(new Manufacturer_DTO("Monroe_2167", "Monroe", "D:\\savedHtml\\savedHtml_Monroe_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Moog_3597", "Moog", "D:\\savedHtml\\savedhtml_moog_all" + "\\"));


        manufacturerDtosArrayList.add(
                new Manufacturer_DTO(
                        "Knfilter_key_3592", "K&N Filters", "D:\\savedHtml\\knfilter_key" + "\\"
                ));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("NerfBar_1878", "NerfBar", "D:\\savedHtml\\savedHtml_KEY_cat_NerfBars" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("Weathertech_4961", "Weathertech", "D:\\savedHtml\\savedHtml_Weathertech_key" + "\\"));
        manufacturerDtosArrayList.add(new Manufacturer_DTO("energysuspension_3156", "EnergySuspension", "D:\\savedHtml\\savedHtml_energysuspension_KEYSTONE" + "\\"));
*/        // manufacturerDtosArrayList.add(new Manufacturer_DTO("","",""+"\\"));
    }

    static int filesAmountLeft;
    static ArrayList<String> filesArrayList = new ArrayList<>();

    static String brand = "";
    static String brandForXls = "";
    static String dir /*= "D:\\savedHtml\\savedHtml_Ironmann_key" + "\\"*/;

    static String writeAllCSV_fileName /*= "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand*/;
    static String domain = "";
    static ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR /*= new ArrayList<String[]>()*/;
    static ArrayList<String[]> arrayListOfAllStringsForCSV_Presta /*= new ArrayList<String[]>()*/;
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR /*= new ArrayList<String[]>()*/;
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_Presta/* = new ArrayList<String[]>()*/;

    static void initNewArrayLists() {
        arrayListOfAllStringsForCSV_EGOR = new ArrayList<String[]>();
        arrayListOfAllStringsForCSV_Presta = new ArrayList<String[]>();
        arrayListOfAllStringsForCSV_noCars_EGOR = new ArrayList<String[]>();
        arrayListOfAllStringsForCSV_noCars_Presta = new ArrayList<String[]>();
    }

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();

    /*pool.submit(Foo::foo);
    pool.submit(() -> bar());
    pool.submit(new Runnable() {
        @Override
        public void run() {
            baz();
        }
    });*/

        /*pool.submit(new Runnable() {
            @Override
            public void run() {
                executeIronmann();
            }
        });*/

        for (Manufacturer_DTO manufacturer_DTO : manufacturerDtosArrayList) {

            initNewArrayLists();

            dir = manufacturer_DTO.getDir();
            brand = manufacturer_DTO.getBrand();
            System.out.println(brand);
            brandForXls = manufacturer_DTO.getBrandForXls();
            int startFileNumber = manufacturer_DTO.getStartItemNumber();
            int finishFileNumber = manufacturer_DTO.getFinishItemNumber();

            filesArrayList = listFilesUsingJavaIO(dir);
            System.out.println("filesArrayList.size() = "+filesArrayList.size());
            if ((finishFileNumber == 0) && (startFileNumber == 0))
                filesAmountLeft = filesArrayList.size() - 1;
            else filesAmountLeft = finishFileNumber - startFileNumber + 1;

            System.out.println(filesArrayList);

            ExecutorService itemsPool = Executors.newFixedThreadPool(numberOfThreads);

            int n = 0;
            Future<?> future = null;
            for (String fileString : filesArrayList) {

                if ((startFileNumber != 0) && (n < startFileNumber)) {
                    n++;
                    continue;
                }

              /*  itemsPool.execute(new Item(new File(dir + fileString), brandForXls, arrayListOfAllStringsForCSV_EGOR, arrayListOfAllStringsForCSV_noCars_EGOR, arrayListOfAllStringsForCSV_Presta, arrayListOfAllStringsForCSV_noCars_Presta) );
                System.out.println("filesAmountLeft = " + (filesAmountLeft--));*/


               future = itemsPool.submit(() -> item_processing(fileString,
                        arrayListOfAllStringsForCSV_EGOR, arrayListOfAllStringsForCSV_noCars_EGOR,
                        arrayListOfAllStringsForCSV_Presta, arrayListOfAllStringsForCSV_noCars_Presta));

                n++;
                if ((finishFileNumber != 0) && (n > finishFileNumber)) break;
            }

            try {
                System.out.println("Result of Future: " + future.get());
            } catch (ExecutionException e) {
                //  throw new RuntimeException(e);
                e.printStackTrace();
            }


            //if (itemsPool.awaitTermination(9000000, TimeUnit.MINUTES))
            itemsPool.shutdown();


            // TODO: check awaitTermination https://javarush.com/quests/lectures/jru.module2.lecture19
            if (itemsPool.awaitTermination(90000000, TimeUnit.SECONDS))
            try {
                    System.out.println(arrayListOfAllStringsForCSV_Presta.size());
                    System.out.println(arrayListOfAllStringsForCSV_noCars_Presta.size());

                    writeExcel(arrayListOfAllStringsForCSV_Presta, brand + "_Presta_multi_"+startFileNumber+"-"+finishFileNumber);
                    writeExcel(arrayListOfAllStringsForCSV_noCars_Presta, brand + "_noCars_Presta_multi_"+startFileNumber+"-"+finishFileNumber);

                    writeExcel(arrayListOfAllStringsForCSV_EGOR, brand + "_EGOR_multi_"+startFileNumber+"-"+finishFileNumber);
                    writeExcel(arrayListOfAllStringsForCSV_noCars_EGOR, brand + "_noCars_EGOR_multi_"+startFileNumber+"-"+finishFileNumber);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            //  pool.submit(Scenario_mulithreaded::executeIronmann);

            Long finish = System.currentTimeMillis();
            System.out.println(filesArrayList.size() + " files processed in " + ((finish - start) / 1000) + " seconds");

        }
    }


    static void foo() { /* долгая задача 1 */ }

    static void bar() { /* долгая задача 2 */ }

    static void baz() { /* долгая задача 3 */ }

    // static void execute_Item_Runnable(Item item) {new Ironmann_Runnable().run();}

    static void item_processing(String fileString, ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR,
                                ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR,
                                ArrayList<String[]> arrayListOfAllStringsForCSV_Presta,
                                ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_Presta) {
        new Item(new File(dir + fileString), brandForXls, arrayListOfAllStringsForCSV_EGOR, arrayListOfAllStringsForCSV_noCars_EGOR, arrayListOfAllStringsForCSV_Presta, arrayListOfAllStringsForCSV_noCars_Presta);
        System.out.println("filesAmountLeft = " + (filesAmountLeft--));
    }


    //  static void execute_() {new ().run();}


}

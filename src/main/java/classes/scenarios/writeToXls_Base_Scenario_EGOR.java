package classes.scenarios;

import com.opencsv.CSVWriter;
import classes.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class writeToXls_Base_Scenario_EGOR {

    static String brand /*= "writeToXls_Base_Scenario_EGOR"*/;
    static String brandForXls /*= "writeToXls_Base_Scenario_EGOR"*/ ;
    static String dir /*= "D:\\savedHtml\\savedHtml_sky_lev_kits" + "\\"*/;
    static String writeAllCSV_fileName /*= "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand*/;
    static String domain = "";
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars = new ArrayList<String[]>();


    public static void main(String inputBrand, String inputBrandForXls, String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls=inputBrandForXls;dir=inputDir; writeAllCSV_fileName=inputWriteAllCSV_fileName; domain=inputDomain;
        Long start = System.currentTimeMillis();
        Long startNewItemsCreating = System.currentTimeMillis();
        filesArrayList = listFilesUsingJavaIO(dir);
        System.out.println(filesArrayList);

        // new code creating list of Items
        /*int n = 1;
        for (String fileString : filesArrayList) {

            if ((inputFilesNumberStart != 0) && (n < inputFilesNumberStart)) {
                n++;continue; }

            Long startItem = System.currentTimeMillis();
            Item item = new Item(new File(dir + fileString));
            generatedItemObjectsArrayList.add(item);
        }
        Long finishNewItemsCreating = System.currentTimeMillis();
        System.out.println(filesArrayList.size() + " new ITEMS_List created in " + ((finishNewItemsCreating - startNewItemsCreating) / 1000) + " seconds");
*/

        int n = 1;
        for (String fileString : filesArrayList) {

              if ( (inputFilesNumberStart!=0) && (n<inputFilesNumberStart) ) { n++; continue; }

            Long startItem = System.currentTimeMillis();
            Item item = new Item(new File(dir + fileString),arrayListOfAllStringsForCSV);

            if (item.carObjectsList.size()>0)
            for (Car car : item.carObjectsList) {

                ArrayList<String> listingStringsArrayList = new ArrayList<>();

                listingStringsArrayList.add(item.sku);
                listingStringsArrayList.add(car.carItemSku);
                listingStringsArrayList.add(item.itemType);
                listingStringsArrayList.add(item.itemFullDescription);
                listingStringsArrayList.add(item.attributesFromDesHTML);
                listingStringsArrayList.add(item.longDescWoAttribsHTML);
                listingStringsArrayList.add(car.carLineString);
                listingStringsArrayList.add(car.carWpCategoryString);
               // listingStringsArrayList.add(car.carPrestaCategoryStringWithYears);
                listingStringsArrayList.add(car.make);
                listingStringsArrayList.add(car.model);
              //  listingStringsArrayList.add(car.driveHashSet.toString());
                listingStringsArrayList.add(car.driveRangeString);
              //  listingStringsArrayList.add(car.yearNumbersSet.toString());
                listingStringsArrayList.add(car.yearNumbersString);
                listingStringsArrayList.add(car.carLiftNumbersString);

                /*String[] eachCarLineStringArray = new String[50];

                eachCarLineStringArray[0] = "";
                eachCarLineStringArray[1] = car.carItemSku;
                eachCarLineStringArray[2] = car.carValue;
                eachCarLineStringArray[3] = car.make;
                eachCarLineStringArray[4] = car.model;
                eachCarLineStringArray[5] = car.drive.toString();
                eachCarLineStringArray[6] = car.yearNumbersSet.toString();*/

                StringBuilder subCarSb = new StringBuilder();
                StringBuilder applicationFootnoteSb = new StringBuilder();
                StringBuilder applicationAttributeSb = new StringBuilder();
                StringBuilder applicationAttributePositionSb = new StringBuilder();
                StringBuilder applicationAttributeLiftSb = new StringBuilder();
                StringBuilder applicationAttributeLiftStartSb = new StringBuilder();
                StringBuilder applicationAttributeLiftFinishSb = new StringBuilder();
                StringBuilder applicationAttributeLiftRangeSb = new StringBuilder();

               /* String itemTitleString=null;
                itemTitleString= item.itemType+" "+car.carTitleString+" "+" "+car.subCarDrivesString +" "
                        +item.itemFrontLift+" " +item.itemPositionArrayList.toString()*//*+"-"+item.itemRearLift+item.itemPositionArrayList*//*;
*/
               // String carTitleString=null;
                for (SubCar subCar : car.subCarObjectsList) {

                   /* carTitleString=item.itemType+" "+car.carMakeModelYearsString +" "+" "+subCar.drive +" "
                            +car.carLiftNumberStart+"-"+car.carLiftNumberFinish+" Lift";*/

                    subCarSb.append(subCar.subCarStringValue).append(System.lineSeparator());

                    for (ApplicationFootnote applicationFootnote : subCar.subCarAppFootnote_ObjectsList) {
                        applicationFootnoteSb.append(applicationFootnote.appFootnote_Value).append(System.lineSeparator());
                    }

                    for (ApplicationAttribute applicationAttribute : subCar.subCarAppAttribute_ObjectsList) {
                        applicationAttributeSb.append(applicationAttribute.applicationAttribute_Value).append(System.lineSeparator());
                        // TODO: appAttPositionArrayList переделать вывод в ячейку
                        applicationAttributePositionSb.append(applicationAttribute.appAttPositionArrayList.toString()).append(System.lineSeparator());
                        applicationAttributeLiftSb.append(applicationAttribute.liftString).append(System.lineSeparator());
                        applicationAttributeLiftStartSb.append(applicationAttribute.appAttLiftNumberStart).append(System.lineSeparator());
                        applicationAttributeLiftFinishSb.append(applicationAttribute.appAttLiftNumberFinish).append(System.lineSeparator());
                       // for (double liftValue : applicationAttribute.appAttLiftNumbersSet) applicationAttributeLiftRangeSb.append(liftValue).append("|");
                        applicationAttributeLiftRangeSb.append(applicationAttribute.liftString).append(System.lineSeparator());
                    }

                    /*eachCarLineStringArray[7] = subCarSb.toString();
                    eachCarLineStringArray[8] = applicationFootnoteSb.toString();
                    eachCarLineStringArray[9] = applicationAttributeSb.toString();
                    eachCarLineStringArray[10] = applicationAttributePositionSb.toString();
                    eachCarLineStringArray[11] = applicationAttributeLiftSb.toString();
                    eachCarLineStringArray[12] = applicationAttributeLiftStartSb.toString();
                    eachCarLineStringArray[13] = applicationAttributeLiftFinishSb.toString();
                    eachCarLineStringArray[14] = applicationAttributeLiftRangeSb.toString();*/
                }

                if ( (item.itemFrontLiftString ==null) && (item.itemRearLiftString ==null) )
                    listingStringsArrayList.add(car.carWpTitleString);
                else listingStringsArrayList.add("ItemLiftStrings=0");

                listingStringsArrayList.add(car.itemWpTitleString);
                listingStringsArrayList.add(car.carWpTitleString);

               /* System.out.println("itemTitleString = "+car.itemTitleString);
                System.out.println("carTitleString = "+car.carTitleString);*/
                listingStringsArrayList.add(applicationFootnoteSb.toString());
                listingStringsArrayList.add(applicationAttributeSb.toString());
                listingStringsArrayList.add(applicationAttributePositionSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftStartSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftFinishSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftRangeSb.toString());

               // eachCarLineStringArray[15] = car.carLiftNumbersSet.toString();
                listingStringsArrayList.add(car.carLiftNumbersSet.toString());

               /* int k = 0;
                for (String s : item.itemLineStringArray) {//  eachCarLineStringArray[k + 16] = s;
                    listingStringsArrayList.add(s);
                    k++;
                }*/

                // listingStringsArrayList.addAll(Arrays.asList(item.itemLineStringArray)) ;
               // System.out.println("Arrays.asList(item.itemLineStringArray) = " + Arrays.asList(item.itemLineStringArray));
                //  arrayListOfAllStringsForCSV.add(eachCarLineStringArray);
                arrayListOfAllStringsForCSV.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));
            }

            // adding rows for  noCar items
            if (item.carObjectsList.size()==0) {
                ArrayList<String> listingStringsArrayListNoCars = new ArrayList<>();

                listingStringsArrayListNoCars.add(item.sku);
                int k = 0;
                for (String s : item.itemLineStringArray) {
                    //  eachCarLineStringArray[k + 16] = s;
                    listingStringsArrayListNoCars.add(s);
                    k++;
                }

              //  arrayListOfAllStringsForCSV.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));
                arrayListOfAllStringsForCSV_noCars.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));

            }

            // replacing wrong symbols
            for (String[] stringArray:arrayListOfAllStringsForCSV) {
                if (stringArray.length>0)
                    for (int i = 0; i <stringArray.length ; i++) {
                        if(stringArray[i]!=null)  stringArray[i].replaceAll("_x000D_"," ");
                }
            }

            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ + " Item WITH cars processed for s=" + ((finishItem - startItem) / 1000));
             if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }
        // if no cars - old code
        /*for (String fileString : filesArrayList) {
            if ( (inputFilesNumberStart!=0) && (n<inputFilesNumberStart) ) { n++; continue; }

            Long startItem = System.currentTimeMillis();
            Item item = new Item(new File(dir + fileString));

            if (item.carObjectsList.size()==0) {
                ArrayList<String> listingStringsArrayListNoCars = new ArrayList<>();

                listingStringsArrayListNoCars.add(item.sku);
                int k = 0;
                for (String s : item.itemLineStringArray) {
                    //  eachCarLineStringArray[k + 16] = s;
                    listingStringsArrayListNoCars.add(s);
                    k++; }

                arrayListOfAllStringsForCSV_noCars.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));
            }

            for (String[] stringArray:arrayListOfAllStringsForCSV_noCars) {
                if (stringArray.length>0)
                    for (int i = 0; i <stringArray.length ; i++) {
                   if (stringArray[i]!=null) stringArray[i].replaceAll("_x000D_"," ");
                }
            }


            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ + " Item NO cars processed for s=" + ((finishItem - startItem) / 1000));
            if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }*/

            // write to EXCEL
        try {
            writeExcel(arrayListOfAllStringsForCSV, brand+"_EGOR");
            writeExcel(arrayListOfAllStringsForCSV_noCars, brand+"_noCars"+"_EGOR");
        } catch (IOException e) { e.printStackTrace(); }

        // write to CSV
        /*CSVWriter csvWriter = csvWriterInit();
        csvWriter.writeAll(arrayListOfAllStringsForCSV, true);
        csvWriter.writeAll(arrayListOfAllStringsForCSV_noCars, true);
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Long finish = System.currentTimeMillis();
        System.out.println(filesArrayList.size() + " files processed in " + ((finish - start) / 1000) + " seconds");
    }


    private static CSVWriter csvWriterInit() {
        CSVWriter csvWriter = null;
        try {
            //  csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName/*,true*/));
            csvWriter = new CSVWriter(new FileWriter(writeAllCSV_fileName + ".csv"), '^');
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvWriter;
    }


}

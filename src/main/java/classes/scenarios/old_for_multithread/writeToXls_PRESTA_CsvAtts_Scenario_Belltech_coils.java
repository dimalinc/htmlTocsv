package classes.scenarios.old_for_multithread;

import classes.*;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import static classes.utils.file_utils.listFilesUsingJavaIO;
import static classes.utils.file_utils.writeExcel;

public class writeToXls_PRESTA_CsvAtts_Scenario_Belltech_coils {

    static String brand = "sky_LevelingKits_"+"1row";
    static String brandForXls = "";
    static String dir = "D:\\savedHtml\\savedHtml_sky_lev_kits" + "\\";
    static String writeAllCSV_fileName = "C:\\Users\\dmitr\\IdeaProjects\\htmlTocsv\\cl_" + brand;
    static String domain = "";
    static ArrayList<String> filesArrayList = new ArrayList<>();
    static ArrayList<String[]> arrayListOfAllStringsForCSV = new ArrayList<String[]>();



    public static void main(String inputBrand, String inputBrandForXls,String inputDir, String inputWriteAllCSV_fileName, String inputDomain,int inputFilesNumberStart, int inputFilesNumberFinish) {

        brand=inputBrand; brandForXls =inputBrandForXls; dir=inputDir; writeAllCSV_fileName="EGOR_"+inputWriteAllCSV_fileName; domain=inputDomain;

        Long start = System.currentTimeMillis();

        filesArrayList = listFilesUsingJavaIO(dir);
        System.out.println(filesArrayList);

        int n = 1;
        for (String fileString : filesArrayList) {

              if ( (inputFilesNumberStart!=0) && (n<inputFilesNumberStart) ) { n++; continue; }

            Long startItem = System.currentTimeMillis();
            Item item = new Item(new File(dir + fileString), brandForXls);

            if (item.carObjectsList!=null)
            for (Car car : item.carObjectsList) {

                ArrayList<String> listingStringsArrayList = new ArrayList<>();

                listingStringsArrayList.add(item.sku);
                listingStringsArrayList.add(car.carItemSku.replace("_x000D_",""));
                // TODO: PrestaAttArrayListShutDown
                listingStringsArrayList.add((item.itemPrestaAttArrayList.toString()+car.carPrestaAttArrayList.toString())
                        .replace("[","").replace("]","").replace("; ,",","));
                //  listingStringsArrayList.add(item.itemPrestaAttArrayList.toString());
              //  System.out.println("item.itemPrestaAttArrayList.toString() "+item.itemPrestaAttArrayList.toString());
                // listingStringsArrayList.add(car.carPrestaAttArrayList.toString());
               // System.out.println("car.carPrestaAttArraylist.toString() "+car.carPrestaAttArrayList.toString());
                listingStringsArrayList.add(item.itemFullDescription);
                listingStringsArrayList.add(car.carLineString);
                listingStringsArrayList.add
                        (car.carPrestaCategoryStringWoYears+", "
                                +car.carPrestaCategoryStringWithYears+", "
                                +"All_Items"+", "
                                +"All_Items/"+item.itemType);                ;
               listingStringsArrayList.add(car.carWpCategoryString);
                listingStringsArrayList.add(item.picUrl);
               // listingStringsArrayList.add(item.shortDesc);
                // listingStringsArrayList.add(car.make);
                // listingStringsArrayList.add(car.model);
               // listingStringsArrayList.add(car.driveHashSet.toString());
                //  listingStringsArrayList.add(car.driveRangeString);
              //  System.out.println(car.driveRangeString);
               // listingStringsArrayList.add(car.yearNumbersSet.toString());
                //  listingStringsArrayList.add(car.yearNumbersString);
                //  listingStringsArrayList.add(car.carLiftNumbersString);

                /*String[] eachCarLineStringArray = new String[50];

                eachCarLineStringArray[0] = "";
                eachCarLineStringArray[1] = car.carItemSku;
                eachCarLineStringArray[2] = car.carValue;
                eachCarLineStringArray[3] = car.make;
                eachCarLineStringArray[4] = car.model;
                eachCarLineStringArray[5] = car.drive.toString();
                eachCarLineStringArray[6] = car.yearNumbersSet.toString();*/

                StringBuilder subCarSb = new StringBuilder();
                StringBuilder applicationFootnoteSb = new StringBuilder(); LinkedHashSet<String> applicationFootnoteLinkedHashSet=new LinkedHashSet<>();
                StringBuilder applicationAttributeSb = new StringBuilder(); LinkedHashSet<String> applicationAttributeLinkedHashSet=new LinkedHashSet<>();
                StringBuilder applicationAttributePositionSb = new StringBuilder(); LinkedHashSet<String> applicationAttributePositionLinkedHashSet=new LinkedHashSet();
                StringBuilder applicationAttributeLiftSb = new StringBuilder(); LinkedHashSet<String> applicationAttributeLiftLinkedHashSet=new LinkedHashSet();
                StringBuilder applicationAttributeLiftStartSb = new StringBuilder(); LinkedHashSet<String> applicationAttributeLiftStartLinkedHashSet=new LinkedHashSet();
                StringBuilder applicationAttributeLiftFinishSb = new StringBuilder(); LinkedHashSet<String> applicationAttributeLiftFinishLinkedHashSet=new LinkedHashSet();
                StringBuilder applicationAttributeLiftRangeSb = new StringBuilder(); LinkedHashSet<String> applicationAttributeLiftRangeLinkedHashSet=new LinkedHashSet();

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
                        applicationFootnoteLinkedHashSet.add(applicationFootnote.appFootnote_Value+System.lineSeparator());
                    }

                    for (ApplicationAttribute applicationAttribute : subCar.subCarAppAttribute_ObjectsList) {
                        applicationAttributeSb.append(applicationAttribute.applicationAttribute_Value).append(System.lineSeparator());
                        applicationAttributeLinkedHashSet.add(applicationAttribute.applicationAttribute_Value+System.lineSeparator());
                        // TODO: appAttPositionArrayList переделать вывод в ячейку
                        applicationAttributePositionSb.append(applicationAttribute.appAttPositionArrayList.toString()).append(System.lineSeparator());
                        applicationAttributePositionLinkedHashSet.add(applicationAttribute.appAttPositionArrayList+System.lineSeparator());
                        applicationAttributeLiftSb.append(applicationAttribute.liftString).append(System.lineSeparator());
                        applicationAttributeLiftLinkedHashSet.add(applicationAttribute.liftString+System.lineSeparator());
                        applicationAttributeLiftStartSb.append(applicationAttribute.appAttLiftNumberStart).append(System.lineSeparator());
                        applicationAttributeLiftStartLinkedHashSet.add(applicationAttribute.appAttLiftNumberStart+System.lineSeparator());
                        applicationAttributeLiftFinishSb.append(applicationAttribute.appAttLiftNumberFinish).append(System.lineSeparator());
                        applicationAttributeLiftFinishLinkedHashSet.add(applicationAttribute.appAttLiftNumberFinish+System.lineSeparator());
                        // for (double liftValue : applicationAttribute.appAttLiftNumbersSet) applicationAttributeLiftRangeSb.append(liftValue).append("|");
                        applicationAttributeLiftRangeSb.append(applicationAttribute.liftString).append(System.lineSeparator());
                        applicationAttributeLiftRangeLinkedHashSet.add(applicationAttribute.liftString+System.lineSeparator());
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
                else listingStringsArrayList.add(car.itemWpTitleString);

                if ( (item.itemFrontLiftString ==null) && (item.itemRearLiftString ==null) )
                    listingStringsArrayList.add(car.carPrestaTitleString);
                else listingStringsArrayList.add(car.itemPrestaTitleString);

               /* listingStringsArrayList.add(car.itemWpTitleString);
                listingStringsArrayList.add(car.carWpTitleString);*/

               /* System.out.println("itemTitleString = "+car.itemTitleString);
                System.out.println("carTitleString = "+car.carTitleString);*/
                /*String carRelatedDescription = applicationFootnoteSb.toString() +
                        applicationAttributeSb.toString() +
                        applicationAttributePositionSb.toString() +
                        applicationAttributeLiftSb.toString() +
                        applicationAttributeLiftStartSb.toString() +
                        applicationAttributeLiftFinishSb.toString() +
                        applicationAttributeLiftRangeSb.toString() +
                        car.carLiftNumbersSet.toString();*/
                String carRelatedDescription = applicationFootnoteLinkedHashSet.toString()+
                        applicationAttributeLinkedHashSet.toString() +
                       // applicationAttributePositionLinkedHashSet.toString()+
                        applicationAttributeLiftLinkedHashSet.toString()
                        /*+applicationAttributeLiftStartLinkedHashSet.toString()+
                        applicationAttributeLiftFinishLinkedHashSet.toString()+*/
                      //  applicationAttributeLiftRangeLinkedHashSet.toString()+"'' Lift"
                        ;
                carRelatedDescription =carRelatedDescription.replace("[","").replace("]","");
              //  listingStringsArrayList.add(carRelatedDescription);
                listingStringsArrayList.add(car.carItemSku.replace("_x000D_","")+" "+item.itemShortDescription.replace("[]",""));
                listingStringsArrayList.add((item.itemFullDescription +System.lineSeparator()+carRelatedDescription).replace("[]",""));
                listingStringsArrayList.add(item.brandForXls);


            /*    listingStringsArrayList.add(car.carAppAttLinkedHashSetString);
                listingStringsArrayList.add(car.appAttPositionString); // ? пустое - норм если не убирать Liftinit
                listingStringsArrayList.add(car.appAttLiftString);
                listingStringsArrayList.add(car.appAttLiftStartString);
                listingStringsArrayList.add(car.appAttLiftFinishString);
                listingStringsArrayList.add(car.appAttLiftRangeString);*/

               /* listingStringsArrayList.add(applicationFootnoteSb.toString());
                listingStringsArrayList.add(applicationAttributeSb.toString());
                listingStringsArrayList.add(applicationAttributePositionSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftStartSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftFinishSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftRangeSb.toString());

                listingStringsArrayList.add(car.carLiftNumbersSet.toString());*/

                // eachCarLineStringArray[15] = car.carLiftNumbersSet.toString();

              /*  int k = 0;
                for (String s : item.itemLineStringArray) {
                  //  eachCarLineStringArray[k + 16] = s;
                    listingStringsArrayList.add(s);
                    k++;
                }*/

                // listingStringsArrayList.addAll(Arrays.asList(item.itemLineStringArray)) ;
               // System.out.println("Arrays.asList(item.itemLineStringArray) = " + Arrays.asList(item.itemLineStringArray));

                //  arrayListOfAllStringsForCSV.add(eachCarLineStringArray);
                arrayListOfAllStringsForCSV.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));
            }

            // if no cars
            if (item.carObjectsList==null) {
                ArrayList<String> listingStringsArrayListNoCars = new ArrayList<>();

                listingStringsArrayListNoCars.add(item.sku);
                int k = 0;
                for (String s : item.itemLineStringArray) {
                    //  eachCarLineStringArray[k + 16] = s;
                    listingStringsArrayListNoCars.add(s);
                    k++;
                }

                arrayListOfAllStringsForCSV.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));
            }

            for (String[] stringArray:arrayListOfAllStringsForCSV) {
                if (stringArray.length>0)
                for (int i = 0; i <stringArray.length ; i++) {
                    if(stringArray[i]!=null)    stringArray[i]=stringArray[i].replaceAll("_x000D_"," ").replaceAll("null","");
                }
            }

            Long finishItem = System.currentTimeMillis();
            System.out.println(n++ +" "+brandForXls+"  processed for mS=" + ((finishItem - startItem) ));
             if ( (inputFilesNumberFinish!=0) && (n>inputFilesNumberFinish) ) break;
        }
        // write to EXCEL
        try { writeExcel(arrayListOfAllStringsForCSV, "PRESTA_"+brand); }
        catch (IOException e) { e.printStackTrace(); }
        // write to CSV
       /* CSVWriter csvWriter = csvWriterInit();
        csvWriter.writeAll(arrayListOfAllStringsForCSV, true);
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        Long finish = System.currentTimeMillis();
        System.out.println(filesArrayList.size() + " files processed in " + ((finish - start) / 1000) + " seconds");
        double avg=(double)(finish - start) / 1000/filesArrayList.size();
        System.out.println(  avg + " average seconds per one file");

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

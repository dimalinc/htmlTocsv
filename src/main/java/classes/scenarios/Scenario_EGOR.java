package classes.scenarios;


import classes.*;

import java.util.ArrayList;

public class Scenario_EGOR {

    public static void main(Item item,
                            ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR,
                            ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR) {

        if (item.carObjectsList!=null)
            for (Car car : item.carObjectsList) {

                ArrayList<String> listingStringsArrayList = new ArrayList<>();

                listingStringsArrayList.add(item.sku);
                listingStringsArrayList.add(car.carItemSku);
                listingStringsArrayList.add(item.itemType);
                listingStringsArrayList.add(item.itemFullDescription);
                listingStringsArrayList.add(item.attributesFromDesHTML);
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

                int k = 0;
                for (String s : item.itemLineStringArray) {//  eachCarLineStringArray[k + 16] = s;
                    listingStringsArrayList.add(s);
                    k++;
                }
                // listingStringsArrayList.addAll(Arrays.asList(item.itemLineStringArray)) ;
                // System.out.println("Arrays.asList(item.itemLineStringArray) = " + Arrays.asList(item.itemLineStringArray));
                //  arrayListOfAllStringsForCSV.add(eachCarLineStringArray);
                arrayListOfAllStringsForCSV_EGOR.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));
            }

        // adding rows for  noCar items
        if (item.carObjectsList==null) {
            ArrayList<String> listingStringsArrayListNoCars = new ArrayList<>();

            listingStringsArrayListNoCars.add(item.sku);
            int k = 0;
            for (String s : item.itemLineStringArray) {
                //  eachCarLineStringArray[k + 16] = s;
                listingStringsArrayListNoCars.add(s);
                k++;
            }

            arrayListOfAllStringsForCSV_EGOR.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));
            arrayListOfAllStringsForCSV_noCars_EGOR.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));

        }

        // replacing wrong symbols
        for (String[] stringArray: arrayListOfAllStringsForCSV_EGOR) {
            if (stringArray.length>0)
                for (int i = 0; i <stringArray.length ; i++) {
                    if(stringArray[i]!=null)  stringArray[i].replaceAll("_x000D_"," ");
                }
        }

    }
}

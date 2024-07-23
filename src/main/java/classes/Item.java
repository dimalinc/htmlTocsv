package classes;

import classes.utils.data_processing_utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static classes.PrestaAttribute.*;
import static classes.utils.data_processing_utils.*;


public class Item implements Runnable {

    static ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR;
    static ArrayList<String[]> arrayListOfAllStringsForCSV_Presta;
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR;
    static ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_Presta;

    public ArrayList<String> itemFullLineArrayListStringForMultithreading_cars;
    public ArrayList<String> itemFullLineArrayListStringForMultithreading_NO_cars;

    public static String domain = "";
    public static String brandForXls;
    /*  ArrayList<Element> carElementsList;
      ArrayList<Element> jsoupElementsList = new ArrayList<>();*/
    public ArrayList<String> xpathStringsList = data_processing_utils.xpathStringsListInit();
    public File inputFile;
    public Document doc;
    public ArrayList<Car> carObjectsList = new ArrayList<>();
    public String[] itemLineStringArray;
    //  protected static String lineSeparator ="&CHAR(10)&";
    public static String lineSeparator = "<br>"/*System.lineSeparator()*/;

    public String sku;
    public String keystoneShortDesc;
    public String keystoneItemTitle;
    public LinkedHashSet<String> itemPositionArrayList = new LinkedHashSet<>();
    public String itemFrontLiftString;
    public String itemRearLiftString;
    public double itemLiftMin;
    public double itemLiftMax;
    public double itemFrontLiftNumberStart = -100.0;
    public double itemFrontLiftNumberFinish = -100.0;
    public double itemRearLiftNumberStart = -100.0;
    public double itemRearLiftNumberFinish = -100.0;
    public LinkedHashSet<Double> itemLiftNumbersSet = new LinkedHashSet<>();
    public String itemLiftNumbersString;
    public String itemType;
    public String productInfoAttributesDesc;
    public String attributesFromDesHTML;
    public String longDesc;
    public String longDescWoAttribsHTML;
    public String pdfUrl;
    public String picUrl;
    public String kitComponents;
    public ArrayList<KitComponent> kitComponentsArrayList = new ArrayList<>();
    public ArrayList<String> stringListItemLiAttributes = new ArrayList<>();
    public String extendedLength;
    String collapsedLength;
    String upperMount;
    String lowerMount;
    public ArrayList<PrestaAttribute> itemPrestaAttArrayList = new ArrayList<>();

    public String itemShortDescription;
    public String itemFullDescription;

    @Override
    public void run() {
        this.toStringArray();
    }

    public String[] toStringArray() {
        ArrayList<String> listingStringsArrayList = new ArrayList<>();

        if (this.carObjectsList != null)
            for (Car car : this.carObjectsList) {

                listingStringsArrayList.add(this.sku);
                listingStringsArrayList.add(car.carItemSku.replace("//r//n",""));
                listingStringsArrayList.add(this.itemType);
                listingStringsArrayList.add(this.itemFullDescription);
                listingStringsArrayList.add(this.attributesFromDesHTML);
                listingStringsArrayList.add(this.longDescWoAttribsHTML);
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
                        +item.itemFrontLift+" " +item.itemPositionArrayList.toString()*//*+"-"+item.itemRearLift+item.itemPositionArrayList*//*;*/
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

                if ((this.itemFrontLiftString == null) && (this.itemRearLiftString == null))
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
                for (String s : this.itemLineStringArray) {//  eachCarLineStringArray[k + 16] = s;
                    listingStringsArrayList.add(s);
                    k++;
                }
                // listingStringsArrayList.addAll(Arrays.asList(item.itemLineStringArray)) ;
                // System.out.println("Arrays.asList(item.itemLineStringArray) = " + Arrays.asList(item.itemLineStringArray));
                //  arrayListOfAllStringsForCSV.add(eachCarLineStringArray);
                arrayListOfAllStringsForCSV_EGOR.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));


            }

        // adding rows for  noCar items
        if (this.carObjectsList == null) {
            ArrayList<String> listingStringsArrayListNoCars = new ArrayList<>();

            listingStringsArrayListNoCars.add(this.sku);
            Collections.addAll(listingStringsArrayListNoCars, this.itemLineStringArray);

            arrayListOfAllStringsForCSV_noCars_EGOR.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));
        }


        return listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]);
    }

    void fullAndShortDescInit() {
        StringBuilder sb = new StringBuilder();
        sb.append(sku).append(" ").append(brandForXls).append(" ").append(itemType).append(lineSeparator);
        if (itemLiftNumbersSet != null)
            sb.append("For ").append(itemLiftNumbersSet).append(" '' Lift").append(lineSeparator);
        if (itemPositionArrayList != null) sb.append(itemPositionArrayList).append(lineSeparator);

        if (extendedLength != null) sb.append("Extended length, in. :").append(extendedLength).append(lineSeparator);
        if (collapsedLength != null) sb.append("Collapsed length, in. :").append(collapsedLength).append(lineSeparator);
        if (upperMount != null) sb.append("Upper mount: ").append(upperMount).append(lineSeparator);
        if (lowerMount != null) sb.append("Lower Mount: ").append(lowerMount).append(lineSeparator);

        sb.append(keystoneShortDesc).append(lineSeparator);
        sb.append(longDesc).append(lineSeparator);
        sb.append(productInfoAttributesDesc).append(lineSeparator);
        sb.append(pdfUrl).append(lineSeparator);
        // sb.append(kitComponents).append(lineSeparator); //TODO: тут не KitComponents а почему-то цены выводятся - проверить?
        if (stringListItemLiAttributes != null)
            sb.append(stringListItemLiAttributes).append(lineSeparator); //TODO: сделать с новой строки каждый элемент списка?
        // if (itemPrestaAttArrayList!=null) sb.append(itemPrestaAttArrayList).append(lineSeparator);
        if (kitComponentsArrayList.size() > 0) {
            sb.append(lineSeparator).append("Kit components: ").append(lineSeparator);
            sb.append(kitComponentsArrayList.toString().replace("[", "").replace("]", ""));
        }
        itemFullDescription = sb.toString().replace("For [] '' Lift","").replace("[]","").replace("null","");

        itemShortDescription = keystoneShortDesc.replace("; ",lineSeparator);
        if (extendedLength != null || collapsedLength != null || upperMount != null || lowerMount != null)
            itemShortDescription = itemShortDescription + lineSeparator + "Ext. length: " + extendedLength + lineSeparator +
                    "Coll. lenght: " + collapsedLength + lineSeparator + "Upper mount: " + upperMount + "Lower mount: " + lowerMount;

        // System.out.println("Short description="+ itemShortDescription);
    }

    public Item() { }

    void kitComponentsArrayListInit() {
        ArrayList<Element> kitComponentElementsList = new ArrayList<>();
        kitComponentElementsList = doc.selectXpath(KitComponent.kitComponentsXpath);

        for (int i = 0; i < kitComponentElementsList.size(); i++) {
            kitComponentsArrayList.add(new KitComponent(i, this));
        }
    }

    void lengthMountsInit() {
        //  System.out.println("void lengthMountsInit()");
        String liAttributesOneString = data_processing_utils.generateCellContentText("//div[@class='product-detail-attributes']/ul/li", doc);
        if (liAttributesOneString != null)
            Collections.addAll(stringListItemLiAttributes, liAttributesOneString.split(lineSeparator));
        // System.out.println(stringListItemLiAttributes.size());

        if (stringListItemLiAttributes != null) {
            for (String attributeLiString : stringListItemLiAttributes) {
                if (attributeLiString.contains("Extended Length"))
                    extendedLength = attributeLiString.split(":")[1].trim();
                if (attributeLiString.contains("Compressed Length"))
                    collapsedLength = attributeLiString.split(":")[1].trim();
                if (attributeLiString.contains("Upper Mounting")) upperMount = attributeLiString.split(":")[1].trim();
                if (attributeLiString.contains("Lower Mounting")) lowerMount = attributeLiString.split(":")[1].trim();
            }
        }
       /* System.out.println(extendedLength);
        System.out.println(collpasedLength);
        System.out.println(upperMount);
        System.out.println(lowerMount);*/
    }

    void itemLiftsPositionsInit(ArrayList<String> shortDescSplitArrayList) {
        // Positions
        for (String s : shortDescSplitArrayList) {
            positionsInit(s);

            // было внутри предыдущего цикла пока не стали тестить левелинг киты https://wwwsc.ekeystone.com/Search/Detail?pid=SKYTT20MSP
            // раньше было EndsWith "Inch Lift"
            if (s.contains("Lift") || s.contains("Drop")) {
                if (!s.contains("Lift Kit Suspension") && !s.contains("Front Springs") && !s.contains("Rear Lift Blocks")
                        && !s.contains("Rear Add-A-Leafs") && !s.contains("Drop Pitman Arm")) {
                    s = s.replace("For", "");
                    itemFrontLiftString = s.trim();
                    itemRearLiftString = s.trim();
                    // System.out.println(sku + " " + itemFrontLiftString + " ** " + itemRearLiftString);
                }

                if (itemType.contains("Leveling Kit")) {
                    if ((itemFrontLiftString != null) && (itemRearLiftString != null) && (itemFrontLiftString.equals(itemRearLiftString)))
                        itemRearLiftString = null;
                    itemPositionArrayList.add("Front");
                    itemPositionArrayList.remove("Rear");
                }

                if ((itemPositionArrayList.contains("Front")) && (itemPositionArrayList.contains("Rear"))
                    /* && (!s.contains("With Front Springs")) && (!s.contains("Rear Lift Blocks") )*/) {
                    itemPositionArrayList.clear();
                    itemPositionArrayList.add("Full Front and Rear");
                }

                if (itemPositionArrayList.contains("Front and Rear")) {
                    itemPositionArrayList.clear();
                    itemPositionArrayList.add("Full Front and Rear");
                }
            }

            liftsInit();

        }
    }

    void positionsInit(String s) {
        if (s.contains("Front")) {
            itemPositionArrayList.add("Front");
        }
        if (s.contains("Rear")) {
            itemPositionArrayList.add("Rear");
        }

        // было endsWith вместо contains
        if ((s.contains("Front Lift")) || (s.contains("Front/ Rear Lift"))) itemFrontLiftString = s.trim();
        if ((s.contains("Rear Lift")) || (s.contains("Front/ Rear Lift"))) itemRearLiftString = s.trim();

        // было endsWith вместо contains
        if ((s.contains("Front Drop")) || (s.contains("Front/ Rear Drop"))) itemFrontLiftString = s.trim();
        if ((s.contains("Rear Drop")) || (s.contains("Front/ Rear Drop"))) itemRearLiftString = s.trim();
    }

    void liftsInit() {

        // checking if contains word Lift twice
        if (itemFrontLiftString != null &&
                (((itemFrontLiftString.length() - itemFrontLiftString.replaceAll("Lift", "").length() > "Lift".length())
                        && itemFrontLiftString.contains("Lift/ "))
                        || ((itemFrontLiftString.length() - itemFrontLiftString.replaceAll("Drop", "").length() > "Drop".length())
                        && itemFrontLiftString.contains("Drop/ ")))) {
            String itemInputFrontLiftString = new String(itemFrontLiftString.getBytes(StandardCharsets.UTF_8));
            itemFrontLiftString = itemInputFrontLiftString.trim().split("/ ")[0]; // System.out.println("itemInputFrontLiftString.split(\"/ \")[0] = "+itemFrontLiftString.split("/ ")[0]);
            itemRearLiftString = itemInputFrontLiftString.trim().split("/ ")[1];  // System.out.println("itemInputFrontLiftString.split(\"/ \")[1] = "+itemInputFrontLiftString.split("/ ")[1]);
        }

        try {
            if (itemFrontLiftString != null)
                if (itemFrontLiftString.contains(" To ") || itemFrontLiftString.contains(" to ")) {
                    itemFrontLiftNumberStart = makeDoubleArray(itemFrontLiftString)[0];
                    itemFrontLiftNumberFinish = makeDoubleArray(itemFrontLiftString)[1];
                }
                // else     if (!itemFrontLiftString.contains("Lift"))
                else if (itemFrontLiftString.contains("Lift")/*&&(!itemFrontLiftString.contains("Lift Kit"))*/) {
                    itemFrontLiftNumberStart = makeDoubleArray(itemFrontLiftString)[0];
                    itemFrontLiftNumberFinish = makeDoubleArray(itemFrontLiftString)[0];
                } else {
                    itemFrontLiftNumberStart = -100.0;
                    itemFrontLiftNumberFinish = -100.0;
                }

            if (itemRearLiftString != null)
                if (itemRearLiftString.contains(" To ") || itemRearLiftString.contains(" to ")) {
                    itemRearLiftNumberStart = makeDoubleArray(itemRearLiftString)[0];
                    itemRearLiftNumberFinish = makeDoubleArray(itemRearLiftString)[1];
                }
                // else if (!itemRearLiftString.contains("Lift"))
                else if (itemRearLiftString.contains("Lift")/*&&(!itemRearLiftString.contains("Lift Kit"))*/) {
                    itemRearLiftNumberStart = makeDoubleArray(itemRearLiftString)[0];
                    itemRearLiftNumberFinish = makeDoubleArray(itemRearLiftString)[0];
                } else {
                    itemRearLiftNumberStart = -100.0;
                    itemRearLiftNumberFinish = -100.0;
                }

            if ((itemFrontLiftString != null) && (itemFrontLiftString.contains(("Drop"))) && (itemRearLiftNumberStart != -100.0))
                itemRearLiftNumberStart = itemRearLiftNumberStart * -1.0;
            if ((itemRearLiftString != null) && (itemRearLiftString.contains(("Drop"))) && (itemRearLiftNumberFinish != -100.0))
                itemRearLiftNumberFinish = itemRearLiftNumberFinish * -1.0;

            // if ( (itemFrontLiftNumberStart!=0)||(itemFrontLiftNumberFinish!=0)||(itemRearLiftNumberStart!=0)||(itemRearLiftNumberFinish!=0) ) System.out.println("itemFr/Re_Lift.split [0] = " + itemFrontLiftNumberStart+"-"+itemFrontLiftNumberFinish + " -_- " + itemRearLiftNumberStart+"-"+itemRearLiftNumberFinish);


        } catch (Exception e) {
            System.out.println(this.sku);
            e.printStackTrace(); }

        // System.out.println(itemFrontLiftNumberStart +" "+ itemFrontLiftNumberFinish+" " + itemRearLiftNumberStart+" "+ itemRearLiftNumberFinish);

        if ((itemFrontLiftNumberStart == -100.0) || (itemRearLiftNumberStart == -100.0))
            itemLiftMin = Double.max(itemFrontLiftNumberStart, itemRearLiftNumberStart);
        else itemLiftMin = Double.min(itemFrontLiftNumberStart, itemRearLiftNumberStart);

        if ((itemFrontLiftNumberFinish == -100.0) || (itemRearLiftNumberFinish == -100.0))
            itemLiftMax = Double.max(itemFrontLiftNumberFinish, itemRearLiftNumberFinish);
        else itemLiftMax = Double.max(itemFrontLiftNumberFinish, itemRearLiftNumberFinish);

        if ((itemLiftMin > -100.0) && (itemLiftMax > -100.0))
            for (double liftValue = itemLiftMin; liftValue <= itemLiftMax; liftValue += 0.25) {
                itemLiftNumbersSet.add(liftValue);
                if (liftValue > itemLiftMax) itemLiftNumbersSet.add(itemLiftMax);
            }

        if ((itemLiftMin > -100.0) || (itemLiftMax > -100.0)) {
            if (Double.max(itemLiftMin, itemLiftMax) != -100)
                itemLiftNumbersSet.add(Double.max(itemLiftMin, itemLiftMax));
            if (Double.min(itemLiftMin, itemLiftMax) != -100)
                itemLiftNumbersSet.add(Double.min(itemLiftMin, itemLiftMax));
        }

        StringBuilder sbItemLiftNumbers = new StringBuilder();
        for (double d : itemLiftNumbersSet) {
            sbItemLiftNumbers.append(d).append("|");
        }

        if (itemLiftNumbersSet.size() > 0)
            try {
                itemLiftNumbersString = sbItemLiftNumbers.substring(0, sbItemLiftNumbers.toString().length() - 1);
            } catch (StringIndexOutOfBoundsException e) {
                e.printStackTrace();
                itemLiftNumbersString = null;
            }

        // System.out.println("itemLiftNumbersSet = " + itemLiftNumbersSet);
        // System.out.println("itemLiftNumbersString = " + itemLiftNumbersString);
    }

    void populate_Presta_itemFullLineStringArrayForMultithreading_cars() {
        if (this.carObjectsList.size()>0)
            for (Car car : this.carObjectsList) {
                ArrayList<String> listingStringsArrayList = new ArrayList<>();

                listingStringsArrayList.add(this.sku);
                listingStringsArrayList.add(brandForXls);
                listingStringsArrayList.add(car.carItemSku);
                listingStringsArrayList.add(this.itemType);
                listingStringsArrayList.add(this.itemFullDescription);
                listingStringsArrayList.add(this.itemShortDescription);
                listingStringsArrayList.add(this.attributesFromDesHTML);
                listingStringsArrayList.add(this.longDescWoAttribsHTML);
                listingStringsArrayList.add(this.picUrl);
                listingStringsArrayList.add(car.carLineString);
                listingStringsArrayList.add((this.itemPrestaAttArrayList.toString()+car.carPrestaAttArrayList.toString())
                        .replace("[","").replace("]","").replace("; ,",","));
                listingStringsArrayList.add
                        (car.carPrestaCategoryStringWoYears+", "
                                +car.carPrestaCategoryStringWithYears+", "
                                +"All_Items"+", "
                                +"All_Items/"+this.itemType);
                listingStringsArrayList.add(car.carWpCategoryString);
                listingStringsArrayList.add(car.make);
                listingStringsArrayList.add(car.model);
                listingStringsArrayList.add(car.driveRangeString);
                listingStringsArrayList.add(car.yearNumbersString);
                listingStringsArrayList.add(car.carLiftNumbersString);

                if ((this.itemFrontLiftString == null) && (this.itemRearLiftString == null))
                    listingStringsArrayList.add("ItemLiftStrings=0");
                else listingStringsArrayList.add("");

                listingStringsArrayList.add(" TITLES WP ");
                listingStringsArrayList.add(car.itemWpTitleString);
                listingStringsArrayList.add(car.carWpTitleString);

                listingStringsArrayList.add(" TITLES PRE ");
                listingStringsArrayList.add(car.itemPrestaTitleString);
                listingStringsArrayList.add(car.carPrestaTitleString);


                StringBuilder subCarSb = new StringBuilder();
                StringBuilder applicationFootnoteSb = new StringBuilder();
                StringBuilder applicationAttributeSb = new StringBuilder();
                StringBuilder applicationAttributePositionSb = new StringBuilder();
                StringBuilder applicationAttributeLiftSb = new StringBuilder();
                StringBuilder applicationAttributeLiftStartSb = new StringBuilder();
                StringBuilder applicationAttributeLiftFinishSb = new StringBuilder();
                StringBuilder applicationAttributeLiftRangeSb = new StringBuilder();

                for (SubCar subCar : car.subCarObjectsList) {
                    //  subCarSb.append(subCar.subCarValue).append(System.lineSeparator());

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

                }

                listingStringsArrayList.add(subCarSb.toString());
                listingStringsArrayList.add(applicationFootnoteSb.toString());
                listingStringsArrayList.add(applicationAttributeSb.toString());
                listingStringsArrayList.add(applicationAttributePositionSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftStartSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftFinishSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftRangeSb.toString());

                listingStringsArrayList.add(car.carLiftNumbersSet.toString());

                arrayListOfAllStringsForCSV_Presta.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));
            }
    }

    void populate_Presta_itemFullLineStringArrayForMultithreading_NO_cars() {
        // adding rows for  noCar items
        if (this.carObjectsList.size()==0) {

            System.out.println("!!! found item NO_cars "+ this.sku.trim());
            ArrayList<String> listingStringsArrayListNoCars = new ArrayList<>();
            System.out.println();
            listingStringsArrayListNoCars.add(this.sku);
            listingStringsArrayListNoCars.add(brandForXls);
            int k = 0;
            for (String s : this.itemLineStringArray) {
                //  eachCarLineStringArray[k + 16] = s;
                listingStringsArrayListNoCars.add(s);
                k++;
            }

            arrayListOfAllStringsForCSV_noCars_Presta.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));
        }
    }

    void populate_EGOR_itemFullLineStringArrayForMultithreading_cars() {

        if (this.carObjectsList.size()>0)
            for (Car car : this.carObjectsList) {
                ArrayList<String> listingStringsArrayList = new ArrayList<>();

                listingStringsArrayList.add(this.sku);
                listingStringsArrayList.add(brandForXls);
                listingStringsArrayList.add(car.carItemSku);
                listingStringsArrayList.add(this.itemType);
                listingStringsArrayList.add(this.itemFullDescription);
                listingStringsArrayList.add(this.attributesFromDesHTML);
                listingStringsArrayList.add(this.longDescWoAttribsHTML);
                listingStringsArrayList.add(this.picUrl);
                listingStringsArrayList.add(car.carLineString);


                listingStringsArrayList.add(car.subCarObjectsList.toString());
                listingStringsArrayList.add(car.subCarEnginesLinkedHashSet.toString());

                StringBuilder sbEngine = new StringBuilder();
                for (String subCarEngineString:car.subCarEnginesLinkedHashSet) {
                    sbEngine.append(subCarEngineString).append(System.lineSeparator());
                }
                listingStringsArrayList.add(sbEngine.toString());

                listingStringsArrayList.add(car.carAppAttLinkedHashSet.toString());
                listingStringsArrayList.add(car.carAppFootnoteLinkedHashSet.toString());
               // System.out.println(car.subCarObjectsList.toString() );
              //  System.out.println(car.carAppAttLinkedHashSet.toString() );
               // System.out.println(car.carAppFootnoteLinkedHashSet.toString() );


                listingStringsArrayList.add(car.carWpCategoryString);
                listingStringsArrayList.add(car.make);
                listingStringsArrayList.add(car.model);
                listingStringsArrayList.add(car.driveRangeString);
                listingStringsArrayList.add(car.yearNumbersString);
                listingStringsArrayList.add(car.carLiftNumbersString);

                if ((this.itemFrontLiftString == null) && (this.itemRearLiftString == null))
                    listingStringsArrayList.add("ItemLiftStrings=0");
                else listingStringsArrayList.add(car.carWpTitleString);

                listingStringsArrayList.add(car.itemWpTitleString);
                listingStringsArrayList.add(car.carWpTitleString);


                StringBuilder subCarSb = new StringBuilder();
                StringBuilder applicationFootnoteSb = new StringBuilder();
                StringBuilder applicationAttributeSb = new StringBuilder();
                StringBuilder applicationAttributePositionSb = new StringBuilder();
                StringBuilder applicationAttributeLiftSb = new StringBuilder();
                StringBuilder applicationAttributeLiftStartSb = new StringBuilder();
                StringBuilder applicationAttributeLiftFinishSb = new StringBuilder();
                StringBuilder applicationAttributeLiftRangeSb = new StringBuilder();

                for (SubCar subCar : car.subCarObjectsList) {
                  //  subCarSb.append(subCar.subCarValue).append(System.lineSeparator());

                    for (ApplicationFootnote applicationFootnote : subCar.subCarAppFootnote_ObjectsList) {
                        applicationFootnoteSb.append(applicationFootnote.appFootnote_Value).append(System.lineSeparator()); }

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
                }
                listingStringsArrayList.add(subCarSb.toString());
                listingStringsArrayList.add(applicationFootnoteSb.toString());
                listingStringsArrayList.add(applicationAttributeSb.toString());
                listingStringsArrayList.add(applicationAttributePositionSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftStartSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftFinishSb.toString());
                listingStringsArrayList.add(applicationAttributeLiftRangeSb.toString());

                listingStringsArrayList.add(car.carLiftNumbersSet.toString());

                arrayListOfAllStringsForCSV_EGOR.add(listingStringsArrayList.toArray(new String[listingStringsArrayList.size()]));
            }


    }

    void populate_EGOR_itemFullLineStringArrayForMultithreading_NO_cars() {
        // adding rows for  noCar items
        if (this.carObjectsList.size()==0) {

            System.out.println("!!! found item NO_cars "+ this.sku.trim());
            ArrayList<String> listingStringsArrayListNoCars = new ArrayList<>();
            System.out.println();
            listingStringsArrayListNoCars.add(this.sku);
            listingStringsArrayListNoCars.add(brandForXls);
            int k = 0;
            for (String s : this.itemLineStringArray) {
                //  eachCarLineStringArray[k + 16] = s;
                listingStringsArrayListNoCars.add(s);
                k++;
            }

            arrayListOfAllStringsForCSV_noCars_EGOR.add(listingStringsArrayListNoCars.toArray(new String[listingStringsArrayListNoCars.size()]));
        }


    }


    void itemFieldsInit() {
        try {
            doc = Jsoup.parse(inputFile, "UTF-8", domain);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            lengthMountsInit();
            sku = data_processing_utils.generateCellContentText(xpathStringsList.get(0), doc).replace("\r\n","");
            keystoneItemTitle = data_processing_utils.generateCellContentText(xpathStringsList.get(1), doc);
            keystoneShortDesc = data_processing_utils.generateCellContentText(xpathStringsList.get(2), doc);


            ArrayList<String> shortDescSplitArrayList = new ArrayList<>(Arrays.asList(keystoneShortDesc.split(";")));
            itemType = shortDescSplitArrayList.get(0);

            itemLiftsPositionsInit(shortDescSplitArrayList);

            attributesFromDesHTML = data_processing_utils.generateCellContentHTML(xpathStringsList.get(3), doc);

            longDesc = data_processing_utils.generateCellContentText(xpathStringsList.get(3), doc) + data_processing_utils.generateCellContentText(xpathStringsList.get(4), doc) + data_processing_utils.generateCellContentText(xpathStringsList.get(5), doc);
            longDesc=longDesc.replace("\\r\\n","\\n");
            longDescWoAttribsHTML = data_processing_utils.generateCellContentText(xpathStringsList.get(4), doc) + data_processing_utils.generateCellContentText(xpathStringsList.get(5), doc);
            pdfUrl = data_processing_utils.generateCellContentText(xpathStringsList.get(8), doc);
            // picUrl = data_processing_utils.generateCellContent(xpathStringsList.get(9), doc);
            picUrl = data_processing_utils.generateCellContentForPicUrl(xpathStringsList.get(9), doc, ", ");
            // System.out.print("itemType = " + itemType + " SKU=  " + sku);

            // System.out.println(xpathStringsList.get(12));
            kitComponents = data_processing_utils.generateCellContentText(xpathStringsList.get(12), doc).replace("Special Order - call 1-800-521-9999 for ordering, lead time, and shipping charges 1-800-521-9999", "");
            kitComponentsArrayListInit();

            //  carObjectsList = new ArrayList<>();
            int i = 1; //xpath outnumbered from 1
            for (Element carWebElement : doc.selectXpath(Car.cars_Xpath)) {
               // carObjectsList.add(new Car(i++, this, brandForXls));
                carObjectsList.add(new Car(carWebElement,i++, this, brandForXls));
            }
            // TODO: another way to make new Cars


        }

        itemLineStringArray = new String[xpathStringsList.size() + 20];
        for (int i = 0; i < xpathStringsList.size(); i++) {
            itemLineStringArray[i] = data_processing_utils.generateCellContentText(xpathStringsList.get(i), doc);
        }
        itemLineStringArray[xpathStringsList.size() + 1] = "-_-";
        itemLineStringArray[xpathStringsList.size() + 2] = "-_-";
        itemLineStringArray[xpathStringsList.size() + 3] = itemFrontLiftString;
        itemLineStringArray[xpathStringsList.size() + 4] = String.valueOf(itemFrontLiftNumberStart);
        itemLineStringArray[xpathStringsList.size() + 5] = String.valueOf(itemFrontLiftNumberFinish);
        itemLineStringArray[xpathStringsList.size() + 6] = itemRearLiftString;
        itemLineStringArray[xpathStringsList.size() + 7] = String.valueOf(itemRearLiftNumberStart);
        itemLineStringArray[xpathStringsList.size() + 8] = String.valueOf(itemRearLiftNumberFinish);
        itemLineStringArray[xpathStringsList.size() + 9] = itemPositionArrayList.toString();
        itemLineStringArray[xpathStringsList.size() + 10] = itemLiftNumbersSet.toString();
        itemLineStringArray[xpathStringsList.size() + 11] = itemLiftNumbersString;

        //  ArrayList<PrestaAttribute> itemPrestaAttributesList= prestaAttributesListCreate("Item Lift",  carLineString,item.itemLiftNumbersSet );
        // itemPrestaAttArrayList.addAll(itemPrestaAttributesList);

       /* if (extendedLength!=null)
        itemPrestaAttArrayList.add(new PrestaAttribute("Extended Length","",extendedLength));
        if (collpasedLength!=null)
        itemPrestaAttArrayList.add(new PrestaAttribute("Collapsed Length","",collpasedLength));
        if (upperMount!=null)
        itemPrestaAttArrayList.add(new PrestaAttribute("Upper Mount","",upperMount));
        if (lowerMount!=null)
        itemPrestaAttArrayList.add(new PrestaAttribute("Lower Mount","",lowerMount));
*/
        itemPrestaAttArrayListInit();

        fullAndShortDescInit();

        // System.out.println("itemPrestaAttArrayList = "+itemPrestaAttArrayList);
    }

    public Item(File inputFile) {
        brandForXls = "";
        this.inputFile = inputFile;
        itemFieldsInit();
    }

    public Item(File inputFile, String inputBrandForXls) {
        brandForXls = inputBrandForXls;
        this.inputFile = inputFile;
        itemFieldsInit();
    }


    public Item(File inputFile, ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR) {
        brandForXls = "";
        this.inputFile = inputFile;
        Item.arrayListOfAllStringsForCSV_EGOR = arrayListOfAllStringsForCSV_EGOR;
        itemFieldsInit();

        populate_EGOR_itemFullLineStringArrayForMultithreading_cars();
        populate_Presta_itemFullLineStringArrayForMultithreading_cars();
    }

    public Item(File inputFile, ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR, ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR) {
        brandForXls = "";
        this.inputFile = inputFile;
        Item.arrayListOfAllStringsForCSV_EGOR = arrayListOfAllStringsForCSV_EGOR;
        Item.arrayListOfAllStringsForCSV_noCars_EGOR = arrayListOfAllStringsForCSV_noCars_EGOR;

        itemFieldsInit();

        /*arrayListOfAllStringsForCSV.add(itemFullLineArrayListStringForMultithreading_cars);
        arrayListOfAllStringsForCSV_noCars.add(itemFullLineArrayListStringForMultithreading_NO_cars);*/

        populate_EGOR_itemFullLineStringArrayForMultithreading_cars();
        populate_EGOR_itemFullLineStringArrayForMultithreading_NO_cars();

        populate_Presta_itemFullLineStringArrayForMultithreading_cars();
        populate_Presta_itemFullLineStringArrayForMultithreading_NO_cars();
    }

    public Item(File inputFile, String inputBrandForXls,
                ArrayList<String[]> arrayListOfAllStringsForCSV_EGOR,
                ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_EGOR,
                ArrayList<String[]> arrayListOfAllStringsForCSV_Presta,
                ArrayList<String[]> arrayListOfAllStringsForCSV_noCars_Presta) {
        brandForXls = inputBrandForXls;
        this.inputFile = inputFile;
        Item.arrayListOfAllStringsForCSV_EGOR = arrayListOfAllStringsForCSV_EGOR;
        Item.arrayListOfAllStringsForCSV_noCars_EGOR = arrayListOfAllStringsForCSV_noCars_EGOR;
        Item.arrayListOfAllStringsForCSV_Presta = arrayListOfAllStringsForCSV_Presta;
        Item.arrayListOfAllStringsForCSV_noCars_Presta = arrayListOfAllStringsForCSV_noCars_Presta;

        itemFieldsInit();

        populate_EGOR_itemFullLineStringArrayForMultithreading_cars();
        populate_EGOR_itemFullLineStringArrayForMultithreading_NO_cars();

        populate_Presta_itemFullLineStringArrayForMultithreading_cars();
        populate_Presta_itemFullLineStringArrayForMultithreading_NO_cars();
    }


    void itemPrestaAttArrayListInit() {
        ArrayList<PrestaAttribute> itemPrestaAttributesList;

        if (this.itemLiftNumbersSet.size() > 0) {
            itemPrestaAttributesList = prestaAttributesListCreate("Item Lift", "", doubleToStringListWithDecimals(new ArrayList<>(this.itemLiftNumbersSet)));
            itemPrestaAttArrayList.addAll(itemPrestaAttributesList);
        }

        if (extendedLength != null)
            itemPrestaAttArrayList.add(new PrestaAttribute("Extended Length", "",
                    String.valueOf(data_processing_utils.stringToDoubleLengthDecoding(extendedLength.replace(" Inch", "").replace(" OEM", "").replace("OEM", "")))));
        if (collapsedLength != null)
            itemPrestaAttArrayList.add(new PrestaAttribute("Collapsed Length", "",
                    String.valueOf(data_processing_utils.stringToDoubleLengthDecoding(collapsedLength.replace(" Inch", "").replace(" OEM", "").replace("OEM", "")))));
        if (upperMount != null)
            itemPrestaAttArrayList.add(new PrestaAttribute("Upper Mount", "", upperMount));
        if (lowerMount != null)
            itemPrestaAttArrayList.add(new PrestaAttribute("Lower Mount", "", lowerMount));
        if (itemType != null)
            itemPrestaAttArrayList.add(new PrestaAttribute("Item type", "", itemType));
        if (brandForXls != null)
            itemPrestaAttArrayList.add(new PrestaAttribute("Brand", "", brandForXls));

        List<String> newStringList = new ArrayList<>(itemPositionArrayList);
        itemPrestaAttributesList = prestaAttributesListCreate("Position ", "", newStringList);
        itemPrestaAttArrayList.addAll(itemPrestaAttributesList);

    }

    /*void addToPrestaAttArrayList(ArrayList<PrestaAttribute> itemPrestaAttArrayList,String prestaAttName,String s) {
        if (s!=null)
            itemPrestaAttArrayList.add(new PrestaAttribute(prestaAttName,"",s));
    }

    void addToPrestaAttArrayList(ArrayList<PrestaAttribute> itemPrestaAttArrayList,String prestaAttName,double d) {
        if (d != -100.0)
            itemPrestaAttArrayList.add(new PrestaAttribute(prestaAttName, "", String.valueOf(d)));
    }

    void addToPrestaAttArrayList(ArrayList<PrestaAttribute> itemPrestaAttArrayList,String prestaAttName,
                                 ArrayList<String> stringValuesArrayList ) {
        if ( (stringValuesArrayList != null) && (stringValuesArrayList.size()>0) )
            itemPrestaAttArrayList.addAll(prestaAttributesListCreate(prestaAttName, "", stringValuesArrayList));
    }*/


  /*  //
    String generateCellContent(String xpath, Document doc) {
        if ( (xpath.contains("href")) || (xpath.contains("src")) ) {
            jsoupElementsList = doc.selectXpath(xpath.substring(0, xpath.lastIndexOf("/")));
         //   System.out.println("jsoupElementsList.size()= "+jsoupElementsList.size());
        }
        else  jsoupElementsList=doc.selectXpath(xpath);

        StringBuilder sb= new StringBuilder();
*//*sb.append("=");*//*
        for (Element element:jsoupElementsList)
        //csv
        { sb.*//*append("\"").*//*append(generateWebElementValue(element,xpath))*//*.append("\"")*//*.append(lineSeparator); }
     *//* if (sb.lastIndexOf(lineSeparator)>0)
            sb.delete(sb.lastIndexOf(lineSeparator),sb.lastIndexOf(lineSeparator.substring(lineSeparator.length())));*//*
        return sb.toString();
    }

    //
    protected String generateWebElementValue(Element element,String xpath) {
        if (xpath.endsWith("href")) {
         //   System.out.println(element.attr("href"));
            return element.attr("href");
        }
        else if (xpath.endsWith("src")) {
          //  System.out.println(element.attr("src"));
            return element.attr("src");
        }
        else return element.text();
    }
*/

    /*@Override
    public String toString() {
        *//*StringBuilder carsListStringBuilder = new StringBuilder();
        for (Car car : carObjectsList) {
            carsListStringBuilder.append(car.carValue).append("\r\n");
        }*//*

        return "Item{" +
                "inputFile=" + inputFile + "\r\n" +
                ", carElementsList=" +carObjectsList.toString()+
                *//*", carElementsList=" + "\r\n" + carsListStringBuilder + "\r\n" +*//*
     *//*", sku='" + sku + "\r\n" +
                ", desc='" + desc + "\r\n" +*//*
                '}';
    }*/

    @Override
    public String toString() {
        return "Item{" +
                "inputFile=" + inputFile + "\r\n" +
                ", carElementsList=" + carObjectsList.toString() + "\r\n" +
                "sku='" + sku + "\r\n" +
                ", shortDesc='" + keystoneShortDesc + "\r\n" +
                ", itemType='" + itemType + "\r\n" +
                ", productInfoAttributesdesc='" + productInfoAttributesDesc + "\r\n" +
                ", longDesc='" + longDesc + "\r\n" +
                ", pdfUrl='" + pdfUrl + "\r\n" +
                ", picUrl='" + picUrl + "\r\n" +
                '}';
    }
}

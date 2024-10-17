package classes;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;

import static classes.PrestaAttribute.*;
import static classes.utils.data_processing_utils.*;


public class Car extends Item {
    HashSet<String> carPositionStringsHashSet = new HashSet<>();
    public Item item;
    public Document doc;
    public String carLineString;
    public static final String cars_Xpath = "//span[@class='title']";
    public String carXpath;
    public static final String carXpathBeginning = "(" + cars_Xpath + ")" + "[";
    public static final String carXpathEnd = "]";
    public ArrayList<Element> subCarWebElementsList;
    public ArrayList<SubCar> subCarObjectsList;
    public String yearsString;
    public int yearStart;
    public int yearFinish;
    public String make;
    public String model;
    public String carPrestaCategoryStringWithYears; public String carPrestaCategoryStringWoYears;
    public String carWpCategoryString;
    public LinkedHashSet<Integer> yearNumbersSet = new LinkedHashSet<>();
    public String yearNumbersString;
    public HashSet<String> driveHashSet;
    public HashSet<String> driveReplacedHashSet = new HashSet<>();
    public String driveRangeString;
    public String carItemSku;
    public LinkedHashSet<Double> carLiftNumbersSet = new LinkedHashSet<>();
    public String carLiftNumbersString;
    public double carLiftNumberStart = -100.0;
    public double carLiftNumberFinish = -100.0;
    public String carMakeModelYearsString;
    public String carPrestaTitleString;
    public String itemPrestaTitleString;
    public String carWpTitleString;
    public String itemWpTitleString;
    public String finalTitle;
    public String subCarDrivesString = null;
    public LinkedHashSet<ApplicationFootnote> carAppFootnoteLinkedHashSet = new LinkedHashSet<>(); public String carAppFootnoteLinkedHashSetString;
    public LinkedHashSet<ApplicationAttribute> carAppAttLinkedHashSet = new LinkedHashSet<>(); public String carAppAttLinkedHashSetString;
    public LinkedHashSet<String> carAppAttPositionLinkedHashSet = new LinkedHashSet<>();
    public LinkedHashSet<String> subCarValuesLinkedHashSet = new LinkedHashSet<>(); public String subCarValueString;
    public LinkedHashSet<String> subCarEnginesLinkedHashSet = new LinkedHashSet<>(); public String subCarEnginesString;

    public String appAttPositionString;
    public String appAttLiftString;
    public String appAttLiftStartString;
    public String appAttLiftFinishString;
    public String appAttLiftRangeString;

    public ArrayList<PrestaAttribute> carPrestaAttArrayList = new ArrayList<>();

    void carPrestaAttArraylistInit() {
        ArrayList<PrestaAttribute> carPrestaAttributesList;

        List<Double> newDoubleList = new ArrayList<>(carLiftNumbersSet);
        /*List<Double> newDoubleList = new ArrayList<>();
        double i;
        for ( i = carLiftNumberStart; i >=carLiftNumberFinish ; i+=0.25) {
            newDoubleList.add(i);}
            if ( (i)<carLiftNumberFinish ) newDoubleList.add(carLiftNumberFinish);*/

        carPrestaAttributesList = prestaAttributesListCreate("Car Lift", carLineString, doubleToStringListWithDecimals(newDoubleList));
       // System.out.println("item.itemLiftNumbersSet = "+item.itemLiftNumbersSet);
         if (item.itemLiftNumbersSet.size()>0) {
            // adding ItemLifts to filteredParameters called Car Lift
            ArrayList<PrestaAttribute> itemPrestaAttributesList;
            itemPrestaAttributesList= prestaAttributesListCreate
                    ("Car Lift",  carLineString, doubleToStringListWithDecimals(new ArrayList<>(item.itemLiftNumbersSet)) );
           // System.out.println(itemPrestaAttributesList);
            carPrestaAttributesList.addAll(itemPrestaAttributesList);
           // System.out.println(carPrestaAttributesList);
        }
        carPrestaAttArrayList.addAll(carPrestaAttributesList);
        // создать процедуру генерации List<Double> carLiftValues по принципу от min до max с шагом 0,25 и если последний больше max то вместо последнего ставим LiftMax

        newDoubleList = integerToDoubleList(new ArrayList<>(yearNumbersSet));
        carPrestaAttributesList = prestaAttributesListCreate("Year", carLineString, doubleToStringListForInt(newDoubleList));
        carPrestaAttArrayList.addAll(carPrestaAttributesList);

        List<String> newStringList = new ArrayList<>(driveReplacedHashSet);
        carPrestaAttributesList = prestaAttributesListCreate("Drive", carLineString, newStringList);
        carPrestaAttArrayList.addAll(carPrestaAttributesList);

        newStringList = new ArrayList<>(carAppAttPositionLinkedHashSet);
        carPrestaAttributesList = prestaAttributesListCreate("Position", carLineString, newStringList);
        carPrestaAttArrayList.addAll(carPrestaAttributesList);

        carPrestaAttArrayList.add(new PrestaAttribute("Make", carLineString, make));
        carPrestaAttArrayList.add(new PrestaAttribute("Model", carLineString, model));

        //addToPrestaAttArrayList(carPrestaAttArrayList,"Car Lift ",(Object) this,carLiftNumbersSet);
        //   System.out.println("carPrestaAttArraylist "+carPrestaAttArraylist);
    }

    public Car(Element carWebElement,int i, Item item, String brandForXls) {
        this.item = item;
        doc = item.doc;
        carXpath = carXpathBeginning + i + carXpathEnd;
        carLineString = carWebElement.text();
        carItemSku = item.sku + "_n_" + i;
        carItemSku = carItemSku.replace("_x000D_","");
        //TODO: other way to get carLineString - get the text i-st element of the carElementsList
        subCarWebElementsList = doc.selectXpath(carXpath + SubCar.subCars_xpath);


        subCarObjectsListInit();
        yearsInit();
        driveInit();
        makeModelInit();
        carLiftInit();
        rangesSeparatorsInit();
        carCategoryInit();
        carMakeModelYearsString = yearStart + "-" + yearFinish + " " + make + " " + model;


        carAppAttPositionArrayListInit();
        subCarValuesArrayListInit();
        appAttAndFootnoteLinkedHashSetsInit();
        carPrestaAttArraylistInit();

        titlesInit();
    }

    // not used
    public Car(int i, Item item, String brandForXls) {
        this.item = item;
        doc = item.doc;
        carXpath = carXpathBeginning + i + carXpathEnd;
        carLineString = doc.selectXpath(carXpath).text();
        carItemSku = item.sku + "_n_" + i;
        carItemSku = carItemSku.replace("_x000D_","");
        //TODO: other way to get carLineString - get the text i-st element of the carElementsList
        subCarWebElementsList = doc.selectXpath(carXpath + SubCar.subCars_xpath);


        subCarObjectsListInit();
        yearsInit();
        driveInit();
        makeModelInit();
        carLiftInit();
        rangesSeparatorsInit();
        carCategoryInit();
        carMakeModelYearsString = yearStart + "-" + yearFinish + " " + make + " " + model;

        carAppAttPositionArrayListInit();

        subCarValuesArrayListInit();
        appAttAndFootnoteLinkedHashSetsInit();
        carPrestaAttArraylistInit();

        titlesInit();
    }

    private void subCarValuesArrayListInit() {
        for (SubCar subCar : subCarObjectsList) {
            subCarValuesLinkedHashSet.add(subCar.subCarStringValue);
            subCarEnginesLinkedHashSet.add(subCar.subCarEngineString);
        }

        StringBuilder sb=new StringBuilder();
        for (String s:subCarValuesLinkedHashSet)
                sb.append(s).append(System.lineSeparator());

            subCarValueString=sb.toString();

    }

    private void carAppAttPositionArrayListInit() {
        for (SubCar subCar : subCarObjectsList)
            for (ApplicationAttribute applicationAttribute : subCar.subCarAppAttribute_ObjectsList) {
                {
                    carAppAttPositionLinkedHashSet.addAll(applicationAttribute.appLiftPositionObject.positionStringsHashSet);
                }
            }
    }


    private void titlesInit() {
        String liftOrDropString=" Lift ";
        if ( (carLiftNumberStart<0) || (carLiftNumberFinish<0) ||(itemLiftMin<0) || (itemLiftMax<0) ) {liftOrDropString=" Drop ";}

        if ( ( (carLiftNumberStart==0.0) && (carLiftNumberFinish==0.0) )
                &&
                ( (itemLiftMin==0.0 ) && (itemLiftMax==0.0) ) )
        {liftOrDropString="";}

        if (!(item.itemLiftMin==item.itemLiftMax))
            itemWpTitleString = item.itemType+" for "+ carMakeModelYearsString +" "+subCarDrivesString+" "
                +item.itemLiftMin +"-"+item.itemLiftMax+"'' "+item.itemPositionArrayList.toString()+carPositionStringsHashSet.toString()+" "+liftOrDropString;
        else
            itemWpTitleString = item.itemType+" for "+ carMakeModelYearsString +" "+subCarDrivesString +" "
                    +item.itemLiftMin+"'' "+item.itemPositionArrayList.toString()+carPositionStringsHashSet.toString()+" "+liftOrDropString;
//replace
            itemWpTitleString = itemWpTitleString.replace("-null","").replace(" null","").replace("[]","")
                    .replace("Suspension ","")
                    .replace("Full ","").replace("  "," ").replace("[","").replace("]","")
                    .replace("FrontFront","Front").replace("RearRear","Rear")
                    .replace("-100''","").replace("-100--100'' Lift","").replace("-100.0--100.0'' Lift ","").replace("0.0-0.0'' Lift","").replace("0-0''  Lift","").replace(".0","").replace("-100''","").replace("_x000D_","")
                    ;

        System.out.println(itemWpTitleString);

        if (!(carLiftNumberStart==carLiftNumberFinish))
            carWpTitleString =brandForXls+" "+carLiftNumberStart+"-"+carLiftNumberFinish+"'' "
                    +carPositionStringsHashSet.toString()+" "+liftOrDropString+item.itemType+" for "+carMakeModelYearsString +" "+subCarDrivesString /*+" "+ carAppAttPositionLinkedHashSet.toString()*/;
        else
            carWpTitleString=brandForXls+" "+carLiftNumberStart+"'' "+carPositionStringsHashSet.toString()+liftOrDropString+item.itemType+" for "+carMakeModelYearsString +" "+subCarDrivesString /*+" "+ carAppAttPositionLinkedHashSet.toString()*/;
//replace
        carWpTitleString = carWpTitleString
                .replace("Suspension ","")
                .replace("FrontFront","Front").replace("RearRear","Rear")
                .replace("-100--100'' Lift","").replace("-100.0--100.0'' Lift ","").replace("0.0-0.0'' Lift","").replace("0-0'' Lift","").replace(".0","").replace("-100''","").replace("_x000D_","").replace("[","").replace("]","")
        // .replace("Four","4WD").replace("All","4WD")
        /*.replace("Rear","2WD")*/;
        // System.out.println(carWpTitleString);

        if (!(item.itemLiftMin==item.itemLiftMax))
            itemPrestaTitleString=/*item.sku+" "*/item.itemType+" for "+carMakeModelYearsString +" "+subCarDrivesString+
                " "+item.itemLiftMin +"-"+item.itemLiftMax+"'' "+item.itemPositionArrayList.toString()+carPositionStringsHashSet.toString()+liftOrDropString+" "+
                brandForXls;
        else
            itemPrestaTitleString=/*item.sku+" "*/item.itemType+" for "+carMakeModelYearsString +" "+subCarDrivesString+
                    " "+item.itemLiftMin +"'' "+item.itemPositionArrayList.toString()+carPositionStringsHashSet.toString()+liftOrDropString+" "+
                    brandForXls;

        itemPrestaTitleString = itemPrestaTitleString.replace("-null","").replace(" null","").replace("[","").replace("]","")
                .replace("Suspension ","")
                .replace("Full ","").replace("  "," ")
                .replace("FrontFront","Front").replace("RearRear","Rear")
                .replace("-100--100'' Lift","").replace("-100.0--100.0'' Lift ","").replace("0.0-0.0'' Lift","").replace("0.0-0.0'' Lift","").replace(".0","").replace("-100''","").replace("_x000D_","")
        ;

        if (!(carLiftNumberStart==carLiftNumberFinish))
        carPrestaTitleString=item.itemType+" for "+carMakeModelYearsString +" "+subCarDrivesString +" "+
                /*carAppAttPositionLinkedHashSet.toString()+*/
                /*+item.sku*/" "+carLiftNumberStart+"-"+carLiftNumberFinish+"'' "+carPositionStringsHashSet.toString()+liftOrDropString
                +" "+brandForXls;
        else
        carPrestaTitleString=item.itemType+" for "+carMakeModelYearsString +" "+subCarDrivesString +" "+
                /*carAppAttPositionLinkedHashSet.toString()+*/
                /*+item.sku*/" "+carLiftNumberStart+"'' "+carPositionStringsHashSet.toString()+liftOrDropString
                +" "+brandForXls;
                //replace
        carPrestaTitleString = carPrestaTitleString.replace("[","").replace("]","")
                .replace("Suspension ","")
                .replace("FrontFront","Front").replace("RearRear","Rear")
                .replace("0.0-0.0'' Lift","").replace("0-0''","").replace("-100--100'' Lift","").replace("-100.0--100.0'' Lift ","").replace(".0","").replace("-100''","").replace("_x000D_","");

        // System.out.println("carPrestaTitleString = " + carPrestaTitleString);
        // System.out.println("itemPrestaTitleString = " + itemPrestaTitleString);
    }

    public Car() {}

    public String getCarLineString() { return carLineString; }

    public void appAttAndFootnoteLinkedHashSetsInit() {
        StringBuilder sb1=new StringBuilder();

        for (SubCar subCar:subCarObjectsList) {

            carAppFootnoteLinkedHashSet.addAll(subCar.subCarAppFootnote_ObjectsList);
            carAppAttLinkedHashSet.addAll(subCar.subCarAppAttribute_ObjectsList);

            for (ApplicationFootnote applicationFootnote:carAppFootnoteLinkedHashSet) {
                sb1.append(applicationFootnote.appFootnote_Value).append(System.lineSeparator());       }
            carAppFootnoteLinkedHashSetString=sb1.toString();

            StringBuilder sbApplicationAttribute=new StringBuilder();
            StringBuilder applicationAttributePositionSb=new StringBuilder();
            StringBuilder applicationAttributeLiftSb=new StringBuilder();
            StringBuilder applicationAttributeLiftStartSb=new StringBuilder();
            StringBuilder applicationAttributeLiftFinishSb=new StringBuilder();
            StringBuilder applicationAttributeLiftRangeSb=new StringBuilder();

            for (ApplicationAttribute applicationAttribute:carAppAttLinkedHashSet) {
                sbApplicationAttribute.append(applicationAttribute.applicationAttribute_Value).append(System.lineSeparator());
              //  System.out.println("applicationAttribute.appLiftPositionObject.getPositionStringsHashSet().toString()" + applicationAttribute.appLiftPositionObject.getPositionStringsHashSet().toString());

                for (String s:applicationAttribute.appLiftPositionObject.getPositionStringsHashSet()) {
                    carPositionStringsHashSet.add(s);
                   // System.out.println("carPositionStringsHashSet ="+ carPositionStringsHashSet);
                    //    applicationAttributePositionSb.append(applicationAttribute.appLiftPositionObject.getPositionStringsHashSet().toString())
                }
                /*.append(System.lineSeparator())*/;
                applicationAttributeLiftSb.append(applicationAttribute.liftString).append(System.lineSeparator());
                applicationAttributeLiftStartSb.append(applicationAttribute.appAttLiftNumberStart).append(System.lineSeparator());
                applicationAttributeLiftFinishSb.append(applicationAttribute.appAttLiftNumberFinish).append(System.lineSeparator());

                // ? maybe liftStart+LiftFinish instead of appAttLiftStringArrayList ?
                applicationAttributeLiftRangeSb.append(applicationAttribute.appAttLiftStringArrayList).append(System.lineSeparator());

            }
            carAppAttLinkedHashSetString=sbApplicationAttribute.toString();
            appAttPositionString=applicationAttributePositionSb.toString(); //
            appAttLiftString=applicationAttributeLiftSb.toString(); // liftString не инициализирована
            appAttLiftStartString=applicationAttributeLiftStartSb.toString();
            appAttLiftFinishString=applicationAttributeLiftFinishSb.toString();
            appAttLiftRangeString=applicationAttributeLiftRangeSb.toString();
        }

    }

    public void subCarObjectsListInit() {
        if (doc != null) {
            subCarObjectsList = new ArrayList<>();
            int ii=1; //xpath outnumbered from 1
            for (Element subCarWebElement: subCarWebElementsList) {
                subCarObjectsList.add(new SubCar(subCarWebElement, ii++,this));
            }
        }
    }

    public void  carCategoryInit(){
        String carPrestaCategorySeparator ="/"; String carWpCategorySeparator ="/";
        StringBuilder sb=new StringBuilder();
        sb.append(make).append(carPrestaCategorySeparator).
                append(make+" "+model);
        carPrestaCategoryStringWoYears=sb.toString();

        sb.append(carPrestaCategorySeparator).append(make+" "+model+" "+
                yearStart+"-"+yearFinish)/*.append(carPrestaCategorySeparator)
                .append(item.itemType)*//*.append((make+" "+model+yearStart+"-"+yearFinish+carPrestaCategorySeparator)
                .append(item.itemType)*/;
        carPrestaCategoryStringWithYears =sb.toString();


        sb=new StringBuilder();
        sb.append(make).append(carWpCategorySeparator).
                append(make+" "+model).append(carWpCategorySeparator)
                .append(yearStart+"-"+yearFinish).append(carWpCategorySeparator)
                /*.append(item.itemType)*/;
        carWpCategoryString=sb.toString();
    }

    void rangesSeparatorsInit() {
        StringBuilder sb = new StringBuilder();
        for (int i : yearNumbersSet) {
            sb.append(i).append("|");
        }
        if (yearNumbersSet.size()>0)
        try { yearNumbersString=sb.substring(0,sb.toString().length()-1); }
        catch (StringIndexOutOfBoundsException e) {e.printStackTrace(); yearNumbersString=null;}

        sb = new StringBuilder();
        for (double d : carLiftNumbersSet) {
            sb.append(d).append("|");
        }
        if (carLiftNumbersSet.size()>0)
        try {carLiftNumbersString=sb.substring(0,sb.toString().length()-1);
            carLiftNumbersString=carLiftNumbersString.replace(".0","");
        }
        catch (StringIndexOutOfBoundsException e) {e.printStackTrace(); carLiftNumbersString=null;}

        sb = new StringBuilder();
        for(String driveValue: driveHashSet) {
            sb.append(driveValue).append("|");
        }
        if (driveHashSet.size()>0)
        try {driveRangeString=sb.substring(0,sb.toString().length()-1);}
        catch (StringIndexOutOfBoundsException e) {e.printStackTrace(); carLiftNumbersString=null;}
        if (driveRangeString!=null) driveRangeString=driveRangeString.replace("Four","4WD").replace("Rear","2WD");

    }

    void carLiftInit() {
        for (SubCar subCar:subCarObjectsList)
            for (ApplicationAttribute applicationAttribute:subCar.subCarAppAttribute_ObjectsList)
                carLiftNumbersSet.addAll(applicationAttribute.appAttLiftNumbersSet);
            //

            if (carLiftNumbersSet.size()>0) {
                carLiftNumberStart = Collections.min(carLiftNumbersSet);
                carLiftNumberFinish = Collections.max(carLiftNumbersSet);
            }
            else {carLiftNumberStart=0.0; carLiftNumberFinish=0.0;}

    }

    void makeModelInit() {

        String carValueReplaced=String.copyValueOf(carLineString.toCharArray());
        carValueReplaced=carValueReplaced.replace(yearsString+" ","");
        make=carValueReplaced.split(" ")[0];
        model=carValueReplaced.replace(make+" ","");
    }

    void driveInit() {
        driveHashSet =new HashSet<>();
        for (SubCar subCar:subCarObjectsList) {
            if (subCar.driveString !=null)
                driveHashSet.add(subCar.driveString);
        }

        for (String s:driveHashSet) {
            driveReplacedHashSet.add(s.replace("Four","4WD").replace("All","4WD").
                    replace("Rear","2WD").replace("Front","2WD"));
        }

        StringBuilder sb=new StringBuilder();
        for (String driveString:driveHashSet)
            sb.append(driveString).append("/");

        if (sb.length()>0)  sb.deleteCharAt(sb.length()-1);
            subCarDrivesString=sb.toString();

            // replacing drives to abbreviation
        subCarDrivesString=subCarDrivesString.replace("Four","4WD").replace("All","4WD").
        replace("Rear","2WD").replace("Front","2WD");
    }

    void yearsInit() {
        yearsString = carLineString.split(" ")[0];
        yearStart=Integer.parseInt(yearsString.split("-")[0]);
        yearFinish=Integer.parseInt(yearsString.split("-")[1]);

        for (int yearValue=yearStart;yearValue<=yearFinish;yearValue++)
        yearNumbersSet.add(yearValue);
    }

    @Override
    public String toString() {
        return "Car{" +
                ", carValue='" + carLineString + "\r\n" +","+
                "+++ subCarElementsList=" + subCarObjectsList +
                '}';
    }
}

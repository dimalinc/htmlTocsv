package classes;

import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

import static classes.utils.data_processing_utils.*;

public class ApplicationAttribute extends SubCar {

    public String appAttS_Xpath;
    public String appAtt_Xpath;
    public static final String appAtts_Xpath = "/ancestor::li[1]/ul/li[@class='application-attribute']";
    // public static final String appAtts_Xpath = "/ancestor::li[1]/ul/li[@class='application']/a";

    public String applicationAttribute_Value;
    public ArrayList<String> appAttSplitArrayList;
    public String position;

    public LinkedHashSet<String> appAttPositionArrayList = new LinkedHashSet<>();
    public String liftString;
    public String appAttLift;
    public double appAttLiftNumberStart=-100.0; public double appAttLiftNumberFinish=-100.0;
    public LinkedHashSet<Double> appAttLiftNumbersSet=new LinkedHashSet<>(); public String  appAttLiftString;
    public ArrayList<String> appAttLiftStringArrayList = new ArrayList<>();


    void applicationAttributeStringsArrayListInit() {
        appAttSplitArrayList =
                new ArrayList<String>(Arrays.asList(applicationAttribute_Value.split(";")));
    }

    void initPositionAndLiftFromAppAttr() {

        for (String stringOfApplicationAttribute : appAttSplitArrayList) {

            if (stringOfApplicationAttribute.contains("Position"))
                position = stringOfApplicationAttribute.split(": ")[1];
            if (position.equals("Front and Rear")) position="Full Front and Rear";

            /// Lift
            if (stringOfApplicationAttribute.contains("Lift"))
                liftString = stringOfApplicationAttribute.replace("With ", "").replace(" Inch Lift", "").replace(" inch Lift", "")
                        .replace(" To ", "-").replace(" to ", "-").replace(" TO ", "-");

            try {
                if (liftString != null) {
                    String[] liftStringArray = liftString.split("-");
                    Collections.addAll(appAttLiftStringArrayList, liftStringArray);

                    if (liftStringArray.length == 1) {
                        appAttLiftNumberStart = frac(liftStringArray[0].trim());
                        appAttLiftNumberFinish = frac(liftStringArray[0].trim());
                    } else if (liftStringArray.length < 3) {
                        appAttLiftNumberStart = frac(liftStringArray[0].trim());
                        appAttLiftNumberFinish = frac(liftStringArray[1].trim());
                    } else {
                        for (int i = 0; i < liftStringArray.length; i++) {
                            //  System.out.println(liftStringArray[i]);
                            double result = 0;
                            if (liftStringArray[i].contains("/"))
                                result = frac(liftStringArray[i - 1]) + frac(liftStringArray[i]);
                            else result = frac(liftStringArray[i]);
                            if (i < 2) appAttLiftNumberStart = result;
                            else appAttLiftNumberFinish = result;
                        }
                    }
                }
            } catch (Exception e) {
               // System.out.println("Exception with item sku = " + item.sku);
                e.printStackTrace();
            }

            if ( (appAttLiftNumberStart>-100.0) && (appAttLiftNumberFinish>-100.0) )
            for (double liftValue = appAttLiftNumberStart; liftValue <=  appAttLiftNumberFinish; liftValue+=0.25) {
                appAttLiftNumbersSet.add(liftValue);
                if (liftValue>appAttLiftNumberStart) appAttLiftNumbersSet.add(appAttLiftNumberStart);
            }


            if ( (appAttLiftNumberStart>-100.0) || (appAttLiftNumberFinish>-100.0) )
                appAttLiftNumbersSet.add(Double.max(appAttLiftNumberStart,appAttLiftNumberFinish));
        }
        //  System.out.println("Position = " + position);
        //  System.out.println("Lift = " + lift + "    LiftStart= " + liftStart + "    LiftFinish= " + liftFinish );
    }

    void initLiftPositionFromObject(ArrayList<String> applicationAttributesArrayListSplit) {
        LiftPositionObject appLiftPositionObject= new LiftPositionObject(applicationAttributesArrayListSplit);
        appAttPositionArrayList=appLiftPositionObject.getPositionArrayList();
      //  System.out.println(appAttPositionArrayList.toString());
        liftString =appLiftPositionObject.getLiftString();
        // System.out.println(liftString);
        appAttLiftNumberStart=appLiftPositionObject.getLiftNumberStart();
        appAttLiftNumberFinish=appLiftPositionObject.getLiftNumberFinish();
        // System.out.println(appAttLiftNumberStart+" "+appAttLiftNumberFinish);
        // System.out.println(itemLiftNumbersSet.toString());

        // appAttLiftNumbersSet init
        if ( (appAttLiftNumberStart>-100.0) && (appAttLiftNumberFinish>-100.0) )
            for (double liftValue = appAttLiftNumberStart; liftValue <=  appAttLiftNumberFinish; liftValue+=0.25) {
                appAttLiftNumbersSet.add(liftValue);
                if (liftValue>appAttLiftNumberStart) appAttLiftNumbersSet.add(appAttLiftNumberStart);
            }

        if ( (appAttLiftNumberStart>-100.0) || (appAttLiftNumberFinish>-100.0) )
            appAttLiftNumbersSet.add(Double.max(appAttLiftNumberStart,appAttLiftNumberFinish));

        StringBuilder stringBuilder = new StringBuilder();
        if (appAttLiftNumbersSet.size()>0) for (Double liftNumber:appAttLiftNumbersSet) {
            stringBuilder.append(liftNumber).append("|");
        }
        appAttLiftString=stringBuilder.substring(0,stringBuilder.length()-1);
       // System.out.println("appAttLiftString= "+appAttLiftString);
    }


    public ApplicationAttribute() { }

    public ApplicationAttribute(Element webElement,int i, SubCar subCar) {
        doc = subCar.doc;
        appAttS_Xpath = subCar.eachSubCarXpath + appAtts_Xpath;
        appAtt_Xpath = "(" + appAttS_Xpath + ")[" + i + "]";
       // applicationAttribute_Value = doc.selectXpath(appAtt_Xpath).text();
        applicationAttribute_Value = webElement.text();
      //  System.out.println("applicationAttribute_Value = "+applicationAttribute_Value);
        applicationAttributeStringsArrayListInit();
        initPositionAndLiftFromAppAttr();
        initLiftPositionFromObject(appAttSplitArrayList);

    }

    public ApplicationAttribute(int i, SubCar subCar) {
        doc = subCar.doc;
        appAttS_Xpath = subCar.eachSubCarXpath + appAtts_Xpath;
        appAtt_Xpath = "(" + appAttS_Xpath + ")[" + i + "]";
        applicationAttribute_Value = doc.selectXpath(appAtt_Xpath).text();
        applicationAttributeStringsArrayListInit();
        initPositionAndLiftFromAppAttr();
        initLiftPositionFromObject(appAttSplitArrayList);

    }

    @Override
    public String toString() {
        return /*"ApplicationAttribute{" +
                ", appAtt_Value='" + */applicationAttribute_Value
                /*+ '}' + "\r\n"*/;
    }
}


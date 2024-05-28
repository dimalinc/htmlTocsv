package classes;

import java.util.ArrayList;
import java.util.List;

public class PrestaAttribute {
    String prestaAttName;
    String carLineString;
    String prestaAttValue;
    int prestaAttNumber;


    public PrestaAttribute(String prestaAttName, String carLineString, String prestaAttValue) {
        this.prestaAttName = prestaAttName;
        this.carLineString = carLineString;
        this.prestaAttValue = prestaAttValue;
        this.prestaAttNumber=1;
    }

    public PrestaAttribute(String prestaAttName, String carLineString, String prestaAttValue, int prestaAttNumber) {
        this.prestaAttName = prestaAttName;
        this.carLineString = carLineString;
        this.prestaAttValue = prestaAttValue;
        this.prestaAttNumber = prestaAttNumber;
    }

    public static ArrayList<PrestaAttribute> prestaAttributesListCreate(String prestaAttName, String carLineString, List<String> valuesList) {
        ArrayList<PrestaAttribute> prestaAttributesArrayList= new ArrayList<PrestaAttribute>();
        int n=1;
        for (String value:valuesList) {
           PrestaAttribute prestaAttribute = new PrestaAttribute(prestaAttName,carLineString,value,n++);
            prestaAttributesArrayList.add(prestaAttribute);
        }
        return prestaAttributesArrayList;
    }



   /* public static ArrayList<PrestaAttribute> prestaAttributesListCreate(String prestaAttName, String carLineString,
                                                                        List<Double> valuesList) {
        ArrayList<PrestaAttribute> prestaAttributesArrayList= new ArrayList<PrestaAttribute>();
        int n=1;
        for (Double value:valuesList) {
            PrestaAttribute prestaAttribute = new PrestaAttribute(prestaAttName,carLineString,value.toString(),n++);
            prestaAttributesArrayList.add(prestaAttribute);
        }
        return prestaAttributesArrayList;
    }

    public static ArrayList<PrestaAttribute> prestaAttributesListCreate(String prestaAttName, String carLineString,
                                                                        LinkedHashSet<Integer> valuesList) {
        ArrayList<PrestaAttribute> prestaAttributesArrayList= new ArrayList<PrestaAttribute>();
        int n=1;
        for (Integer value:valuesList) {
            PrestaAttribute prestaAttribute = new PrestaAttribute(prestaAttName,carLineString,value.toString(),n++);
            prestaAttributesArrayList.add(prestaAttribute);
        }
        return prestaAttributesArrayList;
    }*/

    @Override
    /*public String toString() {
        return "PrestaAttribute{" +
                "prestaAttName='" + prestaAttName + '\'' +
                ", carLineString='" + carLineString + '\'' +
                ", prestaAttValue='" + prestaAttValue + '\'' +
                ", prestaAttNumber=" + prestaAttNumber +
                '}';
    }*/

    public String toString() {
        return prestaAttName+" "+carLineString+": "+prestaAttValue+": "+prestaAttNumber+": 0"+"; "/*+System.lineSeparator()*/;
    }

}

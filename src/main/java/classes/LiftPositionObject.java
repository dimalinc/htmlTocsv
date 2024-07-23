package classes;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static classes.utils.data_processing_utils.makeDoubleArray;

public class LiftPositionObject {

    LinkedHashSet<String> positionStringsHashSet = new LinkedHashSet<>();

    /*String frontLiftString;
    String rearLiftString;
    double frontLiftNumberStart; double frontLiftNumberFinish;
    double rearLiftNumberStart; double rearLiftNumberFinish;*/

    String liftString;
    double liftNumberStart;
    double liftNumberFinish;

    LinkedHashSet<Double> liftNumbersSet ;


    public LiftPositionObject(ArrayList<String> appAttSplitArrayList) {

       // System.out.println("new LiftPositionObject");
        // Positions
        for (String s : appAttSplitArrayList) {
            if (s.contains("Front")) positionStringsHashSet.add("Front");
            if (s.contains("Rear")) positionStringsHashSet.add("Rear");

            //  if ((s.endsWith("Lift")) || (s.endsWith("Front/ Rear Lift"))) frontLiftString = s.trim();
            //   if ((s.endsWith("Rear Lift")) || (s.endsWith("Front/ Rear Lift"))) rearLiftString = s.trim();
            if ( (s.contains("Lift")) && (( !s.contains("Lift Kit Suspension") )) ) {
                s = s.replace("For", "").replace("With", "");
                liftString = s.trim();
              //  System.out.println("liftString = "+liftString);
            }
        }


        if ((positionStringsHashSet.contains("Front")) && (positionStringsHashSet.contains("Rear"))) {
            positionStringsHashSet.clear();
            positionStringsHashSet.add("Full Front and Rear");
            if (positionStringsHashSet.contains("Front and Rear")) {
                positionStringsHashSet.clear();
                positionStringsHashSet.add("Full Front and Rear");
            }
        }


        /// Lifts
        if (liftString != null) {
            liftNumberStart = makeDoubleArray(liftString)[0];
            liftNumberFinish = makeDoubleArray(liftString)[1];
            // System.out.println("liftNumberStart  + liftNumberFinish "+ liftNumberStart + " " + liftNumberFinish);
            double liftRange=liftNumberFinish-liftNumberStart;
            liftNumbersSet=new LinkedHashSet<>();
            for (double liftValue = liftNumberStart; liftValue <=liftNumberFinish ; liftValue+=0.25)
            {
                liftNumbersSet.add(liftValue);
                if (liftValue>liftNumberFinish) liftNumbersSet.add(liftNumberFinish);
            }
        }

                    /*if ( (liftString.contains(" To ")) || (liftString.contains(" to ")) ) {
                        liftNumberStart = makeDoubleArray(frontLiftString)[0];
                        liftNumberFinish = makeDoubleArray(frontLiftString)[1];
                    } else {
                        liftNumberStart = makeDoubleArray(liftString)[0];
                        liftNumberFinish = makeDoubleArray(liftString)[0];
                    }
                else {
                liftNumberStart = -100.0;
                liftNumberFinish = -100.0;
                }*/

                /*if (rearLiftString != null)
                    if ( (rearLiftString.contains("To")) || (rearLiftString.contains("to")) ) {
                        rearLiftNumberStart = makeDoubleArray(rearLiftString)[0];
                        rearLiftNumberFinish = makeDoubleArray(rearLiftString)[1];
                    } else {
                        rearLiftNumberStart = makeDoubleArray(rearLiftString)[0];
                        rearLiftNumberFinish = makeDoubleArray(rearLiftString)[0];
                    }
                else {
                    rearLiftNumberStart = -100.0;
                    rearLiftNumberFinish = -100.0;
                }*/

        /// if ( (itemFrontLiftNumberStart!=0)||(itemFrontLiftNumberFinish!=0)||(itemRearLiftNumberStart!=0)||(itemRearLiftNumberFinish!=0) ) System.out.println("itemFr/Re_Lift.split [0] = " + itemFrontLiftNumberStart+"-"+itemFrontLiftNumberFinish + " -_- " + itemRearLiftNumberStart+"-"+itemRearLiftNumberFinish);

            /*double liftMin = Double.min(frontLiftNumberStart, frontLiftNumberFinish);
            double liftMax = Double.max(frontLiftNumberFinish, rearLiftNumberFinish);

            if ((liftMin > -100.0) && (liftMax > -100.0))
                for (double liftValue = liftMin; liftValue <= liftMax; liftValue += 0.25) {
                    liftNumbersSet.add(liftValue);
                    if (liftValue > liftMax) liftNumbersSet.add(liftMax);
                }
            if ((liftMin > -100.0) || (liftMax > -100.0))
                liftNumbersSet.add(Double.max(liftMin, liftMax));

            if (liftNumbersSet.size() > 1) System.out.println(liftNumbersSet.toString());*/


    }

/*    public String getFrontLiftString() {
        return frontLiftString;
    }

    public String getRearLiftString() {
        return rearLiftString;
    }


    public double getFrontLiftNumberStart() {
        return frontLiftNumberStart;
    }

    public double getFrontLiftNumberFinish() {
        return frontLiftNumberFinish;
    }

    public double getRearLiftNumberStart() {
        return rearLiftNumberStart;
    }

    public double getRearLiftNumberFinish() {
        return rearLiftNumberFinish;
    }*/

    public LinkedHashSet<String> getPositionStringsHashSet() {
        return positionStringsHashSet;
    }

    public LinkedHashSet<Double> getLiftNumbersSet() {
        return liftNumbersSet;
    }

    public String getLiftString() {
        return liftString;
    }

    public double getLiftNumberStart() {
        return liftNumberStart;
    }

    public double getLiftNumberFinish() {
        return liftNumberFinish;
    }
}

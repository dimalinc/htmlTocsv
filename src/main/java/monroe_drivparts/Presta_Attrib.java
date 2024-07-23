package monroe_drivparts;

public class Presta_Attrib {
    String presta_att_name;
    String presta_att_value;
    int presta_attrib_number;
    final int presta_attrib_changeable=0;

    public Presta_Attrib(String presta_att_name, String presta_att_value, int presta_attrib_number) {
        this.presta_att_name = presta_att_name;
        this.presta_att_value = presta_att_value;
        this.presta_attrib_number = presta_attrib_number;
    }

    @Override
    public String toString() {
        return "Presta_Attrib{" +
                " " + presta_att_name + /*'\'' +*/
                ", " + presta_att_value + /*'\'' +*/
                ", " + presta_attrib_number +
                ", " + presta_attrib_changeable +
                '}';
    }
}

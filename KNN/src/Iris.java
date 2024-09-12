import java.util.Arrays;

public class Iris {
    private double[] atributes;

    private String flag;

    public Iris(double[] atributes, String flag) {
        this.atributes = atributes;
        this.flag = flag;
    }


    public double[] getAtributes() {
        return atributes;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "Iris{ " +
                "atributes=" + Arrays.toString(atributes) +

                '}';
    }
}

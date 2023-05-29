import java.util.Comparator;

public class ComparaFecha implements Comparator<Socio> {


    @Override
    public int compare(Socio o1, Socio o2) {
        int value = o1.getFechaIngreso().compareTo(o2.getFechaIngreso());
        if (value == 0) {
            return value;
        } else if (value > 0) {
            return 1;

        } else return -1;
    }
}
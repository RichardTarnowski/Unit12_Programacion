import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FusionaListas {


    public static List<Integer> fusionarListas(List<Integer> lista1, List<Integer> lista2) {

        lista1.addAll(lista2);
        Collections.sort(lista1);
        return lista1;
    }
}

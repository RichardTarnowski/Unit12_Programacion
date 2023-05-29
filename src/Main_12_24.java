import java.util.ArrayList;
import java.util.List;

public class Main_12_24 {
    public static void main(String[] args) {
        List<Integer> toTest1 = new ArrayList<>(List.of(1, 3, 4, 2, 5, 1, 2, 3, 2));
        List<Integer> toTest2 = new ArrayList<>(List.of(4, 500, 1, 2, 20, 22, 150));


        System.out.println(FusionaListas.fusionarListas(toTest1, toTest2));


    }
}
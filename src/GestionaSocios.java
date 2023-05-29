import java.io.*;
import java.util.*;

public class GestionaSocios {


    private Map<String, Socio> gestionSocios = new HashMap<>();
    private final Map<String, Socio> paraMostrar = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);
    private String apodo;
    private String nombre;
    private final Date fecha = new Date();
    private Socio socio;
    boolean arranque = true;


    int opcionElegida;

    private void altaSocio() {

        System.out.println("Registro de Socio.");
        System.out.println("Introduce el nombre del nuevo socio:");
        nombre = scanner.next();
        System.out.println("Introduce el apodo del nuevo socio:");
        apodo = scanner.next();

        socio = new Socio(apodo, nombre, fecha);
        gestionSocios.put(socio.getApodo(), socio);

        mensajeVueltaAMenu();


    }

    private void bajaSocio() {
        haySociosRegistrados();
        System.out.println("Introduce el apodo del socio que quieres dar de baja.");
        apodo = scanner.next();

        if (gestionSocios.containsKey(apodo)) {
            gestionSocios.remove(apodo);
            mensajeVueltaAMenu();
        } else {
            System.out.println("\nNo existe ningún socio con ese apodo, volviendo al menú.\n");
            menu();
        }


    }

    private void modificacionSocio() {
        haySociosRegistrados();
        System.out.println("Introduce el apodo del socio que quieres modificar.");
        apodo = scanner.next();

        if (gestionSocios.containsKey(apodo)) {

            System.out.println("Modificación de Socio.");
            System.out.println("Introduce el nuevo nombre para " + apodo + ":");
            nombre = scanner.next();
            System.out.println("Introduce el nuevo apodo para " + apodo + ":");
            String apodo1 = scanner.next();

            gestionSocios.remove(apodo);
            socio = new Socio(apodo1, nombre, fecha);
            gestionSocios.put(apodo1, socio);

            mensajeVueltaAMenu();
        } else {
            System.out.println("\nNo existe ningún socio con ese apodo, volviendo al menú.\n");
            menu();
        }
    }


    private void listarSocios(int criterioDeOrden) {

        haySociosRegistrados();

        ArrayList<Socio> paraOrdenar = new ArrayList<>();
        String paraMensaje;
        for (Map.Entry<String, Socio> entry : gestionSocios.entrySet()) {
            paraOrdenar.add(entry.getValue());
        }
        if (criterioDeOrden == 0) {
            paraOrdenar.sort(new ComparaApodos());
        } else {
            paraOrdenar.sort(new ComparaFecha());
        }


        if (criterioDeOrden == 0) {
            paraMensaje = "apodo.";
        } else {
            paraMensaje = "antigüedad.";
        }
        System.out.println("Esta es la lista de Socios ordenada por " + paraMensaje);
        System.out.println(paraOrdenar);
        mensajeVueltaAMenu();
    }


    private void filtrarPorFecha() {
        haySociosRegistrados();
        int fechaDeCorte = 0;
        paraMostrar.putAll(gestionSocios);
        System.out.println("Por favor, introduce un año para filtrar el registro de socios:");

        if (scanner.hasNextInt()) {
            fechaDeCorte = scanner.nextInt();
        } else {
            System.out.println("No has introducido un año valido, volviendo al menú.\n");
            menu();
        }


        Set<Map.Entry<String, Socio>> entradas = paraMostrar.entrySet();
        Iterator<Map.Entry<String, Socio>> iterator;
        for (iterator = entradas.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Socio> e = iterator.next();
            if (e.getValue().getFechaIngreso().getYear() >= (fechaDeCorte - 1900)) {
                iterator.remove();
            }
        }

        if (paraMostrar.size() == 0) {
            System.out.println("No hay altas de socio anteriores al año " + fechaDeCorte);
            mensajeVueltaAMenu();
        } else {
            System.out.println("Estos son los socios anteriores al año " + fechaDeCorte);
            System.out.println(paraMostrar.entrySet());
            mensajeVueltaAMenu();
        }


    }

    private void haySociosRegistrados() {
        if (gestionSocios.size() == 0) {
            System.out.println("El registro está vacio, volviendo al menú.\n");
            menu();
        }
    }

    private void mensajeVueltaAMenu() {
        System.out.println("\nOperación exitosa, volviendo al menú.\n");
        menu();
    }

    private void arranque() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("club.dat"))) {
            gestionSocios = (Map<String, Socio>) in.readObject();
        } catch (IOException | ClassNotFoundException ignored) {

        }
        arranque = false;
    }

    private void cierre() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("club.dat"))) {
            out.writeObject(gestionSocios);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void menu() {
        if (arranque) {
            arranque();
        }

        try {
            System.out.println("\nIntroduce el Número de la opción que deseas seleccionar:\n");
            System.out.println("""
                    1. Alta socio
                    2. Baja socio
                    3. Modificación socio
                    4. Listar socios por apodo
                    5. Listar socios por antigüedad
                    6. Listar los socios con alta anterior a un año determinado
                    7. Salir""".indent(1));
            opcionElegida = scanner.nextInt();

            switch (opcionElegida) {
                case 1:
                    altaSocio();
                case 2:
                    bajaSocio();
                case 3:
                    modificacionSocio();
                case 4:
                    listarSocios(0);
                case 5:
                    listarSocios(1);
                case 6:
                    filtrarPorFecha();
                case 7:
                    System.out.println("Hasta pronto!");
                    cierre();
                    System.exit(0);
                default:
                    System.out.println("Por favor, ingresa un número válido.");
                    menu();
                    scanner.nextLine();

            }
        } catch (InputMismatchException ex) {
            System.out.println("Solo está permitido un número entero entre el 1 y el 7. Intentalo de nuevo:");
            scanner.nextLine();
            menu();

        }

    }

}

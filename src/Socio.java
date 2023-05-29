import java.io.Serializable;
import java.util.Date;

public class Socio implements Serializable {

    private String apodo;
    private String nombre;
    private Date fechaIngreso;

    public Socio(String apodo, String nombre, Date fechaIngreso) {
        this.apodo = apodo;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "Nombre real: " + nombre +  "\t Apodo: " + apodo + "\t Fecha de alta: " + fechaIngreso + "\n\n";
    }


}

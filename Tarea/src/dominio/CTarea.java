/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.sql.Date;

public class CTarea {
    private String nombre;
    private int prioridad;
    private Date fecha;
    private boolean especial;
    private String estado; // "Pendiente" o "Hecho"

    public CTarea(String nombre, int prioridad, Date fecha, boolean especial, String estado) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.fecha = fecha;
        this.especial = especial;
        this.estado = estado;
    }

    public String getNombre() { return nombre; }
    public int getPrioridad() { return prioridad; }
    public Date getFecha() { return fecha; }
    public boolean isEspecial() { return especial; }
    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return nombre + " | " + fecha + " | Prioridad: " + prioridad + " | Especial: " + especial + " | Estado: " + estado;
    }
}

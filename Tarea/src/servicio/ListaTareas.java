/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dominio.CTarea;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListaTareas {

    private List<CTarea> tareas;
    private Stack<CTarea> tareasEliminadas;

    public ListaTareas() {
        tareas = new ArrayList<>();
        tareasEliminadas = new Stack<>();
    }

    public void agregarTarea(CTarea tarea) {
        tareas.add(tarea);
    }

    public List<CTarea> getTareas() {
        return tareas;
    }

    public void eliminarTarea(CTarea tarea) {
        if (tareas.remove(tarea)) {
            tareasEliminadas.push(tarea);
        }
    }

    public void deshacerEliminacion() {
        if (!tareasEliminadas.isEmpty()) {
            tareas.add(tareasEliminadas.pop());
        }
    }
}

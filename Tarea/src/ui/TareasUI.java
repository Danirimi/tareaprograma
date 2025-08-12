/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import dominio.CTarea;
import servicio.ListaTareas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import tarea.Tarea;

public class TareasUI extends JFrame {

    private JTextField txtTitulo;
    private JComboBox<String> cbPrioridad;
    private JTextField txtFecha;
    private JCheckBox chkEspecial;
    private JTable tblTareas;
    private JLabel lblEstado;

    private ListaTareas listaTareas;

    public TareasUI() {
        listaTareas = new ListaTareas();
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestor de Tareas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior (formulario)
        JPanel panelForm = new JPanel(new GridLayout(2, 4, 5, 5));
        txtTitulo = new JTextField();
        cbPrioridad = new JComboBox<>(new String[]{"Alta", "Media", "Baja"});
        txtFecha = new JTextField("2025-08-12");
        chkEspecial = new JCheckBox("★ Especial");

        panelForm.add(new JLabel("Título:"));
        panelForm.add(txtTitulo);
        panelForm.add(new JLabel("Prioridad:"));
        panelForm.add(cbPrioridad);
        panelForm.add(new JLabel("Fecha:"));
        panelForm.add(txtFecha);
        panelForm.add(new JLabel(""));
        panelForm.add(chkEspecial);

        add(panelForm, BorderLayout.NORTH);

        // Tabla
        tblTareas = new JTable(new DefaultTableModel(
                new Object[]{"ID", "Título", "Prioridad", "Estado", "Especial", "Fecha"}, 0
        ));
        add(new JScrollPane(tblTareas), BorderLayout.CENTER);

        // Panel inferior (botones + estado)
        JPanel panelBottom = new JPanel(new BorderLayout());
        JPanel panelButtons = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnAlternar = new JButton("Alternar Hecho");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnDeshacer = new JButton("Deshacer");

        panelButtons.add(btnAgregar);
        panelButtons.add(btnAlternar);
        panelButtons.add(btnEliminar);
        panelButtons.add(btnDeshacer);

        lblEstado = new JLabel("Listo");
        panelBottom.add(panelButtons, BorderLayout.CENTER);
        panelBottom.add(lblEstado, BorderLayout.SOUTH);

        add(panelBottom, BorderLayout.SOUTH);

        // Listeners
        btnAgregar.addActionListener(e -> agregarTarea());
        btnEliminar.addActionListener(e -> eliminarTarea());
        btnDeshacer.addActionListener(e -> deshacerTarea());

        setSize(700, 400);
        setLocationRelativeTo(null);
    }

    private void agregarTarea() {
      String titulo = txtTitulo.getText().trim();
    if (titulo.isEmpty()) {
        lblEstado.setText("❌ El título no puede estar vacío.");
        return;
    }

    int prioridad = cbPrioridad.getSelectedIndex() + 1;

 java.sql.Date fecha = null;
String fechaTexto = txtFecha.getText().trim();
if (!fechaTexto.isEmpty()) {
    try {
        // Formato yyyy-MM-dd
        fecha = java.sql.Date.valueOf(fechaTexto);
    } catch (IllegalArgumentException e) {
        lblEstado.setText("❌ Formato de fecha inválido. Use yyyy-MM-dd");
        return;
    }
}

    boolean especial = chkEspecial.isSelected();

    // Aquí el constructor correcto:
    CTarea tarea = new CTarea(titulo, prioridad, fecha, especial, "Pendiente");

    // Agregar a la lista en memoria
    listaTareas.agregarTarea(tarea);

    // Mostrar en la tabla
    DefaultTableModel model = (DefaultTableModel) tblTareas.getModel();
    model.addRow(new Object[]{
        "",      // Si no tienes id aún, puedes mostrar null o autogenerar
        tarea.getNombre(),
        tarea.getPrioridad(),
        tarea.getEstado(),
        tarea.isEspecial(),
        tarea.getFecha()
    });

    lblEstado.setText("✅ Tarea agregada.");
    }

    private void eliminarTarea() {
        int fila = tblTareas.getSelectedRow();
        if (fila >= 0) {
            DefaultTableModel model = (DefaultTableModel) tblTareas.getModel();
            model.removeRow(fila);
            lblEstado.setText("✅ Tarea eliminada.");
        } else {
            lblEstado.setText("❌ Selecciona una tarea para eliminar.");
        }
    }

    private void deshacerTarea() {
        listaTareas.deshacerEliminacion();
        lblEstado.setText("🔄 Deshacer ejecutado.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TareasUI().setVisible(true));
    }
}

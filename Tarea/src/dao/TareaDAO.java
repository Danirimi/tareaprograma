/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author danir
 */
import dominio.CTarea;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TareaDAO {

    public boolean insertar(CTarea tarea) {
        String sql = "INSERT INTO Tareas (titulo, prioridad, fecha, especial, estado) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarea.getNombre());
            stmt.setInt(2, tarea.getPrioridad());
            stmt.setDate(3, tarea.getFecha());
            stmt.setBoolean(4, tarea.isEspecial());
            stmt.setString(5, tarea.getEstado());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar tarea: " + e.getMessage());
            return false;
        }
    }
}

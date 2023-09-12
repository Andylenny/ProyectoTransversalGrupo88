/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.main;

import java.sql.Connection;
import java.time.LocalDate;
import universidad.AccesoAdatos.AlumnoData;
import universidad.AccesoAdatos.Conexion;
import universidad.Entidades.Alumnos;

/**
 *
 * @author Lourdes
 */
public class ProyectoTransversalGrupo88 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conexion=Conexion.getConexion();
        Alumnos gabriel=new Alumnos(40354456, "santillan", "gabriel", LocalDate.of(2000, 7, 10), true);
        AlumnoData alu=new AlumnoData();
        alu.guardarAlumno(gabriel);
    }
    
}

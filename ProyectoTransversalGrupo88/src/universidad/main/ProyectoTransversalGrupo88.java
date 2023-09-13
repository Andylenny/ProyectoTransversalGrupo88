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
import universidad.AccesoAdatos.InscripcionData;
import universidad.AccesoAdatos.MateriaData;
import universidad.Entidades.Alumnos;
import universidad.Entidades.Inscripcion;
import universidad.Entidades.Materia;

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
     //   Alumnos gabriel=new Alumnos(40354456, "santillan", "gabriel", LocalDate.of(2000, 7, 10), true);
       // AlumnoData alu=new AlumnoData();
      //  alu.guardarAlumno(gabriel);
        
        
        
        
        AlumnoData ad =new AlumnoData();
          MateriaData md=new MateriaData();
          InscripcionData ind=new InscripcionData();
          
          Alumnos juan =ad.buscarAlumnos(4);
          Materia lengua =md.buscarMateria(2);
          Inscripcion inscr= new Inscripcion(juan,lengua,8);
          
          ind.guardarInscripcion(inscr);
    }
    
}

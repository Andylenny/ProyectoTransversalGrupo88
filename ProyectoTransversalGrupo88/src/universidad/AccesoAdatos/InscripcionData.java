/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.AccesoAdatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidad.Entidades.Alumnos;
import universidad.Entidades.Inscripcion;
import universidad.Entidades.Materia;

/**
 *
 * @author Lourdes
 */
public class InscripcionData {
    private Connection con=null;
   private MateriaData md=new MateriaData();
    private AlumnoData ad=new AlumnoData();
    
    public InscripcionData(){
        this.con=Conexion.getConexion();
        
        
    }
    public void guardarInscripcion(Inscripcion insc){
        
        String sql="INSERT INTO inscripcion(idAlumno,idMateria,nota)VALUES (?,?,?)";
     
            try {
                PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,insc.getAlumno().getIdAlumno());
                ps.setInt(2,insc.getMateria().getIdMateria());
                ps.setDouble(3, insc.getNota());
                ps.executeUpdate();
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    insc.setIdInscripcion(rs.getInt(1));
                    JOptionPane.showMessageDialog(null, "inscripcion exitosa");
                
                    
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error al acceder a la tabla inscripcion");
                
            }
       
               } 
    public void actualizarNota(int idAlumno,int idMateria,double nota){
        String sql="UPDATE inscripcion SET nota = ? WHERE idAlumno = ? and idMateria = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int filas=ps.executeUpdate();
            if(filas<0){
                JOptionPane.showMessageDialog(null, "nota actulizada");
                
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a la tabla incripcion");
                    
        }
        
    }
    public void borrarInscripcionMateriaAlummno(int idAlumno, int idMateria){
        String sql="DELETE FROM inscripcion WHERE idAlumno = ? and idMateria =?";
        
        try {
            PreparedStatement ps=con.prepareStatement(sql);JOptionPane.showMessageDialog(null, "inscripcion borrada exitosamente");
            
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int filas=ps.executeUpdate();
            if(filas>0){
                
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al borrar inscripcion del alumno");
            
        }
        
    }
    public List<Inscripcion>obtenerInscripciones(){
         ArrayList<Inscripcion> cursadas= new ArrayList<>();
         
         String sql="SELECT * FROM inscripcion";
         
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
               Inscripcion insc= new Inscripcion();
               insc.setIdInscripcion(rs.getInt("idInscripto"));
               Alumnos alu= ad.buscarAlumnos(rs.getInt("idAlumno"));
               Materia mat=md.buscarMateria(rs.getInt("idMateria"));
                  
                  insc.setAlumno(alu);
                  insc.setMateria(mat);
                  insc.setNota(rs.getDouble("nota"));
                  cursadas.add(insc);
               
               
            }
            
            ps.close();
            
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de inscripciones");
        }
        return cursadas;
     }
    public List<Inscripcion> obtenerInscripcionPorAlumno (int idAlumndo){
        ArrayList<Inscripcion> cursadas= new ArrayList<>();
         
         String sql="SELECT * FROM inscripcion WHERE idAlumno = ?";
         
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumndo);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
               Inscripcion insc= new Inscripcion();
               insc.setIdInscripcion(rs.getInt("idInscripto"));
               Alumnos alu= ad.buscarAlumnos(rs.getInt("idAlumno"));
               Materia mat=md.buscarMateria(rs.getInt("idMateria"));
                  
                  insc.setAlumno(alu);
                  insc.setMateria(mat);
                  insc.setNota(rs.getDouble("nota"));
                  cursadas.add(insc);
               
               
            }
            
            ps.close();
            
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de inscripciones");
        }
        return cursadas;
    }
 
    }




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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidad.Entidades.Inscripcion;

/**
 *
 * @author Lourdes
 */
public class InscripcionData {
    private Connection con=null;
    
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
    public void actializarNota(int idAlumno,int idMateria,double nota){
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
    }




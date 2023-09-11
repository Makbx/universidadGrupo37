/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadGrupo37.accesoADatos;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadGrupo37.entidades.Inscripcion;
import universidadGrupo37.entidades.Materia;

/**
 *
 * @author crist
 */
public class InscripcionData {
    private Connection con=null;
    private MateriaData matData;
    private AlumnoData aluData;
    
    public InscripcionData(){
        con=Conexion.getConexion();
    }
    public void guardarInscripcion(Inscripcion insc){
        
    }
    public List<Materia> obtenerMateriasCursadas(int id){
        String sql ="SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"
                +" materia WHERE inscripcion.idMateria = materia.idMateria\n"+
                "AND inscripcion.idAlumno = ?";
        List<Materia> materias = new ArrayList<>();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return materias;
    }
    public List<Materia> obtenerMateriasNOCursadas(int id){
        String sql ="SELECT materia.idMateria, nombre, año FROM inscripcion,"
                +" materia WHERE materia.idMateria NOT IN (SELECT inscripcion.idMateria FROM inscripcion WHERE inscripcion.idAlumno = ?)";
        List<Materia> materias = new ArrayList<>();
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return materias;
    }
     
}

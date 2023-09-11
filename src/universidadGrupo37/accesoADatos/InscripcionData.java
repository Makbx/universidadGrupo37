/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadGrupo37.accesoADatos;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import universidadGrupo37.entidades.Alumno;
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
        String sql="INSERT INTO inscripcion (idMateria, idAlumno)"
                + "VALUE(? ,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, insc.getMateria().getIdMateria());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion Guardada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        
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
        //String sql ="SELECT materia.idMateria, nombre, año FROM inscripcion,"
        //        +" materia WHERE materia.idMateria NOT IN (SELECT inscripcion.idMateria FROM inscripcion WHERE inscripcion.idAlumno = ?)";
        String sql="SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"
                +" materia WHERE inscripcion.idMateria != materia.idMateria\n"
                +"AND inscripcion.idAlumno = ?";        
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
    public List<Inscripcion> obtenerInscripciones(){
        String sql="SELECT idInscripto, nota, idAlumno, idMateria FROM inscripcion";
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion insc=new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                                insc.setNota(rs.getInt("nota"));
                insc.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
                insc.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
                inscripciones.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return inscripciones;
    }
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        String sql="SELECT idInscripto, nota, idAlumno, idMateria FROM inscripcion WHERE idAlumno = ?";
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Inscripcion insc=new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                insc.setNota(rs.getInt("nota"));
                insc.setAlumno(aluData.buscarAlumno(rs.getInt("idAlumno")));
                insc.setMateria(matData.buscarMateria(rs.getInt("idMateria")));
                inscripciones.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return inscripciones;
    }
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        String sql="DELETE inscripcion WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2,idMateria);
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Se borro la inscripcion");
            }
            ps.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
    }
    public void actualizarNota(int idAlumno, int idMateria, int nota){
        String sql="UPDATE inscripcion SET nota =? WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, nota);
            ps.setInt(2,idAlumno);
            ps.setInt(3,idMateria);
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Se actualizo la nota");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
    }
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        String sql="SELECT alumno.idAlumno, dni, apellido, nombre, fechaNacimiento FROM inscripcion,"
                +" alumno WHERE inscripcion.idAlumno=alumno.idAlumno\n"
                +"AND inscripcion.idMateria = ?";
        List<Alumno> alumnos = new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Alumno alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return alumnos;
    }
}

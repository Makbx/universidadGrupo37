/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadGrupo37.accesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadGrupo37.entidades.Alumno;

/**
 *
 * @author crist
 */
public class AlumnoData {
    private Connection con=null;
    
    public AlumnoData(){
        con=Conexion.getConexion(); //cargando Drivers
    }
    public void guardadAlumno(Alumno alumno){
        String sql="INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado)"
                + "VALUE(? , ?, ?, ?, ?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,alumno.getDni());
            ps.setString(2,alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5, alumno.isActivo()); //los atributos booleanos se crean como is
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno Guardado");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
    public void modificarAlumno(Alumno alumno){
        String sql="UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento = ?"
                + "WHERE idAlumno = ?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setInt(5, alumno.getIdAlumno());
            int exito = ps.executeUpdate(); //Recordar que executeupdate devuelve un entero con "la cantidad de filas afectadas"
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Alumno modificado");
            }
            //ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        
    }
    public void eliminarAlumno(int id){ //eliminar consiste en poner su estado a 0 no borrarlo
        String sql="UPDATE alumno SET estado = 0 WHERE idAlumno =?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Alumno Eliminado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        
    }
    public Alumno buscarAlumno(int id){
        String sql="SELECT dni, apellido, nombre, fechaNacimiento FROM alumno WHERE idAlumno = ? AND estado = 1";
        Alumno alumno=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                 alumno=new Alumno();
                 alumno.setIdAlumno(id);
                 alumno.setDni(rs.getInt("dni"));
                 alumno.setApellido(rs.getString("apellido"));
                 alumno.setNombre(rs.getString("nombre"));
                 alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate()); //con el localDate combierto lo que esta en date en un local date para guardarlo en alumno 
                 alumno.setActivo(true);
            }else{
                JOptionPane.showMessageDialog(null, "No existe ese alumno con tal id");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumno;
    }
    public Alumno buscarAlumnoPorDni(int dni){
        String sql="SELECT idAlumno,dni, apellido, nombre, fechaNacimiento FROM alumno WHERE dni = ? AND estado = 1";
        Alumno alumno=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                 alumno=new Alumno();
                 alumno.setIdAlumno(rs.getInt("idAlumno"));
                 alumno.setDni(rs.getInt("dni"));
                 alumno.setApellido(rs.getString("apellido"));
                 alumno.setNombre(rs.getString("nombre"));
                 alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate()); //con el localDate combierto lo que esta en date en un local date para guardarlo en alumno 
                 alumno.setActivo(true);
            }else{
                JOptionPane.showMessageDialog(null, "No existe ese alumno con tal dni");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumno;
    }
    public List<Alumno> listarAlumnos(){
        String sql="SELECT idAlumno,dni, apellido, nombre, fechaNacimiento FROM alumno WHERE estado = 1";
        ArrayList<Alumno> alumnos=new ArrayList<>(); // lista vacia
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                 Alumno alumno=new Alumno();
                 alumno.setIdAlumno(rs.getInt("idAlumno"));
                 alumno.setDni(rs.getInt("dni"));
                 alumno.setApellido(rs.getString("apellido"));
                 alumno.setNombre(rs.getString("nombre"));
                 alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate()); //con el localDate combierto lo que esta en date en un local date para guardarlo en alumno 
                 alumno.setActivo(true);
                 alumnos.add(alumno); //vas agregando a la lista de alumnos
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
        return alumnos;
    }
}

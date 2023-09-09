/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package universidadGrupo37;

import java.sql.Connection;
import java.time.LocalDate;
import universidadGrupo37.accesoADatos.AlumnoData;
import universidadGrupo37.accesoADatos.Conexion;
import universidadGrupo37.entidades.Alumno;
/**
 *
 * @author crist
 */

public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       // Connection con=Conexion.getConexion();
       
       //Guardar alumno ejemplo Tiene que estar activado Xampp
       /**
       Alumno juan=new Alumno(12312312,"Luna","Pedro",LocalDate.of(1980, 4, 25),true);
       AlumnoData alu=new AlumnoData();
       alu.guardadAlumno(juan);
       */
       //Modificar alumno ejemplo el 36 es el id del alumno que se quiere modificar
       /**
       Alumno juan=new Alumno(36,12312312,"Luna","Juan Pedro",LocalDate.of(1980, 4, 25),true);
       AlumnoData alu=new AlumnoData();
       alu.modificarAlumno(juan);
       */
       //Eliminar alumno ejemplo
       /**
       Alumno juan=new Alumno(36,12312312,"Luna","Juan Pedro",LocalDate.of(1980, 4, 25),true);
       AlumnoData alu=new AlumnoData();
       alu.eliminarAlumno(36);
       */
       //Buscar alumno se puede con toString tambien
       /**
       AlumnoData alu=new AlumnoData();
       Alumno alumnoEncontrado=alu.buscarAlumno(4);//es un ejemplo el id
       if(alumnoEncontrado!=null){ //para que no salga NullpointerExeption
            System.out.println("dni "+alumnoEncontrado.getDni());
            System.out.println("apellido "+alumnoEncontrado.getApellido());
       }
       */
       //Buscar alumno por dni 
       /**
       AlumnoData alu=new AlumnoData();
       Alumno alumnoEncontrado=alu.buscarAlumnoPorDni(33);//es un ejemplo el dni
       if(alumnoEncontrado!=null){ //para que no salga NullpointerExeption
            System.out.println("dni "+alumnoEncontrado.getDni());
            System.out.println("apellido "+alumnoEncontrado.getApellido());
       }
       */
       //Listar alumnos
       /**
       AlumnoData alu=new AlumnoData();
       for(Alumno alumno:alu.listarAlumnos()){
           System.out.println(alumno.getDni());
           System.out.println(alumno.getApellido());
           System.out.println(alumno.getNombre());
           System.out.println(alumno.getFechaNac());
       }
       */ 
    }
    
}

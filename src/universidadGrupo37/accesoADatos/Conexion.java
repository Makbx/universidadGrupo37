/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadGrupo37.accesoADatos;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author crist
 */
public class Conexion {
    private static final String URL="jdbc:mariadb://localhost/";
    private static final String DB="universidadulp";
    private static final String USUARIO="root";
    private static final String PASSWORD="";
    private static Connection connection;
    
    private Conexion(){}
    
    public static Connection getConexion(){
        if(connection == null){
            try{
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL+DB,USUARIO,PASSWORD);
            } catch (ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null,"Error al cargar los driver");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error al conectarse a la base de datos");
            }
        }
        return connection;
    }
}

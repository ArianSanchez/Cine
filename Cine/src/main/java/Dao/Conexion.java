package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    Connection conexion = null;

    public Connection conectar() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection("jdbc:sqlserver://vg2021.database.windows.net;databaseName=vg2021", "vg2021", "Vallegrande2021");
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        }
        return conexion;
    }

    public void desconectar() throws Exception {
        if (conexion != null) {
            conexion.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Conexion cn = new Conexion();
        cn.conectar();
        if (cn != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("No estas Conectado");
        }
    }
}

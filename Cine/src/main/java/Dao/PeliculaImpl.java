package Dao;

import Modelo.Pelicula;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeliculaImpl extends Conexion implements IGenerica<Pelicula> {

    @Override
    public void registrar(Pelicula modelo) throws Exception {
        String sql = "INSERT INTO PELICULA (NOMBRE)VALUES(?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMBRE());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Pelicula> listar() throws Exception {
        List<Pelicula> listado;
        Pelicula pelicula;
        String sql = "SELECT NOMBRE FROM PELICULA";
        try {
            listado = new ArrayList<>();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pelicula = new Pelicula();
                pelicula.setNOMBRE(rs.getString(1));
                listado.add(pelicula);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        }
        return listado;
    }
}

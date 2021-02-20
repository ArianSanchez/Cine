package Dao;

import Modelo.Comida;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComidaImpl extends Conexion implements IGenerica<Comida> {

    @Override
    public void registrar(Comida modelo) throws Exception {
        String sql = "INSERT INTO COMIDA (COMBO, PRECIO)VALUES(?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getCOMBO());
            ps.setInt(2, modelo.getPRECIO());
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
    public List<Comida> listar() throws Exception {
        List<Comida> listado;
        Comida comida;
        String sql = "SELECT COMBO, PRECIO FROM COMIDA";
        try {
            listado = new ArrayList<>();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                comida = new Comida();
                comida.setCOMBO(rs.getString(1));
                comida.setPRECIO(rs.getInt(2));
                listado.add(comida);
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

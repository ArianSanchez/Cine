package Dao;

import Modelo.Horario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HorarioImpl extends Conexion implements IGenerica<Horario> {

    @Override
    public void registrar(Horario modelo) throws Exception {
        String sql = "INSERT INTO HORARIO (NOMBRE, DESCRIPCION, PRECIO)VALUES(?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMBRE());
            ps.setString(2, modelo.getDESCRIPCION());
            ps.setInt(3, modelo.getPRECIO());
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
    public List<Horario> listar() throws Exception {
        List<Horario> listado;
        Horario horario;
        String sql = "SELECT NOMBRE, DESCRIPCION, PRECIO FROM HORARIO";
        try {
            listado = new ArrayList<>();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                horario = new Horario();
                horario.setNOMBRE(rs.getString(1));
                horario.setDESCRIPCION(rs.getString(2));
                horario.setPRECIO(rs.getInt(3));
                listado.add(horario);
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

package Dao;

import Modelo.Boleta;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class BoletaImpl extends Conexion implements IGenerica<Boleta> {

    @Override
    public void registrar(Boleta modelo) throws Exception {
        String sql = "INSERT INTO BOLETA (ENTRADAS, IMPORTE, IDPEL, IDCOM, IDHOR)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getENTRADAS());
            ps.setInt(2, modelo.getIMPORTE());
            ps.setString(3, modelo.getIDPEL());
            ps.setString(4, modelo.getIDCOM());
            ps.setString(5, modelo.getIDHOR());
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
    public List<Boleta> listar() throws Exception {
        List<Boleta> listado;
        Boleta boleta;
        String sql = "SELECT ENTRADAS, IMPORTE, P.NOMBRE, COMBO, H.NOMBRE FROM BOLETA\n"
                + "INNER JOIN PELICULA P on P.IDPEL = BOLETA.IDPEL\n"
                + "INNER JOIN COMIDA C on BOLETA.IDCOM = C.IDCOM\n"
                + "INNER JOIN HORARIO H on BOLETA.IDHOR = H.IDHOR";
        try {
            listado = new ArrayList<>();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                boleta = new Boleta();
                boleta.setENTRADAS(rs.getInt(1));
                boleta.setIMPORTE(rs.getInt(2));
                boleta.setIDPEL(rs.getString(3));
                boleta.setIDCOM(rs.getString(4));
                boleta.setIDHOR(rs.getString(5));
                listado.add(boleta);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
            throw e;
        }
        return listado;
    }

    public List<String> autocompletePelicula(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT TOP 5 NOMBRE FROM PELICULA WHERE NOMBRE LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("NOMBRE"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String obtenerCodigoPelicula(String Pelicula) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT IDPEL FROM PELICULA WHERE NOMBRE LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Pelicula);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDPEL");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteComida(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT TOP 5 COMBO FROM COMIDA WHERE COMBO LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("COMBO"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String obtenerCodigoComida(String Comida) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT IDCOM FROM COMIDA WHERE COMBO LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Comida);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDCOM");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public List<String> autocompleteHorario(String Consulta) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        List<String> Lista;
        try {
            String sql = "SELECT TOP 5 NOMBRE FROM HORARIO WHERE NOMBRE LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "%" + Consulta + "%");
            Lista = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                Lista.add(rs.getString("NOMBRE"));
            }
            return Lista;
        } catch (SQLException e) {
            throw e;
        }
    }

    public String obtenerCodigoHorario(String Horario) throws SQLException, Exception {
        this.conectar();
        ResultSet rs;
        try {
            String sql = "SELECT IDHOR FROM HORARIO WHERE NOMBRE LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, Horario);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("IDHOR");
            }
            return null; //sql
        } catch (SQLException e) {
            throw e;
        }
    }

    public void REPORTE_PDF_ALUMNO(Map parameters) throws JRException, IOException, Exception {
        conectar();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("Reportes/Boleta.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, this.conectar());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Boleta.pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }
}

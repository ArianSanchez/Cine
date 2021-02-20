package Controlador;

import Dao.BoletaImpl;
import Dao.ComidaImpl;
import Dao.HorarioImpl;
import Dao.PeliculaImpl;
import Modelo.Boleta;
import Modelo.Comida;
import Modelo.Horario;
import Modelo.Pelicula;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("boletaC")
@SessionScoped
public class BoletaC implements Serializable {

    Boleta boleta;
    BoletaImpl daoBoleta;
    List<Boleta> listaBoleta;

    Pelicula pelicula;
    PeliculaImpl daoPelicula;
    List<Pelicula> listaPelicula;

    Comida comida;
    ComidaImpl daoComida;
    List<Comida> listaComida;

    Horario horario;
    HorarioImpl daoHorario;
    List<Horario> listaHorario;

    public BoletaC() {
        boleta = new Boleta();
        daoBoleta = new BoletaImpl();
        listaBoleta = new ArrayList<>();

        pelicula = new Pelicula();
        daoPelicula = new PeliculaImpl();
        listaPelicula = new ArrayList<>();

        comida = new Comida();
        daoComida = new ComidaImpl();
        listaComida = new ArrayList<>();

        horario = new Horario();
        daoHorario = new HorarioImpl();
        listaHorario = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void registrar() {
        try {

            boleta.setIDPEL(daoBoleta.obtenerCodigoPelicula(boleta.getIDPEL()));
            boleta.setIDHOR(daoBoleta.obtenerCodigoHorario(boleta.getIDHOR()));
            boleta.setIDCOM(daoBoleta.obtenerCodigoComida(boleta.getIDCOM()));
            daoBoleta.registrar(boleta);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void listar() {
        try {
            listaBoleta = daoBoleta.listar();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    //GENERAR BOLETA
    public void REPORTE_PDF_ALUMNO(String IDBOL) throws Exception {
        BoletaImpl report = new BoletaImpl();
        try {
            Map<String, Object> parameters = new HashMap();
            parameters.put("IDBOL", IDBOL);
            report.REPORTE_PDF_ALUMNO(parameters); //Pido exportar Reporte con los parametros
        } catch (Exception e) {
            throw e;
        }
    }

    public List<String> completeTextPelicula(String query) throws SQLException, Exception {
        daoBoleta = new BoletaImpl();
        return daoBoleta.autocompletePelicula(query);
    }

    public List<String> completeTextComida(String query) throws SQLException, Exception {
        daoBoleta = new BoletaImpl();
        return daoBoleta.autocompleteComida(query);
    }

    public List<String> completeTextHorario(String query) throws SQLException, Exception {
        daoBoleta = new BoletaImpl();
        return daoBoleta.autocompleteHorario(query);
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }

    public BoletaImpl getDaoBoleta() {
        return daoBoleta;
    }

    public void setDaoBoleta(BoletaImpl daoBoleta) {
        this.daoBoleta = daoBoleta;
    }

    public List<Boleta> getListaBoleta() {
        return listaBoleta;
    }

    public void setListaBoleta(List<Boleta> listaBoleta) {
        this.listaBoleta = listaBoleta;
    }

}

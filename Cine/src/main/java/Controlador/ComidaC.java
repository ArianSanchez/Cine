package Controlador;

import Dao.ComidaImpl;
import Modelo.Comida;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("comidaC")
@SessionScoped
public class ComidaC implements Serializable {

    Comida comida;
    ComidaImpl daoComida;
    List<Comida> listaComida;

    public ComidaC() {
        comida = new Comida();
        daoComida = new ComidaImpl();
        listaComida = new ArrayList<>();
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
            daoComida.registrar(comida);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void listar() {
        try {
            listaComida = daoComida.listar();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public ComidaImpl getDaoComida() {
        return daoComida;
    }

    public void setDaoComida(ComidaImpl daoComida) {
        this.daoComida = daoComida;
    }

    public List<Comida> getListaComida() {
        return listaComida;
    }

    public void setListaComida(List<Comida> listaComida) {
        this.listaComida = listaComida;
    }
    
    
}

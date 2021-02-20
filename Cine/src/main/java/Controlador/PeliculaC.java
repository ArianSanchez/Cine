package Controlador;

import Dao.PeliculaImpl;
import Modelo.Pelicula;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "peliculaC")
@SessionScoped
public class PeliculaC implements Serializable {

    Pelicula pelicula;
    PeliculaImpl daoPelicula;
    List<Pelicula> listaPelicula;

    public PeliculaC() {
        pelicula = new Pelicula();
        daoPelicula = new PeliculaImpl();
        listaPelicula = new ArrayList<>();
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
            daoPelicula.registrar(pelicula);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void listar() {
        try {
            listaPelicula = daoPelicula.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public PeliculaImpl getDaoPelicula() {
        return daoPelicula;
    }

    public void setDaoPelicula(PeliculaImpl daoPelicula) {
        this.daoPelicula = daoPelicula;
    }

    public List<Pelicula> getListaPelicula() {
        return listaPelicula;
    }

    public void setListaPelicula(List<Pelicula> listaPelicula) {
        this.listaPelicula = listaPelicula;
    }

}

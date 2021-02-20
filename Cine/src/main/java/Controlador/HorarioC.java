package Controlador;

import Dao.HorarioImpl;
import Modelo.Horario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("horarioC")
@SessionScoped
public class HorarioC implements Serializable {

    Horario horario;
    HorarioImpl daoHorario;
    List<Horario> listaHorario;

    public HorarioC() {
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
            daoHorario.registrar(horario);
            listar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void listar() {
        try {
            listaHorario = daoHorario.listar();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public HorarioImpl getDaoHorario() {
        return daoHorario;
    }

    public void setDaoHorario(HorarioImpl daoHorario) {
        this.daoHorario = daoHorario;
    }

    public List<Horario> getListaHorario() {
        return listaHorario;
    }

    public void setListaHorario(List<Horario> listaHorario) {
        this.listaHorario = listaHorario;
    }

}

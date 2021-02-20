package Modelo;

public class Boleta {

    int IDBOL, ENTRADAS, IMPORTE;

    String IDPEL, IDCOM, IDHOR;

    private Pelicula pelicula = new Pelicula();

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public int getIDBOL() {
        return IDBOL;
    }

    public void setIDBOL(int IDBOL) {
        this.IDBOL = IDBOL;
    }

    public int getENTRADAS() {
        return ENTRADAS;
    }

    public void setENTRADAS(int ENTRADAS) {
        this.ENTRADAS = ENTRADAS;
    }

    public int getIMPORTE() {
        return IMPORTE;
    }

    public void setIMPORTE(int IMPORTE) {
        this.IMPORTE = IMPORTE;
    }

    public String getIDPEL() {
        return IDPEL;
    }

    public void setIDPEL(String IDPEL) {
        this.IDPEL = IDPEL;
    }

    public String getIDCOM() {
        return IDCOM;
    }

    public void setIDCOM(String IDCOM) {
        this.IDCOM = IDCOM;
    }

    public String getIDHOR() {
        return IDHOR;
    }

    public void setIDHOR(String IDHOR) {
        this.IDHOR = IDHOR;
    }

}

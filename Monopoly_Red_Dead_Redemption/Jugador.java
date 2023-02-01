import java.util.ArrayList;

public class Jugador{
    private String nombre;
    private String figura;
    private double dinero;
    private ArrayList<Casilla> misCalles = new ArrayList<Casilla>();
    private ArrayList<Casilla> misEstaciones = new ArrayList<Casilla>();
    private int posicion;

    private boolean carcel = false;
    private int pena = 0;

    private final double DINEROINICIAL = 900;

    public Jugador(String nombre, String figura){
        this.nombre = nombre;
        this.figura = figura;
        this.dinero = DINEROINICIAL;
        this.posicion = 0;
    }

// -------------------------------------------------- SET:

    public void setNombre(String nuevoNombre){
       this.nombre = nuevoNombre;
    }
    public void setPosicion(int nuevaPosicion){
       this.posicion = nuevaPosicion;
    }
    public void setFigura(String nuevaFigura){
        this.figura = nuevaFigura;
    }
    public void setDinero(double nuevoDinero){
        this.dinero = nuevoDinero;
    }
    public void setMisCalles(ArrayList<Casilla> nuevoMisCalles){
        this.misCalles = nuevoMisCalles;
    }
    public void setMisEstaciones(ArrayList<Casilla> nuevoMisEstaciones){
        this.misEstaciones = nuevoMisEstaciones;
    }
    public void setCarcel(boolean carcel){
      this.carcel = carcel;
    }
    public void setPena(int pena){
      this.pena = pena;
    }
    
// -------------------------------------------------- GET:

    public double getDinero(){
        return this.dinero;
    }
    public double getDINEROINICIAL(){
        return this.DINEROINICIAL;
    }
    public int getPosicion(){
        return this.posicion;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getFigura(){
        return this.figura;
    }
    public ArrayList<Casilla> getMisCalles(){
        return this.misCalles;
    }
    public ArrayList<Casilla> getMisEstaciones(){
        return this.misEstaciones;
    }
    public boolean getCarcel(){
      return this.carcel;
    }
    public int getPena(){
      return this.pena;
    }


// ----------------------------------------- COMPRA/VENTA:

    public void anadirCalle(Casilla miNuevaCalle){
        this.misCalles.add(miNuevaCalle);
    }
    public void perderCalle(Casilla miAntiguaCalle){
        this.misCalles.remove(miAntiguaCalle);
    }

    public void anadirEstacion(Casilla miNuevaEstacion){
        this.misEstaciones.add(miNuevaEstacion);
    }
    public void perderEstacion(Casilla miAntiguaEstacion){
        this.misEstaciones.remove(miAntiguaEstacion);
    }

// --------------------------------------------- IMPRIMIR:

    public String imprimeCalles(Tablero tabl){
        String salida = "\n    CALLES:\n\n";
        for (Casilla c : tabl.getCasillas()){
            if (this.misCalles.contains(c)){
                salida = salida + ("         ------------------------------------------ " + c.getNombre() + "\n             COLOR: " + c.getColor() + "\n            COMPRA: " + c.getPrecioCompra() + "\n         EDIFICIOS: " + c.getEdificios()
                    + "\n          ALQUILER: " + Math.round(c.getPrecioAlquiler(0)) + "|" + Math.round(c.getPrecioAlquiler(1)) + "|" + Math.round(c.getPrecioAlquiler(2)) + "|" + Math.round(c.getPrecioAlquiler(3)) + "|" + Math.round(c.getPrecioAlquiler(4)) + "|" + Math.round(c.getPrecioAlquiler(5)) + "|" + Math.round(c.getPrecioAlquiler(6)) + "\n\n");
            }
        }
        return salida;
    }

    public String imprimeEstaciones(Tablero tabl){
        String salida = "\n    ESTACIONES:\n\n";
        for (Casilla c : tabl.getCasillas()){
            if (this.misEstaciones.contains(c)){
                salida = salida + ("         ------------------------------------------ " + c.getNombre() + "\n            COMPRA: " + c.getPrecioCompra()
                    + "\n          ALQUILER: " + Math.round(c.getPrecioAlquiler(1)) + "|" + Math.round(c.getPrecioAlquiler(2)) + "|" + Math.round(c.getPrecioAlquiler(3)) + "|" + Math.round(c.getPrecioAlquiler(4)) + "\n\n");
            }
        }
        return salida + "\n";
    }

    public String toString(Tablero tabl){
        return "\n----------------------------------------- " + this.nombre + "\n  FICHA: "
        + this.figura + "\n  FONDOS: " + this.dinero + "\n  POSICION: "
        + this.posicion + "\n  CALLES:" + this.imprimeCalles(tabl)
        + "-----------------------------------------\n\n";
    } //----------------------------------------------- INCOMPLETO  MOSTRAR AL FINAL DEL JUEGO


}
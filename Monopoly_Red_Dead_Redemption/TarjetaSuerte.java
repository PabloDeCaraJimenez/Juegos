

public class TarjetaSuerte{

    private String opcion;
    private double precio;
    private String resolucion;


    public TarjetaSuerte(String opcion, double precio, String resolucion){
        this.opcion = opcion;
        this.precio = precio;
        this.resolucion = resolucion;
    }
    public TarjetaSuerte(){
        this.opcion = "";
        this.precio = 0.0;
        this.resolucion = "";
    }

    public String getOpcion(){
        return this.opcion;
    }
    public double getPrecio(){
        return this.precio;
    }
    public String getResolucion(){
        return this.resolucion;
    }








}    
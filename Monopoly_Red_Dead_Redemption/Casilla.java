import java.util.ArrayList;

public enum Casilla {

    SALIDA("Salida","","", 0, -1, 0, 0, 0, 0, 0, 0, 0, 0),
    PARKING("Parking","","", 0, -1, 0, 0, 0, 0, 0, 0, 0, 0),
    SUERTE("Suerte","","", 0, -1, 0, 0, 0, 0, 0, 0, 0, 0),
    COMUNIDAD("Caja de la Comunidad","","", 0, -1, 0, 0, 0, 0, 0, 0, 0, 0),

    CASILLA3("Calle", "Horseshoe Overlook", "Marron", 60, 0, 50, 2, 4, 10, 30, 90, 160, 250),
    CASILLA4("Calle", "Clemens Point", "Marron", 60, 0, 50, 4, 8, 20, 60, 180, 320, 450),

    CASILLA5("Calle","Mount Hagen","Rojo", 100, 0, 50, 6, 12, 30, 90, 270, 400, 550),
    CASILLA6("Calle","Valentine","Rojo", 100, 0, 50, 6, 12, 30, 90, 270, 400, 550),
    CASILLA7("Calle","Van Horn","Rojo", 120, 0, 50, 8, 16, 40, 100, 300, 450, 600),

    CASILLA8("Calle","Emerald Ranch","Azul", 140, 0, 100, 10, 20, 50, 150, 450, 625, 750),
    CASILLA9("Calle","Annesburg","Azul", 140, 0, 100, 10, 20, 50, 150, 450, 625, 750),
    CASILLA10("Calle","Rhodes","Azul", 160, 0, 100, 12, 24, 60, 180, 500, 700, 900),

    CASILLA11("Calle","Mount Shann","Verde", 180, 0, 100, 14, 28, 70, 200, 550, 750, 950),
    CASILLA12("Calle","Wapiti Indian Reservation","Verde", 180, 0, 100, 14, 28, 70, 200, 550, 750, 950),
    CASILLA13("Calle","Fort Wallace","Verde", 200, 0, 100, 16, 32, 80, 220, 600, 800, 1000),

    CASILLA14("Calle","Caliga Hall","Naranja", 220, 0, 150, 18, 36, 90, 250, 700, 875, 1050),
    CASILLA15("Calle","Lagras","Naranja", 220, 0, 150, 18, 36, 90, 250, 700, 875, 1050),
    CASILLA16("Calle","Lakay","Naranja", 240, 0, 150, 20, 40, 100, 300, 750, 925, 1100),

    CASILLA17("Calle","Thieves Landing","Morado", 260, 0, 150, 22, 44, 110, 330, 800, 975, 1150),
    CASILLA18("Calle","Manzanita Post","Morado", 260, 0, 150, 22, 44, 110, 330, 800, 975, 1150),
    CASILLA19("Calle","Rathskeller Fork","Morado", 280, 0, 150, 24, 48, 120, 360, 850, 1025, 1200),

    CASILLA20("Calle","Guarma","Rosa", 300, 0, 200, 26, 52, 130, 390, 900, 1100, 1275),
    CASILLA21("Calle","Tumbleweed","Rosa", 300, 0, 200, 26, 52, 130, 390, 900, 1100, 1275),
    CASILLA22("Calle","Armadillo","Rosa", 320, 0, 200, 28, 56, 150, 450, 1000, 1200, 1400),

    CASILLA23("Calle","Blackwater","Gris", 350, 0, 200, 35, 70, 175, 500, 1100, 1300, 1500),
    CASILLA24("Calle","Saint Denis","Gris", 400, 0, 200, 50, 100, 200, 600, 1400, 1700, 2000),

    VEALACARCEL("VeALaCarcel","","", 0, -1, 0, 0, 0, 0, 0, 0, 0, 0),
    CARCEL("Carcel", "", "", 0, -1, 0, 0, 0, 0, 0, 0, 0, 0),

    ESTACION1("Estacion", "Estacion 1", "", 200, -1, -1, 0, 25, 50, 100, 200, -1, -1 ),
    ESTACION2("Estacion", "Estacion 2", "", 200, -1, -1, 0, 25, 50, 100, 200, -1, -1 ),
    ESTACION3("Estacion", "Estacion 3", "", 200, -1, -1, 0, 25, 50, 100, 200, -1, -1 ),
    ESTACION4("Estacion", "Estacion 4", "", 200, -1, -1, 0, 25, 50, 100, 200, -1, -1 );

    private String tipo;
    private String nombre;
    private String color;
    private double precioCompra;
    private int edificios;
    private double precioCasas;
    private ArrayList<Double> precioAlquiler = new ArrayList<Double>();

    Casilla (String tipo, String nombre, String color, double precioCompra, int edificios, double precioCasas, double alq0, double alq1, double alq2, double alq3, double alq4, double alq5, double alq6){
        this.tipo = tipo;
        this.nombre = nombre;
        this.color = color;
        this.precioCompra = precioCompra;
        this.edificios = edificios;
        this.precioCasas = precioCasas;
        this.precioAlquiler.add(alq0);
        this.precioAlquiler.add(alq1);
        this.precioAlquiler.add(alq2);
        this.precioAlquiler.add(alq3);
        this.precioAlquiler.add(alq4);
        this.precioAlquiler.add(alq5);
        this.precioAlquiler.add(alq6);
    }

    public String getTipo(){
        return this.tipo;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getColor(){
        return this.color;
    }
    public double getPrecioCompra(){
        return this.precioCompra;
    }
    public double getPrecioAlquiler(int indice){

        return this.precioAlquiler.get(indice);
    }
    public int getEdificios(){
        return this.edificios;
    }
    public double getPrecioCasas(){
        return this.precioCasas;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setColor(String color){
        this.color = color;
    }    
    public void setPrecioCompra(double precioCompra){
        this.precioCompra = precioCompra;
    }
    /*public void setPrecioAlquiler(double precioAlquiler){
        this.precioAlquiler = precioAlquiler;
    }*/
    public void setEdificios(int edificios){
        this.edificios = edificios;
    }

    public double porcentaje(double num){
		return this.precioCompra * 0.25;
    }
}    
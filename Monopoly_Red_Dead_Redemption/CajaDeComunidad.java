enum CajaDeComunidad {
    
    CC1("Mataste a un bandido. Ganas 200 euros", 200),
    CC2("Recuperaste un caballo perdido. Ganas 100 euros", 100),
    CC3("Ganas una partida de poker ilegal. Ganas 55 euros", 50);

    private String enunciado;
    private double dinero;

    private CajaDeComunidad(String enunciado, double dinero){
        this.enunciado = enunciado;
        this.dinero = dinero;
    }
    public String getEnunciado(){
        return this.enunciado;
    }
    public double getDinero(){
        return this.dinero;
    }

}

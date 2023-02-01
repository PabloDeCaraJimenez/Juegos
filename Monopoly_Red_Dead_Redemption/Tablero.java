import java.util.*;

public class Tablero{

    private ArrayList<Casilla> casillas = new ArrayList<>();
    //public ArrayList<ArrayList<Casilla>> grupos = new ArrayList<ArrayList <Casilla>>();


    public Tablero(){
        this.casillas.add(Casilla.SALIDA);              //1
        this.casillas.add(Casilla.CASILLA3);    //2
        this.casillas.add(Casilla.COMUNIDAD);       //3
        this.casillas.add(Casilla.CASILLA4);    //4
        this.casillas.add(Casilla.ESTACION1);           //E
        this.casillas.add(Casilla.CASILLA5);    //5
        this.casillas.add(Casilla.CASILLA6);    //6
        this.casillas.add(Casilla.SUERTE);          //7
        this.casillas.add(Casilla.CASILLA7);    //8
        this.casillas.add(Casilla.CARCEL);              //9
        this.casillas.add(Casilla.CASILLA8);    //10
        this.casillas.add(Casilla.CASILLA9);    //11
        this.casillas.add(Casilla.CASILLA10);   //12
        this.casillas.add(Casilla.ESTACION2);           //E
        this.casillas.add(Casilla.COMUNIDAD);       //13
        this.casillas.add(Casilla.CASILLA11);   //14
        this.casillas.add(Casilla.CASILLA12);   //15
        this.casillas.add(Casilla.CASILLA13);   //16
        this.casillas.add(Casilla.PARKING);             //17
        this.casillas.add(Casilla.CASILLA14);   //18
        this.casillas.add(Casilla.CASILLA15);   //19
        this.casillas.add(Casilla.CASILLA16);   //20
        this.casillas.add(Casilla.ESTACION3);           //E
        this.casillas.add(Casilla.SUERTE);          //21
        this.casillas.add(Casilla.CASILLA17);   //22
        this.casillas.add(Casilla.CASILLA18);   //23
        this.casillas.add(Casilla.CASILLA19);   //24
        this.casillas.add(Casilla.VEALACARCEL);         //25
        this.casillas.add(Casilla.CASILLA20);   //26
        this.casillas.add(Casilla.COMUNIDAD);       //27
        this.casillas.add(Casilla.CASILLA21);   //28
        this.casillas.add(Casilla.ESTACION4);           //E
        this.casillas.add(Casilla.CASILLA22);   //29
        this.casillas.add(Casilla.CASILLA23);   //30
        this.casillas.add(Casilla.SUERTE);          //31
        this.casillas.add(Casilla.CASILLA24);   //32

        /*ArrayList <Casilla> g1 = new ArrayList<>(Arrays.asList(this.casillas.get(1),this.casillas.get(2),this.casillas.get(3)));
        ArrayList <Casilla> g2 = new ArrayList<>(Arrays.asList(this.casillas.get(5),this.casillas.get(6),this.casillas.get(8)));
        ArrayList <Casilla> g3 = new ArrayList<>(Arrays.asList(this.casillas.get(10),this.casillas.get(11),this.casillas.get(12)));
        ArrayList <Casilla> g4 = new ArrayList<>(Arrays.asList(this.casillas.get(13),this.casillas.get(15),this.casillas.get(16)));
        ArrayList <Casilla> g5 = new ArrayList<>(Arrays.asList(this.casillas.get(18),this.casillas.get(19),this.casillas.get(20)));
        ArrayList <Casilla> g6 = new ArrayList<>(Arrays.asList(this.casillas.get(23),this.casillas.get(24),this.casillas.get(25)));
        ArrayList <Casilla> g7 = new ArrayList<>(Arrays.asList(this.casillas.get(26),this.casillas.get(27)));

        grupos.add(g1);
        grupos.add(g2);
        grupos.add(g3);
        grupos.add(g4);
        grupos.add(g5);
        grupos.add(g6);
        grupos.add(g7);*/
    }

    public Casilla getCalle(int pos){
        return this.casillas.get(pos);
    }

    public ArrayList<Casilla> getCasillas(){
        return this.casillas;
    }

    /*public ArrayList<Casilla> getGrupo(int grupo){
        return this.grupos.get(grupo);
    }*/

    public int comoEstaElBarrio(String grupo){ //------------------------ DEVUELVE 0, 1, 2, 3 o 4
        int estadoDelBarrio = -1; //------------------------------------- 0 = TODAS VACIAS
        int suma = 0; //------------------------------------------------- 1 = ENTRE 0 Y 1
        ArrayList<Casilla> miBarrio = new ArrayList<Casilla>(); //------- 2 = TODAS 1
        ArrayList<Integer> misEstados = new ArrayList<Integer>(); //----- 3 = ENTRE [2] Y [4]
//----------------------------------------------------------------------- 4 = TODAS 5
        for (Casilla c : this.casillas){
            if (c.getColor() == grupo){
                miBarrio.add(c);
                if (c.getEdificios()==0) misEstados.add(0);
                else if (c.getEdificios()==1) misEstados.add(1);
                else if (c.getEdificios()==5) misEstados.add(5);
                else misEstados.add(25);
            }
        }
        for (int i : misEstados){
            suma += i;
        }

        if (suma/misEstados.size() == 0) estadoDelBarrio = 0;
        else if (suma/misEstados.size() < 1) estadoDelBarrio = 1;
        else if (suma/misEstados.size() == 1) estadoDelBarrio = 2;
        else if (suma/misEstados.size() == 5) estadoDelBarrio = 4;
        else  estadoDelBarrio = 3;

        return estadoDelBarrio;
    }

    public String imprimeCallesGrupo(String grupo){
        String salida = "\n    CALLES:\n\n";
        for (Casilla c : this.casillas){
            if (c.getColor() == grupo){
                salida = salida + ("         ------------------------------------------ " + c.getNombre() + "\n             COLOR: " + c.getColor() + "\n            COMPRA: " + c.getPrecioCompra() + "\n         EDIFICIOS: " + c.getEdificios() + "\n"
                    +"          ALQUILER: " + Math.round(c.getPrecioAlquiler(0)) + "|" + Math.round(c.getPrecioAlquiler(1)) + "|" + Math.round(c.getPrecioAlquiler(2)) + "|" + Math.round(c.getPrecioAlquiler(3)) + "|" + Math.round(c.getPrecioAlquiler(4)) + "|" + Math.round(c.getPrecioAlquiler(5)) + "|" + Math.round(c.getPrecioAlquiler(6)) + "\n\n");
            }
        }
        return salida + "\n";
    }



}
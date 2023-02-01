import java.util.*;

public class Monopoly {
    private Random rand;
    private Scanner sc;
    private ArrayList<CajaDeComunidad> cajaDeComunidad;

    public Monopoly(){
        rand = new Random();
        sc = new Scanner(System.in);
        cajaDeComunidad = new ArrayList<CajaDeComunidad>();
        cajaDeComunidad.add(CajaDeComunidad.CC1);
        cajaDeComunidad.add(CajaDeComunidad.CC2);
        cajaDeComunidad.add(CajaDeComunidad.CC3);
    }

    //----------------------------------------------

    public int menu(Jugador jug){

        System.out.println("\nTIENES " + jug.getDinero() + " euros\n");

        System.out.println("1. Tirar dados.\n2. Mostrar mis propiedades.");

        return eligeOpcion(1, 2);
    }

/*    public int eligeOpcion(){   //---------------------------------------------ANIADIR rango PARA MAS OPCIONES
        int respuesta = -1;
        boolean respuestaEnRango = false;

        do{
            try{
                System.out.print("OPCION: ");
                respuesta = Integer.parseInt(sc.nextLine());
            }   catch (Exception e){
                System.out.println("Valor incorrecto");
            }

            if (respuesta == 1 || respuesta == 2){
                respuestaEnRango = true;
            }

        } while (!respuestaEnRango);

        return respuesta;
    }*/

//---------------------------------------------------------------- OPCIONES RANGO

    public int eligeOpcion(int min, int max){
        int respuesta = -1;
        boolean respuestaEnRango = false;

        do{
            try{
                System.out.print("OPCION: ");
                respuesta = Integer.parseInt(sc.nextLine());
            }   catch (Exception e){
            }

            if (respuesta >= min && respuesta <= max){
                respuestaEnRango = true;
            }
            else System.out.print("Valor incorrecto\n");

        } while (!respuestaEnRango);

        return respuesta;
    }

    public int tirarDados(){
        int dado1 = rand.nextInt(6-1)+1;
        int dado2 = rand.nextInt(6-1)+1;

        System.out.println("Valor del dado 1: " + dado1);
        System.out.println("Valor del dado 2: " + dado2);

        return dado1+dado2;
    }

    public int tirarDadosCarcel(Jugador jug){
        int sale = 0;
        int dado1 = rand.nextInt(6-1)+1;
        int dado2 = rand.nextInt(6-1)+1;


        System.out.print("\n------------------------------CARCEL------------------------------\n");
        System.out.print("\nTurno de carcel: ");
        System.out.print(jug.getPena()+1);
        System.out.print("\n");
        System.out.println("Valor del dado 1: " + dado1);
        System.out.println("Valor del dado 2: " + dado2);

        if ((dado1 == dado2) || (jug.getPena() >=3)){
            System.out.print("Karem ha hecho la de prostituta borracha y consigue sacarte de la carcel.\n");
            jug.setCarcel(false);
            sale = dado1 + dado2;
        }
        else System.out.print("Hoy no.\n");
        System.out.print("\n------------------------------------------------------------------\n\n");
        jug.setPena(jug.getPena()+1);

        return sale;
    }

    public void moverJugador(Jugador jug, int pasos, Tablero tabl){
        int tamaTablero = tabl.getCasillas().size();

        for (int i = 0; i < pasos; i++){
            jug.setPosicion(jug.getPosicion()+1);

            if (jug.getPosicion() >= tamaTablero){
                jug.setPosicion(0);
                dineroCasillaInicial(jug);
            }
        }
    }

    private void dineroCasillaInicial(Jugador jug){
        double dineroASumar = jug.getDINEROINICIAL() * 0.1;

        jug.setDinero(jug.getDinero() + dineroASumar);
        System.out.println("Se suman " + dineroASumar + " euros al jugador " + jug.getFigura().toUpperCase() + " (" + jug.getNombre().toUpperCase() + ") por pasar por la primera casilla.");
        System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
        System.out.println("");
    }

    public boolean calleConDueno(ArrayList<Jugador> jugadores, Casilla calle){
        boolean propietario = false;
        ArrayList<Casilla> misPropiedades = new ArrayList<Casilla>();
        
        for (Jugador i : jugadores){
            misPropiedades = i.getMisCalles();
            if (calle.getTipo() == "Estacion") misPropiedades = i.getMisEstaciones();
            for (Casilla j : misPropiedades){
                if (calle == j){
                    propietario = true;
                }
            }
        }

        return propietario;
    }

    public Jugador quienEsElDueno(ArrayList<Jugador> jugadores, Casilla calle){
        Jugador propietario = new Jugador("", "");
        ArrayList<Casilla> misPropiedades = new ArrayList<Casilla>();

        for (Jugador i : jugadores){
            misPropiedades = i.getMisCalles();
            if (calle.getTipo() == "Estacion") misPropiedades = i.getMisEstaciones();
            for (Casilla j : misPropiedades){
                if (calle == j){
                    propietario = i;
                }
            }
        }

        return propietario;
    }

    public void comprar(Jugador jug, Casilla calle){
        int respuesta = -1;
        String tipo = calle.getTipo();

        System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
        System.out.println("Quieres comprar la " + tipo.toLowerCase() + " " + calle.getNombre() + " (" + calle.getColor() + ")" + " [" + calle.getPrecioCompra() + " euros]?");
        System.out.print("1. SI\n2. NO\n");
            respuesta = eligeOpcion(1, 2);

        if (respuesta == 1){
            if (jug.getDinero() >= calle.getPrecioCompra()){
                if (calle.getTipo() == "Calle") jug.anadirCalle(calle);
                else jug.anadirEstacion(calle);
                jug.setDinero(jug.getDinero()-calle.getPrecioCompra());
                System.out.println("Se ha comprado la " + tipo.toLowerCase() + ".");
                System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
                System.out.println("");
            }
            else {
                System.out.println("No tienes dinero para comprar la " + tipo.toLowerCase() + ".");
            }
        }
        else{
            System.out.println("No ha comprado la " + tipo.toLowerCase() + ".");
        }
    }

    public void pagarEstacion(Jugador jug, Jugador jugRival, Casilla calle){
        double precioAlquiler = calle.getPrecioAlquiler(jugRival.getMisEstaciones().size());
        jugRival.setDinero(jugRival.getDinero()+precioAlquiler);
        jug.setDinero(jug.getDinero()-precioAlquiler);

        System.out.print("\n-----------------------------ESTACION-----------------------------\n");
        System.out.println("\nCaiste en la estacion " + calle.getNombre() + " del jugador " + jugRival.getFigura().toUpperCase() + " (" + jugRival.getNombre().toUpperCase() + ")");
        System.out.println("Pagaste " + precioAlquiler + " euros de peaje.");
        System.out.print("\n------------------------------------------------------------------\n");
        System.out.println("\nTIENES " + jug.getDinero() + " euros.");
        System.out.println("");
    }

    public void pagarAlquiler(Jugador jug, Jugador jugRival, Casilla calle, Tablero tabl){
        double precioAlquiler;

        if (!(tengoTodas(jugRival, calle, tabl))){
            precioAlquiler = calle.getPrecioAlquiler(0);
        }
        else{
            precioAlquiler = calle.getPrecioAlquiler(calle.getEdificios()+1);
        }


        if (jug.getDinero() - precioAlquiler >= 0){
            jugRival.setDinero(jugRival.getDinero()+precioAlquiler);
            jug.setDinero(jug.getDinero()-precioAlquiler);
        } else {
            jugRival.setDinero(jugRival.getDinero()+precioAlquiler);
            jug.setDinero(0);
        }

        System.out.print("\n-----------------------------ALQUILER-----------------------------\n");
        System.out.println("\nCaiste en la calle " + calle.getNombre() + " del jugador " + jugRival.getFigura().toUpperCase() + " (" + jugRival.getNombre().toUpperCase() + ")");
        System.out.println("Pagaste " + precioAlquiler + " euros de alquiler.");
        System.out.print("\n------------------------------------------------------------------\n");
        System.out.println("\nTIENES " + jug.getDinero() + " euros.");
        System.out.println("");
    }

//----------------------------------------------------------------------------------------------------------

    public void estoyEnMiCalle(Jugador jug, Casilla calle, Tablero tabl){
        int respuesta = -1;
        System.out.print("Eres el propietario de la calle "+calle.getNombre()+".\n");
        if (tengoTodas(jug, calle, tabl)){
            System.out.print("Tienes todas las calles del barrio " + calle.getColor() + ".\n");
            System.out.print(tabl.imprimeCallesGrupo(calle.getColor()));
            System.out.print("Puedes hacer obras:\n    1. SI\n    2. NO\n");
            respuesta = eligeOpcion(1, 2);
            if (respuesta == 1) hacerObras(jug, calle, tabl);
        }
        if (tabl.comoEstaElBarrio(calle.getColor()) == 0) venderMiCalle(jug, calle);
        else System.out.print("\nNo puedes vender tu calle con edificios en el barrio.\n");
    }

//----------------------------------------------------------------------------------------------------------

    public void hacerObras(Jugador jug, Casilla calle, Tablero tabl){
        boolean terminar = false;
//INFO
        while (terminar == false){

            System.out.print("\n");
            for (Casilla c : tabl.getCasillas()){
                if (c.getColor() == calle.getColor()){
                    System.out.print("En " + c.getNombre() + " tienes ");
                    if(c.getEdificios() < 5 && c.getEdificios() > 0){
                        System.out.print(c.getEdificios());
                        System.out.print(" CASAS.\n");
                    }
                    else if(c.getEdificios() == 0){
                        System.out.print("0 CASAS.\n");
                    }
                    else if (c.getEdificios() == 5){
                        System.out.print("4 CASAS Y 1 HOTEL.\n");
                    }
                }
            }
            System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
            System.out.print("Coste por CONSTRUIR: " + calle.getPrecioCasas() + " euros por EDIFICIO\n");
            System.out.print("Ingreso por DERRIBAR: " + (calle.getPrecioCasas()/2) + " euros por EDIFICIO\n");
//OBRAS
            int respuesta = -1;
            System.out.print("\n");
    //MENU "No todas con 1 CASA / 0 CASAS"
            if (tabl.comoEstaElBarrio(calle.getColor()) == 0 || tabl.comoEstaElBarrio(calle.getColor()) == 1){
                int i = 1;
                ArrayList<Casilla> miBarrio = new ArrayList<Casilla>();
                System.out.print("Necesitas al menos 1 CASA en cada CALLE para construir mas de 1 CASA en alguna CALLE:\n");

                for (Casilla c : tabl.getCasillas()){
                    if (c.getColor() == calle.getColor() && c.getEdificios() == 0){
                        miBarrio.add(c);
                        System.out.print("    " + i + ". CONSTRUIR 1 CASA en " + c.getNombre() + "\n");
                        i += 1;
                    }
                }

                for (Casilla c : tabl.getCasillas()){
                    if (c.getColor() == calle.getColor() && c.getEdificios() == 1){
                        miBarrio.add(c);
                        System.out.print("    " + i + ". DERRIBAR 1 CASA en " + c.getNombre() + "\n");
                        i += 1;
                    }
                }                   //------------------REFACTORIZAR TODO LO POSIBLE!!!!
                System.out.print("    " + i + ". TERMINAR\n");
                respuesta = eligeOpcion(1, i);
    //ACCION
                if (respuesta == i){
                    System.out.print("Has terminado las obras.");
                    terminar = true;
                }
                else {
                    for (Casilla c : tabl.getCasillas()){
                        if (c.getNombre() == miBarrio.get(respuesta-1).getNombre()){
                            if (c.getEdificios() == 0){
                                c.setEdificios(1);
                                jug.setDinero(jug.getDinero() - c.getPrecioCasas());
                            }
                            else {
                                c.setEdificios(0);
                                jug.setDinero(jug.getDinero() + (c.getPrecioCasas() / 2));
                            }
                        }
                    }
                }
            }
    //MENU "Todas con 1 CASA / > 1 CASAS"
            else {
                int i = 1;
                ArrayList<Casilla> miBarrio = new ArrayList<Casilla>();
                System.out.print("Ya tiene al menos 1 CASA en cada CALLE. Puedes hacer obras en\n");
                for (Casilla c : tabl.getCasillas()){
                    if (c.getColor() == calle.getColor()){
                        miBarrio.add(c);
                        System.out.print("    " + i +". " + c.getNombre() + "\n");
                        i += 1;
                    }
                }
                System.out.print("    " + i + ". TERMINAR\n");
                respuesta = eligeOpcion(1, i);
    //ACCION
                if (respuesta == i){
                    System.out.print("Has terminado las obras.\n");
                    terminar = true;
                }
                else {
                    for (Casilla c : tabl.getCasillas()){
                        if (c.getNombre() == miBarrio.get(respuesta-1).getNombre()){
                            obrasEnCalle(c, jug, miBarrio, tabl);
                        }
                    }
                }
            }

        }

    }

//----------------------------------------------------------------------------------------------------------

    public void obrasEnCalle(Casilla calle, Jugador jug, ArrayList<Casilla> Barrio, Tablero tabl){
        int respuesta;
        for (Casilla c : tabl.getCasillas()){
            if (c.getNombre() == calle.getNombre()){
// TODAS CON 1 CASA
                if (tabl.comoEstaElBarrio(c.getColor()) == 2){
                    System.out.print("\nEn " + c.getNombre() + " puedes\n    1. CONSTRUIR hasta 4 EDIFICIOS\n    2. DERRIBAR 1 CASA\n    3. VOLVER\n");
                    respuesta = eligeOpcion(1, 3);

                    if (respuesta == 2){
                        c.setEdificios(0);
                        jug.setDinero(jug.getDinero() + (c.getPrecioCasas() / 2));
                    }
                    else if (respuesta == 1){
                        System.out.print("Introduce cuantos EDIFICIOS que quieres CONSTRUIR\n");
                        respuesta = eligeOpcion(0, 4);
                        c.setEdificios(c.getEdificios() + respuesta);
                        jug.setDinero(jug.getDinero() - (c.getPrecioCasas() * respuesta));
                    }
                }
// TODAS CON HOTEL
                else if (c.getEdificios() == 5){
                    System.out.print("\nEn " + c.getNombre() + " puedes DERRIBAR hasta 4 EDIFICIOS.\n Introduce cuantos EDIFICIOS quieres DERRIBAR\n");
                    respuesta = eligeOpcion(0, 4);
                    c.setEdificios(c.getEdificios() - respuesta);
                    jug.setDinero(jug.getDinero() + ((c.getPrecioCasas() / 2) * respuesta));
                }
// MIXTO
                else {
                    int i = 1;
                    System.out.print("\nEn " + c.getNombre() + " puedes\n");
                    if (c.getEdificios() < 5){
                        System.out.print("    " + i + ". CONTRUIR hasta " + (5 - c.getEdificios()) + " EDIFICIOS\n");
                        i += 1;
                    }
                    if (c.getEdificios() > 1){
                        System.out.print("    " + i + ". DERRIBAR hasta " + (c.getEdificios() - 1) + " CASAS\n");
                        i += 1;
                    }
                    System.out.print("    " + i + ". VOLVER\n");
                    respuesta = eligeOpcion(1, i);

                    if (respuesta == 1 && c.getEdificios() < 5){
                        System.out.print("Introduce cuantos EDIFICIOS quieres CONTRUIR\n");
                        respuesta = eligeOpcion(0, (5 - c.getEdificios()));
                        c.setEdificios(c.getEdificios() + respuesta);
                        jug.setDinero(jug.getDinero() - (c.getPrecioCasas() * respuesta));
                    }
                    else if (respuesta != i){
                        System.out.print("Introduce cuantos EDIFICIOS quieres DERRIBAR\n");
                        respuesta = eligeOpcion(0, (c.getEdificios() - 1));
                        c.setEdificios(c.getEdificios() - respuesta);
                        jug.setDinero(jug.getDinero() + ((c.getPrecioCasas() / 2) * respuesta));
                    }
                }
            }
        }
    }

//----------------------------------------------------------------------------------------------------------

    public boolean tengoTodas(Jugador jug, Casilla calle, Tablero tabl){
        boolean tengoTodas = true;

        for (Casilla c : tabl.getCasillas()){
            if (c.getColor() == calle.getColor()){
                if(!(jug.getMisCalles().contains(c))) tengoTodas = false;
            }
        }
        return tengoTodas;
    }

//----------------------------------------------------------------------------------------------------------

    public void venderMiCalle(Jugador jug, Casilla calle){
        int respuesta = -1;
        String tipo = calle.getTipo();

        System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
        System.out.print("Eres el propietario de la " + tipo.toLowerCase() + " " + calle.getNombre()+".\n");
        System.out.print("Puedes vender la " + tipo.toLowerCase() + " a la banca por "+(calle.getPrecioCompra()*0.5)+" euros.\n");
        System.out.print("1. VENDER\n2. NO VENDER\n");
        respuesta = eligeOpcion(1, 2);

        if (respuesta == 1){

            if (tipo == "Calle") jug.perderCalle(calle);
            else jug.perderEstacion(calle);

            jug.setDinero(jug.getDinero()+calle.getPrecioCompra()*0.5);

            System.out.print("Has vendido la " + tipo.toLowerCase() + " " + calle.getNombre()+".\n");
            System.out.println("\nTIENES " + jug.getDinero() + " euros\n");
            System.out.println("");
        } else System.out.print("NO has vendido la " + tipo.toLowerCase() + " " + calle.getNombre()+".\n");
    }

//----------------------------------------------------------------------------------------------------------

    public boolean jugadorArruinado(ArrayList<Jugador> jugadores){
        boolean arruinado = false;

        for (Jugador i : jugadores){
            if (i.getDinero() <= 0){
                arruinado = true;
            }
        }

        return arruinado;
    }

    public Jugador queJugadorSeHaArruinado(ArrayList<Jugador> jugadores){
        Jugador jug = new Jugador("NONE", "NONE");

        for (Jugador i : jugadores){
            if (i.getDinero() <= 0){
                jug = i;
            }
        }

        return jug;
    }

    public CajaDeComunidad cartaCajaDeComunidadAleatoria(){
        int aleatorio = rand.nextInt(cajaDeComunidad.size());

        return cajaDeComunidad.get(aleatorio);
    }

    public void sacarTarjetaSuerte(Jugador jugador){
        Suerte miSuerte = new Suerte();
        TarjetaSuerte miTarjetaSuerte = miSuerte.sacaTarjetaSuerte();

        System.out.print("\n------------------------------SUERTE------------------------------\n\n" + miTarjetaSuerte.getOpcion() + "\n(INTRO para continuar)\n");
        sc.nextLine();
        System.out.print("\n" + miTarjetaSuerte.getResolucion() + "\n");

        if (miTarjetaSuerte.getPrecio() < 0) System.out.print("Has perdido ");
        else System.out.print("Has ganado ");
        if (miTarjetaSuerte.getPrecio() < 0) System.out.print(-(miTarjetaSuerte.getPrecio()));
        else System.out.print(miTarjetaSuerte.getPrecio());

        System.out.print(" euros.\n");
        System.out.print("\n------------------------------------------------------------------\n");
        jugador.setDinero(jugador.getDinero() + miTarjetaSuerte.getPrecio());
        System.out.println("\nTIENES " + jugador.getDinero() + " euros\n");
    }
}
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class Suerte {

    private ArrayList<String> opciones = new ArrayList<String>();
    private ArrayList<Double> precios = new ArrayList<Double>();
    private ArrayList<String> bueno = new ArrayList<String>();
    private ArrayList<String> malo = new ArrayList<String>();
    private Random rand = new Random();
    private int opcion;
    private int karma;
    private double potencia;
    private int masOMenos;
    private String miOpcion;
//------------------------------------------DATOS
    private void aniadeOpciones(){
        this.opciones.add("Esa diligencia tiene buena pinta, vas a intentar atracar a los pasajeros.");//120
        this.opciones.add("Al doblar un recodo del camino te salta a la cara un oso grizzly.");//20
        this.opciones.add("El tren de mercancias es siempre una buena opcion, mucho material y poca gente.");//240
        this.opciones.add("El cementerio de Saint Denis es oscuro y estrecho, cualquiera que permanezca dentro es un buen objetivo.");//80
        this.opciones.add("Has visto un cartel de se busca con una cifra mas alta de lo abitual, vas ha buscar al tipo en cuestion.");//140
        this.opciones.add("Una tarde de relax en la taberna de Valentine con Lenny siempre supone un duro reto.");//40
        this.opciones.add("Ese rebanio de ovejas se podria vender por un buen precio en la subasta de Emerald Ranch.");//160
        this.opciones.add("El pantano de Lagras es traicionero en todos los sentidos.");//60
        this.opciones.add("Un tipo montado a caballo te propone una carrera desde Annesburg hasta Chez Porter.");//100
        this.opciones.add("En el sotano de la armeria de Rhodes se cuece algo turbio. Decides entrar a ver.");//220
        this.opciones.add("Siempre hay tiempo para unas cuantas manos de Poker en la taberna de Vann Horn.");//200
        this.opciones.add("El Black Jack es algo serio para las gentes de Strawberry.");//180
    }
    private void aniadeBueno(){
        this.bueno.add("Esos ricachones de Saint Denis siempre tienen pasta y joyas.");
        this.bueno.add("Por suerte tus reflejos siguen siendo buenos. Disparas al oso en la cara justo a tiempo y vendes la piel y la carne.");
        this.bueno.add("Sin que el maquinista se entere vaciais la mayor parte de los vagones de carga.");
        this.bueno.add("Esa pobre viuda ahora tiene una boca menos que alimentar, y esas joyas le pesan demasiado.");
        this.bueno.add("Encuentras al tipo del cartel en cuclillas y con los pantalones por los tobillos detras de un risco.\nLa cosa resulta muy sencilla.");
        this.bueno.add("Lennyyy!!! Al encontrar a tu amigo reparte contigo las ganancias de la noche.\nHa estado metiendo las manos en los bolsillos de todos los parroquianos.");
        this.bueno.add("Un disparo cerca de los ganaderos es suficiente para hacerlos huir, lo demas es pan comido.");
        this.bueno.add("Entre la niebla ves algo moverse. Esos paletos no deberian intentar nada contigo.\nSaqueas sus bolsillos.");
        this.bueno.add("Ese caballo era rapido, pero tu montura es mas habil a la hora de recortar campo a traves.\nLlegas a chez Porter con tiempo de sobra.");
        this.bueno.add("Un par de tipos estan repartiendo el contenido de un cofre roto.\nAhora tu parte es del 100% y hay dos cadaveres en el sotano.");
        this.bueno.add("La tarde ha sido buena, y la noche mejor.");
        this.bueno.add("Ese crupier quiere algo contigo, seguro.");
    }
    private void aniadeMalo(){
        this.malo.add("Parece que no es buena idea asaltar una diligencia que ya esta siendo secuestrada.\nEsos malditos o Driscoll te pillan por sorpresa.");
        this.malo.add("Parece que estas vivo, pero algun maldito bandido de Lemoyne te ha vaciado los bolsillos.");
        this.malo.add("Pero nunca se sabe que puede salir mal. En esta ocasion, todo. La recompensa por tu cabeza sera alta.");
        this.malo.add("Incluido tu. Tres tipos salen de la nada y te golpean la cabeza. Al despertar tu bolso pesa menos.");
        this.malo.add("Encuentras al tipo del cartel en cuclillas y con los pantalones por los tobillos detras de un risco.\nPero no esta solo y sales mal parado.");
        this.malo.add("Lennyyy!!! La tarde se hace larga y la noche mas.\nAl despertar tienes un tremendo dolor de cabeza y algo menos de dinero.");
        this.malo.add("Un disparo cerca de los ganaderos es suficiente para hacerlos huir, pero \"algo sale mal\" y matas a uno de ellos.\nTienes que pagar la recompensa por tu cabeza.");
        this.malo.add("Por ejemplo, una noche te invitan a cenar y terminas drogado, robado y muy posiblemente violado.");
        this.malo.add("Ese caballo es rapido, por lo que intentas recortar entre los arbustos.\nTu montura tropieza y terminas sucio y dolorido.");
        this.malo.add("Un par de tipos reparten el contenido de un cofre roto.\nIntentas sorprenderlos pero un tercer tipo te rompe una botella en la cabeza.\nAhora el botin que reparten es mayor.");
        this.malo.add("No deberias jugar borracho.");
        this.malo.add("Ese crupier merece la muerte... pero mejor otro dia.");
    }
    private void aniadePrecios(){
        this.precios.add(120.0);
        this.precios.add(20.0);
        this.precios.add(240.0);
        this.precios.add(80.0);
        this.precios.add(140.0);
        this.precios.add(40.0);
        this.precios.add(160.0);
        this.precios.add(60.0);
        this.precios.add(100.0);
        this.precios.add(220.0);
        this.precios.add(200.0);
        this.precios.add(180.0);
    }

//------------------------------------------

    public Suerte(){
        aniadeOpciones();
        aniadeBueno();
        aniadeMalo();
        aniadePrecios();
        this.opcion = rand.nextInt(12);
        this.karma = rand.nextInt(2);
        this.potencia = rand.nextInt(20)+1;
        this.masOMenos = rand.nextInt(2);
        this.miOpcion = this.opciones.get(opcion);
    }


    public TarjetaSuerte sacaTarjetaSuerte(){
        TarjetaSuerte miTarjeta = new TarjetaSuerte();
        double cuantiaFinal;
        String miResolucion;
        
        if (this.karma == 0){
            miResolucion = this.malo.get(this.opcion);
            if (this.masOMenos == 0) cuantiaFinal = -(this.precios.get(this.opcion)+((this.precios.get(this.opcion)/100.0)*this.potencia));
            else cuantiaFinal = -(this.precios.get(this.opcion)-((this.precios.get(this.opcion)/100.0)*this.potencia));
        }
        else{
            miResolucion = this.bueno.get(this.opcion);
            if (this.masOMenos == 0) cuantiaFinal = this.precios.get(this.opcion)-((this.precios.get(this.opcion)/100.0)*this.potencia);
            else cuantiaFinal = this.precios.get(this.opcion)+((this.precios.get(this.opcion)/100.0)*this.potencia);
        }
        miTarjeta = new TarjetaSuerte(this.miOpcion, Math.round(cuantiaFinal), miResolucion);
        return miTarjeta;
    }


}    
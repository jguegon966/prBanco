/**
 * <h2>Clase Main</h2>
 * <p>Clase utilizada para lanzar el proyecto PrBanco y lo ejecuta</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 22-04-2022
 */
package Main;

import Banco.Banco;
import Cuenta.CuentaBonus;
import Cuenta.CuentaProtegida;
import Cuenta.CuentaStandard;
import Operaciones.Operacion;
import Persona.Persona;
import Banco.AppBanco;

import java.util.Calendar;

/**
 * Clase main que servirá dpara lanzar la aplicacion
 */
public class Main {

    /**
     * Metodo main que nos permitirá lanzar el programa
     * @param args se le pasan los argumentos
     */
    public static void main(String[] args) {

        /*
         * se define un nuevo objeto de tipo AppBanco
         */
        AppBanco ab = new AppBanco();
        /*
         * a traves del objeto ab se llama al menu
         */
        ab.menu();

    }
}

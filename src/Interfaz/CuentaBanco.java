/**
 * <h2>Clase Interfaz CuentaBanco</h2>
 * <p>Clase utilizada para crear una interfaz con los metodos necesarios</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 22-04-2022
 */
package Interfaz;

import Operaciones.Operacion;
import Persona.Persona;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Clase de tipo interfaz donde se encontraran los metodos utilizados por las demas clases
 */
public interface CuentaBanco {

    /**
     * Metodo credit que servirá para dar un credito
     * @param amount cantidad del credito
     */
    public void credit(float amount);

    /**
     * Metodo que sirve para retirar dinero
     * @param amount cantidad que se pasa para retirar dinero
     */
    public void withdraw(float amount);

    /**
     * Metodo que sirve para ingresar dinero
     * @param amount cantidad a ingresar
     */
    public void deposit(float amount);

    /**
     * Metodo getter de persona
     * @return devuelve la persona titular
     */
    public Persona getOwner();

    /**
     * Metodo getter de numero de cuenta
     * @return devuelve el numero de la cuenta de la persona especificada
     */
    public String getNumber();

    /**
     * Metodo getter de balance (dinero que tiene cada cuenta)
     * @return devuelve el dinero que tiene dicha cuenta, con un float
     */
    public float getBalance();

    /**
     * Metodo para listar las operaciones a partir de una fecha en concreto
     * @param time fecha en concreto la cual a partir de ella va a listarnos las operaciones de cada cuenta
     * @return devuelve un ArrayList con las operaciones realizadas a partir de una fecha
     */
    public List<Operacion> getOperationsAfter(Calendar time);

}

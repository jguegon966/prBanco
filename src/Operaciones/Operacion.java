/**
 * <h2>Clase Operaciones</h2>
 * <p>Clase utilizada para realizar las distintas operaciones en cada cuenta</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 22-04-2022
 */
package Operaciones;

import Banco.AppBanco;
import Banco.Banco;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Clase Operaciones donde se encontraran las operaciones realizadas
 */
public class Operacion implements Serializable{

    /**
     * date de tipo Calendar que sirve a modo de fecha
     */
    private Calendar date;
    /**
     * amount de tipo float, que es la cantidad que le iremos pasando a los distintos metodos
     */
    private float amount;
    /**
     * type de tipo String, que sirve para indicar el tipo de operacion que realizamos
     */
    private String type;

    /**
     * numeroCuenta de tipo String que sirve para dar un numero de cuenta a una cuenta
     */
    private String numeroCuenta;

    /**
     * Constructor vacio al que se le define internamente un numero de cuenta vacio
     */
    public Operacion(){

        this.numeroCuenta = null;

    }

    /**
     * Constructor al que le pasamos los parametros date, amount y type
     * @param date fecha desde la que queremos listar las operaciones
     * @param amount cantidad que le pasaremos a los distintos metodos
     * @param type tipo de ooperacion que hemos realizado
     */
    public Operacion(Calendar date, float amount, String type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    /**
     * Constructor de la clase Operacion al que se le pasan la fecha, la cantidad, el tipo y el numero de cuenta
     * @param date fecha desde la que queremos listar las operaciones
     * @param amount cantidad que le pasaremos a los distintos metodos
     * @param type tipo de operacion que hemos realizado
     * @param numeroCuenta numero de cuenta desde la que se van a realizar las operaciones
     */
    public Operacion(Calendar date, float amount, String type, String numeroCuenta) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.numeroCuenta = numeroCuenta;
    }
    /**
     * Metodo getDate que nos devolvera la fecha
     * @return devuelve la fecha
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Metodo getAmount que nos devolvera la canntidad de las operaciones
     * @return devuelve la cantidad
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Metodo setAmount que nos dejara establecer la cantidad en concreto que le queremos pasar
     * @param amount almacena la cantidad
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Metodo getType que nos devolvera el tipo de operacion realizada
     * @return devuelve el tipo de operacion realizada
     */
    public String getType() {
        return type;
    }

    /**
     * Metodo setType que nos dejara establecer el tipo de operacion realizada
     * @param type almacenará el tipo de operacion realizada
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Este metodo es una especie de setter camuflado para realizarlo de otra forma que no sea Setter de calendar
     * @param date almacenará la fecha que le pasaremos
     */
    public void changeCalendar(Calendar date){

        this.date = date;

    }

    /**
     * Metodo getter para el numero de cuenta que nos dejará coger el numero de la cuenta
     * @return numeroCuenta devuelve el numero de cuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Metodo setter para numero de cuenta que nos permite definir el numero de la cuenta
     * @param numeroCuenta se le pasa el numero de cuenta
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * Metodo toString que muestra la operacion realizada, la fecha en la que se ha realizado, la cantidad que se le ha pasado, y el tipo de operacion
     * @return cadena de caracteres que muestra la fecha, la cantidad y el tipo de operacion
     */
    @Override
    public String toString() {
        return "\n        MI BANCO       \n" +
                " Fecha: " + getDate().getTime() +"\n"+
                " Operacion: "+type+" Cantidad: " + amount+"\n"+
                " Cuenta:"+ numeroCuenta;
    }

}

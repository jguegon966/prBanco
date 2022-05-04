/**
 * <h2>Clase Banco</h2>
 * <p>Clase utilizada para crear cuentas dentro de un Hasmap que almacenara las distintas cuentas creadas con sus operaciones realizadas</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 22-04-2022
 */
package Banco;

import Cuenta.Cuenta;
import Interfaz.CuentaBanco;
import Operaciones.Operacion;
import Persona.Persona;

import java.io.Serializable;
import java.util.*;

/**
 * Clase Banco que cogerá los metodos de cuenta
 */
public class Banco implements Serializable {

    /**
     * atributo listaCuentas de tipo Map que sirve para almacenar las cuentas en un HasMap
     */
    Map<String, CuentaBanco> listaCuentas;

    /**
     * constructor por defecto el cual sirve para crear un HasMap nuevo
     */
    public Banco(){
        this.listaCuentas= new HashMap<>();
    }

    /**
     * Metodo para crear una cuenta nueva
     * @param account se le pasa la cuenta en concreto para almacenarla en el HasMap
     */
    public void addAccount(CuentaBanco account){

        if (listaCuentas.containsKey(account.getNumber())) {
            System.out.println("No se puede introducir el producto. El código esta repetido.");
        } else {
            listaCuentas.put(account.getNumber(), account);
        }

    }

    /**
     * metodo para eliminar una cuenta del HasMap
     * @param account se le pasa la cuenta en cuestion que deseamos borrar
     */
    public void removeAccount(CuentaBanco account){
        if (listaCuentas.containsKey(account.getNumber())) {
            listaCuentas.remove(account.getNumber());
        }else {
            System.out.println("No hay ninguna cuenta con ese numero");
        }
    }

    /**
     * metodo para mostrar el balance de la cuenta que queremos en concreto del HasMap
     * @param accountNumber se le pasa el numero de la cuenta que queremos mostrar su balance
     * @return devuelve el String dineroactual, que es el dinero actual que tenemos en dicha cuenta
     */
    public String displayBalance(String accountNumber) {

        String dineroactual = null;
        if (!listaCuentas.containsKey(accountNumber)) {
            System.out.println("El código no existe.");
        } else {
            dineroactual = String.valueOf(listaCuentas.get(accountNumber).getBalance());
        }

        return dineroactual;

    }

    /**
     * metodo para dar un creadito a una cuenta, y llamaremos al metodo credit de la clase banco, con la cuenta del HasMap
     * @param accountNumber numero de la cuenta que queremos dar el credito
     * @param amount devuelve la cantidad de la cuenta con el credito aplicado
     */
    public void creditAccount(String accountNumber, float amount){

        if (!listaCuentas.containsKey(accountNumber)) {
            System.out.println("El código no existe.");
        } else {
            listaCuentas.get(accountNumber).credit(amount);
        }

    }

    /**
     * metodo para retirar dinero de una cuenta que llamamos del HasMap
     * @param accountNumber numero de la cuenta de la que queremos retirar dinero
     * @param amount cantidad que queremos retirar de la cuenta dentro del HasMap
     */
    public void withdraw(String accountNumber, float amount){

        if (!listaCuentas.containsKey(accountNumber)) {
            System.out.println("El código no existe.");
        } else {
            listaCuentas.get(accountNumber).withdraw(amount);
        }

    }

    /**
     * metodo para mostrar las cuentas creadas y añadidas al HasMap
     * @param accountNumber numero de la cuenta que queremos mostrar
     * @return  devuelve la lista de las cuentas a mostrar del HasMap
     */
    public String displayAccount(String accountNumber){

        if (!listaCuentas.containsKey(accountNumber)) {
            System.out.println("El código no existe.");
        }

        return listaCuentas.get(accountNumber).toString();

    }

    /**
     * metodo para transferir dinero de una cuenta del HasMap a otra
     * @param from cuenta de la qu queremos quitar el dinero para transferirlo
     * @param to cuenta a la que queremos ingresar el dinero sacado de la otra cuenta
     * @param amount devuelve la cantidad que hemos transferido
     */
    public void transfer(String from, String to, float amount){

        if (amount < 0) {

            amount = 0;

        } else {

            amount = amount;

            listaCuentas.get(from).withdraw(amount);
            listaCuentas.get(to).deposit(amount);

        }

    }

    /**
     * meetodo para depositar dinero en una cuenta del HasMap
     * @param accountNumber numero de cuenta en la que queremos hacer el deposito
     * @param amount devuelve la cantidad a depositar
     */
    public void depositAccount(String accountNumber, float amount){

        if (!listaCuentas.containsKey(accountNumber)) {
            System.out.println("El código no existe.");
        } else {
            listaCuentas.get(accountNumber).deposit(amount);
        }

    }



    /**
     * metodo para listar las operaciones de una cuenta dentro del HasMap
     * @param accountNumber numero de la cuenta de la que queremos listar las operaciones
     * @param time fecha desde la que queremos mostrar las operaciones, es decir a partir de esa fecha que me liste las operaciones realizadas
     * @return devuelve una lista con las operaciones a partir de dicha fecha
     */
    public List<Operacion> listOperationsAccount(String accountNumber, Calendar time) {

        return listaCuentas.get(accountNumber).getOperationsAfter(time);

    }


}

/**
 * <h2>Clase Cuenta</h2>
 * <p>Clase utilizada para crear las distintas cuentas de la clase banco</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 22-04-2022
 */
package Cuenta;


import Interfaz.CuentaBanco;
import Operaciones.Operacion;
import Persona.Persona;

import java.io.Serializable;
import java.util.*;

/**
 * Clase Cuenta que tendra los metodos para ingresar retirar, etc...
 */
public class Cuenta implements CuentaBanco, Serializable {

    /**
     * atributo owner de tipo persona que será el propietario de la cuenta
     */
    private Persona owner;//propietario de la cuenta

    /**
     * atributo number de tipo String que será el numero de la cuenta asignado
     */
    private String number;//numero de cuenta

    /**
     * atributo balance de tipo float que será el dinero actual de la cuenta
     */
    private float balance;//dinero de la cuenta

    /**
     * atributo theOperations de tipo List que servirá para almacenar todas las operaciones realizadas por cada cuenta
     */
    private List<Operacion> theOperations;//lista de operaciones

    /**
     * Constructor vacio de la clase cuenta que nos servirá para definir a traves del teclado los parametros del propietario de la cuenta, entre otros
     * y se define theOperations para crear un nuevo ArrayList para las operaciones vacio
     */
    public Cuenta(){

        this.theOperations = new ArrayList<>();

    }

    /**
     * Constructor al que se le pasa por parametros el numero de cuenta y el propietario de la cuenta
     * @param owner titula de la cuenta
     * @param number numero de la cuenta asignada a un titular
     */
    public Cuenta(Persona owner, String number) {
        this.owner = owner;
        this.number = number;
        this.balance = 0;
        this.theOperations = new ArrayList<>();
    }

    /**
     * Constructor al que se le pasa por parametros el propietario de la cuenta, el numero de cuenta y el balance que este tiene
     * @param owner propietario de la cuenta
     * @param number numero de la cuenta
     * @param balance dinero que el titular tiene en la cuenta
     */
    public Cuenta(Persona owner, String number, float balance) {
        this.owner = owner;
        this.number = number;
        this.balance = balance;
        this.theOperations = new ArrayList<>();
    }

    /**
     * metodo credit que servirá para dar un credito a una cuenta, es decir darle dinero
     * @param amount cantidad del credito
     */
    @Override
    public void credit(float amount) {

        Calendar time = Calendar.getInstance();
        String type = "deposito";

        Operacion op = new Operacion(time, amount, type);

        //comprobaremos si la cantidad amount es mayor que cero, en ese caso si es menor se le asignara a cero
        //si no se continuara con la operacion
        if (amount < 0) {

            amount = 0;

        } else {

            amount = amount;

            this.balance = this.balance + amount;
            setBalance(this.balance);
        }

        //se añade la operacion al ArrayList
        theOperations.add(op);

    }

    /**
     * metodo que serrvira para retirar dinero de una cuenta en concreto
     * @param amount cantidad que se pasa para retirar dinero
     */
    @Override
    public void withdraw(float amount){

        Calendar time = Calendar.getInstance();
        String type = "debito";

        Operacion op = new Operacion(time, amount, type);

        //se comprueba si el amount es mayor que cero y si es menor que cero se establecera este a cero
        if (amount < 0){

            amount = 0;

        } else {

            amount = amount;

            this.balance = this.balance - amount;
            setBalance(this.balance);
        }

        //se añade la operaccion al ArrayList
        theOperations.add(op);

    }

    /**
     * metodo para depositar o ingresar dinero en una cuenta
     * @param amount cantidad a ingresar
     */
    @Override
    public void deposit(float amount) {

        Calendar time = Calendar.getInstance();
        String type = "deposito";

        Operacion op = new Operacion(time, amount, type);

        //se hace la comprobacion de si el amount es mayor que cero y si no es asi se establece este a cero
        if (amount < 0) {

            amount = 0;

        } else {

            amount = amount;

            this.balance = this.balance + amount;
            setBalance(this.balance);

        }

        //se aañade la operacion al arrayList
        theOperations.add(op);

    }

    /**
     * metodo getter de Owner que servira para coger a la persona titular
     * @return devuelve el titular de la cuenta
     */
    public Persona getOwner() {
        return owner;
    }

    /**
     *metodo getter de Number que servira para coger el numero de cuenta
     * @return devuelve el numero de cuenta de cada persona
     */
    public String getNumber() {
        return number;
    }

    /**
     * metodo getter de Balance que devuelve el balance de cada cuenta, es decir el dinero que tiene
     * @return devuelve el balance de cada cuenta
     */
    public float getBalance() {
        return balance;
    }

    /**
     * metodo getter de TheOperations que devuelve la lista con las operaciones realizadas por cada cuenta
     * @return devuelve el ArrayList de las operaciones
     */
    public List<Operacion> getTheOperations() {
        return theOperations;
    }

    /**
     * metodo setter de Balance que sirve para establecer el balance o dinero de cada cuenta
     * @param balance establece el balance de cada cuenta
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * metodo setter de Owner que nos permite asignar al propietario de la cuenta
     * @param owner establece al propietario de la cuenta
     */
    public void setOwner(Persona owner) {
        this.owner = owner;
    }

    /**
     * Metodo setter del numero de cuenta que nos permite asignar el numero de cuenta
     * @param number establece el numero de cuenta
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Metodo getOperationsAfter que listara las operaciones realizadas por cada cuenta a partir de una fecha
     * @param time fecha en concreto la cual a partir de ella va a listarnos las operaciones de cada cuenta
     * @return devuelve una lista con las operaciones realizadas por una  cuenta a partir de la fecha time
     */
    @Override
    public List<Operacion> getOperationsAfter(Calendar time) {

        List<Operacion> theOperationsFecha = new ArrayList<>();

        for (Operacion operation: this.theOperations) {
            if(time.getTimeInMillis() <operation.getDate().getTimeInMillis()) {
                theOperationsFecha.add(operation);
            }
        }

        return theOperations;

    }

    /**
     * metodo dispkayOperatiosn que sirve para mostrar las operaciones del ArrayList por pantalla
     * @return devuelve las operaciones realizadas
     */
    public String displayOperations(){

        Iterator<Operacion> itr = this.theOperations.iterator();

        String temp= " ";

        while(itr.hasNext()){

            Operacion o=(Operacion) itr.next();
            temp = temp.concat(o.toString());

        }

        return temp;

    }

    /**
     * ToString modificado para que muestre solo el numero de cuenta, el nombre de la persona, su dni y el balance que tiene actualmente
     * @return String con el numero de cuenta, el nombre del propietario, su DNI y el balance que tiene tras las operaciones
     */
    @Override
    public String toString() {
        return "\nCuenta: " +number +
                " propietario: " + owner.getNombre().toString() +
                " DNI: " + owner.getDNI().toString() +
                " balance: " + balance;
    }
}

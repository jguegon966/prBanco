/**
 * <h2>Clase CuentaProtegida</h2>
 *<p>Clase CuentaProtegida que hereda de Cuenta y que sirve para sobreescribir cierto metodo ya que segun su tipo cambia la retirada de dinero con un limite minimo lowerbound</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 22-04-2022
 */
package Cuenta;

import Operaciones.Operacion;
import Persona.Persona;

import java.util.Calendar;

/**
 * Clase CuentaProtegida que extiende de Cuenta y tiene sus metodos
 */
public class CuentaProtegida extends Cuenta{

    /**
     * atributo lowerBOund de tipo float, que sirve para establecer un minimo de dinero a sacar
     */
    private float lowerBound;

    /**
     * Constructoor vacio de la clase Cuenta Protegida
     */
    public CuentaProtegida(){
        super();
    }

    /**
     * construcor al que se le pasan por parametros el titular de la persona heredada, el numero de cuenta heredado, y el limite a sacar establecido
     * @param owner propietario de la cuenta
     * @param number numero de la cuenta
     * @param lowerBound limite de dinero establecido
     */
    public CuentaProtegida(Persona owner, String number, float lowerBound) {
        super(owner, number);
        this.lowerBound = lowerBound;
    }

    /**
     * constructor al que se le pasan por parametros el titular de la cuenta heredado, el numero de cuenta heredado, el balance heredado y el limite estableccido
     * @param owner propietario de la cuenta
     * @param number numero de la cuenta
     * @param balance balance o dinero que hay en la cuenta
     * @param lowerBound limite establecido
     */
    public CuentaProtegida(Persona owner, String number, float balance, float lowerBound) {
        super(owner, number, balance);
        this.lowerBound = lowerBound;
    }

    /**
     * Metodo getter de LoweBound que nos permite coger el limite de dinero a retirar de una cuenta
     * @return lowerBound devuelve el limite a sacar de dinero de una cuenta
     */
    public float getLowerBound() {
        return lowerBound;
    }

    /**
     * Metodo setter de lowerBound nos permite asignar el limite de dinero a sacar de una cuenta
     * @param lowerBound devuelve el limite a sacar
     */
    public void setLowerBound(float lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * metodo withdraw sobreescrito que sirve para sacar dinero, pero que no nos dejará sacarlo por debajod del limite establecido
     * @param amount cantidad que se pasa para retirar dinero
     */
    @Override
    public void withdraw(float amount){

        Calendar time = Calendar.getInstance();
        String type = "debito";

        Operacion op = new Operacion(time, amount, type);

        //comprobamos si la cantidad esta por debajo del limite y de ser asi salta es siguiente mensaje.
        if(amount < lowerBound){

            System.out.println("No podras sacar dinero por debajo del limite establecido");

        }else{

            amount = amount;

            //para poner el balance restado (no se si esta bien)
            setBalance(getBalance() - amount);
        }

        //añadimos la operaccion al ArrayList
        getTheOperations().add(op);


    }

}

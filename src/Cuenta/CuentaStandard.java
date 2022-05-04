/**
 * <h2>Clase CuentaStandard</h2>
 * <p>Clase CuentaStandard que hereda de Cuenta y que sirve para sobreescribir cierto metodo ya que segun su tipo cambia la retirada de dinero con interes</p>
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
 * Clase CuentaStandard que extiende de Cuenta y tiene sus metodos
 */
public class CuentaStandard extends Cuenta {

    /**
     * atributo openDate de tipo Calendar que nos servira para establecer una fecha en concreto
     */
    private Calendar openDate;

    /**
     * atributo interest de tipo float qu enos sirve para establecer un interes por defecto
     */
    private float interest = 0.05f;

    /**
     * Constructor vacio de la clase Cuenta Standard
     */
    public CuentaStandard(){

    }

    /**
     * constructor al que se le pasa por parametros la persona titular, el numero de cuenta y el balance que esta tiene
     * @param owner propietario de la cuenta
     * @param number numero de la cuenta
     * @param balance balance o dinero que hay en la cuenta
     */
    public CuentaStandard(Persona owner, String number, float balance) {
        super(owner, number, balance);
    }

    /**
     * metodo withdraw modificado y sobreescrito de heredado en la clase cuenta, al que  a la hora de retirar dinero nos sumará el innteres
     * @param amount cantidad que se pasa para retirar dinero
     */
    @Override
    public void withdraw(float amount) {

        float amountinterest = 0;
        float balancerestado = 0;

        Calendar time = Calendar.getInstance();
        String type = "debito";

        Operacion op = new Operacion(time, amount, type);

        //comprobamos el amount si es mayor que cero
        if (amount < 0) {

            amount = 0;

        } else {

            amount = amount;

            amountinterest = amount * interest;
            balancerestado = getBalance() - (amount + amountinterest);
            setBalance(balancerestado);

        }

        //añadimos la operacion al arrayList
        getTheOperations().add(op);

    }

}

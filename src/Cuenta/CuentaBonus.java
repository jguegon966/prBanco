/**
 * <h2>Clase CuentaBonus</h2>
 *<p>Clase CuentaBonus que hereda de Cuenta y que sirve para sobreescribir cierto metodo ya que segun su tipo cambia el metodo credit y le da un bonus llamado bonusRate</p>
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
 * CuentaBonus que extiende de Cuenta y tiene sus metodos
 */
public class CuentaBonus extends Cuenta{

    /**
     * atributo bonusRate de tipo float que sirve para sumar bonus al credito
     */
    private float bonusRate;

    /**
     * Constructor vacio de la clase Cuenta Bonus
     */
    public CuentaBonus(){

    }

    /**
     * construcor al que se le pasan por parametros el titular de la persona heredada, el numero de cuenta heredado, y el bonus que se le suma al credito
     * @param owner propietario de la cuenta
     * @param number numero de la cuenta
     * @param bonusRate bonus que se le suma al credito
     */
    public CuentaBonus(float bonusRate, Persona owner, String number) {
        super(owner, number);
    }

    /**
     * Metodo getter de BOnus rate que es le bonus que se le da a una cuenta Bonus
     * @return bonusRate devuelve el bonus de la cuenta
     */
    public float getBonusRate() {
        return bonusRate;
    }

    /**
     * Metodo setter de Bonus Rate que nos permite definir el bonus de la cuenta 
     * @param bonusRate se le pasa el parametro bonus
     */
    public void setBonusRate(float bonusRate) {
        this.bonusRate = bonusRate;
    }

    /**
     * metodo para dar un credito a una cuenta, modificado para que se le sume un bonus al credito
     * @param amount cantidad del credito
     */
    @Override
    public void credit(float amount) {

        float amountRate = 0;
        float balancesumado = 0;

        Calendar time = Calendar.getInstance();
        String type = "deposito";

        Operacion op = new Operacion(time, amount, type);

        //comprobamos si la cantidad es menor que cero y de ser asi se establece la cantidad a 0
        if (amount < 0) {

            amount = 0;

        } else {

            amountRate = amount * bonusRate;
            balancesumado = getBalance() - (amount + amountRate);
            setBalance(balancesumado);

        }

        //añadimos la operacion al ArrayList
        getTheOperations().add(op);

    }

}

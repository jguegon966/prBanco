/**
 * <h2>Clase Persona</h2>
 * <p>Se usa para crear personas en el proyecto PrBanco, que servira a modo de titular en cada cuenta</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 22-04-2022
 */
package Persona;

import java.io.Serializable;

/**
 * Clase Persona que nos serviran a modo de titular de las cuentas
 */
public class Persona implements Serializable {

    /**
     * atributo nombre de tipo String
     */
    private String nombre;
    /**
     * atributo edad de tipo int
     */
    private int edad;
    /**
     * atributo DNI de tipo String
     */
    private String DNI;
    /**
     * atributo sexo de tipo caracter
     */
    private char sexo;

    /**
     * Constructor vacio de persona al que se le pasará el DNI a traves de los metodos generaDNI y generarLetra
     */
    public Persona (){
        this.DNI = generaDNI()+generarLetra();
    }

    /**
     * Constructor al que le pasamos por parametros el nombre de la persona
     * @param nombre nombre de la persona
     */
    public Persona(String nombre){

        this.nombre = nombre;
        this.edad = 0;
        this.sexo = ' ';
        this.DNI = generaDNI()+generarLetra();

    }

    /**
     * Constructor al que le pasamos por parametros el nombre, la edad y el sexo de la persona
     * @param nombre nombre de la persona
     * @param edad edad que tiene dicha persona
     * @param sexo sexo de la persona H para hombre y M para mujer
     */
    public Persona(String nombre, int edad, char sexo){

        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.DNI = generaDNI()+generarLetra();

    }

    /**
     * metodo getter de nombre para coger el nombre
     * @return devuelve el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * metodo setter de nombre que sirve pare establecer el nombre de la persona
     * @param nombre establece el nombre de la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * metodo getter de edad que sirve para coger la edad de la persona
     * @return devuelve la edad de la persona
     */
    public int getEdad() {
        return edad;
    }

    /**
     * metodo setter de edad que sirve para establecer la edad de la persona
     * @param edad establece la edad de la persona
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * metodo getter de DNI que sirve para coger el DNI de la persona
     * @return devuelve el DNI de la persona
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * metodo setter de DNI que sirvve para establecer el DNI de la persona
     * @param DNI establece el DNI de la persona
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * metodo getter de sexo que sirve para coger el sexo de la persona
     * @return devuelve el sexo de la persona
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * metodo setter de sexo que sirve para establecer el sexo de la persona
     * @param sexo establece el sexo de la persona H para hombre M para mujer
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Metodo para generar el numero del DNI de forma aleatoria
     * @return devuelve el numero del DNI
     */
    private int generaDNI(){

        int numero_dni;

        //Aqui generaremos un numero entero aleatorio de 8 cifras, puesto que será el numero de nuestro DNI
        numero_dni = (int)Math.floor(Math.random()*(10000000-99999999)+99999999);

        return numero_dni;

    }

    /**
     * metododo para generar la letra del DNI de forma aleatiria
     * @return devuelve la letra del DNI generada aleatoriamente, y en el constructor se juntan la letra y el numero
     */
    private String generarLetra(){

        int aux;
        char letra_dni = 0;
        String letra = "TRWAGMYFPDXBNJZSQVHLCKE";

        String DNI_letra = " ";

        //igualamos nuestro temporal al resto de la division de nuestro numero aleatorio con 23 que son las letras que hemos introducido previamente
        aux = generaDNI() % 23;

        //En este for cogeremos la letra correspondiente a traves del indice auxiliar que hemos generado con ese resto
        for(int i=0; i<aux; i++){

            letra_dni = letra.charAt(i);

        }

        DNI_letra = String.valueOf(letra_dni);
        return DNI_letra;
    }

    /**
     * Metodo toString para mostrar los datos de la persona creada
     * @return cadena de caracteres que muestra a la persona, con su nombre, DNI, edad y sexo
     */
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", DNI='" + DNI + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}

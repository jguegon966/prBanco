/**
 * <h2>Clase AppBanco</h2>
 * <p>Clase utilizada a modo de menu para seleccionar a traves de unn numero que queremos hacer en el banco, almacenar el fichero con las cuentas y crear un log con las operaciones</p>
 *
 * Busca informacion de javadoc en <a href="https://www.google.com/">GOOGLE</a>
 * @see <a href:"https://www.google.com/">Google</a>
 * @Version 1-2022
 * @author JGG
 * @since 03-05-2022
 */
package Banco;

import Cuenta.Cuenta;

import java.io.*;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

import Cuenta.CuentaProtegida;
import Interfaz.CuentaBanco;
import Operaciones.Operacion;
import Persona.Persona;
import Banco.Banco;
import Cuenta.CuentaBonus;
import Cuenta.CuentaStandard;

/**
 * Clase AppBanco que nos servira a modo de menu para seleccionar que queremos hacer en el banco
 */
public class AppBanco {
    Scanner sc = new Scanner(System.in);
    /**
     * Objeto de la clase banco que usaremos mas adelante en los metodos
     */
    Banco bank = new Banco();

    /**
     * atributo de tipo String que nos servirá para poner si una operacion es de tipo debito
     */
    String typedebi = "debito";

    /**
     * atributo de tipo String que nos servirá para poner si una operacion es de tipo deposito
     */
    String typedepo = "deposito";

    /**
     * Metodo numeroCuentas que nos servirá para generar el numero de cuentas aleatorio en formato "Number-XXX"
     * @return devuelve un String con el numero de la cuenta generado
     */
    public String numeroCuentas(){

        int numeroC;
        String numero_Cuenta = " ";

        //Aqui generaremos un numero entero aleatorio de 3 cifras, puesto que será el numero de la cuenta
        numeroC = (int)Math.floor(Math.random()*(100-999)+999);
        numero_Cuenta = "Number-"+numeroC;


        return numero_Cuenta;

    }

    /**
     * Metodo para crear una cuenta protegida el cual le pediremos los valores del propietario de la cuenta por teclado y nos generaá un numero de cuenta aleatorio llamando al metodo numeroCuentas, ademas de añadirle por tecldo el minimo de dinero para sacar de la cuenta
     * y dicha cuenta se añadira a un HasMap de tipo Banco el cual almacenará todas las cuentas
     */
    public void crearCProtegida(){

        String numero;
        numero = numeroCuentas();

        CuentaProtegida cp = new CuentaProtegida();
        Persona p = new Persona();
        sc.skip("\n");
        System.out.println("Introduzca el nombre del titular: ");
        p.setNombre(sc.nextLine());
        sc.skip("\n");
        System.out.println("Introduzca la edad del titular: ");
        p.setEdad(sc.nextInt());
        System.out.println("Introduzca el sexo de la persona H/M: ");
        p.setSexo(sc.next().charAt(0));
        cp.setOwner(p);
        cp.setNumber(numero);
        System.out.println("Introduzca el balance de la cuenta: ");
        cp.setBalance(sc.nextInt());
        System.out.println("Introduzca el limite minimo a sacar de la cuenta: ");
        cp.setLowerBound(sc.nextFloat());

        if (bank.listaCuentas.containsKey(numero)) {
            System.out.println("No se puede introducir el producto. El código esta repetido.");
        } else {
            bank.listaCuentas.put(numero, cp);
        }

    }

    /**
     * Metodo para crear una cuenta Bonus a la cual le pediremos los valores del propietario de la cuenta por teclado y nos generaá un numero de cuenta aleatorio llamando al metodo numeroCuentas, ademas de introducirle por teclado el bonus
     * y dicha cuenta se añadira a un HasMap de tipo Banco el cual almacenará todas las cuentas
     */
    public void crearCBonus(){

        CuentaBonus cb = new CuentaBonus();
        Persona p = new Persona();
        sc.skip("\n");
        System.out.println("Introduzca el nombre del titular: ");
        p.setNombre(sc.nextLine());
        sc.skip("\n");
        System.out.println("Introduzca la edad del titular: ");
        p.setEdad(sc.nextInt());
        System.out.println("Introduzca el sexo de la persona H/M: ");
        p.setSexo(sc.next().charAt(0));
        cb.setOwner(p);
        cb.setNumber(numeroCuentas());
        System.out.println("Introduzca el balance de la cuenta: ");
        cb.setBalance(sc.nextInt());
        System.out.println("Introduzca el bonus de la cuenta: ");
        cb.setBonusRate(sc.nextFloat());

        if (bank.listaCuentas.containsKey(cb.getNumber())) {
            System.out.println("No se puede introducir el producto. El código esta repetido.");
        } else {
            bank.listaCuentas.put(cb.getNumber(), cb);
        }

    }

    /**
     * Metodo para crear una cuenta Standard a la cual le pediremos los valores del propietario de la cuenta por teclado y nos generaá un numero de cuenta aleatorio llamando al metodo numeroCuentas,
     * y dicha cuenta se añadira a un HasMap de tipo Banco el cual almacenará todas las cuentas
     */
    public void crearCStandard(){

        CuentaStandard cs = new CuentaStandard();
        Persona p = new Persona();
        sc.skip("\n");
        System.out.println("Introduzca el nombre del titular: ");
        p.setNombre(sc.nextLine());
        sc.skip("\n");
        System.out.println("Introduzca la edad del titular: ");
        p.setEdad(sc.nextInt());
        System.out.println("Introduzca el sexo de la persona H/M: ");
        p.setSexo(sc.next().charAt(0));
        cs.setOwner(p);
        cs.setNumber(numeroCuentas());
        System.out.println("Introduzca el balance de la cuenta: ");
        cs.setBalance(sc.nextInt());

        if (bank.listaCuentas.containsKey(cs.getNumber())) {
            System.out.println("No se puede introducir el producto. El código esta repetido.");
        } else {
            bank.listaCuentas.put(cs.getNumber(), cs);
        }

    }

    /**
     * Metodo para ingresar dinero en una cuenta que pasaremos por teclado, priimero nos muestra las cuentas que hay y nos preguntara a cual deseamos ingresar el dinero, y la cantidad
     * Tambien llama al crearLog para crearnos un archivo txt con la operacion
     */
    public void ingresarDinero(){

        //He modificado la forma de mostrar las cuentas para que solo aparezca el numero de la cuenta
        sc.skip("\n");
        String clave;
        Iterator<String> cuentas = bank.listaCuentas.keySet().iterator();
        System.out.println("Hay las siguientes cuentas:");
        while(cuentas.hasNext()){
            clave = cuentas.next();
            System.out.println(clave);
        }

        try{
            System.out.println("A que cuenta desea ingresar dinero ?");
            String numeroC = sc.nextLine();
            System.out.println("Cuanto dinero desea ingresar ?");
            float cantidad = sc.nextFloat();
            bank.depositAccount(numeroC, cantidad);
            Operacion op = new Operacion(Calendar.getInstance(),cantidad, typedepo, numeroC);
            crearLog(op, typedepo);
        } catch(Exception e){
            System.err.println("El numero de cuenta no existente");
        }

    }

    /**
     * Metodo para sacar dinero de una cuenta, primero nos mostrará las cuentas que hay y nos preguntará a cual deseamos retirarle el dinero y la cantidad a retirar.
     * Tambien llama al crearLog para crearnos un archivo txt con la operacion
     */
    public void sacarDinero(){

        sc.skip("\n");
        String clave;
        Iterator<String> cuentas = bank.listaCuentas.keySet().iterator();
        System.out.println("Hay las siguientes cuentas:");
        while(cuentas.hasNext()){
            clave = cuentas.next();
            System.out.println(clave);
        }

        try{
            System.out.println("A que cuenta desea retirar dinero ?");
            String numeroC = sc.nextLine();
            System.out.println("Cuanto dinero desea retirar ?");
            float cantidad = sc.nextFloat();
            bank.withdraw(numeroC, cantidad);
            Operacion op = new Operacion(Calendar.getInstance(),cantidad, typedebi, numeroC);
            crearLog(op, typedebi);
        }catch(Exception e) {
            System.err.println("El numero de cuenta no existe");
        }

    }

    /**
     * Metodo para conceder un credito a una cuenta, primero nos muestra las cuentas que hay en el banco y nos pide que introduzcamos la centa a la que deseamos conceder el credito, y la cantidad del credito
     * Tambien llama al crearLog para crearnos un archivo txt con la operacion
     */
    public void acreditarCuenta(){

        sc.skip("\n");
        String clave;
        Iterator<String> cuentas = bank.listaCuentas.keySet().iterator();
        System.out.println("Hay las siguientes cuentas:");
        while(cuentas.hasNext()){
            clave = cuentas.next();
            System.out.println(clave);
        }


        try{
            System.out.println("A que cuenta desea dar el credito?");
            String numeroC = sc.nextLine();
            System.out.println("De cuanto dinero desea dar el credito ?");
            float cantidad = sc.nextFloat();
            bank.creditAccount(numeroC, cantidad);
            Operacion op = new Operacion(Calendar.getInstance(),cantidad, typedebi, numeroC);
            crearLog(op, typedebi);
        }catch(Exception e) {
            System.err.println("El numero de cuenta no existe");
        }

    }

    /**
     * Metodo para transferir dinero de una cuenta a otra, nos mostrará las cuentas disponibles y nos pedira de cual queremos retirar el dinero y a cual ingresarselo,
     * ademas de pedirnos que introduzcamos la cantidad a transferir
     * Tambien llama al crearLog para crearnos un archivo txt con la operacion
     */
    public void transferirDinero(){

        sc.skip("\n");
        String clave;
        Iterator<String> cuentas = bank.listaCuentas.keySet().iterator();
        System.out.println("Hay las siguientes cuentas:");
        while(cuentas.hasNext()){
            clave = cuentas.next();
            System.out.println(clave);
        }

        try{
            System.out.println("Introduzca la cuenta de la que desea retirar el dinero: ");
            String from = sc.nextLine();
            System.out.println("Introduzca la cuenta a la que desea ingresar el dinero: ");
            String to = sc.nextLine();
            System.out.println("Introduzca la cantidad que desea transferir: ");
            float cantidad = sc.nextFloat();

            bank.transfer(from, to, cantidad);
            Operacion op = new Operacion(Calendar.getInstance(),cantidad, typedebi, from);
            crearLog(op, typedebi);
            op = new Operacion(Calendar.getInstance(),cantidad, typedepo, to);
            crearLog(op, typedepo);
        }catch(Exception e) {
            System.err.println("Uno de los numeros de cuenta no existen");
        }

    }

    /**
     * Metodo para listar todas las cuentas del banco
     */
    public void listarCuentasBanco(){

        sc.skip("\n");
        String clave;
        Iterator<String> cuentas = bank.listaCuentas.keySet().iterator();
        System.out.println("Hay las siguientes cuentas:");
        while(cuentas.hasNext()){
            clave = cuentas.next();
            System.out.println(clave + ";\n\t" + bank.listaCuentas.get(clave));
        }

    }

    /**
     * Metodo para mostrar la informacion de una cuenta, nos apreceran los numeros de cuentas y nos pedirá cual de esas deseamos mostrar mas informacion
     */
    public void mostrarCuenta(){

        sc.skip("\n");
        for (Map.Entry<String, CuentaBanco>cuentas:bank.listaCuentas.entrySet()){

            System.out.println(cuentas.getKey());

        }

        try {
            System.out.println("Introduzca el numero de la cuenta a mostrar");
            System.out.println(bank.displayAccount(sc.nextLine()));
        }catch (Exception e){
            System.err.println("El numero de cuenta no existe");
        }

    }

    /**
     * Metodo para mostrar el balance de una cuenta, nos mostrara los numeros de cuenta del banco y nos pedirá que introduzcamos de cual deseamos ver su balance
     */
    public void mostrarBalance(){

        sc.skip("\n");
        for (Map.Entry<String, CuentaBanco>cuentas:bank.listaCuentas.entrySet()){

            System.out.println(cuentas.getKey());

        }

        try{
            System.out.println("Introduzca el numero de la cuenta a mostrar");
            String numeroCuenta = sc.nextLine();

            System.out.println(bank.displayBalance(numeroCuenta));
        }catch (Exception e){
            System.err.println("El numero de cuenta no existe");
        }

    }

    /**
     * Metodo que nos muestra los numeros de cuentas y nos pide que introduzcamos que cuenta queremos mostrar sus operaciones y nos pide tambien a su vez la fecha en formato dd/MM/yyyy
     * para mostrarnos las oepraciones desde esa fecha.
     */
    public void listarOperacionesFecha() {

        sc.skip("\n");
        for (Map.Entry<String, CuentaBanco>cuentas:bank.listaCuentas.entrySet()){

            System.out.println(cuentas.getKey());

        }

        try{
            System.out.println("Introduzca el numero de la cuenta a mostrar");
            String numeroCuenta = sc.nextLine();
            System.out.println("Introduzca la fecha desde la quue se desea mostrar las operaciones: ");
            System.out.println("Con formato dd/mm/yyyy");

            String fecha = sc.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date testDate = null;
            String date = fecha;
            Calendar cal = null;
            try {
                testDate = sdf.parse(date);
                System.out.println("Ahora hemos creado un objeto date con la fecha indicada, " + testDate);
            } catch (Exception e) {
                System.err.println("Formato no valido!!");
            }if (!sdf.format(testDate).equals(date)) {
                System.err.println("Fecha no valida!!");
            } else {
                System.out.println("Fecha valida");
                cal = Calendar.getInstance();
                cal.setTime(testDate);
            }

            Operacion op = new Operacion();
            op.setNumeroCuenta(numeroCuenta);
            System.out.println(bank.listOperationsAccount(numeroCuenta, cal));
        }catch (Exception e){
            System.err.println("El numero de cuenta no existe");
        }

    }

    /**
     * Metodo para modificar los datos ddel propietario de la cuenta, nos muestra las cuentas y nos pide de cual deseamos modificar sus datos,
     * nos aparecera un Switch para seleccionar que deseamos modificar en concreto y una vez cambiado le damos al boton exit
     */
    public void modificarTitular(){

        sc.skip("\n");
        for (Map.Entry<String, CuentaBanco>cuentas:bank.listaCuentas.entrySet()){

            System.out.println(cuentas.getKey());

        }

        try{

            System.out.println("De que que cuenta desea modificar su titular ?");
            String numeroCuenta = sc.nextLine();

            String opc = null;

            do{

                System.out.println("1- Cambiar nombre");
                System.out.println("2- Cambiar sexo");
                System.out.println("3- Cambiar edad");
                System.out.println("4- Salir");

                try{
                    opc = sc.next();
                }catch (Exception e){
                    System.out.println("El operador debe ser un numero");
                }

                switch(opc){

                    case "1":
                        System.out.println("Introduzca el nuevo nombre: ");
                        bank.listaCuentas.get(numeroCuenta).getOwner().setNombre(sc.nextLine());
                        break;
                    case "2":
                        System.out.println("Introduzca el nuevo sexo H/M: ");
                        bank.listaCuentas.get(numeroCuenta).getOwner().setSexo(sc.next().charAt(0));
                        break;
                    case "3":
                        System.out.println("Introduzca la nueva edad: ");
                        bank.listaCuentas.get(numeroCuenta).getOwner().setEdad(sc.nextInt());
                        break;
                    case "4":

                        System.out.println("Modificacion realizada con exito");
                        break;
                    default: System.out.println("Comando no valido");

                }

            }while(!"4".equals(opc));

        }catch (Exception e){
            System.err.println("El numero de cuenta no existe");
        }


    }

    /**
     * Metodo que sirve para borrar una cuenta, primero nos muestra los numeros de cuenta del banco y nos preguntará cual deseamos borrar
     */
    public void borrarCuenta(){

        sc.skip("\n");
        for (Map.Entry<String, CuentaBanco>cuentas:bank.listaCuentas.entrySet()){

            System.out.println(cuentas.getKey());

        }

        try{

            System.out.println("Introduzca el numero de la cuenta a mostrar");
            String numeroCuenta = sc.nextLine();

            CuentaBanco cuentaBorrar = bank.listaCuentas.get(numeroCuenta);

            bank.removeAccount(cuentaBorrar);

        }catch (Exception e){
            System.err.println("El numero de cuenta no existe");
        }


    }

    /**
     * Metodo para guardar las cuentas almacenadas en el hasmap listaCuentas que se le pasa al metodo por parametros y que nos guardará con el nombre que queramos .dat (el .dat se pone automatico)
     * @param listaCuentas parametro que se le pasa con el hasmap de las cuentas dell banco
     */
    public void guardarFichero(Map listaCuentas){

        sc.skip("\n");
        FileOutputStream fos = null;
        ObjectOutputStream ops = null;

        //escritura
        try{
            //se crea el fichero
            System.out.println("Dame el nombre del fichero que quieres guardar");
            String nombrefich = sc.nextLine();
            fos = new FileOutputStream(nombrefich+".dat");
            ops = new ObjectOutputStream(fos);

            ops.writeObject(bank);

        }catch (FileNotFoundException ex){
            System.out.println("1"+ex.getMessage());
        }catch (IOException ex){
            System.out.println("2"+ex.getMessage());
        }finally {
            try{
                System.out.println("Guardado con exito!!");
                if (fos != null){
                    fos.close();
                }
                if (ops != null){
                    ops.close();
                }
            }catch (IOException ex){
                System.out.println("3"+ex.getMessage());
            }
        }

    }

    /**
     * Metodo que sirve para cargar el fichero de tipo .dat donde esta la informacion del banco, nos pedirá el nombre del archivo y lo cargará para seguir trabajando con el
     */
    public void cargarFichero(){

        sc.skip("\n");
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try{
            System.out.println("Dame el nombre del archivo que deseas cargar: ");
            String nombrefich = sc.nextLine();
            fis = new FileInputStream(nombrefich+".dat");
            ois = new ObjectInputStream(fis);
            bank = (Banco) ois.readObject(); //Es necesario el casting, que son los parentesis del principio para indicar el objeto
        }catch (FileNotFoundException ex){
            System.err.println("El archivo que desea cargar no existe");
        }catch (ClassNotFoundException | IOException ex){
            ex.printStackTrace();
        }finally {
            try{
                System.out.println("Cargado con exito!!");
                if (fis != null){
                    fis.close();
                }
                if (ois != null){
                    ois.close();
                }
            }catch (IOException ex){
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     * metodo menu, el cual nos mostrará por pantalla una serie de funciones que puede hacer el banco y nosotros a traves del numero que introduzcamos por teclado
     * podremos hacer todas esas diversas funciones
     */
    public void menu(){
        Scanner sc = new Scanner(System.in);

        String op;

        do{

            System.out.println("1- Crear Cuenta Protegida");
            System.out.println("2- Crear Cuenta Bonus");
            System.out.println("3- Crear Cuenta Standard");
            System.out.println("4- Ingresar dinero en cuenta");
            System.out.println("5- Sacar dinero de Cuenta");
            System.out.println("6- Conceder credito a cuenta");
            System.out.println("7- Hacer transferencia entre cuentas");
            System.out.println("8- Listar cuentas del Banco");
            System.out.println("9- Mostrar cuenta");
            System.out.println("10- Mostrar Balance de la cuenta");
            System.out.println("11- Listar operaciones de cuenta desde una fecha");
            System.out.println("12- Modificar datos titular");
            System.out.println("13- Borrar Cuenta");
            System.out.println("14- Guardar banco en fichero");
            System.out.println("15- Cargar banco desde fichero");
            System.out.println("0- Salir");

            op = sc.next();

            switch(op){

                case "1":

                    crearCProtegida();
                    break;
                case "2":

                    crearCBonus();
                    break;
                case "3":

                    crearCStandard();
                    break;
                case "4":
                    ingresarDinero();
                    break;
                case "5":
                    sacarDinero();
                    break;
                case "6":
                    acreditarCuenta();
                    break;
                case "7":
                    transferirDinero();
                    break;
                case "8":
                    listarCuentasBanco();
                    break;
                case "9":
                    mostrarCuenta();
                    break;
                case "10":
                    mostrarBalance();
                    break;
                case "11":
                    listarOperacionesFecha();
                    break;
                case "12":
                    modificarTitular();
                    break;
                case "13":
                    borrarCuenta();
                    break;
                case "14":
                    guardarFichero(bank.listaCuentas);
                    break;
                case "15":
                    cargarFichero();
                    break;
                case "0":

                    System.out.println("Fin de programa");

                    break;
                default: System.out.println("Comando no valido");

            }

        }while(!"0".equals(op));

    }

    //LOG o registro en formato txt de las operaciones

    /**
     * Metodo para crear un registro de las operaciones que irá realizando la cuenta que indiquemos
     * Este a su vez crea un .txt con la hora, los minutos y los segundos en que se ha realizado la operacion y la almacena en los archivos internos del programa
     * @param ope se le pasa el  objeto ope que es de tipo operacion, con la informacion de la operacion realizada
     * @param type se le pasa el parametro type que indica el tipo de operacion que hemos realizado, debito o credito
     */
    public void crearLog(Operacion ope, String type){

        Scanner sc = new Scanner(System.in);

        int hora;
        int minutos;
        int segundos;

        Calendar calendario = Calendar.getInstance();
        hora =calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        FileWriter file = null;
        PrintWriter pw = null;

        try {

            file = new FileWriter("CUENTA_"+ope.getNumeroCuenta()+"_HORA_"+hora+"_"+minutos+"_"+segundos+"_TIPO_"+type+".txt");

             pw = new PrintWriter(file);
             pw.print(ope);

        }catch(Exception e){
            System.err.println(e.getMessage()+"\n"+e.getCause());
        }finally{
            try{
                pw.close();
                file.close();
            }catch(Exception e){
                System.err.println(e.getMessage()+"\n"+e.getCause());
            }
        }

    }

}

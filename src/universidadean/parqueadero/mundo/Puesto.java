/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto: Parqueadero
 * Adaptado del Proyecto CUPI2 - Uniandes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.parqueadero.mundo;

/**
 * Esta clase representa un puesto en el parqueadero.
 */
public class Puesto {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Eventual carro en el puesto. Si no hay ninguno, carro == null.
     */
    private Carro carro;

    /**
     * Número del puesto dentro del parqueadero.
     */
    private int numeroPuesto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un puesto vacío. <br>
     * <b>post: </b> Se creó un puesto vacío.
     *
     * @param pPuesto Número de puesto.
     */
    public Puesto(int pPuesto) {
        carro = null;
        numeroPuesto = pPuesto;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el carro del puesto. Si no hay ningún carro retorna null.
     *
     * @return El carro que ocupa el puesto.
     */
    public Carro darCarro() {
        return carro;
    }

    /**
     * Indica si el puesto está ocupado.
     *
     * @return Retorna true si el puesto está ocupado. Retorna false en caso contrario.
     */
    public boolean estaOcupado() {
        boolean ocupado = carro != null;
        return ocupado;
    }

    /**
     * Parquea un carro en el puesto. <br>
     * <b>pre: </b> El puesto está vacío. <br>
     * <b>post: </b> El puesto ahora está ocupado por el carro pCarro.
     *
     * @param pCarro Carro que se está parqueando. pCarro != null.
     */
    public void parquearCarro(Carro pCarro) {
        carro = pCarro;
    }

    /**
     * Saca el carro del puesto. <br>
     * <b>post: </b> El puesto está vacío.
     */
    public void sacarCarro() {
        carro = null;
    }

    /**
     * Retorna el número del puesto.
     *
     * @return El número asignado al puesto.
     */
    public int darNumeroPuesto() {
        return numeroPuesto;
    }

    /**
     * Indica si el carro tiene la placa recibida.
     *
     * @param pPlaca Placa del carro que ocupa el puesto.
     * @return Retorna true si tiene la placa, false en caso contrario.
     */
    public boolean tieneCarroConPlaca(String pPlaca) {
        boolean tieneCarro = true;

        if (carro == null) {
            tieneCarro = false;
        }
        else if (carro.tienePlaca(pPlaca)) {
            tieneCarro = true;
        }
        else {
            tieneCarro = false;
        }

        return tieneCarro;
    }

}
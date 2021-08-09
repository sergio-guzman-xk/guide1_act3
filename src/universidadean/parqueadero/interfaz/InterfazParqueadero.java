/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto: Parqueadero
 * Adaptado del Proyecto CUPI2 - Uniandes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.parqueadero.interfaz;

import universidadean.parqueadero.mundo.Parqueadero;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Esta clase representa la ventana de interacci�n del parqueadero.
 */
@SuppressWarnings("serial")
public class InterfazParqueadero extends JFrame {

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el parqueadero que se est� administrando.
     */
    private Parqueadero parqueadero;

    // -----------------------------------------------------------------
    // Componentes de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel que muestra el banner de la aplicaci�n.
     */
    private PanelImagen panelImagen;

    /**
     * Es el panel que muestra el parqueadero.
     */
    private PanelParqueadero panelParqueadero;

    /**
     * Es el panel donde se realizan las operaciones.
     */
    private PanelOperaciones panelOperaciones;

    /**
     * Es el panel que muestra informaci�n sobre el parqueadero.
     */
    private PanelInformacion panelInformacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Sirve para construir la interfaz.
     */
    public InterfazParqueadero() {
        setTitle("Parqueadero");
        setSize(570, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crea el parqueadero con tarifa por hora de 1200
        parqueadero = new Parqueadero();

        // Construir los paneles
        panelImagen = new PanelImagen();
        panelParqueadero = new PanelParqueadero(parqueadero);
        panelOperaciones = new PanelOperaciones(this);
        panelOperaciones.setPreferredSize(new Dimension(570, 100));
        panelInformacion = new PanelInformacion(this);
        JPanel sur = new JPanel();
        sur.setLayout(new BorderLayout());

        // Organizar el panel principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelImagen, BorderLayout.NORTH);
        getContentPane().add(panelParqueadero, BorderLayout.CENTER);
        getContentPane().add(sur, BorderLayout.SOUTH);
        sur.add(panelOperaciones, BorderLayout.CENTER);
        sur.add(panelInformacion, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setResizable(false);

        actualizar();
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Sirve para hacer avanzar una hora el reloj del parqueadero.
     */
    public void avanzar() {
        parqueadero.avanzarHora();
        actualizar();
    }

    /**
     * Este m�todo sirve para ingresar un carro al parqueadero. Debe preguntar la placa del carro e informar la posici�n donde debe estacionarse. <br>
     * Si no se puede parquear, porque el parqueadero est� cerrado o porque no hay ning�n lugar disponible, entonces debe informar del error.
     */
    public void ingresar() {
        String placa = JOptionPane.showInputDialog(this, "Por favor digite la placa del carro que est� ingresando", "Ingresar carro", JOptionPane.QUESTION_MESSAGE);
        if (placa != null) {
            int puesto = parqueadero.entrarCarro(placa);
            switch (puesto) {
                case Parqueadero.NO_HAY_PUESTO:
                    JOptionPane.showMessageDialog(this, "Lo sentimos: el parqueadero est� lleno");
                    break;
                case Parqueadero.CARRO_YA_EXISTE:
                    JOptionPane.showMessageDialog(this, "Lo sentimos: ya hay un carro parqueado con la misma placa");
                    break;
                case Parqueadero.PARQUEADERO_CERRADO:
                    JOptionPane.showMessageDialog(this, "Lo sentimos: el parqueadero est� cerrado");
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Bienvenido:\n Su carro qued� parqueado en el puesto: " + (puesto + 1));
                    break;
            }
            actualizar();
        }

    }

    /**
     * Este m�todo permite cambiar la tarifa del parqueadero. Pide una tarifa al usuario, si esta tarifa no es v�lida informa al usuario.
     */
    public void cambiar() {
        String tarifa = JOptionPane.showInputDialog(this, "Por favor digite la nueva tarifa", "Nueva tarifa", JOptionPane.QUESTION_MESSAGE);
        if (tarifa != null) {
            try {
                int tarifaNumero = Integer.parseInt(tarifa);
                if (tarifaNumero <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingrese una tarifa v�lida", "Tarifa inv�lida", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    parqueadero.cambiarTarifa(tarifaNumero);
                    panelOperaciones.cambiarTarifa(tarifaNumero);
                    JOptionPane.showMessageDialog(this, "Se ha cambiado la tarifa", "Nueva tarifa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ingrese una tarifa v�lida", "Tarifa inv�lida", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * Este m�todo sirve para sacar un carro del parqueadero. Debe pedir la placa y luego contactar al parqueadero para sacar el carro y saber <br>
     * cu�nto debe cancelar. Si la placa no corresponde a un carro que est� en el parqueadero entonces debe mostrar un error.
     */
    public void salir() {
        String placa = JOptionPane.showInputDialog(this, "Por favor digite la placa del carro que est� saliendo", "Salida carro", JOptionPane.QUESTION_MESSAGE);
        if (placa != null) {
            int valor = parqueadero.sacarCarro(placa);
            switch (valor) {
                case Parqueadero.PARQUEADERO_CERRADO:
                    JOptionPane.showMessageDialog(this, "Lo sentimos: el parqueadero est� cerrado", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case Parqueadero.CARRO_NO_EXISTE:
                    JOptionPane.showMessageDialog(this, "El carro de placa " + placa + " no est� en el parqueadero", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Placa: " + placa + " debe cancelar $ " + valor);
                    break;
            }
            actualizar();
        }
    }

    /**
     * Este m�todo se encarga de actualizar los datos que se presentan en la interfaz.
     */
    public void actualizar() {
        panelParqueadero.refrescarParqueadero();
        panelOperaciones.cambiarHora(parqueadero.darHoraActual());
        panelOperaciones.cambiarTarifa(parqueadero.darTarifa());
        panelInformacion.actualizarDatos(parqueadero.calcularPuestosLibres(), parqueadero.darMontoCaja());
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la opci�n 1.
     */
    public void reqFuncOpcion1() {
        String respuesta = parqueadero.metodo1();
        actualizar();
        JOptionPane.showMessageDialog(this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Este m�todo ejecuta la opci�n 2.
     */
    public void reqFuncOpcion2() {
        String respuesta = parqueadero.metodo2();
        actualizar();
        JOptionPane.showMessageDialog(this, respuesta, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicaci�n.
     *
     * @param pArgs Par�metros de la ejecuci�n. No son necesarios.
     */
    public static void main(String[] pArgs) {
        try {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            InterfazParqueadero manejador = new InterfazParqueadero();
            manejador.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
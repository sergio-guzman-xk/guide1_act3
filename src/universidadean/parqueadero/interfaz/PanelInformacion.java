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
package universidadean.parqueadero.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Este panel sirve para mostrar información sobre el estado del parqueadero.
 */
@SuppressWarnings("serial")
public class PanelInformacion extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando extensión 1.
     */
    private final static String OPCION_1 = "OPCION 1";

    /**
     * Comando extensión 2.
     */
    private final static String OPCION_2 = "OPCION 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la aplicación.
     */
    private InterfazParqueadero principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta para el valor de la caja.
     */
    private JLabel labValorCaja;

    /**
     * Etiqueta para el número de puestos vacíos.
     */
    private JLabel labVacios;

    /**
     * Es el botón para ejecutar la opción 1.
     */
    private JButton botonOpcion1;

    /**
     * Es el botón para ejecutar la opción 2.
     */
    private JButton botonOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel. <br>
     * <b>post: </b> Se construyó el panel.
     *
     * @param pPrincipal Instancia de la ventana principal.
     */
    public PanelInformacion(InterfazParqueadero pPrincipal) {
        principal = pPrincipal;
        inicializar();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Organiza el panel para mostrar el valor de la caja y el número de puestos vacíos.
     */
    private void inicializar() {
        setLayout(new GridLayout(1, 4));

        labValorCaja = new JLabel("Valor en Caja: ");
        labVacios = new JLabel("Puestos Vacíos: ");

        botonOpcion1 = new JButton();
        botonOpcion1.setText("Opción 1");
        botonOpcion1.setActionCommand(OPCION_1);
        botonOpcion1.addActionListener(this);

        botonOpcion2 = new JButton();
        botonOpcion2.setText("Opción 2");
        botonOpcion2.setActionCommand(OPCION_2);
        botonOpcion2.addActionListener(this);

        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 5, 0), new TitledBorder("Información")));

        add(labValorCaja);
        add(labVacios);
        add(botonOpcion1);
        add(botonOpcion2);
    }

    /**
     * Actualiza la información mostrada. <br>
     * <b>post: </b> Se actualizó la información mostrada.
     *
     * @param pVacios    Número de puestos vacíos.
     * @param pMontoCaja Cantidad de dinero en la caja.
     */
    public void actualizarDatos(int pVacios, int pMontoCaja) {
        labVacios.setText("Puestos Vacíos: " + pVacios);
        labValorCaja.setText("Valor en Caja: $ " + pMontoCaja);
    }

    /**
     * Este método ejecuta las acciones adecuadas según el botón que haya sido presionado.
     *
     * @param pEvento Es el evento del click sobre el botón.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String command = pEvento.getActionCommand();
        if (command.equals(OPCION_1)) {
            principal.reqFuncOpcion1();
        }
        else if (command.equals(OPCION_2)) {
            principal.reqFuncOpcion2();
        }
    }

}

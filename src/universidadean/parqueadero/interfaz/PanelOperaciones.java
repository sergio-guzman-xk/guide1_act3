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

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Este panel sirve para mostrar la hora actual y realizar las diferentes operaciones sobre el parqueadero.
 */
@SuppressWarnings("serial")
public class PanelOperaciones extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando ingresar.
     */
    private final static String INGRESAR = "INGRESAR";

    /**
     * Comando salir.
     */
    private final static String SALIR = "SALIR";

    /**
     * Comando avanzar.
     */
    private final static String AVANZAR = "AVANZAR";

    /**
     * Comando cambiar.
     */
    private final static String CAMBIAR = "CAMBIAR";

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
     * Es el campo de texto usado para mostrar la hora.
     */
    private JTextField txtHora;

    /**
     * Es el campo de texto usado para mostrar la tarifa.
     */
    private JTextField txtTarifa;

    /**
     * Es el botón para ingresar un carro al parqueadero.
     */
    private JButton botonEntrar;

    /**
     * Es el botón para sacar un carro del parqueadero.
     */
    private JButton botonSalir;

    /**
     * Es el botón para hacer avanzar una hora el reloj del parqueadero.
     */
    private JButton botonAvanzarHora;

    /**
     * Es el botón para cambiar la tarifa.
     */
    private JButton botonCambiar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel. <br>
     * <b>post: </b> Se construyó el panel.
     *
     * @param pPrincipal Es una referencia a la ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelOperaciones(InterfazParqueadero pPrincipal) {
        principal = pPrincipal;
        inicializar();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializar y organizar los componentes del panel.
     */
    private void inicializar() {
        setLayout(new GridLayout(3, 2, 3, 3));
        setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panelHora = new JPanel();
        panelHora.setLayout(new GridLayout(1, 2));
        add(panelHora);
        panelHora.add(new JLabel("Hora actual: "));

        txtHora = new JTextField();
        txtHora.setEditable(false);
        txtHora.setForeground(Color.BLUE);
        panelHora.add(txtHora);

        botonAvanzarHora = new JButton();
        botonAvanzarHora.setText("Avanzar");
        botonAvanzarHora.setActionCommand(AVANZAR);
        botonAvanzarHora.addActionListener(this);
        add(botonAvanzarHora);

        JPanel panelTarifa = new JPanel();
        panelTarifa.setLayout(new GridLayout(1, 2));
        add(panelTarifa);

        panelTarifa.add(new JLabel("Tarifa: "));
        txtTarifa = new JTextField();
        txtTarifa.setEditable(false);
        txtTarifa.setForeground(Color.BLUE);
        panelTarifa.add(txtTarifa);

        botonCambiar = new JButton();
        botonCambiar.setText("Cambiar");
        botonCambiar.setActionCommand(CAMBIAR);
        botonCambiar.addActionListener(this);
        add(botonCambiar);

        botonEntrar = new JButton();
        botonEntrar.setText("Ingresar Carro");
        botonEntrar.setActionCommand(INGRESAR);
        botonEntrar.addActionListener(this);
        add(botonEntrar);

        botonSalir = new JButton();
        botonSalir.setText("Sacar Carro");
        botonSalir.setActionCommand(SALIR);
        botonSalir.addActionListener(this);
        add(botonSalir);
    }

    /**
     * Cambia la hora presentada. <br>
     * <b>post: </b> Se está mostrando la nueva hora.
     *
     * @param pHora Nueva hora que debe mostrarse.
     */
    public void cambiarHora(int pHora) {
        txtHora.setText(pHora + ":00");
    }

    /**
     * Cambia la tarifa presentada. <br>
     * <b>post: </b> Se está mostrando la nueva tarifa.
     *
     * @param pTarifa Nueva tarifa que debe mostrarse.
     */
    public void cambiarTarifa(int pTarifa) {
        txtTarifa.setText("$" + pTarifa);
    }

    /**
     * Este método ejecuta las acciones adecuadas según el botón que haya sido presionado.
     *
     * @param pEvento Es el evento del click sobre el botón.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String command = pEvento.getActionCommand();
        if (command.equals(INGRESAR)) {
            principal.ingresar();
        }
        else if (command.equals(SALIR)) {
            principal.salir();
        }
        else if (command.equals(AVANZAR)) {
            principal.avanzar();
        }
        else if (command.equals(CAMBIAR)) {
            principal.cambiar();
        }
    }
}

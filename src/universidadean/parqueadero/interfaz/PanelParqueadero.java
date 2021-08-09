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

import universidadean.parqueadero.mundo.Parqueadero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Este panel sirve para mostrar el estado actual del parqueadero.
 */
@SuppressWarnings("serial")
public class PanelParqueadero extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Indicadores puesto parqueo.
     */
    private JButton puestos[];

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia al parqueadero que se está mostrando.
     */
    private Parqueadero parqueadero;

    /**
     * Indicadores de ocupación.
     */
    private boolean ocupado[];

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel. <br>
     * <b>post: </b> Se construyó el panel.
     *
     * @param pParqueadero Es una referencia al parqueadero que se va a mostrar.
     */
    public PanelParqueadero(Parqueadero pParqueadero) {
        parqueadero = pParqueadero;
        inicializar();
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Organiza los componentes que se usan para representar el parqueadero.
     */
    private void inicializar() {
        setLayout(new GridLayout(4, 10));
        setBorder(new CompoundBorder(new EmptyBorder(0, 0, 5, 0), new TitledBorder("Parqueadero")));
        setPreferredSize(new Dimension(10, 170));
        puestos = new JButton[parqueadero.calcularPuestosLibres()];
        ocupado = new boolean[parqueadero.calcularPuestosLibres()];
        for (int i = 0; i < parqueadero.calcularPuestosLibres(); i++) {
            ocupado[i] = false;
            puestos[i] = new JButton();
            puestos[i].setActionCommand(i + "");
            puestos[i].addActionListener(this);
            puestos[i].setHorizontalTextPosition(JButton.CENTER);
            puestos[i].setVerticalTextPosition(JButton.CENTER);
            puestos[i].setForeground(Color.WHITE);
            add(puestos[i]);
        }
    }

    /**
     * Este método se encarga de actualizar la representación del parqueadero. <br>
     * <b>post: </b> Se actualizó la información mostrada del parqueadero.
     */
    public void refrescarParqueadero() {
        for (int i = 0; i < puestos.length; i++) {
            if (parqueadero.estaOcupado(i)) {
                if (!ocupado[i]) {
                    int indice = (int) (Math.floor(Math.random() * 4)) + 1;
                    puestos[i].setIcon(new ImageIcon("data/carro" + indice + ".png"));
                    ocupado[i] = true;
                }
                puestos[i].setText("");
            }
            else {
                puestos[i].setIcon(new ImageIcon("data/vacio.png"));
                puestos[i].setText((i + 1) + "");
                ocupado[i] = false;
            }
        }
    }

    /**
     * Este método se encarga de manejar los eventos de la clase.
     *
     * @param pEvento Evento accionado.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String numero = pEvento.getActionCommand();
        int indice = Integer.parseInt(numero);
        String placa = parqueadero.darPlacaCarro(indice);
        JOptionPane.showMessageDialog(this, placa, "Placa", JOptionPane.INFORMATION_MESSAGE);
    }
}

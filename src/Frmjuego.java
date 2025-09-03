import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
// hasta el domingo 
public class Frmjuego extends JFrame {

    private JTabbedPane tpJugadores;

    private Jugador jugador1, jugador2;
    private JPanel pnlJugador1, pnlJugador2;

    public Frmjuego() {
        setSize(800, 400);
        setTitle("Apuntado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);       
  
        // Agregar botones de comando
        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir); 
        btnRepartir.setBackground(Color.black);
        btnRepartir.setForeground(Color.white);      

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(getBounds().width - 130, 10, 100, 25);
        btnVerificar.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(btnVerificar);

        pnlJugador1 = new JPanel();
        pnlJugador1.setLayout(null);
        pnlJugador1.setBackground(new Color(120, 250, 0));

        pnlJugador2 = new JPanel();
        pnlJugador2.setLayout(null);
        pnlJugador2.setBackground(new Color(150, 20, 180));

        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("Martin Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);
        tpJugadores.setBackground(Color.white);

        tpJugadores.setBounds(10, 40, 750, 300);
        getContentPane().add(tpJugadores);

        // crear los eventos
        btnRepartir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repartir();
            }

        });

        btnVerificar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                verificar();
            }

        });

        // instanciar los jugadores
        jugador1 = new Jugador();
        jugador2 = new Jugador();
    }

    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void verificar() {
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                JOptionPane.showMessageDialog(null, jugador1.getGrupos());
                break;
            case 1:
                JOptionPane.showMessageDialog(null, jugador2.getGrupos());
                break;
        }
    }

}

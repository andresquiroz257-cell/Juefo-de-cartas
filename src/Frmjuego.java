import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
 
public class Frmjuego extends JFrame {

    private JTabbedPane tpJugadores;

    private Jugador jugador1, jugador2;
    private JPanel pnlJugador1, pnlJugador2;
    private JPanel pnlCards1, pnlCards2;      
    private JTextArea txtRes1, txtRes2;  
    private JScrollPane spRes1, spRes2;
    private JLabel lblPuntos1, lblPuntos2; 
    private JLabel lblResultadoGeneral;

    public Frmjuego() {
        setSize(820, 525);
        setTitle("Apuntado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);       
  
        // Agregar botones de comando
        JButton btnRepartir = new JButton("REPARTIR");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir); 
        btnRepartir.setBackground(Color.black);
        btnRepartir.setForeground(Color.white);      

        JButton btnVerificar = new JButton("VERIFICAR");
        btnVerificar.setBounds(230, 10, 100, 25);
        btnVerificar.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(btnVerificar);

        JButton btnPuntaje = new JButton("PUNTAJE");
        btnPuntaje.setBounds(getBounds().width - 130, 10, 100, 25);
        btnPuntaje.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(btnPuntaje);

        lblResultadoGeneral = new JLabel("", SwingConstants.CENTER);
        lblResultadoGeneral.setBounds(315, 10, 420, 25);
        lblResultadoGeneral.setFont(new Font("SansSerif", Font.BOLD, 14));
        getContentPane().add(lblResultadoGeneral);

        pnlJugador1 = new JPanel();
        pnlJugador1.setLayout(null);
        pnlJugador1.setBounds(0, 0, 800, 600);
        pnlJugador1.setBackground(new Color(4, 133, 14));

        pnlJugador2 = new JPanel();
        pnlJugador2.setLayout(null);
        pnlJugador2.setBounds(0, 0, 800, 600);
        pnlJugador2.setBackground(new Color(134, 2, 171));

        pnlCards1 = new JPanel(null);
        pnlCards1.setBounds(10, 10, 730, 300); 
        pnlCards1.setOpaque(false); 
        pnlJugador1.add(pnlCards1);

        pnlCards2 = new JPanel(null);
        pnlCards2.setBounds(10, 10, 730, 300);
        pnlCards2.setOpaque(false);
        pnlJugador2.add(pnlCards2);

        lblPuntos1 = new JLabel("", SwingConstants.CENTER);
        lblPuntos1.setBounds(10, 200, 730, 30);
        lblPuntos1.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPuntos1.setForeground(Color.BLACK);
        pnlJugador1.add(lblPuntos1);

        lblPuntos2 = new JLabel("", SwingConstants.CENTER);
        lblPuntos2.setBounds(10, 200, 730, 30);
        lblPuntos2.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPuntos2.setForeground(Color.BLACK);
        pnlJugador2.add(lblPuntos2);

        // para mostrar los textos incrustados y no en mensajes emeregentes
        txtRes1 = new JTextArea ();
        
        txtRes1.setEditable(false);
        txtRes1.setLineWrap(true);
        txtRes1.setWrapStyleWord(true);
         txtRes1.setFont(new Font("SansSerif", Font.BOLD, 12)); 
        txtRes1.setForeground(Color.BLACK);
        txtRes1.setBackground(new Color(12, 194, 29));
        spRes1 = new JScrollPane(txtRes1);
        spRes1.setBounds(10, 235, 400, 160);
        spRes1.setBorder(BorderFactory.createTitledBorder("Resultados"));
        pnlJugador1.add(spRes1);

        txtRes2 = new JTextArea();
        txtRes2.setEditable(false);
        txtRes2.setLineWrap(true);
        txtRes2.setWrapStyleWord(true);
        txtRes2.setFont(new Font("SansSerif", Font.BOLD, 12));
        txtRes2.setForeground(Color.white);
        txtRes2.setBackground(new Color(165, 24, 199));
        spRes2 = new JScrollPane(txtRes2);
        spRes2.setBounds(10, 235, 400, 160);
        spRes2.setBorder(BorderFactory.createTitledBorder("Resultados"));
        pnlJugador2.add(spRes2);

        tpJugadores = new JTabbedPane();
        tpJugadores.addTab("Martin Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Raúl Vidal", pnlJugador2);
        tpJugadores.setBackground(Color.white);

        tpJugadores.setBounds(10, 40, 750, 440);
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

        btnPuntaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPuntaje();
            }
        });

        // instanciar los jugadores
        jugador1 = new Jugador();
        jugador2 = new Jugador();

        limpiarResultados();
    }

    private void repartir() {
        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlCards1);
        jugador2.mostrar(pnlCards2);

        txtRes1.setText(" ");
        txtRes2.setText(" ");
        lblResultadoGeneral.setText(" ");
        
        spRes1.setBorder(BorderFactory.createTitledBorder("Resultados"));
        spRes2.setBorder(BorderFactory.createTitledBorder("Resultados"));

        limpiarResultados();
        pnlCards1.revalidate();
        pnlCards1.repaint();
        pnlCards2.revalidate();
        pnlCards2.repaint();
    }

    private void verificar() {
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                String texto1 = jugador1.getGrupos() + "\n" + jugador1.getEscaleras();
                    txtRes1.setText(texto1);
                    lblPuntos1.setText("");
                    spRes1.setBorder(BorderFactory.createTitledBorder("Resultados - Verificar"));
            break;

            case 1:
                String texto2 = jugador2.getGrupos() + "\n" + jugador2.getEscaleras();
                    txtRes2.setText(texto2);
                    lblPuntos2.setText("");
                    spRes2.setBorder(BorderFactory.createTitledBorder("Resultados - Verificar"));
            break;
        }
    }

    private void mostrarPuntaje() {
        final int MIN_RUN = 2;  
        int p1 = jugador1.getPuntaje();
        int p2 = jugador2.getPuntaje();

        String sobrantes1 = jugador1.getCartasSobran(MIN_RUN);
        String sobrantes2 = jugador2.getCartasSobran(MIN_RUN);

        lblPuntos1.setText("Puntos: " + p1);
        txtRes1.setText(sobrantes1);
        spRes1.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), " Resultados (Puntos: " + p1 + ")",
                TitledBorder.LEFT, TitledBorder.TOP));

        lblPuntos2.setText("Puntos: " + p2);
        txtRes2.setText(sobrantes2);
        spRes2.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), " Resultados (Puntos: " + p2 + ")",
                TitledBorder.LEFT, TitledBorder.TOP));

        String ganador;
        if (p1 < p2) {
            ganador = "GANO MARTIN ESTRADA CON " + p1 + " DE PUNTAJE";
        } else if (p2 < p1) {
            ganador = "GANO RAUL VIDAL CON  " + p2 + " DE PUNTAJE";
        } else {
            ganador = "EMPATE DE " + p1 + " PUNTOS";
        }

        lblResultadoGeneral.setText(ganador);

        // repintar
        spRes1.revalidate();
        spRes1.repaint();
        spRes2.revalidate();
        spRes2.repaint();
    }

    private void limpiarResultados() {
        txtRes1.setText("");
        txtRes2.setText("");
        lblPuntos1.setText("");
        lblPuntos2.setText("");
        lblResultadoGeneral.setText("");
        spRes1.setBorder(BorderFactory.createTitledBorder("Resultados"));
        spRes2.setBorder(BorderFactory.createTitledBorder("Resultados"));
    }
}

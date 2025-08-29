import javax.swing.JFrame;
import javax.swing.JButton;

public class Frmjuego extends JFrame {

    public Frmjuego(){
        setTitle(title:"Apuntado");
        setSize(width:600, 400)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir=new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        getContentPane().add(btnRepartir);

        JButton btnVerificar=new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        getContentPane().add(btnVerificar);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(r:0, g: 230, b: 356));
        pnlJugador1.setLayout(mgr:null)

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(r:0, g: 230, b: 356));
        pnlJugador2.setLayout(mgr:null)

        jTabbedpane tpjugadores = new jTabbedpane();
        tpjugadores.addTab(title)

        Jugador1=new Jugador();
        Jugador2=new Jugador();

        private void repartir(){
            Jugador1.repartir();
            Jugador2.repartir();

        }
    }
}

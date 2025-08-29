import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 40;
    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MARGEN + TOTAL_CARTAS * DISTANCIA;
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i].mostrar(pnl, posicion, MARGEN);
            posicion -= DISTANCIA;
        }
        pnl.repaint();
    }

}

import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 25;
    private final int DISTANCIA = 53;
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

    public String getGrupos() {
        String resultado = "No se encontraron grupos ";
        // inicial contadores
        int[] contadores = new int[13];
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }
        // obtener los resultados
        boolean hayGrupos = false;
        for (int cont : contadores) {
            if (cont >= 2) {
                hayGrupos = true;
            }
        }
        if (hayGrupos) {
            resultado = "Se encontraron los siguientes grupos: \n";
            int p = 0;
            for (int cont : contadores) {
                if (cont >= 2) {
                    resultado += "\n" + Grupo.values()[cont] + " de " + NombreCarta.values()[p] + "\n";
                }
                p++;
            }
        }

        return resultado;
    }
}

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

    public String getEscaleras() {
    String resultado = "";
    boolean encontrado = false;
    
    final int MIN_run = 3;

    // se recorre cada pinta
    for (Pinta pinta : Pinta.values()) {
        
        boolean[] present = new boolean[13];
        boolean any = false;

        for (Carta c : cartas) {
            if (c != null && c.getPinta() == pinta) {
                present[c.getNombre().ordinal()] = true;
                any = true;
            }
        }
        if (!any) continue; // no hay cartas de esta pinta se sigue
        
         boolean[] ext = new boolean[14];
        for (int i = 0; i < 13; i++) ext[i] = present[i];
        ext[13] = present[0]; 
     
        int runStart = -1;
        for (int i = 0; i <= 14; i++) {
            if (i < 14 && ext[i]) {
                if (runStart == -1) runStart = i;
            } else {
                if (runStart != -1) {
                    int longitud = i - runStart;
                    if (longitud >= MIN_run) {
                        
                        encontrado = true;
                        resultado += String.format("Escalera de %d cartas de %s: ", longitud, pinta);

                        for (int j = runStart; j < i; j++) {
                            
                            if (j == 13) {
                                resultado += "AS ";
                            } else {
                                resultado += NombreCarta.values()[j].name() + " ";
                            }
                        }
                        resultado += "\n";
                    }
                    runStart = -1; 
                }
            }
        }
    }

    if (!encontrado) return "No se encontraron escaleras.";
    return resultado.toString();
}
}

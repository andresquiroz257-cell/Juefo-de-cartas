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
                    resultado += Grupo.values()[cont] + " de " + NombreCarta.values()[p] + "\n";
                }
                p++;
            }
        }
        return resultado;
    }

    public String getEscaleras() {
    String resultado = "";
    boolean encontrado = false;
    
    final int MIN_run = 2;

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
                        resultado += String.format("Escalera de %d cartas de %s:  | ", longitud, pinta);

                        for (int j = runStart; j < i; j++) {
                            
                            if (j == 13) {
                                resultado += "AS ";
                            } else {
                                resultado += NombreCarta.values()[j].name() + " | ";
                            }
                        }
                        resultado += "\n";
                    }
                    runStart = -1; 
                }
            }
        }
    }
    if (!encontrado) return "No se encontraron escaleras. \n";
    return resultado.toString();
}

    private boolean[] detectarIndicesEscaleras(int minRun) {
        boolean[] usadasEsc = new boolean[TOTAL_CARTAS];

        for (Pinta pinta : Pinta.values()) {
            boolean[] present = new boolean[13];
            int[] posPorValor = new int[13]; // primer índice donde aparece ese valor (o -1)
            for (int v = 0; v < 13; v++) posPorValor[v] = -1;

            // rellenar present[] y posPorValor[] (guardando la primera coincidencia)
            for (int i = 0; i < TOTAL_CARTAS; i++) {
                Carta c = cartas[i];
                if (c != null && c.getPinta() == pinta) {
                    int v = c.getNombre().ordinal();
                    present[v] = true;
                    if (posPorValor[v] == -1) posPorValor[v] = i;
                }
            }

            // si no hay cartas de esta pinta, saltar
            boolean any = false;
            for (int i = 0; i < 13; i++) if (present[i]) { any = true; break; }
            if (!any) continue;

            // ext[0..13] = present[0..12] y ext[13] = present[0] (As como alto)
            boolean[] ext = new boolean[14];
            for (int i = 0; i < 13; i++) ext[i] = present[i];
            ext[13] = present[0];

            // buscar runs en ext[]
            int runStart = -1;
            for (int i = 0; i <= 14; i++) { // i==14 forzar cierre
                if (i < 14 && ext[i]) {
                    if (runStart == -1) runStart = i;
                } else {
                    if (runStart != -1) {
                        int runLength = i - runStart;
                        if (runLength >= minRun) {
                            // marcar en usadasEsc las cartas correspondientes (una por valor)
                            for (int j = runStart; j < i; j++) {
                                int valorOrdinal = (j == 13) ? 0 : j; // j==13 representa AS como alto
                                int idxCarta = posPorValor[valorOrdinal];
                                if (idxCarta != -1) {
                                    usadasEsc[idxCarta] = true;
                                    // NOTA: si hay duplicados solo marcamos la primer coincidencia
                                }
                            }
                        }
                        runStart = -1;
                    }
                }
            }
        }
        return usadasEsc;
    }

    public int getPuntaje() {
        boolean[] usadas = new boolean[TOTAL_CARTAS];

        // 1) marcar pares/ternas/cuarta por nombre
        int[] contadores = new int[13];
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            if (cartas[i] != null) contadores[cartas[i].getNombre().ordinal()]++;
        }
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            if (cartas[i] != null) {
                int count = contadores[cartas[i].getNombre().ordinal()];
                if (count >= 2) usadas[i] = true;
            }
        }

        // 2) marcar cartas de escaleras
        final int MIN_RUN = 2;
        boolean[] usadasEsc = detectarIndicesEscaleras(MIN_RUN);
        for (int i = 0; i < TOTAL_CARTAS; i++) if (usadasEsc[i]) usadas[i] = true;

        // 3) sumar cartas no usadas
        int total = 0;
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            if (cartas[i] != null && !usadas[i]) {
                switch (cartas[i].getNombre()) {
                    case AS:
                    case JACK:
                    case QUEEN:
                    case KING:
                        total += 10;
                        break;
                    default:
                        total += cartas[i].getNombre().ordinal() + 1;
                }
            }
        }
        return total;
    }

    public String getCartasSobran(int minRun) {
        boolean[] usadas = new boolean[TOTAL_CARTAS];

        // 1) marcar pares/ternas/cuarta por nombre
        int[] contadores = new int[13];
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            if (cartas[i] != null) contadores[cartas[i].getNombre().ordinal()]++;
        }
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            if (cartas[i] != null) {
                int count = contadores[cartas[i].getNombre().ordinal()];
                if (count >= 2) usadas[i] = true;
            }
        }

        // 2) marcar cartas de escaleras usando el helper
        boolean[] usadasEsc = detectarIndicesEscaleras(minRun);
        for (int i = 0; i < TOTAL_CARTAS; i++) if (usadasEsc[i]) usadas[i] = true;

        // 3) construir String de sobrantes
        String sobrante = " ";
        sobrante += (" \n Cartas sobrantes:\n");
        boolean any = false;
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            if (cartas[i] != null && !usadas[i]) {
                any = true;
                NombreCarta nc = cartas[i].getNombre();
                Pinta p = cartas[i].getPinta();
                int valor;
                switch (nc) {
                    case AS:
                    case JACK:
                    case QUEEN:
                    case KING:
                        valor = 10;
                        break;
                    default:
                        valor = nc.ordinal() + 1;
                }
                sobrante += "  " + (nc.name()) + (" de ") + (p.name())
                   + (" ( ") + (valor) + (") \n");
            }
        }
        if (!any) return "No quedan cartas sobrantes.";
        return sobrante;
    }
}

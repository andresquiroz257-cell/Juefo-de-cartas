public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int SEPARACION = 40;
    private Carta [] cartas = new Carta(TOTAL_CARTAS)
    private Random r = new Random();

    public repartir(){
        for(int i=0 ; i<TOTAL_CARTAS; i++){
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar (JPanel pnl){
        pnl.removeAll();

        for (int i= 0; i < TOTAL_CARTAS; i++){
            cartas[i].mostrar(pnl, 10 + i * SEPARACION, y:10)
        }

        pnl.repaint();
    }
}

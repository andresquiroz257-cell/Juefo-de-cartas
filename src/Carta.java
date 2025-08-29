public class Carta {

    private int indice;

    public Carta (Random r) {
        indice = r.nextint(bound 52) + 1;
    }

    public void mostrar (JPanel pnl, int x, int y){
        JLabel lblCarta = new JLabel();
        String archivoCarta = "imagenes/CARTA"+indice + ".jpg";
        ImageIcon imgCarta = new ImageIcon();
        lblCarta.setIcon(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidht(), imgCarta.getIconHeigth);
        pnl.add(lblCarta);     
    }
        public Pinta getPinta(){
            if(indice<= 13) {
                return Pinta.TREBOL;
            }
            else if(indice<= 26){
                return Pinta.PICA;
            }
            else if(indice<= 39){
                return Pinta.CORAZON;
            }
            else (
                return Pinta.DIAMANTE;
            )         
        }
}


package panel;


public class Matrice{
    int[][] matrice; 

    public Matrice() {
        matrice = new int[8][8];
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; j++) {
                matrice[i][j] = 0;
            }
        }

    }
    public int[][] getMatrice() {
        return this.matrice;
    }

    public void affichage() {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + "   ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println(" ");
    }



}
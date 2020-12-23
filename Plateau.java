import java.util.Random;

public class Plateau {
    //attributs
    private final int hauteur;
    private final int largeur = 9;
    private Case[][] cases;

    //constructeur
    public Plateau() {
        Random rd = new Random();
        this.hauteur = rd.nextInt(11-10+1)+10;
        this.cases = new Case[hauteur][largeur];
        ajouteBlocs();
        ajouteAnimaux();
    }

    //afficher le plateau
    public void affiche() {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int coord = 1;
        System.out.print("     ");
        for (int i = 0; i < largeur; i++)
            System.out.print(coord++ + "   ");
        System.out.println();
        System.out.print("   ");
        for (int i = 0; i < largeur; i++)
            System.out.print("----");
        System.out.println();

        for (int i = 0; i < this.hauteur; i++) {
            System.out.print(alpha.charAt(i) + " ");
            System.out.print("| ");
            for (int j = 0; j < this.largeur; j++) {
                if (cases[i][j] != null)
                    System.out.print(cases[i][j].getCouleur() + " ");
            }
            System.out.println();
        }
    }

    //placement des animaux
    private void ajouteAnimaux() {
        for (int a = 0; a < this.largeur; a++)
            this.cases[0][a] = new Animal();
    }

    //placement des blocs dans le plateau
    private void ajouteBlocs() {
        for (int i = 1; i < this.cases.length; i++) {
            for (int j = 0; j < this.cases[i].length; j++) {
                this.cases[i][j] = new Bloc();
            }
        }
    }

    public static void main(String[] args) {
        Plateau p = new Plateau();
        p.affiche();
    }
}
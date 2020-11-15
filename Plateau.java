import java.util.Random;

public class Plateau {
    //attributs
    private final int hauteur;
    private final int largeur = 9;
    private Case[][] case;

    //constructeur
    public Plateau() {
        Random rd = new Random();
        this.hauteur = rd.nextInt(11-10+1)+10;
        this.case = new Case [hauteur] [largeur];
        ajouteBlocs();
    }

    //afficher le plateau
    public void affiche() {
        //ligne contenant les animaux, fonction à définir plus tard
        for (int a = 0; a < this.largeur; a++)
            System.out.print("Animal" + " ");
        System.out.println();

        for (int i = 1; i < this.hauteur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                System.out.print(case [i][j].getCouleur() + " ");
            }
            System.out.println();
        }
    }

    //placement des animaux
    private void ajouteAnimaux() {

    }

    //placement des blocs dans le plateau
    private void ajouteBlocs() {
        for (int i = 1; i < this.blocs.length; i++) {
            for (int j = 0; j < this.blocs[i].length; j++) {
                this.blocs[i][j] = new Bloc();
            }
        }
    }

    public static void main(String[] args) {
        Plateau p = new Plateau();
        p.affiche();
    }
}
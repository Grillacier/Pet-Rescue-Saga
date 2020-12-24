import java.util.Scanner;

public class Jeu {
    //attributs
    private Joueur joueur;
    private Plateau plateau;

    //constructeur
    public Jeu(Joueur j, Plateau p) {
        this.joueur = j;
        this.plateau = p;
    }

    //déroulement d'une partie
    public void jouer() {
        char action;
        int[] coord = new int[2];
        boolean fini = false;
        while (!fini) {
            coord = joueur.demanderCoordonnes();
            plateau.casserBloc(coord[0],coord[1]);
            plateau.affiche();
            fini = true;
        }
    }
}

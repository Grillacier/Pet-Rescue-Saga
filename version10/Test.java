import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Test {

    public static void serialiser (Niveau n) {
        try {
            FileOutputStream fichier = new FileOutputStream("Niveau" + n.getId());
            ObjectOutputStream objet = new ObjectOutputStream(fichier);
            objet.writeObject(n);
            objet.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Meilleur jeu de 2021");

        Joueur joueur = new Joueur();
        Plateau p = new Plateau();
        p.affiche();
        Jeu partie = new Jeu(joueur,p);
        partie.jouer();
        /*
        //Niveau1 n1 = new Niveau1();
        //n1.affiche();
        //Jeu partie1 = new Jeu(joueur,n1);
        //partie1.jouer();
        if (!joueur.getRobot())
            joueur.finir();
        */
        //serialiser(new Niveau(1));
        /*
        Joueur j = new Joueur();
        Plateau n1 = new Niveau(1).getPlateau();
        n1.affiche();
        Jeu partie1 = new Jeu(j, n1);
        partie1.jouer();
        */
    }
}

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

        Plateau p = new Plateau();
        Joueur joueur = new Joueur();
        p.affiche();
        Jeu partie = new Jeu(joueur,p);
        partie.jouer();
        if (!joueur.getRobot())
            joueur.finir();
        //serialiser(new Niveau(3));

        /*
        Plateau n1 = new Niveau(1).getPlateau();
        n1.affiche();
        Jeu partie1 = new Jeu(j, n1);
        partie1.jouer();
        */
    }
}

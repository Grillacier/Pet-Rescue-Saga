public class Test {
    public static void main(String[] args) {
        Joueur joueur = new Joueur(false);
        Plateau p = new Plateau();
        p.affiche();
        Jeu partie = new Jeu(joueur,p);
        partie.jouer();

        //Niveau1 n1 = new Niveau1();
        //n1.affiche();
        //Jeu partie1 = new Jeu(joueur,n1);
        //partie1.jouer();
        joueur.finir();
    }
}

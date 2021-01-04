public class Jeu {
    //attributs
    private Joueur joueur;
    private Plateau plateau;

    //constructeur
    public Jeu(Joueur j, Plateau p) {
        this.joueur = j;
        this.plateau = p;
    }

    public static int randRange(int a, int b) {
        return Plateau.rand.nextInt(b-a)+a;
    }

    //déroulement d'une partie
    public void jouer() {
        int[] coord = new int[2];
        boolean fini = false;
        while (!fini) {
            if (joueur.getRobot()) {
                coord[0] = randRange(0, plateau.getHauteur());
                coord[1] = randRange(0, plateau.getLargeur());
            }
            else
                coord = joueur.demanderCoordonnes();
            plateau.casserBloc(coord[0],coord[1]);
            plateau.affiche();
            if (plateau.gagne() || plateau.perdu())
                fini = true;
        }
        if (plateau.gagne())
            System.out.println("Vous avez gagné !");
        if (plateau.perdu())
            System.out.println("Vous avez perdu !");
    }
}

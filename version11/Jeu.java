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

    public boolean oui(String o) {
        switch (o) {
            case "oui":
            case "Oui":
            case "OUI":
            case "o":
            case "O":
            case "yes":
            case "Yes":
            case "YES":
            case "y":
            case "Y":
                return true;
            default: return false;
        }
    }

    public boolean non(String n) {
        switch (n) {
            case "non":
            case "Non":
            case "NON":
            case "n":
            case "N":
            case "no":
            case "No":
            case "NO":
                return true;
            default: return false;
        }
    }

    //déroulement d'une partie
    public void jouer() {
        int[] coord = new int[2];
        String rob = joueur.demanderStr("Voulez-vous faire jouer un robot ? (o/n)");
        if (oui(rob))
            joueur.setRobot(true);
        else if (non(rob))
            joueur.setRobot(false);

        while (!plateau.fini()) {
            if (plateau.getNbFusees() != 0) {
                String fusee = joueur.demanderStr("Voulez-vous utiliser une fusée ?");
            }

            if (joueur.getRobot()) {
                for (int i = 0; i < plateau.getHauteur(); i++) {
                    for (int j = 0; j < plateau.getLargeur(); j++) {
                        if (plateau.getCases()[i][j] instanceof BlocBonus) //on demande au robot de prioriser les cases bonus avant de casser les autres
                            plateau.casserBloc(i,j);
                    }
                }
                coord[0] = randRange(0, plateau.getHauteur());
                coord[1] = randRange(0, plateau.getLargeur());
            }
            else
                coord = joueur.demanderCoordonnes();
            plateau.casserBloc(coord[0],coord[1]);
            plateau.affiche();
        }
        if (plateau.gagne())
            System.out.println("Vous avez gagné !");
        else if (plateau.perdu())
            System.out.println("Vous avez perdu !");
    }
}

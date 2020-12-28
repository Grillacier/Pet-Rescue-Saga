import java.util.Random;

public class Plateau {
    //attributs
    private final int hauteur;
    private final int largeur = 9;
    private Case[][] cases;
    private Case[][] adjacent;

    //constructeur
    public Plateau() {
        Random rd = new Random();
        this.hauteur = rd.nextInt(11-10+1)+10;
        this.cases = new Case[hauteur][largeur];
        this.adjacent = new Case[hauteur][largeur];
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
                    System.out.print(cases[i][j].toString() + " ");
                else
                    System.out.print(" *  ");
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

    //fonction testant si des blocs adjacents sont de la même couleur
    public boolean memeCouleur(int a, int b) {
        String couleur = cases[a][b].getCouleur();
        boolean pareil = false;

        if (a-1 > 0) {
            if (cases[a - 1][b] instanceof Bloc) {
                if (couleur.equals(cases[a - 1][b].getCouleur()) && adjacent[a - 1][b] == null) {
                    pareil = true;
                    adjacent[a - 1][b] = cases[a - 1][b];
                    memeCouleur(a - 1, b);
                }
            }
        }

        if (a+1 < hauteur) {
            if (cases[a + 1][b] instanceof Bloc) {
                if (couleur.equals(cases[a + 1][b].getCouleur()) && adjacent[a + 1][b] == null) {
                    pareil = true;
                    adjacent[a + 1][b] = cases[a + 1][b];
                    memeCouleur(a + 1, b);
                }
            }
        }

        if (b-1 > 0) {
            if(cases[a][b - 1] instanceof Bloc) {
                if (couleur.equals(cases[a][b - 1].getCouleur()) && adjacent[a][b - 1] == null) {
                    pareil = true;
                    adjacent[a][b - 1] = cases[a][b - 1];
                    memeCouleur(a, b - 1);
                }
            }
        }

        if (b+1 < largeur) {
            if (cases[a][b + 1] instanceof Bloc) {
                if (couleur.equals(cases[a][b + 1].getCouleur()) && adjacent[a][b + 1] == null) {
                    pareil = true;
                    adjacent[a][b + 1] = cases[a][b + 1];
                    memeCouleur(a, b + 1);
                }
            }
        }

        return pareil;
    }

    //fonction détruisant le bloc choisit si ceux à côtés sont de la même couleur
    public void casserBloc(int h, int l) {
        if (this.cases[h][l] instanceof Bloc) { //si la case contient un bloc
            System.out.println(cases[h][l].getCouleur());
            if (memeCouleur(h,l)) {
                cases[h][l] = null;
                for (int i = 0; i < cases.length; i++) {
                    for (int j = 0; j < cases[i].length; j++) {
                        if (adjacent[i][j] != null) {
                            cases[i][j] = null;
                            adjacent[i][j] = null;
                            tomber();
                        }
                    }
                }
            }
        }
    }

    //fonction faisant tomber les cases
    public void tomber() {
        for (int i = cases.length - 1; i > 0; i--) {
            for (int j = cases[i].length - 1; j >= 0; j--) {
                if (cases[i][j] == null) {
                    cases[i][j] = cases[i - 1][j];
                    cases[i - 1][j] = null;
                }

                //on supprime l'animal qui arrive en bas du tableau
                if (cases[hauteur - 1][j] instanceof Animal)
                    cases[hauteur - 1][j] = null;
            }
        }

        /* ne marche pas
        for (int i = 0; i < hauteur; i++) {
            for (int j = 1; j < largeur; j++) {
                //les colonnes de droite vont à gauche
                if (cases[hauteur - 1][j-1] == null) {
                    cases[i][j-1] = cases[i][j];
                    cases[i][j] = null;
                }
            }
        }*/
    }

    public static void main(String[] args) {
        Joueur j = new Joueur();
        Plateau p = new Plateau();
        p.affiche();
        while (true) {
            Jeu partie = new Jeu(j,p);
            partie.jouer();
        }
    }
}
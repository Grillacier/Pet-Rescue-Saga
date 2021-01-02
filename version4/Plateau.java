import java.util.Random;

public class Plateau {
    //attributs
    private static int nbCoups = 20;
    private final int hauteur;
    private final int largeur = 9;
    private Case[][] cases;
    private Case[][] adjacent;
    public static Random rand = new Random();

    //constructeur
    public Plateau() {
        Random rd = new Random();
        this.hauteur = rd.nextInt(11-10+1)+10;
        this.cases = new Case[hauteur][largeur];
        this.adjacent = new Case[hauteur][largeur];
        ajouteBallons();
        ajouteBlocs();
        ajouteAnimaux();
    }

    public static int randRange(int a, int b) {
        return rand.nextInt(b-a)+a;
    }

    //afficher le plateau
    public void affiche() {
        System.out.println("Meilleur jeu de 2021");
        System.out.println("Coups restans : " + nbCoups);
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
                if (cases[i][j] == null)
                    this.cases[i][j] = new Bloc();
            }
        }
    }

    //placement des blocs bonus
    private void ajouteBallons() {
        if (randRange(0,2) == 1) {
            int l = randRange(0,largeur+1);
            int h = randRange(0, hauteur+1);
            cases[h][l] = new BlocBonus();
        }
    }

    //fonction testant si des blocs adjacents sont de la meme couleur
    public boolean memeCouleur(int a, int b) {
        String couleur = cases[a][b].getCouleur();
        boolean pareil = false;

        if (a-1 > 0) { //on verifie qu'on ne sorte pas du plateau
            if (cases[a - 1][b] instanceof Bloc) { //on verifie que la case du dessus soit bien un Bloc
                if (couleur.equals(cases[a - 1][b].getCouleur()) && adjacent[a - 1][b] == null) { //on verifie que le Bloc au dessus soit de la meme couleur que le Bloc actuel
                    pareil = true;
                    adjacent[a - 1][b] = cases[a - 1][b]; //on remplit le tableau contenant les blocs adjacents avec un Bloc de la meme couleur
                    memeCouleur(a - 1, b); //on relance la fonction avec la case au dessus
                }
            }
        }

        if (a+1 < hauteur) { //on verifie qu'on ne sorte pas du plateau
            if (cases[a + 1][b] instanceof Bloc) { //on verifie que la case du dessous soit bien un Bloc
                if (couleur.equals(cases[a + 1][b].getCouleur()) && adjacent[a + 1][b] == null) { //on verifie que le Bloc en dessous soit de la meme couleur que le Bloc actuel
                    pareil = true;
                    adjacent[a + 1][b] = cases[a + 1][b]; //on remplit le tableau contenant les blocs adjacents avec un Bloc de la meme couleur
                    memeCouleur(a + 1, b); //on relance la fonction avec la case en dessous
                }
            }
        }

        if (b-1 > 0) { //on verifie qu'on ne sorte pas du plateau
            if(cases[a][b - 1] instanceof Bloc) { //on verifie que la case de gauche soit bien un Bloc
                if (couleur.equals(cases[a][b - 1].getCouleur()) && adjacent[a][b - 1] == null) { //on verifie que le Bloc a gauche soit de la meme couleur que le Bloc actuel
                    pareil = true;
                    adjacent[a][b - 1] = cases[a][b - 1]; //on remplit le tableau contenant les blocs adjacents avec un Bloc de la meme couleur
                    memeCouleur(a, b - 1); //on relance la fonction avec la case a gauche
                }
            }
        }

        if (b+1 < largeur) { //on verifie qu'on ne sorte pas du plateau
            if (cases[a][b + 1] instanceof Bloc) { //on verifie que la case de droite soit bien un Bloc
                if (couleur.equals(cases[a][b + 1].getCouleur()) && adjacent[a][b + 1] == null) { //on verifie que le Bloc a droite soit de la meme couleur que le Bloc actuel
                    pareil = true;
                    adjacent[a][b + 1] = cases[a][b + 1]; //on remplit le tableau contenant les blocs adjacents avec un Bloc de la meme couleur
                    memeCouleur(a, b + 1); //on relance la fonction avec la case a droite
                }
            }
        }

        return pareil;
    }

    //fonction détruisant le bloc choisit si ceux à côtés sont de la même couleur
    public void casserBloc(int h, int l) {
        if (this.cases[h][l] instanceof Bloc) { //si la case contient un bloc qu'on peut casser
            if (cases[h][l] instanceof BlocBonus) { //si la case contient un ballon
                ballon(cases[h][l]);
                nbCoups--;
            }
            else { //si la case contient un bloc normal
                if (memeCouleur(h, l)) {
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
                    nbCoups--;
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
                if (cases[hauteur - 1][j - 1] == null) { //si une case de la derniere ligne est vide
                        cases[i][j - 1] = cases[i][j];
                        cases[i][j] = null;
                }
            }
        }
        */
    }

    //fonction qui supprime tous les blocs de la même couleur, quelque soit leur position
    public void ballon(Case ball) {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                if (cases[i][j].getCouleur().equals(ball.getCouleur())) {
                    cases[i][j] = null;
                    tomber();
                }
            }
        }
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
import java.io.Serializable;
import java.util.Random;

public class Plateau implements Serializable {
    //attributs
    private static int score;
    private static int nbCoups;
    private static int nbAnimaux;
    private int nbBallons;
    private int nbBombes;
    private int nbFusees;
    private int nbMarteaux;
    private final int hauteur;
    private final int largeur;
    private Case[][] cases;
    private Case[][] adjacent;
    public static Random rand = new Random();

    //constructeur
    public Plateau() {
        Random rd = new Random();
        score = 0;
        //nbBallons = randRange(0,2);
        nbBallons = 1;
        nbBombes = randRange(0,2);
        nbCoups = 20;
        largeur = 9;
        hauteur = rd.nextInt(11-10+1)+10;
        nbAnimaux = randRange(2, largeur);
        cases = new Case[hauteur][largeur];
        adjacent = new Case[hauteur][largeur];
        ajouteBallons();
        ajouteBombes();
        ajouteBlocs();
        ajouteAnimaux();
    }

    //accesseurs
    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public static int randRange(int a, int b) {
        return rand.nextInt(b-a)+a;
    }

    //afficher le plateau
    public void affiche() {
        System.out.println("Meilleur jeu de 2021");
        System.out.println("Score : " + score);
        System.out.println("Coups restants : " + nbCoups);
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
    public void ajouteAnimaux() {
        int c = 0;
        while (c < nbAnimaux) {
            int y = randRange(0, largeur);
            if (cases[0][y] == null) {
                this.cases[0][y] = new Animal();
                c++;
            }
        }
    }

    //placement des blocs dans le plateau
    public void ajouteBlocs() {
        for (int i = 1; i < this.cases.length; i++) {
            for (int j = 0; j < this.cases[i].length; j++) {
                if (cases[i][j] == null)
                    this.cases[i][j] = new Bloc();
            }
        }
    }

    //placement des blocs incassables
    public void ajouteObstacles() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (cases[i][j] == null)
                    this.cases[i][j] = new BlocIncassable("Invisible");
            }
        }
    }

    //placement des blocs bonus
    public void ajouteBallons() {
        if (nbBallons == 1) {
            int h = randRange(1, hauteur);
            int l = randRange(0,largeur);
            if (cases[h][l] == null)
                cases[h][l] = new Ballon();
        }
    }

    public void ajouteBombes() {
        if (nbBombes == 1) {
            int h = randRange(1, hauteur);
            int l = randRange(0,largeur);
            cases[h][l] = new Bombe();
        }
    }

    //fonction testant si des blocs adjacents sont de la meme couleur
    public boolean memeCouleur(int a, int b) {
        String couleur = cases[a][b].getCouleur();
        boolean pareil = false;

        if (a-1 > 0) { //on verifie qu'on ne sorte pas du plateau
            if (cases[a - 1][b] instanceof Bloc && !(cases[a - 1][b] instanceof Animal)) { //on verifie que la case du dessus soit bien un Bloc
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
            if (cases[h][l] instanceof Bloc) {
                if (cases[h][l] instanceof BlocBonus) {
                    if (cases[h][l] instanceof Bombe)
                        bombe(h,l);
                    //else
                    if (cases[h][l] instanceof Ballon)
                        ballon(h,l);
                }
                else { //si la case contient un bloc normal
                    if (!(cases[h][l] instanceof BlocIncassable)) {
                    if (memeCouleur(h, l)) {
                        cases[h][l] = null;
                        for (int i = 0; i < cases.length; i++) {
                            for (int j = 0; j < cases[i].length; j++) {
                                if (adjacent[i][j] != null) {
                                    cases[i][j] = null;
                                    adjacent[i][j] = null;
                                    score += 10;
                                    tomber();
                                }
                            }
                            decalage();
                        }
                    }
                }
                nbCoups--;
            }
        }
    }

    //fonction faisant tomber les cases
    public void tomber() {
        for (int i = cases.length - 1; i > 0; i--) {
            for (int j = cases[i].length - 1; j >= 0; j--) {
                if (cases[i][j] == null && !(cases[i - 1][j] instanceof BlocIncassable)) {
                    cases[i][j] = cases[i - 1][j];
                    cases[i - 1][j] = null;
                }

                //on supprime l'animal qui arrive en bas du tableau
                if (cases[hauteur - 1][j] instanceof Animal) {
                    cases[hauteur - 1][j] = null;
                    nbAnimaux--;
                    score += 1000;
                }
            }
        }
    }

    //on verifie si la colonne col est vide
    public boolean vide(int col) {
        for (int i = 0; i < hauteur; i++) {
            if (cases[i][col] != null && !(cases[i][col] instanceof BlocIncassable))
                return false;
        }
        return true;
    }

    //la colonne col recupere les Cases de la colonne a sa droite
    public void decalageGauche(int col) {
        for (int i = 0; i < hauteur; i++) {
            if (col+1 < largeur) {
                if (!(cases[i][col+1] instanceof BlocIncassable)) {
                    cases[i][col] = cases[i][col + 1];
                    cases[i][col + 1] = null;
                }
            }
        }
    }

    //les colonnes de droite vont a gauche
    public void decalage() {
        for (int i = hauteur-1; i >= 0; i--) {
            for (int j = largeur-1; j >= 0; j--) {
                if (vide(j)) {
                    decalageGauche(j);
                }
            }
        }
    }

    //supprime tous les blocs de la même couleur, quelque soit leur position
    public void ballon(int a, int b) {
        String couleur = cases[a][b].getCouleur();
        for (int i = 1; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                if (cases[i][j].getCouleur().equals(couleur)) {
                    cases[i][j] = null;
                    score += 10;
                    tomber();
                    decalage();
                }
            }
        }
    }

    //supprime les cases autour de la case choisie
    public void bombe(int a, int b) {
        cases[a][b] = null;
        score += 300;

        if (a - 1 > 0) { //on verifie qu'on ne sorte pas du plateau
            if (!(cases[a - 1][b] instanceof BlocIncassable) && !(cases[a - 1][b] instanceof Animal)) { //on verifie que la case du dessus soit destructible
                cases[a - 1][b] = null; //on supprime le bloc
            }
            if (b - 1 > 0) {
                if (!(cases[a - 1][b - 1] instanceof BlocIncassable) && !(cases[a - 1][b - 1] instanceof Animal)) {
                    cases[a - 1][b - 1] = null;
                }
            }
            if (b + 1 < largeur) {
                if (!(cases[a - 1][b + 1] instanceof BlocIncassable) && !(cases[a - 1][b + 1] instanceof Animal)) {
                    cases[a - 1][b + 1] = null;
                }
            }
        }

        if (a + 1 < hauteur) { //on verifie qu'on ne sorte pas du plateau
            if (!(cases[a + 1][b] instanceof BlocIncassable) && !(cases[a + 1][b] instanceof Animal)) { //on verifie que la case du dessous soit destructible
                cases[a + 1][b] = null; //on supprime le bloc
            }
            if (b - 1 > 0) {
                if (!(cases[a + 1][b - 1] instanceof BlocIncassable) && !(cases[a + 1][b - 1] instanceof Animal)) {
                    cases[a + 1][b - 1] = null;
                }
            }
            if (b + 1 < largeur) {
                if (!(cases[a + 1][b + 1] instanceof BlocIncassable) && !(cases[a + 1][b + 1] instanceof Animal)) {
                    cases[a + 1][b + 1] = null;
                }
            }
        }

        if (b - 1 > 0) { //on verifie qu'on ne sorte pas du plateau
            if (!(cases[a][b - 1] instanceof BlocIncassable) && !(cases[a][b - 1] instanceof Animal)) { //on verifie que la case de gauche soit destructible
                cases[a][b - 1] = null; //on supprime le bloc
            }
        }

        if (b + 1 < largeur) { //on verifie qu'on ne sorte pas du plateau
            if (!(cases[a][b + 1] instanceof BlocIncassable) && !(cases[a][b + 1] instanceof Animal)) { //on verifie que la case de droite soit destructible
                cases[a][b + 1] = null; //on supprime le bloc
            }
        }

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                tomber();
                decalage();
            }
        }
    }


    //supprime une colonne
    public void fusee(int col) {
        for (int i = 0; i < hauteur; i++) {
            if (cases[i][col] != null)
                score += 10;
        }
        decalageGauche(col);
        decalage();
    }

    //supprime un bloc
    public void marteau(int a, int b) {
        if (!(cases[a][b] instanceof BlocIncassable)) {
            cases[a][b] = null;
            tomber();
            score += 10;
        }
    }

    public boolean perdu() {
        return nbCoups == 0 && nbAnimaux != 0;
    }

    public boolean gagne() {
        if (nbAnimaux == 0) {
            if (nbCoups >= 0) { //s'il reste des coups apres avoir sauve tous les animaux, on gagne des points
                for (int i = 0; i < nbCoups; i++)
                    score += 1000;
            }
            for (int i = 0; i < hauteur; i++) {
                for (int j = 0; j < largeur; j++) {
                    if (cases[i][j] != null && !(cases[i][j] instanceof BlocIncassable))
                        score += 10; //on ajoute des points s'il reste des blocs (niveaux sans defilement)
                }
            }
        }
        return score >= 10000;
    }
}
import java.util.Random;

public class Bloc extends Case{
    //attributs
    private String couleur;

    //constructeur
    public Bloc() {
        colorier();
    }

    public static Random rand = new Random();

    //permet de tirer des entiers au hasard entre a inclus et b exclus
    public static int randRange(int a, int b) {
        return rand.nextInt(b-a)+a;
    }

    //on choisit la couleur des blocs aléatoirement
    public void colorier(){
        int c = randRange(1, 6);
        switch (c) {
            case 1: this.couleur = "Rouge"; break;
            case 2: this.couleur = "Bleu"; break;
            case 3: this.couleur = "Jaune"; break;
            case 4: this.couleur = "Vert"; break;
            case 5: this.couleur = "Violet"; break;
        }
    }

    //accesseurs
    public String getCouleur() {
        return '[' + couleur.substring(0,1) + ']';
    }
}

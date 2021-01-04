import java.util.Random;

public class Animal implements Case {
    //attribut
    private String espece;

    //constructeurs
    public Animal() {
        type();
    }

    public Animal(String espece) {
        this.espece = espece;
    }

    public static Random rand = new Random();

    // permet de tirer des entiers au hasard entre a inclus et b exclus
    public static int randRange(int a, int b) {
        return rand.nextInt(b-a)+a;
    }

    public void type() {
        int c = randRange(1, 13);
        switch (c) {
            case 1: this.espece = "Chat"; break;
            case 2: this.espece = "Chien"; break;
            case 3: this.espece = "Cochon"; break;
            case 4: this.espece = "Ecureuil"; break;
            case 5: this.espece = "Furet"; break;
            case 6: this.espece = "Kangourou"; break;
            case 7: this.espece = "Lapin"; break;
            case 8: this.espece = "Panda"; break;
            case 9: this.espece = "Phoque"; break;
            case 10: this.espece = "Pigeon"; break;
            case 11: this.espece = "Poussin"; break;
            case 12: this.espece = "Tortue"; break;
        }
    }

    //accesseur
    @Override
    public String getCouleur() {
        return espece;
    }

    @Override
    public String toString() {
        return ' ' + espece.substring(0,1).toLowerCase() + ' ';
    }
}

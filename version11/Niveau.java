import java.io.Serializable;
import java.util.Random;

public class Niveau implements Serializable {
    //attributs
    private Plateau plateau;
    private static int id;

    //accesseurs
    public Plateau getPlateau() {
        return plateau;
    }

    public int getId() {
        return id;
    }

    //constructeurs
    public Niveau(Plateau p, int id) {
        this.plateau = p;
        Niveau.id = id;
    }

    /*
    //creation du niveau 1 du vrai jeu
    public Niveau(int id) {
        Niveau.id = id;

        plateau = new Plateau(7, 7, 2, 30,2000,0,0,0,0);

        plateau.getCases()[0][1] = new Animal("Chien");
        plateau.getCases()[0][5] = new Animal("Chat");

        plateau.getCases()[0][0] = new BlocIncassable("Invisible");
        plateau.getCases()[0][6] = new BlocIncassable("Invisible");
        for (int j = 2; j <= 4; j++)
            plateau.getCases()[0][j] = new BlocIncassable("Invisible");

        for (int i = 1; i <= 2; i++) {
            for (int j = 0; j <= 1; j++)
                plateau.getCases()[i][j] = new Bloc("Mauve");
        }
        plateau.getCases()[5][0] = new Bloc("Mauve");
        plateau.getCases()[6][0] = new Bloc("Mauve");
        for (int i = 5; i <= 6; i++) {
            for (int j = 4; j <= 5; j++)
                plateau.getCases()[i][j] = new Bloc("Mauve");
        }

        for (int j = 2; j <= 4; j++)
            plateau.getCases()[1][j] = new Bloc("Jaune");
        for (int i = 3; i <= 4; i++) {
            for (int j = 0; j <= 1; j++)
                plateau.getCases()[i][j] = new Bloc("Jaune");
        }
        plateau.getCases()[5][3] = new Bloc("Jaune");
        plateau.getCases()[6][3] = new Bloc("Jaune");
        plateau.getCases()[5][6] = new Bloc("Jaune");
        plateau.getCases()[6][6] = new Bloc("Jaune");

        for (int i = 1; i <= 2; i++) {
            for (int j = 5; j <= 6; j++)
                plateau.getCases()[i][j] = new Bloc("Rouge");
        }
        for (int i = 5; i <= 6; i++) {
            for (int j = 1; j <= 2; j++)
                plateau.getCases()[i][j] = new Bloc("Rouge");
        }

        for (int i = 3; i <= 4; i++) {
            for (int j = 5; j <= 6; j++)
                plateau.getCases()[i][j] = new Bloc("Bleu");
        }

        for (int i = 2; i <= 4; i++) {
            for (int j = 2; j <= 4; j++)
                plateau.getCases()[i][j] = new Bloc("Vert");
        }
    }
    */


    //creation du niveau 13 du vrai jeu
    public Niveau(int id) {
        Random rd = new Random();
        Niveau.id = id;

        plateau = new Plateau(8, 9, 5, 25,7000,1,0,6,0);

        for (int j = 2; j <= 6; j++)
            plateau.getCases()[5][j] = new Animal();

        plateau.getCases()[0][0] = new BlocIncassable("Invisible");
        plateau.getCases()[0][8] = new BlocIncassable("Invisible");
        for (int i = 5; i <= 7; i++)
            plateau.getCases()[i][0] = new BlocIncassable("Invisible");
        for (int i = 5; i <= 7; i++)
            plateau.getCases()[i][8] = new BlocIncassable("Invisible");

        plateau.getCases()[3][0] = new BlocIncassable("Brique");
        plateau.getCases()[3][8] = new BlocIncassable("Brique");
        for (int j = 0; j <= 8; j++)
            plateau.getCases()[4][j] = new BlocIncassable("Brique");
        for (int i = 5; i <= 7; i++)
            plateau.getCases()[i][1] = new BlocIncassable("Brique");
        for (int i = 5; i <= 7; i++)
            plateau.getCases()[i][7] = new BlocIncassable("Brique");

        plateau.getCases()[3][4] = new Ballon("Vert");

        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 8; j++) {
                if (plateau.getCases()[i][j] == null) {
                    int c = Plateau.randRange(1,5);
                    switch (c) {
                        case 1: plateau.getCases()[i][j] = new Bloc("Mauve"); break;
                        case 2: plateau.getCases()[i][j] = new Bloc("Bleu"); break;
                        case 3: plateau.getCases()[i][j] = new Bloc("Jaune"); break;
                        case 4: plateau.getCases()[i][j] = new Bloc("Vert"); break;
                    }
                }
            }
        }

        for (int j = 2; j <= 6; j+=2)
            plateau.getCases()[6][j] = new Bloc("Mauve");
        for (int j = 3; j <= 6; j+=2)
            plateau.getCases()[7][j] = new Bloc("Mauve");

        for (int j = 3; j <= 6; j+=2)
            plateau.getCases()[6][j] = new Bloc("Bleu");
        for (int j = 2; j <= 6; j+=2)
            plateau.getCases()[7][j] = new Bloc("Bleu");
    }
}

import java.io.Serializable;

public class Niveau implements Serializable {
    private Plateau plateau;

    public Niveau() {
        //creation du niveau 1
        plateau = new Plateau(7, 7, 2);
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

    public Plateau getPlateau() {
        return plateau;
    }
}

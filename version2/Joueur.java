import java.util.Scanner;

public class Joueur {
    //attributs
    private Scanner scanReponse;

    //constructeur
    public Joueur() {
        this.scanReponse = new Scanner(System.in);
    }

    //fonction pour récupérer ce qui est entré au clavier
    private String demanderStr(String q) {
        System.out.println(q);
        return scanReponse.next();
    }

    //on demande au joueur quel bloc il veut détruire
    public int[] demanderCoordonnes() {
        int[] tab = new int[2];
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String coord = demanderStr("Entrez des coordonnées (ex : B6)");
        for (int i = 0; i < alpha.length(); i++) {
            if (coord.charAt(0) == alpha.charAt(i))
                tab[0] = i;
        }
        tab[1] = Character.getNumericValue(coord.charAt(1))-1;
        return tab;
    }

    public void finir() {
        this.scanReponse.close();
    }
}

public class Ballon extends BlocBonus {
    //attribut
    private String couleur;

    //constructeur
    public Ballon() {
        super("Ballon");
        coloriage();
    }

    //accesseur
    @Override
    public String getCouleur() {
        return this.couleur;
    }

    public void coloriage() {
        int co = Bloc.randRange(1, 6);
        switch (co) {
            case 1: this.couleur = "Rouge"; break;
            case 2: this.couleur = "Bleu"; break;
            case 3: this.couleur = "Jaune"; break;
            case 4: this.couleur = "Vert"; break;
            case 5: this.couleur = "Mauve"; break;
        }
    }

    @Override
    public String toString() {
        return '(' + this.couleur.substring(0,1).toLowerCase() + ')';
    }
}

public class Bombe extends BlocBonus {
    //constructeur
    public Bombe() {
        super("Bombe");
    }

    //accesseur
    @Override
    public String getCouleur() {
        return super.getType();
    }

    @Override
    public String toString() {
        return '(' + super.getType().substring(0,1).toLowerCase() + ')';
    }
}

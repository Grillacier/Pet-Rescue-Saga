public class BlocBonus extends Bloc {
    //attribut
    private String type;

    //constructeur
    public BlocBonus(String type) {
        this.type = type;
    }

    //accesseur
    @Override
    public String getCouleur() {
        if (type.equals("Ballon"))
            return super.getCouleur();
        else
            return type;
    }

    @Override
    public String toString() {
        if (type.equals("Ballon"))
            return '(' + super.getCouleur().substring(0,1).toLowerCase() + ')';
        else
            return '(' + type.substring(0,1).toLowerCase() + ')';
    }
}

public class BlocIncassable extends Case {
    //attribut
    private String type;

    //constructeur
    public BlocIncassable(String type) {
        this.type = type;
    }

    //accesseurs
    @Override
    public String getCouleur() {
        return type;
    }

    @Override
    public String toString() {
        return '|' + type.substring(0,1) + '|';
    }
}

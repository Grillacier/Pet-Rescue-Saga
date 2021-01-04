public class BlocBonus extends Bloc {
    //attribut
    private String type;

    //constructeur
    public BlocBonus(String type) {
        this.type = type;
    }

    //accesseur
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "( )";
    }
}

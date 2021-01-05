import java.io.Serializable;

/*
types de blocs incassables : blocs invisibles
                             briques
                             pierres
 */

public class BlocIncassable implements Case, Serializable {
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
        if (type.equals("Invisible"))
            return "   ";
        return '|' + type.substring(0,1) + '|';
    }
}

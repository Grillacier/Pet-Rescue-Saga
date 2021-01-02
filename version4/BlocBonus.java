public class BlocBonus extends Bloc {
    @Override
    public String toString() {
        return '(' + super.getCouleur().substring(0,1) + ')';
    }
}

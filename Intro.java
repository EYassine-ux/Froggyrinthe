import eko.*;

public abstract class Intro extends ObjetJeu{
    protected Intro(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Cette methode permet d'executer les methodes qui affiche les cerateurs du jeux
     * et le nom du jeux.
     */
    public static void jouer() {
        Auteurs.jouerAutheurs();
        EKO.attendre(25);
        TitreJeu.jouerTitre();
    }
}

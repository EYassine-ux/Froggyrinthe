import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;

public class PorteExit extends Porte{
        private boolean fermer;
        private static boolean visible;
    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */


    protected PorteExit(String nom, int x, int y, Etiquette etiquette, EKOCouleur couleur) {
        super(nom, x, y, etiquette, couleur);
        fermer = true;
        visible = true;
    }

    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    @Override
    protected void dessiner() {
        if (visible) {
            super.dessiner();
        }
    }
    public static void setVisible(boolean visible) {
        PorteExit.visible = visible;
    }

}

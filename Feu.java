import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;

import java.util.Random;

public class Feu extends Ennemi{
    private EKOChaine chaine;

    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    public Feu(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Permet d'initializer un Object de type Joueur (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected Feu(String nom, int x, int y, Etiquette etiquette) {
        super(nom, x, y, etiquette);
        chaine = new EKOChaine(this.etiquette.code, EKOCouleur.RVB(255,140,0));
        tempsProchaineAction = 1000;
        tempsAction = 0;
    }

    /**
     * Permet d'executer l'action de l'ennemi.
     */
    @Override
    public void executerAction() {
        //Effectuer l'effect mirroir
        chaine.retourner();
        Random rng = new Random();
        tempsProchaineAction = rng.nextInt(50, 2000);
    }

    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    @Override
    protected void dessiner() {
        EKOConsole.afficher(this.getX(), this.getY(),chaine);
    }
}

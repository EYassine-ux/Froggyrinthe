import eko.*;

public class Potion extends Item{
    private EKOChaine chaine;
    private EKOSon son;

    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    protected Potion(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Permet d'initializer un Object de type Joueur (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected Potion(String nom, int x, int y, Etiquette etiquette) {
        super(nom, x, y, etiquette);
        chaine = new EKOChaine(this.etiquette.code, EKOCouleur.ROUGE);
        son = EKOAudio.charger("JeuAudio/son_potion.wav");
    }

    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    @Override
    public void dessiner(){
        EKOConsole.afficher(this.getX(), this.getY(),chaine);
    }

    protected void jouerSon(){
        EKOAudio.jouer(son);
    }
}

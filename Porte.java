import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;

public class Porte extends ObjetJeu {
    private EKOChaine chaine;
    protected static boolean fermer; //Verifier si la porte est fermer ou ouverte

    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    protected Porte(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Permet d'initializer un Object de type Porte (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected Porte(String nom, int x, int y, Etiquette etiquette, EKOCouleur couleur) {
        super(nom, x, y, etiquette);
        chaine = new EKOChaine(this.etiquette.code, couleur);
    }


    /**
     * Toutes les classes qui sont des objets de jeu doivent implémenter la méthode de mise à jour.
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, avant le dessin.
     * Permet de modifier des parametre de l'objet
     *
     * @param deltaTemps Temps écoulé (en millisecondes) depuis la dernière trame
     */
    @Override
    protected void mettreAJour(long deltaTemps) {

    }

    /**
     * Toutes les classes qui sont des objets de jeu doivent implémenter la méthode de mise à jour.
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    @Override
    protected void dessiner() {
        EKOConsole.afficher(this.getX(), this.getY(),chaine);
    }

    /**
     * On retourne la valeur de "fermer" (Getter)
     * @return si la porte est ouverte(false) ou fermer(true).
     */
    public static boolean isFermer() {
        return fermer;
    }

    /**
     * Permet de definir la valeur "fermer" (Setter)
     * @param fermer valeur que va prendre "fermer" (True OU False)
     */
    public static void setFermer(boolean fermer) {
        Porte.fermer = fermer;
    }


}

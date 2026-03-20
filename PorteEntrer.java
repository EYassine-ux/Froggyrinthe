import eko.EKOCouleur;

public class PorteEntrer extends Porte{

    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    protected PorteEntrer(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Permet d'initializer un Object de type PorteEntrer (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected PorteEntrer(String nom, int x, int y, Etiquette etiquette, EKOCouleur couleur) {
        super(nom, x, y, etiquette, couleur);
        fermer = true;
    }
}

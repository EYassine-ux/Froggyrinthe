import eko.*;

public class Clef extends Item{
    private static EKOSon sonClef;
    private EKOChaine chaine;

    /**
     * Permet d'initializer un Object de type Joueur (Constructeur).
     * @param nom Le nom de l'object.
     * @param x Coordonnee x de l'objet.
     * @param y Coordonnee x de l'objet.
     * @param etiquette Ethiquette de l'objet.
     */
    public Clef(String nom,int x,int y, Etiquette etiquette){
        super(nom,x,y,etiquette);
        chaine = new EKOChaine(this.etiquette.code, EKOCouleur.JAUNE);
        sonClef = EKOAudio.charger("JeuAudio/DoorSound.wav");
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
     * Permet de jouer le son qui "ouvre la porte".
     */
    public static void activerSon(){
        EKOAudio.jouer(sonClef);
    }

}

import eko.EKOConsole;

import java.util.ArrayList;
import java.util.Comparator;

public class GestionnaireObjetsJeu {
    static private GestionnaireObjetsJeu instance = null;

    private final ArrayList<ObjetJeu> objetsJeu = new ArrayList<>();

    private GestionnaireObjetsJeu() {}  // pour éviter une instanciation externe

    /**
     * Retourne l'instance unique (singleton) du gestionnaire des objets de jeu.
     *
     * @return L'instance du gestionnaire des objets de jeu
     */
    public static GestionnaireObjetsJeu obtenir() {
        if (instance == null) {
            instance = new GestionnaireObjetsJeu();
        }

        return instance;
    }

    /**
     * cette methode supprime/clear tout les objets de la table objetsJeu
     */
    public void reinitialiser(){
        objetsJeu.clear();
    }


    /**
     * Enregistre un objet de jeu dans le gestionnaire des objets de jeu.
     * Les objets de jeu enregistrés sont mis à jour et dessinés par le gestionnaire.
     * Cette méthode est appelée par le constructeur de la classe ObjetJeu.
     *
     * @param objetJeu Objet de jeu à enregistrer
     */
    public void enregistrer(ObjetJeu objetJeu) {
        objetsJeu.add(objetJeu);
        ordonnerCouches();
    }

    /**
     * Met à jour tous les objets de jeu.
     * Retire de la gestion les objets de jeu marqués pour être détruits.
     *
     * @param deltaTemps Temps écoulé (en millisecondes) depuis la dernière trame
     */
    public void mettreAJour(long deltaTemps) {
        for (int i = objetsJeu.size() - 1; i >= 0; i--) {
            if (objetsJeu.get(i).doitEtreDetruit()) {
                objetsJeu.remove(i);  // cet objet de jeu ne sera plus mis à jour, ni dessiné
            }
            else if (objetsJeu.get(i).estActif()) {
                objetsJeu.get(i).mettreAJour(deltaTemps);
            }
        }

        // détection des collisions
        if (objetsJeu.size() >= 2) {
            for (int i = 0; i < objetsJeu.size(); i++) {
                ObjetJeu un = objetsJeu.get(i);
                for (int j = i + 1; j < objetsJeu.size(); j++) {
                    ObjetJeu deux = objetsJeu.get(j);
                    if (un.estActif() && deux.estActif() &&
                        un.getX() == deux.getX() && un.getY() == deux.getY()) {
                        if (un instanceof Collisionnable) {
                            ((Collisionnable) un).gererCollisionAvec(deux);
                        }
                        if (deux instanceof Collisionnable) {
                            ((Collisionnable) deux).gererCollisionAvec(un);
                        }
                    }
                }
            }
        }
    }

    /**
     * Dessine tous les objets de jeu.
     */
    public void dessiner() {
        EKOConsole.effacer();

        for (ObjetJeu objetJeu : objetsJeu) {
            if (objetJeu.estActif()) {
                objetJeu.dessiner();
            }
        }

        EKOConsole.rendre();
    }

    /**
     * Trie les objets de jeu en ordre de profondeur (position z).
     * Les objets avec une position z plus élevée sont dessinés en dernier, donc sur le dessus.
     */
    protected void ordonnerCouches() {
        objetsJeu.sort(Comparator.comparingInt(ObjetJeu::getZ));  // tri les objets en ordre de profondeur
    }

    /**
     * Trouve et retourne les objets de jeu avec l'étiquette spécifiée.
     *
     * @param etiquette Étiquette
     * @return Liste des objets de jeu trouvés
     */
    public ArrayList<ObjetJeu> trouverObjetsJeu(Etiquette etiquette) {
        ArrayList<ObjetJeu> objetsTrouves = new ArrayList<>();

        for (ObjetJeu objetJeu : objetsJeu) {
            if (objetJeu.etiquette == etiquette) {
                objetsTrouves.add(objetJeu);
            }
        }

        return objetsTrouves;
    }

    /**
     * Trouve et retourne un objet de jeu par son nom.
     *
     * @param nom Nom donné à l'objet de jeu
     * @return Objet de jeu si trouvé, null sinon
     */
    public ObjetJeu trouverObjetJeu(String nom) {
        for (ObjetJeu objetJeu : objetsJeu) {
            if (objetJeu.nom != null && objetJeu.nom.equals(nom)) {
                return objetJeu;
            }
        }

        return null;
    }
}

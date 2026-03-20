
import eko.*;

public class Joueur extends ObjetJeu implements Collisionnable  {
    protected static Joueur instance;
    protected final int POINTS_DE_VIE_MAX = 5; //Nombre maximum de vie du joueur
    protected  int pointDeVie; //Point de vie que le joueur a en se moment
    protected Position positionDepart; // Retient la position de depart du joueur
    protected boolean aCle ;
    protected long tempsProchaineAction;
    protected long tempsAction;
    private EKOSon sonMourir;
    private EKOSon sonGameOver;


    /**
     * Permet d'initializer un Object de type Joueur (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    public Joueur(String nom, int x, int y, Etiquette etiquette){
        super(nom, x, y, etiquette);
        this.pointDeVie = POINTS_DE_VIE_MAX;
        this.positionDepart = new Position(x,y,0);
        instance = this;
        aCle = false;
        sonMourir = EKOAudio.charger("JeuAudio/son_mourir.wav");
        sonGameOver = EKOAudio.charger("JeuAudio/game_over_SM.wav");
        tempsProchaineAction = 100;
        tempsAction = 0;


    }

    public boolean aLaCle(){
        return aCle;
    }
    public static Joueur getInstance(){
        return instance;
    }

    /**
     * Toutes les classes qui sont des objets de jeu doivent implémenter la méthode de mise à jour.
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, avant le dessin.
     * Permet de modifier des parametre de l'objet
     *
     * @param deltaTemps Temps écoulé (en millisecondes) depuis la dernière trame
     */
    protected void mettreAJour(long deltaTemps) {
        tempsAction += deltaTemps;
        if (tempsAction >= tempsProchaineAction) {
            tempsAction -= tempsProchaineAction;

            if (EKOTouche.W.estEnfoncee() || EKOTouche.FLECHE_HAUT.estEnfoncee()) this.position.y--;
            if (EKOTouche.S.estEnfoncee() || EKOTouche.FLECHE_BAS.estEnfoncee()) this.position.y++;
            if (EKOTouche.A.estEnfoncee() || EKOTouche.FLECHE_GAUCHE.estEnfoncee()) this.position.x--;
            if (EKOTouche.D.estEnfoncee() || EKOTouche.FLECHE_DROITE.estEnfoncee()) this.position.x++;
        }
    }

    /**
     * Toutes les classes qui sont des objets de jeu doivent implémenter la méthode de mise à jour.
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    public void dessiner(){
        EKOConsole.afficher(this.getX(), this.getY(),etiquette.code, EKOCouleur.JAUNE);
        InterfaceJeu.afficherCoueur(pointDeVie, POINTS_DE_VIE_MAX);
        InterfaceJeu.afficherSalle(GestionnaireNiveaux.obtenir().getNiveauActuel());
    }

    /**
     *  Montre l'ecran de defaite et reinitialize tous les objets du niveau et le nombre de vie du personnage.
     */
    public void reset(){
        InterfaceJeu.defaite();
        pointDeVie = POINTS_DE_VIE_MAX;
        aCle = false;
        PorteExit.setVisible(true);
        this.position = new Position(positionDepart.x, positionDepart.y, 0);
    }

    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu lorsque deux
     * objets sont à la même position.
     * Permet de gerer les collisions entre le joueur et l'objet en contact.
     *
     * @param autre Autre objet de jeu impliqué dans la collision
     */
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Mur) {
            //On inverse les commandes de "mettreAJour" pour faire comme si le personnage n'a pas bouger.
            if (EKOTouche.W.estEnfoncee() || EKOTouche.FLECHE_HAUT.estEnfoncee()) this.position.y++;
            if (EKOTouche.S.estEnfoncee() || EKOTouche.FLECHE_BAS.estEnfoncee()) this.position.y--;
            if (EKOTouche.A.estEnfoncee() || EKOTouche.FLECHE_GAUCHE.estEnfoncee()) this.position.x++;
            if (EKOTouche.D.estEnfoncee() || EKOTouche.FLECHE_DROITE.estEnfoncee()) this.position.x--;
        } else if (autre instanceof Grenouille) {
            /*On verifie que le personnage est toujours vivant avant d'executer l'action. S'il n'y a plus de vie,
            on declanche la methode qui recommence le niveau.*/
            if (pointDeVie > 1) {
                pointDeVie--;
                EKOAudio.jouer(sonMourir);
                this.position = new Position(positionDepart.x, positionDepart.y, 0);
            } else {
                EKOAudio.jouer(sonGameOver);
                reset();
            }
        } else if (autre instanceof Feu) {
            if (pointDeVie > 1) {
                pointDeVie--;
                EKOAudio.jouer(sonMourir);
                this.position = new Position(positionDepart.x, positionDepart.y, 0);
            } else {
                EKOAudio.jouer(sonGameOver);
                reset();
            }
        } else if (autre instanceof Insecte) {
            if (pointDeVie > 1) {
                pointDeVie--;
                EKOAudio.jouer(sonMourir);
                this.position = new Position(positionDepart.x, positionDepart.y, 0);
            } else {
                EKOAudio.jouer(sonGameOver);
                reset();
            }
        } else if (autre instanceof Fantome) {
            if (pointDeVie > 1) {
                pointDeVie--;
                EKOAudio.jouer(sonMourir);
                this.position = new Position(positionDepart.x, positionDepart.y, 0);
            } else {
                EKOAudio.jouer(sonGameOver);
                reset();
            }
        } else if (autre instanceof PorteExit) {
            //Commentaire ici
            if (aLaCle()) {
                GestionnaireNiveaux.obtenir().chargerNiveauSuivant();
            } else {
                if (EKOTouche.W.estEnfoncee() || EKOTouche.FLECHE_HAUT.estEnfoncee()) position.y++;
                if (EKOTouche.S.estEnfoncee() || EKOTouche.FLECHE_BAS.estEnfoncee()) position.y--;
                if (EKOTouche.A.estEnfoncee() || EKOTouche.FLECHE_GAUCHE.estEnfoncee()) position.x++;
                if (EKOTouche.D.estEnfoncee() || EKOTouche.FLECHE_DROITE.estEnfoncee()) position.x--;
            }
        } else if (autre instanceof PorteEntrer) {
            if (EKOTouche.W.estEnfoncee() || EKOTouche.FLECHE_HAUT.estEnfoncee()) position.y++;
            if (EKOTouche.S.estEnfoncee() || EKOTouche.FLECHE_BAS.estEnfoncee()) position.y--;
            if (EKOTouche.A.estEnfoncee() || EKOTouche.FLECHE_GAUCHE.estEnfoncee()) position.x++;
            if (EKOTouche.D.estEnfoncee() || EKOTouche.FLECHE_DROITE.estEnfoncee()) position.x--;
        }else if(autre instanceof Potion){
            //Commentaire ici
            pointDeVie = POINTS_DE_VIE_MAX;
            ((Potion) autre).jouerSon();
            autre.desactiver();
        } else if(autre instanceof Clef){
            //On deverouille la porte pour enclancher la methode "mettreAJour" de "PorteExit" qui enleve la porte.
            //GestionnaireObjetsJeu.obtenir().trouverObjetJeu("PorteExit").desactiver();
            this.aCle = true;
            PorteExit.setVisible(false);
            Clef.activerSon();
            autre.desactiver();

        } else if (autre instanceof Squelette) {
            if (pointDeVie > 1) {
                pointDeVie--;
                this.position = new Position(positionDepart.x, positionDepart.y, 0);
            } else {
                EKOAudio.jouer(sonGameOver);
                reset();
            }
        }

    }
}
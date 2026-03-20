public abstract class ObjetJeu {
    protected String nom;
    protected Etiquette etiquette;
    protected Position position;

    protected boolean actif;
    protected boolean detruire;

    /**
     * Construit et enregistre un objet de jeu.
     *
     * @param nom Nom donné à l'objet de jeu
     * @param x La position x à l'écran (la colonne)
     * @param y La position y à l'écran (la colonne)
     */
    protected ObjetJeu(String nom, int x, int y) {
        this(nom, x, y, null);
    }

    /**
     * Construit et enregistre un objet de jeu.
     *
     * @param nom Nom donné à l'objet de jeu
     * @param x La position x à l'écran (la colonne)
     * @param y La position y à l'écran (la colonne)
     * @param etiquette etiquette
     */
    protected ObjetJeu(String nom, int x, int y, Etiquette etiquette) {
        this.nom = nom;
        this.position = new Position(x, y, 0);
        this.etiquette = etiquette;
        this.detruire = false;
        this.actif = true;
        GestionnaireObjetsJeu.obtenir().enregistrer(this);
    }

    /**
     * Toutes les classes qui sont des objets de jeu doivent implémenter la méthode de mise à jour.
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, avant le dessin.
     *
     * @param deltaTemps Temps écoulé (en millisecondes) depuis la dernière trame
     */
    protected abstract void mettreAJour(long deltaTemps);

    /**
     * Toutes les classes qui sont des objets de jeu doivent implémenter la méthode de dessin.
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     */
    protected abstract void dessiner();

    /**
     * Détruit cet objet de jeu : il sera libéré du gestionnaire des objets de jeu.
     */
    public void detruire() {
        this.actif = false;
        this.detruire = true;
    }

    /**
     * Active l'objet de jeu : il sera mis à jour et dessiné.
     */
    public void activer() { this.actif = true; }

    /**
     * Désactive l'objet de jeu : il ne sera plus mis à jour, ni dessiné.
     */
    public void desactiver() { this.actif = false; }

    public boolean doitEtreDetruit() { return this.detruire; }
    public boolean estActif() { return this.actif; }

    // accesseurs disponibles pour les tris
    public int getX() { return this.position.x; }
    public int getY() { return this.position.y; }
    public int getZ() { return this.position.z; }

    public void setZ(int z) {
        this.position.z = z;
        GestionnaireObjetsJeu.obtenir().ordonnerCouches();
    }
}

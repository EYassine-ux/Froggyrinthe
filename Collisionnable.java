public interface Collisionnable {

    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu lorsque deux
     * objets sont à la même position.
     *
     * @param autre Autre objet de jeu impliqué dans la collision
     */
    void gererCollisionAvec(ObjetJeu autre);
}

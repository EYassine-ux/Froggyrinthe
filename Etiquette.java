public enum Etiquette {
    CLEF("\uF084"),
    POTION("\uF0C3"),
    FEU("\uE3BF"),
    FANTOM("\uEEFE"),
    SQUELETTE("\uEE15"),
    INSCECTE("\uF188"),
    GRENOUILLE("\uEDF8"),
    MILLIEU_LANGUE_GRENOUILLE("\u2500"),
    FIN_LANGUE_GRENOUILLE("\u257C"),
    MUR("\u2588"),
    PORTE_HAUT("\u2501"),
    PORTE_COTE("\u2503"),
    PERSONNAGE("\uF4FF"),
    COEUR("\uEC04"),
    COEUR_VIDE("\uF08A");

    public String code;

    /**
     * Permet d'utiliser le code "String" de chaque constante.
     * @param code le "String" de la constante.
     */
    private Etiquette(String code){
        this.code = code;
    }
}
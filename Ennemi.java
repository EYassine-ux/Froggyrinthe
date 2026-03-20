public abstract class Ennemi extends ObjetJeu {

    protected long tempsProchaineAction;
    protected long tempsAction;

    protected Ennemi(String nom, int x, int y) {
        super(nom, x, y);
    }

    protected Ennemi(String nom, int x, int y, Etiquette etiquette) {
        super(nom, x, y, etiquette);
    }

    public abstract void executerAction();

    public void mettreAJour(long deltaTemps){
        tempsAction += deltaTemps;
        if(tempsAction >= tempsProchaineAction){
            tempsAction -= tempsProchaineAction;
            executerAction();
        }
    }
}

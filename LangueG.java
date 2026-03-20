import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;

public class LangueG extends Grenouille {
    private EKOChaine chaine;
    private int numeroAction = 0;

    /**
     * Permet d'initializer un Object de type LangueG(renouille) (Constructeur)
     *
     * @param nom
     * @param x
     * @param y
     * @param etiquette
     * @param retourner
     */
    protected LangueG(String nom, int x, int y, Etiquette etiquette, boolean retourner) {
        super(nom, x, y, etiquette, retourner);
        if(retourner){
            chaine = new EKOChaine(this.etiquette.code, EKOCouleur.ROUGE);
            chaine.retourner();
        } else {
            chaine = new EKOChaine(this.etiquette.code, EKOCouleur.ROUGE);
        }
        this.desactiver();
    }

    /**
     * Permet de compter les actions a faire et retourner le nombre lui meme
     * @return numero de l'action a faire
     */
    protected int getNumeroAction(){
        numeroAction++;
        if(numeroAction == 7) {
            numeroAction = 1;
        }
        return numeroAction;
    }

    /**
     * On attribue une nouvelle valeur a la position x de la langue
     * @param x la nouvelle position x de la langue
     */
    protected void setX(int x){
        this.position.x = x;
    }

    /**
     * Retourne la position de la langue.
     * @return position x de la langue
     */
    public int getX(){
        return this.position.x;
    }

    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    @Override
    protected void dessiner() {
        EKOConsole.afficher(this.getX(), this.getY(), chaine);
    }
}

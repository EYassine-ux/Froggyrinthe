import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;

public class Squelette extends Ennemi{
    private EKOChaine chaine;
    private int bestDirectionX=-1;
    private int bestDirectionY=-1;
    private boolean goUpNext = false;
    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    protected Squelette(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Permet d'initializer un Object de type Joueur (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected Squelette(String nom, int x, int y, Etiquette etiquette) {
        super(nom, x, y, etiquette);
        chaine = new EKOChaine(this.etiquette.code, EKOCouleur.BLANC);
        tempsProchaineAction = 400;
        tempsAction = 0;
    }

    /**
     * Permet d'executer l'action de l'ennemi.
     */
    @Override
    public void executerAction() {
            SqueletteMovement(Joueur.getInstance().getX(), Joueur.getInstance().getY());

    }
    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    @Override
    protected void dessiner() {
        EKOConsole.afficher(this.getX(), this.getY(),chaine);
    }

    public void SqueletteMovement(int x1, int y1){
        int x=getX();
        int y=getY();
        if(x1 > x && !LectureCarte.estBloquantMur(x+1,y)){
                this.position.x++;
        }else if(x1 < x && !LectureCarte.estBloquantMur(x-1,y)){
                this.position.x--;
        }else if (y1 > y&&!LectureCarte.estBloquantMur(x,y+1)){
                this.position.y++;
        }else if(y1 < y &&!LectureCarte.estBloquantMur(x,y-1)){
                this.position.y--;
        }

    }

}






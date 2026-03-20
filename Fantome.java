import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;

public class Fantome extends Ennemi implements Collisionnable{
    private EKOChaine chaine;
    protected boolean horizontal;
    protected boolean changementDirection;

    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    protected Fantome(String nom, int x, int y,  boolean direction) {
        super(nom, x, y);
        this.horizontal = direction;
        this.changementDirection = true;
    }

    /**
     * Permet d'initializer un Object de type Joueur (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected Fantome(String nom, int x, int y, Etiquette etiquette, boolean direction) {
        super(nom, x, y, etiquette);
        this.horizontal = direction;
        this.changementDirection = true;
        chaine = new EKOChaine(this.etiquette.code, EKOCouleur.RVB(255,140,0));
        if(!direction){
            tempsProchaineAction = 50;
            tempsAction = 0;
        } else {
            tempsProchaineAction = 100;
            tempsAction = 0;
        }
    }

    /**
     * Permet d'executer l'action de l'ennemi.
     */
    @Override
    public void executerAction() {
        if(horizontal){
            if(changementDirection){
                this.position.x++;
            } else {
                this.position.x--;
            }
        } else {
            if(changementDirection){
                this.position.y--;
            } else {
                this.position.y++;
            }
        }
    }

    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    @Override
    protected void dessiner() {
        EKOConsole.afficher(this.getX(), this.getY(),chaine);
    }

    @Override
    public void mettreAJour(long deltaTemps){
        super.mettreAJour(deltaTemps);
    }
    @Override
    public void gererCollisionAvec(ObjetJeu autre) {
        if (autre instanceof Mur || autre instanceof Porte) {
            if(changementDirection){
                if(horizontal){
                    if(changementDirection){
                        this.position.x--;
                    } else {
                        this.position.x++;
                    }
                } else {
                    if(changementDirection){
                        this.position.y++;
                    } else {
                        this.position.y--;
                    }
                }
                changementDirection = false;
            } else {
                if(horizontal){
                    if(changementDirection){
                        this.position.x--;
                    } else {
                        this.position.x++;
                    }
                } else {
                    if(changementDirection){
                        this.position.y++;
                    } else {
                        this.position.y--;
                    }
                }
                changementDirection = true;
            }

        }
    }
}

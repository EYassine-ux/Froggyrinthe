import eko.EKO;
import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;

public class Grenouille extends Ennemi {
    private EKOChaine chaine;
    private LangueG lFin;
    private LangueG lMillieu1;
    private LangueG lMillieu2;
    protected boolean retourner;

    /**
     * Permet d'initializer un Object de type Grenouille (Constructeur)
     *
     * @param nom       Le nom de l'object
     * @param x         Coordonnee x de l'objet
     * @param y         Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected Grenouille(String nom, int x, int y, Etiquette etiquette, boolean retourner) {
        super(nom, x, y, etiquette);
        this.retourner = retourner;
        if(retourner){
            chaine = new EKOChaine(this.etiquette.code, EKOCouleur.VERT);
            chaine.retourner();
        } else {
            chaine = new EKOChaine(this.etiquette.code, EKOCouleur.VERT);
        }

        tempsProchaineAction = 750;
        tempsAction = 250;
    }

    /**
     * Permet d'executer l'action de l'ennemi.
     */
    @Override
    public void executerAction() {
        /*Durant l'execution, on doit verifier que les 3 parties de la langue ne sont pas nulles, car parfois elles
        * le sont et plante le code. Cette condition arrete les possibilite que le code se plante*/
        if (lFin != null || lMillieu1 != null || lMillieu2 != null) {
            //On verifie si la grenouille et retourner ou pas, et chacun a ces propres instruction.
            if (!retourner) {
                int temp = lFin.getNumeroAction();
                toutDetruire(temp);
                if (temp == 1) {
                    lFin.setX(lFin.getX() + 1);
                    lMillieu1.setX(lMillieu1.getX() + 1);
                    lMillieu2.setX(lMillieu2.getX() + 1);
                } else if (temp == 2) {
                    lFin.setX(lFin.getX() + 1);
                    lMillieu1.setX(lMillieu1.getX() + 1);
                } else if (temp == 3) {
                    lFin.setX(lFin.getX() + 1);
                } else if (temp == 4) {
                    lFin.setX(lFin.getX() - 1);
                } else if (temp == 5) {
                    lFin.setX(lFin.getX() - 1);
                    lMillieu1.setX(lMillieu1.getX() - 1);
                } else if (temp == 6) {
                    lFin.setX(lFin.getX() - 1);
                    lMillieu1.setX(lMillieu1.getX() - 1);
                    lMillieu2.setX(lMillieu2.getX() - 1);
                }
                activation(temp);
            } else {
                int temp = lFin.getNumeroAction();
                toutDetruire(temp);
                if (temp == 1) {
                    lFin.setX(lFin.getX() - 1);
                    lMillieu1.setX(lMillieu1.getX() - 1);
                    lMillieu2.setX(lMillieu2.getX() - 1);
                } else if (temp == 2) {
                    lFin.setX(lFin.getX() - 1);
                    lMillieu1.setX(lMillieu1.getX() - 1);
                } else if (temp == 3) {
                    lFin.setX(lFin.getX() - 1);
                } else if (temp == 4) {
                    lFin.setX(lFin.getX() + 1);
                } else if (temp == 5) {
                    lFin.setX(lFin.getX() + 1);
                    lMillieu1.setX(lMillieu1.getX() + 1);
                } else if (temp == 6) {
                    lFin.setX(lFin.getX() + 1);
                    lMillieu1.setX(lMillieu1.getX() + 1);
                    lMillieu2.setX(lMillieu2.getX() + 1);
                }
                activation(temp);
            }
        }
    }

    /**
     * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
     * Permet d'afficher l'objet sur la console EKO
     */
    @Override
    protected void dessiner() {
        EKOConsole.afficher(this.getX(), this.getY(), chaine);
    }

    /**
     * Permet de desactiver selectivement les objects de la langue
     * @param temp le nombre de l'etape (determine ce qui doit etre desactiver)
     */
    protected void toutDetruire(int temp) {
        switch (temp) {
            case 1:
            case 6:
                if (lMillieu2 != null) {
                    lMillieu2.desactiver();
                }
                if (temp == 6) {
                    lFin.desactiver();
                }
            case 2:
            case 5:
                if (lMillieu1 != null) {
                    lMillieu1.desactiver();
                }
            case 3:
            case 4:
                if (lFin != null) {
                    lFin.desactiver();
                }
                break;
            case 0:
                if (lFin != null) {
                    lFin.desactiver();
                }
        }
    }

    /**
     * Permet d'activer selectivement les objects de la langue
     * @param temp le nombre de l'etape (determine ce qui doit etre activer)
     */
    protected void activation(int temp) {
        switch (temp) {
            case 3:
            case 4:
                lMillieu2.activer();
            case 2:
                lMillieu2.activer();
            case 5:
                lMillieu1.activer();
            case 1:
                lFin.activer();
                break;
        }
    }

    /**
     * Permet d'instancier les trois partie de la langue avec l'object respectif de la class "LectureCarte"
     * @param lg l'object de langue qui est instancier
     */
    protected void instasierObj(LangueG lg) {
        if (lg.nom.equals("LangueFin")) {
            lFin = new LangueG(lg.nom, lg.getX(), lg.getY(), lg.etiquette, lg.retourner);
        } else if (lg.nom.equals("LangueMillieu1")) {
            lMillieu1 = new LangueG(lg.nom, lg.getX(), lg.getY(), lg.etiquette, lg.retourner);
        } else if (lg.nom.equals("LangueMillieu2")) {
            lMillieu2 = new LangueG(lg.nom, lg.getX(), lg.getY(), lg.etiquette, lg.retourner);
        }
    }
}

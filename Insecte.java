import eko.EKOChaine;
import eko.EKOConsole;
import eko.EKOCouleur;

public class Insecte extends Ennemi {
    private EKOChaine chaine;
    private Direction direction;

    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    protected Insecte(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Permet d'initializer un Object de type Insecte (Constructeur)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     * @param etiquette Ethiquette de l'objet
     */
    protected Insecte(String nom, int x, int y, Etiquette etiquette) {
        super(nom, x, y, etiquette);
        chaine = new EKOChaine(this.etiquette.code, EKOCouleur.VERT);

        // cette condition assure la direction de l'insect par rapport a le mur
        if(LectureCarte.estBloquantMur(getX(), getY()-1)){
            this.direction = Direction.DROITE;
        } else if (LectureCarte.estBloquantMur(getX(), getY()+1)) {
            this.direction = Direction.GAUCHE;
        }else if (LectureCarte.estBloquantMur(getX()+1, getY())) {
            this.direction = Direction.HAUT;
        }else if (LectureCarte.estBloquantMur(getX()-1, getY())) {
            this.direction = Direction.BAS;
        }else{
            this.direction = Direction.DROITE;
        }
        tempsProchaineAction = 800;
        tempsAction = 0;
    }

    /**
     * Permet d'executer l'action de l'ennemi.
     */
    @Override
    public void executerAction() {
        verifierMurPorte();
        switch(direction){
            case DROITE -> position.x++;
            case GAUCHE -> position.x--;
            case HAUT -> position.y--;
            case BAS -> position.y++;
        }
    }
        /**
         * Cette méthode est appelée par le gestionnaire des objets de jeu à chaque trame, après la mise à jour.
         * Permet d'afficher l'objet sur la console EKO
         */
        @Override
        protected void dessiner () {
            EKOConsole.afficher(this.getX(), this.getY(), chaine);

        }
        /*
         Cette méthode change la direction de l'insect par rapport au mur et porte
         */
        public void verifierMurPorte(){
            int x = getX();
            int y = getY();
            /* Tous ses boolean c'est pour verifier si il y a un mur ou une porte dans la position donner
                pour faciliter la logic de mouvement de l'insect par rapport a chaque mur et porte
             */
            boolean murHaut = LectureCarte.estBloquantMur(x, y-1);
            boolean murBas = LectureCarte.estBloquantMur(x, y+1);
            boolean murDroite = LectureCarte.estBloquantMur(x+1, y);
            boolean murGauche = LectureCarte.estBloquantMur(x-1, y);
            boolean murBasDroite = LectureCarte.estBloquantMur(x+1, y+1);
            boolean murHautDroite = LectureCarte.estBloquantMur(x+1, y-1);
            boolean murBasGauche = LectureCarte.estBloquantMur(x-1, y+1);
            boolean murHautGauche = LectureCarte.estBloquantMur(x-1, y-1);
            boolean porteBasDroite = LectureCarte.estBloquantPorte(x+1, y+1);
            boolean porteHautDroite = LectureCarte.estBloquantPorte(x+1, y-1);
            boolean porteBasGauche = LectureCarte.estBloquantPorte(x-1, y+1);
            boolean porteHautGauche = LectureCarte.estBloquantPorte(x-1, y-1);
            /*
            pour cette switch on donne des conditions pour chaque direction puisque chaque direction a sa logic de mouvement
            */
            switch(direction){
                case DROITE -> {
                                /*example si le mur en haut et mur en droite alors l insect doit tourner vers le bas
                                  pour les autres condition la meme logic applique juste un changement de position de mur
                                 */
                                if(murHaut && murDroite) this.direction = Direction.BAS;
                                    else if(porteHautDroite || porteBasDroite) this.direction = Direction.GAUCHE;
                                    else if( murBas && murDroite) this.direction = Direction.HAUT;
                                    else if(murBasGauche && !(murBas || murHaut)) this.direction = Direction.BAS;
                                    else if(murHautGauche && !(murBas || murHaut)) this.direction = Direction.HAUT;

                }
                case GAUCHE -> { if(murBas && murGauche) this.direction = Direction.HAUT;
                                    else if(porteBasGauche || porteHautGauche) this.direction = Direction.DROITE;
                                    else if( murGauche && murHaut) this.direction = Direction.BAS;
                                    else if(murBasDroite && !murBas) this.direction = Direction.BAS;
                                    else if(murHautDroite && !(murBas||murHaut)) this.direction = Direction.HAUT;

                }
                case HAUT -> { if(murDroite && murHaut) this.direction = Direction.GAUCHE;
                                else if( murHaut && murGauche) this.direction = Direction.DROITE;
                                else if (porteHautDroite || porteHautGauche) this.direction = Direction.BAS;
                                else if(murBasDroite && !(murGauche || murDroite)) this.direction = Direction.DROITE;
                                else if(murBasGauche && !(murGauche || murDroite)) this.direction = Direction.GAUCHE;

                }
                case BAS -> { if(murDroite && murBas) this.direction = Direction.GAUCHE;
                                 else if(porteBasDroite || porteBasGauche) this.direction = Direction.HAUT;
                                 else if( murBas && murGauche) this.direction = Direction.DROITE;
                                 else if(murHautDroite && !(murDroite || murGauche)) this.direction = Direction.DROITE;
                                 else if(murHautGauche && !(murDroite || murGauche)) this.direction = Direction.GAUCHE;


                }
            }
        }
    }







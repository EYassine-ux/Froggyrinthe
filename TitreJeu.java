import eko.EKO;
import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;

public abstract class TitreJeu extends Intro{
    /**
     * Premier constructeur de l'objet (On ne l'utilise pas)
     * @param nom Le nom de l'object
     * @param x Coordonnee x de l'objet
     * @param y Coordonnee x de l'objet
     */
    protected TitreJeu(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Cette methode permet d'afficher le logo et le nom du jeux.
     * Cette methode attend aussi jusqu'a ce que l'utilisateur appuis sur "ENTER" pour quitter la methode.
     */
    public static void jouerTitre(){
        int x = (EKOConsole.largeur()) /2 ;
        int y = EKOConsole.hauteur();
        EKO.attendre(500);


        //Dessin de la grenouille divise
        final String FROGGYRINTHELOGO1 = " @..@\n";
        final String FROGGYRINTHELOGO2 = " (----)\n";
        final String FROGGYRINTHELOGO3 = " ( >__< )\n";
        final String FROGGYRINTHELOGO4 = " ^^~~^^\n";
        final String FROGGYRINTHELOGO5 = "FROGGYRINTHE";

        //Apparition du logo
        int alpha;
        for (alpha = 0; alpha < 255; alpha += 2) {
            /*Chaque 2 lignes est comme suivie: le texte noir fait une apparition. Apres on intensifie
            "Alpha" de chaque partie du texte pour faire une apparition plus fondue*/
            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO1.length()) / 2, EKOConsole.hauteur() / 2 - 2, FROGGYRINTHELOGO1, EKOCouleur.NOIR);
            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO1.length()) / 2, EKOConsole.hauteur() / 2 - 2, FROGGYRINTHELOGO1, EKOCouleur.RVB(128, 128, 128, alpha));

            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO2.length()) / 2, EKOConsole.hauteur() / 2 - 1, FROGGYRINTHELOGO2, EKOCouleur.NOIR);
            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO2.length()) / 2, EKOConsole.hauteur() / 2 - 1, FROGGYRINTHELOGO2, EKOCouleur.RVB(128, 128, 128, alpha));

            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO3.length()) / 2, EKOConsole.hauteur() / 2, FROGGYRINTHELOGO3, EKOCouleur.NOIR);
            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO3.length()) / 2, EKOConsole.hauteur() / 2, FROGGYRINTHELOGO3, EKOCouleur.RVB(128, 128, 128, alpha));

            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO4.length()) / 2, EKOConsole.hauteur() / 2 + 1, FROGGYRINTHELOGO4, EKOCouleur.NOIR);
            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO4.length()) / 2, EKOConsole.hauteur() / 2 + 1 , FROGGYRINTHELOGO4, EKOCouleur.RVB(128, 128, 128, alpha));

            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO5.length()) / 2, EKOConsole.hauteur() / 2 + 2, FROGGYRINTHELOGO5, EKOCouleur.NOIR);
            EKOConsole.afficher((EKOConsole.largeur() - FROGGYRINTHELOGO5.length()) / 2, EKOConsole.hauteur() / 2 + 2, FROGGYRINTHELOGO5, EKOCouleur.RVB(0, 128, 0, alpha));
            EKOConsole.rendre();
            EKO.attendre(10);
        }

        // apparition de message pour continuer
        String appuyez = "Appuyez sur ESPACE pour continuer";
        for (alpha = 0; alpha < 255; alpha += 2) {
            EKOConsole.afficher((EKOConsole.largeur() - appuyez.length()) / 2, EKOConsole.hauteur() - 3, appuyez, EKOCouleur.NOIR);
            EKOConsole.afficher((EKOConsole.largeur() - appuyez.length()) / 2, EKOConsole.hauteur() - 3, appuyez, EKOCouleur.RVB(128, 128, 128, alpha));
            EKOConsole.rendre();
            EKO.attendre(10);
        }
        while (!EKOTouche.ESPACE.estEnfoncee()) {
            EKO.attendre(100);
        }
        EKOConsole.effacer();


    }
}

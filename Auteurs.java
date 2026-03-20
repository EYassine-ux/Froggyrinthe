import eko.EKO;
import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;

public abstract class Auteurs extends Intro {
    /**
     * Permet d'initializer un Object de type Auteurs (Constructeur).
     * Le contructeur est prive qui permet d'eviter la creations d'instance et d'objet.
     */
    private Auteurs(String nom, int x, int y) {
        super(nom, x, y);
    }

    /**
     * Cette methode permet d'afficher les noms des createur du jeux sur la console EKO
     */
    public static void jouerAutheurs() {
        String nomEtudiant = "Yassine E. et Eric Z.";
        int x = (EKOConsole.largeur() - nomEtudiant.length()) /2 ;
        int y = EKOConsole.hauteur();
        EKO.attendre(500);

        //Apparition des noms
        int alpha;
        for (alpha = 0; alpha < 255; alpha += 2) {
            //Boucle qui affiche de plus en plus les nom sur la console

            //Boucle qui affiche de plus en plus les nom sur la console
            EKOConsole.afficher((EKOConsole.largeur() - nomEtudiant.length()) / 2, EKOConsole.hauteur() / 2, nomEtudiant, EKOCouleur.NOIR);
            EKOConsole.afficher((EKOConsole.largeur() - nomEtudiant.length()) / 2, EKOConsole.hauteur() / 2, nomEtudiant, EKOCouleur.RVB(128, 128, 128, alpha));
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
        //Efface les noms de la console
        EKOConsole.effacer();
    }
}

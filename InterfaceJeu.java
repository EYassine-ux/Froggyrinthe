import eko.EKO;
import eko.EKOConsole;
import eko.EKOCouleur;
import eko.EKOTouche;

public class InterfaceJeu {
    private static String[] nomObjectJeu = new String[]{"Joueur", "Mur", "Porte","PorteExit","PorteEntrer", "Feu",
            "Fantome","Insecte","Grenouille", "Squelette", "Clef", "Potion"};

    /**
     * Permet d'afficher l'ecran de victoire lorsque le joueur termine le jeu.
     */
    public static void victoire() {
        //Message de defaite
        String text = "Vous avez gagnié!";
        EKOConsole.effacer();
        EKO.attendre(500);
        EKOConsole.afficher((EKOConsole.largeur() - text.length()) / 2, EKOConsole.hauteur() / 2, text, EKOCouleur.JAUNE);
        EKOConsole.rendre();
        EKO.attendre(6300);
        System.exit(0);
    }

    /**
     * Permet d'afficher le nombre de coeurs qui reste au joueur.
     * @param coeur le nombre de coeur
     * @param POINT_DE_VIE_MAX le nombre maximum de coeur que peux avoir le personnage
     */
    public static void afficherCoueur(int coeur, int POINT_DE_VIE_MAX){
        for(int i = 0; i < coeur; i++){
            EKOConsole.afficher(i,0,Etiquette.COEUR.code,EKOCouleur.ROUGE);
        }
        for(int j = POINT_DE_VIE_MAX; j > coeur; j--){
            EKOConsole.afficher(j-1,0,Etiquette.COEUR_VIDE.code,EKOCouleur.GRIS_FONCE);
        }
        EKOConsole.rendre();
    }

    /**
     * Permet d'afficher le numero de salle.
     * @param niveau le numero du niveau
     */
    public static void afficherSalle(int niveau){
        String numSalle = "Salle ";
        if(niveau <= 9) {
            numSalle += "0" + niveau;
        } else {
            numSalle += niveau;
        }
        EKOConsole.afficher(EKOConsole.largeur() - numSalle.length() - 1,0,numSalle,EKOCouleur.GRIS_FONCE);
    }

    /**
     * Permet d'afficher l'ecran de defaite lorsque le joueur est mort et n'a plus de vie.
     * Permet aussi de reinitialiser tous les objets du niveau en les activant.
     */
    public static void defaite(){
        //Message de defaite
        String text = "Vous avez échoué!";
        EKOConsole.effacer();
        EKO.attendre(500);
        EKOConsole.afficher((EKOConsole.largeur() - text.length()) / 2, EKOConsole.hauteur() / 2, text, EKOCouleur.JAUNE);
        EKOConsole.rendre();
        EKO.attendre(1000);
        String appuyez = "Appuyez sur ESPACE pour rejoué!";
        EKOConsole.afficher((EKOConsole.largeur() - appuyez.length()) / 2, EKOConsole.hauteur() - 3, appuyez, EKOCouleur.JAUNE);
        EKOConsole.rendre();
        while (!EKOTouche.ESPACE.estEnfoncee()) {
            EKO.attendre(100);
        }

        //Active tous les objets du niveau
        for(String nomObj : nomObjectJeu) {
            ObjetJeu obj = GestionnaireObjetsJeu.obtenir().trouverObjetJeu(nomObj);
            if(obj != null){
                obj.activer();
                if(obj.nom.equals("PorteExit")){
                    PorteExit.setFermer(true);
                }
            }
        }
    }

}

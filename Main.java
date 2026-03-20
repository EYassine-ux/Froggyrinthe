import eko.EKO;
import eko.EKOConsole;

public class Main {

    private static final int TPS = 30; // trames par seconde (FPS – Frames Per Second)
    private static final long MS_PAR_TRAME = 1000 / TPS; // temps par trame (en millisecondes)

    public static void main(String[] args) {
        GestionnaireNiveaux.obtenir().chargerNiveau(1);
        Intro.jouer();


        //Code a ne pas toucher
        long tempsAttente;
        long maintenant;
        long deltaTemps;
        long dernierTemps = System.nanoTime();
        while (true) {
            maintenant = System.nanoTime();
            deltaTemps = maintenant - dernierTemps;
            dernierTemps = maintenant;
            GestionnaireObjetsJeu.obtenir().mettreAJour(deltaTemps / 1_000_000);
            GestionnaireObjetsJeu.obtenir().dessiner();
            tempsAttente = MS_PAR_TRAME - (System.nanoTime() - dernierTemps) / 1_000_000;
            if (tempsAttente > 0) {
                EKO.attendre(tempsAttente);
            }
        }
    }
}
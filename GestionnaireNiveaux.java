import eko.*;
public class GestionnaireNiveaux {
    private static GestionnaireNiveaux instance;
    private int niveauActuel ;
    private final int NVIEAU_MAX = 5;
    private boolean ekoInitialisateur = false;
    private boolean musicJouer = false;
    private EKOSon music;
    private EKOSon victoire;

    private GestionnaireNiveaux() {}

    /**
     * Retourne l'instance de GestionnaireNiveau
     * @return gestionnaireNiveau
     */
    public static GestionnaireNiveaux obtenir() {
        if (instance == null) instance = new GestionnaireNiveaux();
        return instance;
    }

    /**
     * Charger le niveau a partir de son fichier.
     * @param numeroNiveau le numero du niveau
     */
    public void chargerNiveau(int numeroNiveau){

        GestionnaireObjetsJeu.obtenir().reinitialiser();
        LectureCarte lc = new LectureCarte("Carte/map"+numeroNiveau+".txt",numeroNiveau);
        if (!ekoInitialisateur) {

            EKO.initialiser("Froggyrinthe", lc.carte[0].length, lc.carte.length + 1);
            EKOConsole.icone("Logo/FroggyrintheNOIRE.png");
            ekoInitialisateur = true;
        }
        if(!musicJouer){
            music = EKOAudio.charger("JeuAudio/GameMap1Music.wav");
            EKOAudio.jouer(music,true);
            musicJouer = true;
        }

        niveauActuel = numeroNiveau;
    }

    /**
     * Charger le prochain niveau et verifier qu'on a pas fini le jeu au complet.
     */
    public void chargerNiveauSuivant(){
        if(niveauActuel != NVIEAU_MAX){
            chargerNiveau(niveauActuel+1);
        } else {
            victoire();
        }
    }

    /**
     * Action quand le jeu est fini.
     */
    private void victoire(){
        EKOAudio.arreter(music);
        victoire = EKOAudio.charger("JeuAudio/son_victoire_SM.wav");
        EKOAudio.jouer(victoire);
        InterfaceJeu.victoire();
    }

    /**
     * retourne le numero du niveau actuell
     * @return numero de niveau
     */
    public int getNiveauActuel() {
        return niveauActuel;
    }

}

import eko.EKOCouleur;

import java.io.Closeable;
    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class LectureCarte {
        protected String nom;
        public static String[][] carte;
        public Position player;

        /**
         * Permet d'initializer un Object de type LecteurCarte (Constructeur)
         *
         * @param nom       Le chemin dans les dossiers du fichers ".txt" qui contient le niveau a dessiner
         * @param mapNombre Le nombre du niveau (1,2,3,4,5)
         */
        LectureCarte(String nom, int mapNombre) {
            this.nom = nom;
            getLecture();

        }

        /**
         * Permet de lire le fichier ".txt" qui contient le niveau a dessiner.
         * On stock aussi les elements lu dans un tableau 2D
         */
        public void getLecture() {
            //Debut lecture fichier
            FileInputStream fichier = ouvrirFichierLecture(nom);
            Scanner lecteurFichier = new Scanner(fichier);
            ArrayList<String> ligneList = new ArrayList<>();
            while (lecteurFichier.hasNext()) {
                String ligne = lecteurFichier.nextLine();
                ligneList.add(ligne);
            }
            fermerFichier(fichier);
            //Fin lecture fichier

            //On insert les donnes de l'ArrayList dans le tableau 2D
            int ligneCarte = ligneList.size();
            carte = new String[ligneCarte][ligneList.get(0).length()];
            for (int i = 0; i < ligneCarte; i++) {
                for (int j = 0; j < ligneList.get(i).length(); j++) {
                    String caractere = String.valueOf(ligneList.get(i).charAt(j));
                    carte[i][j] = caractere;
                }
            }
            afficherCarte();
        }

        /**
         * Permet de cree des objets qui seront ajouter au ArrayList de "GestionnaireObjectsJeu", dessiner ces
         * objets plus tard et debuter le son du jeux qui boucle.
         */
        public void afficherCarte() {
            /*Boucle embriquer pour parcourir le tableau 2D et cree les objets a partir des lignes et colones du tableau
            comme coordonne x et y des objets*/
            for (int i = 0; i < carte.length; i++) {
                for (int j = 0; j < carte[i].length; j++) {
                    if (carte[i][j] != null && !carte[i][j].isEmpty()) {
                        switch (carte[i][j]) {
                            case "A":
                                Joueur J = new Joueur("Joueur", j, i + 1, Etiquette.PERSONNAGE);
                                break;
                            case "#":
                                Mur m = new Mur("Mur", j, i + 1, Etiquette.MUR);
                                break;
                            case "-":
                                //On verifie ou est place la porte
                                if (i == 0 || i == carte.length - 1) {
                                    Porte P = new PorteExit("PorteExit", j, i + 1, Etiquette.PORTE_HAUT, EKOCouleur.JAUNE);
                                } else {
                                    Porte P = new PorteExit("PorteExit", j, i + 1, Etiquette.PORTE_COTE, EKOCouleur.JAUNE);
                                }
                                break;
                            case "+":
                                if (i == 0 || i == carte.length - 1) {
                                    Porte P = new PorteEntrer("PorteEntrer", j, i + 1, Etiquette.PORTE_HAUT, EKOCouleur.GRIS_FONCE);
                                } else {
                                    Porte P = new PorteEntrer("PorteEntrer", j, i + 1, Etiquette.PORTE_COTE, EKOCouleur.GRIS_FONCE);
                                }
                                break;
                            case "F":
                                Feu F = new Feu("Feu", j, i + 1, Etiquette.FEU);
                                break;
                            case "H":
                                Fantome f = new Fantome("Fantome", j, i + 1, Etiquette.FANTOM, true);
                                break;
                            //Utilisation de la lettre B pour le fantome verticale
                            case "V":
                                Fantome f2 = new Fantome("Fantome", j, i + 1, Etiquette.FANTOM, false);
                                break;
                            case "I":
                                Insecte I = new Insecte("Insecte", j, i + 1, Etiquette.INSCECTE);
                                break;
                            case "G":
                                Grenouille G = new Grenouille("Grenouille", j, i + 1, Etiquette.GRENOUILLE, false);
                                LangueG lgFin = new LangueG("LangueFin", j, i + 1, Etiquette.FIN_LANGUE_GRENOUILLE, false);
                                G.instasierObj(lgFin);
                                LangueG lgMillieu1 = new LangueG("LangueMillieu1", j, i + 1, Etiquette.MILLIEU_LANGUE_GRENOUILLE, false);
                                G.instasierObj(lgMillieu1);
                                LangueG lgMillieu2 = new LangueG("LangueMillieu2", j, i + 1, Etiquette.MILLIEU_LANGUE_GRENOUILLE, false);
                                G.instasierObj(lgMillieu2);
                                break;
                            case "g" :
                                Grenouille Gr = new Grenouille("Grenouille", j, i + 1, Etiquette.GRENOUILLE, true);
                                LangueG lgFinr = new LangueG("LangueFin", j, i + 1, Etiquette.FIN_LANGUE_GRENOUILLE, true);
                                Gr.instasierObj(lgFinr);
                                LangueG lgMillieu1r = new LangueG("LangueMillieu1", j, i + 1, Etiquette.MILLIEU_LANGUE_GRENOUILLE, true);
                                Gr.instasierObj(lgMillieu1r);
                                LangueG lgMillieu2r = new LangueG("LangueMillieu2", j, i + 1, Etiquette.MILLIEU_LANGUE_GRENOUILLE, true);
                                Gr.instasierObj(lgMillieu2r);
                                break;
                            case "S":
                                Squelette S = new Squelette("Squelette", j, i + 1, Etiquette.SQUELETTE);
                                break;
                            case "C":
                                Clef C = new Clef("Clef", j, i + 1, Etiquette.CLEF);
                                break;
                            case "P":
                                Potion P = new Potion("Potion", j, i + 1, Etiquette.POTION);
                                break;
                        }
                    }
                }
            }
        }

        /**
         * cette methode verifie si on a un mur dans la position donner
         * @param x position x donner par l'utilisateur
         * @param y position y donner par l'utilisateur
         * @return return a boolean true si on rencontre un mur sinon false
         */

        public static boolean estBloquantMur(int x, int y) {
            return carte[y - 1][x].equals("#");
        }

        /**
         * cette methode verifie si on a une porte dans la position donner
         * @param x position x donner par l'utilisateur
         * @param y position y donner par l'utilisateur
         * @return return a boolean true si on rencontre une porte sinon false
         */

        public static boolean estBloquantPorte(int x, int y) {
            return carte[y - 1][x].equals("-") || carte[y - 1][x].equals("+");
        }

        /**
         * Permet de retourner un nouveau "FileInputStream"
         *
         * @param nomFichier Emplacement du fichier en String
         * @return Le "FileInputStream" du fichier.
         */
        private static FileInputStream ouvrirFichierLecture(String nomFichier) {
            FileInputStream fichier = null;
            try {
                fichier = new FileInputStream(nomFichier);
            } catch (FileNotFoundException ex) {
                System.out.println("Erreur d’ouverture de fichier");
            }
            return fichier;
        }

        /**
         * Permet de fermer le fichier sans erreur
         *
         * @param fichier le fichier qui doit etre fermer
         */
        private static void fermerFichier(Closeable fichier) {
            try {
                fichier.close();
            } catch (IOException ex) {
                System.out.println("Erreur de fermeture de fichier");
            }
        }
    }



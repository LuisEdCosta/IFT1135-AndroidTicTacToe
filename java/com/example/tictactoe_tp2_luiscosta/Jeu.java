package com.example.tictactoe_tp2_luiscosta;

public class Jeu implements TicTacToe {

    /* Attributs */
    private int[] pos_joue; // positions sur la grille avec un "X" ou un "O" (en ordre de jeu: X en premier)
    private int nbJeu;      // nb de coups (+1 avant que chaque joueur joue son tour)

    /* Constructeurs */
    public Jeu() {
        this.pos_joue = new int[9];
        this.nbJeu = -1;
    }
    public Jeu(int[] PosJoue, int NbJeu) {
        this.pos_joue = PosJoue;
        this.nbJeu = NbJeu;
    }

    /* Methodes */
    // Determine si une cellule est deja occupee par le joueur choisi ("X" ou "O")
    public boolean findCell(int aTrouver, String joueur)
    {
        for(int i=0; i<=this.nbJeu; i++) {
            if(aTrouver == this.pos_joue[i]) {
                if((joueur == "X" && i%2==0) || (joueur == "O" && i%2==1)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Determine si le joueur choisi ("X" ou "O") peut gagner au prochain coup et retour de la cellule gagnante
    public int findWinningCell(String joueur)
    {
        // Combinaisons gagnantes pour comparaison
        int[][] comboGagnants = {{0,1,2}, {0,4,8}, {0,3,6}, {6,7,8}, {6,4,2}, {3,4,5}, {1,4,7}, {2,5,8}};

        // Determine l'adversaire du joueur choisi
        String autre_joueur;
        if(joueur == "X") {
            autre_joueur = "O";
        }
        else {
            autre_joueur = "X";
        }

        /* Verification par combinaison:
           Nous verifions si une des cellules est deja occupes par l'adversaire -> si oui ne peux pas etre une combinaison gagnante -> prochaine combinaisaon
           Si les cellules ne sont pas prises par l'adversaire -> compte le nombre de cellules occupe par le joueur -> si 2 retour de la cellule gagnante
        */
        int cellule = 0;
        int nbOccupee = 0;
        boolean adversaire = false;

        for(int i=0; i<8; i++) {

            for(int j=0; j<3; j++) {
                if(findCell(comboGagnants[i][j], autre_joueur)) {
                    adversaire = true;
                }
            }

            if(!adversaire) {
                for(int k=0; k<3; k++) {
                    if(findCell(comboGagnants[i][k], joueur)) {
                        nbOccupee++;
                    }
                    else {
                        cellule = comboGagnants[i][k];
                    }
                }

                if(nbOccupee == 2) {
                    return cellule;
                }
            }

            // Reset des variables pour le prochain test
            adversaire = false;
            nbOccupee = 0;
        }

        // Aucune combinaison gagnante en ce moment
        return -1;
    }

    // Determine si deux X et un O sur une meme diagonale
    public boolean gameStateDiag() {

        int count_X = 0, count_O = 0;
        int[][] comboDiag = {{0,4,8}, {2,4,6}};

        for(int i=0; i<2; i++) {
            for(int j=0; j<3; j++) {
                if(findCell(comboDiag[i][j], "X")) {
                    count_X++;
                }
                else if(findCell(comboDiag[i][j], "O")) {
                    count_O++;
                }
            }

            if(count_X==2 && count_O==1) {
                return true;
            }
            else {
                count_X = 0;
                count_O = 0;
            }
        }
        return false;
    }

    // Determine la premiere cellule disponible parmis les cellules donnees en entree
    public int firstFreeCell(int[] pos)
    {
        for(int i=0; i<pos.length; i++) {
            if(!findCell(pos[i],"X")) {
                if(!findCell(pos[i],"O")) {
                    return pos[i];
                }
            }
        }
        return -1;
    }

    // Determine s'il y a une ligne, colonne ou diagonale sans "X" et avec au moins un "O" + retour d'une
    // cellule disponible parmis la ligne, colonne ou diagonale trouvee.
    public int gameStateOpenCombo()
    {
        int cellule = -1;
        int count_X = 0, count_O = 0;
        int[][] openCombo = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

        for(int i=0; i<8; i++) {
            for(int j=0; j<3; j++) {
                if(findCell(openCombo[i][j], "X")) {
                    count_X++;
                }
                else if(findCell(openCombo[i][j], "O")) {
                    count_O++;
                }
                else {
                    cellule = openCombo[i][j];
                }
            }

            // Verification de la condition voulu
            if(count_X==0 && count_O==1) {
                return cellule;
            }
            else {
                // Reset des compteurs pour la prochaine verification
                count_X = 0;
                count_O = 0;
            }
        }
        return -1;
    }


/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++
+  Methodes de l'interface TicTacToe.java a implementer +
++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/

    /* Pour la sauvegarde du jeu */
    @Override
    public int[] getPosJoue() {
        return this.pos_joue;
    }

    @Override
    public int getNbJeu() {
        return this.nbJeu;
    }

    @Override
    public String getCaseContent(int cellule) {
        if(findCell(cellule, "X")) {
            return "X";
        }
        else if(findCell(cellule, "O")) {
            return "O";
        }
        return "";
    }

    /* pour initialiser un jeu de tic tac toe */
    @Override
    public void initialise() {
        this.pos_joue = new int[9];
        this.nbJeu = -1;
    }

    /* pour recevoir l'index de la cellule choisie par X */
    @Override
    public void setX(int cellule) {
        this.nbJeu++;
        this.pos_joue[this.nbJeu] = cellule;
    }

    /* Pour transmettre l'index de la position du O jou? */
    @Override
    public int getO() {

        int cellule = -1;
        int[] coins = {0,2,6,8};
        int[] cotes = {1,3,5,7};
        int[] grille = {0,1,2,3,4,5,6,7,8};

        if(this.nbJeu == 0) {
            // Au premier coup -> Avantage d'avoir le centre, sinon un des coins.
            if(!findCell(4, "X")) {
                cellule = 4;
            }
            else {
                cellule = 0;
            }
        }
        else if(this.nbJeu == 2) {
        /* Au deuxieme coup -> Si X peut gagner au prochain coup on bloque

           sinon -> si diagonale de deux X et un O -> verification O dans le centre
                        si O dans le centre -> choisir 1,3,5 ou 7, sinon 0,2,6 ou 8

                 -> si O dans le centre avec X dans un coin (0,2,6 ou 8) et X dans les cotes (1,3,5 ou 7) -> choisir coin pres des X

                 -> si O dans le centre avec deux X dans les cotes (1,3,5 ou 7) -> choisir coin contigu

                 -> si O dans le centre et X opposee -> choisir coin
        */

            cellule = findWinningCell("X");

            if(cellule < 0) {
                if(gameStateDiag()) {
                    if(findCell(4, "O")) {
                        cellule = firstFreeCell(cotes);
                    }
                    else {
                        cellule = firstFreeCell(coins);
                    }
                }
                else {
                    if(findCell(4, "O")) {
                        if (findCell(0, "X") && findCell(5, "X")) {
                            cellule = 2;
                        }
                        else if (findCell(0, "X") && findCell(7, "X")) {
                            cellule = 6;
                        }
                        else if (findCell(2, "X") && findCell(3, "X")) {
                            cellule = 0;
                        }
                        else if (findCell(2, "X") && findCell(7, "X")) {
                            cellule = 8;
                        }
                        else if (findCell(6, "X") && findCell(1, "X")) {
                            cellule = 0;
                        }
                        else if (findCell(6, "X") && findCell(5, "X")) {
                            cellule = 8;
                        }
                        else if (findCell(8, "X") && findCell(1, "X")) {
                            cellule = 2;
                        }
                        else if (findCell(8, "X") && findCell(3, "X")) {
                            cellule = 6;
                        }
                        else if (findCell(1, "X") && findCell(5, "X")) {
                            cellule = 2;
                        }
                        else if (findCell(5, "X") && findCell(7, "X")) {
                            cellule = 8;
                        }
                        else if (findCell(7, "X") && findCell(3, "X")) {
                            cellule = 6;
                        }
                        else if (findCell(3, "X") && findCell(1, "X")) {
                            cellule = 0;
                        }
                        else {
                            cellule = firstFreeCell(coins);
                        }
                    }
                }
            }
        }
        else if(this.nbJeu >= 4) {
        /* Au troisieme et quatrieme coup -> verification si on peut gagner au prochain coup -> victoire

           sinon bloque X s'il peut gagner au prochain coup

           sinon cherche une ligne, colonne ou diagonale avec un O mais pas de X

           sinon joue a une position libre
        */

            cellule = findWinningCell("O");

            if(cellule < 0) {
                cellule = findWinningCell("X");

                if(cellule < 0) {
                    cellule = gameStateOpenCombo();

                    if(cellule < 0)  {
                        cellule = firstFreeCell(grille);
                    }
                }
            }
        }

        // On incremente le nombre de coups
        this.nbJeu++;
        // On sauvegarde la cellule qui recoit un "O"
        this.pos_joue[this.nbJeu] = cellule;
        // Transmet le choix a l'interface graphique
        return cellule;
    }

    /* vrai ou faux, le joueur pass? en param?tre ("X" ou "O")
     * a gagn??  Si vrai, les index des 3 cellules de la combinaison
     * gagnante sont dans le tableau pos.
     *
     *  Il y a 8 combinaisons gagnantes :
     * (0, 1, 2), ( 0, 4, 8), (0, 3, 6), (6, 7, 8), (6, 4, 2), (3, 4, 5), (1, 4, 7), (2, 5, 8)
     */
    @Override
    public boolean gagnant(String joueur, int[] pos) {

        int[][] comboGagnants = {{0,1,2}, {0,4,8}, {0,3,6}, {6,7,8}, {6,4,2}, {3,4,5}, {1,4,7}, {2,5,8}};
        int count = 0;

        for(int i=0; i<8; i++) {
            for(int j=0; j<3; j++) {
                if(findCell(comboGagnants[i][j], joueur)) {
                    pos[j] = comboGagnants[i][j];
                    count++;
                }
            }

            // Verification de la condition de victoire
            if(count == 3) {
                return true;
            }

            // Reset des variables pour la prochaine iteration
            count = 0;
        }

        // Le joueur n'a pas encore gagne
        return false;
    }

    /*Toutes les cellules sont occup?es et il n'y a aucun gagnant */
    @Override
    public boolean isPartieNulle()
    {
        int[] grille = {0,1,2,3,4,5,6,7,8};
        int cellule = firstFreeCell(grille);

        if(cellule < 0) {
            if(!gagnant("X", new int[3])) {
                if(!gagnant("O", new int[3])) {
                    return true;
                }
            }
        }
        return false;
    }


}


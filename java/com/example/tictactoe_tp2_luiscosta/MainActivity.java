/*
 * Tp2 pour le cours IFT 1135
 *
 * Auteur: Luis Costa
 * Matricule: 20146688
 *
 * Derniere modification: 2019-11-26
 */

package com.example.tictactoe_tp2_luiscosta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.ConfigurationCompat;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Objects de l'interface
    TextView resultat;
    Button case0, case1, case2, case3, case4, case5, case6, case7, case8, newGame;
    // Configuration de l'appareil
    Locale locale;
    String language;
    // Variables pour la logique du jeu
    private TicTacToe unJeu;	//Référence sur le jeu
    private int[] tabGagne;     //Un tableau qui contiendra les indices de la combinaison gagnante
    private boolean fini;	    //Le jeu est fini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definition des variables pour les boutons et texte de l'interface
        resultat = findViewById(R.id.textView_resultat);
        case0 = findViewById(R.id.button_case0);
        case1 = findViewById(R.id.button_case1);
        case2 = findViewById(R.id.button_case2);
        case3 = findViewById(R.id.button_case3);
        case4 = findViewById(R.id.button_case4);
        case5 = findViewById(R.id.button_case5);
        case6 = findViewById(R.id.button_case6);
        case7 = findViewById(R.id.button_case7);
        case8 = findViewById(R.id.button_case8);
        newGame = findViewById(R.id.button_newGame);

        // Definition des on-click events
        case0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case0.setText("X");
                case0.setClickable(false);
                boutonClique(0);
            }
        });
        case1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case1.setText("X");
                case1.setClickable(false);
                boutonClique(1);
            }
        });
        case2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case2.setText("X");
                case2.setClickable(false);
                boutonClique(2);
            }
        });
        case3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case3.setText("X");
                case3.setClickable(false);
                boutonClique(3);
            }
        });
        case4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case4.setText("X");
                case4.setClickable(false);
                boutonClique(4);
            }
        });
        case5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case5.setText("X");
                case5.setClickable(false);
                boutonClique(5);
            }
        });
        case6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case6.setText("X");
                case6.setClickable(false);
                boutonClique(6);
            }
        });
        case7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case7.setText("X");
                case7.setClickable(false);
                boutonClique(7);
            }
        });
        case8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                case8.setText("X");
                case8.setClickable(false);
                boutonClique(8);
            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialiser();
            }
        });

        // Configuration de langue de l'appareil
        locale = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration()).get(0);
        language = locale.getLanguage();

        // Commencement de la partie
        unJeu = new Jeu();
        tabGagne = new int[3];
        fini = false;
        initialiser();
    }


    // Rétablit l'aspect initial de la grille
    private void initialiser(){
        // Affichage selon la langue de l'utilisateur
        if(language =="en") {
            newGame.setText("New game");
        }
        else if(language == "fr") {
            newGame.setText("Nouvelle partie");
        }
        // Boutons et texte de l'interface
        resultat.setText("");
        case0.setText("");
        case0.setClickable(true);
        case0.setBackgroundColor(Color.LTGRAY);
        case1.setText("");
        case1.setClickable(true);
        case1.setBackgroundColor(Color.LTGRAY);
        case2.setText("");
        case2.setClickable(true);
        case2.setBackgroundColor(Color.LTGRAY);
        case3.setText("");
        case3.setClickable(true);
        case3.setBackgroundColor(Color.LTGRAY);
        case4.setText("");
        case4.setClickable(true);
        case4.setBackgroundColor(Color.LTGRAY);
        case5.setText("");
        case5.setClickable(true);
        case5.setBackgroundColor(Color.LTGRAY);
        case6.setText("");
        case6.setClickable(true);
        case6.setBackgroundColor(Color.LTGRAY);
        case7.setText("");
        case7.setClickable(true);
        case7.setBackgroundColor(Color.LTGRAY);
        case8.setText("");
        case8.setClickable(true);
        case8.setBackgroundColor(Color.LTGRAY);
        // Logique du jeu
        unJeu.initialise();
        for(int i=0; i<3; i++) {
            tabGagne[i]=-1;
        }
        fini = false;
    }

    // Transfert a la logique de jeu que le bouton à l'index i a été cliqué
    void boutonClique(int i){
        if(fini)
            return;
        unJeu.setX(i);				//Transmettre l'index du choix X au jeu
        if(unJeu.gagnant("X",tabGagne)){	//Victoire de X?
            fini = true;
            marque(tabGagne);
            if(language == "en") {
                resultat.setText("X won!");
            }
            else if(language == "fr") {
                resultat.setText("X gagne!");
            }
        }
        else
        if(!unJeu.isPartieNulle()){				 // Si pas victoire de X et pas nulle
            // Demande le choix pour O
            int cellule = unJeu.getO();
            // Modifier l'affichage sur la grille pour
            switch (cellule) {
                case 0:
                    case0.setText("O");
                    case0.setClickable(false);
                    break;
                case 1:
                    case1.setText("O");
                    case1.setClickable(false);
                    break;
                case 2:
                    case2.setText("O");
                    case2.setClickable(false);
                    break;
                case 3:
                    case3.setText("O");
                    case3.setClickable(false);
                    break;
                case 4:
                    case4.setText("O");
                    case4.setClickable(false);
                    break;
                case 5:
                    case5.setText("O");
                    case5.setClickable(false);
                    break;
                case 6:
                    case6.setText("O");
                    case6.setClickable(false);
                    break;
                case 7:
                    case7.setText("O");
                    case7.setClickable(false);
                    break;
                case 8:
                    case8.setText("O");
                    case8.setClickable(false);
                    break;
            }
            // réfléter le choix de O
            if(unJeu.gagnant("O",tabGagne)){	 // O gagne?
                fini = true;
                marque(tabGagne);
                if(language == "en") {
                    resultat.setText("O won!");
                }
                else if(language == "fr") {
                    resultat.setText("O gagne!");
                }
            }
        }
        else	//Partie nulle
        {
            fini = true;
            if(language == "en") {
                resultat.setText("Draw!");
            }
            else if(language == "fr") {
                resultat.setText("Partie nulle!");
            }
        }
    }

    //changer la couleur des boutons de la combinaison gagnante
    public void marque(int[] t){
        // Victoire atteinte donc desactive la grill
        case0.setClickable(false);
        case1.setClickable(false);
        case2.setClickable(false);
        case3.setClickable(false);
        case4.setClickable(false);
        case5.setClickable(false);
        case6.setClickable(false);
        case7.setClickable(false);
        case8.setClickable(false);
        for(int i=0; i<3;i++){
            switch(tabGagne[i]) {
                case 0:
                    case0.setBackgroundColor(Color.BLUE); break;
                case 1:
                    case1.setBackgroundColor(Color.BLUE); break;
                case 2:
                    case2.setBackgroundColor(Color.BLUE); break;
                case 3:
                    case3.setBackgroundColor(Color.BLUE); break;
                case 4:
                    case4.setBackgroundColor(Color.BLUE); break;
                case 5:
                    case5.setBackgroundColor(Color.BLUE); break;
                case 6:
                    case6.setBackgroundColor(Color.BLUE); break;
                case 7:
                    case7.setBackgroundColor(Color.BLUE); break;
                case 8:
                    case8.setBackgroundColor(Color.BLUE); break;
            }
        }
    }

    // Sauvegarde la partie lors d'un changement d'orientation de l'appareil
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putCharSequence("resultat", resultat.getText());
        savedInstanceState.putBoolean("fini", fini);
        savedInstanceState.putIntArray("tabGagne", tabGagne);
        savedInstanceState.putIntArray("posJoue", unJeu.getPosJoue());
        savedInstanceState.putInt("nbJeu", unJeu.getNbJeu());
        super.onSaveInstanceState(savedInstanceState);
    }

    // Restore la partie apres un changement d'orientation de l'appareil
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fini = savedInstanceState.getBoolean("fini");
        tabGagne = savedInstanceState.getIntArray("tabGagne");
        int[] posJoue = savedInstanceState.getIntArray("posJoue");
        int nbJeu = savedInstanceState.getInt("nbJeu");
        resultat.setText(savedInstanceState.getCharSequence("resultat"));

        // Restoration de la partie en cours
        unJeu = new Jeu(posJoue, nbJeu);
        for(int i=0; i<=nbJeu; i++) {
            switch(posJoue[i]) {
                case 0:
                    case0.setClickable(false);
                    case0.setText(unJeu.getCaseContent(0));
                    break;
                case 1:
                    case1.setClickable(false);
                    case1.setText(unJeu.getCaseContent(1));
                    break;
                case 2:
                    case2.setClickable(false);
                    case2.setText(unJeu.getCaseContent(2));
                    break;
                case 3:
                    case3.setClickable(false);
                    case3.setText(unJeu.getCaseContent(3));
                    break;
                case 4:
                    case4.setClickable(false);
                    case4.setText(unJeu.getCaseContent(4));
                    break;
                case 5:
                    case5.setClickable(false);
                    case5.setText(unJeu.getCaseContent(5));
                    break;
                case 6:
                    case6.setClickable(false);
                    case6.setText(unJeu.getCaseContent(6));
                    break;
                case 7:
                    case7.setClickable(false);
                    case7.setText(unJeu.getCaseContent(7));
                    break;
                case 8:
                    case8.setClickable(false);
                    case8.setText(unJeu.getCaseContent(8));
                    break;
            }
        }

    }
}

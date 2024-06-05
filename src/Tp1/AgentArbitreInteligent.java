package Tp1;

import java.util.ArrayList;

public class AgentArbitreInteligent {

    // Méthode pour afficher la grille TicTacToe
    static void affichage(char[][] board) {
        System.out.println("    0     1     2");
        System.out.println("  +-----+-----+-----+");

        for (int i = 0; i < 3; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + "  | ");
            }
            System.out.println();
            System.out.println("  +-----+-----+-----+");
        }
    }

    // Méthode pour vérifier si la partie est terminée
     static boolean gameOver(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Il reste au moins une case vide
                } }
        }
        return true; // Toutes les cases sont remplies
    }
    
    // Méthode pour vérifier si un symbole a gagné la partie
     static boolean gagner(char[][] board, ArrayList<String> BF,ArrayList<Reglecomplex> BRC) {
    	 MoteurInference.chainageAvantcomplexsansprop(BRC, BF);
    	        // Vérification de la présence de "gagner" dans la liste BF
    	        return BF.contains("gagner");
    	    }
     // Méthode pour vérifier si un symbole a gagné la partie
     static boolean gagner(char[][] board, char symbole) {
        // Vérification par ligne
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbole && board[i][1] == symbole && board[i][2] == symbole) {
                return true; // Gagné par ligne
                }}
        // Vérification par colonne
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbole && board[1][j] == symbole && board[2][j] == symbole) {
                return true; // Gagné par colonne 
                }}
        // Vérification par diagonale
        if (board[0][0] == symbole && board[1][1] == symbole && board[2][2] == symbole) {
            return true; // Gagné par diagonale
            }
        // Vérification par anti-diagonale
        if (board[0][2] == symbole && board[1][1] == symbole && board[2][0] == symbole) {
            return true; // Gagné par anti-diagonale
        }

        return false; // Aucune victoire
    }
    
    public static void partie(int tour, char[][] board, AgentIntelligent j1, AgentIntelligent j2,ArrayList<Reglecomplex> BR) {
        if (tour % 2 == 0) {
            System.out.println("Tour du joueur  (X) :");
            j1.jouer( BR,j1.getBF(),  j2.getBF(),board);
        } else {
            System.out.println("Tour du joueur  (O) :");
            j2.jouer( BR,j2.getBF(),j1.getBF(),board);
        }
        affichage(board);
    }

    public static void partie(int tour, char[][] board, AgentIntelligent j1, NewAgentJoueur j2,ArrayList<Reglecomplex> BR) {
        if (tour % 2 == 0) {
            System.out.println("Tour du joueur  (X) :");
            j1.jouer( BR,j1.getBF(),  j2.getBF(),board);
        } else {
            System.out.println("Tour du joueur  (O) :");
            j2.jouer( board,j2.getBF());
        }
        affichage(board);
    }
    public static void partie(int tour, char[][] board, NewAgentJoueur j1, NewAgentJoueur j2) {
    	ArrayList<String> BF = new ArrayList<String>();
  
        if (tour % 2 == 0) {
            System.out.println("Tour du joueur  (X) :");
            j1.jouer(board,BF);
        } else {
            System.out.println("Tour du joueur  (O) :");
            j2.jouer(board,BF);
        }
        affichage(board);
    }
    public static void resultat(char[][] board, AgentIntelligent j1, AgentIntelligent j2,ArrayList<Reglecomplex> BRC) {
        if (gagner(board, j1.getBF(), BRC)){
            System.out.println("Le joueur X a gagné !");
        } else if (gagner(board, j2.getBF(),BRC)) {
            System.out.println("Le joueur O a gagné !");
        } else {
            System.out.println("Match nul !");
        }
    }
    public static void resultat(char[][] board, AgentIntelligent j1, NewAgentJoueur j2,ArrayList<Reglecomplex> BRC) {
        if (gagner(board, j1.getBF(), BRC)){
            System.out.println("Le joueur X a gagné !");
        } else if (gagner(board, j2.getBF(),BRC)) {
            System.out.println("Le joueur O a gagné !");
        } else {
            System.out.println("Match nul !");
        }
    }
    public static void resultat(char[][] board, NewAgentJoueur j1, NewAgentJoueur j2) {
        if (gagner(board, j1.getSymbole())) {
            System.out.println("Le joueur X a gagné !");
        } else if (gagner(board, j2.getSymbole())) {
            System.out.println("Le joueur O a gagné !");
        } else {
            System.out.println("Match nul !");
        }
    }
}
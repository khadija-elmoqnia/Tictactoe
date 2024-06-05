package Tp1;

import java.util.ArrayList;

class Tictactoegamever2 {

    public static void main(String[] args) {
    	  // Déclarer les règles  pour les conditions du jeu TicTacToe
    	
        Reglecomplex RcTicTacToe1 = new Reglecomplex("0,0&1,0&2,0", "gagner");
        Reglecomplex RcTicTacToe2 = new Reglecomplex("0,1&1,1&2,1", "gagner");
        Reglecomplex RcTicTacToe3 = new Reglecomplex("0,2&1,2&2,2", "gagner");
        Reglecomplex RcTicTacToe4 = new Reglecomplex("0,0&0,1&0,2", "gagner");
        Reglecomplex RcTicTacToe5 = new Reglecomplex("1,0&1,1&1,2", "gagner");
        Reglecomplex RcTicTacToe6 = new Reglecomplex("2,0&2,1&2,2", "gagner");
        Reglecomplex RcTicTacToe7 = new Reglecomplex("0,0&1,1&2,2", "gagner");
        Reglecomplex RcTicTacToe8 = new Reglecomplex("0,2&1,1&2,0", "gagner");

        // Ajouter les règles composées à la base de règles (BRC)
        ArrayList<Reglecomplex> BRC = new ArrayList<Reglecomplex>();
        BRC.add(RcTicTacToe1);
        BRC.add(RcTicTacToe2);
        BRC.add(RcTicTacToe3);
        BRC.add(RcTicTacToe4);
        BRC.add(RcTicTacToe5);
        BRC.add(RcTicTacToe6);
        BRC.add(RcTicTacToe7);
        BRC.add(RcTicTacToe8);
    	
	
        char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
        ArrayList<String> BF1 = new ArrayList<String>();
        ArrayList<String> BF2 = new ArrayList<String>();
        AgentIntelligent j1 = new AgentIntelligent('X', BF1);
        AgentIntelligent j2 = new AgentIntelligent('O', BF2);

        int tour = 0;

        AgentArbitreInteligent.affichage(board);

        while (! AgentArbitreInteligent.gameOver(board)) {
        	 AgentArbitreInteligent.partie(tour, board, j1, j2, BRC);

            if ( AgentArbitreInteligent.gagner(board, BF1,BRC) ||  AgentArbitreInteligent.gagner(board, BF2,BRC)) {
                break;
            }

            tour++;
        }

        AgentArbitreInteligent.resultat(board, j1, j2,BRC);
        }
    }


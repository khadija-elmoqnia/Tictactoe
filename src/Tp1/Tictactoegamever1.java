package Tp1;
import java.util.ArrayList;

public class Tictactoegamever1 {
    public static void main(String[] args) {
        char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
        ArrayList<String> BF1 = new ArrayList<String>();
        ArrayList<String> BF2 = new ArrayList<String>();
        
        NewAgentJoueur j1 = new NewAgentJoueur('X', BF1);
        NewAgentJoueur j2 = new NewAgentJoueur('O', BF2);
       

        int tour = 0;

        AgentArbitreInteligent.affichage(board);

        while (! AgentArbitreInteligent.gameOver(board)) {
        	 AgentArbitreInteligent.partie(tour, board, j1, j2);

            if ( AgentArbitreInteligent.gagner(board, j1.getSymbole()) ||  AgentArbitreInteligent.gagner(board, j2.getSymbole())) {
                break;
            }

            tour++;
        }

        AgentArbitreInteligent.resultat(board, j1, j2);
    }
}
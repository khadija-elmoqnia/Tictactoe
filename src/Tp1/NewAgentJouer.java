package Tp1;

import java.util.ArrayList;
import java.util.Random;

class NewAgentJoueur{
	private char symbole;
	private ArrayList<String> BF; // Base de Fait
	
	    // Constructeur paramétrique
	public NewAgentJoueur(char symbole, ArrayList<String> BF) {
        this.symbole = symbole;
        this.BF = BF;}

	    // Getter
	    public char getSymbole() {
	        return symbole;}
	    // Setter
	    public void setSymbole(char symbole) {
	        this.symbole = symbole; }
	    // Getter pour BF
	    public ArrayList<String> getBF() {
	        return BF; }

	    // Setter pour BF
	    public void setBF(ArrayList<String> BF) {
	        this.BF = BF;}

	    // Méthode pour que l'agent joueur effectue un coup
	    public void jouer(char[][] ticTacToe,ArrayList<String> BF) {
	        Random rand = new Random();
	        int row, col;
	        do {
	            // Génération de nombres aléatoires entre 0 et 2
	            row = rand.nextInt((2 - 0) + 1) + 0;
	            col = rand.nextInt((2 - 0) + 1) + 0;
	        } while (ticTacToe[row][col] != '-');

	        // Affectation du symbole du joueur à la case adéquate
	        ticTacToe[row][col] = symbole;
	        BF.add(row + "," + col);}
	}



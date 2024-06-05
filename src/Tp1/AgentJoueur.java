package Tp1;

import java.util.Random;

class AgentJoueur {
    private char symbole;
    // Constructeur paramétrique
    public AgentJoueur(char symbole) {
        this.symbole = symbole;}

    // Getter
    public char getSymbole() {
        return symbole;}
    // Setter
    public void setSymbole(char symbole) {
        this.symbole = symbole; }

    // Méthode pour que l'agent joueur effectue un coup
    public void jouer(char[][] ticTacToe) {
        Random rand = new Random();
        int row, col;
        do {
            // Génération de nombres aléatoires entre 0 et 2
            row = rand.nextInt((2 - 0) + 1) + 0;
            col = rand.nextInt((2 - 0) + 1) + 0;
        } while (ticTacToe[row][col] != '-');

        // Affectation du symbole du joueur à la case adéquate
        ticTacToe[row][col] = symbole; }
}



    

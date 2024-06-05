package Tp1;


public class Tictactoegamever0 {

    // Méthode pour afficher la grille TicTacToe
    private static void affichage(char[][] board) {
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
    private static boolean gameOver(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Il reste au moins une case vide
                } }
        }
        return true; // Toutes les cases sont remplies
    }
    
    // Méthode pour vérifier si un symbole a gagné la partie
    private static boolean gagner(char[][] board, char symbole) {
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

    public static void main(String[] args) {
        // Affichage de la grille initiale
        char[][] board = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
        affichage(board);

        // Création d'instances des joueurs J1 et J2 avec les symboles X et O
        AgentJoueur j1 = new AgentJoueur('X');
        AgentJoueur j2 = new AgentJoueur('O');

        // Boucle principale du jeu
        while (!gameOver(board)) {
            // Tour du joueur J1
            j1.jouer(board);

            // Affichage de la grille après le coup du joueur J1
            affichage(board);

            // Vérification de la fin de la partie après le coup du joueur J1
            if (gagner(board, j1.getSymbole())) {
                System.out.println("Le joueur J1 a gagné !");
                break; }

            // Tour du joueur J2
            j2.jouer(board);

            // Affichage de la grille après le coup du joueur J2
            affichage(board);

            // Vérification de la fin de la partie après le coup du joueur J2
            if (gagner(board, j2.getSymbole())) {
                System.out.println("Le joueur J2 a gagné !");
                break;}

            // Autres actions ou tour du joueur suivant...
        }

        // Affichage du résultat final de la partie
        if (gameOver(board) && !gagner(board, j1.getSymbole()) && !gagner(board, j2.getSymbole())) {
            System.out.println("Match nul !");
        }
    }

}

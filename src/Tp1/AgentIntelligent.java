package Tp1;

import java.util.ArrayList;
import java.util.Arrays;

public class AgentIntelligent {
    private char symbole;
    private ArrayList<String> BF; // Base de Fait

    // Constructeur par défaut
    public AgentIntelligent() { }

    // Constructeur avec symbole et BF en paramètres
    public AgentIntelligent(char symbole, ArrayList<String> BF) {
        this.symbole = symbole;
        this.BF = BF;}

    // Getter pour symbole
    public char getSymbole() {
        return symbole;}

    // Setter pour symbole
    public void setSymbole(char symbole) {
        this.symbole = symbole;}

    // Getter pour BF
    public ArrayList<String> getBF() {
        return BF; }

    // Setter pour BF
    public void setBF(ArrayList<String> BF) {
        this.BF = BF;}
 
    
 
 // Méthode pour vérifier la possibilité de gagner
    private boolean checkPossibiliteGagner(ArrayList<String> BF) {
        // Vérification de la présence de "gagner" dans la liste BF
        return BF.contains("gagner");
    }

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

 // Méthode pour mettre à jour la base de fait
    public static void updateBF(ArrayList<String> BF, char[][] matrice, char symbole) {
        // Supprimer toutes les occurrences du symbole dans la base de faits
        BF.removeIf(position -> position.contains(String.valueOf(symbole)));

        // Ajouter les nouvelles positions du symbole à la base de faits
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] == symbole) {
                    // Ajouter la position au format "i,j" à la base de faits
                    BF.add(i + "," + j);
                }
            }
        }
    }

 
 // Méthode pour simuler un mouvement
    public int[] simulateMove(char[][] matriceTicTacToe, ArrayList<Reglecomplex> BRC, ArrayList<String> BF) {
        // Créer des copies de la matrice de corrélation, la base de fait, et la base de règles
        char[][] copieMatrice = copierMatrice(matriceTicTacToe);
        ArrayList<String> copieBF = new ArrayList<>(BF);
        ArrayList<Reglecomplex> copieBRC = new ArrayList<>();
        for (Reglecomplex item : BRC) {
            copieBRC.add(new Reglecomplex(item));
            //      copieBRC.add(new Reglecomplex(item));
        }
       

        // Parcourir chaque case de la matrice pour simuler un mouvement
        for (int i = 0; i < copieMatrice.length; i++) {
            for (int j = 0; j < copieMatrice[i].length; j++) {
                if (copieMatrice[i][j] == '-') {
                    // La case est vide, simuler un mouvement en plaçant le symbole de l'agent
                    copieMatrice[i][j] = this.symbole;
                    // Utiliser la fonction updateBF pour ajouter la position à la base de fait de la copie
                    copieBF.add(i + "," + j);
                    // Appliquer le chaînage avant complexe sur la copie de la base de règles et de fait
                    MoteurInference.chainageAvantcomplexsansprop(copieBRC, copieBF);
                    // Vérifier si l'agent peut gagner après ce mouvement
                    if (checkPossibiliteGagner(copieBF)) {
                        // Retourner la position du mouvement simulé
                        return new int[]{i, j};}
                    else {
                        // Réinitialiser la case pour simuler le mouvement suivant
                        copieMatrice[i][j] = '-';
                        // Utiliser la fonction updateBF pour retirer la position de la base de fait de la copie
                        copieBF.remove(i + "," + j); }
                }
            }
        }
        // Aucun mouvement gagnant trouvé, retourner null
        return null;
    }
    
    private int[] jouercaseAdjacente(char[][] matriceTicTacToe, char symbole) {
    	 // Tableau pour stocker les coordonnées (ligne, colonne)
    	int[] coordonnees = new int[] { -1, -1 }; 

        // Parcourir la matrice pour trouver la case avec le symbole donné
        for (int i = 0; i < matriceTicTacToe.length; i++) {
            for (int j = 0; j < matriceTicTacToe[i].length; j++) {
                if (matriceTicTacToe[i][j] == symbole) {
                    // Trouvé le symbole, maintenant chercher une case adjacente
                    coordonnees = trouverCaseAdjacente(matriceTicTacToe, i, j);
                   }
                }
            }
		return coordonnees;
		}

       
    

    private int[] trouverCaseAdjacente(char[][] matriceTicTacToe, int ligne, int colonne) {
        int[] coordonnees = new int[] { -1, -1 };  // Valeurs par défaut si aucune case adjacente n'est trouvée

        // Parcourir les cases adjacentes à la case avec le symbole opposé
        for (int i = Math.max(0, ligne - 1); i <= Math.min(matriceTicTacToe.length - 1, ligne + 1); i++) {
            for (int j = Math.max(0, colonne - 1); j <= Math.min(matriceTicTacToe[i].length - 1, colonne + 1); j++) {
                if (matriceTicTacToe[i][j] == '-' && (i != ligne || j != colonne)) {
                    // Case adjacente vide trouvée, retourner les coordonnées
                    coordonnees[0] = i;
                    coordonnees[1] = j;
                    return coordonnees;
                }
            }
        }

        // Retourner les coordonnées par défaut si aucune case adjacente n'est trouvée
        return coordonnees;
    }



    // Méthode pour copier une matrice
    private char[][] copierMatrice(char[][] matrice) {
        char[][] copieMatrice = new char[matrice.length][matrice[0].length];
        for (int i = 0; i < matrice.length; i++) {
            System.arraycopy(matrice[i], 0, copieMatrice[i], 0, matrice[i].length);
        }
        return copieMatrice;
    }
    
    private static int[] trouverCaseVide(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    // Case vide trouvée, retourner les coordonnées
                    return new int[]{i, j};
                }
            }
        }

        // Retourner null si aucune case vide n'est trouvée
        return null;
    }

 // Méthode pour jouer
    public void jouer(ArrayList<Reglecomplex> BR, ArrayList<String> BF, ArrayList<String> BFadversaire,char[][] board) {
        // a. Appliquer le chaînage avant composé pour vérifier la possibilité de gagner
    	if (simulateMove(board, BR, BF) != null) {
    	    // Le mouvement n'est pas nul, c'est-à-dire un mouvement gagnant a été trouvé
    	    int row=simulateMove(board, BR, BF)[0];
    	    int col=simulateMove(board, BR, BF)[1];
    		board[row][col] = symbole;
    		BF.add(row + "," + col);
    	} else{
    		if (simulateMove(board, BR, BFadversaire) != null) {
    			 // Le mouvement n'est pas nul, c'est-à-dire un mouvement gagnant a été trouvé pour ladversaire on le bloc
        	    int row=simulateMove(board, BR, BFadversaire)[0];
        	    int col=simulateMove(board, BR, BFadversaire)[1];
        		board[row][col] = symbole;
        		BF.add(row + "," + col);	
    		}
    		else {
    		    int[] test = new int[] { -1, -1 };
    		    int[] adjacentCase = jouercaseAdjacente(board, symbole);
    		    
    		    if (!Arrays.equals(adjacentCase, test)) {
    		        // La case n'est pas { -1, -1 }, c'est-à-dire une case adjacente a été trouvée
    		        int row = adjacentCase[0];
    		        int col = adjacentCase[1];
    		        board[row][col] = symbole;
    		        BF.add(row + "," + col);
    				
    			}else {
    				int[] coordinates = trouverCaseVide(board);
    				if (coordinates != null) {
    				    int row = coordinates[0];
    				    int col = coordinates[1];
    				    board[row][col] = symbole;
    				    BF.add(row + "," + col);
    				} else {
    				    // Handle the case where no adjacent empty cell is found
    				    // For example, you can choose a different strategy or print a message
    				}}}
    		}
    	
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }
    
}


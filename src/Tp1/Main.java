/**
 * 
 */

package Tp1;

/**
 * @author H P
 *
 */
import java.util.ArrayList;
import java.util.Arrays;

class RegleSimple {
    private String premisse;
    private String conclusion;

    // Constructeur par défaut
    public RegleSimple() {
        this.premisse = "";
        this.conclusion = "";
    }
    // Constructeur paramétrique
    public RegleSimple(String premisse, String conclusion) {
        this.premisse = premisse;
        this.conclusion = conclusion;
    }
    public String getPremisse() {
        return this.premisse;
    }
    public String getConclusion() {
        return this.conclusion;
    }
    public void setPermisse(String permisse) {
    	this.premisse=premisse;	
    }
    public void setConclusion(String conclusion) {
    	this.conclusion=conclusion;  	
    }
}



class Reglecomplex {
    private String[] premissecomplex;
    private String conclusion;

    // Constructeur par défaut
    public Reglecomplex() {
    }
    // Constructeur paramétrique
    public Reglecomplex(String stringregle, String conclusion) {
        this.conclusion = conclusion;
        this.premissecomplex=stringregle.split("&");
    }
    public String[] getPremissecomplex() {
        return this.premissecomplex;
    }
    public String getConclusion() {
        return this.conclusion;
    }
    public void setPermissecomplex(String[] premissecomplex) {
    	this.premissecomplex =premissecomplex;	
    }
    public void setConclusion(String conclusion) {
    	this.conclusion=conclusion;
    	
    }
    // Copy constructor
    public Reglecomplex(Reglecomplex other) {
        // Assuming premissecomplex is not null
        this.premissecomplex = Arrays.copyOf(other.premissecomplex, other.premissecomplex.length);
        this.conclusion = other.conclusion;
    }
}



public class Main {
	 public static void main(String[] args) {
	        // a. Déclarer deux objets de la classe RegleSimple
	        RegleSimple R1 = new RegleSimple("P", "Q");
	        RegleSimple R2 = new RegleSimple("Q", "R");
	        RegleSimple R3 = new RegleSimple("T", "F");
	       
	        // b. Déclarer un tableau dynamique (ArrayList, etc.) nommé BF (Base de Fait) de type String
	        ArrayList<String> BF = new ArrayList<String>();
	        BF.add("P");

	        // c. Déclarer un tableau dynamique d’objet RegleSimple et nommer le BR (Base de Règle).
	        ArrayList<RegleSimple> BR = new ArrayList<RegleSimple>();
	        BR.add(R1);
	        BR.add(R3);
	        BR.add(R2);
	        
	        

	        // Call the method to display rules and facts
	        System.out.println("\navant moteur d'inférence");
	        displayRulesAndFacts(BR, BF);

	        // Utiliser le moteur d'inference pour le chainage avant
	        MoteurInference moteur = new MoteurInference();
	        boolean propVerifREstEtabli = moteur.chainageAvant(BR, BF, "G");
	        boolean propVerifFEstEtabli = moteur.chainageAvant(BR, BF, "F");

	        // Afficher le résultat
	        if (propVerifREstEtabli) {
	            System.out.println("La proposition R est établie.");
	        } else {
	            System.out.println("La proposition R n'est pas établie.");
	        }
	        
	        if (propVerifFEstEtabli) {
	            System.out.println("La proposition F est établie.");
	        } else {
	            System.out.println("La proposition F n'est pas établie./n");
	        }
	        
	        //Display BF and BR after traitement
	        System.out.println("\napres moteur d'inférence");
	        displayRulesAndFacts(BR, BF);
	       
	        
	     //// TEST COMPLEX
	        
	        
	        // a. Déclarer deux objets de la classe Reglecomplex
	        Reglecomplex RC1 = new Reglecomplex("A&B","C");
	        Reglecomplex RC2= new Reglecomplex("C&D", "F");
	        Reglecomplex RC3 = new Reglecomplex("F&B", "E");
	        Reglecomplex RC4 = new Reglecomplex("F&A", "G");
	        Reglecomplex RC5 = new Reglecomplex("G&F", "B");

	        // b. Déclarer un tableau dynamique (ArrayList, etc.) nommé BF (Base de Fait) de type String
	        ArrayList<String> BFC = new ArrayList<String>();
	        BFC.add("A");
	        BFC.add("B");
	        BFC.add("D");

	        // c. Déclarer un tableau dynamique d’objet Reglecomplex et nommer le BR (Base de Règle).
	        ArrayList<Reglecomplex> BRC = new ArrayList<Reglecomplex>();
	        BRC.add(RC1);
	        BRC.add(RC2);
	        BRC.add(RC3);
	        BRC.add(RC4);
	        BRC.add(RC5);
	        

	        // Call the method to display  complex rules and facts
	        System.out.println("\navant moteur d'inférence complex");
	        displaycomplexRulesAndFacts(BRC, BFC);

	        // Utiliser le moteur d'inference pour le chainage avant
	        MoteurInference moteurC = new MoteurInference();
	        boolean propVerifFCEstEtabli = moteurC.chainageAvantcomplexcorig(BRC, BFC, "F");
	        
	        // Afficher le résultat
	        if (propVerifFCEstEtabli) {
	            System.out.println("La proposition F complex est établie ");
	        } else {
	            System.out.println("La proposition F complex n'est pas établie.");
	        }
	        
	          
	        //Display BF and BR after traitement
	        System.out.println("\napres moteur d'inférence complex");
	        displaycomplexRulesAndFacts(BRC, BFC);
	        
////////tester update vf et br 
	        System.out.println("\ntester update vf et br pour complex");
	        moteurC.chainageAvantcomplexsansprop(BRC, BFC);
	        System.out.println("\ntester update vf et br pour simple");
	        moteurC.chainageAvantsansprop(BR,BF);
	        
///Tester update avec donne de tp 
	     // Création des règles complexes
	        Reglecomplex RCtest1 = new Reglecomplex("fleur&graine", "phanérogame");
	        Reglecomplex RCtest2 = new Reglecomplex("phanérogame&grainenue", "sapin");
	        Reglecomplex RCtest3 = new Reglecomplex("phanérogame&1-cotylédone", "monocotylédone");
	        Reglecomplex RCtest4 = new Reglecomplex("phanérogame&2-cotylédone", "dicotylédone");
	        Reglecomplex RCtest5 = new Reglecomplex("monocotylédone&rhyzome", "muguet");
	        Reglecomplex RCtest6 = new Reglecomplex("dicotylédone","anémone");
	        Reglecomplex RCtest7 = new Reglecomplex("monocotylédone&non_rhyzome", "lilas");
	        Reglecomplex RCtest8 = new Reglecomplex("feuille&fleur", "cryptogame");
	        Reglecomplex RCtest9 = new Reglecomplex("cryptogame&non_racine", "mousse");
	        Reglecomplex RCtest10 = new Reglecomplex("cryptogame&racine", "fougère");
	        Reglecomplex RCtest11 = new Reglecomplex("non_feuilles&plante", "thallophyte");
	        Reglecomplex RCtest12 = new Reglecomplex("thallophyte&chlorophylle", "algue");
	        Reglecomplex RCtest13 = new Reglecomplex("thallophyte&non_chlorophylle", "champignon");
	        Reglecomplex RCtest14 = new Reglecomplex("non_feuilles&non_fleur&non_plante", "colibacile");

	        // Ajout des règles complexes à la base de règles
	        ArrayList<Reglecomplex> BRCtest = new ArrayList<Reglecomplex>();
	        BRCtest.add(RCtest1);
	        BRCtest.add(RCtest2);
	        BRCtest.add(RCtest3);
	        BRCtest.add(RCtest4);
	        BRCtest.add(RCtest5);
	        BRCtest.add(RCtest6);
	        BRCtest.add(RCtest7);
	        BRCtest.add(RCtest8);
	        BRCtest.add(RCtest9);
	        BRCtest.add(RCtest10);
	        BRCtest.add(RCtest11);
	        BRCtest.add(RCtest12);
	        BRCtest.add(RCtest13);
	        BRCtest.add(RCtest14);
	     // b. Déclarer un tableau dynamique (ArrayList, etc.) nommé BF (Base de Fait) de type String
	        ArrayList<String> BFtest = new ArrayList<String>();
	        BFtest.add("chlorophylle");
	        BFtest.add("fleur");
	        BFtest.add("graine");
	        BFtest.add("plante");
	        
	        System.out.println("\n Resultat de Test des regle fait donné par prof");
	        moteurC.chainageAvantcomplexsansprop(BRCtest,BFtest);
	        
	 }//fin main      	  
	 
	 

       // Method to display rules and facts
        private static void displayRulesAndFacts(ArrayList<RegleSimple> BR, ArrayList<String> BF) {
        // Display rules
        System.out.println("Base de Règle:");
        for (RegleSimple regle : BR) {
            System.out.println("Premisse: " + regle.getPremisse() + ", Conclusion: " + regle.getConclusion());
        }

        // Display facts
        System.out.println("\nBase de Fait:");
        for (String fait : BF) {
            System.out.println("Fait: " + fait);
        }
    }
     
     // Method to display rules and facts
        private static void displaycomplexRulesAndFacts(ArrayList<Reglecomplex> BR, ArrayList<String> BF) {
        // Display rules
        System.out.println("Base de Règle:");
        for (Reglecomplex regle : BR) {
        	    System.out.print("Premisse: ");
        	    for (String premisse : regle.getPremissecomplex()) {
        	        System.out.print(premisse + " ");
        	    }
        	    System.out.println(", Conclusion: " + regle.getConclusion());
        	}
     // Display facts
        System.out.println("\nBase de Fait:");
        for (String fait : BF) {
            System.out.println("Fait: " + fait);
        }
        }
        
           
 }
        
 





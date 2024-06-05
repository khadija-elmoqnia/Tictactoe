package Tp1;

import java.util.ArrayList;
import java.util.Iterator;


public class MoteurInference {
    // Constructeur par défaut
    public MoteurInference() {
        // Initialize any attributes if needed
    }

    // Méthode pour le chaînage avant
    public static boolean chainageAvant(ArrayList<RegleSimple> BR, ArrayList<String> BF, String PropVerif) {
        while (!BF.contains(PropVerif) && !BR.isEmpty()) {
            boolean ruleApplied = false;

            // Recherche d'une règle applicable
            for (int i = 0; i < BR.size();) {
            	RegleSimple regle = BR.get(i);
                if (isRuleApplicable(regle, BF)&&!BF.contains(regle)) {
                    // Appliquer la règle
                    applyRule(regle, BF);
                    // Supprimer la règle de la BR
                    BR.remove(regle);
                    ruleApplied = true;
                }
                i+=1;
            }

            // Si aucune règle n'a été appliquée, la boucle doit s'arrêter
            if (!ruleApplied) {
                break;}
        }

        // Vérifier si PropVerif est dans BF
        return BF.contains(PropVerif);
    }
  
/////////////COrection qconfusion dans supression 

    public static boolean chainageAvantcorig(ArrayList<RegleSimple> BR, ArrayList<String> BF, String PropVerif) {
        Iterator<RegleSimple> iterator = BR.iterator();
        
        while (!BF.contains(PropVerif) && iterator.hasNext()) {
            RegleSimple regle = iterator.next();

            if (isRuleApplicable(regle, BF) && !BF.contains(regle.getConclusion())) {
                applyRule(regle, BF);
                iterator.remove();  // Utilisation de l'itérateur pour supprimer l'élément de la liste
            }
        }

        // Vérifier si PropVerif est dans BF
        return BF.contains(PropVerif);
    }
    
    public static void chainageAvantsansprop(ArrayList<RegleSimple> BR, ArrayList<String> BF) {
        while (!BR.isEmpty()) {
            boolean ruleApplied = false;
            // Recherche d'une règle applicable
            for (int i = 0; i < BR.size();) {
            	RegleSimple regle = BR.get(i);
                if (isRuleApplicable(regle, BF)&&!BF.contains(regle)) {
                    // Appliquer la règle
                    applyRule(regle, BF);
                    // Supprimer la règle de la BR
                    BR.remove(regle);
                    ruleApplied = true;
                }
                i+=1;
            }

            // Si aucune règle n'a été appliquée, la boucle doit s'arrêter
            if (!ruleApplied) {
                break;}
        }

        // Vérifier si PropVerif est dans BF
        displayRulesAndFacts(BR,BF);
    }
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
    // Méthode pour vérifier si une règle est applicable
    private static boolean isRuleApplicable(RegleSimple regle, ArrayList<String> BF) {
        // Vérifier si la prémisse de la règle est contenue dans BF
        return BF.contains(regle.getPremisse());
    }

    // Méthode pour appliquer une règle
    private static void applyRule(RegleSimple regle, ArrayList<String> BF) {
        // Ajouter la conclusion de la règle à BF
        BF.add(regle.getConclusion());
    }
    
    
///////////////////////COMPLEX
//surcharge CAD plusieur fct mem nom mais type ou para diferents
    
    public static boolean chainageAvantcomplex(ArrayList<Reglecomplex> BRC, ArrayList<String> BFC, String PropVerif) {
        while (!BFC.contains(PropVerif) && !BRC.isEmpty()) {
            boolean ruleApplied = false;

            // Recherche d'une règle applicable
            for (int i = 0; i < BRC.size();) {
            	Reglecomplex tabregle = BRC.get(i);
            	//tester existance pour ne pas lajouetr 2 fois ou plus 
                if (isRulecomplexApplicable(tabregle, BFC) && !BFC.contains(tabregle)) {
                    // Appliquer la règle
                    applyRulecomplex(tabregle, BFC);
                    // Supprimer la règle de la BR
                    BRC.remove(tabregle);
                    ruleApplied = true;
                }
                i+=1;
            
            }

            // Si aucune règle n'a été appliquée, la boucle doit s'arrêter
            if (!ruleApplied) {
                break;}
        }
     // Vérifier si PropVerif est dans BF
        return BFC.contains(PropVerif);
}
/////////corection confusion lors iteration pour remove 
    
    public static boolean chainageAvantcomplexcorig(ArrayList<Reglecomplex> BRC, ArrayList<String> BFC,String PropVerif) {
        Iterator<Reglecomplex> iterator = BRC.iterator();
        
        while (!BFC.contains(PropVerif) && iterator.hasNext()) {
            boolean ruleApplied = false;

            // Recherche d'une règle applicable
            Reglecomplex tabregle = iterator.next();
            
            // Tester existence pour ne pas l'ajouter 2 fois ou plus
            if (isRulecomplexApplicable(tabregle, BFC) && !BFC.contains(tabregle.getConclusion())) {
                // Appliquer la règle
                applyRulecomplex(tabregle, BFC);
                
                // Supprimer la règle de la BR using iterator's remove method
                iterator.remove();
                
                ruleApplied = true;
            }

            // Si aucune règle n'a été appliquée, la boucle doit s'arrêter
            if (!ruleApplied) {
                break;
            }
        }

        // Vérifier si PropVerif est dans BF
        return BFC.contains(PropVerif);
    }

    public static void chainageAvantcomplexsansprop(ArrayList<Reglecomplex> BRC, ArrayList<String> BFC) {
        while ( !BRC.isEmpty()) {
            boolean ruleApplied = false;

            // Recherche d'une règle applicable
            for (int i = 0; i < BRC.size();) {
            	Reglecomplex tabregle = BRC.get(i);
            	//tester existance pour ne pas lajouetr 2 fois ou plus 
                if (isRulecomplexApplicableupdate(tabregle, BFC)) {
                    // Appliquer la règle
                    applyRulecomplex(tabregle, BFC);
                    // Supprimer la règle de la BR
                    BRC.remove(tabregle);
                    ruleApplied = true;
                }
                i+=1;
            
            }

            // Si aucune règle n'a été appliquée, la boucle doit s'arrêter
            if (!ruleApplied) {
                break;}
        }
     // Vérifier si PropVerif est dans BF
        displaycomplexRulesAndFacts(BRC,BFC);
}
    
    
    
    // Méthode pour le chaînage avant complex
    
    // Méthode pour vérifier si une règle est applicable
    private static boolean isRulecomplexApplicable(Reglecomplex tabregle, ArrayList<String> BF) {
        // Vérifier si la prémisse de la règle est contenue dans BF
    	int i = 0;
    	int j = 0;
    	int ln= tabregle.getPremissecomplex().length;
        while (i < ln) {
        	if (BF.contains(tabregle.getPremissecomplex()[i])){
        		j+=1;
        	}
        	i+=1;
        	}
        if (j == ln) {
            return true;
        } else {
            return false;
        }
    }

    // Méthode pour appliquer une règle
    private static void applyRulecomplex(Reglecomplex tabregle, ArrayList<String> BF) {
        // Ajouter la conclusion de la règle à BF
        BF.add(tabregle.getConclusion());
    }
////////////Methode pour isRulecomplexApplicable pour le dernier exo
 //logique: si non_feuil cad que la caracteristique feuille n"existe pas dans BF d'ou non_feuil vrai si elle n'existe 
    //en considerant que la base des fait est la description d'une plante qu'on veut caracteriser ou classer .
    
    private static boolean isRulecomplexApplicableupdate(Reglecomplex tabregle, ArrayList<String> BF) {     
        // Vérifier si la prémisse de la règle est contenue dans BF
    	int i = 0;
    	int j = 0;
    	int ln= tabregle.getPremissecomplex().length;
        while (i < ln) {
        	if (verite(tabregle.getPremissecomplex()[i], BF)){
        		j+=1;
        	}
        	i+=1;
        	}
        if (j == ln) {
            return true;
        } else {
            return false;
        }
         }
    
    private static boolean verite(String premiss, ArrayList<String> BF) {
        boolean verif = true;  // Initialisez à true par défaut

            // Vérifier si la prémisse est négative (commence par "non_")
            if (premiss.startsWith("non_")) {
                // La prémisse est négative, vérifier qu'elle est pas présente ou non dans BF
                if (BF.contains(premiss.substring(4))) {
                    verif = false;  // si oui donc la negation est fausse 
                } else {
                    verif = true;
                }
            } else {
                // La prémisse est positive, vérifier qu'elle est présente dans BF
                if (!BF.contains(premiss)) {
                    verif = false;  // sil nexiste pas donc la premiss est fausse 
                } else {
                    verif = true;
                }
            }

        return verif;
    }


 }




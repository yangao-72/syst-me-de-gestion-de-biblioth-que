package data_structures;

import models.Livre;

public class BookArray {
    
    private Livre[] livres;  
    private int taille;  
    private static final int CAPACITE_PAR_DEFAUT = 100;
    
    public BookArray() {
        this.livres = new Livre[CAPACITE_PAR_DEFAUT];
        this.taille = 0;
    }
    
    public BookArray(int capacite) {
        this.livres = new Livre[capacite];
        this.taille = 0;
    }
    
    public boolean ajouterLivre(Livre livre) {
        if (taille >= livres.length) {
            agrandirTableau();
        }
        livres[taille] = livre;
        taille++;
        return true;
    }
    



    private void agrandirTableau() {
        Livre[] nouveauTableau = new Livre[livres.length * 2];
        System.arraycopy(livres, 0, nouveauTableau, 0, taille);
        livres = nouveauTableau;
    }
    
    public boolean supprimerLivre(String isbn) {
        int index = trouverIndexParISBN(isbn);
        if (index == -1) {
            return false;
        }
        
        
        for (int i = index; i < taille - 1; i++) {
            livres[i] = livres[i + 1];
        }
        livres[taille - 1] = null;
        taille--;
        return true;
    }
    
    public boolean mettreAJourLivre(String isbn, Livre nouveauLivre) {
        int index = trouverIndexParISBN(isbn);
        if (index == -1) {
            return false;
        }
        livres[index] = nouveauLivre;
        return true;
    }
    
    private int trouverIndexParISBN(String isbn) {
        for (int i = 0; i < taille; i++) {
            if (livres[i].getIsbn().equals(isbn)) {
                return i;
            }
        }
        return -1;
    }
    
    public Livre[] getTousLesLivres() {
        Livre[] resultat = new Livre[taille];
        System.arraycopy(livres, 0, resultat, 0, taille);
        return resultat;
    }
    




    public int getNombreDeLivres() {
        return taille;
    }
    
    public Livre rechercheLineaireParTitre(String titre) {
        System.out.println("\n--- Recherche Linéaire par Titre ---");
        for (int i = 0; i < taille; i++) {
            if (livres[i].getTitre().equalsIgnoreCase(titre)) {
                return livres[i];
            }
        }
        return null;
    }
    



    public Livre rechercheLineaireParAuteur(String auteur) {
        System.out.println("\n--- Recherche Linéaire par Auteur ---");
        for (int i = 0; i < taille; i++) {
            if (livres[i].getAuteur().equalsIgnoreCase(auteur)) {
                return livres[i];
            }
        }
        return null;
    }
    



    public Livre rechercheLineaireParISBN(String isbn) {
        System.out.println("\n--- Recherche Linéaire par ISBN ---");
        for (int i = 0; i < taille; i++) {
            if (livres[i].getIsbn().equals(isbn)) {
                return livres[i];
            }
        }
        return null;
    }
    
    public Livre rechercheBinaireParTitre(String titre) {
        System.out.println("\n--- Recherche Binaire par Titre ---");
        // Tri préalable requis
        triRapideParTitre();
        
        int gauche = 0;
        int droite = taille - 1;
        
        while (gauche <= droite) {
            int milieu = (gauche + droite) / 2;
            int comparaison = livres[milieu].getTitre().compareToIgnoreCase(titre);
            
            if (comparaison == 0) {
                return livres[milieu];
            } else if (comparaison < 0) {
                gauche = milieu + 1; 
            } else {
                droite = milieu - 1; 
            }
        }
        return null;
    }
    
    public Livre rechercheBinaireParAuteur(String auteur) {
        System.out.println("\n--- Recherche Binaire par Auteur ---");
        triRapideParAuteur();
        
        int gauche = 0;
        int droite = taille - 1;
        
        while (gauche <= droite) {
            int milieu = (gauche + droite) / 2;
            int comparaison = livres[milieu].getAuteur().compareToIgnoreCase(auteur);
            
            if (comparaison == 0) {
                return livres[milieu];
            } else if (comparaison < 0) {
                gauche = milieu + 1;
            } else {
                droite = milieu - 1;
            }
        }
        return null;
    }
    
    public Livre rechercheBinaireParISBN(String isbn) {
        System.out.println("\n--- Recherche Binaire par ISBN ---");
        triRapideParISBN();
        
        int gauche = 0;
        int droite = taille - 1;
        
        while (gauche <= droite) {
            int milieu = (gauche + droite) / 2;
            int comparaison = livres[milieu].getIsbn().compareTo(isbn);
            
            if (comparaison == 0) {
                return livres[milieu];
            } else if (comparaison < 0) {
                gauche = milieu + 1;
            } else {
                droite = milieu - 1;
            }
        }
        return null;
    }
    
    public void triBubbleSortParTitre() {
        System.out.println("\n--- Tri à Bulles par Titre ---");
        Livre[] temp = copierTableau();
        
        for (int i = 0; i < taille - 1; i++) {
            boolean echanger = false;
            
            for (int j = 0; j < taille - i - 1; j++) {
                if (temp[j].getTitre().compareTo(temp[j + 1].getTitre()) > 0) {
                   
                    Livre tempLivre = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempLivre;
                    echanger = true;
                }
            }
            
            if (!echanger) {
                break;
            }
        }
        
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    
    
    
    public void triBubbleSortParAuteur() {
        System.out.println("\n--- Tri à Bulles par Auteur ---");
        Livre[] temp = copierTableau();
        
        for (int i = 0; i < taille - 1; i++) {
            boolean echanger = false;
            
            for (int j = 0; j < taille - i - 1; j++) {
                if (temp[j].getAuteur().compareTo(temp[j + 1].getAuteur()) > 0) {
                    Livre tempLivre = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempLivre;
                    echanger = true;
                }
            }
            
            if (!echanger) {
                break;
            }
        }
        
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
   
   
   
    public void triBubbleSortParAnnee() {
        System.out.println("\n--- Tri à Bulles par Année ---");
        Livre[] temp = copierTableau();
        
        for (int i = 0; i < taille - 1; i++) {
            boolean echanger = false;
            
            for (int j = 0; j < taille - i - 1; j++) {
                if (temp[j].getAnneePublication() > temp[j + 1].getAnneePublication()) {
                    Livre tempLivre = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = tempLivre;
                    echanger = true;
                }
            }
            
            if (!echanger) {
                break;
            }
        }
        
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    public void triSelectionSortParTitre() {
        System.out.println("\n--- Tri par Sélection par Titre ---");
        Livre[] temp = copierTableau();
        
        for (int i = 0; i < taille - 1; i++) {
            // Trouver l'index du minimum dans la partie non triée
            int indexMin = i;
            
            for (int j = i + 1; j < taille; j++) {
                if (temp[j].getTitre().compareTo(temp[indexMin].getTitre()) < 0) {
                    indexMin = j;
                }
            }
            
            // Échanger le minimum avec le premier élément non trié
            Livre tempLivre = temp[indexMin];
            temp[indexMin] = temp[i];
            temp[i] = tempLivre;
        }
        
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    



    public void triSelectionSortParAuteur() {
        System.out.println("\n--- Tri par Sélection par Auteur ---");
        Livre[] temp = copierTableau();
        
        for (int i = 0; i < taille - 1; i++) {
            int indexMin = i;
            
            for (int j = i + 1; j < taille; j++) {
                if (temp[j].getAuteur().compareTo(temp[indexMin].getAuteur()) < 0) {
                    indexMin = j;
                }
            }
            
            Livre tempLivre = temp[indexMin];
            temp[indexMin] = temp[i];
            temp[i] = tempLivre;
        }
        
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    public void triSelectionSortParAnnee() {
        System.out.println("\n--- Tri par Sélection par Année ---");
        Livre[] temp = copierTableau();
        
        for (int i = 0; i < taille - 1; i++) {
            int indexMin = i;
            
            for (int j = i + 1; j < taille; j++) {
                if (temp[j].getAnneePublication() < temp[indexMin].getAnneePublication()) {
                    indexMin = j;
                }
            }
            
            Livre tempLivre = temp[indexMin];
            temp[indexMin] = temp[i];
            temp[i] = tempLivre;
        }
        
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    public void triRapideParTitre() {
        System.out.println("\n--- Tri Rapide par Titre ---");
        Livre[] temp = copierTableau();
        triRapideRecursif(temp, 0, taille - 1, "titre");
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    
    
    
    public void triRapideParAuteur() {
        System.out.println("\n--- Tri Rapide par Auteur ---");
        Livre[] temp = copierTableau();
        triRapideRecursif(temp, 0, taille - 1, "auteur");
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    public void triRapideParISBN() {
        System.out.println("\n--- Tri Rapide par ISBN ---");
        Livre[] temp = copierTableau();
        triRapideRecursif(temp, 0, taille - 1, "isbn");
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    public void triRapideParAnnee() {
        System.out.println("\n--- Tri Rapide par Année ---");
        Livre[] temp = copierTableau();
        triRapideRecursif(temp, 0, taille - 1, "annee");
        System.arraycopy(temp, 0, livres, 0, taille);
    }
    
    private void triRapideRecursif(Livre[] tableau, int debut, int fin, String critere) {
    
        if (debut < fin) {
            int indexPivot = partitionner(tableau, debut, fin, critere);
            
            triRapideRecursif(tableau, debut, indexPivot - 1, critere);
            triRapideRecursif(tableau, indexPivot + 1, fin, critere);
        }
    }
    
    private int partitionner(Livre[] tableau, int debut, int fin, String critere) {
       
        Livre pivot = tableau[fin];
        int i = debut - 1;  
        
        for (int j = debut; j < fin; j++) {
            
            boolean plusPetit = false;
            
            switch (critere) {
                case "titre":
                    plusPetit = tableau[j].getTitre().compareTo(pivot.getTitre()) <= 0;
                    break;
                case "auteur":
                    plusPetit = tableau[j].getAuteur().compareTo(pivot.getAuteur()) <= 0;
                    break;
                case "isbn":
                    plusPetit = tableau[j].getIsbn().compareTo(pivot.getIsbn()) <= 0;
                    break;
                case "annee":
                    plusPetit = tableau[j].getAnneePublication() <= pivot.getAnneePublication();
                    break;
            }
            
            if (plusPetit) {
                i++;
                
                Livre temp = tableau[i];
                tableau[i] = tableau[j];
                tableau[j] = temp;
            }
        }
        
       
        Livre temp = tableau[i + 1];
        tableau[i + 1] = tableau[fin];
        tableau[fin] = temp;
        
        return i + 1;
    }
    
   
   
   
    private Livre[] copierTableau() {
        Livre[] copie = new Livre[taille];
        System.arraycopy(livres, 0, copie, 0, taille);
        return copie;
    }
    
    
    public void afficherLivres() {
        if (taille == 0) {
            System.out.println("Aucun livre dans la bibliothèque.");
            return;
        }
        
        System.out.println("\n=== Liste des Livres ===");
        System.out.println("+---------------------------+----------------------+-----------------+------+-----------------+");
        System.out.println("| Titre                     | Auteur               | ISBN            | Année| Genre           |");
        System.out.println("+---------------------------+----------------------+-----------------+------+-----------------+");
        
        for (int i = 0; i < taille; i++) {
            System.out.println(livres[i].toString());
        }
        
        System.out.println("+---------------------------+----------------------+-----------------+------+-----------------+");
        System.out.println("Total: " + taille + " livre(s)");
    }
}

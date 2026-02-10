package services;

import data_structures.ActivityStack;
import data_structures.BookArray;
import data_structures.LinkedList;
import models.Livre;

import java.util.Scanner;

public class BibliothequeService {
    
   
    private BookArray collectionLivres;
    private LinkedList<String> historiqueEmprunts;  
    private ActivityStack<String> activitesRecentes;
    

    private Scanner scanner;
    
    public BibliothequeService() {
        this.collectionLivres = new BookArray();
        this.historiqueEmprunts = new LinkedList<>();
        this.activitesRecentes = new ActivityStack<>(50);  
        this.scanner = new Scanner(System.in);
    }
    
    // ==================== GESTION DES LIVRES ====================
    
    public boolean ajouterLivre() {
        System.out.println("\n=== Ajout d'un Nouveau Livre ===");
        
        System.out.print("Titre: ");
        String titre = scanner.nextLine().trim();
        
        System.out.print("Auteur: ");
        String auteur = scanner.nextLine().trim();
        
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        System.out.print("Année de publication: ");
        int annee = scanner.nextInt();
        scanner.nextLine();  
        
        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();
        
        Livre nouveauLivre = new Livre(titre, auteur, isbn, annee, genre);
        boolean succes = collectionLivres.ajouterLivre(nouveauLivre);
        
        if (succes) {
            String activite = "Ajout du livre: " + titre + " (" + auteur + ")";
            activitesRecentes.push(activite);
            System.out.println("Livre ajoute avec succes!");
        } else {
            System.out.println("Erreur lors de l'ajout du livre.");
        }
        
        return succes;
    }
    
    public void supprimerLivre() {
        System.out.println("\n=== Suppression d'un Livre ===");
        System.out.print("Entrez l'ISBN du livre à supprimer: ");
        String isbn = scanner.nextLine().trim();
        
        Livre livre = collectionLivres.rechercheLineaireParISBN(isbn);
        if (livre == null) {
            System.out.println(" Livre non trouvé avec l'ISBN: " + isbn);
            return;
        }
        
        boolean succes = collectionLivres.supprimerLivre(isbn);
        if (succes) {
            String activite = "Suppression du livre: " + livre.getTitre();
            activitesRecentes.push(activite);
            System.out.println("Livre '" + livre.getTitre() + "' supprime avec succes!");
        } else {
            System.out.println("Erreur lors de la suppression.");
        }
    }
    



    public void mettreAJourLivre() {
        System.out.println("\n=== Mise à Jour d'un Livre ===");
        System.out.print("Entrez l'ISBN du livre à modifier: ");
        String isbn = scanner.nextLine().trim();
        
        Livre livreExistant = collectionLivres.rechercheLineaireParISBN(isbn);
        if (livreExistant == null) {
            System.out.println(" Livre non trouvé avec l'ISBN: " + isbn);
            return;
        }
        
        System.out.println("\nLivre trouvé: " + livreExistant.getTitre());
        System.out.println("Laissez vide pour conserver la valeur actuelle.\n");
        
        System.out.print("Nouveau titre [" + livreExistant.getTitre() + "]: ");
        String titre = scanner.nextLine().trim();
        if (titre.isEmpty()) {
            titre = livreExistant.getTitre();
        }
        
        System.out.print("Nouvel auteur [" + livreExistant.getAuteur() + "]: ");
        String auteur = scanner.nextLine().trim();
        if (auteur.isEmpty()) {
            auteur = livreExistant.getAuteur();
        }
        
        System.out.print("Nouvelle année [" + livreExistant.getAnneePublication() + "]: ");
        String anneeStr = scanner.nextLine().trim();
        int annee = livreExistant.getAnneePublication();
        if (!anneeStr.isEmpty()) {
            annee = Integer.parseInt(anneeStr);
        }
        
        System.out.print("Nouveau genre [" + livreExistant.getGenre() + "]: ");
        String genre = scanner.nextLine().trim();
        if (genre.isEmpty()) {
            genre = livreExistant.getGenre();
        }
        
        Livre nouveauLivre = new Livre(titre, auteur, isbn, annee, genre);
        boolean succes = collectionLivres.mettreAJourLivre(isbn, nouveauLivre);
        
        if (succes) {
            String activite = "Mise a jour du livre: " + titre;
            activitesRecentes.push(activite);
            System.out.println("Livre mis a jour avec succes!");
        } else {
            System.out.println("Erreur lors de la mise a jour.");
        }
    }
    



    public void afficherTousLesLivres() {
        collectionLivres.afficherLivres();
    }
    
    // ==================== FONCTIONNALITÉS DE RECHERCHE ====================
    
    public void rechercherLivre() {
        System.out.println("\n=== Recherche de Livres ===");
        System.out.println("1. Rechercher par titre");
        System.out.println("2. Rechercher par auteur");
        System.out.println("3. Rechercher par ISBN");
        System.out.print("Votre choix: ");
        int choix = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Entrez le terme de recherche: ");
        String terme = scanner.nextLine().trim();
        
        System.out.println("\nChoisir la méthode de recherche:");
        System.out.println("1. Recherche Linéaire (O(n)) - Fonctionne sur tableau non trié");
        System.out.println("2. Recherche Binaire (O(log n)) - Requiert tableau trié");
        System.out.print("Votre choix: ");
        int methode = scanner.nextInt();
        scanner.nextLine();
        
        Livre resultat = null;
        long debut = System.nanoTime();
        
        switch (choix) {
            case 1:  // Par titre
                if (methode == 1) {
                    resultat = collectionLivres.rechercheLineaireParTitre(terme);
                } else {
                    resultat = collectionLivres.rechercheBinaireParTitre(terme);
                }
                break;
                
            case 2:  // Par auteur
                if (methode == 1) {
                    resultat = collectionLivres.rechercheLineaireParAuteur(terme);
                } else {
                    resultat = collectionLivres.rechercheBinaireParAuteur(terme);
                }
                break;
                
            case 3:  // Par ISBN
                if (methode == 1) {
                    resultat = collectionLivres.rechercheLineaireParISBN(terme);
                } else {
                    resultat = collectionLivres.rechercheBinaireParISBN(terme);
                }
                break;
                
            default:
                System.out.println(" Option invalide.");
                return;
        }
        
        long fin = System.nanoTime();
        double tempsMicrosecondes = (fin - debut) / 1000.0;
        
        if (resultat != null) {
            System.out.println("\n Livre trouvé!");
            System.out.println(resultat.toString());
            String activite = "Recherche: " + terme + " (trouvé)";
            activitesRecentes.push(activite);
        } else {
            System.out.println("\n Livre non trouvé.");
            String activite = "Recherche: " + terme + " (non trouvé)";
            activitesRecentes.push(activite);
        }
        
        System.out.printf("Temps de recherche: %.2f µs\n", tempsMicrosecondes);
    }
    
    // ==================== FONCTIONNALITÉS DE TRI ====================
    
    public void trierLivres() {
        System.out.println("\n=== Tri des Livres ===");
        
        if (collectionLivres.getNombreDeLivres() < 2) {
            System.out.println(" Pas assez de livres pour trier (moins de 2).");
            return;
        }
        
        System.out.println("Choisir le critère de tri:");
        System.out.println("1. Par titre");
        System.out.println("2. Par auteur");
        System.out.println("3. Par année de publication");
        System.out.print("Votre choix: ");
        int critere = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("\nChoisir l'algorithme de tri:");
        System.out.println("1. Tri à Bulles (Bubble Sort) - O(n²)");
        System.out.println("2. Tri par Sélection (Selection Sort) - O(n²)");
        System.out.println("3. Tri Rapide (Quick Sort) - O(n log n)");
        System.out.print("Votre choix: ");
        int algorithme = scanner.nextInt();
        scanner.nextLine();
        
        long debut = System.nanoTime();
        
        switch (critere) {
            case 1:  
                switch (algorithme) {
                    case 1:
                        collectionLivres.triBubbleSortParTitre();
                        break;
                    case 2:
                        collectionLivres.triSelectionSortParTitre();
                        break;
                    case 3:
                        collectionLivres.triRapideParTitre();
                        break;
                }
                break;
                
            case 2:  
                switch (algorithme) {
                    case 1:
                        collectionLivres.triBubbleSortParAuteur();
                        break;
                    case 2:
                        collectionLivres.triSelectionSortParAuteur();
                        break;
                    case 3:
                        collectionLivres.triRapideParAuteur();
                        break;
                }
                break;
                
            case 3: 
                switch (algorithme) {
                    case 1:
                        collectionLivres.triBubbleSortParAnnee();
                        break;
                    case 2:
                        collectionLivres.triSelectionSortParAnnee();
                        break;
                    case 3:
                        collectionLivres.triRapideParAnnee();
                        break;
                }
                break;
                
            default:
                System.out.println(" Critère invalide.");
                return;
        }
        
        long fin = System.nanoTime();
        double tempsMicrosecondes = (fin - debut) / 1000.0;
        
        String critereStr = (critere == 1) ? "titre" : (critere == 2) ? "auteur" : "année";
        String algoStr = (algorithme == 1) ? "Bubble Sort" : 
                         (algorithme == 2) ? "Selection Sort" : "Quick Sort";
        
        String activite = "Tri des livres par " + critereStr + " (" + algoStr + ")";
        activitesRecentes.push(activite);
        
        System.out.println("✅ Tri effectué avec succès!");
        System.out.printf("Temps de tri: %.2f µs\n", tempsMicrosecondes);
        
        
        collectionLivres.afficherLivres();
    }
    
    // ==================== GESTION DES EMPRUNTS ====================
    
    public void enregistrerEmprunt() {
        System.out.println("\n=== Enregistrement d'un Emprunt ===");
        
        System.out.print("Entrez l'ISBN du livre emprunté: ");
        String isbn = scanner.nextLine().trim();
        
        Livre livre = collectionLivres.rechercheLineaireParISBN(isbn);
        if (livre == null) {
            System.out.println(" Livre non trouvé avec l'ISBN: " + isbn);
            return;
        }
        
        System.out.print("Entrez le nom de l'emprunteur: ");
        String emprunteur = scanner.nextLine().trim();
        
        String historique = livre.getTitre() + " → " + emprunteur;
        historiqueEmprunts.add(historique);
        
        String activite = "Emprunt: '" + livre.getTitre() + "' par " + emprunteur;
        activitesRecentes.push(activite);
        
        System.out.println(" Emprunt enregistré avec succès!");
    }
    
    
     //Affiche l'historique des emprunts.
     
    public void afficherHistoriqueEmprunts() {
        System.out.println("\n=== Historique des Emprunts ===");
        
        if (historiqueEmprunts.isEmpty()) {
            System.out.println("Aucun emprunt enregistré.");
            return;
        }
        
        System.out.println("Nombre d'emprunts: " + historiqueEmprunts.size());
        System.out.println("\nDerniers emprunts:");
        historiqueEmprunts.printList();
    }
    
    // ==================== ACTIVITÉS RÉCENTES ====================
    


     
    public void afficherActivitesRecentes() {
        System.out.println("\n=== Activités Récentes ===");
        activitesRecentes.printRecent(10);
    }
    
    // ==================== CHARGEMENT DE DONNÉES DE TEST ====================
    
    /**
     * Charge des données de test dans la bibliothèque.
     */
    public void chargerDonneesTest() {
        System.out.println("\n=== Chargement des Données de Test ===");
        
        Livre[] livresTest = {
            new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "978-2-07-040850-8", 1943, "Fable"),
            new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling", "978-2-07-058405-8", 1997, "Fantasy"),
            new Livre("1984", "George Orwell", "978-2-07-036822-8", 1949, "Science-fiction"),
            new Livre("L'Alchimiste", "Paulo Coelho", "978-2-266-19156-1", 1988, "Roman"),
            new Livre("Les Misérables", "Victor Hugo", "978-2-07-040723-5", 1862, "Roman"),
            new Livre("Le Comte de Monte-Cristo", "Alexandre Dumas", "978-2-07-041234-5", 1844, "Aventure"),
            new Livre(" Notre-Dame de Paris", "Victor Hugo", "978-2-07-040722-8", 1831, "Roman"),
            new Livre(" Guerre et Paix", "Léon Tolstoï", "978-2-286-02053-8", 1869, "Roman historique"),
            new Livre("Don Quichotte", "Miguel de Cervantes", "978-2-07-036822-8", 1605, "Aventure"),
            new Livre("Cien años de soledad", "Gabriel García Márquez", "978-84-204-3164-1", 1967, "Roman")
        };
        
        for (Livre livre : livresTest) {
            collectionLivres.ajouterLivre(livre);
        }
        
        System.out.println("" + livresTest.length + " livres de test ajoutés!");
        activitesRecentes.push("Chargement des données de test");
    }
}

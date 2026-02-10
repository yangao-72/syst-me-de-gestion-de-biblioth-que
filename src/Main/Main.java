package Main;

import services.BibliothequeService;

import java.util.Scanner;


 //Interface console interactive pour le système de gestion de bibliothèque.
public class Main {
    
    private static BibliothequeService bibliotheque;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("   SYSTEME DE GESTION DE BIBLIOTHEQUE");
        System.out.println("================================================");
        
        
        bibliotheque = new BibliothequeService();
        scanner = new Scanner(System.in);
        
        
        boolean quitter = false;
        
        while (!quitter) {
            afficherMenuPrincipal();
            int choix = lireChoix();
            
            switch (choix) {
                case 1:
                    menuGestionLivres();
                    break;
                case 2:
                    menuRecherche();
                    break;
                case 3:
                    menuTri();
                    break;
                case 4:
                    menuEmprunts();
                    break;
                case 5:
                    bibliotheque.afficherActivitesRecentes();
                    break;
                case 6:
                    bibliotheque.chargerDonneesTest();
                    break;
                case 7:
                    bibliotheque.afficherTousLesLivres();
                    break;
                case 0:
                    System.out.println("\n================================================");
                    System.out.println("   Au revoir! Merci d'avoir utilise notre systeme.");
                    System.out.println("==================================================");
                    quitter = true;
                    break;
                default:
                    System.out.println("\n[!] Option invalide. Veuillez reessayer.");
            }
            
            if (!quitter) {
                System.out.print("\nAppuyez sur Entree pour continuer...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    



    private static void afficherMenuPrincipal() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║            MENU PRINCIPAL - BIBLIOTHEQUE               ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Gestion des livres (ajouter, supprimer, modifier)  ║");
        System.out.println("║  2. Rechercher un livre                                ║");
        System.out.println("║  3. Trier les livres                                   ║");
        System.out.println("║  4. Gestion des emprunts                               ║");
        System.out.println("║  5. Voir les activites recentes                        ║");
        System.out.println("║  6. Charger les donnees de test                        ║");
        System.out.println("║  7. Afficher tous les livres                           ║");
        System.out.println("║  0. Quitter                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.print("Votre choix: ");
    }
    
    private static void menuGestionLivres() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              GESTION DES LIVRES                        ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Ajouter un livre                                   ║");
        System.out.println("║  2. Supprimer un livre                                 ║");
        System.out.println("║  3. Modifier un livre                                  ║");
        System.out.println("║  4. Afficher tous les livres                           ║");
        System.out.println("║  0. Retour au menu principal                           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.print("Votre choix: ");
        
        int choix = lireChoix();
        
        switch (choix) {
            case 1:
                bibliotheque.ajouterLivre();
                break;
            case 2:
                bibliotheque.supprimerLivre();
                break;
            case 3:
                bibliotheque.mettreAJourLivre();
                break;
            case 4:
                bibliotheque.afficherTousLesLivres();
                break;
            case 0:
                return;
            default:
                System.out.println("[!] Option invalide.");
        }
    }
    
    
    
    
    private static void menuRecherche() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                RECHERCHE DE LIVRES                     ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  Cette option vous permet de rechercher des livres     ║");
        System.out.println("║  en utilisant soit la recherche lineaire, soit         ║");
        System.out.println("║  la recherche binaire (plus rapide sur tableau trie).  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        bibliotheque.rechercherLivre();
    }
    
   
   
   
    private static void menuTri() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║                  TRI DES LIVRES                        ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  Vous pouvez choisir:                                  ║");
        System.out.println("║  - Le critere de tri (titre, auteur, annee)            ║");
        System.out.println("║  - L'algorithme de tri:                                ║");
        System.out.println("║      * Bubble Sort (O(n²))                             ║");
        System.out.println("║      * Selection Sort (O(n²))                          ║");
        System.out.println("║      * Quick Sort (O(n log n)) - Plus rapide           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        bibliotheque.trierLivres();
    }
    



    private static void menuEmprunts() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              GESTION DES EMPRUNTS                      ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Enregistrer un emprunt                             ║");
        System.out.println("║  2. Afficher l'historique des emprunts                 ║");
        System.out.println("║  0. Retour au menu principal                           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.print("Votre choix: ");
        
        int choix = lireChoix();
        
        switch (choix) {
            case 1:
                bibliotheque.enregistrerEmprunt();
                break;
            case 2:
                bibliotheque.afficherHistoriqueEmprunts();
                break;
            case 0:
                return;
            default:
                System.out.println("[!] Option invalide.");
        }
    }
    
    private static int lireChoix() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

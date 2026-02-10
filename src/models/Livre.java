package models;
 // Classe représentant un livre dans la bibliothèque.
public class Livre {
    private String titre;
    private String auteur;
    private String isbn;
    private int anneePublication;
    private String genre;

    
    public Livre(String titre, String auteur, String isbn, int anneePublication, String genre) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }


    public void setAuteur(String auteur) {    
        this.auteur = auteur;
    }
   
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    @Override
    public String toString() {
        return String.format("| %-25s | %-20s | %-15s | %4d | %-15s |", 
                titre, auteur, isbn, anneePublication, genre);
    }

    public int compareParTitre(Livre autreLivre) {
        return this.titre.compareTo(autreLivre.titre);
    }

    public int compareParAuteur(Livre autreLivre) {
        return this.auteur.compareTo(autreLivre.auteur);
    }

    public int compareParAnnee(Livre autreLivre) {
        return Integer.compare(this.anneePublication, autreLivre.anneePublication);
    }
}

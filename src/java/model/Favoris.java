package model;
// Generated 7 janv. 2020 21:01:58 by Hibernate Tools 4.3.1



/**
 * Favoris generated by hbm2java
 */
public class Favoris  implements java.io.Serializable {


     private FavorisId id;
     private UtilisateurConnecte utilisateurConnecte;
     private Annonce annonce;

    public Favoris() {
    }

    public Favoris(FavorisId id, UtilisateurConnecte utilisateurConnecte, Annonce annonce) {
       this.id = id;
       this.utilisateurConnecte = utilisateurConnecte;
       this.annonce = annonce;
    }
   
    public FavorisId getId() {
        return this.id;
    }
    
    public void setId(FavorisId id) {
        this.id = id;
    }
    public UtilisateurConnecte getUtilisateurConnecte() {
        return this.utilisateurConnecte;
    }
    
    public void setUtilisateurConnecte(UtilisateurConnecte utilisateurConnecte) {
        this.utilisateurConnecte = utilisateurConnecte;
    }
    public Annonce getAnnonce() {
        return this.annonce;
    }
    
    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }




}



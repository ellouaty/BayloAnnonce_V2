package model;
// Generated 7 janv. 2020 21:01:58 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.List;




/**
 * ActionUser generated by hbm2java
 */
public class ActionUser  implements java.io.Serializable {


     private ActionUserId id;
     private UtilisateurConnecte utilisateurConnecte;
     private Annonce annonce;
     private String libelleActionUser;
     private String motifActionUser;

    public ActionUser() {
    }

	
    public ActionUser(ActionUserId id, UtilisateurConnecte utilisateurConnecte, Annonce annonce) {
        this.id = id;
        this.utilisateurConnecte = utilisateurConnecte;
        this.annonce = annonce;
    }
    public ActionUser(ActionUserId id, UtilisateurConnecte utilisateurConnecte, Annonce annonce, String libelleActionUser, String motifActionUser) {
       this.id = id;
       this.utilisateurConnecte = utilisateurConnecte;
       this.annonce = annonce;
       this.libelleActionUser = libelleActionUser;
       this.motifActionUser = motifActionUser;
    }
   
    public ActionUserId getId() {
        return this.id;
    }
    
    public void setId(ActionUserId id) {
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
    public String getLibelleActionUser() {
        return this.libelleActionUser;
    }
    
    public void setLibelleActionUser(String libelleActionUser) {
        this.libelleActionUser = libelleActionUser;
    }
    public String getMotifActionUser() {
        return this.motifActionUser;
    }
    
    public void setMotifActionUser(String motifActionUser) {
        this.motifActionUser = motifActionUser;
    }





}



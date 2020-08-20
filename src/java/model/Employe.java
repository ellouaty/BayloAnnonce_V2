package model;
 
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
 
public class Employe  implements java.io.Serializable {


     private BigDecimal idEmploye;
     private String nomEmploye;
     private String prenomEmploye;
     private String emailEmploye;
     private String loginEmploye;
     private String passwordEmploye;
     private String rolesEmploye;
     private Set reclamations = new HashSet(0);
     private int nbAnAccepted;
     private int nbAnRefused;
     private int NbanModr;

    public Employe() {
    }

	
    public Employe(BigDecimal idEmploye) {
        this.idEmploye = idEmploye;
    }
    public Employe(BigDecimal idEmploye, String nomEmploye, String prenomEmploye, String emailEmploye, String loginEmploye, String passwordEmploye, String rolesEmploye, Set reclamations) {
       this.idEmploye = idEmploye;
       this.nomEmploye = nomEmploye;
       this.prenomEmploye = prenomEmploye;
       this.emailEmploye = emailEmploye;
       this.loginEmploye = loginEmploye;
       this.passwordEmploye = passwordEmploye;
       this.rolesEmploye = rolesEmploye;
       this.reclamations = reclamations;
    }
   
    public BigDecimal getIdEmploye() {
        return this.idEmploye;
    }
    
    public void setIdEmploye(BigDecimal idEmploye) {
        this.idEmploye = idEmploye;
    }
    public String getNomEmploye() {
        return this.nomEmploye;
    }
    
    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }
    public String getPrenomEmploye() {
        return this.prenomEmploye;
    }
    
    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }
    public String getEmailEmploye() {
        return this.emailEmploye;
    }
    
    public void setEmailEmploye(String emailEmploye) {
        this.emailEmploye = emailEmploye;
    }
    public String getLoginEmploye() {
        return this.loginEmploye;
    }
    
    public void setLoginEmploye(String loginEmploye) {
        this.loginEmploye = loginEmploye;
    }
    public String getPasswordEmploye() {
        return this.passwordEmploye;
    }
    
    public void setPasswordEmploye(String passwordEmploye) {
        this.passwordEmploye = passwordEmploye;
    }
    public String getRolesEmploye() {
        return this.rolesEmploye;
    }
    
    public void setRolesEmploye(String rolesEmploye) {
        this.rolesEmploye = rolesEmploye;
    }
    public Set getReclamations() {
        return this.reclamations;
    }
    
    public void setReclamations(Set reclamations) {
        this.reclamations = reclamations;
    }

    public int getNbAnAccepted() {
        return nbAnAccepted;
    }

    public void setNbAnAccepted(int nbAnAccepted) {
        this.nbAnAccepted = nbAnAccepted;
    }

    public int getNbAnRefused() {
        return nbAnRefused;
    }

    public void setNbAnRefused(int nbAnRefused) {
        this.nbAnRefused = nbAnRefused;
    }

    public int getNbanModr() {
        return NbanModr;
    }

    public void setNbanModr(int NbanModr) {
        this.NbanModr = NbanModr;
    }

   


}



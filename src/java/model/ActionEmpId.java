package model;
// Generated 7 janv. 2020 21:01:58 by Hibernate Tools 4.3.1


import java.math.BigDecimal;

/**
 * ActionEmpId generated by hbm2java
 */
public class ActionEmpId  implements java.io.Serializable {


     private BigDecimal idEmploye;
     private BigDecimal idAnnonce;
     private String dateActionEmp;
     private String heureActionEmp;

    public ActionEmpId() {
    }

    public ActionEmpId(BigDecimal idEmploye, BigDecimal idAnnonce, String dateActionEmp, String heureActionEmp) {
       this.idEmploye = idEmploye;
       this.idAnnonce = idAnnonce;
       this.dateActionEmp = dateActionEmp;
       this.heureActionEmp = heureActionEmp;
    }
   
    public BigDecimal getIdEmploye() {
        return this.idEmploye;
    }
    
    public void setIdEmploye(BigDecimal idEmploye) {
        this.idEmploye = idEmploye;
    }
    public BigDecimal getIdAnnonce() {
        return this.idAnnonce;
    }
    
    public void setIdAnnonce(BigDecimal idAnnonce) {
        this.idAnnonce = idAnnonce;
    }
    public String getDateActionEmp() {
        return this.dateActionEmp;
    }
    
    public void setDateActionEmp(String dateActionEmp) {
        this.dateActionEmp = dateActionEmp;
    }
    public String getHeureActionEmp() {
        return this.heureActionEmp;
    }
    
    public void setHeureActionEmp(String heureActionEmp) {
        this.heureActionEmp = heureActionEmp;
    }




}



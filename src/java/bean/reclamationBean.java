/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ReclamationDao;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map; 
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import model.Employe;
import model.Reclamation;
import model.ReclamationId;
import org.primefaces.context.RequestContext;
import session.Util;

/**
 *
 * @author ibm
 */
 
@Named
@SessionScoped
public class reclamationBean  implements Serializable{
      @Size(min=1 ,message ="Valeur recherch?") 
        private String paramSearch;
       @NotNull(message = "Option?") 
       private String optionSearch; 
 private List<Reclamation>list_rec=new ArrayList<>();
  private List<Reclamation>list_rec_nonTrait=new ArrayList<>();
  private Reclamation recToEdit;
    public String getOptionSearch() {
        return optionSearch;
    }

    public void setOptionSearch(String optionSearch) {
        this.optionSearch = optionSearch;
    }
     

    public String getParamSearch() {
        return paramSearch;
    }

    public void setParamSearch(String paramSearch) {
        this.paramSearch = paramSearch;
    }

    public List<Reclamation> getList_rec() {
        return list_rec;
    }

    public Reclamation getRecToEdit() {
        return recToEdit;
    }

    public void setRecToEdit(Reclamation recToEdit) {
        this.recToEdit = recToEdit;
    }

    public void setList_rec(List<Reclamation> list_rec) {
        this.list_rec = list_rec;
    }

    public List<Reclamation> getList_rec_nonTrait() {
        return list_rec_nonTrait;
    }

    public void setList_rec_nonTrait(List<Reclamation> list_rec_nonTrait) {
        this.list_rec_nonTrait = list_rec_nonTrait;
    }
  
    public  void recupReclamations(){
        list_rec.clear();
        list_rec_nonTrait.clear();
        ReclamationDao recdao=new ReclamationDao();
        List<Reclamation> list=recdao.getReclamations();
      
       if(!list.isEmpty()){
        for (Reclamation reclamation : list) {
            if (reclamation.getEtatReclamation().equals("done")) {
                list_rec.add(reclamation);
            }else{
                list_rec_nonTrait.add(reclamation);
            }} 
            
        }
    }
        public void afficherRec(){
              Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String idA=params.get("recidAnn");
              String idU=params.get("recidU");
              String d=params.get("recDate");
              String h=params.get("recHeur"); 
                 
                 ReclamationId recId=new ReclamationId();
                 recId.setIdAnnonce(new BigDecimal(idA.trim()));
                 recId.setIdUtilisateurConnecte(new BigDecimal(idU.trim()));
                 recId.setDateReclamation(d.trim());
                 recId.setHeureReclamation(h.trim());
                
              ReclamationDao recDao=new ReclamationDao();
               recToEdit =recDao.getById(recId); 
              RequestContext.getCurrentInstance().execute("PF('dialogReclam').show();");
        }
   public  void traiterRec() throws IOException{ 
         Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String idA=params.get("recidAnn");
              String idU=params.get("recidU");
              String d=params.get("recDate");
              String h=params.get("recHeur");
               HttpSession s=Util.getSession();
               Employe emp=(Employe) s.getAttribute("emp");   
                 ReclamationId recId=new ReclamationId();
                 recId.setIdAnnonce(new BigDecimal(idA.trim()));
                 recId.setIdUtilisateurConnecte(new BigDecimal(idU.trim()));
                 recId.setDateReclamation(d.trim());
                 recId.setHeureReclamation(h.trim());
              ReclamationDao recDao=new ReclamationDao();
             Reclamation r=recDao.getById(recId);
               r.setEtatReclamation("done");
               r.setEmploye(emp);
               
               recDao.modifierReclamation(r);
              FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Reclamations");
               
               
   }
      public void chercher() { 
        ReclamationDao  rDao=new ReclamationDao();
        /* if(optionSearch!="tel"){
           list_user =uDao.UserByCrit(capitaliser(paramSearch),optionSearch);}
         else{
              list_user =uDao.UserByCrit(paramSearch,optionSearch); 
         }
            RequestContext.getCurrentInstance().update("pnDet"); 
        
    */
    }
    
        
        
        
}

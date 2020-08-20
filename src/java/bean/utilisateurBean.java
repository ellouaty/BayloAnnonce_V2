/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UtilisateurConnecteDao;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;  
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import model.UtilisateurConnecte;
import org.primefaces.context.RequestContext;
/**
 *
 * @author ibm
 */
@Named 
@SessionScoped
public class utilisateurBean implements Serializable{
     private List<UtilisateurConnecte>list_user =new ArrayList<>();
    @Size(min=1 ,message ="Valeur recherch?") 
        private String paramSearch;
       @NotNull(message = "Option?") 
       private String optionSearch; 
     @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email invalid")
     private String emailTobloque; 
      @Pattern(regexp = "^0[567]\\d{8}$",message = "Numero de Telphone invalid")
       private String telTobloque;
      private String ipTobloque;
      private BigDecimal idUtil;
      private String nomUtil;
      private List<String>ips_Util=new ArrayList<String>();
      private String telUtil;
      private String emailUtil;
      private String villeUtil;
      private String etatUtil;
   public void remplireListUser(){
        UtilisateurConnecteDao utilDao=new UtilisateurConnecteDao();
       list_user =utilDao.getUtilisateurConnectes();
   
    }

    public List<UtilisateurConnecte> getList_user() {
        return list_user;
    }

    public void setList_user(List<UtilisateurConnecte> list_user) {
        this.list_user = list_user;
    }

    public String getEmailTobloque() {
        return emailTobloque;
    }

    public void setEmailTobloque(String emailTobloque) {
        this.emailTobloque = emailTobloque;
    }

    public String getTelTobloque() {
        return telTobloque;
    }

    public void setTelTobloque(String telTobloque) {
        this.telTobloque = telTobloque;
    }

     

    public String getParamSearch() {
        return paramSearch;
    }

    public void setParamSearch(String paramSearch) {
        this.paramSearch = paramSearch;
    }

    public String getOptionSearch() {
        return optionSearch;
    }

    public void setOptionSearch(String optionSearch) {
        this.optionSearch = optionSearch;
    }

    public String getIpTobloque() {
        return ipTobloque;
    }

    public BigDecimal getIdUtil() {
        return idUtil;
    }

    public void setIdUtil(BigDecimal idUtil) {
        this.idUtil = idUtil;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public List<String> getIps_Util() {
        return ips_Util;
    }

    public void setIps_Util(List<String> ips_Util) {
        this.ips_Util = ips_Util;
    }

    public String getTelUtil() {
        return telUtil;
    }

    public void setTelUtil(String telUtil) {
        this.telUtil = telUtil;
    }

    public String getEmailUtil() {
        return emailUtil;
    }

    public void setEmailUtil(String emailUtil) {
        this.emailUtil = emailUtil;
    }

    public String getVilleUtil() {
        return villeUtil;
    }

    public void setVilleUtil(String villeUtil) {
        this.villeUtil = villeUtil;
    }

    public String getEtatUtil() {
        return etatUtil;
    }

    public void setEtatUtil(String etatUtil) {
        this.etatUtil = etatUtil;
    }

    public void setIpTobloque(String ipTobloque) {
        this.ipTobloque = ipTobloque;
    }
    
    public void chercher() { 
         UtilisateurConnecteDao  uDao=new UtilisateurConnecteDao();
         if(optionSearch!="tel"){
           list_user =uDao.UserByCrit(capitaliser(paramSearch),optionSearch);}
         else{
              list_user =uDao.UserByCrit(paramSearch,optionSearch); 
         }
            RequestContext.getCurrentInstance().update("pnDet"); 
        
    
    }
    
       public String capitaliser(String mot){ 
    StringBuilder s= new StringBuilder();
    s.append(Character.toTitleCase(mot.charAt(0))).append(mot.substring(1,mot.length()).toLowerCase());
    return s.toString();
    }
       public void editerUtilisateur(){
 
           Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String p=params.get("UserToEdit");
             if(p!=null){
                 BigDecimal id=new BigDecimal(p.trim());
                 if(id!=null){
                 UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
              UtilisateurConnecte   u=udao.getById(id);
              idUtil=u.getIdUtilisateurConnecte();
              nomUtil=u.getNom();
              emailUtil=u.getEmailUtilConnecte();
              telUtil=u.getTelUtilConnecte();
             ips_Util=u.getIps();
              villeUtil=u.getVilleUtilConnecte();
              etatUtil=u.getEtatcompte();
              ips_Util=new ArrayList<>();
                 }
             }
         
     }
       public void bloquerTel() throws IOException{ 
           UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
           udao.bloquerByTel(telTobloque);
           FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Utilisateurs");
       }
        public void bloquerEmail() throws IOException{ 
             UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
           udao.bloquerByEmail(capitaliser(emailTobloque));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Utilisateurs");
       }
             public void bloquerIp() throws IOException{ 
             UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
           udao.bloquerByIp(ipTobloque );
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Utilisateurs");
       }
              public void debloquerTel() throws IOException{ 
           UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
           udao.debloquerByTel(telTobloque);
           FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Utilisateurs");
       }
        public void debloquerEmail() throws IOException{ 
             UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
           udao.debloquerByEmail(capitaliser(emailTobloque));
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Utilisateurs");
       }
             public void debloquerIp() throws IOException{ 
             UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
           udao.debloquerByIp(ipTobloque );
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Utilisateurs");
       }
   public void bloquerUser(String f) throws IOException{ 
           UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
           Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
             if(f.equals("convRecep")){ 
              String p=params.get("idutilR");
               String s=params.get("idch"); 
              idUtil=new BigDecimal(p.trim());
              udao.bloquerbyId(idUtil);   
              FacesContext.getCurrentInstance().getExternalContext().redirect("/Panel/conversation?idChat="+s); 
           }
      else  if(f.equals("convEmet")){
      
              String p=params.get("idutilE");
               String s=params.get("idch"); 
              idUtil=new BigDecimal(p.trim());
              udao.bloquerbyId(idUtil);
              FacesContext.getCurrentInstance().getExternalContext().redirect("/Panel/conversation?idChat="+s);
           }
           else  if(f.equals("util")){ 
               String p=params.get("idUtil");
               idUtil=new BigDecimal(p.trim()); 
               udao.bloquerbyId(idUtil);
                 FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Utilisateurs"); 
           } }
   public void debloquerUser(String f) throws IOException{ 
          UtilisateurConnecteDao udao=new UtilisateurConnecteDao(); 
          Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
             if(f.equals("convRecep")){
           
              String p=params.get("idutilR");
               String s=params.get("idch"); 
              idUtil=new BigDecimal(p.trim());
              udao.debloquerbyId(idUtil);
              FacesContext.getCurrentInstance().getExternalContext().redirect("/Panel/conversation?idChat="+s); 
           }
       else if(f.equals("convEmet")){
       String p=params.get("idutilE");
               String s=params.get("idch"); 
              idUtil=new BigDecimal(p.trim());
              udao.debloquerbyId(idUtil);
              FacesContext.getCurrentInstance().getExternalContext().redirect("/Panel/conversation?idChat="+s);
           }
           else  if(f.equals("util")){
               String p=params.get("idUtil");
               idUtil=new BigDecimal(p.trim()); 
               udao.debloquerbyId(idUtil);
                 FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Gestion_Utilisateurs"); 
           }  }
   
   
             public void  annuler() throws IOException{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Gestion_Utilisateurs");
     }
}
 
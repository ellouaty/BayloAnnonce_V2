/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ActionEmpDao;
import dao.ActionUserDao;
import dao.AnnonceDao;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
 
import javax.faces.context.FacesContext; 
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import model.ActionEmp;
import model.ActionUser;
import model.Annonce;
import org.primefaces.context.RequestContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
/**
 *
 * @author ibm
 */

@Named
@SessionScoped

public class FindBean   implements Serializable{
        List<Annonce>listMyAnnAct=new ArrayList<>();
        List<Annonce> ListMyAnnDesac=new ArrayList<>();
         List<Annonce> ListMyAnnRejet=new ArrayList<>();
         List<Annonce> ListMyAnnModer=new ArrayList<>();
         List<Annonce> listMyAnnSupr=new ArrayList<>();
         List<String[]>listAction=new ArrayList<>(); 
           @Size(min=1 ,message ="critaire?") 
        private String paramSearch;
       @NotNull(message = "Option?") 
       private String optionSearch; 

    public List<Annonce> getListMyAnnAct() {
        return listMyAnnAct;
    }

    public void setListMyAnnAct(List<Annonce> listMyAnnAct) {
        this.listMyAnnAct = listMyAnnAct;
    }

    public List<Annonce> getListMyAnnDesac() {
        return ListMyAnnDesac;
    }

    public void setListMyAnnDesac(List<Annonce> ListMyAnnDesac) {
        this.ListMyAnnDesac = ListMyAnnDesac;
    }

    public List<Annonce> getListMyAnnRejet() {
        return ListMyAnnRejet;
    }

    public void setListMyAnnRejet(List<Annonce> ListMyAnnRejet) {
        this.ListMyAnnRejet = ListMyAnnRejet;
    }

    public List<Annonce> getListMyAnnModer() {
        return ListMyAnnModer;
    }

    public void setListMyAnnModer(List<Annonce> ListMyAnnModer) {
        this.ListMyAnnModer = ListMyAnnModer;
    }

    public List<Annonce> getListMyAnnSupr() {
        return listMyAnnSupr;
    }

    public void setListMyAnnSupr(List<Annonce> listMyAnnSupr) {
        this.listMyAnnSupr = listMyAnnSupr;
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

    public List<String[]> getListAction() {
        return listAction;
    }

    public void setListAction(List<String[]> listAction) {
        this.listAction = listAction;
    }
      
      
      
      
        public void onloadListeSearch(String from) throws IOException{ 
       String email=null;
           String tel=null;
            String ip=null;
             String ref=null;
                AnnonceDao  aDao=new AnnonceDao();
        if(from.equals("btnFind")){
         switch (optionSearch) {
             case "email":
                 email=capitaliser(paramSearch);
                 break;
             case "tel": 
                 tel=paramSearch;
                 break;
             case "ip":
                 ip=paramSearch;
                 break;
             case "ref":
                 ref=paramSearch;
                 break;
             default:
                 break;
         }
         }else if(from.equals("lien")){ 
               Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
               if(params.get("email")!=null){
                    email=capitaliser(params.get("email"));
               }
                if(params.get("tel")!=null){
             tel=params.get("tel").trim();}
                 if(params.get("ip")!=null){
           ip=params.get("ip").trim();}
                  if(params.get("ref")!=null){
             ref=params.get("ref").trim();}
              
         } 
         
        if(email!=null){   
         listMyAnnAct=aDao.AnnoncesByEmail(email,"publie");
     ListMyAnnDesac=aDao.AnnoncesByEmail(email,"desactive");
         ListMyAnnRejet=aDao.AnnoncesByEmail(email,"rejete");
         ListMyAnnModer=aDao.AnnoncesByEmail(email,"en_cours");
         listMyAnnSupr=aDao.AnnoncesByEmail(email,"supprime");
         optionSearch="email";
         paramSearch=email;
     }
           else if(tel!=null){ 
                
         listMyAnnAct=aDao.AnnoncesByTel(tel,"publie");
     ListMyAnnDesac=aDao.AnnoncesByTel(tel,"desactive");
         ListMyAnnRejet=aDao.AnnoncesByTel(tel,"rejete");
         ListMyAnnModer=aDao.AnnoncesByTel(tel,"en_cours");
         listMyAnnSupr=aDao.AnnoncesByTel(tel,"supprime");
           optionSearch="tel";
         paramSearch=tel;
     }
               else if(ip!=null){ 
         listMyAnnAct=aDao.AnnoncesByIp(ip,"publie");
     ListMyAnnDesac=aDao.AnnoncesByIp(ip,"desactive");
         ListMyAnnRejet=aDao.AnnoncesByIp(ip,"rejete");
         ListMyAnnModer=aDao.AnnoncesByIp(ip,"en_cours");
          listMyAnnSupr=aDao.AnnoncesByIp(ip,"supprime");
           optionSearch="ip";
         paramSearch=ip;
     }
                  else  if(ref!=null){  
         listMyAnnAct=aDao.AnnoncesByRef(ref,"publie");
     ListMyAnnDesac=aDao.AnnoncesByRef(ref,"desactive");
         ListMyAnnRejet=aDao.AnnoncesByRef(ref,"rejete");
         ListMyAnnModer=aDao.AnnoncesByRef(ref,"en_cours");
          listMyAnnSupr=aDao.AnnoncesByRef(ref,"supprime");
           optionSearch="ref";
         paramSearch=ref; 
     }  
  
            if(from.equals("btnFind")){ 
                RequestContext.getCurrentInstance().update("MyAnnonces"); }
            else if(from.equals("lien")){ 
         FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Search");
            RequestContext.getCurrentInstance().update("MyAnnonces");}
     
     }
            public String capitaliser(String mot){ 
    StringBuilder s= new StringBuilder();
    s.append(Character.toTitleCase(mot.charAt(0))).append(mot.substring(1,mot.length()).toLowerCase());
    return s.toString();
    }
               public void remplireListAction(){
                   listAction.clear();
          Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            BigDecimal id=new BigDecimal(params.get("idDetails").trim());
          ActionEmpDao ademp=new ActionEmpDao();
          ActionUserDao aduser=new ActionUserDao();
          List<ActionEmp>list_emp=ademp.ActionxEmpSurAnnonce(id);
           List<ActionUser>list_user=aduser.ActionUserSurAnnonce(id);
           Map<LocalDateTime,String[]> mp=new TreeMap<>();
           DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
           for (ActionUser actionUser : list_user) {
             String[]tab={actionUser.getId().getDateActionUser(),actionUser.getId().getHeureActionUser(),actionUser.getLibelleActionUser(),actionUser.getMotifActionUser(),"user"};
              String h=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
              LocalDateTime dh=LocalDateTime.parse(actionUser.getId().getDateActionUser()+" "+actionUser.getId().getHeureActionUser(),formatter);
            mp.put(dh,tab);
           }
          for (ActionEmp actionEmp : list_emp) {
             String[]tab={actionEmp.getId().getDateActionEmp(),actionEmp.getId().getHeureActionEmp(),actionEmp.getLibelleActionEmp(),actionEmp.getMotifActionEmp(),actionEmp.getEmploye().getLoginEmploye()};
           String h=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
              LocalDateTime dh=LocalDateTime.parse(actionEmp.getId().getDateActionEmp()+" "+actionEmp.getId().getHeureActionEmp(),formatter);
            mp.put(dh,tab);
         }
         for (Map.Entry<LocalDateTime, String[]> entry : mp.entrySet()) {
              
            listAction.add(entry.getValue());
           
         }
           
        
           RequestContext.getCurrentInstance().execute("PF('dgDetails').show();");
            RequestContext.getCurrentInstance().update("pnDet"); 
     }   
     
}

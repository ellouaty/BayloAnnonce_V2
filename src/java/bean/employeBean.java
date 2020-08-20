/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ActionEmpDao;
import dao.AnnonceDao;
import dao.AppartementBureauDao;
import dao.AutreImmobilierDao;
import dao.EmployeDao;
import dao.MaisonVillaDao;
import dao.ModificationDao;
import dao.MotoDao;
import dao.PhotoDao;
import dao.TerrainFermeDao;
import dao.UtilisateurConnecteDao;
import dao.VehiculeProfessionnelDao;
import dao.VoitureDao;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map; 
import javax.annotation.PostConstruct;    
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;  
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession; 
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import model.ActionEmp;
import model.ActionEmpId;
import model.Annonce;
import model.AppartementBureau;
import model.AutreImmobilier;
import model.Employe;
import model.MaisonVilla;
import model.Modification;
import model.Moto;
import model.TerrainFerme;
import model.UtilisateurConnecte;
import model.VehiculeProfessionnel;
import model.Voiture;
import org.primefaces.context.RequestContext;
import session.Util;

/**
 *
 * @author ibm
 */
@Named
@ViewScoped
public class employeBean implements Serializable{
    
    private String login;
    private String pwd;
    private String nom;
    private String prenom;
    private String msg;
    private HashMap<String,String> roles ;
    List<Annonce> list_abus=new ArrayList<>();
    List<Annonce> list_new_nv=new ArrayList<>();
    List<Annonce> list_gray_nv=new ArrayList<>();
    List<Annonce> list_modified_nv=new ArrayList<>();
    List<Annonce> list_duplicate_nv=new ArrayList<>();
    List<Annonce> list_wrong_priceCat_nv=new ArrayList<>();
    List<Annonce> list_dup_picture_nv=new ArrayList<>();
     List<Annonce> list_new_all=new ArrayList<>();
    List<Annonce> list_gray_all=new ArrayList<>();
    List<Annonce> list_modified_all=new ArrayList<>();
    List<Annonce> list_duplicate_all=new ArrayList<>();
    List<Annonce> list_wrong_priceCat_all=new ArrayList<>();
    List<Annonce> list_dup_picture_all=new ArrayList<>();
    private Annonce annonceToModerate ;
     private String[] photoTomoderate ;
    private Voiture voitureToModerate;
    private Moto motoToModerate;
    private VehiculeProfessionnel vehiculeProTomoderate;
    private MaisonVilla maisonVillaToModerate;
    private AppartementBureau appartBureauToModerate;
    private AutreImmobilier autreImmoToModerate;
    private TerrainFerme terrainFerTomoderate;
    private String heureModeration ;
    private String heureDepot;
    private String viewedBy;
    private String noActionBy;
    private String PhotoFirst;
    private String motifDerefus; 
    private BigDecimal idEmp;
       @NotNull(message = "Entrez un nom")
     @Size(min = 5,message = "5 a 30 caractéres") 
    private String nomEmploye;
          @NotNull(message = "Entrez un prenom")
     @Size(min = 5,message = "5 a 30 caractéres") 
    private String prenomEmploye;
             @NotNull(message = "Entrez un login")
     @Size(min = 5,message = "5 a 30 caractéres") 
    private String loginEmploye;
     @NotNull(message = "Champs obligatoire")
        @Size(min = 5,message = "5 a 30 caractéres") 
    private String pwdEmploye;
       @NotNull(message = "Champs obligatoire")
     @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email invalid")
      
    private String emailEmploye;
    private String nbAnnMdr; 
     @Size(min=5 ,message = "Plus de 5 lettres")
     private String  titre; 
         @Size(min=5 ,message = "Description ?")
       private String description ;
     @NotNull(message = "Prix?")
         @Pattern(regexp = "^\\d+$" ,message = "Invalide!")
      @Size(max = 10,message = "10 chiffres au max")
     private String  prix;
       private List<Employe>list_emp=new ArrayList<>();
       private boolean droitQueus;
       private boolean  droitSearch;
       private boolean  droitSpam;
       private boolean  droitliveChat;
       private boolean  droitGestionUtil;
       private boolean  droitGestionEmp;
       private boolean  droitSaifMail;
       private boolean  droitGestionBtq;
       private boolean  droitWebSql;
       private boolean  droitReclamations;
       private  boolean delete0;
       private  boolean delete1;
       private  boolean delete2;
       private  boolean delete3;
       private  boolean delete4;
        @Size(min=1 ,message ="Valeur recherch?") 
        private String paramSearch;
       @NotNull(message = "Option?") 
       private String optionSearch;
                
    public String getNomEmploye() {
        return nomEmploye;
    }

    public String getPwdEmploye() {
        return pwdEmploye;
    }

    public String getOptionSearch() {
        return optionSearch;
    }

    public void setOptionSearch(String optionSearch) {
        this.optionSearch = optionSearch;
    }

    public void setPwdEmploye(String pwdEmploye) {
        this.pwdEmploye = pwdEmploye;
    }

     
    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getLoginEmploye() {
        return loginEmploye;
    }

    public void setLoginEmploye(String loginEmploye) {
        this.loginEmploye = loginEmploye;
    }

    public boolean isDelete0() {
        return delete0;
    }

    public void setDelete0(boolean delete0) {
        this.delete0 = delete0;
    }

    public boolean isDelete1() {
        return delete1;
    }

    public void setDelete1(boolean delete1) {
        this.delete1 = delete1;
    }

    public boolean isDelete2() {
        return delete2;
    }

    public void setDelete2(boolean delete2) {
        this.delete2 = delete2;
    }

    public boolean isDelete3() {
        return delete3;
    }

    public void setDelete3(boolean delete3) {
        this.delete3 = delete3;
    }

    public boolean isDelete4() {
        return delete4;
    }

    public void setDelete4(boolean delete4) {
        this.delete4 = delete4;
    }

    public String getEmailEmploye() {
        return emailEmploye;
    }

    public void setEmailEmploye(String emailEmploye) {
        this.emailEmploye = emailEmploye;
    }

    public String getNbAnnMdr() {
        return nbAnnMdr;
    }

    public void setNbAnnMdr(String nbAnnMdr) {
        this.nbAnnMdr = nbAnnMdr;
    }

    public String getParamSearch() {
        return paramSearch;
    }

    public void setParamSearch(String paramSearch) {
        this.paramSearch = paramSearch;
    }
    
    

    public String getTitre() {
        return titre;
    }

    
    public void setTitre(String titre) {
        this.titre = titre;
    }

     

    public String getDescription() {
        return description;
    }

    public List<Employe> getList_emp() {
        return list_emp;
    }

    public void setList_emp(List<Employe> list_emp) {
        this.list_emp = list_emp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
   

    public employeBean() {
       
                  
    }
  
    public String getLogin() {
        return login;
    }

    public Annonce getAnnonceToModerate() {
        return annonceToModerate;
    }

    public void setAnnonceToModerate(Annonce annonceToModerate) {
        this.annonceToModerate = annonceToModerate;
    }

    public Voiture getVoitureToModerate() {
        return voitureToModerate;
    }

    public void setVoitureToModerate(Voiture voitureToModerate) {
        this.voitureToModerate = voitureToModerate;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotifDerefus() {
        return motifDerefus;
    }

    public String getHeureDepot() {
        return heureDepot;
    }

    public void setHeureDepot(String heureDepot) {
        this.heureDepot = heureDepot;
    }

    public void setMotifDerefus(String motifDerefus) {
        this.motifDerefus = motifDerefus;
    }

    public String getPwd() {
        return pwd;
    }

    public boolean isDroitQueus() {
        return droitQueus;
    }

    public void setDroitQueus(boolean droitQueus) {
        this.droitQueus = droitQueus;
    }

    public boolean isDroitSearch() {
        return droitSearch;
    }

    public void setDroitSearch(boolean droitSearch) {
        this.droitSearch = droitSearch;
    }

    public boolean isDroitSpam() {
        return droitSpam;
    }

    public void setDroitSpam(boolean droitSpam) {
        this.droitSpam = droitSpam;
    }

    public boolean isDroitliveChat() {
        return droitliveChat;
    }

    public void setDroitliveChat(boolean droitliveChat) {
        this.droitliveChat = droitliveChat;
    }

    public boolean isDroitGestionUtil() {
        return droitGestionUtil;
    }

    public void setDroitGestionUtil(boolean droitGestionUtil) {
        this.droitGestionUtil = droitGestionUtil;
    }

    public boolean isDroitGestionEmp() {
        return droitGestionEmp;
    }

    public void setDroitGestionEmp(boolean droitGestionEmp) {
        this.droitGestionEmp = droitGestionEmp;
    }

    public boolean isDroitSaifMail() {
        return droitSaifMail;
    }

    public void setDroitSaifMail(boolean droitSaifMail) {
        this.droitSaifMail = droitSaifMail;
    }

    public boolean isDroitGestionBtq() {
        return droitGestionBtq;
    }

    public void setDroitGestionBtq(boolean droitGestionBtq) {
        this.droitGestionBtq = droitGestionBtq;
    }

    public boolean isDroitWebSql() {
        return droitWebSql;
    }

    public void setDroitWebSql(boolean droitWebSql) {
        this.droitWebSql = droitWebSql;
    }

    public boolean isDroitReclamations() {
        return droitReclamations;
    }

    public void setDroitReclamations(boolean droitReclamations) {
        this.droitReclamations = droitReclamations;
    }

    public BigDecimal getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(BigDecimal idEmp) {
        this.idEmp = idEmp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String[] getPhotoTomoderate() {
        return photoTomoderate;
    }

    public void setPhotoTomoderate(String[] photoTomoderate) {
        this.photoTomoderate = photoTomoderate;
    }

    public String getViewedBy() {
        return viewedBy;
    }

    public void setViewedBy(String viewedBy) {
        this.viewedBy = viewedBy;
    }

    public String getNoActionBy() {
        return noActionBy;
    }

    public void setNoActionBy(String noActionBy) {
        this.noActionBy = noActionBy;
    }
 

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public HashMap<String, String> getRoles() {
        return roles;
    }

    public void setRoles(HashMap<String, String> roles) {
        this.roles = roles;
    }

 

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

 
    public String getPhotoFirst() {
        return PhotoFirst;
    }

    public List<Annonce> getList_abus() {
        return list_abus;
    }

    public void setList_abus(List<Annonce> list_abus) {
        this.list_abus = list_abus;
    }

    public void setPhotoFirst(String PhotoFirst) {
        this.PhotoFirst = PhotoFirst;
    }

    public List<Annonce> getList_new_nv() {
        return list_new_nv;
    }

    public void setList_new_nv(List<Annonce> list_new_nv) {
        this.list_new_nv = list_new_nv;
    }

    public List<Annonce> getList_gray_nv() {
        return list_gray_nv;
    }

    public String getHeureModeration() {
        return heureModeration;
    }

    public void setHeureModeration(String heureModeration) {
        this.heureModeration = heureModeration;
    }

    public Moto getMotoToModerate() {
        return motoToModerate;
    }

    public void setMotoToModerate(Moto motoToModerate) {
        this.motoToModerate = motoToModerate;
    }

    public VehiculeProfessionnel getVehiculeProTomoderate() {
        return vehiculeProTomoderate;
    }

    public void setVehiculeProTomoderate(VehiculeProfessionnel vehiculeProTomoderate) {
        this.vehiculeProTomoderate = vehiculeProTomoderate;
    }

    public MaisonVilla getMaisonVillaToModerate() {
        return maisonVillaToModerate;
    }

    public void setMaisonVillaToModerate(MaisonVilla maisonVillaToModerate) {
        this.maisonVillaToModerate = maisonVillaToModerate;
    }

    public AppartementBureau getAppartBureauToModerate() {
        return appartBureauToModerate;
    }

    public void setAppartBureauToModerate(AppartementBureau appartBureauToModerate) {
        this.appartBureauToModerate = appartBureauToModerate;
    }

    public AutreImmobilier getAutreImmoToModerate() {
        return autreImmoToModerate;
    }

    public void setAutreImmoToModerate(AutreImmobilier autreImmoToModerate) {
        this.autreImmoToModerate = autreImmoToModerate;
    }

    public TerrainFerme getTerrainFerTomoderate() {
        return terrainFerTomoderate;
    }

    public void setTerrainFerTomoderate(TerrainFerme terrainFerTomoderate) {
        this.terrainFerTomoderate = terrainFerTomoderate;
    }

    public void setList_gray_nv(List<Annonce> list_gray_nv) {
        this.list_gray_nv = list_gray_nv;
    }

    public List<Annonce> getList_modified_nv() {
        return list_modified_nv;
    }

    public void setList_modified_nv(List<Annonce> list_modified_nv) {
        this.list_modified_nv = list_modified_nv;
    }

    public List<Annonce> getList_duplicate_nv() {
        return list_duplicate_nv;
    }

    public void setList_duplicate_nv(List<Annonce> list_duplicate_nv) {
        this.list_duplicate_nv = list_duplicate_nv;
    }

    public List<Annonce> getList_wrong_priceCat_nv() {
        return list_wrong_priceCat_nv;
    }

    public void setList_wrong_priceCat_nv(List<Annonce> list_wrong_priceCat_nv) {
        this.list_wrong_priceCat_nv = list_wrong_priceCat_nv;
    }

    public List<Annonce> getList_dup_picture_nv() {
        return list_dup_picture_nv;
    }

    public void setList_dup_picture_nv(List<Annonce> list_dup_picture_nv) {
        this.list_dup_picture_nv = list_dup_picture_nv;
    }

    public List<Annonce> getList_new_all() {
        return list_new_all;
    }

    public void setList_new_all(List<Annonce> list_new_all) {
        this.list_new_all = list_new_all;
    }

    public List<Annonce> getList_gray_all() {
        return list_gray_all;
    }

    public void setList_gray_all(List<Annonce> list_gray_all) {
        this.list_gray_all = list_gray_all;
    }

    public List<Annonce> getList_modified_all() {
        return list_modified_all;
    }

    public void setList_modified_all(List<Annonce> list_modified_all) {
        this.list_modified_all = list_modified_all;
    }

    public List<Annonce> getList_duplicate_all() {
        return list_duplicate_all;
    }

    public void setList_duplicate_all(List<Annonce> list_duplicate_all) {
        this.list_duplicate_all = list_duplicate_all;
    }

    public List<Annonce> getList_wrong_priceCat_all() {
        return list_wrong_priceCat_all;
    }

    public void setList_wrong_priceCat_all(List<Annonce> list_wrong_priceCat_all) {
        this.list_wrong_priceCat_all = list_wrong_priceCat_all;
    }

    public List<Annonce> getList_dup_picture_all() {
        return list_dup_picture_all;
    }

    public void setList_dup_picture_all(List<Annonce> list_dup_picture_all) {
        this.list_dup_picture_all = list_dup_picture_all;
    }
 
   
    
  @PostConstruct
         public void init()   {
            
           
         }
    
  public void onloadQueus(){  
      UtilisateurConnecteDao utdao=new UtilisateurConnecteDao();
          List<UtilisateurConnecte> listUtil =utdao.UtlisateurSansAnnonceDejaPublie();
             remplirListeQueus(listUtil);
         }
   public void onloadNew() throws IOException{
       
       annoncenModeration("new","nv");
            
         }
    public void   onloadAbus() throws IOException{
       
        annoncenModeration("abus","all");
            
         }
    
   public void onloadModif() throws IOException{
       annoncenModeration("mod","nv");
         }
   public void onloadGray() throws IOException{
               annoncenModeration("gray","nv");
   }
 public void onloadNewAll() throws IOException{
       
       annoncenModeration("new","all");
            
         }
   public void onloadModifAll() throws IOException{
       annoncenModeration("mod","all");
         }
   public void onloadGrayAll() throws IOException{
               annoncenModeration("gray","all");
   }
  
   public String enregistrerActionEmp(String action,Annonce a,String motif){
     
       HttpSession s=Util.getSession();
       Employe e=(Employe) s.getAttribute("emp");
       ActionEmpDao ed=new ActionEmpDao();
       ActionEmp ae=new ActionEmp();
        ActionEmpId eid=new ActionEmpId();
        String h=String.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE)));
         eid.setDateActionEmp(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
             eid.setHeureActionEmp(h);
             eid.setIdAnnonce(a.getIdAnnonce());
             eid.setIdEmploye(e.getIdEmploye());
             ae.setId(eid);
             ae.setAnnonce(a);
             ae.setEmploye(e);
             ae.setMotifActionEmp(motif);
             ae.setLibelleActionEmp(action);
             ed.ajouterActionEmp(ae);  
             return h;
   } 
   public void annoncenModeration(String queu,String vu) throws IOException{ 
          UtilisateurConnecteDao utdao=new UtilisateurConnecteDao();
          List<UtilisateurConnecte> listUtil =utdao.UtlisateurSansAnnonceDejaPublie();
          
       List<Annonce>list;
        HttpSession s=Util.getSession();
       Employe e=(Employe) s.getAttribute("emp");
        AnnonceDao adao=new AnnonceDao();
        list=adao.annoncesByQueu(queu,vu,listUtil);
        if(!list.isEmpty()){  
          
              annonceToModerate=list.get(0);
               titre=annonceToModerate.getTitreAnnonce();
              description=annonceToModerate.getDescriptionAnnonce(); 
              prix=String.valueOf(annonceToModerate.getPrixAnnonce());
            VoitureDao voitdao=new VoitureDao();
             voitureToModerate=voitdao.VoitureByANN(annonceToModerate);
              VehiculeProfessionnelDao vhdao=new VehiculeProfessionnelDao();
              if(voitureToModerate!=null){
              vehiculeProTomoderate=vhdao.VproByVoiture(voitureToModerate);}
              MotoDao mtdao=new MotoDao();
              motoToModerate=mtdao.MotoByAnn(annonceToModerate);
               MaisonVillaDao mvdao=new MaisonVillaDao();
                 maisonVillaToModerate=mvdao.MaisonVillaByAnn(annonceToModerate);
               AppartementBureauDao apBurdao=new AppartementBureauDao();
               if(maisonVillaToModerate!=null){
            appartBureauToModerate=apBurdao.AppartementBureuByMV(maisonVillaToModerate);}
               AutreImmobilierDao autdao=new AutreImmobilierDao();
              autreImmoToModerate=autdao.AutreImmoByAnn(annonceToModerate);
               TerrainFermeDao terdao=new TerrainFermeDao();
               if(autreImmoToModerate!=null){
               terrainFerTomoderate=terdao.TerrainByAutreImmo(autreImmoToModerate);}
               ActionEmpDao acdao=new ActionEmpDao();
              switch (queu) {
                  case "new":
                      {
                          String etat=annonceToModerate.getEtatAnnonce();
                          if(etat.equals("LockedNew")){
                              viewedBy=acdao.nomView(annonceToModerate,"LockedNew");
                          }else if(etat.equals("NoActionNew")){
                              noActionBy=acdao.nomView(annonceToModerate,"NoActionNew");
                          }             break;
                      }
                  case "gray":
                      {
                          String etat=annonceToModerate.getEtatAnnonce();
                          if(etat.equals("LockedGray")){
                              viewedBy=acdao.nomView(annonceToModerate,"LockedGray");
                          }else if(etat.equals("NoActionGray")){
                              noActionBy=acdao.nomView(annonceToModerate,"NoActionGray");
                          }             break;
                      }
                  case "mod":
                      {
                          String etat=annonceToModerate.getEtatAnnonce();
                          if(etat.equals("LockedModif")){
                              viewedBy=acdao.nomView(annonceToModerate,"LockedModif");   
                          }else if(etat.equals("NoActionModif")){
                              noActionBy=acdao.nomView(annonceToModerate,"NoActionModif");
                          }             break;
                      }
                  case "abus":
                      {
                          String etat=annonceToModerate.getEtatAnnonce();
                          if(etat.equals("NoActionAbus")){
                              noActionBy=acdao.nomView(annonceToModerate,"NoActionAbus");
                          }             break;
                      }
                  default:
                      break;
              }
//autre*/
               List<String> l = annonceToModerate.getChemins();
            if(!l.isEmpty()){
                 photoTomoderate=new String[l.size()];
                for (int i = 0; i < l.size(); i++) {
                photoTomoderate[i]=l.get(i);
            }
            PhotoFirst=  photoTomoderate[0];}
            else{
                   PhotoFirst="NoPh.JPG"  ;
                    }
            
            Annonce a=annonceToModerate;
           
            switch (queu) {
                case "new":
                    a.setEtatAnnonce("LockedNew");
                    adao.modifierAnnonce(a);
                    break;
                case "gray":
                    a.setEtatAnnonce("LockedGray");
                    adao.modifierAnnonce(a);
                    break;
                case "mod":
                    a.setEtatAnnonce("LockedModif");
                    adao.modifierAnnonce(a);
                    break;
                default:
                    break;
            }
            String h="";
           if(vu.equals("nv")){
               
              h= enregistrerActionEmp("Locked",a,"_");}
           else{
             h=String.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE)));
           }
                 heureModeration=h.substring(0,5);
                 heureDepot=annonceToModerate.getHeureDepotAnnonce().substring(0,5);
        }else{ 
             FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Queus");
        }
   }  
   public void afficherPhoto(int n){
       PhotoFirst=  photoTomoderate[n];
        RequestContext.getCurrentInstance().update("grandPh");  
   }
    
  public void remplirListeQueus(List<UtilisateurConnecte> listUtil){
            
      AnnonceDao adao=new AnnonceDao();
     
         //non vue or after 5min
          list_new_nv=adao.annoncesByQueu("new","nv",listUtil);
      list_gray_nv=adao.annoncesByQueu("gray","nv",listUtil);
      list_modified_nv=adao.annoncesByQueu("mod","nv",listUtil);
      list_abus=adao.annoncesByQueu("abus","all",listUtil);
      //list_duplicate_nv=adao.annoncesByQueu("dup","nv",listUtil);
     // list_wrong_priceCat_nv=adao.annoncesByQueu("wCatPric","nv",listUtil);
      // list_dup_picture_nv=adao.annoncesByQueu("dupPic","nv",listUtil);
      list_new_all=adao.annoncesByQueu("new","all",listUtil);
      list_gray_all=adao.annoncesByQueu("gray","all",listUtil);
      list_modified_all=adao.annoncesByQueu("mod","all",listUtil);
     // list_duplicate_all=adao.annoncesByQueu("dup","all",listUtil);
      //list_wrong_priceCat_all=adao.annoncesByQueu("wCatPric","all",listUtil);
     // list_dup_picture_all=adao.annoncesByQueu("dupPic","all",listUtil); 
   
    
       }
    
     public void MoveToAbus(){
                Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String p=params.get("idAnnAbus");
             
        AnnonceDao ad=new AnnonceDao();
        Annonce a=ad.getByIdAllEtat(new BigDecimal(p.trim()));
          a.setEtatAnnonce("abus");
          ad.modifierAnnonce(a); 
         enregistrerActionEmp("Move to Abus",a,"_");
             RequestContext.getCurrentInstance().execute("$(\"#mtfNo\").hide()");
            RequestContext.getCurrentInstance().execute("$(\"#btnref\").hide()");
            RequestContext.getCurrentInstance().execute("$(\"#btnrefE\").show()");
            RequestContext.getCurrentInstance().execute("$(\"#labFeedBMoved\").show()");
            RequestContext.getCurrentInstance().update("pn"); 
            RequestContext.getCurrentInstance().execute("$(\"#pn1\").show()"); 
         
          }
         
          public void NoActionAnnonceByEmp(String q) throws IOException{
                Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String p=params.get("idAnnNoAct");
          AnnonceDao ad=new AnnonceDao();
        Annonce a=ad.getByIdAllEtat(new BigDecimal(p.trim()));
        if(!a.getEtatAnnonce().equals("publie")&&!a.getEtatAnnonce().equals("rejete")&&!a.getEtatAnnonce().equals("desactive")) {
        switch (q) {
            case "new":
                a.setEtatAnnonce("NoActionNew");
                enregistrerActionEmp("No action",a,"_"); 
                ad.modifierAnnonce(a); 
                break;
            case "gray":
                a.setEtatAnnonce("NoActionGray");
                 enregistrerActionEmp("No action",a,"_");
                 ad.modifierAnnonce(a); 
                break;
            case "modif":
                a.setEtatAnnonce("NoActionModif");
                 enregistrerActionEmp("No action",a,"_");
                 ad.modifierAnnonce(a); 
                break;
            case "abus":
                a.setEtatAnnonce("NoActionAbus");
                 enregistrerActionEmp("No action",a,"_");
                 ad.modifierAnnonce(a); 
                break;
            default:
                break;
        }
       }
              String page="Queus";
               switch (q) {
            case "new":
                page="NewUser";
                break;
            case "gray":
                page="Gray";
                break;
            case "modif":
             page="Modified";
                break;
                 case "abus":
            
            page="Abus";
                break;
                 case "newAll":
                page="NewUserAll";
                break;
                 case "grayAll":
                page="grayAll";
                break;
                 case "modAll":
                page="ModifiedAll"; 
                break;
                       
            default:
                break;
        }
               FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/"+page);
          }
   
       public void refuseAnnonceByEmp(String f){  
           if(!motifDerefus.equals("NO")){ 
             Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
             String p=params.get("idAnnToRefu");
              
        AnnonceDao ad=new AnnonceDao();
        Annonce a=ad.getByIdAllEtat(new BigDecimal(p.trim()));
        if(f.equals("norm")){
        a.setEtatAnnonce("rejete"); 
        ad.modifierAnnonce(a); 
        
            }
        else if(f.equals("modif")){
             Annonce n=new Annonce();
             Annonce old=new Annonce();
             ModificationDao mdfao=new ModificationDao();
             Modification md=mdfao.getModificationByAnnNew(a);  
              old=ad.getByIdAllEtat(md.getAnnonceOld().getIdAnnonce()); 
               n=ad.getByIdAllEtat(md.getAnnonceNew().getIdAnnonce());
               n.setEtatAnnonce("modifRejete"); 
                 ad.modifierAnnonce(n);
                 
              
             old.setEtatAnnonce("publie"); 
          ad.modifierAnnonce(old); 
        }
         enregistrerActionEmp("refuse", a,motifDerefus);
            annonceToModerate.setEtatAnnonce("rejete");
              
             
          RequestContext.getCurrentInstance().execute("$(\"#btnMOVE\").hide()");  
          RequestContext.getCurrentInstance().update("pn");
            
           }
           RequestContext.getCurrentInstance().execute("$(\"#pn1\").show()"); 
           RequestContext.getCurrentInstance().update("pn1");
       }
   
           public void accepteAnnonceByEmp(String f){
            
               PhotoDao pdao=new PhotoDao();
                if(delete0==true){ 
                    pdao.supprimerPhotoBychemin(photoTomoderate[0]);
                    File fil=new File("C:\\Users\\ibm\\Desktop\\BayloAnnonce_V2\\web\\resources\\img\\"+photoTomoderate[0]);
                    fil.delete();
                }
                 if(delete1==true){
                     pdao.supprimerPhotoBychemin(photoTomoderate[1]);
                       File fil=new File("C:\\Users\\ibm\\Desktop\\BayloAnnonce_V2\\web\\resources\\img\\"+photoTomoderate[1]);
                    fil.delete();
                }
                  if(delete2==true){
                      pdao.supprimerPhotoBychemin(photoTomoderate[2]);
                        File fil=new File("C:\\Users\\ibm\\Desktop\\BayloAnnonce_V2\\web\\resources\\img\\"+photoTomoderate[2]);
                    fil.delete();
                }
                  
                   if(delete3==true){
                       pdao.supprimerPhotoBychemin(photoTomoderate[3]);
                         File fil=new File("C:\\Users\\ibm\\Desktop\\BayloAnnonce_V2\\web\\resources\\img\\"+photoTomoderate[3]);
                    fil.delete();
                }
                    if(delete4==true){
                        pdao.supprimerPhotoBychemin(photoTomoderate[4]);
                          File fil=new File("C:\\Users\\ibm\\Desktop\\BayloAnnonce_V2\\web\\resources\\img\\"+photoTomoderate[4]);
                    fil.delete();
                }
                    
               
                      
            Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String p=params.get("idAnnToAcct"); 
             AnnonceDao ad=new AnnonceDao();
        Annonce a=ad.getByIdAllEtat(new BigDecimal(p.trim())); 
                 
     a.setTitreAnnonce(titre);   
      a.setCategorie(annonceToModerate.getCategorie());
      a.setSouscategorie(annonceToModerate.getSouscategorie());
      a.setPrixAnnonce(new BigDecimal(prix));
      a.setTypeAnnonce(annonceToModerate.getTypeAnnonce());
      a.setDescriptionAnnonce(description);  
      a.setNoteAnnonce(annonceToModerate.getNoteAnnonce());
       a.setDegreNoteAnnonce(annonceToModerate.getDegreNoteAnnonce());
       UtilisateurConnecteDao ud=new UtilisateurConnecteDao();
       List<UtilisateurConnecte> list_utilTel=ud.getUtilisateurConnecteByTel(annonceToModerate.getUtilisateurConnecte().getTelUtilConnecte());
               for (UtilisateurConnecte utilisateurConnecte : list_utilTel) {
                   
                   utilisateurConnecte.setNoteTelUtilConnecte(annonceToModerate.getUtilisateurConnecte().getNoteTelUtilConnecte());
                   ud.modifierUtilisateurConnecte(utilisateurConnecte);
               }
                 
        a.setEtatAnnonce("publie");
             ad.modifierAnnonce(a);  
       if(f.equals("norm")){
        a.setEtatAnnonce("publie");
             ad.modifierAnnonce(a);  
           } 
        else if(f.equals("modif")){  
             Annonce old=new Annonce();
             ModificationDao mdfao=new ModificationDao();
             Modification md=mdfao.getModificationByAnnNew(a); 
               a.setEtatAnnonce("publie");
              ad.modifierAnnonce(a); 
             old=ad.getByIdAllEtat(md.getAnnonceOld().getIdAnnonce()); 
             old.setEtatAnnonce("ancien"); 
          ad.modifierAnnonce(old); 
         mdfao.supprimerModification(md);
        }
               enregistrerActionEmp("accepte", a,"_");
       annonceToModerate.setEtatAnnonce("publie");
       
             RequestContext.getCurrentInstance().execute("$(\"#mtfNo\").hide()");
           RequestContext.getCurrentInstance().execute("$(\"#labFeedBAcc\").show()");
           RequestContext.getCurrentInstance().execute("$(\"#btnMOVE\").hide()"); 
          RequestContext.getCurrentInstance().update("pn"); 
                RequestContext.getCurrentInstance().execute("$(\"#pn1\").show()"); 
           }
           
        
  public void authentification() throws IOException{
       
        
      EmployeDao udao=new EmployeDao();
         Employe emp=udao.authentifier(login,pwd);
        
         HttpSession session = Util.getSession();
        
        if (emp!=null) {
          session.setAttribute("select", "0");
          session.setAttribute("emp",emp);    
           nom=emp.getNomEmploye();
           prenom=emp.getPrenomEmploye(); 
         remplirRoles();
             FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Menu");
        } else { 
           
       
          msg="*Login ou mot de passe incorrect .";
            RequestContext.getCurrentInstance().update("vld");           
       }
   
       
    }
 public void singOut(String from) throws IOException{
      HttpSession session = Util.getSession();
      session.setAttribute("emp",null);
      session.setAttribute("select",null);
      session.setAttribute("droits",null);
           session.setAttribute("d0",null);
                     session.setAttribute("d1",null); 
                     session.setAttribute("d3",null); 
                     session.setAttribute("d4",null); 
                     session.setAttribute("d6",null); 
                     session.setAttribute("d2",null); 
                     session.setAttribute("d8",null); 
                     session.setAttribute("d7",null); 
                     session.setAttribute("d9",null); 
                     session.setAttribute("d5",null); 
               session.invalidate();
                
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel");
                
       
 }
 
 public void redirection() throws IOException{
     HttpSession s=Util.getSession();
     String select=(String) s.getAttribute("select");
     String p = null;
  if(select.equals("0")){
        p="Menu";
     }
     if(select.equals("1")){
        p="Queus";
     }
       if(select.equals("2")){
        p="Search";
     }
              if(select.equals("3")){
        p="Saif_Mail";
     }
                  if(select.equals("4")){
        p="Spam_Filter";
     }
                   if(select.equals("6")){
     p="Reclamations";
     } 
                          if(select.equals("5")){
        p="Live_chat";
     }
                           if(select.equals("7")){
        p="Gestion_Utilisateurs";
     }
                              if(select.equals("8")){
        p="Gestion_Boutique";
     }
                              if(select.equals("9")){
         p="Gestion_Employees";
     }                                            
                                if(select.equals("10")){   
        p="Web_Sql";
     }
                          FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/"+p);
 }
 
 public void remplirRoles(){
      roles  = new HashMap<String, String>();  
      HttpSession s=Util.getSession();
       Employe e=(Employe) s.getAttribute("emp");
       
      String droits=e.getRolesEmploye();
                     String[] t = droits.split("_"); 
                       if(t[0].equals("t")){ 
                        roles.put("⇒Queus","1");
                        s.setAttribute("d0",t[0]);
                      }
                      if(t[1].equals("t")){
                      roles.put("⇒Search","2");
                       s.setAttribute("d1",t[1]);
                     }
                       if(t[2].equals("t")){
                    roles.put("⇒Spam Filter","4");
                     s.setAttribute("d3",t[2]);
                     }
                       if(t[3].equals("t")){
                       s.setAttribute("d4",t[3]);
                        roles.put("⇒Live chat","5");
                     }
                       if(t[4].equals("t")){
                            s.setAttribute("d6",t[4]);
                   roles.put("⇒Gestion Utilisateurs","7");
                     }
                       if(t[(5)].equals("t")){
                           s.setAttribute("d2",t[5]); 
                       roles.put("⇒Saif Mail","3");
                     }
                       if(t[6].equals("t")){
                      roles.put("⇒Gestion Employees","9");
                       s.setAttribute("d8",t[6]);
                     }
                       if(t[7].equals("t")){
                          roles.put("⇒Gestion Boutique","8");
                            s.setAttribute("d7",t[7]);
                    
                     }
                       if(t[8].equals("t")){
                       roles.put("⇒Web Sql","10");
                        s.setAttribute("d9",t[8]);
                      
                     }
                 
                      if(t[9].equals("t")){
             roles.put("⇒Reclamations","6");
              s.setAttribute("d5",t[9]);
                     }
                      s.setAttribute("droits",roles);
      
 }
 
 public void editAnnoncePnn(){
     Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String p=params.get("idAnnToEditPn"); 
       HttpSession s=Util.getSession();
       Employe e=(Employe) s.getAttribute("emp");
         if(!p.isEmpty()){  
          AnnonceDao adao=new AnnonceDao(); 
              annonceToModerate=adao.getByIdAllEtat(new BigDecimal(p.trim()));
               titre=annonceToModerate.getTitreAnnonce();
              description=annonceToModerate.getDescriptionAnnonce(); 
              prix=String.valueOf(annonceToModerate.getPrixAnnonce());
            VoitureDao voitdao=new VoitureDao();
              voitureToModerate=voitdao.VoitureByANN(annonceToModerate);
              VehiculeProfessionnelDao vhdao=new VehiculeProfessionnelDao();
              vehiculeProTomoderate=vhdao.VproByVoiture(voitureToModerate);
              MotoDao mtdao=new MotoDao();
              motoToModerate=mtdao.MotoByAnn(annonceToModerate);
               MaisonVillaDao mvdao=new MaisonVillaDao();
                 maisonVillaToModerate=mvdao.MaisonVillaByAnn(annonceToModerate);
               AppartementBureauDao apBurdao=new AppartementBureauDao();
            appartBureauToModerate=apBurdao.AppartementBureuByMV(maisonVillaToModerate);
               AutreImmobilierDao autdao=new AutreImmobilierDao();
              autreImmoToModerate=autdao.AutreImmoByAnn(annonceToModerate);
               TerrainFermeDao terdao=new TerrainFermeDao();
               terrainFerTomoderate=terdao.TerrainByAutreImmo(autreImmoToModerate);
               ActionEmpDao acdao=new ActionEmpDao();
           
               String etat=annonceToModerate.getEtatAnnonce();
         switch (etat) {
             case "LockedNew":
                 viewedBy=acdao.nomView(annonceToModerate,"LockedNew");
                 break;

             case "NoActionNew":
                 noActionBy=acdao.nomView(annonceToModerate,"NoActionNew");
                 break;
             case "LockedGray":
                 viewedBy=acdao.nomView(annonceToModerate,"LockedGray");
                 break;
             case "NoActionGray":
                 noActionBy=acdao.nomView(annonceToModerate,"NoActionGray");
                 break;
             case "LockedModif":
                 viewedBy=acdao.nomView(annonceToModerate,"LockedModif");
                 break;
             case "NoActionModif":
                 noActionBy=acdao.nomView(annonceToModerate,"NoActionModif");
                 break;
             case "NoActionAbus":
                 noActionBy=acdao.nomView(annonceToModerate,"NoActionAbus");
                 break;
             default:
                 break;
                 //autre*/
         }
          
         List<String> l = annonceToModerate.getChemins();
            if(!l.isEmpty()){
                 photoTomoderate=new String[l.size()];
                for (int i = 0; i < l.size(); i++) {
                photoTomoderate[i]=l.get(i);
            }
            PhotoFirst=  photoTomoderate[0];}
            else{
                   PhotoFirst="NoPh.JPG"  ;
                    }
            
  
            
           
               String h=String.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE)));
              heureModeration=h.substring(0,5);
                 heureDepot=annonceToModerate.getHeureDepotAnnonce().substring(0,5);  
         
         }
 }
     public void remplireListEmploye(){
           list_emp.clear();
          EmployeDao empDao=new EmployeDao(); 
          list_emp=empDao.getEmployes();
          
     } 
     public void creerEmploye() throws IOException{
         EmployeDao empDao=new EmployeDao();
         Employe emp=new Employe();
         emp.setNomEmploye(capitaliser(nomEmploye));
         emp.setPrenomEmploye(capitaliser(prenomEmploye));
         emp.setLoginEmploye(capitaliser(loginEmploye));
         emp.setEmailEmploye(capitaliser(emailEmploye));
         emp.setPasswordEmploye(pwdEmploye);
         emp.setNbanModr(Integer.valueOf(nbAnnMdr));
         emp.setRolesEmploye(String.valueOf(droitQueus).charAt(0)+"_"+String.valueOf(droitSearch).charAt(0)+"_"+String.valueOf(droitSpam).charAt(0)
                 +"_"+String.valueOf(droitliveChat).charAt(0)+"_"+String.valueOf(droitGestionUtil).charAt(0)+"_"+String.valueOf(droitGestionEmp).charAt(0)+"_"+
                 String.valueOf(droitSaifMail).charAt(0)+"_"+String.valueOf(droitGestionBtq).charAt(0)+"_"+String.valueOf(droitWebSql).charAt(0)+"_"+String.valueOf(droitReclamations).charAt(0));
    
         empDao.ajouterEmploye(emp);
         FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Employees");
     }
      public void modifierEmp() throws IOException{
            Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String p=params.get("idModiEmp");
          EmployeDao empdao=new EmployeDao();
          Employe e=new Employe();
          e.setIdEmploye(new BigDecimal(p.trim()));
          e.setNomEmploye(nomEmploye);
         e.setPrenomEmploye(prenomEmploye);
          e.setPasswordEmploye(pwdEmploye);
          e.setEmailEmploye(emailEmploye);
          e.setLoginEmploye(loginEmploye);
          e.setNbanModr(Integer.valueOf(nbAnnMdr));
         
                    String droits="";
                      if(droitQueus==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                      if(droitSearch==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                           if(droitSpam==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                      if(droitliveChat==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                           if(droitGestionUtil==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                   
                             if( droitSaifMail==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                   
                               if( droitGestionEmp==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                               if(droitGestionBtq==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                                 if(droitWebSql==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                                     if(droitReclamations==true){
                          droits+="t_";
                        
                     }else{
                         droits+="f_";
                     }
                    
           e.setRolesEmploye(droits); 
           
           empdao.modifierEmploye(e);
          
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Employees");
     }
   
              public void  annuler() throws IOException{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Panel/Gestion_Employees");
     }
     public void editerEmp(){
           Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              String p=params.get("EmpToEdit");
             if(p!=null){
                 BigDecimal id=new BigDecimal(p.trim());
                 if(id!=null){
                 EmployeDao empdao=new EmployeDao();
              Employe   empToEdit=empdao.getById(id);
              idEmp=empToEdit.getIdEmploye();
              nomEmploye=empToEdit.getNomEmploye();
              prenomEmploye=empToEdit.getPrenomEmploye();
              pwdEmploye=empToEdit.getPasswordEmploye();
              emailEmploye=empToEdit.getEmailEmploye();
              loginEmploye=empToEdit.getLoginEmploye();
              nbAnnMdr=String.valueOf(empToEdit.getNbanModr());
                 String droits=empToEdit.getRolesEmploye();
                     String[] t = droits.split("_");
 
                      if( t[0].equals("t")){
                       droitQueus=true;  
                     }else{
                         droitQueus=false;
                     }
                      if(t[1].equals("t")){
                       droitSearch=true;  
                     }else{
                         droitSearch=false;
                     }
                       if(t[2].equals("t")){
                       droitSpam=true;  
                     }else{
                         droitSpam=false;
                     }
                                   if(t[3].equals("t")){
                     droitliveChat   =true;  
                     }else{
                         droitliveChat =false;
                     }
                       if(t[4].equals("t")){
                        droitGestionUtil=true;  
                     }else{
                          droitGestionUtil=false;
                     }
                       if(t[(5)].equals("t")){
                       droitSaifMail =true;  
                     }else{
                        droitSaifMail  =false;
                     }
                       if(t[6].equals("t")){
                       droitGestionEmp =true;  
                     }else{
                        droitGestionEmp  =false;
                     }
                       if(t[7].equals("t")){
                           droitGestionBtq=true;  
                     }else{
                            droitGestionBtq =false;
                     }
                       if(t[8].equals("t")){
                       droitWebSql =true;  
                     }else{
                         droitWebSql =false;
                     }
                 
                      if(t[9].equals("t")){
                       droitReclamations =true;  
                     }else{
                        droitReclamations  =false;
                     }
                 
                
                 }
             }
         
     }
     public void chercher()  throws IOException{ 
         EmployeDao  eDao=new EmployeDao();
         
           list_emp=eDao.EmployeByCrit(capitaliser(paramSearch),optionSearch);
            RequestContext.getCurrentInstance().update("pnDet"); 
        
        
     }
        public String capitaliser(String mot){ 
    StringBuilder s= new StringBuilder();
    s.append(Character.toTitleCase(mot.charAt(0))).append(mot.substring(1,mot.length()).toLowerCase());
    return s.toString();
    }
}

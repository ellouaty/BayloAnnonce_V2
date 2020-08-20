/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

 
import model.*;
import session.Util;
import dao.UtilisateurConnecteDao; 
import email.SendMail;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Random;  
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;  
import javax.swing.JOptionPane;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ibm
 */@Named
 @SessionScoped
public class login implements Serializable{
    @NotNull(message = "Champs obligatoire")
  @Size(min = 5,message = "5 a 30 caractéres") 
      private String nom ; 
     @NotNull(message = "Champs obligatoire")
      private String ville;
      @NotNull(message = "Champs obligatoire")
     @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email invalid")
      private String  email;
     @NotNull(message = "Champs obligatoire")
       @Size(min = 5,message = "5 a 30 caractéres") 
      private String motDepasse ;
       @NotNull(message = "Champs obligatoire")
      @Pattern(regexp = "^0[567]\\d{8}$",message = "Numero de Telphone invalid")
     private String tel  ;
     private boolean visibilite;
      private  String msg;
      private String codeRecu;
      private String codeRecuPwd;
      private   int cpt=3;
       @NotNull(message = "Champs obligatoire")
        @Size(min = 5,message = "5 a 30 caractéres") 
      private String newPass;
      private UploadedFile file; 
              
         private String code;     
       private   int tentative=1;  
        private String pageDemande;
        private String msgtentativ; 
        private String cptExiste; 
     @NotNull(message = "Entrez un nom")
     @Size(min = 5,message = "5 a 30 caractéres") 
        private String nomNew;
     @NotNull(message = "Champs obligatoire")
      private String villeNew;
     @NotNull(message = "Champs obligatoire")
      @Pattern(regexp = "^0[567]\\d{8}$",message = "Numero de Telphone invalid")
     private String telNew  ;
     
   private String ancienPwd;
        @NotNull(message = "Champs obligatoire")
  @Size(min = 5,message = "5 a 30 caractéres") 
    private String newPwd;
        private boolean newVisib;

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    public int getTentative() {
        return tentative;
    }

    public void setTentative(int tentative) {
        this.tentative = tentative;
    }
        
        
        
        
        
        
        
        public login() {
        
    }

    

    public String getPageDemande() {
        return pageDemande;
    }

    public void setPageDemande(String pageDemande) {
        this.pageDemande = pageDemande;
    }

    public boolean isNewVisib() {
        return newVisib;
    }

    public void setNewVisib(boolean newVisib) {
        this.newVisib = newVisib;
    }
        
        
        

    public String getTelNew() {
        return telNew;
    }

    public void setTelNew(String telNew) {
        this.telNew = telNew;
    }
      
    //getters setters
      public String getVille() {
        return ville;
    }      

    public String getMsgtentativ() {
        return msgtentativ;
    }

    public String getNomNew() {
        return nomNew;
    }

    public void setNomNew(String nomNew) {
        this.nomNew = nomNew;
    }

    public void setMsgtentativ(String msgtentativ) {
        this.msgtentativ = msgtentativ;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAncienPwd() {
        return ancienPwd;
    }

    public void setAncienPwd(String ancienPwd) {
        this.ancienPwd = ancienPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    

    public String getCptExiste() {
        return cptExiste;
    }

    public void setCptExiste(String cptExiste) {
        this.cptExiste = cptExiste;
    }

    public String getVilleNew() {
        return villeNew;
    }

    public void setVilleNew(String villeNew) {
        this.villeNew = villeNew;
    }
        
   
  
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

  

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

   public String getCodeRecuPwd() {
        return codeRecuPwd;
    }

    public void setCodeRecuPwd(String codeRecuPwd) {
        this.codeRecuPwd = codeRecuPwd;
    }
  public String getCodeRecu() {
        return codeRecu;
    }

    public void setCodeRecu(String codeRecu) {
        this.codeRecu = codeRecu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
     public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMotDepasse() {
        return motDepasse;
    }

    public void setMotDepasse(String motDepasse) {
        this.motDepasse = motDepasse;
    }

    public boolean isVisibilite() {
        return visibilite;
    }

    public void setVisibilite(boolean visibilite) {
        this.visibilite = visibilite;
    }

  public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 

  
  public void authentification() throws IOException{
       UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
         UtilisateurConnecte uti= udao.authentifier(email,motDepasse);
        
         HttpSession session = Util.getSession();
        
        if (uti!=null) {
          
            session.setAttribute("user",uti);    
           nom=uti.getNom();
            
            msg="";
             RequestContext.getCurrentInstance().execute("$(\"#dialog\").hide()");
           if(pageDemande.equals("chatBTN")){
              MessageBean m=new MessageBean();
              m.chatterBTN();
           } 
           
           else{ 
               FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/"+pageDemande);   
           }
          String ip=User_IP();
               String pays=Pays_User(ip);
                session.setAttribute("pays", pays);
                 session.setAttribute("ip", ip);  
        } else { 
           
       
          msg="*Email ou mot de passe invalide .";
            RequestContext.getCurrentInstance().update("vld");           
       }
      
       
    }
         //l’utilisation d’un script / service tiers gratuit.
      
  public String Pays_User(String ip){
            String paysByIp="";
        try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://ipapi.co/"+ip+"/json/").openStream(), "UTF-8").useDelimiter("\\A")) {
            String TEXT=s.next();
              TEXT=TEXT.replaceAll("[\"|{|}|\n|\\s]",""); 
           String[] Informations=TEXT.split(",");
                  String[] infoPays=Informations[9].split(":");
                paysByIp=infoPays[1]; 
  
} catch (java.io.IOException e) {
    e.printStackTrace();
    
}  return paysByIp;
    }
  
  
    
 
      public String User_IP(){
          String ip="";
                       try (java.util.Scanner s = new java.util.Scanner(new java.net.URL("https://api.ipify.org").openStream(), "UTF-8").useDelimiter("\\A")) {
           
             ip= s.next();
            
} catch (java.io.IOException e) {
    e.printStackTrace();
    
} 
                        
            return ip; }
      
      
    public void pageDemander(String p){
       pageDemande=p;
     }
  
    
    
    public void reintialiserPwd(){ 
            UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
         boolean rep= udao.findByemail(email); 
        if(rep==true){
         Random r=new Random();
        int c =1000+r.nextInt(8999);
         code=String.valueOf(c);
         envoyerEmail(email.trim(),code+" est votre code de confirmation Baylo ",
                 "Bonjour,\n\n Pour réinitialiser votre mot de passe.Voici votre Code de Confirmation: \n\t"+code);
         
         
         RequestContext.getCurrentInstance().execute("$(\"#loginPnlPwdOUBL2\").show()");
         RequestContext.getCurrentInstance().execute("$(\"#loginPnlPwdOUBL1\").hide()");
          RequestContext.getCurrentInstance().execute("$(\"#loginPnlPwdOUBL3\").hide()");}
        else{
        RequestContext.getCurrentInstance().execute("$(\"#compteInexiMsg\").show()");}
    
    }
    public  void  suivant() throws InterruptedException{
        
      UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
      boolean rep=udao.findByemail(email.trim());
      if(rep==false){ 
          inscrire();
        
          RequestContext.getCurrentInstance().execute("$(\"#cptExiste\").hide()");
       RequestContext.getCurrentInstance().execute("$(\"#btnSuiv\").hide()");
       RequestContext.getCurrentInstance().execute("$(\"#codeRecu\").show()");
        RequestContext.getCurrentInstance().execute("$(\"#btnNoCode\").show()");
       RequestContext.getCurrentInstance().execute("$(\"#btnInsc\").show()"); 
       RequestContext.getCurrentInstance().execute("$(\"#labInfosC\").show()");
       RequestContext.getCurrentInstance().execute("$(\"#visibilite\").hide()");
       RequestContext.getCurrentInstance().execute("$(\"#loginPanel1Insc\").hide()");
       RequestContext.getCurrentInstance().execute("$(\"#labEntCod\").show()");  }
      else{
          RequestContext.getCurrentInstance().execute("$(\"#cptExiste\").show()");
         cptExiste="*adresse email déja utilisé.";
      }
             
    }
   
   
    public String capitaliser(String mot){ 
    StringBuilder s= new StringBuilder();
    s.append(Character.toTitleCase(mot.charAt(0))).append(mot.substring(1,mot.length()).toLowerCase());
    return s.toString();
    }
     public void deconnect_particulier() throws IOException{  
          HttpSession session = Util.getSession();
               session.invalidate();     
             RequestContext.getCurrentInstance().execute("$(\"#dd\").hide()");
      FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Acceuil");   
       }

     public void inscrire(){  
        Random r=new Random();
        int c =1000+r.nextInt(8999);
         code=String.valueOf(c);  
         envoyerEmail(email,code+" est votre code de confirmation Baylo ","Bonjour "+nom+",\n\n Vous avez récemment creé un compte sur Baylo.Voici votre Code de Confirmation: \n\t"+code);
         }
      public void test(){ 
        
      inscrire();
      }
     public static void main(String[] args) {
        login L=new login();
          
        
        L.inscrire();
    }
     public void verifiCode_validation_codeRecuPwd(){
     boolean val=verifiCode_validation(codeRecuPwd);
     
if(val==true){
            RequestContext.getCurrentInstance().execute("$(\"#loginPnlPwdOUBL3\").show()");
         RequestContext.getCurrentInstance().execute("$(\"#loginPnlPwdOUBL1\").hide()");
          RequestContext.getCurrentInstance().execute("$(\"#loginPnlPwdOUBL2\").hide()");
    
}
     }
    public void verifiCode_validation_codeRecu() throws IOException{ 
       
        
        
    if(tentative<=cpt &&tentative>=1){
      
             
     boolean val=verifiCode_validation(codeRecu);
          tentative=tentative+1;
       if(val==true){
  
      
       UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
    UtilisateurConnecte e=new UtilisateurConnecte();
       e.setNom(capitaliser(nom));
    e.setEmailUtilConnecte(capitaliser(email)); 
    e.setTelUtilConnecte(tel);
    e.setVilleUtilConnecte(ville); 
   e.setNoteEmailUtilConnecte("");
   e.setNoteTelUtilConnecte("");
   e.setDegreNoteTELUtilConnecte("false");
   e.setDegreNoteEmailUtilConnecte("false");
   e.setEtatcompte("normal");
     if(visibilite==true){
     e.setTelVisiblUtilConnecte("ok");}
     else{
          e.setTelVisiblUtilConnecte("ko");
     }
     e.setPasswordUtilConnecte(motDepasse); 
      
     e.setPhProfil("c_1.png"); 
     
     
      boolean b=udao.ajouterUtilisateurConnecte(e);
        if(b==true){   
         HttpSession session = Util.getSession();
           session.setAttribute("user",e);   
               FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Acceuil");  } 
       
     
         }
    if(val==false){
         cpt--;
          msgtentativ=cpt+"tentative(s) restants";        
     
             RequestContext.getCurrentInstance().execute("$(\"#tentative\").show()");
             
         RequestContext.getCurrentInstance().update("tentative");
    }
    
    
    
    }
       
         
     else{
              
               RequestContext.getCurrentInstance().execute("$(\"#loginPanel1Insc\").hide()");
                      RequestContext.getCurrentInstance().execute("$(\"#btnSuiv\").hide()"); 
              RequestContext.getCurrentInstance().execute("$(\"#labInfosC\").hide()"); 
              
               RequestContext.getCurrentInstance().execute("$(\"#btnNoCode\").hide()"); 
                RequestContext.getCurrentInstance().execute("$(\"#codeRecu\").hide()"); 
             
             RequestContext.getCurrentInstance().execute("$(\"#tentative\").hide()"); 
              RequestContext.getCurrentInstance().execute("$(\"#prob\").show()");
               RequestContext.getCurrentInstance().execute("$(\"#btnInsc\").hide()");
               
         } 
     
     }
          
  public void modifierVille(){   
        HttpSession session = Util.getSession();
        UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
          UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user"); 
         UtilisateurConnecte e=udao.getById(u.getIdUtilisateurConnecte());
         e.setVilleUtilConnecte(villeNew);  
          udao.modifierUtilisateurConnecte(e);
           session.setAttribute("user",e); 
           ville=e.getVilleUtilConnecte();
           RequestContext.getCurrentInstance().update("labVille");
          RequestContext.getCurrentInstance().execute("$(\"#h\").hide()");
                   RequestContext.getCurrentInstance().execute("$(\"#j\").show()"); } 
                  
  

     public void modifierPwd() throws IOException{ 
         UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
             boolean t= udao.modifierPwdUtilisateurConnecte(email,newPass);
             if(t==true){ 
         motDepasse=newPass;
        
       authentification();
              
          } 
     }
    public boolean verifiCode_validation(String c){
           
          return c.equals(code);
         
     }
      public void envoyerEmail(String toEmail,String subject,String message){
          
          String user = "****@GMAIL.COM";
                String pass = "*****";
                SendMail.send(toEmail,subject, message, user, pass); 
                
      }      
      public void saveFile(String name)throws MalformedURLException, IOException{
    
      try (InputStream in = file.getInputstream()) { 
            String chemin="C:/Users/ibm/Desktop/BayloAnnonce_V2/web/resources/img/"+name;
            
            File f=new File(chemin);
          try (FileOutputStream out = new FileOutputStream(f)) {
              byte[] buffer=new byte[1024];
              int length;
              while((length=in.read(buffer))>0){
                  out.write(buffer, 0, length);
                   
                       
              }
            
          
          }} 
      
      }
               
       public void uploadPh() throws IOException, InterruptedException {
           
             if(file!=null){ 
             UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
        HttpSession session=Util.getSession();
    UtilisateurConnecte u= (UtilisateurConnecte) session.getAttribute("user");
     UtilisateurConnecte e=udao.getById(u.getIdUtilisateurConnecte());
     String[] c=file.getFileName().split("/");
       String name=e.getIdUtilisateurConnecte()+c[c.length-1].replace("-","").replace("'","").replace("\"",""); 
  saveFile(name);
        e.setPhProfil(name);
        udao.modifierUtilisateurConnecte(e);  
       session.setAttribute("user", e);
       Thread.sleep(1000);
 
    RequestContext.getCurrentInstance().update("cont");
  RequestContext.getCurrentInstance().update("im2");
             RequestContext.getCurrentInstance().update("im1");}
             
           
    }
   
    public void modifierNom(){  
        if(nomNew.replaceAll("\\s","").isEmpty()||nomNew.replaceAll("\\s","").length()<5){ 
             RequestContext.getCurrentInstance().execute("$(\"#esp\").show()");
        }else{
             RequestContext.getCurrentInstance().execute("$(\"#esp\").hide()");
        
        HttpSession session = Util.getSession();
        UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
          UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user"); 
         UtilisateurConnecte e=udao.getById(u.getIdUtilisateurConnecte());
         e.setNom(capitaliser(nomNew));
          udao.modifierUtilisateurConnecte(e);
           session.setAttribute("user",e); 
           nom=e.getNom();
             RequestContext.getCurrentInstance().update("n");     
                  RequestContext.getCurrentInstance().update("labNom");  
                   RequestContext.getCurrentInstance().execute("$(\"#b\").hide()");
                   RequestContext.getCurrentInstance().execute("$(\"#a\").show()");}
                  
   }   
 
     
  
    public void modifierTel(){ 
         UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
        HttpSession session = Util.getSession();
         UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user");
          UtilisateurConnecte e=udao.getById(u.getIdUtilisateurConnecte());
         e.setTelUtilConnecte(telNew);
          if(newVisib==true){ 
          e.setTelVisiblUtilConnecte("ok");}
     else{ 
                  e.setTelVisiblUtilConnecte("ko"); 
     }
         
          udao.modifierUtilisateurConnecte(e);
          session.setAttribute("user",e); 
       telNew="";
       newVisib=false;
       
        RequestContext.getCurrentInstance().update("telInsc");
        RequestContext.getCurrentInstance().update("visibilite");
                   
               RequestContext.getCurrentInstance().execute("$(\"#e\").toggle()");
                 RequestContext.getCurrentInstance().execute("$(\"#f\").toggle()"); 
                 RequestContext.getCurrentInstance().execute("$(\"#k\").toggle()"); 
                  RequestContext.getCurrentInstance().update("telmod");  
              RequestContext.getCurrentInstance().update("visTel");
                  
   }   
   
    public void modifierPwdCon(){ 
        
        HttpSession session = Util.getSession();
        UtilisateurConnecteDao udao=new UtilisateurConnecteDao();
          UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user");
    
          if(u.getPasswordUtilConnecte().equals(ancienPwd)){
              RequestContext.getCurrentInstance().execute("$(\"#pwdanc\").hide()");
              UtilisateurConnecte e=udao.getById(u.getIdUtilisateurConnecte());
               e.setPasswordUtilConnecte(newPwd); 
                udao.modifierUtilisateurConnecte(e);
                   session.setAttribute("user",e); 
                   ancienPwd="";
                   newPass="";
                    RequestContext.getCurrentInstance().update("passwordNew");
                     RequestContext.getCurrentInstance().update("passwordAncien");
                     RequestContext.getCurrentInstance().execute("$(\"#c\").toggle()");
                 RequestContext.getCurrentInstance().execute("$(\"#d\").toggle()"); 
 
          }
           
          else{
               RequestContext.getCurrentInstance().execute("$(\"#pwdanc\").show()");
              
          }
    
                  
   }   

  
  
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.AnnonceDao;
import dao.ChatDao;
import dao.MessageDao;
import dao.UtilisateurConnecteDao;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections; 
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;  
import javax.faces.context.FacesContext; 
import javax.servlet.http.HttpSession;  
import model.Annonce;
import model.Chat;
import model.Message;
import model.UtilisateurConnecte;
import org.primefaces.context.RequestContext;
import session.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author ibm
 */
 @Named
 @SessionScoped
public class MessageBean implements Serializable {
    
    private String lastdateUpdate;
    private String lastHeureUpdate;
    private String contenu;
    private  UtilisateurConnecte emeteur;
    private UtilisateurConnecte recepteur;
    private int notifications;
    private List <Message> list_messageSpam;
    private BigDecimal idCh;
    private BigDecimal idAnnToMsg;
    
    private List <Message> list_msgChat;
   
      
 public MessageBean() { }
 
  @PostConstruct
         public void init()   {
            RefrechNotif();
        }
         
 //getters et setters
    public int getNotifications() {
        return notifications;
    }

    public BigDecimal getIdCh() {
        return idCh;
    }

    public void setIdCh(BigDecimal idCh) {
        this.idCh = idCh;
    }

    public void setNotifications(int notifications) {
        this.notifications = notifications;
    }

    public UtilisateurConnecte getRecepteur() {
        return recepteur;
    }

 
    public List<Message> getList_msgChat() {
        return list_msgChat;
    }

    public void setList_msgChat(List<Message> list_msgChat) {
        this.list_msgChat = list_msgChat;
    }

    public void setRecepteur(UtilisateurConnecte recepteur) {
        this.recepteur = recepteur;
    }
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }  

    public List<Message> getList_messageSpam() {
        return list_messageSpam;
    }

    public UtilisateurConnecte getEmeteur() {
        return emeteur;
    }

    public void setEmeteur(UtilisateurConnecte emeteur) {
        this.emeteur = emeteur;
    }

    public void setList_messageSpam(List<Message> list_messageSpam) {
        this.list_messageSpam = list_messageSpam;
    }
      
     public void notificationByChat(UtilisateurConnecte u,Chat c){
             MessageDao msdao=new MessageDao();
         List<Message> l=msdao.MessageUser_chat_NonLu(c,u); 
         int nb=l.size();
         c.setNbMessageNonLu(nb); 
         }

    
     
     
     
  
   public void sendMessage() throws IOException {  
       MessageDao msdao=new MessageDao();
       Message message=new Message();
       HttpSession s=Util.getSession();
       Chat c= (Chat) s.getAttribute("chatCourant");
         UtilisateurConnecte u=(UtilisateurConnecte) s.getAttribute("user"); 
       
       if(c.getContact().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
           emeteur=c.getContact();
           recepteur=c.getUtilisateurConnecte();
           
   } else if(c.getUtilisateurConnecte().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
           emeteur=c.getUtilisateurConnecte();
           recepteur=c.getContact(); 
          
       } 
        message.setUtilisateurConnecteByIdUtilConEnvoi(emeteur);
      message.setUtilisateurConnecteByIdUtilConRecoi(recepteur); 
                
       message.setDateMessage(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
         message.setHeureMessage(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE)));
         message.setChat(c);
        message.setContenuMessage(contenu);
        message.setEtatMessage("nonLu");
         message.setModMessage("nonMod");
       msdao.ajouterMessage(message);  
      
       ChatDao cd=new ChatDao();
       Chat ch=cd.getById(c.getIdChat());
       ch.setLastMessage(message.getContenuMessage());
       ch.setDateLastMessage(message.getDateMessage()+"_"+message.getHeureMessage());
       ch.setEtatLastMsg("nonLu");
       ch.setIdRecepLast(message.getIdMessage());
       cd.modifierChat(ch);
      
     contenu="";
         RequestContext.getCurrentInstance().update("idmsg"); 
      
    }
   
    public static void main(String[] args) throws IOException {
        MessageBean mb=new MessageBean();
        UtilisateurConnecteDao UD=new UtilisateurConnecteDao();
        UtilisateurConnecte u=UD.getById(BigDecimal.ONE);
        ChatDao cd=new ChatDao();
       Chat c= cd.getById(BigDecimal.ONE);
        mb.notificationByChat(u, c);
    }
   
   
        
       public void chatCourant(){
           
           MessageDao msdao=new MessageDao();
            Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
      ChatDao cdao =new ChatDao();
        BigDecimal idchatC= new BigDecimal(params.get("idchatC"));
     Chat c=cdao.getById(idchatC);
       
        HttpSession s=Util.getSession();
       UtilisateurConnecte u=(UtilisateurConnecte) s.getAttribute("user");
      
     
       
         if((u.getIdUtilisateurConnecte()).equals(c.getContact().getIdUtilisateurConnecte())){
            recepteur=c.getUtilisateurConnecte();}
             else{
                  recepteur=c.getContact();
             }
       
       
       
           List<Message>l_msg=msdao.getMessagesByChat(c);
          
          for (Message message :l_msg) {
                if(message.getUtilisateurConnecteByIdUtilConRecoi().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
                message.setEtatMessage("lu");
              msdao.modifierMessage(message);
                
                
                }
                
                
                if((message.getUtilisateurConnecteByIdUtilConRecoi().getIdUtilisateurConnecte()).equals(c.getIdRecepLast())){
                    Chat ch=c;
                    ch.setEtatLastMsg("lu");
                    cdao.modifierChat(ch);
                }
          }  
       
        s.setAttribute("list_Last",msdao.getMessagesByChat(c));
        s.setAttribute("chatCourant",c);
        s.setAttribute("contact",recepteur);
        RequestContext.getCurrentInstance().update("contact"); 
       RequestContext.getCurrentInstance().update("messages"); 
    }       
   
       public void creerChat() throws IOException{
            Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id= (String) params.get("idAnnToMsg");
           MessageDao msdao=new MessageDao();
             ChatDao cdao =new ChatDao();
         Message message=new Message();
       Chat c=new Chat();
       c.setUtilisateurConnecte(emeteur);
        c.setContact(recepteur);
       
       message.setContenuMessage(contenu);
         message.setDateMessage(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
         message.setHeureMessage(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE)));
         message.setChat(c); 
          message.setUtilisateurConnecteByIdUtilConEnvoi(emeteur);
           message.setUtilisateurConnecteByIdUtilConRecoi(recepteur);
           message.setEtatMessage("nonLu");
           message.setModMessage("nonMod");
          
         c.setLastMessage(message.getContenuMessage());
       c.setDateLastMessage(message.getDateMessage()+"_"+message.getHeureMessage());
       c.setEtatLastMsg("nonLu");
       c.setIdRecepLast(message.getIdMessage());
      cdao.ajouterChat(c); 
      msdao.ajouterMessage(message);
            contenu="";  
         FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+id);
         
   }
    public void firstUnreadMessage(){
          ChatDao cdao =new ChatDao();
        HttpSession s=Util.getSession();
        
       Chat c=(Chat) s.getAttribute("chatCourant");
        BigDecimal idLastmessage= (BigDecimal) s.getAttribute("idlast");
             
        UtilisateurConnecte u=(UtilisateurConnecte) s.getAttribute("user");
       List<Chat> list_chat=(List<Chat>) cdao.ChatbyUser(u); 
    
                       Collections.sort(list_chat,new Comparator<Chat>(){
                    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                @Override
                   public int compare(Chat o1, Chat o2) {
                     LocalDateTime hp1=LocalDateTime.parse(o1.getDateLastMessage().split("_")[0]+" "+o1.getDateLastMessage().split("_")[1],formatter);
                LocalDateTime hp2=LocalDateTime.parse(o2.getDateLastMessage().split("_")[0]+" "+o2.getDateLastMessage().split("_")[1],formatter);
                 return (hp2).compareTo(hp1);
                   }
                   
               });
              
               
  
       
        for (Chat ch : list_chat) {
                     notificationByChat(u,ch);
                    if(ch.getContact().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
                        ch.setEtiquette(ch.getUtilisateurConnecte().getNom());
                         ch.setEtiquettePhoto(ch.getUtilisateurConnecte().getPhProfil());
                    }else{
                         ch.setEtiquette(ch.getContact().getNom());
                           ch.setEtiquettePhoto(ch.getContact().getPhProfil());
                    } }
      
        MessageDao msdao=new MessageDao();
     
       List<Message> messages=msdao.getMessagesByChat_msg(c,idLastmessage);
       
        
      Message m=null;
      RequestContext ctx = RequestContext.getCurrentInstance();
       
        
       for (Message ms : messages) {
              
    
           DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
       
        LocalDateTime dateHeureMsg=LocalDateTime.parse(ms.getDateMessage()+" "+ms.getHeureMessage(),formatter);
                LocalDateTime lastUpdate=LocalDateTime.parse(lastdateUpdate+" "+lastHeureUpdate,formatter);
                if(dateHeureMsg.isAfter(lastUpdate)){
                     m  = ms;
                }
                 
        }
             
       ctx.addCallbackParam("ok","KO");
       
        if(m!=null){
         ctx.addCallbackParam("ok","ok");
        ctx.addCallbackParam("contenu", m.getContenuMessage());
         ctx.addCallbackParam("emeteur",m.getUtilisateurConnecteByIdUtilConEnvoi().getIdUtilisateurConnecte());
         ctx.addCallbackParam("idUser",u.getIdUtilisateurConnecte());
          lastdateUpdate = m.getDateMessage() ;
       lastHeureUpdate=m.getHeureMessage();
       s.setAttribute("idlast",m.getIdMessage());
       
        
        }
        s.setAttribute("chats",list_chat);
        RequestContext.getCurrentInstance().update("listconv");
     
   
   }   
    public void RefrechNotif(){
        MessageDao msdao=new MessageDao();
         HttpSession s=Util.getSession();
        UtilisateurConnecte u=(UtilisateurConnecte) s.getAttribute("user");
        notifications=msdao.getMessageUser_NonLu(u);
         
     RequestContext.getCurrentInstance().update("l"); 
   }
     private Annonce annonceToEdit; 

    public Annonce getAnnonceToEdit() {
        return annonceToEdit;
    }

    public void setAnnonceToEdit(Annonce annonceToEdit) {
        this.annonceToEdit = annonceToEdit;
    }

    
     
   public void onloadFirstMsg(){
       /* Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
           String idanntoedit= (String) params.get("idAnnToMsg");*/
           AnnonceDao ad=new AnnonceDao();
            
              // BigDecimal id=annonceToEdit.getIdAnnonce();
               if(idAnnToMsg!=null){
                   annonceToEdit=ad.getByIdPub(idAnnToMsg);
                   
               
           }
   }

    public BigDecimal getIdAnnToMsg() {
        return idAnnToMsg;
    }

    public void setIdAnnToMsg(BigDecimal idAnnToMsg) {
        this.idAnnToMsg = idAnnToMsg;
    }
   public void chatterBTN() throws IOException {
          
        Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        HttpSession session = Util.getSession();
         UtilisateurConnecte u=  (UtilisateurConnecte) session.getAttribute("user"); 
          
        String id= (String) params.get("idAnn");
        
        String idanntoMSG= (String) params.get("idAnnToMsg");
        
         UtilisateurConnecteDao  udao=new UtilisateurConnecteDao();
         BigDecimal idann=new BigDecimal(id.trim());
            UtilisateurConnecte an=udao.getById(idann);
            emeteur=u;
            recepteur=an;
               if((emeteur.getIdUtilisateurConnecte()).equals(recepteur.getIdUtilisateurConnecte())){ 
           
            emeteur=null;
            recepteur=null; 
           FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+idanntoMSG);
         
     
       } 
        
       else{
         ChatDao cdao =new ChatDao();
      Chat c=cdao.ChatbyUser_contact(u,an);
       
      if(c==null){
                 FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/FirstMessage/"+idanntoMSG); }
           else{ 
                List<Chat> list_chat=new ArrayList<>();
              
               list_chat=cdao.ChatbyUser(u);  
                for (Chat ch : list_chat) {
                     notificationByChat(u,ch);
                     if(ch.getContact().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
                        ch.setEtiquette(ch.getUtilisateurConnecte().getNom());
                         ch.setEtiquettePhoto(ch.getUtilisateurConnecte().getPhProfil());
                    }else{
                         ch.setEtiquette(ch.getContact().getNom());
                           ch.setEtiquettePhoto(ch.getContact().getPhProfil());
                    } 
                    
                    
                    
              
          }
                Chat   ch=cdao.ChatbyUser_contact(u,an); 
     lastdateUpdate=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        lastHeureUpdate=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
        MessageDao msdao=new MessageDao();
          List<Message>l_msg=msdao.getMessagesByChat(ch); 
          for (Message message :l_msg) { 
               Message m= msdao.getById(message.getIdMessage());
                if(m.getUtilisateurConnecteByIdUtilConRecoi().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
                m.setEtatMessage("lu"); 
              msdao.modifierMessage(m);}
          }   
            List<Message>l_m=msdao.getMessagesByChat(ch);
               session.setAttribute("chats",list_chat);
               session.setAttribute("list_Last",l_m);
               session.setAttribute("chatCourant",l_m.get(0).getChat());
               session.setAttribute("contact",recepteur);
               session.setAttribute("idlast",l_msg.get(l_m.size()-1).getIdMessage());  
                   FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Chat");
       
           }
            
        }
             
     
       
   }
   
   
   public void chatterLien() throws IOException{
       HttpSession session = Util.getSession();
         UtilisateurConnecte u=  (UtilisateurConnecte) session.getAttribute("user"); 
        
        List<Chat> list_chat=new ArrayList<Chat>();
                  ChatDao cdao =new ChatDao();
              
                  list_chat=cdao.ChatbyUser(u); 
                 List<Message>l_msg=null;
               if(!list_chat.isEmpty()){
     
            for (Chat ch : list_chat) {
                    notificationByChat(u,ch);
                    if(ch.getContact().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
                        ch.setEtiquette(ch.getUtilisateurConnecte().getNom());
                         ch.setEtiquettePhoto(ch.getUtilisateurConnecte().getPhProfil());
                    }else{
                         ch.setEtiquette(ch.getContact().getNom());
                           ch.setEtiquettePhoto(ch.getContact().getPhProfil());
                    } 
              
          }
               
                 
              Chat ch=list_chat.get(0);
                     
          MessageDao msdao=new MessageDao();
               l_msg=msdao.getMessagesByChat(ch); 
              
               for (Message m :l_msg) { 
                      if(m.getUtilisateurConnecteByIdUtilConRecoi().getIdUtilisateurConnecte().equals(u.getIdUtilisateurConnecte())){
                         
                          if(m.getEtatMessage().equals("nonLu")){ 
                m.setEtatMessage("lu");
              msdao.modifierMessage(m);}}
          }  
                 lastdateUpdate=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        lastHeureUpdate=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
            
             emeteur=u;
             if((u.getIdUtilisateurConnecte()).equals(ch.getContact().getIdUtilisateurConnecte())){
            recepteur=ch.getUtilisateurConnecte();}
             else{
                  recepteur=ch.getContact();
             }} 
         
               session.setAttribute("chats",list_chat);
               
                  
                session.setAttribute("list_Last",l_msg);
                if(l_msg!=null){
                 session.setAttribute("chatCourant",l_msg.get(0).getChat());
               session.setAttribute("contact",recepteur);
                session.setAttribute("idlast",l_msg.get(l_msg.size()-1).getIdMessage());}
             
               
                
       
   }
   public void recupMesageSpam(){
       //all message non moderer et spam(apres)
       list_messageSpam=new ArrayList<>();
       MessageDao mdao=new MessageDao();
      list_messageSpam=mdao.MessagesNonModerer();
      
      
   }
       
    public void remplirListesMsgChat(){
        list_msgChat=new ArrayList<>();
         Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String s=params.get("idChat"); 
        if(s!=null){
            idCh= new BigDecimal(s.trim());
             ChatDao chdao=new ChatDao();
             Chat c=chdao.getById(idCh);
             MessageDao msda=new MessageDao();
             list_msgChat=msda.getMessagesByChat(c);
             recepteur=list_msgChat.get(0).getChat().getContact();
             emeteur=list_msgChat.get(0).getChat().getUtilisateurConnecte();
           
             
        } 
    }
   public void validerVerifMessage(){
       
        Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String s=params.get("idmsg"); 
        BigDecimal idms= new BigDecimal(s.trim());
        MessageDao mda=new MessageDao();
        Message m=mda.getById(idms);
        m.setModMessage("mod");
        mda.modifierMessage(m);
        recupMesageSpam();
         RequestContext.getCurrentInstance().update("pnspam"); 
      
   }
   
}

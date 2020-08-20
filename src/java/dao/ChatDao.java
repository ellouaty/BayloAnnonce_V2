/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 


package dao;  
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
 
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List; 
import model.Chat;
import model.UtilisateurConnecte;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ibm
 */
public class ChatDao implements Serializable { 
    public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterChat(Chat e){
 boolean rep=false;
  Transaction tra=s.beginTransaction();
	          s.save(e);
                try{  tra.commit();
                rep=true;}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
                 return rep;}
  
public boolean modifierChat(Chat e){  
      boolean rep=false;
  Transaction tra=s.beginTransaction();
	          s.update(e);
                try{  tra.commit();
                rep=true;}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
                 return rep;
}  
   
public boolean supprimerChat(Chat e){  
        boolean rep=false;
  Transaction tra=s.beginTransaction();
	          s.delete(e);
                try{  tra.commit();
                rep=true;}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
                 return rep;
}  
 
public Chat getById(BigDecimal id){  
    Chat e=(Chat)s.get(Chat.class,id);  
    return e;  
}  
 
public List<Chat> getChats(){  
       Transaction tra=s.beginTransaction();
    List<Chat> list=new ArrayList<Chat>();  
    Criteria criteria = s.createCriteria(Chat.class);
            list=criteria.list();
    tra.commit();
    return list;  
  
}  
public List<Chat> ChatbyUser(UtilisateurConnecte user) {
         Criteria criteria = s.createCriteria(Chat.class);
         criteria.add(Restrictions.eq("utilisateurConnecte",user));
         List<Chat>  listChat=criteria.list();
       
          Criteria criteria1 = s.createCriteria(Chat.class);
         criteria1.add(Restrictions.eq("contact",user));
        List<Chat>  listChat1=criteria1.list(); 
         for (Chat chat : listChat1) {
         listChat.add(chat);
    }
          
        return listChat;
 
        
    }
    public  Chat  ChatbyUser_contact(UtilisateurConnecte user,UtilisateurConnecte an) {
          Chat  c;
         
        Criteria criteria = s.createCriteria(Chat.class);
         criteria.add(Restrictions.eq("utilisateurConnecte",user));
            criteria.add(Restrictions.eq("contact",an));
         c=(Chat) criteria.uniqueResult();
         if(c==null){
             Criteria criteria1 = s.createCriteria(Chat.class);
         criteria1.add(Restrictions.eq("utilisateurConnecte",an));
            criteria1.add(Restrictions.eq("contact",user)); 
            c=(Chat) criteria1.uniqueResult();
         }
         
         
         
       return c;
 
        
    }
  
  
    public static void main(String[] args) {
        ChatDao cd=new ChatDao();
        Chat c=cd.getById(BigDecimal.ONE);
        
        String [] tab=c.getDateLastMessage().split("_");
        String date=tab[0];
        String heur=tab[1];
      
         DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       LocalDateTime hp=LocalDateTime.parse(tab[0]+" "+tab[1],formatter); 
           }
   
}

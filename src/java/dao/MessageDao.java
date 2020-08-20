 
package dao;  
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
 import model.Message;
import model.Chat; 
import model.UtilisateurConnecte; 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions; 
 

/**
 *
 * @author ibm
 */
public class MessageDao implements Serializable{ 
 
  public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterMessage(Message e){  
   boolean rep=false;
    Transaction tra=s.beginTransaction();
	          s.save(e); 
               try{  tra.commit();
                rep=true;}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
             }
               return rep;
}  
  
public boolean modifierMessage(Message e){ 
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
   
public boolean supprimerMessage(Message e){  
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
 
public Message getById(BigDecimal id){  
    Message e=(Message)s.get(Message.class,id);  
    return e;  
}  
 
 
public List<Message> getMessages(){  
    
         List<Message> list=new ArrayList<Message>(); 
    String rs = "from Message";
	
		  Query q = s.createQuery(rs);
	
                 list=q.list();
    return list;  
  
}  

    
     public List<Message> getMessagesByChat_msg(Chat c,BigDecimal id){  
    
         List<Message> list=new ArrayList<Message>(); 
    String rs = "from Message where idMessage>:id and chat=:c  order by idMessage ";
	
		  Query q = s.createQuery(rs);
                   q.setParameter("id",id);
	           q.setParameter("c",c);
                   list=q.list();
    return list;  
  
}  
    
    public List<Message> getMessagesByChat(Chat c){  
    
         List<Message> list=new ArrayList<Message>(); 
    String rs = "from Message where chat=:c order by idMessage";
	
		  Query q = s.createQuery(rs);
	 q.setParameter("c",c);
                 list=q.list();
    return list;  
  
}  

    public Message lastMessage(Chat c) {
        
         Query Q=s.createQuery("select max(idMessage)  FROM Message where chat=:c") ;
          Q.setParameter("c",c);
       BigDecimal idMessage=(BigDecimal) Q.uniqueResult();
        Message m=getById(idMessage);
         return m;
    }

    public int getMessageUser_NonLu(UtilisateurConnecte u) {
        List<Message> l=new ArrayList<>();
         Criteria c = s.createCriteria(Message.class); 
                 c.add(Restrictions.eq("utilisateurConnecteByIdUtilConRecoi",u));
        c.add(Restrictions.eq("etatMessage","nonLu"));
      l= c.list();
        int nbMessage=l.size();
    return nbMessage;
    
    }
   
 
public List<Message> MessageUser_chat_NonLu(Chat ch,UtilisateurConnecte recep){
             Criteria c = s.createCriteria(Message.class); 
               c.add(Restrictions.eq("chat",ch));
                  c.add(Restrictions.eq("etatMessage","nonLu"));
                   c.add(Restrictions.eq("utilisateurConnecteByIdUtilConRecoi",recep));
                    List<Message> l= c.list();
                       return l;
}
    public static void main(String[] args) {
        ChatDao cd=new ChatDao();
        Chat c=cd.getById(BigDecimal.ONE);
        MessageDao mdao=new MessageDao();
         Message lastMessage=mdao.lastMessage(c); 
    }

    public List<Message> MessagesNonModerer() {
        Criteria c = s.createCriteria(Message.class); 
            c.add(Restrictions.eq("modMessage","nonMod"));
            //soupçon spam
              List<Message> l= c.list();
                
                         return l; }
  
}

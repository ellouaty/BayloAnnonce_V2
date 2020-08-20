 
package dao;  
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import model.UtilisateurConnecte;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale; 
import model.ActionUser; 
import model.Annonce;
import model.ActionUserId;
import org.hibernate.Criteria;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
 
public class ActionUserDao implements Serializable{ 
    public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
 
public boolean ajouterActionUser(ActionUser e){  
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
  
public boolean modifierActionUser(ActionUser e){ 
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
   
public boolean supprimerActionUser(ActionUser e){  
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
 
public ActionUser getById(BigDecimal id){  
     Transaction tra=s.beginTransaction();
    ActionUser e=(ActionUser)s.get(ActionUser.class,id);  
     try{  tra.commit();}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
    return e;  
}  
 
public List<ActionUser> getActionUsers(){  
      List<ActionUser> list=new ArrayList<ActionUser>();  
       Criteria criteria = s.createCriteria(ActionUser.class);
            list=criteria.list();
    return list;
  
}  
     
    public List<ActionUser> ActionUserSurAnnonce(BigDecimal id) {
           
   AnnonceDao ad=new AnnonceDao();
   Annonce a=ad.getByIdAllEtat(id);
   Criteria criteria = s.createCriteria(ActionUser.class); 
   criteria.add(Restrictions.eq("annonce", a));
   List<ActionUser> list=criteria.list();
  
  return list;
    
      }
}

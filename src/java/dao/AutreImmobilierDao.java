/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 


package dao;  
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.AutreImmobilier; 
import model.Annonce;
import org.hibernate.Criteria; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions; 

/**
 *
 * @author ibm
 */
public class AutreImmobilierDao implements Serializable { 
     static Session  s = NewHibernateUtil1.getSessionFactory().openSession();
 
public boolean ajouterAutreImmobilier(AutreImmobilier e){  
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
  
public boolean modifierAutreImmobilier(AutreImmobilier e){  
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
   
public boolean supprimerAutreImmobilier(AutreImmobilier e){  
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
 
public AutreImmobilier getById(BigDecimal id){  
    AutreImmobilier e=(AutreImmobilier)s.get(AutreImmobilier.class,id);  
    return e;  
}  
 
public List<AutreImmobilier> getAutreImmobiliers(){  
    List<AutreImmobilier> list=new ArrayList<AutreImmobilier>();  
  Criteria criteria = s.createCriteria(AutreImmobilier.class);
  list=criteria.list();
    return list;  
  
}  

        public AutreImmobilier AutreImmoByAnn (Annonce a){
      
     Criteria criteria = s.createCriteria(AutreImmobilier.class);
    
			criteria.add(Restrictions.eq("annonce",a));
                  AutreImmobilier o =(AutreImmobilier) criteria.uniqueResult();
		 
    return o;
    
}
}

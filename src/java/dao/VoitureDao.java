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
import model.Voiture; 
import model.Annonce;
import org.hibernate.Criteria; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions; 

/**
 *
 * @author ibm
 */
public class VoitureDao implements Serializable{ 
       public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterVoiture(Voiture e){  
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
  
public boolean modifierVoiture(Voiture e){ 
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
   
public boolean supprimerVoiture(Voiture e){  
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
 
public Voiture getById(BigDecimal id){  
    Voiture e=(Voiture)s.get(Voiture.class,id);  
    return e;  
}  
 
public List<Voiture> getVoitures(){  
    List<Voiture> list=new ArrayList<Voiture>();  
    Criteria criteria = s.createCriteria(Voiture.class); 
  list=criteria.list();
    return list;  
  
}

public Voiture VoitureByANN(Annonce a){
      
     Criteria criteria = s.createCriteria(Voiture.class);
    
			criteria.add(Restrictions.eq("annonce",a));
                     Voiture v=(Voiture) criteria.uniqueResult();
		 
    return v;
    
}
}

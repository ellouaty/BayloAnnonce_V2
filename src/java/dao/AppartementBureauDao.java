/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 


package dao;  
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.AppartementBureau; 
import model.MaisonVilla;
import org.hibernate.Criteria; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions; 

/**
 *
 * @author ibm
 */
public class AppartementBureauDao implements Serializable{/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ static Session  s = NewHibernateUtil1.getSessionFactory().openSession();
 
 
  
 public boolean ajouterAppartement(AppartementBureau e){  
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
  
public boolean modifierAppartement(AppartementBureau e){ 
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
   
public boolean supprimerAppartement(AppartementBureau e){  
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

  
 
public List<AppartementBureau> getAppartements(){  
    List<AppartementBureau> list=new ArrayList<AppartementBureau>();  
    Criteria criteria = s.createCriteria(AppartementBureau.class);
            list=criteria.list();
    return list;  
  
}

        public AppartementBureau AppartementBureuByMV(MaisonVilla mv){
      AppartementBureau ab=new AppartementBureau();
     Criteria criteria = s.createCriteria(AppartementBureau.class);
    
			criteria.add(Restrictions.eq("maisonVilla",mv));
                 ab =(AppartementBureau) criteria.uniqueResult();
		 
    return ab;
    
}
}

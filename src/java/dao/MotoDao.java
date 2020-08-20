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
import model.Moto;
import model.Annonce;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ibm
 */
public class MotoDao implements Serializable{ 
     
      public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
      
public boolean ajouterMoto(Moto e){  
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
  
public boolean modifierMoto(Moto e){ 
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
   
public boolean supprimerMoto(Moto e){  
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
 
public Moto getById(BigDecimal id){  
    Moto e=(Moto)s.get(Moto.class,id);  
    return e;  
}  
 
public List<Moto> getMotos(){  
    List<Moto> list=new ArrayList<Moto>();  
  Criteria criteria = s.createCriteria(Moto.class); 
  list=criteria.list();
    return list;  
  
}  
    
     
        public Moto MotoByAnn(Annonce a){
      
     Criteria criteria = s.createCriteria(Moto.class);
    
			criteria.add(Restrictions.eq("annonce",a));
                   Moto m=(Moto) criteria.uniqueResult();
		 
    return m;
    
}
}

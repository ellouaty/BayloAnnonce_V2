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
import model.Doublant;   
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author ibm
 */
public class DoublantDao implements Serializable{ 
        public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterDoublant(Doublant e){  
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
  
public boolean modifierDoublant(Doublant e){ 
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
   
public boolean supprimerDoublant(Doublant e){  
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
 
public Doublant getById(BigDecimal id){  
    Doublant e=(Doublant)s.get(Doublant.class,id);  
    return e;  
}  
 
public List<Doublant> getDoublants(){  
    List<Doublant> list=new ArrayList<Doublant>();  
  Criteria criteria = s.createCriteria(Doublant.class); 
  list=criteria.list();
    return list;  
  
}  
}

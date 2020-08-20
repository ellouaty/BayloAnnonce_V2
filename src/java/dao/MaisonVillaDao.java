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
import model.MaisonVilla;  
import model.Annonce;
import org.hibernate.Criteria;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions; 
  
public class MaisonVillaDao implements Serializable{
  public Session  s = NewHibernateUtil1.getSessionFactory().openSession(); 
public boolean ajouterMaisonVilla(MaisonVilla e){  
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
  
public boolean modifierMaisonVilla(MaisonVilla e){ 
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
   
public boolean supprimerMaisonVilla(MaisonVilla e){  
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
 
public MaisonVilla getById(BigDecimal id){  
    MaisonVilla e=(MaisonVilla)s.get(MaisonVilla.class,id);  
    return e;  
}  
 
public List<MaisonVilla> getMaisonVillas(){  
    List<MaisonVilla> list=new ArrayList<MaisonVilla>();  
  Criteria criteria = s.createCriteria(MaisonVilla.class); 
  list=criteria.list();
    return list;  
  
}  

         public MaisonVilla MaisonVillaByAnn(Annonce a){
      MaisonVilla mv=new MaisonVilla();
     Criteria criteria = s.createCriteria(MaisonVilla.class);
    
			criteria.add(Restrictions.eq("annonce",a));
       mv =(MaisonVilla) criteria.uniqueResult();
		 
    return mv;
    
}
}

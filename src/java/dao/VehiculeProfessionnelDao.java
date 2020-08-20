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
import model.VehiculeProfessionnel;
import model.Voiture;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ibm
 */
public class VehiculeProfessionnelDao implements Serializable{ 
       public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterVehiculeProfessionnel(VehiculeProfessionnel e){  
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
  
public boolean modifierVehiculeProfessionnel(VehiculeProfessionnel e){ 
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
   
public boolean supprimerVehiculeProfessionnel(VehiculeProfessionnel e){  
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
 
public VehiculeProfessionnel getById(BigDecimal id){  
    VehiculeProfessionnel e=(VehiculeProfessionnel)s.get(VehiculeProfessionnel.class,id);  
    return e;  
}  
 
public List<VehiculeProfessionnel> getVehiculeProfessionnels(){  
    List<VehiculeProfessionnel> list=new ArrayList<VehiculeProfessionnel>();  
    Criteria criteria = s.createCriteria(VehiculeProfessionnel.class); 
  list=criteria.list();
    return list;  
  
}
 
public  VehiculeProfessionnel VproByVoiture(Voiture v){
    Criteria criteria = s.createCriteria(VehiculeProfessionnel.class);
    
			criteria.add(Restrictions.eq("voiture",v));
                   VehiculeProfessionnel vh=(VehiculeProfessionnel) criteria.uniqueResult();
		return vh;
}

    public VehiculeProfessionnel getvehiculeProfessionnelByVoiture(Voiture v) {
         VehiculeProfessionnel vh=null;
     Criteria criteria = s.createCriteria(VehiculeProfessionnel.class); 
    criteria.add(Restrictions.eq("voiture", v)); 
  vh=(VehiculeProfessionnel) criteria.uniqueResult();
    return vh;   }

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 


package dao; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Reclamation;
import org.hibernate.Criteria;
import org.hibernate.Session; 
import java.math.BigDecimal; 
import model.Employe;
import model.ReclamationId;
import org.hibernate.Transaction;

/**
 *
 * @author ibm
 */
public class ReclamationDao implements Serializable{  
      public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterReclamation(Reclamation e){  
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
  
public boolean modifierReclamation(Reclamation e){ 
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
   
public boolean supprimerReclamation(Reclamation e){  
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
 
public Reclamation getById(ReclamationId id){  
    Reclamation e=(Reclamation)s.get(Reclamation.class,id);  
    return e;  
}  
 
public List<Reclamation> getReclamations(){  
    List<Reclamation> list=new ArrayList<Reclamation>();  
    Criteria criteria = s.createCriteria(Reclamation.class); 
  list=criteria.list();
    return list;  
  
}  
    public static void main(String[] args) {
    
         String idA="15";
              String idU="2";
              String d="01-02-2020" ;
              String h="17:18:07";
                 ReclamationId recId=new ReclamationId();
                 recId.setIdAnnonce(new BigDecimal(idA.trim()));
                 recId.setIdUtilisateurConnecte(new BigDecimal(idU.trim()));
                 recId.setDateReclamation(d.trim());
                 recId.setHeureReclamation(h.trim());
              ReclamationDao recDao=new ReclamationDao();
              Reclamation R =recDao.getById(recId);
                  EmployeDao empdao=new EmployeDao();
              Employe emp=empdao.getById(new BigDecimal(2)); 
              R.setEmploye(emp);
              R.setEtatReclamation("done");
              recDao.modifierReclamation(R); 
              
    }
}

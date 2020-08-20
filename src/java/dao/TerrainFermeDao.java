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
import model.TerrainFerme;
import model.AutreImmobilier;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ibm
 */
public class TerrainFermeDao implements Serializable{ 
   public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterTerrainFerme(TerrainFerme e){  
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
  
public boolean modifierTerrainFerme(TerrainFerme e){ 
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
   
public boolean supprimerTerrainFerme(TerrainFerme e){  
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
 
public TerrainFerme getById(BigDecimal id){  
    TerrainFerme e=(TerrainFerme)s.get(TerrainFerme.class,id);  
    return e;  
}  
 
public List<TerrainFerme> getTerrainFerme(){  
    List<TerrainFerme> list=new ArrayList<TerrainFerme>();  
  Criteria criteria = s.createCriteria(TerrainFerme.class); 
  list=criteria.list();
    return list;  
  
}  
         public TerrainFerme TerrainByAutreImmo (AutreImmobilier au){
      
     Criteria criteria = s.createCriteria(TerrainFerme.class);
    
			criteria.add(Restrictions.eq("autreImmobilier",au));
                  TerrainFerme t =(TerrainFerme) criteria.uniqueResult();
		 
    return t;}
    
}

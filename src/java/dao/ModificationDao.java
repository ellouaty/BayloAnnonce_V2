package dao; 
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;  
import model.Annonce;
import model.Modification;
import org.hibernate.Criteria;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
public class ModificationDao implements Serializable{ 
    public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
 
public boolean ajouterModification(Modification e){  
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
  
public boolean modifierModification(Modification e){ 
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
   
public boolean supprimerModification(Modification e){  
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
 
public Modification getById(BigDecimal id){  
     Transaction tra=s.beginTransaction();
    Modification e=(Modification)s.get(Modification.class,id);  
     try{  tra.commit();}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
    return e;  
}  
 
public List<Modification> getModifications(){  
      List<Modification> list=new ArrayList<Modification>();  
       Criteria criteria = s.createCriteria(Modification.class);
            list=criteria.list();
    return list;
  
}  

  public Modification getModificationByAnnNew(Annonce a) { 
   
     Criteria criteria = s.createCriteria(Modification.class); 
     criteria.add(Restrictions.eq("annonceNew",a));
  Modification m=(Modification) criteria.uniqueResult();
    
   return m;} 
  

  public static void main(String[] args) {
      AnnonceDao ad=new AnnonceDao();
      Annonce a =ad.getByIdAllEtat(new BigDecimal(2));
        ModificationDao md=new ModificationDao();
        Modification m=md.getModificationByAnnNew(a); 
    }
      
}


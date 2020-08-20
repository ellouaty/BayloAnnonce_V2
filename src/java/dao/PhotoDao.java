 

package dao;  
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List; 
import model.Photo;
import model.Annonce;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ibm
 */
public class PhotoDao implements Serializable{ 
  
      public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterPhoto(Photo e){  
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
  
public boolean modifierPhoto(Photo e){ 
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
   
public boolean supprimerPhoto(Photo e){  
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
 
public Photo getById(BigDecimal id){  
    Photo e=(Photo)s.get(Photo.class,id);  
    return e;  
}  
public List<Photo> getPhotos(){  
    List<Photo> list=new ArrayList<Photo>(); 
    String rs = "from Photo";
	
		  Query q = s.createQuery(rs);
	
    list=q.list();  
    return list;   
  
}  
public List<String> getPhotosByIdAnnChemn(BigDecimal idAn){
    AnnonceDao adao=new AnnonceDao();
    Annonce a=adao.getByIdAllEtat(idAn);
      Query Q=s.createQuery("select cheminPhoto   FROM Photo WHERE annonce=:a");
        Q.setParameter("a",a);
        List<String> photo;
        photo = Q.list();
    
    
    return  photo;
} 
public List<Photo> getPhotosByIdAnn(BigDecimal idAn){
    AnnonceDao adao=new AnnonceDao();
    Annonce a=adao.getByIdAllEtat(idAn);
      Query Q=s.createQuery("FROM Photo WHERE annonce=:a");
        Q.setParameter("a",a);
        List<Photo> photo;
        photo = Q.list();
    
    
    return  photo;
} 
    public void supprimerPhotoBychemin(String chemin) {
           Criteria criteria = s.createCriteria(Photo.class);
        criteria.add(Restrictions.eq("cheminPhoto", chemin));
        List<Photo> l=criteria.list();
        if(l.get(0)!=null){
        supprimerPhoto(l.get(0));}
    }
    
}

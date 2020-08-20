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
import model.Favoris;
import model.Annonce;
import model.UtilisateurConnecte;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions; 

/**
 *
 * @author ibm
 */
public class FavorisDao implements Serializable{ 
    
  public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterFavoris(Favoris e){  
   Transaction tra=s.beginTransaction();
	          s.save(e);  
                  tra.commit();
                  return true;
}  
  
public boolean modifierFavoris(Favoris e){  
     Transaction tra=s.beginTransaction();
	          s.update(e);  
                  tra.commit();
                  return true;  
}  
   
public boolean supprimerFavoris(Favoris e){  
   Transaction tra=s.beginTransaction();
	          s.delete(e);
                  tra.commit();
                  return true;
}  
 
public Favoris getById(BigDecimal id){  
    Favoris e=(Favoris)s.get(Favoris.class,id);  
    return e;  
}  
 
public List<Favoris> getFavoriss(){  
    List<Favoris> list=new ArrayList<Favoris>();  
  Criteria criteria = s.createCriteria(Favoris.class); 
  list=criteria.list();
    return list;  
  
}  

public List<Favoris> FavorisbYuser(UtilisateurConnecte u){
     List<Favoris> l=new ArrayList<>();
                Query Q=s.createQuery("FROM Favoris WHERE utilisateurConnecte=:u");
        Q.setParameter("u",u);
          
	l=Q.list();
          List<Favoris> f=new ArrayList<>();
        for (Favoris favoris : l) {
            if("publie".equals(favoris.getAnnonce().getEtatAnnonce())){
                f.add(favoris);
            }
        
    }
                         
                         return f;
           
}

    public int nbSigneFav(Annonce annonceToEdit) {
        
         Criteria criteria = s.createCriteria(Favoris.class);
           criteria.add(Restrictions.eq("annonce",annonceToEdit));
		List<Favoris>l=criteria.list();
                        int nb=l.size();
        return nb;
    }

    public void supprimerByUser_ANNONCE(UtilisateurConnecte u, Annonce anToedit) {
       Criteria criteria1 = s.createCriteria(Favoris.class);
         criteria1.add(Restrictions.eq("utilisateurConnecte",u));
            criteria1.add(Restrictions.eq("annonce",anToedit)); 
         Favoris   f=(Favoris) criteria1.uniqueResult(); 
         supprimerFavoris(f);
    }
}

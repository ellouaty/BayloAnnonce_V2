 

package dao;    
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List; 
import java.util.Set; 
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
public class UtilisateurConnecteDao implements Serializable {  
  public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
    
 
public boolean ajouterUtilisateurConnecte(UtilisateurConnecte e){  
   Transaction tra=s.beginTransaction();
	          s.save(e);  
                  tra.commit();
                  return true;
}  
  
public boolean modifierUtilisateurConnecte(UtilisateurConnecte e){  
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
   
public void supprimerUtilisateurConnecte(UtilisateurConnecte e){  
    Transaction tra=s.beginTransaction();
              
	          s.delete(e);  
                  tra.commit(); 
}  
 






public UtilisateurConnecte getById(BigDecimal id){  
    UtilisateurConnecte e=(UtilisateurConnecte) s.get(UtilisateurConnecte.class,id);  
    return e;  
}  
 
public List<UtilisateurConnecte> getUtilisateurConnectes(){  
    List<UtilisateurConnecte> list=new ArrayList<UtilisateurConnecte>();  
    Criteria criteria = s.createCriteria(UtilisateurConnecte.class); 
  list=criteria.list();
    return list;  
  
}  


public UtilisateurConnecte authentifier(String em,String pwd){
    UtilisateurConnecte e=null;
       Query Q=s.createQuery("select idUtilisateurConnecte   FROM UtilisateurConnecte WHERE LOWER(emailUtilConnecte)=LOWER(:em) and  passwordUtilConnecte=:pwd and etatcompte=:etat");
        Q.setParameter("em",em);
         Q.setParameter("pwd",pwd);
           Q.setParameter("etat","normal");
         
        BigDecimal id=(BigDecimal) Q.uniqueResult();
        if(id!=null){
            e=getById(id);
        } 
      
        return e;
        
}

    public boolean modifierPwdUtilisateurConnecte(String email, String newPass) { 
         Transaction tra=s.beginTransaction();
                Query Q=s.createQuery("select idUtilisateurConnecte  FROM UtilisateurConnecte WHERE LOWER(emailUtilConnecte)=LOWER(:em)");
        Q.setParameter("em",email);
         BigDecimal id=(BigDecimal) Q.uniqueResult();
          if(id!=null){
                UtilisateurConnecte e=getById(id);
                e.setPasswordUtilConnecte(newPass);
                         s.update(e); 
                         tra.commit();
                         
		 
             return true;}
             else{
                 return false;
             }
    }
public boolean findByemail(String em){
      
       Query Q=s.createQuery("select idUtilisateurConnecte   FROM UtilisateurConnecte WHERE LOWER(emailUtilConnecte)=LOWER(:em)");
        Q.setParameter("em",em); 
        BigDecimal id=(BigDecimal) Q.uniqueResult();
      return id != null;
      
}
public   List<UtilisateurConnecte> UtlisateurSansAnnonceDejaPublie(){
    
    List<UtilisateurConnecte> l_u=new ArrayList<>();
    Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
     List<UtilisateurConnecte> l_all=criteria.list();
      for (UtilisateurConnecte u : l_all) {
          
          List<Annonce>l_p=new ArrayList<>();
         Set<Annonce> l_an= u.getAnnonces();
          for (Annonce annonce : l_an) {
            if(annonce.getEtatAnnonce().equals("publie")||annonce.getEtatAnnonce().equals("rejete")||annonce.getEtatAnnonce().equals("modif")
               ||annonce.getEtatAnnonce().equals("supprime")||annonce.getEtatAnnonce().equals("desactive")){
               l_p.add(annonce);
            }
        }
      if(l_p.size()<=2){
            l_u.add(u);
        } 
    }
      return l_u;
}



    public static void main(String[] args) {
        UtilisateurConnecteDao ud=new UtilisateurConnecteDao(); 
       List<UtilisateurConnecte> l= ud.UtlisateurSansAnnonceDejaPublie();
        for (UtilisateurConnecte u : l) {
         System.out.println(u.getNom()+" "+u.getEmailUtilConnecte()+" "+u.getPasswordUtilConnecte());   
        }
        
    }

 
    public List<UtilisateurConnecte> getUtilisateurConnecteByTel(String telUtilConnecte) { 
     List<UtilisateurConnecte> l=new ArrayList<>();
     Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
      criteria.add(Restrictions.eq("telUtilConnecte",telUtilConnecte));
     l=criteria.list();
        return l; }

    public List<UtilisateurConnecte> UserByCrit(String paramSearch, String optionSearch) {
        List<UtilisateurConnecte>list=new ArrayList<UtilisateurConnecte>();
         Criteria criteria = s.createCriteria(UtilisateurConnecte.class); 
     switch (optionSearch) {
             case "nom":
                 criteria.add(Restrictions.eq("nom",paramSearch));
                 break;
             case "email":
                criteria.add(Restrictions.eq("emailUtilConnecte",paramSearch));
                 break;
             case "tel":
                 criteria.add(Restrictions.eq("telUtilConnecte",paramSearch));
                 break;
             
             default:
                 break;
         }
     list=criteria.list();
     return list;
    
    }

    public void bloquerByTel(String telTobloque) {
      Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
      criteria.add(Restrictions.eq("telUtilConnecte",telTobloque));
    List<UtilisateurConnecte> l=criteria.list();
        for (UtilisateurConnecte utilisateurConnecte : l) {
            utilisateurConnecte.setEtatcompte("bloque");
            modifierUtilisateurConnecte(utilisateurConnecte);
        }
    
    }

    public void bloquerByEmail(String emailTobloque) {
     Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
      criteria.add(Restrictions.eq("emailUtilConnecte",emailTobloque));
    List<UtilisateurConnecte> l=criteria.list();
     for (UtilisateurConnecte utilisateurConnecte : l) {
            utilisateurConnecte.setEtatcompte("bloque");
             modifierUtilisateurConnecte(utilisateurConnecte);
        }
    }

    public void bloquerByIp(String ipTobloque) {
     
         Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
       List<UtilisateurConnecte> l=criteria.list();
     for (UtilisateurConnecte utilisateurConnecte : l) {
         for (String ip :utilisateurConnecte.getIps()) {
             if(ip.equals(ipTobloque)){
                  utilisateurConnecte.setEtatcompte("bloque");
             modifierUtilisateurConnecte(utilisateurConnecte);
             break;
             }
         }
           
        }
    }
  
          
    
    public void debloquerByTel(String telTobloque) {
      Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
      criteria.add(Restrictions.eq("telUtilConnecte",telTobloque));
    List<UtilisateurConnecte> l=criteria.list();
        for (UtilisateurConnecte utilisateurConnecte : l) {
            utilisateurConnecte.setEtatcompte("normal");
            modifierUtilisateurConnecte(utilisateurConnecte);
        }
    
    }

    public void debloquerByEmail(String emailTobloque) {
     Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
      criteria.add(Restrictions.eq("emailUtilConnecte",emailTobloque));
    List<UtilisateurConnecte> l=criteria.list();
     for (UtilisateurConnecte utilisateurConnecte : l) {
            utilisateurConnecte.setEtatcompte("normal");
             modifierUtilisateurConnecte(utilisateurConnecte);
        }
    }

    public void debloquerByIp(String ipTobloque) {
     
         Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
       List<UtilisateurConnecte> l=criteria.list();
     for (UtilisateurConnecte utilisateurConnecte : l) {
         for (String ip :utilisateurConnecte.getIps()) {
             if(ip.equals(ipTobloque)){
                  utilisateurConnecte.setEtatcompte("normal");
             modifierUtilisateurConnecte(utilisateurConnecte);
             break;
             }
         }
           
        }
    }

    public void debloquerbyId(BigDecimal idUtil) { 
       UtilisateurConnecte u=getById(idUtil); 
                  u.setEtatcompte("normal");
             modifierUtilisateurConnecte(u);  }
   public void bloquerbyId(BigDecimal idUtil) { 
       UtilisateurConnecte u=getById(idUtil); 
                  u.setEtatcompte("bloque");
             modifierUtilisateurConnecte(u);  }
  
        
          
}

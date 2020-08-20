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
import model.Employe;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction; 
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author ibm
 */
public class EmployeDao implements Serializable { 
 
  public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
public boolean ajouterEmploye(Employe e){  
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
  
public boolean modifierEmploye(Employe e){ 
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
   
public boolean supprimerEmploye(Employe e){  
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
 
public Employe getById(BigDecimal id){  
    Employe e=(Employe)s.get(Employe.class,id);  
    return e;  
}  
 
public List<Employe> getEmployes(){  
    List<Employe> list=new ArrayList<Employe>();  
    Criteria criteria = s.createCriteria(Employe.class); 
  list=criteria.list();
    return list;  
  
}   
    public static void main(String[] args) {
    EmployeDao empdao=new EmployeDao();
              Employe emp=empdao.getById(new BigDecimal(2)); 
        
    }

    public Employe authentifier(String login, String pwd) {
     Employe e=null;
        Query Q=s.createQuery("select idEmploye   FROM Employe WHERE LOWER(loginEmploye)=LOWER(:log) and passwordEmploye=:pwd");
        Q.setParameter("log",login);
         Q.setParameter("pwd",pwd);
         
        BigDecimal id=(BigDecimal) Q.uniqueResult();
        if(id!=null){
            e=getById(id);        }  
        return e;  
}

    public List<Employe> EmployeByCrit(String paramSearch, String optionSearch) {
        List<Employe>list=new ArrayList<Employe>();
         Criteria criteria = s.createCriteria(Employe.class); 
     switch (optionSearch) {
             case "nom":
                 criteria.add(Restrictions.eq("nomEmploye",paramSearch));
                 break;
             case "prenom":
                criteria.add(Restrictions.eq("prenomEmploye",paramSearch));
                 break;
             case "email":
                 criteria.add(Restrictions.eq("emailEmploye",paramSearch));
                 break;
             case "login":
                criteria.add(Restrictions.eq("loginEmploye",paramSearch));
                 break;
             default:
                 break;
         }
     list=criteria.list();
     return list;
         
    }
    
}

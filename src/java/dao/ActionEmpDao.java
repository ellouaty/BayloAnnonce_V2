 
package dao; 
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale; 
import model.ActionEmp; 
import model.ActionEmpId;
import model.Annonce;
import model.Employe;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
 
public class ActionEmpDao implements Serializable{ 
    public Session  s = NewHibernateUtil1.getSessionFactory().openSession();
 
public boolean ajouterActionEmp(ActionEmp e){  
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
  
public boolean modifierActionEmp(ActionEmp e){ 
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
   
public boolean supprimerActionEmp(ActionEmp e){  
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
 
public ActionEmp getById(BigDecimal id){  
     Transaction tra=s.beginTransaction();
    ActionEmp e=(ActionEmp)s.get(ActionEmp.class,id);  
     try{  tra.commit();}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
    return e;  
}  
 
public List<ActionEmp> getActionEmps(){  
      List<ActionEmp> list=new ArrayList<ActionEmp>();  
       Criteria criteria = s.createCriteria(ActionEmp.class);
            list=criteria.list();
    return list;
  
}  

    public ActionEmp getActionEmpRejetByAnnonce(Annonce an) {
         Criteria criteria = s.createCriteria(ActionEmp.class); 
         criteria.add(Restrictions.eq("annonce", an));
         criteria.add(Restrictions.eq("libelleActionEmp","refuse"));
        List<ActionEmp> act= criteria.list();
        return act.get(act.size()-1);
    }
    public List <ActionEmp>  ActionEmpsByAnnonceRejAcc (Annonce an) {
        String[] t={"refuse","accepte"};
     Criteria criteria = s.createCriteria(ActionEmp.class); 
         criteria.add(Restrictions.eq("annonce", an));
         criteria.add(Restrictions.in("libelleActionEmp",t));
        List<ActionEmp> act= criteria.list();
        return  act;
}
   
  public  ActionEmp ActionEmpsByAnnonce(Annonce a) {
           ActionEmp ac=null;
           Criteria criteria = s.createCriteria(ActionEmp.class);
        criteria.add(Restrictions.eq("annonce",a));
        List<ActionEmp> list=criteria.list();
         if(!list.isEmpty()){
             ac=list.get(list.size()-1);
         }
         return ac;
    }

    public String nomView(Annonce annonceToModerate,String etat) {
        ActionEmp act=new ActionEmp();
        String r="";
     Criteria criteria = s.createCriteria(ActionEmp.class); 
         criteria.add(Restrictions.eq("annonce", annonceToModerate));
         if(etat.charAt(0)=='L'){
               criteria.add(Restrictions.eq("libelleActionEmp","Locked"));
            }else if(etat.charAt(0)=='N'){
               criteria.add(Restrictions.eq("libelleActionEmp","No action"));
            }
        List<ActionEmp> actList= criteria.list();
        if(!actList.isEmpty()){
        act=actList.get(actList.size()-1);
          r= act.getEmploye().getNomEmploye()+" "+act.getEmploye().getPrenomEmploye();}
      
    return r;
    
    }

    public List<ActionEmp> ActionxEmpSurAnnonce(BigDecimal id) {
      
   AnnonceDao ad=new AnnonceDao();
   Annonce a=ad.getByIdAllEtat(id);
   Criteria criteria = s.createCriteria(ActionEmp.class); 
   criteria.add(Restrictions.eq("annonce", a));
   List<ActionEmp> list=criteria.list();
  
  return list;
    
     }

    public int ActionEmpsByEmployeBetwenF_T(Employe employe, String dateStat1, String dateStat2, String libelle) throws ParseException {
       Criteria criteria = s.createCriteria(ActionEmp.class);
         criteria.add(Restrictions.eq("employe", employe));
          criteria.add(Restrictions.eq("libelleActionEmp", libelle));
          List<ActionEmp> l_ac=criteria.list(); 
            List<ActionEmp> lfin=new ArrayList<>();
              DateTimeFormatter f=DateTimeFormatter.ofPattern("HH:mm:ss");
          LocalTime heur_st1=LocalTime.parse(dateStat1.substring(11), f);
         LocalTime heur_st2=LocalTime.parse(dateStat2.substring(11), f);
           for (ActionEmp actionEmp : l_ac) {
              ActionEmpId acId=actionEmp.getId();
              LocalTime heur_ACT=LocalTime.parse(acId.getHeureActionEmp(),f);
               DateFormat DF=new SimpleDateFormat("dd-MM-yyyy");
                Date d1=DF.parse(dateStat1.substring(0,10));
               Date d2=DF.parse(dateStat2.substring(0,10));  
        Date daction=DF.parse(acId.getDateActionEmp()); 
        if(daction.after(d2) &&  daction.before(d1)||daction.equals(d2)
                ||daction.equals(d1)){
             if((dateStat1.substring(0,10).equals(acId.getDateActionEmp())&& (heur_ACT.isAfter(heur_st1)))||
                  (dateStat2.substring(0,10).equals(acId.getDateActionEmp())&& (heur_ACT.isBefore(heur_st2)))||((!dateStat1.substring(0,10).equals(acId.getDateActionEmp()))
                  &&(!dateStat2.substring(0,10).equals(acId.getDateActionEmp())))){
                 
              lfin.add(actionEmp);}
        } 
        } 
              
            
         return lfin.size();
    }
    
    public static void main(String[] args) throws ParseException {
        EmployeDao empdao=new EmployeDao();
        Employe e=empdao.getById(new BigDecimal(2));
        ActionEmpDao ad=new ActionEmpDao();
        ad.ActionEmpsByEmployeBetwenF_T(e, "08-02-2020 10:00:00","08-02-2020 11:00:00","accepte");
        
    }
}

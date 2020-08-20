/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 


package dao;
  
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import java.util.Locale; 
import model.ActionEmp;
import model.Annonce;
import model.MaisonVilla;
import model.Moto;
import model.UtilisateurConnecte;
import model.VehiculeProfessionnel;
import model.Voiture; 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
 
/**
 *
 * @author ibm
 */
public class AnnonceDao implements Serializable{   
   static Session  s = NewHibernateUtil1.getSessionFactory().openSession();
 
public BigDecimal ajouterAnnonce(Annonce e){  
     
     Transaction tra=s.beginTransaction();
	         BigDecimal id= (BigDecimal) s.save(e);  
                try{  tra.commit();
                 }
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
                 
         return id;           
}  
  
public boolean  modifierAnnonce(Annonce e){ 
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
   
public boolean supprimerAnnonce(Annonce e){ 
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

public void modifierNbVueAnnonce(BigDecimal id,BigDecimal nb){
    Annonce a=getByIdPub(id);
    a.setNbVueAnnonce(nb);
    Transaction tra=s.beginTransaction();
	          s.update(a);
                try{  tra.commit();}
                catch(Exception ex){
                    tra.rollback();
                    throw ex;
                }
    
}
 
public Annonce getByIdPub(BigDecimal id){   
    Criteria criteria = s.createCriteria(Annonce.class); 
    criteria.add(Restrictions.eq("idAnnonce", id));
    criteria.add(Restrictions.eq("etatAnnonce","publie"));
  Annonce e=(Annonce) criteria.uniqueResult();
    return e;  
}  

public Annonce getByIdAllEtat(BigDecimal id){   
    Criteria criteria = s.createCriteria(Annonce.class); 
    criteria.add(Restrictions.eq("idAnnonce", id));
  Annonce e=(Annonce) criteria.uniqueResult();
    return e;  
}  
 
public List<Annonce> getAnnoncesByEtat(String etat){  
    
    List<Annonce> list=new ArrayList<Annonce>(); 
   String rs = "from Annonce where etatAnnonce=:p";
	
		  Query q = s.createQuery(rs);
	q.setParameter("p",etat);
    list=q.list();  
    return list;  
  
}  

public List<Annonce> getAnnoncesByCritaires(String titreSearch,String prixminsearch,String  prixmaxsearch,
      String superficieminsearch, String superficiemaxsearch, String anneModelminsearch,String anneModelmaxsearch,
      String  klmtMinSearch,String  klmtMaxSearch, String typeCarburantSearch,String typeVehuculeSearch,String    categorieSearch,
        String villeSearch,String marqueSearch  ,
     String modeleSearch ,String typeAnnonceSearch,String puissanceFiscaleSearch){ 
     
                     
                     
     Criteria criteria = s.createCriteria(Annonce.class);
     boolean c=true;
     criteria.add(Restrictions.eq("etatAnnonce","publie"));
  
      if (!"".equals(titreSearch.trim())) {
			criteria.add(Restrictions.ilike("titreAnnonce",titreSearch.trim(),MatchMode.EXACT));
		}
   
    
   
  
   if (!"".equals(villeSearch)) {
			criteria.add(Restrictions.eq("villeAnnonce",villeSearch));
		}  
  
      if (!"".equals(typeAnnonceSearch)) {
			criteria.add(Restrictions.eq("typeAnnonce",typeAnnonceSearch));
		} 
   
   
    
     if (!"".equals(categorieSearch)) {
        
         if ("INFORMATIQUE ET MULTIMEDIA".equals(categorieSearch)||"VEHICULES".equals(categorieSearch)||"IMMOBILIER".equals(categorieSearch) ||"POUR LA MAISON ET JARDIN".equals(categorieSearch) || "ENTREPRISES".equals(categorieSearch)|| "EMPLOI ET SERVICES".equals(categorieSearch) ||"LOISIRS ET DIVERTISSEMENT".equals(categorieSearch) ||"HABILLEMENT ET BIEN ETRE".equals(categorieSearch)) {
         
             criteria.add(Restrictions.eq("categorie",categorieSearch));
         
         
         }else{
             
          criteria.add(Restrictions.eq("souscategorie",categorieSearch));
         } 
     }
      
    if (!"".equals(prixminsearch)) { 
        criteria.add(Restrictions.ge("prixAnnonce",new BigDecimal(prixminsearch)));  }
   if (!"".equals(prixmaxsearch)) {
    criteria.add(Restrictions.le("prixAnnonce",new BigDecimal(prixmaxsearch.trim())));}
  
   
     
    if ("Appartements".equals(categorieSearch)||"Maisons et Villas".equals(categorieSearch)||"Bureaux et Plateaux".equals(categorieSearch)||"Magasins Commerces et Locaux industriels".equals(categorieSearch)||"Magasins Commerces et Locaux industriels".equals(categorieSearch)
      ||"Locations de vacances".equals(categorieSearch)||"Colocations".equals(categorieSearch)){
       
        if (!"".equals(superficieminsearch)){ 
      
                 Criteria maisonCriteria = s.createCriteria(MaisonVilla.class,"mv"); 
                 maisonCriteria.add(Restrictions.ge("superficieMaisonVilla",new BigDecimal(superficieminsearch)));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  maisonCriteria.setProjection(properties);
                   List<Annonce> l= maisonCriteria.list();
                   if(!l.isEmpty()){
                   List<BigDecimal>ids=new ArrayList<>();
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));
                   }else{
                       c=false;
                   }
              
        
        
        }  
            if (!"".equals(superficiemaxsearch)){
              Criteria maisonCriteria = s.createCriteria(MaisonVilla.class,"mv"); 
                 maisonCriteria.add(Restrictions.le("superficieMaisonVilla",new BigDecimal(superficiemaxsearch)));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  maisonCriteria.setProjection(properties);
                   List<Annonce> l= maisonCriteria.list();
                   if(!l.isEmpty()){
                   List<BigDecimal>ids=new ArrayList<>();
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));

                   }else{
                       c=false;
                   }
         } 
     } 
      
       
    if("Motos".equals(categorieSearch)){
          // String [] tabKm= {"0","0;4999","5000;9999","10000;14999","15000;19999","20000;24999","25000;29999","30000;34999","35000;39999","40000;44999","45000;49999","50000;54999","55000;59999", "60000;64999","65000;69999","70000;74999","75000;79999","80000;84999","85000;89999","90000;94999","95000;99999","100000;109999", "110000;119999","120000;129999","130000;139999","140000;149999","150000;159999","160000;169999","170000;179999","180000;189999","190000;199999","200000;249999","250000;299999","300000;349999","350000;399999","400000;449999","450000;499999","Plus de 500000" };
   
   if (!"".equals(klmtMinSearch)){
       
              Criteria motoCriteria = s.createCriteria(Moto.class,"mt"); 
                 motoCriteria.add(Restrictions.ge("kilometrageMoto",klmtMinSearch));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  motoCriteria.setProjection(properties);
                   List<Annonce> l= motoCriteria.list();
                   if(!l.isEmpty()){
                       
                 
                   List<BigDecimal>ids=new ArrayList<>();
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));  }
                   else{
                      c=false;
                   }

         
         }
  if (!"".equals(klmtMaxSearch)){ 
              Criteria motoCriteria = s.createCriteria(Moto.class,"mt"); 
                 motoCriteria.add(Restrictions.le("kilometrageMoto",klmtMaxSearch));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  motoCriteria.setProjection(properties);
                   List<Annonce> l= motoCriteria.list();
                     if(!l.isEmpty()){
                   List<BigDecimal>ids=new ArrayList<>();
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));}
  else{
        c=false;}

         
         } 
  if (!"".equals(anneModelmaxsearch)){
              Criteria motoCriteria = s.createCriteria(Moto.class,"mt"); 
                 motoCriteria.add(Restrictions.le("anneModelMoto",new BigDecimal(anneModelmaxsearch)));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  motoCriteria.setProjection(properties);
                   List<Annonce> l= motoCriteria.list();
                   List<BigDecimal>ids=new ArrayList<>();
                   if(l.isEmpty()){
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                  
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));
                   }else{
                       c=false;
                   }
         
         }
  if (!"".equals(anneModelminsearch)){
              Criteria motoCriteria = s.createCriteria(Moto.class,"mt"); 
                 motoCriteria.add(Restrictions.ge("anneModelMoto",new BigDecimal(anneModelminsearch)));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  motoCriteria.setProjection(properties);
                   List<Annonce> l= motoCriteria.list();
                   List<BigDecimal>ids=new ArrayList<>();
                   if(!l.isEmpty()){
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                 
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));

                   }else{
                       c=false;
                   }
         } 
    }
    
 
         
      if ("Voitures".equals(categorieSearch)){  
         
         
         if (!"".equals(modeleSearch)) {
             	         
                                Criteria voitureCriteria = s.createCriteria(Voiture.class,"vtmod"); 
                 voitureCriteria.add(Restrictions.eq("modeleVoiture",modeleSearch.trim()));
                 ProjectionList prop= Projections.projectionList();
                 prop.add(Projections.property("annonce")); 

                  voitureCriteria.setProjection(prop);
                   List<Annonce> l= voitureCriteria.list();
                   if(!l.isEmpty()){
                   List<BigDecimal>idv=new ArrayList<>();
            for (Annonce o : l) {
                 idv.add(o.getIdAnnonce());
                  
            } 
                  criteria.add(Restrictions.in("idAnnonce",idv));}
                   else{
                      c=false; 
                   }
                   
		} else if (!"".equals(marqueSearch)) {
			      
                                 Criteria voitureCriteria = s.createCriteria(Voiture.class,"v"); 
                 voitureCriteria.add(Restrictions.eq("marqueVoiture",marqueSearch));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  voitureCriteria.setProjection(properties);
                   List<Annonce> l= voitureCriteria.list();
                   if(!l.isEmpty()){
                   List<BigDecimal>idv=new ArrayList<>();
                   for (Annonce o : l) {
                 idv.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",idv));}
          else{
                      c=false; 
                   }
		}
          
          
                 if (!"".equals(typeCarburantSearch)) {
			        
                                 Criteria voitureCriteria = s.createCriteria(Voiture.class,"v"); 
                 voitureCriteria.add(Restrictions.eq("typeCarburantVoiture",typeCarburantSearch));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  voitureCriteria.setProjection(properties);
                   List<Annonce> l= voitureCriteria.list();
                   if(!l.isEmpty()){
                   List<BigDecimal>idv=new ArrayList<>();
                   for (Annonce o : l) {
                 idv.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",idv));
		}
                 else{
                     c=false;
                 }}
      
          
         
          if (!"".equals(klmtMinSearch)){
              Criteria voitureCriteria = s.createCriteria(Voiture.class,"vt"); 
                 voitureCriteria.add(Restrictions.ge("kilometrage",new BigDecimal(klmtMinSearch.replaceAll("\\s",""))));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  voitureCriteria.setProjection(properties);
                   List<Annonce> l= voitureCriteria.list();
                   List<BigDecimal>ids=new ArrayList<>();
                   if(!l.isEmpty()){
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));}
                   else{
              c =false;
          }

         
         }    
  if (!"".equals(klmtMaxSearch)){ 
      
              Criteria voitureCriteria = s.createCriteria(Voiture.class,"mt"); 
                 voitureCriteria.add(Restrictions.le("kilometrage",new BigDecimal(klmtMaxSearch.replaceAll("\\s",""))));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  voitureCriteria.setProjection(properties);
                   List<Annonce> l= voitureCriteria.list();
                    if(!l.isEmpty()){
                   List<BigDecimal>ids=new ArrayList<>();
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));}
                   else{
              c =false;
          }

         
         }  
  if (!"".equals(anneModelmaxsearch)){
              Criteria vtCriteria = s.createCriteria(Voiture.class,"mt"); 
                 vtCriteria.add(Restrictions.le("anneeModeleVoiture",new BigDecimal(anneModelmaxsearch)));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  vtCriteria.setProjection(properties);
                   List<Annonce> l= vtCriteria.list();
                    if(!l.isEmpty()){
                   List<BigDecimal>ids=new ArrayList<>();
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                  
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));}
                   else{
              c=false;
          }

         
         }

  if (!"".equals(anneModelminsearch)){
              Criteria vtCriteria = s.createCriteria(Voiture.class,"mt"); 
                 vtCriteria.add(Restrictions.ge("anneeModeleVoiture",new BigDecimal(anneModelminsearch)));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  vtCriteria.setProjection(properties);
                   List<Annonce> l= vtCriteria.list();
   if(!l.isEmpty()){
                   List<BigDecimal>ids=new ArrayList<>();
                   for (Annonce o : l) {
                 ids.add(o.getIdAnnonce());
            }
                 
                   
                  criteria.add(Restrictions.in("idAnnonce",ids));}
                   else{
              c=false;
          }

         
         }  
         
         
       
    if (!"".equals(puissanceFiscaleSearch)) {
			       
                                 Criteria voitureCriteria = s.createCriteria(Voiture.class,"v"); 
                 voitureCriteria.add(Restrictions.eq("puissanceFiscaleVoiture",puissanceFiscaleSearch));
                 ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("annonce")); 

                  voitureCriteria.setProjection(properties);
                   List<Annonce> l= voitureCriteria.list();
                    if(!l.isEmpty()){
                   List<BigDecimal>idv=new ArrayList<>();
                   for (Annonce o : l) {
                 idv.add(o.getIdAnnonce());
            }
                   
              
                    criteria.add(Restrictions.in("idAnnonce",idv)); }
                    else{
                        c=false;
                    }
                    
                    
                    
		}  
     }
        
      if("Véhicules Professionnels".equals(categorieSearch)){
            if (!"".equals(typeVehuculeSearch)) {
                
                 Criteria vehiCriteria = s.createCriteria(VehiculeProfessionnel.class,"vp"); 
                 vehiCriteria.add(Restrictions.eq("sousCatVehiculeProf",typeVehuculeSearch));
                  ProjectionList properties = Projections.projectionList();
                 properties.add(Projections.property("voiture")); 

                  vehiCriteria.setProjection(properties);
                   List<Voiture> v= vehiCriteria.list();
                   
                    if(!v.isEmpty()){
                   List<BigDecimal>idvp=new ArrayList<>();
                   for (Voiture o : v) {
                 idvp.add(o.getIdVoiture());
            }
                   
                   
           Criteria voitureCriteria = s.createCriteria(Voiture.class,"mt"); 
                 voitureCriteria.add(Restrictions.in("idVoiture",idvp));
                 ProjectionList propertiesA = Projections.projectionList();
                 propertiesA.add(Projections.property("annonce")); 

                  voitureCriteria.setProjection(propertiesA);
                   List<Annonce> l= voitureCriteria.list();
                   List<BigDecimal>idF=new ArrayList<>();
                   for (Annonce o : l) {
                 idF.add(o.getIdAnnonce());
            }
                   
                   
                  criteria.add(Restrictions.in("idAnnonce",idF));}
    else{c=false;} 
      } 
      } 
       List<Annonce> list=new ArrayList<Annonce>();
  
    if(c==false){
         list=new ArrayList<Annonce>();
    }
    else{
  list=criteria.list();}

    return list;  
  
}  


    public List<Annonce> AnnoncesByProp(UtilisateurConnecte u,String etat) {
        List<Annonce> list=new ArrayList<Annonce>(); 
        
        if(etat.equals("en_cours")){
            String[]t={"en_cours","LockedNew","NoActionNew","LockedGray","NoActionGray","LockedNew","NoActionNew","modif","LockedModif","NoActionModif","abus","NoActionAbus"};
           Criteria c=s.createCriteria(Annonce.class);
            c.add(Restrictions.eq("utilisateurConnecte",u));
           c.add(Restrictions.in("etatAnnonce",t));
            list=c.list();
            
    }else{
    String rs = "from Annonce where utilisateurConnecte=:u and etatAnnonce=:etat";
	Query  q = s.createQuery(rs);
	q.setParameter("u",u);
        q.setParameter("etat",etat);
        list=q.list();}
     
    return list;  
  }


    
    
    
    
     public List<Annonce> annoncesByQueu(String q,String c,List<UtilisateurConnecte> listUtil){
           List<Annonce> list=new ArrayList<>();
         List<Annonce> listFinal=new ArrayList<>();
         Criteria criteria = s.createCriteria(Annonce.class);
         String[]tnew={"en_cours","LockedNew","NoActionNew"};
          String[]tnewSanL={"LockedNew","NoActionNew"};
           String[]tgra={"en_cours","LockedGray","NoActionGray"}; 
            String[]tmod={"modif","LockedModif","NoActionModif"};
            String[]tabus={"abus","NoActionAbus"}; 
       switch (q) {
           case "new":
              if(!listUtil.isEmpty()){  
               criteria.add(Restrictions.in("etatAnnonce",tnew));
                 criteria.add(Restrictions.in("utilisateurConnecte",listUtil));
              list=criteria.list();  }
              else{
                  criteria.add(Restrictions.in("etatAnnonce",tnewSanL));
              list=criteria.list();  
              }
               break;
           case "gray":
               if(!listUtil.isEmpty()){ 
                criteria.add(Restrictions.in("etatAnnonce",tgra));
                 criteria.add((Restrictions.not(Restrictions.in("utilisateurConnecte",listUtil))));
                  
                }   list=criteria.list();  
               
               break;
           case "mod":
             criteria.add(Restrictions.in("etatAnnonce",tmod));
             list=criteria.list();
               break;
               case "abus":
               criteria.add(Restrictions.in("etatAnnonce",tabus));
                list=criteria.list();
               break;
           default:
               break;
       }
         
           
       switch (q) {
           case "dup":
              // criteria.add(Restrictions.eq("etatAnnonce","en_cours"));
              //traitement  special
               list=new ArrayList<>();
               break;
           case "wCatPric":
               //criteria.add(Restrictions.eq("etatAnnonce","en_cours"));
               //traitement  special
               list=new ArrayList<>();
               break;
           case "dupPic":
              // criteria.add(Restrictions.eq("etatAnnonce","en_cours"));
               //traitement  special
               list=new ArrayList<>();
               break;
           default:  
               break;
       } 
        
         if(c.equals("nv")){ 
          ActionEmpDao aedao=new ActionEmpDao();
                   
               for (Annonce a : list) { 
                  
            ActionEmp ac=aedao.ActionEmpsByAnnonce(a);  
        if(ac==null){
            listFinal.add(a);
            
        }else{  
            
           /* if(ac.getLibelleActionEmp().equals("No action")){
                      listFinal.add(a);
                     }*/
            
            // else if(ac.getLibelleActionEmp().equals("Locked")){
                         
            // else{ je modere une seul annonce par page pour eviter de revoir meme annoce
                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                      String d=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                   String h=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
                     LocalDateTime dateHeureNow=LocalDateTime.parse(d+" "+h,formatter);
                LocalDateTime dateHeureAction=LocalDateTime.parse(ac.getId().getDateActionEmp()+" "+ac.getId().getHeureActionEmp(),formatter);
                
                if(dateHeureNow.isAfter(dateHeureAction.plusMinutes(3))){
                     listFinal.add(a);
                }
                 }
               
            
        //}
               } }   else{
          listFinal=list;} 
         
        return listFinal;
    }

    public List<Annonce> AnnoncesByIp(String ip, String etat) {
      List<Annonce> l=new ArrayList<>();
       Criteria criteriaAN = s.createCriteria(Annonce.class);
        if(etat.equals("en_cours")){
            String[]t={"en_cours","LockedNew","NoActionNew","LockedGray","NoActionGray","LockedNew","NoActionNew","modif","LockedModif","NoActionModif","abus"};
          criteriaAN.add(Restrictions.in("etatAnnonce",t));}
        else{
           criteriaAN.add(Restrictions.eq("etatAnnonce",etat)); }
       criteriaAN.add(Restrictions.eq("ipAnnonce",ip)); 
       l=criteriaAN.list();
    return l;
   }
    public List<Annonce> AnnoncesByEmail(String email, String etat) {
        Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
           criteria.add(Restrictions.eq("emailUtilConnecte",email)); 
          List<UtilisateurConnecte>lu=criteria.list();
           List<Annonce> LfIN=new ArrayList<>();
           for (UtilisateurConnecte utilisateurConnecte : lu) {
             List<Annonce> l=new ArrayList<>();
              Criteria criteriaAN = s.createCriteria(Annonce.class);
            if(etat.equals("en_cours")){
            String[]t={"en_cours","LockedNew","NoActionNew","LockedGray","NoActionGray","LockedNew","NoActionNew","modif","LockedModif","NoActionModif","abus"};
          criteriaAN.add(Restrictions.in("etatAnnonce",t));}
        else{
           criteriaAN.add(Restrictions.eq("etatAnnonce",etat)); }
             criteriaAN.add(Restrictions.eq("utilisateurConnecte",utilisateurConnecte));
             l=criteriaAN.list();
             LfIN.addAll(l);
        }
          
           
           
           return LfIN;}
    public static void main(String[] args) throws ParseException {
        AnnonceDao d=new AnnonceDao();
        List<Annonce>l=d.getAnnoncesByEtat("publie");
        System.out.println(l.size());
    }
    public List<Annonce> AnnoncesByTel(String tel, String etat) {
           Criteria criteria = s.createCriteria(UtilisateurConnecte.class);
           criteria.add(Restrictions.eq("telUtilConnecte",tel)); 
          List<UtilisateurConnecte>lu=criteria.list();
           List<Annonce> LfIN=new ArrayList<>();
           for (UtilisateurConnecte utilisateurConnecte : lu) {
             List<Annonce> l=new ArrayList<>();
              Criteria criteriaAN = s.createCriteria(Annonce.class);
         if(etat.equals("en_cours")){
            String[]t={"en_cours","LockedNew","NoActionNew","LockedGray","NoActionGray","LockedNew","NoActionNew","modif","LockedModif","NoActionModif","abus"};
          criteriaAN.add(Restrictions.in("etatAnnonce",t));}
        else{
           criteriaAN.add(Restrictions.eq("etatAnnonce",etat)); }
             criteriaAN.add(Restrictions.eq("utilisateurConnecte",utilisateurConnecte));
             l=criteriaAN.list();
             LfIN.addAll(l);
        }
          
           
           
           return LfIN;}
    public List<Annonce> AnnoncesByRef(String ref, String etat) {
         List<Annonce> l=new ArrayList<>();
       Criteria criteriaAN = s.createCriteria(Annonce.class);
       if(etat.equals("en_cours")){
            String[]t={"en_cours","LockedNew","NoActionNew","LockedGray","NoActionGray","LockedNew","NoActionNew","modif","LockedModif","NoActionModif","abus","NoActionAbus"};
          criteriaAN.add(Restrictions.in("etatAnnonce",t));}
        else{
           criteriaAN.add(Restrictions.eq("etatAnnonce",etat)); }
       criteriaAN.add(Restrictions.eq("idAnnonce",new BigDecimal(ref))); 
       l=criteriaAN.list();
    return l;}

    public List<String> getIpsByUser(UtilisateurConnecte aThis) {
        List<Annonce> l=new ArrayList<>();
       Criteria criteria = s.createCriteria(Annonce.class);
       criteria.add(Restrictions.eq("utilisateurConnecte",aThis));
       l=criteria.list();
       List<String> ips=new ArrayList<String>();
        for (Annonce annonce : l) {
            if( annonce.getIpAnnonce()!=null){
            ips.add(annonce.getIpAnnonce());}
        }
    return ips;
}

    public String calculerAverageTime(String dateAver1, String dateAver2) throws ParseException {
       DateFormat DF=new SimpleDateFormat("dd-MM-yyyy");
        Date d1=DF.parse(dateAver1.substring(0,10));
        Date d2=DF.parse(dateAver2.substring(0,10));  
          String rs = "from Annonce where  (etatAnnonce=:p or etatAnnonce =:r ) and to_date(dateDepotAnnonce) BETWEEN :d1 and :d2";
	 Query q = s.createQuery(rs);
	 q.setParameter("d1",d1);
         q.setParameter("d2",d2);
         q.setParameter("p","publie");
         q.setParameter("r","rejete");
         List<Annonce> list=q.list(); 
        List<Annonce> listH=new ArrayList<>();
         DateTimeFormatter f=DateTimeFormatter.ofPattern("HH:mm:ss");
         LocalTime heur_av1=LocalTime.parse(dateAver1.substring(11), f);
         LocalTime heur_av2=LocalTime.parse(dateAver2.substring(11), f);
          for (Annonce annonce : list) {
          LocalTime heur_depot=LocalTime.parse(annonce.getHeureDepotAnnonce(), f);
         
          
          
          
          
          
          
          if((dateAver1.substring(0,10).equals(annonce.getDateDepotAnnonce())&& (heur_depot.isAfter(heur_av1)))||
                  (dateAver2.substring(0,10).equals(annonce.getDateDepotAnnonce())&& (heur_depot.isBefore(heur_av2)))||((!dateAver1.substring(0,10).equals(annonce.getDateDepotAnnonce()))
                  &&(!dateAver2.substring(0,10).equals(annonce.getDateDepotAnnonce())))){
                 
              listH.add(annonce);}
         }
     
       int sommeAnn=listH.size(); 
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
           ActionEmpDao adao=new ActionEmpDao();
            long tmod=0;
        for (Annonce annonce :listH) {
              LocalDateTime tDepot=LocalDateTime.parse(annonce.getDateDepotAnnonce()+" "+annonce.getHeureDepotAnnonce(),formatter);
              List<ActionEmp> l_act=adao.ActionEmpsByAnnonceRejAcc(annonce); 
              LocalDateTime tAction=LocalDateTime.parse(l_act.get(0).getId().getDateActionEmp()+" "+l_act.get(0).getId().getHeureActionEmp(),formatter);
              
             Duration dif=Duration.between(tDepot, tAction);
               long l=dif.toMillis();
              
              tmod+=l;
        }
        
        
        long avMlSeconds=tmod/sommeAnn;
          
         long ll=avMlSeconds/1000;
               int hh=(int)ll/3600;
              int modd=(int)  (ll%3600); 
               int mm=(int) modd/60 ;
               int ss=modd%60;   
              String sh=String.valueOf(hh);
              String sm=String.valueOf(mm);
              String sss=String.valueOf(ss);
              if(sh.length()==1){
                  sh="0"+sh;
              }
               if(sm.length()==1){
                  sm="0"+sm;
              }
                if(sss.length()==1){
                  sss="0"+sss;
              }
              
            
      
        return  sh+":"+sm+":"+sss;
    }
}
    

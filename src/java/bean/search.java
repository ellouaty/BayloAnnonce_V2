 
package bean;
 
import dao.AnnonceDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct; 
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named; 
import model.Annonce;

/**
 *
 * @author ibm
 */
@Named 
@SessionScoped

public class search implements Serializable{
    //SEARCH attributs
    private String titreSearch;
    private String prixminsearch;
     private String  prixmaxsearch;
      private String superficieminsearch;
    private String superficiemaxsearch;
 private String anneModelminsearch;
 private String anneModelmaxsearch;
 private String  klmtMinSearch;
 private String  klmtMaxSearch;
  private String typeCarburantSearch;
    private String      typeVehuculeSearch;
      private String    categorieSearch;
        private String          villeSearch;
         private String marqueSearch  ;
     private String modeleSearch ;
     private String typeAnnonceSearch;
      private String puissanceFiscaleSearch;
      private List<ArrayList<Annonce>> page=new ArrayList<ArrayList<Annonce>>(); 
       private  List resultat=new ArrayList<>();
        
        //pour utiliser les listes deja initialiser et remplire les listes deroulantes 
          private TreeMap<String,String> modelesSearch ; 
          private  annonceBean an;
           
     public  search(){
         
     }
  @PostConstruct
         public void init()   {
      
            an=new annonceBean();
            an.remplirData();
           viderChampsSearch();
           
          }
       public void  viderChampsSearch(){
                       typeAnnonceSearch="Offre";
            titreSearch="";
prixminsearch="";
prixmaxsearch="";
superficieminsearch="";
superficiemaxsearch="";
anneModelminsearch="";
anneModelmaxsearch="";
klmtMinSearch="";
klmtMaxSearch="";
       typeCarburantSearch="";
	   typeVehuculeSearch="";
	   categorieSearch="";
	   villeSearch="";
	   marqueSearch="";
	  modeleSearch ="";
	  puissanceFiscaleSearch="";
           
          
       }
         //onload search.xhtml
          public void onloadList() throws IOException{
            chercher();
}
          
         //getters setters
            public List<ArrayList<Annonce>> getPage() {
        return page;
    }
    public void setPage(List<ArrayList<Annonce>> page) {
        this.page = page;
    }
    public String getPuissanceFiscaleSearch() {
        return puissanceFiscaleSearch;
    }
    public void setPuissanceFiscaleSearch(String puissanceFiscaleSearch) {
        this.puissanceFiscaleSearch = puissanceFiscaleSearch;
    }

    public String getTypeAnnonceSearch() {
        return typeAnnonceSearch;
    }

    public void setTypeAnnonceSearch(String typeAnnonceSearch) {
        this.typeAnnonceSearch = typeAnnonceSearch;
    }

    public annonceBean getAn() {
        return an;
    }

    public void setAn(annonceBean an) {
        this.an = an;
    }

    public String getMarqueSearch() {
        return marqueSearch;
    }

    public void setMarqueSearch(String marqueSearch) {
        this.marqueSearch = marqueSearch;
    }

    public String getModeleSearch() {
        return modeleSearch;
    }

    public void setModeleSearch(String modeleSearch) {
        this.modeleSearch = modeleSearch;
    }

    public TreeMap<String, String> getModelesSearch() {
        return modelesSearch;
    }

    public void setModelesSearch(TreeMap<String, String> modelesSearch) {
        this.modelesSearch = modelesSearch;
    }

    public String getTypeCarburantSearch() {
        return typeCarburantSearch;
    }

    public void setTypeCarburantSearch(String typeCarburantSearch) {
        this.typeCarburantSearch = typeCarburantSearch;
    }

    public String getTypeVehuculeSearch() {
        return typeVehuculeSearch;
    }

    public void setTypeVehuculeSearch(String typeVehuculeSearch) {
        this.typeVehuculeSearch = typeVehuculeSearch;
    }

    public String getCategorieSearch() {
        return categorieSearch;
    }

    public void setCategorieSearch(String categorieSearch) {
        this.categorieSearch = categorieSearch;
    }

    public String getVilleSearch() {
        return villeSearch;
    }

    public void setVilleSearch(String villeSearch) {
        this.villeSearch = villeSearch;
    }

    public String getTitreSearch() {
        return titreSearch;
    }

    public void setTitreSearch(String titreSearch) {
        this.titreSearch = titreSearch;
    }

    public String getPrixminsearch() {
        return prixminsearch;
    }

    public void setPrixminsearch(String prixminsearch) {
        this.prixminsearch = prixminsearch;
    }

    public String getPrixmaxsearch() {
        return prixmaxsearch;
    }

    public void setPrixmaxsearch(String prixmaxsearch) {
        this.prixmaxsearch = prixmaxsearch;
    }

    public String getSuperficieminsearch() {
        return superficieminsearch;
    }

    public void setSuperficieminsearch(String superficieminsearch) {
        this.superficieminsearch = superficieminsearch;
    }

    public String getSuperficiemaxsearch() {
        return superficiemaxsearch;
    }

    public void setSuperficiemaxsearch(String superficiemaxsearch) {
        this.superficiemaxsearch = superficiemaxsearch;
    }

    public String getAnneModelminsearch() {
        return anneModelminsearch;
    }

    public void setAnneModelminsearch(String anneModelminsearch) {
        this.anneModelminsearch = anneModelminsearch;
    }

    public String getAnneModelmaxsearch() {
        return anneModelmaxsearch;
    }

    public void setAnneModelmaxsearch(String anneModelmaxsearch) {
        this.anneModelmaxsearch = anneModelmaxsearch;
    }

    public String getKlmtMinSearch() {
        return klmtMinSearch;
    }

    public void setKlmtMinSearch(String klmtMinSearch) {
        this.klmtMinSearch = klmtMinSearch;
    }

    public String getKlmtMaxSearch() {
        return klmtMaxSearch;
    }

    public void setKlmtMaxSearch(String klmtMaxSearch) {
        this.klmtMaxSearch = klmtMaxSearch;
    }
 //methodes
   
    public void onMarqueChange() {
                   
        if(marqueSearch !=null && !marqueSearch.equals(""))
          
            modelesSearch = an.getData().get(marqueSearch);
        else
            modelesSearch = new TreeMap<String, String>();
         }
 public void lien() throws IOException{ 
          
         if (villeSearch == null) {
       villeSearch="Villes";
   }
   if (categorieSearch == null) {
       categorieSearch="Categories";
   }if ((titreSearch == null)||(titreSearch.equals(""))) {
       titreSearch="Annonces";
   }
   String[] t={villeSearch,categorieSearch,titreSearch};

     from="Resultat";
          for (String t1 : t) {
              
                  from =from+ "_" + t1;
              
          }
           String p="";
           String p1=""; 
           String pNames = "";
           String pValues="";
      
          if(!"".equals(typeAnnonceSearch)&&!"".equals("Offres d'emploi")&&!"".equals("Demandes d'emploi"))
                        {
                   pNames +="typeAnnonce_";
                   pValues+=typeAnnonceSearch+"_";
               }
          if(!"".equals(prixminsearch)){
                pNames+="prixmin_";
                   pValues+=prixminsearch+"_";
          }
          if(!"".equals(prixmaxsearch)){
                pNames+="prixmax_";
                   pValues+=prixmaxsearch+"_";
          }
          if(anneModelminsearch!=null){
                pNames+="anneModelmin_";
                   pValues+=anneModelminsearch+"_";
          }
    
             if(anneModelmaxsearch!=null){
                pNames+="anneModelmax_";
                   pValues+=anneModelmaxsearch+"_";
          }
                if(klmtMinSearch!=null){
                pNames+="klmtMin_";
                   pValues+=klmtMinSearch+"_";
          }
                if(klmtMaxSearch!=null){
                pNames+="klmtMax_";
                   pValues+=klmtMaxSearch+"_";
          }
                     if(typeCarburantSearch!=null){
                pNames+="typeCarburant_";
                   pValues+=typeCarburantSearch+"_";
          }
               if(marqueSearch!=null){
                pNames+="marque_";
                   pValues+=marqueSearch+"_";
          }
                 if(modeleSearch!=null){
                pNames+="modele_";
                   pValues+=modeleSearch+"_";
          }
                        if(puissanceFiscaleSearch!=null){
                pNames+="puissanceFiscale_";
                   pValues+=puissanceFiscaleSearch+"_";
          }
                                       if(typeVehuculeSearch!=null){
                pNames+="typeVehucule_";
                   pValues+=typeVehuculeSearch+"_";
          }
                                       
                if(superficieminsearch!=null){
                pNames+="superficiemin_";
                   pValues+=superficieminsearch+"_";
          }
                 if(superficiemaxsearch!=null){
                pNames+="superficiemax_";
                   pValues+=superficiemaxsearch+"_";
          }
                                       
         String[] tab_nom=pNames.split("_");
         String[] tab_val=pValues.split("_");
       
               for(int i=0;i<tab_val.length;i++){
        
      if(!"".equals(tab_val[i])&&(tab_val[i]!=null)){
      int l=i;
      p1="?"+tab_nom[l]+"="+tab_val[l];
      l=l+1;
      for(int j=l;j<tab_val.length;j++){
         if(!"".equals(tab_val[j].trim())&&(tab_val[j]!=null)){
         
               String parm="&"+tab_nom[j]+"="+tab_val[j];
               p+=parm;
     }}
      i=tab_val.length;
      }}
   
         FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/listeAnnonces/"+from+p1+p);  
       
  }
 private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
 
 
  public void chercher() throws IOException{
       Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       
        if(from!=null){
         resultat.clear();
        AnnonceDao anDao=new AnnonceDao();
          if((from.equals("INFORMATIQUE ET MULTIMEDIA"))||(from.equals("IMMOBILIER"))||(from.equals("VEHICULES"))||(from.equals("HABILLEMENT ET BIEN ETRE"))||(from.equals("LOISIRS ET DIVERTISSEMENT"))||(from.equals("EMPLOI ET SERVICES"))
                  ||(from.equals("ENTREPRISES"))||(from.equals("POUR LA MAISON ET JARDIN"))||(from.equals("Autres"))){
               categorieSearch=from;
               from="listC";
          }
      else if(from.equals("Casablanca")||from.equals("Agadir")||from.equals("Al Hoceima")||from.equals("Beni Mellal")||from.equals("El Jadida")||from.equals("Fes") ||
               from.equals("Errachidia")||from.equals("Kenitra")||from.equals("Khenifra")||from.equals("Khouribga")||from.equals("Larache")||
              from.equals("Marrakech")||from.equals("Meknes")||from.equals("Nador")||from.equals("Ouarzazate")||from.equals("Oujda")||
              from.equals("Rabat")||from.equals("Safi")||from.equals("Settat")||from.equals("Sale")||
               from.equals("Tanger")||from.equals("Taza")||from.equals("Tetouan")||from.equals("Autre ville")){
           villeSearch=from;
               from="listV";
      }
      else if(from.charAt(0)=='R'){
          String [] t=from.split("_");
                  from=t[0];
                 villeSearch=t[1];
                 categorieSearch=t[2];
                  titreSearch=t[3];
         }
          
          
          switch (from) {
            case "Resultat":
                   if (villeSearch.equals("Villes")) {
                     villeSearch="";
                            }
      if (categorieSearch.equals("Categories")) {
                   categorieSearch="";
                     }
      if (titreSearch.equals("Annonces")) {
                           titreSearch="";
                                 }
              String[] tab_params={titreSearch, params.get("prixmin"),params.get("prixmax"),
                    params.get("superficiemin"), params.get("superficiemax"), params.get("anneModelmin"), params.get("anneModelmax"),
                    params.get("klmtMin"), params.get("klmtMax"), params.get("typeCarburant"), params.get("typeVehucule"),
                    categorieSearch, villeSearch, params.get("marque"), params.get("modele"),
                    params.get("typeAnnonce"), params.get("puissanceFiscale")};
                for(int i=0;i<tab_params.length;i++){
                    if(tab_params[i]==null){
                        
                        tab_params[i]="";
                    }else{
                        tab_params[i]=tab_params[i].trim();
                    }
                }       resultat=anDao.getAnnoncesByCritaires(tab_params[0],tab_params[1],tab_params[2],tab_params[3],tab_params[4],tab_params[5], 
                        tab_params[6],tab_params[7],tab_params[8],
                        tab_params[9],tab_params[10],tab_params[11],tab_params[12],tab_params[13],tab_params[14],tab_params[15],tab_params[16]);
              
               break;
            case "listC":
                
                resultat=anDao.getAnnoncesByCritaires("","","", "","","","","","","","",categorieSearch,"","","","","");
              
                   break;
            case "listV": 
                resultat=anDao.getAnnoncesByCritaires("","","", "","","","","","","","","",villeSearch,"","","","");
                 break;
            case "All":
                 resultat=anDao.getAnnoncesByEtat("publie"); 
                break;
            default: 
                break;
        }
        Pagination( resultat, resultat.size()); 
         }
     }
   public void Pagination(List<Annonce> list,int size) throws IOException{
      
      page.clear();
       List<Annonce> p=new ArrayList<Annonce>();
        
         for(int j=1;j<=size;j++){
           if(j%12==0){
               p.add(list.get(j-1));
                 page.add((ArrayList<Annonce>) p);
                 p=new ArrayList<Annonce>();
             } 
           else{
            p.add(list.get(j-1));
           
           if(j==size){
               page.add((ArrayList<Annonce>) p); 
           }
           }
         }
     }
  
      
 
}

 
package bean;

import dao.*;
import email.SendMail;
 import model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;  
import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;  
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import session.Util;

/**
 *
 * @author ibm
 */

@Named
@SessionScoped

public class annonceBean implements Serializable{
     private TreeMap<String,TreeMap<String,String>> data = new TreeMap<String, TreeMap<String,String>>();
      private TreeMap<String,TreeMap<String,String>> data_categories = new TreeMap<String, TreeMap<String,String>>();
    //  @NotNull(message = "Marque?")
    private String marque  ;
     // @NotNull(message = "Modele?")
     private String modele ;
     // @NotNull(message = "Type?")
      private String typeVehiculePro ;
      // @NotNull(message = "Carburant?")
       private String  typeCarburant ;
      //@NotNull(message = "Anne  du modele?")
     private String  anneMOdel ;
      //@NotNull(message = "Kilometrage?")
      private String  kilomtrage ;
      //@NotNull(message = "Puissance Fiscale?")
       private String puissanceFiscale;
      //@NotNull(message = "Etage?")
     private String  etage;
     // @NotNull(message = "Nombre de pieces?")
      private String  nbPieces ;
     // @Size(min=5 ,message = "Adresse?")
       private String emplacement  ;
     // @NotNull(message = "Zone?")
     private String zone  ;
      // @Size(min=1 ,message = "Superficie?")
      private String  superficie ;
      // @Size(min=1 ,message = "Prix du m2?")
       private String  prixM2  ;
        @Size(min=5 ,message = "Plus de 5 lettres")
     private String  titre;
      private String  typeAnnonce ;
         @Size(min=5 ,message = "Description")
       private String description ;
      //@NotNull(message = "Prix?")
         @Pattern(regexp = "^\\d+$" ,message = "Invalide!")
      @Size(max = 10,message = "10 chiffres au max")
     private String  prix;
      @NotNull(message = "Categorie?")
     private String categorie ;
       @NotNull(message = "SousCategorie?")
      private String sousCategorie;
      @NotNull(message = "Ville?")
      private String  ville;
     private UploadedFile file;
    private StreamedContent streamed;
    private StreamedContent streamed1;
    private StreamedContent streamed2;
    private StreamedContent streamed3;
    private StreamedContent streamed4; 
    private UploadedFile[]  listphoto=new UploadedFile[5];
     private int numOngl;
      private String messageReclamation;
       private String motifReclamation;
       private String dateBean;
       private String reponse;
       List<Annonce>listMyAnnAct=new ArrayList<>();
       List<Annonce> ListMyAnnDesac=new ArrayList<>();
         List<Annonce> ListMyAnnRejet=new ArrayList<>();
         List<Annonce> ListMyAnnModer=new ArrayList<>(); 
       List<Favoris>ListFav=new ArrayList<>();
    private List<String> ptoUpdat=new ArrayList<>();
      private Annonce  annonceToEdit; 

    public Annonce getAnnonceToUpdate() {
        return annonceToUpdate;
    }

    public void setAnnonceToUpdate(Annonce annonceToUpdate) {
        this.annonceToUpdate = annonceToUpdate;
    }
     
            
   //remplire les listes deroulantes 
    private TreeMap<String,String> modeles ;
    private TreeMap<String,String> marques ; 
    
    //remplire les listes categories dans la page de publication
    private TreeMap<String,String> categories ;
    private TreeMap<String,String> sousCategories ;
    //pour precedent suivant
    List<Annonce>l=new ArrayList<>();
    
         private String btnSuiv;
     private String btnPrec;
    private Annonce annonceToUpdate;
  //Constructeur
    public annonceBean() {
       
     }
     //initialisation du bean
    @PostConstruct
    public void init() {
        typeAnnonce="Offre";
        remplirData();
        remplirData_categories();
    }
    //onload mesAnnonces.xhtml
      public void onloadListe(){
        remplirListeAnnFav();
    }
 
    
    //GETTERS SETTERS
      public Annonce getAnnonceToEdit() {
        return annonceToEdit;
    }

    public String getBtnSuiv() {
        return btnSuiv;
    }

 

    public void setBtnSuiv(String btnSuiv) {
        this.btnSuiv = btnSuiv;
    }

    public String getBtnPrec() {
        return btnPrec;
    }

    public void setBtnPrec(String btnPrec) {
        this.btnPrec = btnPrec;
    }

    public void setAnnonceToEdit(Annonce annonceToEdit) {
        this.annonceToEdit = annonceToEdit;
    }
    public List<String> getPtoUpdat() {
        return ptoUpdat;
    }

    public void setPtoUpdat(List<String> ptoUpdat) {
        this.ptoUpdat = ptoUpdat;
    }
    
   public String getDateBean() {
        return dateBean;
    }

    public void setDateBean(String dateBean) {
        this.dateBean = dateBean;
    }

    public String getMessageReclamation() {
        return messageReclamation;
    }

    public void setMessageReclamation(String messageReclamation) {
        this.messageReclamation = messageReclamation;
    }

    public String getMotifReclamation() {
        return motifReclamation;
    }

     public void setMotifReclamation(String motifReclamation) {
        this.motifReclamation = motifReclamation;
    }

    public int getNumOngl() {
        return numOngl;
    }

    public void setNumOngl(int numOngl) {
        this.numOngl = numOngl;
    }

    public List<Annonce> getListMyAnnAct() {
        return listMyAnnAct;
    }

    public void setListMyAnnAct(List<Annonce> listMyAnnAct) {
        this.listMyAnnAct = listMyAnnAct;
    }
          
     public void setModeles(TreeMap<String, String> modeles) {
        this.modeles = modeles;
    }

    public TreeMap<String, TreeMap<String, String>> getData() {
        return data;
    }

    public void setData(TreeMap<String, TreeMap<String, String>> data) {
        this.data = data;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
 

    public List<Annonce> getL() {
        return l;
    }

    public void setL(List<Annonce> l) {
        this.l = l;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getTypeVehiculePro() {
        return typeVehiculePro;
    }

    public void setTypeVehiculePro(String typeVehiculePro) {
        this.typeVehiculePro = typeVehiculePro;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    public String getAnneMOdel() {
        return anneMOdel;
    }

    public void setAnneMOdel(String anneMOdel) {
        this.anneMOdel = anneMOdel;
    }

    public String getKilomtrage() {
        return kilomtrage;
    }

    public void setKilomtrage(String kilomtrage) {
        this.kilomtrage = kilomtrage;
    }

    public String getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public void setPuissanceFiscale(String puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public String getNbPieces() {
        return nbPieces;
    }

    public void setNbPieces(String nbPieces) {
        this.nbPieces = nbPieces;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getZone() {
        return zone;
    }

    public List<Annonce> getListMyAnnDesac() {
        return ListMyAnnDesac;
    }

    public void setListMyAnnDesac(List<Annonce> ListMyAnnDesac) {
        this.ListMyAnnDesac = ListMyAnnDesac;
    }

    public List<Annonce> getListMyAnnRejet() {
        return ListMyAnnRejet;
    }

    public void setListMyAnnRejet(List<Annonce> ListMyAnnRejet) {
        this.ListMyAnnRejet = ListMyAnnRejet;
    }

    public List<Annonce> getListMyAnnModer() {
        return ListMyAnnModer;
    }

    public void setListMyAnnModer(List<Annonce> ListMyAnnModer) {
        this.ListMyAnnModer = ListMyAnnModer;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getPrixM2() {
        return prixM2;
    }

    public void setPrixM2(String prixM2) {
        this.prixM2 = prixM2;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTypeAnnonce() {
        return typeAnnonce;
    }

    public void setTypeAnnonce(String typeAnnonce) {
        this.typeAnnonce = typeAnnonce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(String sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

  

    public TreeMap<String, String> getModeles() {
        return modeles;
    }

    public void setModels(TreeMap<String, String> modeles) {
        this.modeles = modeles;
    }

    public TreeMap<String, String> getMarques() {
        return marques;
    }

    public void setMarques(TreeMap<String, String> marques) {
        this.marques = marques;
    }
       public TreeMap<String, TreeMap<String, String>> getData_categories() {
        return data_categories;
    }

    public void setData_categories(TreeMap<String, TreeMap<String, String>> data_categories) {
        this.data_categories = data_categories;
    }

    public TreeMap<String, String> getCategories() {
        return categories;
    }

    public void setCategories(TreeMap<String, String> categories) {
        this.categories = categories;
    }

    public TreeMap<String, String> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(TreeMap<String, String> sousCategories) {
        this.sousCategories = sousCategories;
    }
    
    
    public UploadedFile[] getListphoto() {
        return listphoto;
    }

    public void setListphoto(UploadedFile[] listphoto) {
        this.listphoto = listphoto;
    }

     
    
    public StreamedContent getStreamed1() throws IOException {
        if(listphoto[1]!=null){
       InputStream in=listphoto[1].getInputstream();
  streamed1 = new DefaultStreamedContent(in,"image/jpg");}
        return streamed1;
    }

    public void setStreamed1(StreamedContent streamed1) {
        this.streamed1 = streamed1;
    }

    public StreamedContent getStreamed2() throws IOException {
        if(listphoto[2]!=null){
       InputStream in=listphoto[2].getInputstream();
  streamed2 = new DefaultStreamedContent(in,"image/jpg");}
        return streamed2;
    }

    public void setStreamed2(StreamedContent streamed2) {
        this.streamed2 = streamed2;
    }

    public StreamedContent getStreamed3() throws IOException {
        if(listphoto[3]!=null){
       InputStream in=listphoto[3].getInputstream();
  streamed3 = new DefaultStreamedContent(in,"image/jpg");}
        return streamed3;
    }

    public void setStreamed3(StreamedContent streamed3) {
        this.streamed3 = streamed3;
    }

    public StreamedContent getStreamed4() throws IOException {
        if(listphoto[4]!=null){
       InputStream in=listphoto[4].getInputstream();
  streamed4 = new DefaultStreamedContent(in,"image/jpg");}
        return streamed4;
    }

    public void setStreamed4(StreamedContent streamed4) {
        this.streamed4 = streamed4;
    }
 
    public StreamedContent getStreamed() throws IOException {
        if(listphoto[0]!=null){
       InputStream in=listphoto[0].getInputstream();
  streamed = new DefaultStreamedContent(in,"image/jpg");}
         return streamed;
       
    }

    public void setStreamed(StreamedContent streamed) {
        this.streamed = streamed;
    }
   
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
   

    public List<Favoris> getListFav() {
        return ListFav;
    }

    public void setListFav(List<Favoris> ListFav) {
        this.ListFav = ListFav;
    }
   
    public void remplirData(){
     
        
        marques  = new TreeMap<String, String>();
         TreeMap<String,String> map = new TreeMap<String, String>();
        map.put("Dokker", "Dokker");
        map.put("Dokker van", "Dokker van");
         map.put("Duster", "Duster");
         map.put("Lodgy", "Lodgy");
         map.put("Logan", "Logan");
         map.put( "loganmcv","Logan Mcv");
         map.put("Pickup", "Pickup");
         map.put( "Pick-Up Double Cab","pickupdoublecab");
         map.put("Sandero", "Sandero");
         map.put("Solenza", "Solenza");
        data.put("Dacia", map);
		
       map =  new TreeMap<String, String>();
        map.put("Avenger", "Avenger");
        map.put("Caliber", "Caliber"); 
         
        data.put("Dodge", map);
		
		 
        map =  new TreeMap<String, String>();
        map.put("Espero", "Espero");
        map.put("Evanda", "Evanda");
         map.put("Kalos", "Kalos");
         map.put("Korando", "Korando");
         map.put("Lacetti", "Lacetti");
         map.put("Lanos", "Lanos");
         map.put("Matiz", "Matiz");
         map.put("Musso", "Musso");
         map.put("Nexia", "Nexia");
         map.put("Nubira", "Nubira");
         map.put("Rezzo", "Rezzo");
        
        data.put("Daewoo", map);
		
		
	map =  new TreeMap<String, String>();
        map.put("33", "33");
        map.put("4C", "4C");
         map.put("75", "75");
         map.put("8c", "8c");
         map.put("145", "145");
         map.put("146", "146");
         map.put("147", "147");
         map.put("155", "155");
         map.put("156", "156");
         map.put("159", "159");
         map.put("164", "164");
         map.put("166", "166");
         map.put("147", "147");
         map.put("gta", "gta");
         map.put("Brera", "Brera");
         map.put("GIULIA", "GIULIA");
         map.put("Gtv", "Gtv");
         map.put("Mito", "Mito");
		  map.put("Spider", "Spider"); 
         
        data.put("Alfa Romeo", map);
		
		   map =  new TreeMap<String, String>();
        map.put("Amv8", "Amv8");
        map.put("DB7", "DB7");
         map.put("DB9", "DB9");
         map.put("DBS", "DBS");
         map.put("Vanquish", "Vanquish");
         map.put("Vantage", "Vantage");
         map.put("Virage", "Virage");
        data.put("Aston Martin", map);
		
	    map =  new TreeMap<String, String>();
        map.put("80", "80");
        map.put("90", "90");
         map.put("100", "100");
         map.put("200", "200");
         map.put("A1", "A1");
         map.put("A2", "A2");
         map.put("A3", "A3");
         map.put("A4", "A4");
         map.put("A5", "A5");
         map.put("A6", "A6");
         map.put("A7", "A7");
         map.put("A8", "A8");
         map.put("Allroad", "Allroad");
         map.put("Coupe", "Coupe");
         map.put("Q3", "Q3");
         map.put("Q5", "Q5");
         map.put("Q7", "Q7");
         map.put("R8", "R8");
	 map.put("RS2", "RS2");
         map.put("RS3", "RS3");
         map.put("RS4", "RS4");
         map.put("RS5", "RS5");
         map.put("RS6", "RS6");
         map.put("S2", "S2");
         map.put("S3", "S3"); 
	 map.put("S4", "S4");
         map.put("S5", "S5");
         map.put("S6", "S6");
         map.put("S8", "S8");
         map.put("TT", "TT");
		  map.put("TTS", "TTS");
		   map.put("V8", "V8");
        data.put("Audi", map);
          map =  new TreeMap<String, String>();
        map.put("Arnage", "Arnage");
        map.put("Azure", "Azure");
         map.put("Brooklands", "Brooklands");
         map.put("Camargue", "Camargue");
         map.put("Continental t", "Continental t");
         map.put("Continental", "Continental");
         map.put("Flying Spur", "Flying Spur");
         map.put("Continental gt", "Continental gt");
         map.put("Continental gtc", "Continental gtc");
         map.put("Continental r", "Continental r");
         map.put("Continental s", "Continental s");
         map.put("Corniche convertible", "Corniche convertible");
         map.put("Corniche coupé", "Corniche coupé");
         map.put("Continental gt coupé", "Continental gt coupé");
        map.put("Eight", "Eight");
         map.put("Mulsanne", "Mulsanne");
         map.put("T series", "T series");
         map.put("Turbo r", "Turbo r"); 
        data.put("Bentley", map);
        
        
          map =  new TreeMap<String, String>();
        map.put("CABRIOLET", "CABRIOLET");
        map.put("COMPACT", "COMPACT");
         map.put("M", "M");
         map.put("M3", "M3");
         map.put("M4", "M4");
         map.put("M5", "M5");
         map.put("M6", "M6");
         map.put("SERIE 2", "SERIE 2");
         map.put("SERIE 3 GT", "SERIE 3 GT");
         map.put("Serie 1", "Serie 1");
         map.put("Serie 3", "Serie 3");
         map.put("Serie 4", "Serie 4");
         map.put("Serie 5", "Serie 5");
         map.put("Serie 6", "Serie 6");
         map.put("Serie 7", "Serie 7");
         map.put("Serie 8", "Serie 8");
         map.put("X1", "X1");
         map.put("X3", "X3");
         map.put("X4", "X4");
         map.put("X5", "X5");
         map.put("X6", "X6");
         map.put("Z1", "Z1");
         map.put("Z3", "Z3");
         map.put("Z4", "Z4");
         map.put("Z8", "Z8"); 
         
        data.put("BMW", map);
        
            map =  new TreeMap<String, String>();
        map.put("F1", "F1");
        map.put("F3", "F3"); 
         
        data.put("BYD", map);
            map =  new TreeMap<String, String>();
        map.put("Allante", "Allante");
        map.put("BLS", "BLS");
         map.put("Brougham", "Brougham");
         map.put("CTS", "CTS");
         map.put("DeVille", "DeVille");
         map.put("DLS", "DLS");
         map.put("Eldorado", "Eldorado");
         map.put("Escalade", "Escalade");
         map.put("Fleetwood", "Fleetwood");
         map.put("Seville", "Seville");
         map.put("SLS", "SLS");
         map.put("SRX", "SRX");
         map.put("STS", "STS");
         map.put("XLR", "XLR");
        data.put("Cadillac", map);
        
          map =  new TreeMap<String, String>();
        map.put("Ideal", "Ideal");
        map.put("Autres", "Autres");
        data.put("Changhe", map);
		
          map =  new TreeMap<String, String>();
        map.put("a1", "a1");
        map.put("A113", "A113");
         map.put("a3", "a3");
         map.put("a5", "a5");
         map.put("A516", "A516");
         map.put("Eastar", "Eastar");
         map.put("QQ", "QQ");
         map.put("QQ6", "QQ6");
         map.put("Tiggo", "Tiggo");
         map.put("v5", "v5");
         map.put("V525", "V525");
        data.put("LISTy", map);
        
                map =  new TreeMap<String, String>();
        map.put("Caprice", "Caprice");
        map.put("Alero", "Alero");
         map.put("Astro", "Astro");
         map.put("Avalanche", "Avalanche");
         map.put("Aveo", "Aveo");
         map.put("Beretta", "Beretta");
         map.put("Blazer", "Blazer");
         map.put("Camaro", "Camaro");
         map.put("Captiva ", "Captiva ");
         map.put("Cavalier", "Cavalier");
         map.put("CMP", "CMP");
         map.put("Corvette", "Corvette");
         map.put("Corvette Cabrio", "Corvette Cabrio");
         map.put("CR8", "CR8");
         map.put("Cruze", "Cruze");
         map.put("El Camino", "El Camino");
         map.put("Epica ", "Epica ");
         map.put("EXPRESS", "EXPRESS");
         map.put("HHR", "HHR");
         map.put("Lacetti", "Lacetti");
         map.put("METRO", "METRO");
         map.put("MONTE CARLO", "MONTE CARLO");
         map.put("NUBIRA SW", "NUBIRA SW");
         map.put("Optra", "Optra");
         map.put("S 10", "S 10");   
		 map.put("SILVERADO", "SILVERADO");
         map.put("Spark", "Spark");
         map.put("SSR", "SSR");
         map.put("SUBURBAN", "SUBURBAN");
         map.put("TACUMA", "TACUMA");
         map.put("Tahoe", "Tahoe");
         map.put("TRACKER", "TRACKER");
         map.put("Trailblazer", "Trailblazer");
         map.put("TRANS SPORT", "TRANS SPORT");
         map.put("TRAVERSE", "TRAVERSE");
		 map.put("UPLANDER", "UPLANDER");
        data.put("Chevrolet" , map);
	   map =  new TreeMap<String, String>();
        map.put("Alero", "Alero");
        map.put("Astro", "Astro");
         map.put("Avalanche", "Avalanche");
         map.put("Aveo", "Aveo");
         map.put("Beretta", "Beretta");
         map.put("Blazer", "Blazer");
         map.put("Camaro", "Camaro");
         map.put("Caprice", "Caprice");
         map.put("Captiva", "Captiva");
         map.put("Cavalier", "Cavalier");
         map.put("CMP", "CMP");
         map.put("Corvette", "Corvette");
         map.put("Corvette Cabrio", "Corvette Cabrio");
         map.put("CR8", "CR8");
         map.put("Cruze", "Cruze");
         map.put("El Camino", "El Camino");
         map.put("Epica", "Epica");
         map.put("EXPRESS", "EXPRESS");  
		 map.put("HHR", "HHR");
         map.put("Lacetti", "Lacetti");
         map.put("METRO", "METRO");
         map.put("MONTE CARLO", "MONTE CARLO");
         map.put("NUBIRA SW", "NUBIRA SW");
         map.put("Optra", "Optra");
         map.put("S 10", "S 10"); 
		 map.put("SILVERADO", "SILVERADO");
         map.put("Spark", "Spark");
         map.put("SSR", "SSR");
         map.put("SUBURBAN", "SUBURBAN");
         map.put("TACUMA ", "TACUMA ");
         map.put("Tahoe", "Tahoe");
         map.put("TRACKER", "TRACKER");
         map.put("Trailblazer", "Trailblazer");
		 map.put("TRANS SPORT", "TRANS SPORT");
		 map.put("TRAVERSE", "TRAVERSE");
		 map.put("UPLANDER ", "UPLANDER ");
        data.put("Chrysler", map);
		
 map =  new TreeMap<String, String>();
 map.put("2cv","2 CV"); 
 map.put("ax","AX");  
 map.put("berlingo","Berlingo"); 
 map.put("bx","BX");  
 map.put("c1","C1");
 map.put("c15","C15");
 map.put("c2","C2");
 map.put("c25","C25"); 
 map.put("c3","C3"); 
 map.put("c35","C35"); 
 map.put("c3 Picasso","C3 Picasso"); 
 map.put("c3 Pluriel","C3 PLURIEL"); 
 map.put("c4","C4");  
 map.put("c4 Aircross","C4 Aircross"); 
 map.put("c4 Picasso","C4 Picasso");  
 map.put("C4 CACTUS","c4cactus");   
 map.put("c5","C5"); 
 map.put("c6","C6");  
 map.put("c8","C8");  
 map.put("C Elysée","c elysee");  
 map.put("C CROSSER","ccrosser");  
 map.put("C ELYSEE","celysee");  
 map.put("cx","CX");
 map.put("ds","DS");   
 map.put("ds19","DS19");  
 map.put("ds20","DS20");  
 map.put("ds21","DS21"); 
 map.put("ds23","DS23"); 
 map.put("ds3","DS3");   
 map.put("ds4","DS4");   
 map.put("ds5","DS5");  
 map.put("evasion","EVASION"); 
 map.put("GRAND C4 PICASSO","grandc4picasso"); 
 map.put("jumper","Jumper"); 
 map.put("jumpy","Jumpy");  
 map.put("nemo","Nemo");  
 map.put("saxo","SAXO"); 
 map.put("sm","SM"); 
 map.put("visa","VISA"); 
 map.put("xantia","XANTIA"); 
 map.put("xm","XM");  
 map.put("xsara","XSARA");  
 map.put("Xsara Picasso","xsara picasso");  
 map.put("ZX","ZX");  

 data.put("citroein", map);
map = new TreeMap<String, String>();
        map.put("Copen", "Copen");
         map.put("Sirion", "Sirion");
         map.put("Terios", "Terios");
         map.put("YRV", "YRV");
        
		 
        data.put("Daihatsu", map);	 
		
      
    map =  new TreeMap<String, String>();
 map.put("daily","Daily");
 map.put("trakker","Trakker");
 map.put("Autres","Autres"); 
  data.put("Iveco", map);
     map =  new TreeMap<String, String>();
map.put("458","458");
map.put("488","488");
map.put("california","California");
map.put("f40","F40");
map.put("f430","F430");
map.put("nitro","Nitro");
map.put("ram","Ram");
data.put("Ferrari", map);

  map =  new TreeMap<String, String>();
  map.put("126","126");
map.put("127","127");
map.put("131","131");
map.put("500","500");
map.put("500c","500C");
map.put("500l","500L");map.put("500x","500X");
map.put("albea","Albea");map.put("barchetta","BARCHETTA");map.put("bertone","BERTONE");
map.put("brava","BRAVA");map.put("bravo","Bravo");
map.put("burstner","BURSTNER");map.put("cinquecento","CINQUECENTO");
map.put("croma","CROMA");map.put("doblo","Doblo");map.put("ducato","Ducato");
map.put("fiorino","FIORINO");map.put("freemont","FREEMONT");
map.put("grandepunto","GRANDE PUNTO");map.put("idea","IDEA");
map.put("linea","Linea");map.put("marea","MAREA");map.put("multipla","MULTIPLA");map.put("palio","Palio");map.put("panda","Panda");map.put("pinto","Pinto");map.put("punto","Punto");map.put("regata","REGATA");map.put("ritmo","RITMO");map.put("scudo","Scudo");map.put("sedici","SEDICI");map.put("seicento","SEICENTO");map.put("siena","Siena");map.put("stilo","STILO");map.put("tempra","TEMPRA");map.put("tipo","TIPO");map.put("ulysse","ULYSSE");map.put("uno","Uno");
data.put("Fiat", map);
 map =  new TreeMap<String, String>();
 map.put("e pace","E PACE");map.put("f pace","F PACE");
 map.put("f type","F Type");
 map.put("s type","S Type");map.put("sovreign","Sovreign");map.put("x type","X Type");
 map.put("xe","XE");map.put("xf","XF");map.put("xj","XJ");map.put("xj6","XJ6");map.put("xj8","XJ8");map.put("xk8","XK8");map.put("xkr","XKR");map.put("Autres","Autres");
  data.put("Jaguar", map);
    map =  new TreeMap<String, String>();
   map.put("cherokee","Cherokee"); map.put("cj5","CJ5"); map.put("commander","Commander"); map.put("compass","Compass"); map.put("dallas","DALLAS"); map.put("grand cherokee","Grand Cherokee"); map.put("kaisser","KAISSER"); map.put("liberty","Liberty"); map.put("m151mutt","M151 MUTT"); map.put("patriot","Patriot"); map.put("renegade","Renegade"); map.put("wrangler","Wrangler"); map.put("Autres","Autres"); 
   data.put("Jeep", map);
    map =  new TreeMap<String, String>(); 
 map.put("besta","BESTA"); map.put("cadenza","CADENZA"); map.put("carens","Carens"); map.put("carnival","CARNIVAL"); map.put("ceed","C'eed"); map.put("cerato","Cerato"); map.put("clarus","CLARUS"); map.put("combi","COMBI"); map.put("k2700","K2700"); map.put("magentis","MAGENTIS"); map.put("mohave","Mohave"); map.put("opirus","Opirus"); map.put("optima","Optima"); map.put("picanto","Picanto"); map.put("pregio","PREGIO"); map.put("pride","PRIDE"); map.put("rio","Rio"); map.put("sephia","SEPHIA"); map.put("shuma","SHUMA"); map.put("sorento","Sorento"); map.put("soul","Soul"); map.put("sportage","Sportage"); map.put("Autres","Autres");
  data.put("Kia", map);
   map =  new TreeMap<String, String>();
map.put("ex35","EX35");map.put("fx35","FX35");map.put("fx50","FX50");map.put("g35","G35");map.put("g37","G37");map.put("Autres","Autres");
 data.put("Infiniti", map);
      map =  new TreeMap<String, String>();
  map.put("aventador","Aventador");map.put("gallardo","Gallardo");map.put("huracan","Huracan");map.put("Autres","Autres"); 
   data.put("Lamorguini", map);
    map =  new TreeMap<String, String>();
 map.put("delta","Delta");map.put("y","Y");map.put("ypsilon","Ypsilon");map.put("Autres","Autres"); 
  data.put("Lancia", map);
   map =  new TreeMap<String, String>();
 map.put("defender","Defender");map.put("discovery","Discovery");map.put("Discovery Sport","discovery sport");map.put("freelander","Freelander");map.put("range rover","Range Rover");map.put("Range Rover Evoque","range rover evoque");map.put("Range Rover Sport","range rover sport");map.put("velar","Velar");map.put("Autres","Autres"); 
  data.put("Land rover", map);
    map =  new TreeMap<String, String>();
  map.put("ls","LS");map.put("rx","RX");map.put("Autres","Autres"); 
   data.put("Lexus", map);
     map =  new TreeMap<String, String>();
  map.put("Town Car","town car");map.put("Autres","Autres"); 
   data.put("Lincoln", map);
   
     map =  new TreeMap<String, String>();
 map.put("hover","Hover");map.put("scorpion","Scorpion");map.put("Autres","Autres"); 
  data.put("Mahindra", map);
   map =  new TreeMap<String, String>();
 map.put("8x4","8X4");map.put("tga","TGA");map.put("Autres","Autres");
  data.put("Man", map);
    map =  new TreeMap<String, String>();
  map.put("440","440");map.put("Autres","Autres");
   data.put("Masey Ferguson", map);
     map =  new TreeMap<String, String>();
 map.put("3200 gt","3200 GT");map.put("4200 gt","4200 GT");map.put("quattroporte","Quattroporte");map.put("Autres","Autres"); 
  data.put("Maserati", map);
   map =  new TreeMap<String, String>();
 map.put("accent","Accent");map.put("atos","Atos");map.put("Atos Prime","atos prime");map.put("azera","Azera");map.put("centennial","CENTENNIAL");map.put("county","COUNTY");map.put("coupe","Coupe");map.put("creta","CRETA");map.put("elantra","Elantra");map.put("excel","EXCEL");map.put("galloper","GALLOPER");map.put("genesis","Genesis");map.put("getz","GETZ");map.put("Grand i10","grand i10");map.put("grandeur","GRANDEUR");map.put("h","H");map.put("h 1","H 1");map.put("h 100","H 100");map.put("h200","H200");map.put("i 10","i10");map.put("i 20","i20");map.put("i30","i 30");map.put("i 40","i 40");map.put("ix55","IX55");map.put("ix 35","ix 35");map.put("lantra","LANTRA");map.put("matrix","Matrix");map.put("pony","PONY");map.put("santa fe","Santa Fe");map.put("santamo","SANTAMO");map.put("satellite","SATELLITE");map.put("sonata","SONATA");map.put("sonica","SONICA");map.put("terracan","TERRACAN");map.put("trajet","TRAJET");map.put("tucson","Tucson");map.put("veloster","VELOSTER");map.put("veracruz","Veracruz");map.put("xg","XG");map.put("Autres","Autres");
  data.put("Hyndai", map);
   map =  new TreeMap<String, String>();
 map.put("h2","H2");map.put("h3","H3");map.put("Autres","Autres");
  data.put("Hummer", map);
  
   map.put("accent","Accent");map.put("accord","Accord");map.put("acty","ACTY");map.put("aerodeck","AERODECK"); map =  new TreeMap<String, String>();
 map.put("boss","BOSS");map.put("city","City");map.put("civic","Civic");map.put("concerto","CONCERTO");map.put("cr v","CR V");map.put("crx","CR X");map.put("frv","FR V");map.put("hrv","HR V");map.put("integra","INTEGRA");map.put("jazz","Jazz");map.put("legend","Legend");map.put("logo","LOGO");map.put("nsx","NSX");map.put("odyssey","Odyssey");map.put("prelude","PRELUDE");map.put("s2000","S 2000");map.put("shuttle","SHUTTLE");map.put("stream","STREAM");map.put("vigor","Vigor");
 
  data.put("Honda", map);
  map =  new TreeMap<String, String>();
 map.put("hover","Hover");
 map.put("yukon","Yukon"); 
  data.put("GMC", map);
    map =  new TreeMap<String, String>();
map.put("gk gl","GK GL");map.put("gk gs","GK GS");map.put("gk gt","GK GT");
map.put("lc","LC");map.put("mk gl","MK GL");map.put("mk gs","MK GS");map.put("mk gt","MK GT");map.put("zotye","Zotye");
 data.put("Geely", map);
  map =  new TreeMap<String, String>();
 map.put("bedford","BEDFORD");map.put("c max","C Max");map.put("capri","CAPRI");
 map.put("connect","CONNECT");map.put("cougar","COUGAR");map.put("courrier","COURRIER");map.put("crownvictoria","CROWN VICTORIA");map.put("edge","EDGE");map.put("escape","ESCAPE");map.put("escort","ESCORT");map.put("excursion","EXCURSION");map.put("expedition","Expedition");map.put("explorer","EXPLORER");map.put("f150","F150");map.put("f250","F250");map.put("f350","F350");map.put("fiesta","Fiesta");map.put("focus","Focus");map.put("focuscmax","FOCUS C MAX");map.put("fusion","Fusion");map.put("galaxy","Galaxy");map.put("gt","GT"); map.put("ka","Ka");map.put("kuga","Kuga");map.put("maverick","MAVERICK");map.put("minibus","MINIBUS");map.put("mondeo","Mondeo");map.put("mustang","Mustang");map.put("orion","ORION");map.put("probe","PROBE");map.put("puma","PUMA");map.put("ranger","Ranger");map.put("s max","S Max");map.put("scorpio","SCORPIO");map.put("sierra","SIERRA");map.put("streetka","STREET KA");map.put("superduty","SUPER DUTY");map.put("thunderbird","THUNDERBIRD");map.put("tourneo","Tourneo");map.put("transit","Transit");map.put("victoria","VICTORIA"); 
  data.put("Ford", map);
   map =  new TreeMap<String, String>();
 map.put("2","2");map.put("3","3");map.put("5","5");map.put("6","6");map.put("121","121");map.put("323","323");map.put("626","626");map.put("929","929");map.put("astina","ASTINA");map.put("b2500","B 2500");map.put("bk","BK");map.put("bongo","BONGO");map.put("bt 50","BT 50");map.put("cx 9","CX 9");map.put("cx3","CX 3");map.put("cx5","CX 5");map.put("cx7","CX7");map.put("demio","DEMIO");map.put("e2000","E2000");map.put("familia","FAMILIA");map.put("mpv","MPV");map.put("mx 5","MX 5");map.put("mx3","MX3");map.put("mx6","MX6");map.put("premacy","PREMACY");map.put("rx 8","RX 8");map.put("rx7","RX7");map.put("sportiva","SPORTIVA");map.put("tribute","TRIBUTE");map.put("xedos","XEDOS");map.put("Autres","Autres");
  data.put("Mazda", map);
    
    map =  new TreeMap<String, String>();
map.put("190","190");map.put("210","210");map.put("220","220");map.put("230","230");map.put("240","240");map.put("250","250");map.put("260","260");map.put("270","270");map.put("280","280");map.put("300","300");map.put("400","400");map.put("408","408");map.put("410","410");map.put("412","412");map.put("560","560");map.put("608","608");map.put("207d","207D");map.put("310d","310D");map.put("amggt","AMG GT");map.put("citan","CITAN");map.put("classe A","Classe A");map.put("classe B","Classe B");map.put("classe C","Classe C");map.put("classe cl","Classe CL");map.put("classe cla","Classe CLA");map.put("classe clk","Classe CLK");map.put("classe cls","Classe CLS");map.put("classe e","Classe E");map.put("classe g","Classe G");map.put("Classe GL","classe gl");map.put("Classe GLA","classe gla");map.put("classe glc","Classe GLC");map.put("classe gle","Classe GLE");map.put("Classe GLS","classe gls");map.put("Classe M","classe m");map.put("Classe S","classe s");map.put("Classe SL","classe sl");map.put("Classe SLK","classe slk");map.put("Classe V","classe v");map.put("CLASSE C COUPE","classeccoupe");map.put("CLASSE CLC","classeclc");map.put("CLASSE GLK","classeglk");map.put("CLASSE ML","classeml");map.put("CLASSE R","classer");map.put("classeslc","CLASSE SLC");map.put("CLASSE SLR","classeslr");map.put("CLASSE SLS","classesls");map.put("mb","MB");map.put("roadstar","ROAD STAR");map.put("sprinter","Sprinter");map.put("utilitaire","UTILITAIRE");map.put("vaneo","VANEO");map.put("viano","Viano");map.put("vito","Vito");map.put("Autres","Autres");
data.put("Mercedes Benz", map);

 
  map =  new TreeMap<String, String>();
 map.put("911 turbo","911 Turbo"); map.put("911 carrera","911 Carrera"); map.put("boxster","Boxster"); map.put("Carrera GT","carrera gt"); map.put("cayenne","Cayenne"); map.put("cayman","Cayman"); map.put("panamera","Panamera"); map.put("Autres","Autres");
 data.put("Porsche", map);
 map =  new TreeMap<String, String>();
 map.put("g6","G6"); map.put("solstice","Solstice"); map.put("Autres","Autres");
 data.put("Pontiac", map);
 map =  new TreeMap<String, String>();
 map.put("104","104"); map.put("106","106"); map.put("107","107"); map.put("204","204"); map.put("205","205"); map.put("206","206"); map.put("207","207"); map.put("208","208"); map.put("301","301"); map.put("304","304"); map.put("305","305"); map.put("306","306"); map.put("307","307"); map.put("308","308"); map.put("309","309"); map.put("403","403"); map.put("404","404"); map.put("405","405"); map.put("406","406"); map.put("407","407"); map.put("504","504"); map.put("505","505"); map.put("506","506"); map.put("507","507"); map.put("508","508"); map.put("604","604"); map.put("605","605"); map.put("607","607"); map.put("806","806"); map.put("807","807"); map.put("1007","1007"); map.put("2008","2008"); map.put("3008","3008"); map.put("4007","4007"); map.put("4008","4008"); map.put("5008","5008"); map.put("206+","206+"); map.put("206cc","206 CC"); map.put("206sw","206 SW"); map.put("207cc","207 CC"); map.put("207sw","207 SW"); map.put("307cc","307 CC"); map.put("307sw","307 SW"); map.put("308sw","308 SW"); map.put("407sw","407 SW"); map.put("407coupe","407 COUPE"); map.put("bipper","Bipper"); map.put("boxer","Boxer"); map.put("expert","Expert"); map.put("j5","J5"); map.put("j9","J9"); map.put("partner","Partner"); map.put("rcz","RCZ"); map.put("tepee","Tepee"); map.put("Autres","Autres");
 data.put("Peugeot", map);
 map =  new TreeMap<String, String>();
 map.put("adam","ADAM"); map.put("agila","Agila"); map.put("antara","ANTARA"); map.put("ascona","ASCONA"); map.put("astra","Astra"); map.put("calibra","CALIBRA"); map.put("campo","CAMPO"); map.put("combo","COMBO"); map.put("corsa","Corsa"); map.put("frontera","FRONTERA"); map.put("gt","GT"); map.put("insignia","Insignia"); map.put("kadett","KADETT"); map.put("meriva","Meriva"); map.put("mokka","MOKKA"); map.put("monterey","MONTEREY"); map.put("movano","MOVANO"); map.put("omega","OMEGA"); map.put("opc","OPC"); map.put("rekord","REKORD"); map.put("signum","SIGNUM"); map.put("sintra","SINTRA"); map.put("speedster","SPEEDSTER"); map.put("tigra","Tigra"); map.put("vectra","Vectra"); map.put("vivaro","VIVARO"); map.put("zafira","Zafira"); map.put("Autres","Autres");
 data.put("Opel", map);
 map =  new TreeMap<String, String>();
 map.put("280","280"); map.put("300","300"); map.put("350z","350Z"); map.put("370z","370Z"); map.put("240sx","240SX"); map.put("almera","ALMERA"); map.put("altima","Altima"); map.put("bluebird","BLUEBIRD"); map.put("cabstar","CABSTAR"); map.put("evalia","EVALIA"); map.put("gtr","GTR");
 map.put("infiniti","INFINITI"); map.put("juke","Juke"); map.put("kingcab","KING CAB"); map.put("kingdoublecab","KING DOUBLE CAB"); map.put("kubistar","KUBISTAR"); map.put("maxima","MAXIMA"); map.put("micra","Micra"); map.put("murano","Murano"); map.put("navara","Navara"); map.put("note","NOTE"); map.put("pathfinder","Pathfinder"); map.put("patrol","PATROL"); map.put("Patrol GR","patrol gr"); map.put("pick up","Pick up"); map.put("prairie","PRAIRIE"); map.put("primastar","PRIMASTAR"); map.put("primera","primera"); map.put("qashqai","Qashqai"); map.put("rogue","Rogue"); map.put("serena","SERENA"); map.put("sunny","Sunny"); map.put("terrano","TERRANO"); map.put("tiida","Tiida"); map.put("tino","TINO"); map.put("trade","TRADE"); map.put("urvan","URVAN"); map.put("vanette","VANETTE"); map.put("versa","VERSA"); map.put("x trail","X Trail"); map.put("Autres","Autres");
 data.put("Nissan", map);
 map =  new TreeMap<String, String>();
 map.put("canter","canter"); map.put("l200","L200"); map.put("lancer","lancer"); map.put("nativa","nativa"); map.put("outlander","outlander"); map.put("pajero","pajero"); map.put("pajero sport","pajero sport"); map.put("pick up","pick up"); map.put("Autres","Autres");
 data.put("Mitsubishi" , map);
 
 map =  new TreeMap<String, String>();
 map.put("cabrio","cabrio"); map.put("clubman","CLUBMAN"); map.put("cooper","cooper"); map.put("country man","country man"); map.put("one","one"); map.put("Autres","Autres");
  data.put("mini", map);
    
    
    map =  new TreeMap<String, String>();
 map.put("aerio","AERIO"); map.put("alto","Alto"); map.put("apv","APV"); map.put("baleno","BALENO"); map.put("carry","Carry"); map.put("celerio","Celerio"); map.put("grand vitara","Grand Vitara"); map.put("ignis","IGNIS"); map.put("jimmy","Jimmy"); map.put("jimny","JIMNY"); map.put("liana","LIANA"); map.put("maruti","Maruti"); map.put("samurai","SAMURAI"); map.put("splash","SPLASH"); map.put("swift","Swift"); map.put("sx4","SX4"); map.put("vitara","Vitara"); map.put("wagonr","WAGON R"); map.put("x90","X 90"); map.put("xl7","XL7"); map.put("Autres","Autres");

		data.put("suzuki", map);		
		 


map =  new TreeMap<String, String>();
 map.put("d max","D MAX"); map.put("landwind","Landwind"); map.put("trooper","Trooper"); map.put("Autres","Autres");
 data.put("Isuzu", map);		
		 map =  new TreeMap<String, String>();
		  
 map.put("12","12"); map.put("14","14"); map.put("16","16"); map.put("19","19"); map.put("30","30"); map.put("avantime","Avantime"); map.put("b110","B110"); map.put("b120","B120"); map.put("b80","B80"); map.put("captur","Captur"); map.put("clio","Clio"); map.put("espace","Espace"); map.put("estafette","Estafette"); map.put("express","Express"); map.put("fluence","Fluence"); map.put("fuego","Fuego"); map.put("grand espace","Grand Espace"); map.put("grandmodus","Grand Modus"); map.put("grandscenic","Grand Scenic"); map.put("kadjar","Kadjar"); map.put("kangoo","Kangoo"); map.put("kangoo express","Kangoo Express"); map.put("koleos","Koleos"); map.put("laguna","Laguna"); map.put("lagunacoupe","Laguna Coupe"); map.put("lagunaestate","Laguna Estate"); map.put("latitude","Latitude"); map.put("mascott","Mascott"); map.put("master","Master"); map.put("master ccb","Master Ccb"); map.put("megane","Megane"); map.put("megane 3","Megane 3"); map.put("megane coupe","Megane Coupe"); map.put("meganecabriolet","Megane Cabriolet"); map.put("meganecc","Megane CC"); map.put("meganeestate","Megane Estate"); map.put("meganesedan","Megane Sedan"); map.put("messenger","Messenger"); map.put("microbus","Microbus"); map.put("midlum","Midlum"); map.put("modus","Modus"); map.put("nevada","Nevada"); map.put("r11","R11"); map.put("r18","R18"); map.put("r19","R19"); map.put("r20","R20"); map.put("r21","R21"); map.put("r25","R25"); map.put("r4","R4"); map.put("r5","R5"); map.put("r8","R8"); map.put("r9","R9"); map.put("safrane","Safrane"); map.put("samsungs3","Samsung SM3"); map.put("scenic","Scenic"); map.put("spider","Spider"); map.put("super5","Super 5"); map.put("symbol","Symbol"); map.put("talisman","Talisman"); map.put("trafic","Trafic"); map.put("twingo","Twingo"); map.put("twizy","Twizy"); map.put("vel satis","Vel Satis"); map.put("zoe","Zoe"); map.put("Autres","Autres");

data.put("Renault",map);

        
		  map =  new TreeMap<String, String>();
 
 map.put("25","25"); map.put("45","45"); map.put("75","75"); map.put("mini","Mini"); map.put("s100","Serie 100"); map.put("s200","Serie 200"); map.put("s400","Serie 400"); map.put("s600","Serie 600"); map.put("s800","Serie 800"); map.put("sd1","SD1"); map.put("streetwise","Streetwise"); map.put("Autres","Autres");
 data.put("Rover",map);        
		  map =  new TreeMap<String, String>();

 map.put("alhambra","ALHAMBRA"); map.put("altea","Altea"); map.put("alteaxl","ALTEA XL"); map.put("arosa","AROSA"); map.put("cordoba","Cordoba"); map.put("exeo","Exeo"); map.put("ibiza","Ibiza"); map.put("inca","INCA"); map.put("leon","Leon"); map.put("leonst","LEON ST"); map.put("malaga","MALAGA"); map.put("marbella","MARBELLA"); map.put("toledo","Toledo"); map.put("vario","VARIO"); map.put("Autres","Autres");
 data.put("Seat",map);       
		  map =  new TreeMap<String, String>();
 
 map.put("fabia","Fabia"); map.put("favorit","FAVORIT"); map.put("felicia","FELICIA"); map.put("octavia","Octavia"); map.put("rapid","RAPID"); map.put("roomster","Roomster"); map.put("superb","Superb"); map.put("yeti","Yeti"); map.put("Autres","Autres");
 data.put("Skoda",map);       
		  map =  new TreeMap<String, String>();
 
 
 map.put("crossblade","Crossblade"); map.put("fortwo","ForTwo"); map.put("smart","Smart"); map.put("Autres","Autres");
data.put("Smart",map);    
		  map =  new TreeMap<String, String>();
 
 map.put("actyon","Actyon"); map.put("ceo","Ceo"); map.put("family","FAMILY"); map.put("faw","FAW"); map.put("korando","Korando"); map.put("kyron","Kyron"); map.put("rexton","Rexton"); map.put("rodius","Rodius"); map.put("stavic","STAVIC"); map.put("Autres","Autres");
 map =  new TreeMap<String, String>();
		  data.put("Ssangyong",map);     

 map.put("impreza","Impreza"); map.put("legacy","Legacy"); map.put("tribeca","Tribeca"); map.put("Autres","Autres");

		  data.put("Subaru",map);    

 map =  new TreeMap<String, String>();


 map.put("4runner","4RUNNER"); map.put("auris","Auris"); map.put("avensis","Avensis"); map.put("avensisverso","AVENSIS VERSO"); map.put("aygo","Aygo"); map.put("camry","Camry"); map.put("carina","CARINA"); map.put("celica","CELICA"); map.put("corolla","Corolla"); map.put("corolla verso","Corolla verso"); map.put("corona","CORONA"); map.put("fj","FJ"); map.put("fjcruiser","FJ CRUISER"); map.put("hi ace","Hi Ace"); map.put("highlander","HIGHLANDER"); map.put("hilux","Hilux"); map.put("land cruiser","Land Cruiser"); map.put("lexus","LEXUS"); map.put("liteace","LITE ACE"); map.put("mr","MR"); map.put("paseo","PASEO"); map.put("picnic","PICNIC"); map.put("prado","Prado"); map.put("previa","PREVIA"); map.put("prius","Prius"); map.put("rav 4","RAV 4"); map.put("runner","RUNNER"); map.put("sienna","SIENNA"); map.put("starlet","STARLET"); map.put("supra","SUPRA"); map.put("tercel","Tercel"); map.put("tundra","TUNDRA"); map.put("verso","VERSO"); map.put("yaris","Yaris"); map.put("yaris verso","Yaris Verso"); map.put("Autres","Autres");
 data.put("Toyota",map);        
		  map =  new TreeMap<String, String>();

 map.put("rv3","RV3"); map.put("Autres","Autres");

 data.put("UFO",map);       
		  map =  new TreeMap<String, String>();



 map.put("amarok","Amarok"); map.put("beetle","Beetle"); map.put("bora","Bora"); map.put("caddy","Caddy"); map.put("caravelle","Caravelle"); map.put("cc","CC"); map.put("coccinelle","COCCINELLE"); map.put("combi","COMBI"); map.put("corrado","CORRADO"); map.put("crafter","CRAFTER"); map.put("eos","EOS"); map.put("fox","Fox"); map.put("gol","Gol"); map.put("golf","Golf"); map.put("golf2","GOLF 2"); map.put("golf3","GOLF 3"); map.put("golf4","GOLF 4"); map.put("golf5","GOLF 5"); map.put("golf6","GOLF 6"); map.put("golf7","GOLF 7"); map.put("golfplus","GOLF PLUS"); map.put("jetta","Jetta"); map.put("karman","KARMAN"); map.put("lt","LT"); map.put("lupo","LUPO"); map.put("multivan","MULTIVAN"); map.put("newbeetle","NEW BEETLE"); map.put("parati","PARATI"); map.put("passat","Passat"); map.put("passatcc","PASSAT CC"); map.put("phaeton","Phaeton"); map.put("polo","Polo"); map.put("scirocco","Scirocco"); map.put("sharan","SHARAN"); map.put("thing","THING"); map.put("tiguan","Tiguan"); map.put("touareg","Touareg"); map.put("touran","Touran"); map.put("transporter","TRANSPORTER"); map.put("up","UP"); map.put("vento","Vento"); map.put("Autres","Autres");
data.put("Volkswagen",map);   
		  map =  new TreeMap<String, String>();
 
 map.put("c30","C30"); map.put("c70","C70"); map.put("s40","S40"); map.put("s60","S60"); map.put("s80","S80"); map.put("v40","V40"); map.put("v40 cross country","V40 Cross Country"); map.put("xc60","XC60"); map.put("xc70","XC70"); map.put("xc90","XC90"); map.put("Autres","Autres");
 data.put("Volvo",map);         
		  map =  new TreeMap<String, String>();
 
 map.put("nomad","Nomad"); map.put("Autres","Autres");
data.put("Zotye",map);       
		  map =  new TreeMap<String, String>();
 
 map.put("cobra","COBRA"); map.put("gt40","GT 40"); map.put("seven","SEVEN"); map.put("Autres","Autres");
 data.put("AC",map);          
		  map =  new TreeMap<String, String>();
 
 map.put("zest","ZEST"); map.put("Autres","Autres");
data.put("Acrea",map);       
		  map =  new TreeMap<String, String>();
 map.put("mdx","MDX"); map.put("rdx","RDX"); map.put("rl","RL"); map.put("tl","TL"); map.put("tsx","TSX"); map.put("zdx","ZDX"); map.put("Autres","Autres");

data.put("Acura",map);       
		  map =  new TreeMap<String, String>();
		   map =  new TreeMap<String, String>();
map.put("Autres","Autres");
 data.put("Autres",map); 
 
    map =  new TreeMap<String, String>();
		 map.put("Forland","Forland");
		  data.put("Foton", map);	







   
    
    }
    
    
 public void remplirData_categories(){
     
         TreeMap<String,String> map = new TreeMap<String, String>();
         map.put("Téléphones","Téléphones");
                          map.put("Tablettes","Tablettes");
                             map.put("Ordinateurs portables","Ordinateurs portables");
                              map.put("Ordinateurs de bureau","Ordinateurs de bureau");
                             map.put("Accessoires informatique et Gadgets","Accessoires informatique et Gadgets");
                              map.put("Jeux vidéo et Consoles","Jeux vidéo et Consoles");
                              map.put("Appareils photo et Caméras","Appareils photo et Caméras");
                              map.put("Télévisions","Télévisions");
                             map.put("Image et Son","Image et Son");
        data_categories.put("INFORMATIQUE ET MULTIMEDIA", map);
        
        
           map = new TreeMap<String, String>();
              map.put("Voitures","Voitures");
                                map.put("Motos","Motos");
                       map.put("Vélos","Vélos");
                          map.put("Véhicules Professionnels","Véhicules Professionnels");
                           map.put("Bateaux","Bateaux");
                           map.put("Pièces et Accessoires pour véhicules","Pièces et Accessoires pour véhicules");
           
           data_categories.put("VEHICULES", map);
		
       
  map = new TreeMap<String, String>();
		      map.put("Appartements","Appartements");
                          map.put("Maisons et Villas","Maisons et Villas");
                          map.put("Bureaux et Plateaux","Bureaux et Plateaux");
                          map.put("Magasins, Commerces et Locaux industriels","Magasins Commerces et Locaux industriels");
                           map.put("Terrains et Fermes","Terrains et Fermes");
                          map.put("Locations de vacances","Locations de vacances");
                          map.put("Colocations","Colocations");
                          map.put("Autre Immobilier","Autre Immobilier");
		  data_categories.put("IMMOBILIER", map);
                  
                  
                   map = new TreeMap<String, String>();
		        map.put("Electroménager et Vaisselles","Electroménager et Vaisselles");
                            map.put("Meubles et Décoration","Meubles et Décoration");
                           map.put("Jardin et Outils de bricolage","Jardin et Outils de bricolage");
		           data_categories.put("POUR LA MAISON ET JARDIN", map);
                  
                  
                  
                   map = new TreeMap<String, String>();
		      map.put("Vêtements","Vêtements");
                           map.put("Chaussures","Chaussures");
                           map.put("Montres et Bijoux","Montres et Bijoux");
                            map.put("Sacs et Accessoires","Sacs et Accessoires");
                           map.put("Vêtements pour enfant et bébé","Vêtements pour enfant et bébé");
                           map.put("Equipements pour enfant et bébé","Equipements pour enfant et bébé");
                           map.put("Produits de beauté","Produits de beauté");
		  data_categories.put("HABILLEMENT ET BIEN ETRE", map);
               
                    map = new TreeMap<String, String>();
		      map.put("Sports et Loisirs","Sports et Loisirs");
                            map.put("Animaux","Animaux");
                           map.put("Instruments de Musique","Instruments de Musique");
                           map.put("Art et Collections","Art et Collections");
                            map.put("Voyages et Billetterie","Voyages et Billetterie");
                           map.put("Films, Livres, Magazines","Films Livres Magazines");
		  data_categories.put("LOISIRS ET DIVERTISSEMENT", map);
                             
                              
                                      
                                        map = new TreeMap<String, String>();
		      map.put("Offres d'emploi","Offres d'emploi");
                           map.put("Demandes d'emploi","Demandes d'emploi");
                           map.put("Stages","Stages");
                           map.put("Services","Services");
                           map.put("Cours et Formations","Cours et Formations");
		  data_categories.put("EMPLOI ET SERVICES", map);
                      
                                     map = new TreeMap<String, String>();
		                                  map.put("Business et Affaires commerciales","Business et Affaires commerciales");
                           map.put("Matériels Professionnels","Matériels Professionnels");
                           map.put("Stocks et Vente en gros","Stocks et Vente en gros");
		  data_categories.put("ENTREPRISES", map);
                                   
                                
                                              map = new TreeMap<String, String>();
		      map.put("AUTRES","AUTRES");
		  data_categories.put("Autres", map);
                                            
             
                  
                  
     
 }

     //Methode des deux combo liés (marque model)
    public void onMarqueChange() {
        if(marque !=null && !marque.equals(""))
            modeles = data.get(marque);
        else
            modeles = new TreeMap<String, String>();
        
        
        
        
    }
    
    
     public void onCategorieChange() {
        if(categorie !=null && !categorie.equals(""))
           sousCategories= data_categories.get(categorie); 
        else
           sousCategories = new TreeMap<String, String>();
        
        
        
        
    }
     
     
    
 
    
    public void supprimer(String a){
       if(a.equals("0")){
           streamed=null;
           listphoto[0]=null; 
       }
       if(a.equals("1")){
           streamed1=null;
           listphoto[1]=null; 
       }
       if(a.equals("2")){
           streamed2=null;
           listphoto[2]=null; 
       }
       if(a.equals("3")){
           streamed3=null;
           listphoto[3]=null; 
       }
       if(a.equals("4")){
           streamed=null;
           listphoto[4]=null; 
       }
    }
     
    public void upload() throws MalformedURLException, IOException{
    
             int i=0;
          for (UploadedFile uploadedFile : listphoto) {
              if(uploadedFile!=null){
                  i++;
              }
          }
     int  nbRestPh=i+ptoUpdat.size();
        
        
  if((file!=null) &&(nbRestPh<=4)){
     
  InputStream in=file.getInputstream();
  
   if(streamed==null){
  streamed = new DefaultStreamedContent(in,"image/jpg");
   listphoto[0]=file;
   
   }  
   else if(streamed1==null){
  streamed1 = new DefaultStreamedContent(in,"image/jpg"); 
   listphoto[1]=file;
   }
  
 else if(streamed2==null){
  streamed2 = new DefaultStreamedContent(in,"image/jpg");
    listphoto[2]=file;
 
 }
  else if(streamed3==null){
  streamed3 = new DefaultStreamedContent(in,"image/jpg");
 
   listphoto[3]=file;}
    
 
   else if(streamed4==null){
  streamed4 = new DefaultStreamedContent(in,"image/jpg");
 
    listphoto[4]=file;}
  
    
   else{
        FacesMessage errorMsg = new FacesMessage("nombre max de photo est 5");  
            FacesContext.getCurrentInstance().addMessage(null, errorMsg);
   }
 
  
 }else{
//no file selected
       }
             
    }
    public void upload1() throws MalformedURLException, IOException{
     if((file!=null)){
     
  InputStream in=file.getInputstream();
  
   if(streamed==null){
  streamed = new DefaultStreamedContent(in,"image/jpg");
   listphoto[0]=file ;
   }  
   else if(streamed1==null){
  streamed1 = new DefaultStreamedContent(in,"image/jpg"); 
   listphoto[1]=file; 
   }
  
 else if(streamed2==null){
  streamed2 = new DefaultStreamedContent(in,"image/jpg");
    listphoto[2]=file; 
 
 }
  else if(streamed3==null){
  streamed3 = new DefaultStreamedContent(in,"image/jpg");
 
   listphoto[3]=file;}
    
 
   else if(streamed4==null){
  streamed4 = new DefaultStreamedContent(in,"image/jpg");
 
    listphoto[4]=file;}
  
    
   else{
        FacesMessage errorMsg = new FacesMessage("nombre max de photo est 5");  
            FacesContext.getCurrentInstance().addMessage(null, errorMsg);
   }
 
  
 }else{
//no file selected
       }
             
    }
    
    public String capitaliser(String mot){ 
    StringBuilder s= new StringBuilder();
    s.append(Character.toTitleCase(mot.charAt(0))).append(mot.substring(1,mot.length()).toLowerCase());
    return s.toString();
    }
       
    public BigDecimal publier(String mode) throws IOException, SQLException{
             
          Annonce a=new Annonce();
     AnnonceDao  aDao=new AnnonceDao();
          String date= LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
           String heure=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
          String date_exp= LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
          HttpSession session= Util.getSession();
          UtilisateurConnecte e=(UtilisateurConnecte) session.getAttribute("user");
      a.setUtilisateurConnecte(e); 
    a.setTitreAnnonce(titre);
    if(mode.equals("")){
         AnnonceDao and=new AnnonceDao();
          Annonce annoLD= and.getByIdPub(idAnnonce);
          a.setDateDepotAnnonce(annoLD.getDateDepotAnnonce());
     a.setHeureDepotAnnonce(annoLD.getHeureDepotAnnonce());
     a.setNoteAnnonce("");a.setDegreNoteAnnonce("false");
    }else{
     a.setDateDepotAnnonce(date);
     a.setHeureDepotAnnonce(heure);}
      a.setCategorie(categorie);
       a.setSouscategorie(sousCategorie);
      if(prix!=null){
       a.setPrixAnnonce(new BigDecimal(prix.trim()));}
       if(sousCategorie.equalsIgnoreCase("Offres d'emploi")){
         a.setTypeAnnonce("Offre");
       
       }      
                        else  if(sousCategorie.equalsIgnoreCase("Demandes d'emploi")){
                            a.setTypeAnnonce("Demande");
                          } 
                        else{
                              a.setTypeAnnonce(typeAnnonce);
                        }
   
     a.setDescriptionAnnonce(description);
     a.setVilleAnnonce(ville);
     a.setDateExpirationAnnonce(date_exp);
     a.setNbVueAnnonce(BigDecimal.ZERO);
       if(mode.equals("modif")){
       a.setEtatAnnonce("modif");} 
         if(mode.equals("pub")){
         a.setEtatAnnonce("en_cours");}
      
   String pays=(String) session.getAttribute("pays");
   String ip=(String) session.getAttribute("ip");
   a.setIpAnnonce(ip);
  a.setPaysAnnonce(pays);
    
     BigDecimal idnewAnn=  aDao.ajouterAnnonce(a);
    if (idnewAnn!=null){
      for (UploadedFile p : listphoto) {
               
            if(p!=null){
               String[] c=p.getFileName().split("/");
       String name=a.getIdAnnonce()+c[c.length-1].replace("-","").replace("'","").replace("\"",""); 
       
            InputStream in=p.getInputstream(); 
           
            String chemin="C:\\Users\\ibm\\Desktop\\BayloAnnonce_V2\\web\\resources\\img\\"+name;
                  File f=new File(chemin); 
                  FileOutputStream out=new FileOutputStream(f);
                  byte[] buffer=new byte[1024];
            int length;
             while((length=in.read(buffer))>0){
                out.write(buffer, 0, length);
            }
            
            out.close();
            in.close();
         
      PhotoDao  phdao=new PhotoDao();
                    Photo ph=new Photo();
              ph.setCheminPhoto(name);
              ph.setAnnonce(a);
            phdao.ajouterPhoto(ph); 
            
                
        } 
             if (!ptoUpdat.isEmpty()){
          PhotoDao  phdao=new PhotoDao();
          for (String n : ptoUpdat) {
               Photo ph=new Photo();
              ph.setCheminPhoto(n);
              ph.setAnnonce(a);
            phdao.ajouterPhoto(ph); 
          }
      }
      
    
      } 
    
          
        
          
           
      
                            if(sousCategorie.equalsIgnoreCase("Voitures")){ 
                            VoitureDao voitdao=new VoitureDao();
          
              Voiture v=new Voiture(); 
            
     v.setMarqueVoiture(marque); 
     v.setModeleVoiture(modele);
     v.setTypeCarburantVoiture(typeCarburant);
     if(!"".equals(anneMOdel)){
     v.setAnneeModeleVoiture(new BigDecimal(anneMOdel ));}
     if(!"".equals(kilomtrage)){
     v.setKilometrage(new BigDecimal(kilomtrage ));}
      v.setPuissanceFiscaleVoiture(puissanceFiscale);  
     v.setAnnonce(a);
             voitdao.ajouterVoiture(v);
              }
                                if(sousCategorie.equalsIgnoreCase("Motos")){
                                    MotoDao mtdao=new MotoDao();
          
              Moto m=new Moto();
           
         
              
               if(!"".equals(anneMOdel)){
        m.setAnneModelMoto(new BigDecimal(anneMOdel));}
     if(!"".equals(kilomtrage)){
          m.setKilometrageMoto(new BigDecimal(kilomtrage ));}
              m.setAnnonce(a);   
              mtdao.ajouterMoto(m);
                                
                                }
                          if(sousCategorie.equalsIgnoreCase("Véhicules Professionnels")){
                          VoitureDao voitdao=new VoitureDao();
          
              Voiture v=new Voiture(); 
            
     v.setMarqueVoiture(marque); 
     v.setModeleVoiture(modele.trim());
     v.setTypeCarburantVoiture(typeCarburant); if(!"".equals(anneMOdel)){
       v.setAnneeModeleVoiture(new BigDecimal(anneMOdel));}
     if(!"".equals(kilomtrage)){
          v.setKilometrage(new BigDecimal(kilomtrage ));}
     v.setPuissanceFiscaleVoiture(puissanceFiscale);  
     v.setAnnonce(a);
             voitdao.ajouterVoiture(v);
                              VehiculeProfessionnelDao vhprodao=new VehiculeProfessionnelDao();
                              VehiculeProfessionnel vpro=new VehiculeProfessionnel();
                            vpro.setVoiture(v);
                         vpro.setSousCatVehiculeProf(typeVehiculePro);
                              vhprodao.ajouterVehiculeProfessionnel(vpro);
                              
                        } 
                          
                          
                        
                          
                          if(sousCategorie.equalsIgnoreCase("Appartements")||(sousCategorie.equalsIgnoreCase("Bureaux et Plateaux")) ||(sousCategorie.equalsIgnoreCase("Magasins, Commerces et Locaux industriels"))||
                           (sousCategorie.equalsIgnoreCase("Terrains et Fermes"))||
                          (sousCategorie.equalsIgnoreCase("Locations de vacances"))||
                          (sousCategorie.equalsIgnoreCase("Colocations"))
                                  
                                  
                                  ){
                            MaisonVillaDao mvdao=new MaisonVillaDao();
                          MaisonVilla mv=new MaisonVilla();
                           
                          mv.setEmplacementMaisonVilla(emplacement);
                          
                           if(!"".equals(prixM2)){ 
                          mv.setPrixMetreCarreMaisonVilla(new BigDecimal(Integer.valueOf(prixM2)));}
                           else{
                                        mv.setPrixMetreCarreMaisonVilla(BigDecimal.ZERO);
                                   }
                              if(!"".equals(nbPieces)){ 
                        mv.setNbPiecesMaisonVilla(new BigDecimal(Integer.valueOf(nbPieces))); }
                                 if(!"".equals(superficie)){ 
                             mv.setSuperficieMaisonVilla(new BigDecimal(Integer.valueOf(superficie))); }
                                 else{
                                        mv.setSuperficieMaisonVilla(BigDecimal.ZERO);
                                   }
                             mv.setAnnonce(a);
                             mvdao.ajouterMaisonVilla(mv);
                              AppartementBureauDao abDao=new AppartementBureauDao();
                           AppartementBureau appartB=new AppartementBureau();
                           appartB.setUsage(sousCategorie.substring(0,3));
                              if(!"".equals(etage)){ 
                           appartB.setEtageAppartement(new BigDecimal(Integer.valueOf(etage)));}
                            appartB.setMaisonVilla(mv);
                                abDao.ajouterAppartement(appartB);
                            }
                         if(sousCategorie.equalsIgnoreCase("Maisons et Villas")){
                         
                      MaisonVillaDao mvdao=new MaisonVillaDao();
                          MaisonVilla mv=new MaisonVilla();
                           
                          mv.setEmplacementMaisonVilla(emplacement);
                             if(!"".equals(prixM2)){ 
                          mv.setPrixMetreCarreMaisonVilla(new BigDecimal(Integer.valueOf(prixM2)));}
                               else{
                                        mv.setPrixMetreCarreMaisonVilla(BigDecimal.ZERO);
                                   }
                                if(!"".equals(nbPieces)){ 
                        mv.setNbPiecesMaisonVilla(new BigDecimal(Integer.valueOf(nbPieces))); }
                                   if(!"".equals(superficie)){ 
                             mv.setSuperficieMaisonVilla(new BigDecimal(Integer.valueOf(superficie))); }
                                   else{
                                        mv.setSuperficieMaisonVilla(BigDecimal.ZERO);
                                   }
                                   
                             mv.setAnnonce(a);
                             mvdao.ajouterMaisonVilla(mv);
                         
                         }
                         
                         
                         if((sousCategorie.equalsIgnoreCase("Autre Immobilier"))||
                           (sousCategorie.equalsIgnoreCase("Terrains et Fermes"))){
                         
                             AutreImmobilierDao autDao=new AutreImmobilierDao();
                             AutreImmobilier aut=new AutreImmobilier();
                             aut.setAnnonce(a);
                              aut.setEmplacementAutreImmobilier(emplacement);
                              if(!"".equals(superficie)){ 
                              aut.setSuperficieAutreImmobilier(new BigDecimal(Integer.valueOf(superficie)));}
                              else{
                                        aut.setSuperficieAutreImmobilier(BigDecimal.ZERO);
                                   }
                                 if(!"".equals(prixM2)){ 
                                aut.setPrixMcAutreImmobilier(new BigDecimal(Integer.valueOf(prixM2)));}
                                   else{
                                        aut.setPrixMcAutreImmobilier(BigDecimal.ZERO);
                                   }
                             autDao.ajouterAutreImmobilier(aut);
                         
                         
                         
                         if (sousCategorie.equalsIgnoreCase("Terrains et Fermes")){
                             TerrainFermeDao tDao=new TerrainFermeDao();
                             TerrainFerme t=new TerrainFerme();
                               t.setAutreImmobilier(aut);
                            t.setZoningTerrainFerme(zone);
                             tDao.ajouterTerrainFerme(t);
                         }
                         
                         
                         }  
		        
                         
                         if(mode.equals("modif")){
                               
           
                             RequestContext.getCurrentInstance().execute("$(\"#panfeedbakM\").show()");
                             RequestContext.getCurrentInstance().execute("$(\"#divMod\").hide()");
                             RequestContext.getCurrentInstance().execute("$(\"#panM\").hide()");
                    }else if(mode.equals("pub")){ 
                       
                             
      
                                  RequestContext.getCurrentInstance().execute("$(\"#panfeedbakP\").show()");
                             RequestContext.getCurrentInstance().execute("$(\"#divPub\").hide()");
                         }
                            
                     
		  
    }
		    
		    
      return idnewAnn;
     
       }

  
 public void supprimerAnnonce(String w) throws IOException{
      Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       AnnonceDao  aDao=new AnnonceDao();
     String s=params.get("idAnnToSupr");
       BigDecimal idAnnToSupr= new BigDecimal(s.trim());
       //recuperer la raison de suppression 
       Annonce a=aDao.getByIdAllEtat(idAnnToSupr);
         a.setEtatAnnonce("supprime");  
       aDao.modifierAnnonce(a);
  enregistrerActionUser("Suppression",a,"_");
       remplirListeAnnFav();
       if(w.equals("myann")){
            reponse="Annonce supprimée avec succeé";
            RequestContext.getCurrentInstance().update("MyAnnonces");   
       }
      if(w.equals("edit")){
           reponse="Annonce supprimée avec succeé";
        FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+s);
         
                                  
       }
          
          
 }
      
      public void desactiverAnnonce(String w) throws IOException{
           Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
      
      String s=params.get("idAnnToDesac");
       BigDecimal idAnnToDesac= new BigDecimal(s.trim());
       //recuperer la raison   
         AnnonceDao  aDao=new AnnonceDao();
       Annonce a=aDao.getByIdAllEtat(idAnnToDesac);
        
        a.setCheminFirst(a.getCheminFirst());
       a.setEtatAnnonce("desactive"); 
       aDao.modifierAnnonce(a);
  
       remplirListeAnnFav();  
       if(w.equals("myann")){
            RequestContext.getCurrentInstance().update("MyAnnonces");   
       }
      if(w.equals("edit")){
          reponse="Annonce désactivée avec succeé";
       FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+s);
         
       
      }
          enregistrerActionUser("desactivation",a,"_");
          
          
 }
      
      
      
     
        public void activerAnnonce(){
           Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       String s=params.get("idAnnToActiv"); 
       BigDecimal idAnnToActiv= new BigDecimal(s.trim());
       //recuperer la raison   
         AnnonceDao  aDao=new AnnonceDao();
       Annonce a=aDao.getByIdAllEtat(idAnnToActiv);
          a.setEtatAnnonce("publie");  
          aDao.modifierAnnonce(a);
     
       remplirListeAnnFav();
       enregistrerActionUser("activation",a,"_"); 
         RequestContext.getCurrentInstance().update("MyAnnonces");   
       
 }
     public void remplirListeAnnFav(){ 
      HttpSession session= Util.getSession();
        UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user"); 
         
        if(u!=null){
          AnnonceDao  aDao=new AnnonceDao();
          
         listMyAnnAct=aDao.AnnoncesByProp(u,"publie");
     ListMyAnnDesac=aDao.AnnoncesByProp(u,"desactive");
         ListMyAnnRejet=aDao.AnnoncesByProp(u,"rejete");
         ListMyAnnModer=aDao.AnnoncesByProp(u,"en_cours");
          
          FavorisDao fvDao=new FavorisDao();
        ListFav=fvDao.FavorisbYuser(u);} 
       
    }
     
  
  public void addFavorite(){ 
       HttpSession session= Util.getSession();
   UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user");
       Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        AnnonceDao  aDao=new AnnonceDao();
     BigDecimal idan= new BigDecimal(params.get("idToFav").trim()); 
      Annonce a=aDao.getByIdPub(idan);
        String date= LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
           String heure=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
           FavorisId favId=new FavorisId();
           favId.setDateFavoris(date);
           favId.setHeureFavoris(heure);
           favId.setIdAnnonce(a.getIdAnnonce());
           favId.setIdUtilisateurConnecte(u.getIdUtilisateurConnecte());
          Favoris fav=new Favoris();
           fav.setId(favId);
          fav.setAnnonce(a);
          fav.setUtilisateurConnecte(u);
           FavorisDao fvDao=new FavorisDao();
          fvDao.ajouterFavoris(fav);
       annonceToEdit.setFav(true);
         int nbFav=fvDao.nbSigneFav(a);
         annonceToEdit.setNbFav(nbFav);
           RequestContext.getCurrentInstance().update("pFav"); 
           
           
      
      
  }
 
   public void removeFavorite(){
        Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       HttpSession session= Util.getSession();
      UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user"); 
       BigDecimal idan= new BigDecimal(params.get("idToFav").trim()); 
       AnnonceDao  aDao=new AnnonceDao();
      Annonce a=aDao.getByIdPub(idan); 
       FavorisDao fvDao=new FavorisDao();
          fvDao.supprimerByUser_ANNONCE(u,a);
           annonceToEdit.setFav(false); 
         int nbFav=fvDao.nbSigneFav(a);
         annonceToEdit.setNbFav(nbFav);
         
           RequestContext.getCurrentInstance().update("pFav"); 
      
  }
  
    public void removeFavoriteList(){
         Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
          HttpSession session= Util.getSession();
      UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user");
       BigDecimal idan= new BigDecimal(params.get("idAnnfav").trim()); 
         AnnonceDao  aDao=new AnnonceDao();
       Annonce a=aDao.getByIdPub(idan);  
        FavorisDao fvDao=new FavorisDao();
          fvDao.supprimerByUser_ANNONCE(u,a);
          remplirListeAnnFav();
          RequestContext.getCurrentInstance().update("listMfav"); 
      
  }   
   
    public void signaler() throws IOException{
        String dateRec= LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
           String heureRec=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE));
            Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
              AnnonceDao  aDao=new AnnonceDao();
       HttpSession session= Util.getSession();
        String id=params.get("idAnnToReclam");
      Annonce a=aDao.getByIdPub(new BigDecimal(id.trim())); 
        UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user");
        Reclamation r=new Reclamation();
        ReclamationId rId=new ReclamationId();
        rId.setDateReclamation(dateRec);
         rId.setHeureReclamation(heureRec);
          rId.setIdAnnonce(a.getIdAnnonce());
           rId.setIdUtilisateurConnecte(u.getIdUtilisateurConnecte());
        r.setId(rId);
        r.setContenuReclamation(messageReclamation);
        r.setAnnonce(a);
        r.setMotifReclamation(motifReclamation);
        r.setEtatReclamation("en cours");
        r.setUtilisateurConnecte(u);
         ReclamationDao  recDa=new ReclamationDao(); 
        recDa.ajouterReclamation(r); 
         messageReclamation="";
    motifReclamation="";
      RequestContext.getCurrentInstance().execute("PF('dialogReclam').hide()"); 
      FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+id);
         
       
   }
              public void  annulerRec() throws IOException{
                   Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
             String id=params.get("idAnnToReclam");
            RequestContext.getCurrentInstance().execute("PF('dialogReclam').hide()"); 
      FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+id);
         
       
     }
    public void annoncePrecedente() throws IOException{
    Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        remplirListeAnnFav();
        String id= params.get("idAnnToEdit");
       BigDecimal idbd=new BigDecimal(id); 
        BigDecimal idP=null;
        
        
        for(int i=1;i<=l.size();i++){
            
            if(l.get(i).getIdAnnonce().equals(idbd)){
                  idP=l.get(i-1).getIdAnnonce();
                       
                  break;
            }}
       
          if(idP!=null){
             recupererAnnonce(idP);
      
       FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+idP);
          }
    }
      public void annonceSuivante() throws IOException{ 
         Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        remplirListeAnnFav();
        String id= params.get("idAnnToEdit"); 
       BigDecimal idbd=new BigDecimal(id); 
        BigDecimal idSuiv=null;
        
        
        for(int i=0;i<l.size();i++){
            
            if(l.get(i).getIdAnnonce().equals(idbd)){
                  idSuiv=l.get(i+1).getIdAnnonce();
                       
                  break;
            }}
       
          if(idSuiv!=null){ 
             recupererAnnonce(idSuiv);
       
      FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/EditAnnonce/"+idSuiv);
         
          }    
          
      
      
    }
   
     public void onload() throws IOException {
            AnnonceDao anDao=new AnnonceDao();
         l  =anDao.getAnnoncesByEtat("publie");
            
         btnSuivPrec();
         
       recupererAnnonce(idAnnonce);
      }

     public void   btnSuivPrec(){
         /* Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
           String id= params.get("idAnnToEdit");*/
         if(!l.isEmpty()){
           if(idAnnonce!=null){
               
           BigDecimal idbd=idAnnonce;
         BigDecimal a=l.get(l.size()-1).getIdAnnonce();
         BigDecimal b=l.get(0).getIdAnnonce();
         
         if(a.equals(idbd)){
             btnSuiv="ko";
         }else{
             btnSuiv="ok";
         }
        if(b.equals(idbd)){
             btnPrec="ko";
         }else{
             btnPrec="ok";
         }}}
         
     }
     
      
     public void recupererAnnonce(BigDecimal idbd) throws IOException{
        
       remplirListeAnnFav();
         AnnonceDao  aDao=new AnnonceDao();
        annonceToEdit=aDao.getByIdPub(idbd);
       
        if(annonceToEdit!=null){
        
          String dateN= LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String date=annonceToEdit.getDateDepotAnnonce();
          int da=Integer.valueOf(annonceToEdit.getDateDepotAnnonce().substring(0,2));
                int ma=Integer.valueOf(annonceToEdit.getDateDepotAnnonce().substring(3,5));
                int ya=Integer.valueOf(annonceToEdit.getDateDepotAnnonce().substring(6,8));
                
                 int dnow=Integer.valueOf(dateN.substring(0,2));
                int mnow=Integer.valueOf(dateN.substring(3,5));
                int ynow=Integer.valueOf(dateN.substring(6,8));
        
                if(ynow==ya&&mnow==ma){
                    if(da==(dnow-1)){
                        date="hier";
                    }
                    else if(da==dnow){
                        date="Aujourd'hui";
                    }
                    else{
                        date=annonceToEdit.getDateDepotAnnonce();
                    }
                }
                dateBean=date;
        
          annonceToEdit.setFav(false);
     
         for (Favoris favoris :ListFav) {
            if(favoris.getAnnonce().getIdAnnonce().equals(idbd)){
                  annonceToEdit.setFav(true);
                 break;
             
        }}
         BigDecimal nbVue=annonceToEdit.getNbVueAnnonce();
        BigDecimal nbvNew=nbVue.add(BigDecimal.ONE);
      ptoUpdat=getChemins(idbd);
        annonceToEdit.setNbVueAnnonce(nbvNew); 
         FavorisDao fvDao=new FavorisDao();
        int nbFav=fvDao.nbSigneFav(annonceToEdit);
        annonceToEdit.setNbFav(nbFav);
          
        aDao.modifierNbVueAnnonce(annonceToEdit.getIdAnnonce(),nbvNew);
        } 
        else{
            Annonce a=aDao.getByIdAllEtat(idbd);
            String etat=a.getEtatAnnonce();
           switch (etat.charAt(0)) {
               case 's':
                   reponse="annonce suppriméé  ";
                   break;
               case 'd':
                   reponse="annonce désactivéé !!";
                   break;
               default:
                   reponse="annonce introuvable !!";
                   break;
           }
        }
    }
    
      public void supprimerPhToUpdate(String a){
        if(a.equals("0")){
            ptoUpdat.remove(0); 
        }
        if(a.equals("1")){
            ptoUpdat.remove(1); 
        }
        if(a.equals("2")){
            ptoUpdat.remove(2); 
        }
        if(a.equals("3")){
            ptoUpdat.remove(3); 
        }
        if(a.equals("4")){
            ptoUpdat.remove(4); 
        }
        
       
       
   }
   BigDecimal idAnnonce;

    public BigDecimal getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(BigDecimal idAnnonce) {
        this.idAnnonce = idAnnonce;
    }
   
//modification
  public void afficheAnn() throws FileNotFoundException, IOException{
 
        viderShamps();
  /*Map<String,String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
           String id=params.get("idAnnToUpd"); */
           BigDecimal idan=idAnnonce;
             
       if(idan!=null){
       AnnonceDao  aDao=new AnnonceDao();
      
    annonceToUpdate=aDao.getByIdPub(idan); 
   
     if(annonceToUpdate!=null){
         
    idAnnonce=annonceToUpdate.getIdAnnonce();
         
     categorie=annonceToUpdate.getCategorie();  
      onCategorieChange();
      sousCategorie=annonceToUpdate.getSouscategorie();
     typeAnnonce=annonceToUpdate.getTypeAnnonce(); 
      ville=annonceToUpdate.getVilleAnnonce();  
      titre=annonceToUpdate.getTitreAnnonce();  
     description=annonceToUpdate.getDescriptionAnnonce();   
     prix=String.valueOf(annonceToUpdate.getPrixAnnonce());  
     
      ptoUpdat=getChemins(idan); 
     if(sousCategorie.equalsIgnoreCase("Voitures")){
          VoitureDao voitdao=new VoitureDao();
            Voiture v=voitdao.VoitureByANN(annonceToUpdate); 
          if(v!=null){
           marque=v.getMarqueVoiture();
            onMarqueChange();
     modele=v.getModeleVoiture();
     typeCarburant=v.getTypeCarburantVoiture();
      anneMOdel=String.valueOf(v.getAnneeModeleVoiture()); 
      kilomtrage=String.valueOf(v.getKilometrage());
      puissanceFiscale=v.getPuissanceFiscaleVoiture();
     
          } else{
                modele="";
     typeCarburant="";
      anneMOdel=""; 
      kilomtrage="";
      puissanceFiscale="";
     
          }}
      if(sousCategorie.equalsIgnoreCase("Motos")){
                                   MotoDao mtdao=new MotoDao();
              Moto m=mtdao.MotoByAnn(annonceToUpdate);
               if(m!=null){
           modele= String.valueOf(m.getAnneModelMoto());
             kilomtrage= String.valueOf(m.getKilometrageMoto()); 
             
               }else{
                    modele="";
             kilomtrage=""; 
               }}
     
      if(sousCategorie.equalsIgnoreCase("Véhicules Professionnels")){
           VoitureDao voitdao=new VoitureDao();
              Voiture v=voitdao.VoitureByANN(annonceToUpdate); 
          if(v!=null){ 
              marque=v.getMarqueVoiture();
     modele=v.getModeleVoiture();
     typeCarburant=v.getTypeCarburantVoiture();
      anneMOdel=String.valueOf(v.getAnneeModeleVoiture()); 
      kilomtrage=String.valueOf(v.getKilometrage());
      puissanceFiscale=v.getPuissanceFiscaleVoiture();
          }else{
                marque="";
     modele="";
     typeCarburant="";
      anneMOdel=""; 
      kilomtrage="";
      puissanceFiscale="";
          }
       VehiculeProfessionnelDao vhprodao=new VehiculeProfessionnelDao();
       
          VehiculeProfessionnel vpro=vhprodao.VproByVoiture(v);
           if(vpro!=null){
                          
                    typeVehiculePro  =vpro.getSousCatVehiculeProf();
                              
                        } else{
                typeVehiculePro ="";
                           
           }
                 }   
                         if(sousCategorie.equalsIgnoreCase("Appartements")||(sousCategorie.equalsIgnoreCase("Bureaux et Plateaux")) ||(sousCategorie.equalsIgnoreCase("Magasins, Commerces et Locaux industriels"))||
                           (sousCategorie.equalsIgnoreCase("Terrains et Fermes"))||
                          (sousCategorie.equalsIgnoreCase("Locations de vacances"))||
                          (sousCategorie.equalsIgnoreCase("Colocations"))
                                  
                                  
                                  ){
                            MaisonVillaDao  mvDao=new MaisonVillaDao();
       
       
                          MaisonVilla mv=mvDao.MaisonVillaByAnn(annonceToUpdate);
                            if(mv!=null){
                         emplacement=mv.getEmplacementMaisonVilla();
                          
                         prixM2=String.valueOf(mv.getPrixMetreCarreMaisonVilla());
                      nbPieces=  String.valueOf(mv.getNbPiecesMaisonVilla()); 
                            superficie=String.valueOf(mv.getSuperficieMaisonVilla()); 
                            
                            
                             AppartementBureauDao abDao=new AppartementBureauDao();
                        
                           AppartementBureau appartB=abDao.AppartementBureuByMV(mv);
                            if(appartB!=null){
                                etage=String.valueOf(appartB.getEtageAppartement());
                            }
                            else{
                                etage="";
                            }
                            }
                            else{
                                  emplacement="";
                          
                         prixM2="";
                      nbPieces=""; 
                            superficie=""; 
                            
                            }
                         
                         }             
                         
                         if(sousCategorie.equalsIgnoreCase("Maisons et Villas")){
                          MaisonVillaDao  mvDao=new MaisonVillaDao();
       
       
                          MaisonVilla mv=mvDao.MaisonVillaByAnn(annonceToUpdate);
                            if(mv!=null){
                              emplacement=mv.getEmplacementMaisonVilla();
                          nbPieces=String.valueOf( mv.getNbPiecesMaisonVilla()); 
                          prixM2=String.valueOf(mv.getPrixMetreCarreMaisonVilla()); 
                            superficie=String.valueOf(mv.getSuperficieMaisonVilla()); 
                            }
                               else{
                                  emplacement="";
                          prixM2="";
                      nbPieces=""; 
                            superficie=""; 
                            
                            }
                         }
                         
     
     
      
                         if((sousCategorie.equalsIgnoreCase("Autre Immobilier"))||
                           (sousCategorie.equalsIgnoreCase("Terrains et Fermes"))){
                           AutreImmobilierDao autDao=new AutreImmobilierDao();
                             AutreImmobilier aut=autDao.AutreImmoByAnn(annonceToUpdate);
                          if(aut!=null){
                            emplacement= aut.getEmplacementAutreImmobilier();
                              superficie=String.valueOf(aut.getSuperficieAutreImmobilier());
                                prixM2=String.valueOf(aut.getPrixMcAutreImmobilier());
                          }   else{
                                  emplacement="";
                          prixM2=""; 
                            superficie=""; 
                            
                            }
                         
                         if (sousCategorie.equalsIgnoreCase("Terrains et Fermes")){
                              TerrainFermeDao tfDao=new TerrainFermeDao();
                             TerrainFerme t=tfDao.TerrainByAutreImmo(aut);
                         if(aut!=null){    zone=t.getZoningTerrainFerme();}
                             
                         }else{
                             zone="";
                         }
                         
                         
                         }   
     }    
     else{
         
                                  RequestContext.getCurrentInstance().execute("$(\"#panM\").show()");
     }
           }
           
           else{
            
           }  
  }
  public void redirectModif() throws IOException{
       FacesContext.getCurrentInstance().getExternalContext().redirect("/BayloAnnonce_V2/Modifier_annonce/"+idAnnonce);
        
  }
          
 public List<String>  getChemins(BigDecimal id) {
       PhotoDao PDA=new PhotoDao();
       List<Photo> list_phpto=PDA.getPhotosByIdAnn(id);
         List<String> F=new ArrayList<>(); 
        if(!list_phpto.isEmpty()){
            for (Photo e : list_phpto) {
                  String[] c=e.getCheminPhoto().split("/");
                String   chemin=c[c.length-1]; 
        F.add(chemin);
             }
            
        }
        
        return F;  
         }
      public void modifierAnnonce() throws IOException, SQLException{
         
          
         BigDecimal id=publier("modif");
          if(id!=null){
            AnnonceDao and=new AnnonceDao();
            Annonce annoLD= and.getByIdPub(idAnnonce);
       
          Annonce annNew= and.getByIdAllEtat(id);
         
             ModificationDao mdfao=new ModificationDao();
             Modification md=new Modification();
             md.setAnnonceOld(annoLD);
             md.setDateModification(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
             md.setHeureModification(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE)));
             md.setAnnonceNew(annNew);
             mdfao.ajouterModification(md);
             
         enregistrerActionUser("modification",annoLD,"_");
          
         
         
          annoLD.setEtatAnnonce("en_modif");
         and.modifierAnnonce(annoLD);
                        RequestContext.getCurrentInstance().execute("$(\"#panfeedbakM\").show()");
                 
        } 
     
      
 } 
  
      
      public void enregistrerActionUser(String action,Annonce a,String motif){
          HttpSession session= Util.getSession();
           UtilisateurConnecteDao ud=new UtilisateurConnecteDao();
           
          UtilisateurConnecte u=(UtilisateurConnecte) session.getAttribute("user");
          ActionUserDao daoAu=new ActionUserDao();
          
          ActionUserId idactU=new ActionUserId();
             idactU.setDateActionUser(String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
             idactU.setHeureActionUser(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.FRANCE)));
             idactU.setIdAnnonce(a.getIdAnnonce());
              idactU.setIdUser(u.getIdUtilisateurConnecte());
             
          ActionUser au=new ActionUser();
          au.setId(idactU);
          au.setAnnonce(a);
          au.setLibelleActionUser(action);
            au.setMotifActionUser(motif);
          au.setUtilisateurConnecte(u);
           daoAu.ajouterActionUser(au);
          
      }
      
      
      
      
     
              
 
    
 public void viderShamps(){
     //parceque managedbean est sessionscopde=> prob photo
 marque="";
 modele ="";
 typeVehiculePro ="";
  typeCarburant ="";
   anneMOdel ="";
    kilomtrage ="";
       puissanceFiscale="";
     etage="";
      nbPieces ="";
     emplacement  ="";
   zone  ="";
     superficie ="";
       prixM2  ="";
        titre=""; 
        description ="";
        prix="";
       categorie ="";
       sousCategorie="";
      ville="";
     file=null;
    streamed=null;
     streamed1=null;
    streamed2=null;
     streamed3=null;
     streamed4=null; 
    listphoto=new UploadedFile[5] ;
    messageReclamation="";
        motifReclamation="";
        dateBean="";
        reponse=""; 
         ptoUpdat=new ArrayList<>() ;
       annonceToEdit=null; 
}
  
}

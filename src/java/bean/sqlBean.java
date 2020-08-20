/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ActionEmpDao;
import dao.AnnonceDao;
import dao.EmployeDao;
import java.io.Serializable;
import java.text.ParseException; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List; 
import javax.enterprise.context.SessionScoped;
import javax.inject.Named; 
import model.Employe;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ibm
 */

@Named
@SessionScoped

public class sqlBean   implements Serializable{
    private String dateStat1;
    private String dateStat2;
    private String dateAver1;
    private String dateAver2;
    private String resultaAverage;
    private List<Employe> list_emp=null;

    public sqlBean() {
       dateStat1= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"00:00:00";
         dateStat2= LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"00:00:00";
      dateAver1= LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"00:00:00";
         dateAver2= LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"00:00:00";
    }

    
    public String getDateStat1() {
        return dateStat1;
    }

    public void setDateStat1(String dateStat1) {
        this.dateStat1 = dateStat1;
    }

    public String getDateStat2() {
        return dateStat2;
    }

    public void setDateStat2(String dateStat2) {
        this.dateStat2 = dateStat2;
    }

    public String getDateAver1() {
        return dateAver1;
    }

    public void setDateAver1(String dateAver1) {
        this.dateAver1 = dateAver1;
    }

    public List<Employe> getList_emp() {
        return list_emp;
    }

    public void setList_emp(List<Employe> list_emp) {
        this.list_emp = list_emp;
    }

    public String getDateAver2() {
        return dateAver2;
    }

    public void setDateAver2(String dateAver2) {
        this.dateAver2 = dateAver2;
    }

    public String getResultaAverage() {
        return resultaAverage;
    }

    public void setResultaAverage(String resultaAverage) {
        this.resultaAverage = resultaAverage;
    }
   public void recupAverrage() throws ParseException{
       AnnonceDao andao=new AnnonceDao();
        resultaAverage=andao.calculerAverageTime(dateAver1,dateAver2);
          RequestContext.getCurrentInstance().update("labResultat"); 
   }
    
   public void recupStatistiques() throws ParseException{
       list_emp=new ArrayList<>();
       EmployeDao empdao=new EmployeDao();
       List<Employe> list=empdao.getEmployes();
         ActionEmpDao adao=new ActionEmpDao();
         for (Employe employe : list) {
            int nbAcc=adao.ActionEmpsByEmployeBetwenF_T(employe,dateStat1,dateStat2,"accepte");
              int nbRefu=adao.ActionEmpsByEmployeBetwenF_T(employe,dateStat1,dateStat2,"refuse");
                employe.setNbAnAccepted(nbAcc);
                 employe.setNbAnRefused(nbRefu);
                 list_emp.add(employe);
       }
       Collections.sort(list_emp,new Comparator<Employe>(){
           @Override
           public int compare(Employe e1, Employe e2) {
          return Integer.compare(e2.getNbAnAccepted(),e1.getNbAnAccepted()); }
       });
       RequestContext.getCurrentInstance().execute("$('#pnAverage').hide()");
       RequestContext.getCurrentInstance().execute("$('#pnStatistique').show()"); 
      RequestContext.getCurrentInstance().update("pnStatistique");
   }
}

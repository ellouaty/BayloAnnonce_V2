package model;
// Generated 7 janv. 2020 21:01:58 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.sql.Blob;

/**
 * Photo generated by hbm2java
 */
public class Photo  implements java.io.Serializable {


     private BigDecimal idPhoto;
     private Annonce annonce;
     private String cheminPhoto;
     private Blob photoContent;

    public Photo() {
    }

    public Photo(Annonce annonce, String cheminPhoto, Blob photoContent) {
       this.annonce = annonce;
       this.cheminPhoto = cheminPhoto;
       this.photoContent = photoContent;
    }
   
    public BigDecimal getIdPhoto() {
        return this.idPhoto;
    }
    
    public void setIdPhoto(BigDecimal idPhoto) {
        this.idPhoto = idPhoto;
    }
    public Annonce getAnnonce() {
        return this.annonce;
    }
    
    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
    public String getCheminPhoto() {
        return this.cheminPhoto;
    }
    
    public void setCheminPhoto(String cheminPhoto) {
        this.cheminPhoto = cheminPhoto;
    }
    public Blob getPhotoContent() {
        return this.photoContent;
    }
    
    public void setPhotoContent(Blob photoContent) {
        this.photoContent = photoContent;
    }




}



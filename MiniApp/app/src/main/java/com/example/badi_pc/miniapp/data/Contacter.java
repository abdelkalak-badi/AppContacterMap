package com.example.badi_pc.miniapp.data;

import java.io.Serializable;

public class Contacter implements Serializable {
    public  String ID;
    public  String Nom;
    public  String Prenom;
    public  String Img;
    public  String Email;
    public  String Phone;
    public  String SiteWeb;
    public  String MapLongitude;
    public  String MapLatitude;

    public Contacter() {
    }

    public Contacter(String ID, String nom, String prenom,String img, String email, String phone, String siteWeb, String mapLongitude, String mapLatitude) {
        this.ID = ID;
        Nom = nom;
        Prenom = prenom;
        Img=img;
        Email = email;
        Phone = phone;
        SiteWeb = siteWeb;
        MapLongitude = mapLongitude;
        MapLatitude = mapLatitude;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }
    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getSiteWeb() {
        return SiteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        SiteWeb = siteWeb;
    }

    public String getMapLongitude() {
        return MapLongitude;
    }

    public void setMapLongitude(String mapLongitude) {
        MapLongitude = mapLongitude;
    }

    public String getMapLatitude() {
        return MapLatitude;
    }

    public void setMapLatitude(String mapLatitude) {
        MapLatitude = mapLatitude;
    }
}

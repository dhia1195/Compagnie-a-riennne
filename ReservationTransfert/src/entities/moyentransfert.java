/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author firas
 */
public class moyentransfert {
        private int id_transfert ;  
    private String model ;
    private int prix ;
    private String type ;
    private int cautionnement;
    private Date date_depart;
    private Date date_retour;

    public moyentransfert() {
    }

    public moyentransfert(int id_transfert, String model, int prix, Date disponibilite, String type, int cautionnement) {
        this.id_transfert = id_transfert;
        this.model = model;
        this.prix = prix;
        this.type = type;
        this.cautionnement = cautionnement;
        this.date_depart= date_depart;
        this.date_retour= date_retour;
    }

    public moyentransfert(String model, int prix, Date disponibilite, String type, int cautionnement) {
        this.model = model;
        this.prix = prix;
        this.type = type;
        this.cautionnement = cautionnement;
        this.date_depart= date_depart;
        this.date_retour= date_retour;
    }

    public moyentransfert(String model, int prix, String type, int cautionnement, Date date_depart, Date date_retour) {
        this.model = model;
        this.prix = prix;
        this.type = type;
        this.cautionnement = cautionnement;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
    }

    public moyentransfert(int id_transfert, String model, int prix, String type, int cautionnement, Date date_depart, Date date_retour) {
        this.id_transfert = id_transfert;
        this.model = model;
        this.prix = prix;
        this.type = type;
        this.cautionnement = cautionnement;
        this.date_depart = date_depart;
        this.date_retour = date_retour;
    }

    public int getId_transfert() {
        return id_transfert;
    }

    public void setId_transfert(int id_transfert) {
        this.id_transfert = id_transfert;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCautionnement() {
        return cautionnement;
    }

    public void setCautionnement(int cautionnement) {
        this.cautionnement = cautionnement;
    }
    public Date getDate_depart() {
        return date_depart;
    }
    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    @Override
    public String toString() {
        return "moyentransfer{" + "id_transfert=" + id_transfert + ", model=" + model + ", prix=" + prix + ",  type=" + type + ", cautionnement=" + cautionnement + ",  date_depart=" + date_depart + ", date_retour=" + date_retour + '}';
    }
    
}

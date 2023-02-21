
package entities;

import java.sql.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author firas
 */
public class ReservationTransfert {
    private int id_reservationt ;  
    private Date date ;
    private int prix ;
    private int nb_jours ;
    private int id_MT ;

    public ReservationTransfert(SimpleDateFormat simpleDateFormat, int prix, int nbJours, int idMt) {
    }

    public ReservationTransfert(int id_reservationt, Date date, int prix, int nb_jours, int id_MT) {
        this.id_reservationt = id_reservationt;
        this.date = date;
        this.prix = prix;
        this.nb_jours = nb_jours;
        this.id_MT = id_MT;
    }

    public ReservationTransfert(Date date, int prix, int nb_jours, int id_MT) {
        this.date = date;
        this.prix = prix;
        this.nb_jours = nb_jours;
        this.id_MT = id_MT;
    }

    public int getId_reservationt() {
        return id_reservationt;
    }

    public void setId_reservationt(int id_reservationt) {
        this.id_reservationt = id_reservationt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNb_jours() {
        return nb_jours;
    }

    public void setNb_jours(int nb_jours) {
        this.nb_jours = nb_jours;
    }

    public int getId_MT() {
        return id_MT;
    }

    public void setId_MT(int id_MT) {
        this.id_MT = id_MT;
    }

    @Override
    public String toString() {
        return "ReservationTransfer{" + "id_reservationt=" + id_reservationt + ", date=" + date + ", prix=" + prix + ", nb_jours=" + nb_jours + ", id_MT=" + id_MT + '}';
    }


}



    
    
    

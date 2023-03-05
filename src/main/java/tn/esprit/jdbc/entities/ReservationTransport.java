package tn.esprit.jdbc.entities;

import java.util.Date;
/**
 *
 * @author azizi
 */
public class ReservationTransport {

    private int id_reservationt ;
    private int id_chauffeur;
    private int id_moyent;
    private Date date_debut;
    private Date date_fin;
    private String destination;
    private boolean statut_chauffeur;

    public ReservationTransport(int id_reservationt, int id_chauffeur, int id_moyent, Date date_debut, Date date_fin, String destination, boolean statut_chauffeur) {
        this.id_reservationt = id_reservationt;
        this.id_chauffeur = id_chauffeur;
        this.id_moyent = id_moyent;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.destination = destination;
        this.statut_chauffeur = statut_chauffeur;
    }

    public int getId_reservationt() {
        return id_reservationt;
    }

    public void setId_reservationt(int id_reservationt) {
        this.id_reservationt = id_reservationt;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public int getId_moyent() {
        return id_moyent;
    }

    public void setId_moyent(int id_moyent) {
        this.id_moyent = id_moyent;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isStatut_chauffeur() {
        return statut_chauffeur;
    }

    public void setStatut_chauffeur(boolean statut_chauffeur) {
        this.statut_chauffeur = statut_chauffeur;
    }

    @Override
    public String toString() {
        return "ReservationTransport{" + "id_reservationt=" + id_reservationt + ", id_chauffeur=" + id_chauffeur + ", id_moyent=" + id_moyent + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", destination=" + destination + ", statut_chauffeur=" + statut_chauffeur + '}';
    }

    public ReservationTransport() {
    }


}

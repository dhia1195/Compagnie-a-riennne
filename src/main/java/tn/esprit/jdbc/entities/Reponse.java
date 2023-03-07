package tn.esprit.jdbc.entities;

import java.util.Date;
import java.util.Objects;

public class Reponse {
    private int id_reponse;
    private int id_reclamation;
    private Date dateR;
    private String reponse;

    public Reponse() {

    }

    public Reponse(int id_reponse, int id_reclamation, Date dateR, String reponse) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.dateR = dateR;
        this.reponse = reponse;
    }
    public Reponse( int id_reclamation, Date dateR, String reponse) {
        this.id_reponse = id_reponse;
        this.id_reclamation = id_reclamation;
        this.dateR = dateR;
        this.reponse = reponse;
    }



    public int getId_reponse() {
        return id_reponse;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public Date getDateR() {
        return dateR;
    }

    public String getReponse() {
        return reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reponse)) return false;
        Reponse reponse1 = (Reponse) o;
        return getId_reponse() == reponse1.getId_reponse() && getId_reclamation() == reponse1.getId_reclamation() && getDateR().equals(reponse1.getDateR()) && getReponse().equals(reponse1.getReponse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_reponse(), getId_reclamation(), getDateR(), getReponse());
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id_reponse=" + id_reponse +
                ", id_reclamation=" + id_reclamation +
                ", dateR='" + dateR + '\'' +
                ", reponse='" + reponse + '\'' +
                '}';
    }
}

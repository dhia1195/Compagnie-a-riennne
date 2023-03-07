package tn.esprit.jdbc.entities;

import java.util.Date;
import java.util.Objects;

public class Reclamation {
    private int id_reclamation;
    private int id_user;
    private String type_reclamation;
    private Date date;
    private String description;
    public Reclamation() {
    }
    public Reclamation(int id_reclamation, int id_user, String type_reclamation, Date date, String description) {
        this.id_reclamation = id_reclamation;
        this.id_user = id_user;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.description = description;
    }

    public Reclamation(int id_user, String type_reclamation, Date date, String description) {

        this.id_user = id_user;
        this.type_reclamation = type_reclamation;
        this.date = date;
        this.description = description;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public int getId_user() {
        return id_user;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reclamation)) return false;
        Reclamation that = (Reclamation) o;
        return getId_reclamation() == that.getId_reclamation() && getId_user() == that.getId_user() && Objects.equals(getType_reclamation(), that.getType_reclamation()) && Objects.equals(getDate(), that.getDate()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_reclamation(), getId_user(), getType_reclamation(), getDate(), getDescription());
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id_reclamation=" + id_reclamation +
                ", id_user=" + id_user +
                ", type_reclamation='" + type_reclamation + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}

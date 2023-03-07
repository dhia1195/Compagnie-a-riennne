package tn.esprit.jdbc.entities;

import java.util.Objects;

public class Avion {

    private int ID;
    private String modele;
    private int capacite;


    public Avion(){}

    public Avion(int ID, String modele, int capacite) {
        this.ID = ID;
        this.modele = modele;
        this.capacite = capacite;
    }

    public Avion(String modele, int capacite) {
        this.modele = modele;
        this.capacite = capacite;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "ID=" + ID +
                ", model='" + modele + '\'' +
                ", capacite=" + capacite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avion avion = (Avion) o;
        return ID == avion.ID && capacite == avion.capacite && Objects.equals(modele, avion.modele);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, modele, capacite);
    }
}


package tn.esprit.jdbc.entities;

import java.util.Objects;

public class Siege {
    private int ID;
    private String typesiege;

    public Siege(){}
    public Siege(int ID, String typesiege) {
        this.ID = ID;
        this.typesiege = typesiege;}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTypesiege() {
        return typesiege;
    }

    public void setTypesiege(String typesiege) {
        this.typesiege = typesiege;
    }



    @Override
    public String toString() {
        return "Siege{" +
                "Numero=" + ID +
                ", typesiege='" + typesiege + '\'' +

                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Siege siege = (Siege) o;
        return ID == siege.ID && typesiege == siege.typesiege && Objects.equals(typesiege, siege.typesiege);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, typesiege);
    }
}







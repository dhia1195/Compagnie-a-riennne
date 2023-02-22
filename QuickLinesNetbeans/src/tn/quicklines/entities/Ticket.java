package tn.quicklines.entities;

import java.util.Objects;

public class Ticket {
    //private Vol volTicket;
    private static int compteurTicket=0;
    private int idTicket;
    private String codeTicket;
    private int volTicket;
    private int siegeTicket;
    private float prix;
    private int client;
    private String nomPassager;
    private String prenomPassager;
    private String agePassager;

    public Ticket() {
    }
    
    

    public Ticket(int volTicket, int siegeTicket, float prix) {
        this.volTicket = volTicket;
        this.siegeTicket = siegeTicket;
        this.codeTicket= String.valueOf(volTicket)+String.valueOf(siegeTicket);
        this.prix = prix;
        this.idTicket=compteurTicket;
        compteurTicket++;
        
    }

    public static int getCompteurTicket() {
        return compteurTicket;
    }
    
    

    public int getIdTicket() {
        return idTicket;
    }

        

    public String getCodeTicket() {
        return codeTicket;
    }

    public void setCodeTicket(String codeTicket) {
        this.codeTicket = codeTicket;
    }
    
    

    public int getVolTicket() {
        return volTicket;
    }

    public void setVolTicket(int volTicket) {
        this.volTicket = volTicket;
    }

    public int getSiegeTicket() {
        return siegeTicket;
    }

    public void setSiegeTicket(int siegeTicket) {
        this.siegeTicket = siegeTicket;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public String getNomPassager() {
        return nomPassager;
    }

    public void setNomPassager(String nomPassager) {
        this.nomPassager = nomPassager;
    }

    public String getPrenomPassager() {
        return prenomPassager;
    }

    public void setPrenomPassager(String prenomPassager) {
        this.prenomPassager = prenomPassager;
    }

    public String getAgePassager() {
        return agePassager;
    }

    public void setAgePassager(String agePassager) {
        this.agePassager = agePassager;
    }

    @Override
    public String toString() {
        return "Ticket{" + "volTicket=" + volTicket + ", siegeTicket=" + siegeTicket + ", prix=" + prix + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        return Objects.equals(this.idTicket, other.idTicket);
    }
    
    
    
    
    
}

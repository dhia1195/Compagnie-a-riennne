package tn.esprit.jdbc.entities;


public class MoyenTransport {
    private int id_moyent;
    private String photo;
    private String modele;
    private float prix_location_jour;
    private float frais_chauffeur;
    private String description;
    private String type;

    public MoyenTransport() {
    }

    public MoyenTransport(String photo, String modele, float prix_location_jour, float frais_chauffeur, String description, String type) {
        this.photo = photo;
        this.modele = modele;
        this.prix_location_jour = prix_location_jour;
        this.frais_chauffeur = frais_chauffeur;
        this.description = description;
        this.type = type;
    }


    public MoyenTransport(int id_moyent, String photo, String modele, float prix_location_jour, float frais_chauffeur, String description, String type) {
        this.id_moyent = id_moyent;
        this.photo = photo;
        this.modele = modele;
        this.prix_location_jour = prix_location_jour;
        this.frais_chauffeur = frais_chauffeur;
        this.description = description;
        this.type = type;
    }

    public int getId_moyent() {
        return id_moyent;
    }

    public void setId_moyent(int id_moyent) {
        this.id_moyent = id_moyent;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public float getPrix_location_jour() {
        return prix_location_jour;
    }

    public void setPrix_location_jour(float prix_location_jour) {
        this.prix_location_jour = prix_location_jour;
    }

    public float getFrais_chauffeur() {
        return frais_chauffeur;
    }

    public void setFrais_chauffeur(float frais_chauffeur) {
        this.frais_chauffeur = frais_chauffeur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MoyenTransport{" + "id_moyent=" + id_moyent + ", photo=" + photo + ", modele=" + modele + ", prix_location_jour=" + prix_location_jour + ", frais_chauffeur=" + frais_chauffeur + ", description=" + description + ", type=" + type + '}';
    }





}

package tn.esprit.jdbc.services;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.jdbc.entities.MoyenTransport;
import tn.esprit.jdbc.utils.MaConnexion;


/**
 *
 * @author firas
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author azizi
 */


public class MoyenTransportService implements IServicet<MoyenTransport> {

    private Connection cnx;

    public MoyenTransportService(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public List<MoyenTransport> afficheListe() {
        List<MoyenTransport> list = new ArrayList<>();
        try {
            String req = "Select * from  `moyentransport`";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                MoyenTransport p = new MoyenTransport();
                p.setId_moyent(RS.getInt("id_moyent"));
                p.setPrix_location_jour(RS.getFloat("prix_location_jour"));
                p.setModele(RS.getString("modele"));
                p.setType(RS.getString("type"));
                p.setFrais_chauffeur(RS.getFloat("frais_chauffeur"));
                p.setDescription(RS.getString("description"));
                p.setPhoto(RS.getString("photo"));


                list.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }





    @Override
    public void ajouter(MoyenTransport t)  {
        String requete="insert into moyentransport (prix_location_jour,modele,type,frais_chauffeur,description,photo) values (?,?,?,?,?,?)";

        try {
            PreparedStatement rec=cnx.prepareStatement(requete);
            rec.setFloat(1,t.getPrix_location_jour());
            rec.setString(2, t.getModele());
            rec.setString(3, t.getType());
            rec.setFloat(4, t.getFrais_chauffeur());
            rec.setString(5, t.getDescription());
            rec.setString(6, t.getPhoto());
            rec.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MoyenTransportService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimer(MoyenTransport p) {
        try {
            String req = "DELETE FROM `MoyenTransport` WHERE `id_moyent` = " + p.getId_moyent();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("MoyenTransport supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(MoyenTransport p) {
        try {

            String req = "UPDATE `MoyenTransport` SET `prix_location_jour`= ? ,`modele`=?, `type` = ?,`frais_chauffeur`=?,`description` = ?, `photo` = ? WHERE id_moyent= ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setFloat(1, p.getPrix_location_jour());
            ps.setString(2, p.getModele());
            ps.setString(3, p.getType());
            ps.setFloat(4, p.getFrais_chauffeur());
            ps.setString(5, p.getDescription());
            ps.setString(6, p.getPhoto());
            ps.setInt(7,p.getId_moyent());

            ps.executeUpdate();

            System.out.println("MoyenTransport mis a jour");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<MoyenTransport> afficherMoyenTransportsDisponibles(String modele,Date dateDebut, Date dateFin) {
        List<MoyenTransport> MoyenTransportsDisponibles = new ArrayList<>();
        try {
            String query = "SELECT * FROM moyentransport WHERE  `modele` = ?  AND id_moyent NOT IN " +
                    "(SELECT id_moyent FROM reservationtransport WHERE (date_debut <= ? AND date_fin >= ?))";

            PreparedStatement st = cnx.prepareStatement(query);

            st.setString(1, modele);




            st.setDate(2, dateDebut);
            st.setDate(3, dateFin);

            ResultSet RS = st.executeQuery();
            while (RS.next()) {
                MoyenTransport p = new MoyenTransport();

                p.setId_moyent(RS.getInt("id_moyent"));
                p.setPrix_location_jour(RS.getFloat("prix_location_jour"));
                p.setModele(RS.getString("modele"));
                p.setType(RS.getString("type"));
                p.setFrais_chauffeur(RS.getFloat("frais_chauffeur"));
                p.setDescription(RS.getString("description"));
                p.setPhoto(RS.getString("photo"));
                MoyenTransportsDisponibles.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return MoyenTransportsDisponibles;
    }




}

package tn.esprit.jdbc.services;


import tn.esprit.jdbc.entities.ReservationTransport;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.utils.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author firas
 */
public class ReservationTservice implements IServicet<ReservationTransport> {


    private Connection cnx;

    public ReservationTservice(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public List<ReservationTransport> afficheListe() {
        List<ReservationTransport> list = new ArrayList<>();
        try {
            String req = "Select * from  `reservationtransport`";
            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                ReservationTransport p = new ReservationTransport();
                p.setId_reservationt (RS.getInt("id_reservationt"));
                p.setDestination(RS.getString("destination"));
                p.setDate_debut(RS.getDate("date_debut"));
                p.setDate_fin(RS.getDate("date_fin"));
                p.setStatut_chauffeur(RS.getBoolean("statut_chauffeur"));
                p.setId_moyent(RS.getInt("id_moyent"));
                p.setId_chauffeur(RS.getInt("id_chauffeur"));


                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }






    @Override
    public void ajouter(ReservationTransport p) {
        try {
            String req = "INSERT INTO  `reservationtransport`(`destination`,`date_debut`, `date_fin`,`statut_chauffeur`,`id_moyent`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, p.getDestination());
            ps.setDate(2, new java.sql.Date(p.getDate_debut().getTime()));
            ps.setDate(3, new java.sql.Date(p.getDate_fin().getTime()));
            ps.setBoolean(4, p.isStatut_chauffeur());
            //ps.setDate(3, (Date)p.getDate_debut());
            //ps.setDate(4, (Date)p.getDate_fin());
            ps.setInt(5,p.getId_moyent());
            ps.executeUpdate();

            System.out.println("Reservation inséré");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(ReservationTransport p) {
        try {
            String req = "DELETE FROM `reservationtransport` WHERE id_reservationt = " + p.getId_reservationt ();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ReservationTransport supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(ReservationTransport p) {
        try {

            String req = "UPDATE `reservationtransport` SET   destination = ?,`date_debut` = ?, `date_fin` = ?, `statut_chauffeur` = ?, `id_moyent` = ?, `id_chauffeur` = ? WHERE `reservationtransport`.`id_reservationt` = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getDestination());
            ps.setDate(2,new java.sql.Date(p.getDate_debut().getTime()));
            ps.setDate(3, new java.sql.Date(p.getDate_fin().getTime()));
            ps.setBoolean(4, p.isStatut_chauffeur());
            ps.setInt(5, p.getId_moyent());
            ps.setInt(5, p.getId_chauffeur());
            ps.setInt(6, p.getId_reservationt());


            ps.executeUpdate();
            System.out.println("Reservation mis a jour");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<ReservationTransport> getByIdChauffeur(int id) {
        List<ReservationTransport> list = new ArrayList<>();
        try {
            String req = "Select * from  `reservationtransport` where `id_chauffeur`=?";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);

            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                ReservationTransport p = new ReservationTransport();
                p.setId_reservationt (RS.getInt("id_reservationt"));
                p.setDestination(RS.getString("destination"));
                p.setDate_debut(RS.getDate("date_debut"));
                p.setDate_fin(RS.getDate("date_fin"));
                p.setStatut_chauffeur(RS.getBoolean("statut_chauffeur"));
                p.setId_moyent(RS.getInt("id_moyent"));
                p.setId_chauffeur(RS.getInt("id_chauffeur"));


                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public List<ReservationTransport> getAvailableReservation() {
        List<ReservationTransport> list = new ArrayList<>();
        try {
            String req = "Select * from  `reservationtransport` where `id_chauffeur` is NULL";

            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);


            while (RS.next()) {
                ReservationTransport p = new ReservationTransport();
                p.setId_reservationt (RS.getInt("id_reservationt"));
                p.setDestination(RS.getString("destination"));
                p.setDate_debut(RS.getDate("date_debut"));
                p.setDate_fin(RS.getDate("date_fin"));
                p.setStatut_chauffeur(RS.getBoolean("statut_chauffeur"));
                p.setId_moyent(RS.getInt("id_moyent"));
                p.setId_chauffeur(RS.getInt("id_chauffeur"));


                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public void addChauffeurTo(Integer id_chauffeur, int id_res){
        try {

            String req = "UPDATE `reservationtransport` SET  `id_chauffeur` = ? WHERE `id_reservationt` = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setObject(1, id_chauffeur);
            ps.setInt(2, id_res);


            ps.executeUpdate();
            System.out.println("Reservation mis a jour");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public int[] countResWithChauf() throws SQLException {
        String reqSansChauffeur = "SELECT COUNT(*) from `reservationtransport` where `id_chauffeur` is NULL";
        PreparedStatement psSansChauffeur = cnx.prepareStatement(reqSansChauffeur);
        ResultSet rsSansChauffeur = psSansChauffeur .executeQuery();
        rsSansChauffeur.next();
        int countSansChauffeur = rsSansChauffeur.getInt(1);

        String reqAvecChauffeur = "SELECT COUNT(*) from `reservationtransport` where `id_chauffeur` is Not NULL";
        PreparedStatement psAvecChauffeur = cnx.prepareStatement(reqAvecChauffeur);
        ResultSet rsAvecChauffeur = psAvecChauffeur.executeQuery();
        rsAvecChauffeur.next();
        int countAvecChauffeur= rsAvecChauffeur.getInt(1);

        return new int[] { countSansChauffeur, countAvecChauffeur };
    }
    public List<ReservationTransport> rechercheBy(String des) throws SQLException{
        List<ReservationTransport> temp = new ArrayList<>();
        String req="SELECT * FROM reservationtransport \n" +
                "WHERE (destination LIKE '%"+des+"%' OR date_debut LIKE '%"+des+"%') \n" +
                "AND id_chauffeur IS NULL";;
        PreparedStatement ps = cnx.prepareStatement(req);


        Statement st = cnx.createStatement();

        ResultSet RS = st.executeQuery(req);


        while (RS.next()) {
            ReservationTransport p = new ReservationTransport();
            p.setId_reservationt (RS.getInt("id_reservationt"));
            p.setDestination(RS.getString("destination"));
            p.setDate_debut(RS.getDate("date_debut"));
            p.setDate_fin(RS.getDate("date_fin"));
            p.setStatut_chauffeur(RS.getBoolean("statut_chauffeur"));
            p.setId_moyent(RS.getInt("id_moyent"));
            p.setId_chauffeur(RS.getInt("id_chauffeur"));


            temp.add(p);
        }


        return temp;

    }
    public List<ReservationTransport> rechGetByIdChauffeur(int id,String des) {
        List<ReservationTransport> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM reservationtransport  " +
                    "WHERE (destination LIKE '%"+des+"%' OR date_debut LIKE '%"+des+"%') "+
                    "AND `id_chauffeur`=?";

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);

            ResultSet RS = ps.executeQuery();
            while (RS.next()) {
                ReservationTransport p = new ReservationTransport();
                p.setId_reservationt (RS.getInt("id_reservationt"));
                p.setDestination(RS.getString("destination"));
                p.setDate_debut(RS.getDate("date_debut"));
                p.setDate_fin(RS.getDate("date_fin"));
                p.setStatut_chauffeur(RS.getBoolean("statut_chauffeur"));
                p.setId_moyent(RS.getInt("id_moyent"));
                p.setId_chauffeur(RS.getInt("id_chauffeur"));


                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
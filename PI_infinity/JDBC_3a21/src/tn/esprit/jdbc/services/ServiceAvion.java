package tn.esprit.jdbc.services;
import tn.esprit.jdbc.entities.Avion;
import tn.esprit.jdbc.utils.MaConnexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceAvion implements IService<Avion>{
    private Connection cnx;

    public ServiceAvion(){
        cnx = MaConnexion.getInstance().getCnx();
    }


    @Override
    public void createOne(Avion avion) throws SQLException {
        String req = "INSERT INTO `avion`(`ID`, `modele`, `capacite`)" +
                " VALUES ('"+avion.getID()+"', '"+avion.getModele()+"','"+avion.getCapacite()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Avion ajouté !");
    }
    public void createOne1(Avion avion) throws SQLException {
        String req = "INSERT INTO `avion`(`ID`, `modele`, `capacite`)" +
                " VALUES (?, ?, ?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, avion.getID());
        st.setString(2, avion.getModele());
        st.setInt(3, avion.getCapacite());
        st.executeUpdate(req);
        System.out.println("Avion ajouté !");
    }
    @Override
    public boolean updateOne(Avion avion) throws SQLException {
        if (search(avion)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE `avion` SET ID = ?, modele = ?,capacite = ?  WHERE ID=? ;");
            pre.setInt(1,avion.getID());
            pre.setString(2,avion.getModele());
            pre.setInt(3,avion.getCapacite());
            pre.setInt(4,avion.getID());




            pre.executeUpdate();
            return true;}
        else{
            System.out.println("L'utulisateur n'existe pas");
            return true;
        }    }




    @Override
    public boolean deletOne(Avion avion) throws SQLException {
        Statement ste =  cnx.createStatement();
        if (search(avion)==true){
            ste = cnx.createStatement();
            String requeteDelete ="DELETE FROM avion WHERE ID="+ avion.getID()+"";
            ste.executeUpdate(requeteDelete);}
        else{
            System.out.println("L'utulisateur n'existe pas");
        }
        return true;    }


    public boolean search(Avion t) throws SQLException {
        Statement ste= cnx.createStatement();
        ResultSet rs=ste.executeQuery("select * from avion");
        boolean ok=false;
        while (rs.next()&&(ok==false)) {
            if (rs.getInt(1)==t.getID())
                ok=true;
        }
        return ok;    }


    @Override
    public List<Avion> selectAll() throws SQLException {
        List<Avion> temp = new ArrayList<>();

        String req = "SELECT * FROM `avion`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Avion p = new Avion();

            p.setID(rs.getInt(1));
            p.setModele(rs.getString("modele"));

            p.setCapacite(rs.getInt("capacite"));

            temp.add(p);

        }


        return temp;
    }
}




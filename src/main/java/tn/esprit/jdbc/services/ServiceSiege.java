package tn.esprit.jdbc.services;

import tn.esprit.jdbc.entities.Siege;
import tn.esprit.jdbc.utils.MaConnexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceSiege implements IServiceA<Siege> {

    private Connection cnx;

    public ServiceSiege(){
        cnx = MaConnexion.getInstance().getCnx();
    }

    @Override
    public void createOne(Siege siege) throws SQLException {
        String req = "INSERT INTO `siege`(`Numero`, `typesiege`)" +
                " VALUES ('"+siege.getID()+"', '"+siege.getTypesiege()+"')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Siege ajouté !");
    }



    @Override
    public boolean updateOne(Siege siege) throws SQLException {
        if (search(siege)==true){
            PreparedStatement pre=cnx.prepareStatement("UPDATE `siege` SET Numero = ?, typesiege = ?  WHERE Numero=? ;");
            pre.setInt(1,siege.getID());
            pre.setString(2,siege.getTypesiege());
            pre.setInt(3,siege.getID());


            pre.executeUpdate();
            return true;}
        else{
            System.out.println("Siege n'existe pas");
            return true;
        }    }



    @Override
    public boolean deletOne(Siege siege) throws SQLException {
        Statement ste =  cnx.createStatement();
        if (search(siege)==true){
            ste = cnx.createStatement();
            String requeteDelete ="DELETE FROM siege WHERE Numero="+ siege.getID()+"";
            ste.executeUpdate(requeteDelete);}
        else{
            System.out.println("L'siege n'existe pas");
        }
        return true;    }




    public boolean search(Siege t) throws SQLException {
        Statement ste= cnx.createStatement();
        ResultSet rs=ste.executeQuery("select * from siege");
        boolean ok=false;
        while (rs.next()&&(ok==false)) {
            if (rs.getInt(1)==t.getID())
                ok=true;
        }
        return ok;    }

    @Override
    public List<Siege> selectAll() throws SQLException {



        List<Siege> temp = new ArrayList<>();

        String req = "SELECT * FROM `siege`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Siege p = new Siege();

            p.setID(rs.getInt("Numero"));
            p.setTypesiege(rs.getString("typesiege"));


            temp.add(p);

        }


        return temp;
    }

}




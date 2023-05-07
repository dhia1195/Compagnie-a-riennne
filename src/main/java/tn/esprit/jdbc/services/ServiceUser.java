package tn.esprit.jdbc.services;

import org.mindrot.jbcrypt.BCrypt;
import tn.esprit.jdbc.entities.User;
import tn.esprit.jdbc.utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser implements IService<User> {

    private Connection cnx;

    public ServiceUser(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    ServiceRole sr = new ServiceRole();

    @Override
    public void createOne(User user) throws SQLException {
        String req = "INSERT INTO `user`( `nom`, `prenom`, `email`,`password`, `sexe`, `user_role`) VALUES (?,?,?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, user.getNom());
        st.setString(2, user.getPrenom());
        st.setString(3, user.getEmail());
        st.setString(4, user.getPassword());
        st.setString(5, user.getSexe());
        st.setInt(6, user.getRole().getId());

        st.executeUpdate();


        System.out.println("Person ajouté !");
    }
    public void updateRole(int id_user,int id_role)throws SQLException{
        String req="UPDATE `user` SET `user_role`=? WHERE id_user=?";
        User trueUser = findById(id_user);
        PreparedStatement st = cnx.prepareStatement(req);
        trueUser.setRole(sr.findById(id_role));
        st.setInt(1,id_role );
        st.setInt(2, id_user);
        st.executeUpdate();


    }

    @Override
    public void updateOne(User user,int id) throws SQLException {
        String req = "UPDATE `user` SET`nom`=?,`prenom`=?,`email`=?,`password`=?,`sexe`=?,`user_role`=? WHERE id_user=?";
        User trueUser = findById(id);
        PreparedStatement st = cnx.prepareStatement(req);
        if (!trueUser.getEmail().equals(user.getEmail()) )
            trueUser.setEmail(user.getEmail());
        if (!trueUser.getNom().equals(user.getNom()))
            trueUser.setNom(user.getNom());
        if (!trueUser.getPrenom().equals(user.getPrenom()))
            trueUser.setPrenom(user.getPrenom());
        if (!user.getPassword().equals(""))
            trueUser.setPassword(user.getPassword());
        if (!trueUser.getSexe().equals(user.getSexe()))
            trueUser.setSexe(user.getSexe());
        if (!trueUser.getRole().equals(""))
            trueUser.setRole(user.getRole());

        st.setString(1, trueUser.getNom());
        st.setString(2, trueUser.getPrenom());
        st.setString(3, trueUser.getEmail());
        st.setString(4, trueUser.getPassword());
        st.setString(5, trueUser.getSexe());
        st.setInt(6, trueUser.getRole().getId());
        st.setInt(7, id);

        st.executeUpdate();

    }

    @Override
    public User findById(int id) throws SQLException {
        User u =new User();
        String req = "SELECT * FROM `user` Where `id_user`=?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            u.setId(rs.getInt("id_user"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setSexe(rs.getString("sexe"));
            u.setRole(sr.findById(rs.getInt("user_role")));
        }

        return u;
    }

    @Override
    public void deletOne(int id) throws SQLException {
        String req="DELETE FROM `user` WHERE `id_user`=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        st.executeUpdate();



    }
    public User findByMail(String mail) throws SQLException{
        User u =new User();
        String req="SELECT * FROM `user` WHERE `email`=?";
        PreparedStatement st = cnx.prepareStatement((req));
        st.setString(1,mail);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            u.setId(rs.getInt("id_user"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setSexe(rs.getString("sexe"));
            u.setRole(sr.findById(rs.getInt("user_role")));
        }

        return u;

    }
    public List<User>selectChauffeur() throws SQLException{
        List<User> temp = new ArrayList<>();
        String req="SELECT * FROM `user` WHERE `user_role`=?";
        PreparedStatement st = cnx.prepareStatement((req));
        st.setInt(1,sr.findByRole("chauffeur").getId());

        ResultSet rs = st.executeQuery();

        while (rs.next()){

            User p = new User();

            p.setId(rs.getInt("id_user"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
            p.setSexe(rs.getString("sexe"));
            p.setRole(sr.findById(rs.getInt("user_role")));

            temp.add(p);

        }


        return temp;

    };
    public List<User> rechercheBy(String nom) throws SQLException{
     List<User> temp = new ArrayList<>();
     String req="SELECT * FROM user WHERE nom LIKE '%"+nom+"%' OR email LIKE '%"+nom+"%'";
        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            User p = new User();

            p.setId(rs.getInt("id_user"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
            p.setSexe(rs.getString("sexe"));
            p.setRole(sr.findById(rs.getInt("user_role")));

            temp.add(p);

        }


        return temp;

    }

    @Override
    public List<User> selectAll() throws SQLException {
        List<User> temp = new ArrayList<>();

        String req = "SELECT * FROM `user`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            User p = new User();

            p.setId(rs.getInt("id_user"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
            p.setSexe(rs.getString("sexe"));
            p.setRole(sr.findById(rs.getInt("user_role")));

            temp.add(p);

        }


        return temp;
    }

    public User authenticate(String mail, String password) throws SQLException {
        User u = null;
        String req = "SELECT * FROM `user` WHERE `email`=?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, mail);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            String hashedPassword = rs.getString("password");
            if (BCrypt.checkpw(password, hashedPassword)) {
                u = new User();
                u.setId(rs.getInt("id_user"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setEmail(rs.getString("email"));
                u.setPassword(hashedPassword);
                u.setSexe(rs.getString("sexe"));
                u.setRole(sr.findById(rs.getInt("user_role")));
            }
        }

        return u;
    }







}

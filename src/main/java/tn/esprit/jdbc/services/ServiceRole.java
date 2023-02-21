package tn.esprit.jdbc.services;

import tn.esprit.jdbc.entities.Role;
import tn.esprit.jdbc.services.IService;
import tn.esprit.jdbc.utils.MaConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRole implements IService<Role> {
    private Connection cnx;

    public ServiceRole(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    @Override
    public void createOne(Role role) throws SQLException {
        String req="INSERT INTO `role`(`role`) VALUES (?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, role.getRole());
        st.executeUpdate();

    }

    @Override
    public void updateOne(Role role, int id) throws SQLException {
        String req="UPDATE `role` SET `role`=? WHERE id_role=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, role.getRole());
        st.setInt(1, id);
        st.executeUpdate();


    }

    @Override
    public Role findById(int id) throws SQLException {
        Role r =new Role();
        String req = "SELECT * FROM `role` Where `id_role`=?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            r.setId(rs.getInt("id_role"));
            r.setRole(rs.getString("role"));
        }
        return r;
    }
    public Role findByRole(String role) throws SQLException {
        Role r =new Role();
        String req = "SELECT * FROM `role` Where `role`=?";
        PreparedStatement st = cnx.prepareStatement(req);

        st.setString(1, role);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            r.setId(rs.getInt("id_role"));
            r.setRole(rs.getString("role"));
        }
        return r;
    }

    @Override
    public void deletOne(int id) throws SQLException {
        String req="DELETE FROM `role` WHERE `id_role`=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, id);
        st.executeUpdate();

    }

    @Override
    public List<Role> selectAll() throws SQLException {
        List<Role> temp = new ArrayList<>();

        String req = "SELECT * FROM `role`";

        PreparedStatement ps = cnx.prepareStatement(req);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){

            Role r = new Role();

            r.setId(rs.getInt("id_role"));
            r.setRole(rs.getString("role"));
            temp.add(r);

        }


        return temp;
    }
}

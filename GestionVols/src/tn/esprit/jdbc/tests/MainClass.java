package tn.esprit.jdbc.tests;

import tn.esprit.jdbc.entities.Escale;
import tn.esprit.jdbc.entities.Vol;
import tn.esprit.jdbc.services.ServiceEscale;
import tn.esprit.jdbc.services.ServiceVol;

import java.sql.SQLException;

public class MainClass {
    public static void main(String[] args) {
        ServiceEscale se = new ServiceEscale();
        ServiceVol sv = new ServiceVol();
       Escale t = new Escale(100,"ttt","hh","hhh","hhhh");

        Vol v = new Vol(100,"a","aa","aa","aa","aa",23,t);

        Vol ve = new Vol(2,3,"doussa","","farah","ameni","aa",23,t);
        Escale te = new Escale(200,"ena","houa","shyy","oppa");




        try {
            sv.updateOne(ve);


            System.out.println(se.selectAll());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }}

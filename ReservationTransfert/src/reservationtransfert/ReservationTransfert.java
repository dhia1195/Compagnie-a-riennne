/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationtransfert;

import java.sql.Date;

import entities.moyentransfert;
import services.moyenTService;
import services.reservationTService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;


/**
 *
 * @author firas
 */
public class ReservationTransfert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
         entities.ReservationTransfert p = new entities.ReservationTransfert(4,Date.valueOf("2001-5-12"),300,3,1);
        entities.moyentransfert m=new moyentransfert(1,"yosra",555,"dqsd",55,Date.valueOf("2000-5-5"),Date.valueOf("2001-5-4"));
        moyenTService mp=new moyenTService();
        reservationTService rp = new reservationTService();

        //System.out.println(rp.readAll());
        //System.out.println(mp.readAll());
        //rp.delete(p);
        //mp.delete(m);
        //mp.insert(m);
        //rp.insert(p);
        //rp.update(p);
        //mp.update(m);


    }
    
}

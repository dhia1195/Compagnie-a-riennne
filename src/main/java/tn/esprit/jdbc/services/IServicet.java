package tn.esprit.jdbc.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author walid
 */
public interface IServicet<T> {

    List<T> afficheListe() ;
    void ajouter (T p);
    void supprimer (T p);
    void modifier (T p);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.modele.Operation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class GestionOperation {

    public GestionOperation() {
    }

    public void setOperation(List<Operation> ops) throws SQLException {
        Connection con = DB_Connection.get_connection();
        for (Operation op : ops) {

            Statement statement = con.createStatement();
            /* Exécution d'une requête de lecture */
            System.out.println(op.getCategorie().getLibelle());
            ResultSet resultat = statement.executeQuery("SELECT idC FROM categorie WHERE libelle='" + op.getCategorie().getLibelle() + "';");
            resultat.last();
            System.out.println(resultat.getRow());
            if (resultat.getRow() < 1) {
                System.out.println("hola");
                statement.executeUpdate("INSERT INTO categorie (libelle) VALUES"
                        + " ('" + op.getCategorie().getLibelle() + "');");
                resultat = statement.executeQuery("SELECT idC FROM categorie WHERE libelle='" + op.getCategorie().getLibelle() + "';");
            }
            int id = 0;
            resultat.beforeFirst();
            while (resultat.next()) {
                id = resultat.getInt("idC");
            }
            System.err.println(id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String time = sdf.format(op.getDate());

            int statut = statement.executeUpdate("INSERT INTO operation (libelle,date,montant,type,recurrence,idCa) VALUES"
                    + " ('" + op.getLibelle() + "','" + time + "','" + op.getMontant() + "','" + op.getType() + "','" + op.getRecurrence() + "','" + id + "');");

        }
    }
}

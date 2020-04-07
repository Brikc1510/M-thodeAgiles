package gestionbuget;

import Acceuil.Acceuil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.sql.SQLException;

public class GestionBuget {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException, SQLException {
        Acceuil a = new Acceuil();
        a.show();
    }
}

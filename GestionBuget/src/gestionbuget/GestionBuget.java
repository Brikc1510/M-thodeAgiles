package gestionbuget;


import AnalyseOperation.AnalyseOperation;
import com.Importation_données.ImportationDonnees;
import com.modele.Operation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import Visualisation.VisualisationOperations;
import connection.GestionOperation;
import java.sql.SQLException;


public class GestionBuget {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException, SQLException 
    {

        //AnalyseOperation ao = new AnalyseOperation(null);
        ImportationDonnees m = new ImportationDonnees();
        List<Operation> operations = m.importer();
         GestionOperation g = new GestionOperation();
         g.setOperation(operations);
         
//         VisualisationOperations vo = new VisualisationOperations();
//       vo.show(true);  
         
         }
        
    }
    


        
 
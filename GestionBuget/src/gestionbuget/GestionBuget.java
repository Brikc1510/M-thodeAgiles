package gestionbuget;

import AnalyseOperation.AnalyseOperation;
import com.Importation_données.ImportationDonnees;
import com.modele.Operation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class GestionBuget {

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException 
    {
        //AnalyseOperation ao = new AnalyseOperation(null);
        ImportationDonnees m = new ImportationDonnees();
        List<Operation> operations = m.importer();
         for (Operation oneData : operations) {
             System.out.println(oneData.getDate());
              System.out.println(oneData.getLibelle());
               System.out.println(oneData.getMontant());
                System.out.println(oneData.getType());
         
         }
         
         }
        
    }
    


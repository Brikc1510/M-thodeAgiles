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
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;


public class GestionBuget {
     private static JDBCCategoryDataset dataset;
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException, SQLException 
    {

        //AnalyseOperation ao = new AnalyseOperation(null);
        ImportationDonnees m = new ImportationDonnees();
        List<Operation> operations = m.importer();
        GestionOperation g = new GestionOperation();
        g.setOperation(operations);
		
        AnalyseOperation.pieChart();
        AnalyseOperation.barChart();
        AnalyseOperation.lineChart();
    }
    
//         VisualisationOperations vo = new VisualisationOperations();
//       vo.show(true);  
         
         }
        
    
    


        
 
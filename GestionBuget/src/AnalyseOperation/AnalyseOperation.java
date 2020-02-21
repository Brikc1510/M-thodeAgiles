/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseOperation;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author Utilisateur
 */
public class AnalyseOperation {
    
    private static JDBCCategoryDataset dataset;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, IOException {
        String url = "jdbc:mysql://localhost:3306/gestionbudgetdb";
        String user = "root";
        String password = "";
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            dataset = new JDBCCategoryDataset(con);
            dataset.executeQuery("SELECT idCa, montant FROM operation");
        }
        
        JFreeChart barChart = ChartFactory.createBarChart(
                "Olympic Gold medals in London",
                "",
                "Gold medals",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartUtils.saveChartAsPNG(new File("medals.png"), barChart, 450, 400);
    }
    
}

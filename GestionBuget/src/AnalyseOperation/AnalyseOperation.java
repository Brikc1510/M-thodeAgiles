/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseOperation;

import connection.DB_Connection;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

/**
 *
 * @author Utilisateur
 */
public class AnalyseOperation {

    private static JDBCCategoryDataset dataset;

    public static void pieChart() {
        Connection con = DB_Connection.get_connection();
        JDBCPieDataset datasetpie = new JDBCPieDataset(con);
        try {

            String query = "SELECT c.libelle, o.montant FROM categorie c inner join operation o on o.idCa=c.idC";
            datasetpie.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart("Dépenses/Catégorie", datasetpie);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}"));

        final ChartRenderingInfo info = new ChartRenderingInfo(
                new StandardEntityCollection());

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JFrame f = new JFrame("Gestion budget");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setContentPane(chartPanel);
        f.pack();
        f.setVisible(true);

    }

    public static void barChart() {
        Connection con = DB_Connection.get_connection();
        JDBCCategoryDataset datasetBar = new JDBCCategoryDataset(con);
        try {
            datasetBar.executeQuery("SELECT c.libelle, o.montant FROM categorie c inner join operation o on o.idCa=c.idC");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Dépenses/Catégorie",
                "Categorie",
                "Dépenses",
                datasetBar,
                PlotOrientation.VERTICAL,
                false, true, false);
        
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JFrame f = new JFrame("Gestion budget");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setContentPane(chartPanel);
        f.pack();
        f.setVisible(true);
    }

    public static void lineChart() {
        Connection con = DB_Connection.get_connection();
        JDBCCategoryDataset datasetLine = new JDBCCategoryDataset(con);
        try {
            datasetLine.executeQuery("SELECT c.libelle, o.montant FROM categorie c inner join operation o on o.idCa=c.idC");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JFreeChart barChart = ChartFactory.createLineChart(
                "Dépenses/Catégorie",
                "Categorie",
                "Dépenses",
                datasetLine,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        JFrame f = new JFrame("Gestion budget");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setContentPane(chartPanel);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) throws SQLException, IOException {    
        barChart();
        pieChart();
        lineChart();
    }
}

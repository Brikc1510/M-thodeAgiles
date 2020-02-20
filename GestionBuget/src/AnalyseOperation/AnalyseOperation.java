package AnalyseOperation;

import javax.swing.JFrame;

public class AnalyseOperation extends JFrame {

    PieChart p;

    public AnalyseOperation(JFrame parent) {
        super();
        p = new PieChart("Analyse des operation");

        this.setTitle("Analyse des opérations");
        this.setSize(500, 500);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(p);

        //populatePieChart();
    }

    public void populatePieChart() {

    }
}

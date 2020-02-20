package AnalyseOperation;

import javax.swing.JFrame;
//import org.jfree.data.general.PieDataset;

public class AnalyseOperation extends JFrame {
    
    
    public AnalyseOperation(JFrame parent) {
        super();

        this.setTitle("Analyse des opérations");
        this.setSize(500, 500);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void populatePieChart() {

    }
}

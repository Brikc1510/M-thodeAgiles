package Visualisation;

import java.sql.Connection;

import com.modele.Operation;
import connection.DB_Connection;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Visualisation extends JFrame {
    
    private Connection connexionBDD;
    private final JLabel labelCategorie, labelTypeOperation, labelDate, labelLibelle, labelMontant, labelCategorieDansListe;
    private JComboBox dropdownCategorie;
    
    public Visualisation(JFrame parent)
    {
        super();

        this.setTitle("Visualisation des operations");
        this.setSize(500, 500);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        connexionBDD = DB_Connection.get_connection();
        
        labelCategorie = new JLabel("Catégorie");
        labelTypeOperation = new JLabel("Type opération");
        labelDate = new JLabel("Date");
        labelLibelle = new JLabel("Libellé");
        labelMontant = new JLabel("Montant");
        labelCategorieDansListe = new JLabel("Catégorie");
    }
    
    public void setView()
    {
        this.add(labelCategorie);
        this.add(labelTypeOperation);
    }
}

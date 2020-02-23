/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visualisation;
import connection.DB_Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VisualisationOperations extends javax.swing.JFrame {

      Connection connexion;
    int selectedIndex;
    ArrayList<Integer> idArray;
    public VisualisationOperations() {
        connexion = DB_Connection.get_connection();
        selectedIndex = -1;
        idArray = new ArrayList<>();
        
        initComponents();
        populateValue();
        setupListener();
    }
      private void populateValue()
    {
        populateCategorie();
        populateTableauxDonnees();
    }
    
    private void setupListener()
    {        
        ListSelectionListener lsl = (ListSelectionEvent e) -> {
            int index = ((javax.swing.JList<String>)e.getSource()).getSelectedIndex();
            selectedIndex = index;
            setSelectionListe(index);
        };
        
        ActionListener al = (ActionEvent e) -> {
            selectedIndex = -1;
            
            populateTableauxDonnees();
        };
        
        this.listeCategorie.addListSelectionListener(lsl);
        this.listeDate.addListSelectionListener(lsl);
        this.listeLibelle.addListSelectionListener(lsl);
        this.listeMontant.addListSelectionListener(lsl);
         
        this.cbDepense.addActionListener(al);
        this.cbRecette.addActionListener(al);
        this.listeDeroulanteCategorie.addActionListener(al);
        
        this.btnFermer.addActionListener((ActionEvent e) -> {
            closeFrame();
        });
        
        this.btnSupprimer.addActionListener((ActionEvent e) -> {
             deleteSelectedRecord();
             populateTableauxDonnees();
        });
    }
    
    private void closeFrame()
    {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Lancel a fermeture de la frame
    }
    
    private void setSelectionListe(int index)
    {
        this.listeCategorie.setSelectedIndex(index);
        this.listeDate.setSelectedIndex(index);
        this.listeLibelle.setSelectedIndex(index);
        this.listeMontant.setSelectedIndex(index);
    }
    
    private void populateCategorie()
    {
        Statement stmt = null;
        String query = "SELECT * FROM categorie";
        try
        {
            stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            this.listeDeroulanteCategorie.addItem("Tout");
            while(rs.next())
            {
                this.listeDeroulanteCategorie.addItem(rs.getString("libelle"));
            }
            stmt.close();
        }
        catch(SQLException e)
        {}
    }
    
    private void populateTableauxDonnees()
    {
        String query = constructQuery();
        idArray.clear();
        if(!query.equals(""))
        {
            try (Statement stmt = connexion.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);

                ArrayList<String> listDataDate = new ArrayList<>();
                ArrayList<String> listDataLibelle = new ArrayList<>();
                ArrayList<String> listDataMontant = new ArrayList<>();
                ArrayList<String> listDataCategorie = new ArrayList<>();
                while(rs.next())
                {
                    idArray.add(rs.getInt("id"));
                    LocalDate localDate = rs.getDate("date").toLocalDate();
                    String date = String.format("%02d", localDate.getDayOfMonth()) + "/" + String.format("%02d", localDate.getMonthValue()) + "/" + String.valueOf(localDate.getYear());
                    
                    listDataDate.add(date);
                    listDataMontant.add(String.valueOf(rs.getDouble("montant")));
                    listDataLibelle.add(rs.getString("libelle"));
                    listDataCategorie.add(rs.getString("categorie.libelle"));
                }
                this.listeDate.setListData(GetStringArray(listDataDate));
                this.listeMontant.setListData(GetStringArray(listDataMontant));
                this.listeLibelle.setListData(GetStringArray(listDataLibelle));
                this.listeCategorie.setListData(GetStringArray(listDataCategorie));
            }
            catch(SQLException e)
            {}
        }
        else
        {
            String[] value = new String[0];
            this.listeDate.setListData(value);
            this.listeMontant.setListData(value);
            this.listeLibelle.setListData(value);
            this.listeCategorie.setListData(value);
        }
    }
    
    private void deleteSelectedRecord()
    {
        try 
        {
            PreparedStatement pstmt = connexion.prepareStatement("DELETE FROM operation WHERE id=?");
            pstmt.setInt(1, idArray.get(selectedIndex));
            pstmt.execute();
        } 
        catch (SQLException ex) 
        {}
        
    }
   public static String[] GetStringArray(ArrayList<String> arr) 
    {
        String str[] = new String[arr.size()]; 
        for (int j = 0; j < arr.size(); j++) {
            str[j] = arr.get(j); 
        } 
        return str; 
    } 

    private String constructQuery()
    {
        // Query de base qui selectionne tout
        String query = "SELECT * FROM `operation` INNER JOIN categorie ON idCa=idC";
        
        // Choisi quel type dépense / recette voir:
        if(!this.listeDeroulanteCategorie.getSelectedItem().toString().equals("Tout"))
        {
            query += " WHERE (categorie.libelle='" + (String)this.listeDeroulanteCategorie.getSelectedItem() +"'";
        }
        else
        {
            query += " WHERE (categorie.libelle<>''"; // Pour éviter d'avoir des if imbriqué horrible
        }
        
        // Verifie si on veut les dépense ou les recettes
        if(this.cbDepense.isSelected() && !this.cbRecette.isSelected())
        {
            query += " AND montant<=0.0";
        }
        else if(!this.cbDepense.isSelected() && this.cbRecette.isSelected())
        {
            query += " AND montant>0.0";
        }
        // Dans le cas ou le type d'opération n'a pas été choisi, on retourne une chaine vide
        else if(!this.cbDepense.isSelected() && !this.cbRecette.isSelected())
        {
            return "";
        }
        
        query += ") ORDER BY date DESC";
        return query;
    }
    
   // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    

        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualisationOperations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VisualisationOperations().setVisible(true);
        });
        
    }
        /* Create and display the form */
       private javax.swing.JButton btnFermer;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JCheckBox cbDepense;
    private javax.swing.JCheckBox cbRecette;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listeCategorie;
    private javax.swing.JList<String> listeDate;
    private javax.swing.JComboBox<String> listeDeroulanteCategorie;
    private javax.swing.JList<String> listeLibelle;
    private javax.swing.JList<String> listeMontant;
    // End of variables declaration//GEN-END:variables
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


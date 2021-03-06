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
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VisualisationOperations extends javax.swing.JFrame {

    private Connection connexion;
    int selectedIndex;
    ArrayList<Integer> idArray;

    public VisualisationOperations() {
        connexion = DB_Connection.get_connection();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectedIndex = -1;
        idArray = new ArrayList<>();

        initComponents();
        populateValue();
        setupListener();
    }

    private void populateValue() {
        populateCategorie();
        populateTableauxDonnees();
    }

    private void setupListener() {
        ListSelectionListener lsl = (ListSelectionEvent e) -> {
            int index = ((javax.swing.JList<String>) e.getSource()).getSelectedIndex();
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

    private void closeFrame() {
        dispose();
    }

    private void setSelectionListe(int index) {
        this.listeCategorie.setSelectedIndex(index);
        this.listeDate.setSelectedIndex(index);
        this.listeLibelle.setSelectedIndex(index);
        this.listeMontant.setSelectedIndex(index);
    }

    private void populateCategorie() {
        Statement stmt = null;
        String query = "SELECT * FROM categorie";
        try {
            stmt = connexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            this.listeDeroulanteCategorie.addItem("Tout");
            while (rs.next()) {
                this.listeDeroulanteCategorie.addItem(rs.getString("libelle"));
            }
            stmt.close();
        } catch (SQLException e) {
        }
    }

    private void populateTableauxDonnees() {
        String query = constructQuery();
        idArray.clear();
        if (!query.equals("")) {
            try (Statement stmt = connexion.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);

                ArrayList<String> listDataDate = new ArrayList<>();
                ArrayList<String> listDataLibelle = new ArrayList<>();
                ArrayList<String> listDataMontant = new ArrayList<>();
                ArrayList<String> listDataCategorie = new ArrayList<>();
                while (rs.next()) {
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
            } catch (SQLException e) {
            }
        } else {
            String[] value = new String[0];
            this.listeDate.setListData(value);
            this.listeMontant.setListData(value);
            this.listeLibelle.setListData(value);
            this.listeCategorie.setListData(value);
        }
    }

    private void deleteSelectedRecord() {
        try {
            PreparedStatement pstmt = connexion.prepareStatement("DELETE FROM operation WHERE id=?");
            pstmt.setInt(1, idArray.get(selectedIndex));
            pstmt.execute();
        } catch (SQLException ex) {
        }

    }

    public static String[] GetStringArray(ArrayList<String> arr) {
        String str[] = new String[arr.size()];
        for (int j = 0; j < arr.size(); j++) {
            str[j] = arr.get(j);
        }
        return str;
    }

    private String constructQuery() {
        // Query de base qui selectionne tout
        String query = "SELECT * FROM `operation` INNER JOIN categorie ON idCa=idC";

        // Choisi quel type dépense / recette voir:
        if (!this.listeDeroulanteCategorie.getSelectedItem().toString().equals("Tout")) {
            query += " WHERE (categorie.libelle='" + (String) this.listeDeroulanteCategorie.getSelectedItem() + "'";
        } else {
            query += " WHERE (categorie.libelle<>''"; // Pour éviter d'avoir des if imbriqué horrible
        }

        // Verifie si on veut les dépense ou les recettes
        if (this.cbDepense.isSelected() && !this.cbRecette.isSelected()) {
            query += " AND montant<=0.0";
        } else if (!this.cbDepense.isSelected() && this.cbRecette.isSelected()) {
            query += " AND montant>0.0";
        } // Dans le cas ou le type d'opération n'a pas été choisi, on retourne une chaine vide
        else if (!this.cbDepense.isSelected() && !this.cbRecette.isSelected()) {
            return "";
        }

        query += ") ORDER BY date DESC";
        return query;
    }

    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        listeDeroulanteCategorie = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listeDate = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listeMontant = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listeCategorie = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listeLibelle = new javax.swing.JList<>();
        btnFermer = new javax.swing.JButton();
        cbDepense = new javax.swing.JCheckBox();
        cbRecette = new javax.swing.JCheckBox();
        btnSupprimer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2.setText("Catégorie");

        jLabel1.setText("Type d'opérations");

        jLabel3.setText("Date");

        jLabel4.setText("Montant");

        jLabel5.setText("Libellé");

        jLabel6.setText("Catégorie");

        jScrollPane1.setViewportView(listeDate);

        jScrollPane2.setViewportView(listeMontant);

        jScrollPane3.setViewportView(listeCategorie);

        jScrollPane4.setViewportView(listeLibelle);

        btnFermer.setText("Fermer");

        cbDepense.setSelected(true);
        cbDepense.setText("Dépense");

        cbRecette.setSelected(true);
        cbRecette.setText("Recette");

        btnSupprimer.setText("Supprimer selection");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSupprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFermer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(listeDeroulanteCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel4)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(256, 256, 256)
                                        .addComponent(jLabel6)))
                                .addGap(18, 18, 18)
                                .addComponent(cbDepense)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbRecette)))))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(listeDeroulanteCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbDepense)
                    .addComponent(cbRecette))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSupprimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFermer)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VisualisationOperations().setVisible(true);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Importation_données;

import com.modele.Categorie;
import com.modele.Operation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class ImportationDonnees {
   

    public List<Operation> importer(String path) throws ParseException, IOException {

        List<Operation> operations = new ArrayList<>();
        Path orderPath = Paths.get(path);
        List<String> lines = null; //null mean no value by default
        try {
            lines = Files.readAllLines(orderPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier des commandes");
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lines.size(); i++) {
            String[] tabChaine = lines.get(i).split(";");
            String dateO = tabChaine[0];
            String libelle = tabChaine[1];
            String categorie = tabChaine[2];
            String montantO = tabChaine[3];

            String type = null;
            double montant = Double.parseDouble(montantO);
            if (montant > 0) {
                type = "Revenue";
            } else {
                type = "Depense";
            }

            Date date = dateFormat.parse(dateO);
            

            Operation op = new Operation(libelle, date, montant, type, null, new Categorie(categorie));
            operations.add(op);
        }

        return operations;

    }
}

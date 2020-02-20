/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ahmed
 */
public class Connexion {
    
    /* Connexion à la base de données */
    String url = "jdbc:mysql://localhost:3306/gestionbudgetdb";
    String utilisateur = "root";
    String motDePasse = "ahmed";
    Connection connexion = null;
    try {
        Class.forName("com.mysql.jdbc.Driver");
        connexion = DriverManager.getConnection(url,utilisateur,motDePasse);

    } catch (Exception e ) {
        
    }
    
}

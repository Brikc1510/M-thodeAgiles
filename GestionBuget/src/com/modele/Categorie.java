/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele;

import connection.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Ahmed
 */
public class Categorie {
    
    private String libelle;
    DB_Connection obj_DB_Connection=new DB_Connection();
    Connection connection=obj_DB_Connection.get_connection();
    
    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    public int getIdCategorie()
    {
         int Id=0;
         PreparedStatement prepare=null;
        try {
            
            String query="select idC from categorie where libelle=?";
            prepare=(PreparedStatement)connection.prepareStatement(query);
            prepare.setString(1, this.libelle);
            System.out.println(prepare);
            ResultSet result =prepare.executeQuery();
            while(result.next())
            {
            Id=Integer.parseInt(result.getString("idC")); 
            }
            }
        catch (Exception e) {
                System.out.println(e);
        }
            return Id;
    }

    
    
}

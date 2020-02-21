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
    private int idC;
    
   Connection con = DB_Connection.get_connection();

    public Categorie(String libelle) {
        this.libelle = libelle;
        setId();
    }

    public String getLibelle() {
        return libelle;
    }
    
    public int getidC() {
        return idC;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
        setId();
    }
    
    
    public void setId()
    {
        PreparedStatement prepare=null;
        try {
            
            String query="select * from categorie where libelle=?";
            prepare=(PreparedStatement)con.prepareStatement(query);
            prepare.setString(1, this.libelle);
            System.out.println(prepare);
            ResultSet result =prepare.executeQuery();
            while(result.next())
            {
              idC=Integer.parseInt(result.getString("idC")); 
            }
        }
        catch (Exception e) {
                System.out.println(e);
        }
    }
    public Categorie getCategorie()
    {
        return null;
        //Categorie categorie=new Categorie(this.libelle);
        
        /*PreparedStatement prepare=null;
        try {
            
            String query="select * from categorie where libelle=?";
            prepare=(PreparedStatement)con.prepareStatement(query);
            prepare.setString(1, this.libelle);
            System.out.println(prepare);
            ResultSet result =prepare.executeQuery();
            while(result.next())
            {
             idC=Integer.parseInt(result.getString("idC")); 
            }
            }
        catch (Exception e) {
                System.out.println(e);
        }
            return idC;*/
    }
    
    
}

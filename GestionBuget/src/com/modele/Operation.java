/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele;

import connection.DB_Connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Ahmed
 */
public class Operation {
    private String libelle;
    private Date date;	
    private double montant;	
    private String type;
    private String recurrence;
    private int idCat;

    DB_Connection obj_DB_Connection=new DB_Connection();
    Connection connection=obj_DB_Connection.get_connection();
    
    public Operation(String libelle, Date date, double montant, String type, String recurrence, int idCat) {
        this.libelle = libelle;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.recurrence = recurrence;
        this.idCat=idCat;
    }

    public int idCat() {
        return idCat;
    }

    public void setidCat(int idCat) {
        this.idCat = idCat;
    }

    public String getLibelle() {
        return libelle;
    }

    public Date getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public String getType() {
        return type;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }
    
    
    public void Ajouter_Opération()
    {    
        PreparedStatement prepare=null;
        try {
            
            String query="insert into operation(libelle,date,montant,type,recurrence,idCa) values (?,?,?,?,?,?)";
            prepare=(PreparedStatement)connection.prepareStatement(query);
            prepare.setString(1, this.libelle);
            prepare.setDate(2,  this.date);
            prepare.setDouble(3, this.montant );
            prepare.setString(4, this.type);
            prepare.setString(5, this.recurrence);
            prepare.setInt(6, this.idCat);
            prepare.executeUpdate();		
            }
        catch (Exception e) {
                System.out.println(e);
        }	
    }
    
}

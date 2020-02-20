/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modele;

import java.util.Date;

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
    private Categorie categorie;

    public Operation(String libelle, Date date, double montant, String type, String recurrence, Categorie categorie) {
        this.libelle = libelle;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.recurrence = recurrence;
        this.categorie = categorie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
    
}

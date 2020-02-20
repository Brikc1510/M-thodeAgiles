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
public class Solde {
    private Date date;
    private double montant;

    public Solde(Date date, double montant) {
        this.date = date;
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
}

package com.makarimal.mabanque.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;
@Entity
@DiscriminatorValue("V")
public class Versement extends  Operation {
    public Versement() {
    }

    public Versement(LocalDate dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }
}

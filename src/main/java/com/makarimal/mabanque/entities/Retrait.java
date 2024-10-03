package com.makarimal.mabanque.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;
@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {
    public Retrait() {
        super();
    }

    public Retrait(LocalDate dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }
}

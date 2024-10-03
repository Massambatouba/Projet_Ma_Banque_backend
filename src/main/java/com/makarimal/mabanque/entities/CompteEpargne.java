package com.makarimal.mabanque.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("CE")
@Data

public class CompteEpargne extends Compte {
    private double taux;

    public CompteEpargne(Client client, double taux) {
        super();
        this.taux = taux;
    }

    public CompteEpargne(String codeCompte, LocalDate dateCreation, double solde, Client client, double taux) {
        super(codeCompte, dateCreation, solde, client);
        this.taux = taux;
    }

    public CompteEpargne() {
        super();
    }
}

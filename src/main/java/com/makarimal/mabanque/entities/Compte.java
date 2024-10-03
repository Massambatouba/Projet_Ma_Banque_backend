package com.makarimal.mabanque.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE",
        discriminatorType = DiscriminatorType.STRING, length = 2)

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CompteCourant.class, name = "CC"),
        @JsonSubTypes.Type(value = CompteEpargne.class, name = "CE")
})
public abstract class Compte implements Serializable {
    @Id
    //pas de GENERETED VALUE CAR STRING
    private String codeCompte;
    private LocalDate dateCreation;
    private double solde;
    @ManyToOne
    // clé etrangere
    @JsonBackReference
    @JoinColumn(name = "CODE_CLIENT")
    private Client client;
    @OneToMany(mappedBy = "compte")
    @JsonManagedReference
    private Collection<Operation> operations;

    public Compte() {
    }


    public Compte(String codeCompte, LocalDate dateCreation, double solde, Client client) {
        this.codeCompte = codeCompte;
        this.dateCreation = dateCreation;
        this.solde = solde;
        this.client = client;
    }

    public String getCodeCompte() {
        return codeCompte;
    }

    public void setCodeCompte(String codeCompte) {
        this.codeCompte = codeCompte;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
    }
}

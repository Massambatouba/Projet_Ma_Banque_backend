package com.makarimal.mabanque.service;

import com.makarimal.mabanque.entities.Compte;
import com.makarimal.mabanque.entities.Operation;
import com.makarimal.mabanque.exceptions.CompteInexistanteException;
import com.makarimal.mabanque.exceptions.CompteIntrouvableException;
import com.makarimal.mabanque.exceptions.CompteException;
import org.springframework.data.domain.Page;

public interface ICompte {
    public Compte consulterCompte(String codeCompte) throws CompteIntrouvableException;
    public void verser(String codeCompte, double montant);
    public void retrait(String codeCompte, double montant);
    public void virement(String codeCompte1, String codeCompte2, double montant);
    public Page<Operation> listOperation(String codeCompte, int page , int size);
    public Compte createCompte(Compte compte) throws CompteException;
    public Compte editCompte(Compte compte);
    public void deleteCompte(String codeCompte) throws CompteInexistanteException;
}

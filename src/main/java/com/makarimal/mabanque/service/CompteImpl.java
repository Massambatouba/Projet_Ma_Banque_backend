package com.makarimal.mabanque.service;

import com.makarimal.mabanque.dao.CompteRepository;
import com.makarimal.mabanque.dao.OperationRepository;
import com.makarimal.mabanque.entities.*;
import com.makarimal.mabanque.exceptions.CompteInexistanteException;
import com.makarimal.mabanque.exceptions.CompteIntrouvableException;
import com.makarimal.mabanque.exceptions.SoldeInsufisantException;
import com.makarimal.mabanque.exceptions.CompteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
//@Transactional
public class CompteImpl implements ICompte{
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Override
    public Compte consulterCompte(String codeCompte) throws CompteIntrouvableException {
        Compte cp=compteRepository.findById(codeCompte).get();
        if(cp==null){
            throw new CompteIntrouvableException("compte introuvable");
        }
        return cp;
    }

    @Override
    public void verser(String codeCompte, double montant) {
        Compte cp = consulterCompte(codeCompte);
        Versement v = new Versement(LocalDate.now(), montant, cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);
    }

    @Override
    public void retrait(String codeCompte, double montant) throws SoldeInsufisantException {
        Compte cp = consulterCompte(codeCompte);
        Retrait r = new Retrait(LocalDate.now(),montant, cp);
        double facilitesCaisse = 0;
        if (cp instanceof  CompteCourant)
            facilitesCaisse = ((CompteCourant) cp).getDecouvert();
         if(cp.getSolde()+facilitesCaisse<montant)
            throw new SoldeInsufisantException("Solde insuffisant");
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);

    }

    @Override
    public void virement(String codeCompte1, String codeCompte2, double montant) {
        retrait(codeCompte1, montant);
        verser(codeCompte2, montant);
    }

    @Override
    public Page<Operation> listOperation(String codeCompte, int page, int size) {
        return operationRepository.listOperation(codeCompte, PageRequest.of(page, size));
    }

    @Override
    public Compte createCompte(Compte compte) throws CompteException {
        return compteRepository.save(compte);
    }

    @Override
    public Compte editCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    @Override
    public void deleteCompte(String codeCompte) throws CompteInexistanteException {
        if (codeCompte==null){
            throw new CompteInexistanteException("Compte n'existe pas");
        }
        compteRepository.deleteById(codeCompte);
    }
}

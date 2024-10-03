package com.makarimal.mabanque.service;

import com.makarimal.mabanque.dao.CompteRepository;
import com.makarimal.mabanque.dao.OperationRepository;
import com.makarimal.mabanque.dto.OperationDTO;
import com.makarimal.mabanque.entities.*;
import com.makarimal.mabanque.exceptions.SoldeInsufisantException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OperationServiceImp implements IOperationService{
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private ICompte iCompteService;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void verser(String codeCompte, double montant) {
        Compte cp = iCompteService.consulterCompte(codeCompte);
        Versement v = new Versement(LocalDate.now(), montant,cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);
    }

    @Override
    public OperationDTO convertEntityToDto(Operation operation) {
        OperationDTO operationDTO = modelMapper.map(operation,OperationDTO.class);
        return operationDTO;
    }

    @Override
    public Operation convertDtoToEntity(OperationDTO operationDTO) {
        Operation operation = modelMapper.map(operationDTO,Operation.class);
        return operation;
    }

    public void retrait(String codeCompte, double montant) throws SoldeInsufisantException{
        double faciliteCaisse = 0;
        Compte cp = iCompteService.consulterCompte(codeCompte);
        Retrait r = new Retrait(LocalDate.now(),montant,cp);
        if (cp instanceof CompteCourant) {
            faciliteCaisse = ((CompteCourant) cp).getDecouvert();
            if (cp.getSolde()+faciliteCaisse < montant) throw new SoldeInsufisantException("Votre solde est insuffisant");
        }
        operationRepository.save(r);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);
    }
    @Transactional
    public void virement(String codeCompte1, String codeCompte2, double montant) {
        retrait(codeCompte1, montant);
        verser(codeCompte2,montant);
    }

}

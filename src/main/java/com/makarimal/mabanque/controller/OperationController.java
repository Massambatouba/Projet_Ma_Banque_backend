package com.makarimal.mabanque.controller;

import com.makarimal.mabanque.dao.OperationRepository;
import com.makarimal.mabanque.dto.OperationDTO;
import com.makarimal.mabanque.entities.Compte;
import com.makarimal.mabanque.entities.Operation;
import com.makarimal.mabanque.service.OperationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@CrossOrigin("*")
@RestController
@RequestMapping("api")
public class OperationController {
    @Autowired
    private OperationServiceImp operationServiceImp;
    @Autowired
    private OperationRepository operationRepository;


    @PostMapping("/saveOperation")
    public ResponseEntity<String> saveOperation(@RequestBody OperationDTO operationDTO) {
        try {
            String codeCompte = operationDTO.getCodeCompte();
            String typeOperation = operationDTO.getTypeOperation();
            Double montant = operationDTO.getMontant();
            String codeCompte2 = operationDTO.getCodeCompte2();

            if (typeOperation != null && typeOperation.equals("versement")) {
                operationServiceImp.verser(codeCompte, montant);
            } else if (typeOperation != null && typeOperation.equals("virement")) {
                operationServiceImp.virement(codeCompte, codeCompte2, montant);
            } else if (typeOperation != null && typeOperation.equals("retrait")) {
                operationServiceImp.retrait(codeCompte, montant);
            }

            return ResponseEntity.ok("Operation saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving operation: " + e.getMessage());
        }
    }
    @GetMapping("/listeOperation/{compteCompte}")
    public Page<Operation> findAll(@PathVariable String compteCompte, @PageableDefault(size = 5)  Pageable pageable) {

        return operationServiceImp.listeOperation(compteCompte, pageable);
    }

}



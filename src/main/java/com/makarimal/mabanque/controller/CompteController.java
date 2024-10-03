package com.makarimal.mabanque.controller;

import com.makarimal.mabanque.entities.Compte;
import com.makarimal.mabanque.entities.CompteCourant;
import com.makarimal.mabanque.exceptions.CompteException;
import com.makarimal.mabanque.exceptions.CompteInexistanteException;
import com.makarimal.mabanque.service.ICompte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin("*")
public class CompteController {
    @Autowired
    private ICompte iCompteService;

    @GetMapping("/showCompte/{codeCompte}")
    public Compte getComteByCode(@PathVariable String codeCompte) {
        return iCompteService.consulterCompte(codeCompte);
    }

    @PostMapping("/createCompte")
    public ResponseEntity<?> createCompte(@RequestBody Compte compte) {
        log.info("Create compte request : {}", compte);
        Compte saveCompte = iCompteService.createCompte(compte);
        return new ResponseEntity<>(saveCompte, HttpStatus.CREATED);
    }
    @PutMapping("/editCompte")
    public Compte updateCompte(@RequestBody Compte compte) {
        log.info("editer compte request {}", compte);
        Compte editCompte = iCompteService.editCompte(compte);
        return editCompte;
    }
    @DeleteMapping("/deleteCompte/{codeCompte}")
    public void supCompte(@PathVariable String codeCompte) throws CompteInexistanteException {
        log.info("delete compte request {}", codeCompte);
        iCompteService.deleteCompte(codeCompte);
    }
}

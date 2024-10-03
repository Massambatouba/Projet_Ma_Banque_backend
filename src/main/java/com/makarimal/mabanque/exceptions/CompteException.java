package com.makarimal.mabanque.exceptions;

public class CompteException extends RuntimeException {
    public CompteException(String codeCompte) {
        super(String.format("Compte numero %d inexistant",codeCompte));
    }
}

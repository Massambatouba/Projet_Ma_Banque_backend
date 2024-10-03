package com.makarimal.mabanque.service;

import com.makarimal.mabanque.dto.OperationDTO;
import com.makarimal.mabanque.entities.Operation;

public interface IOperationService {
    public void verser(String codeCompte, double montant);

    OperationDTO convertEntityToDto(Operation operation);
    Operation convertDtoToEntity(OperationDTO operationDTO);
}

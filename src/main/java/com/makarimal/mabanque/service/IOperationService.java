package com.makarimal.mabanque.service;

import com.makarimal.mabanque.dto.OperationDTO;
import com.makarimal.mabanque.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOperationService {
    public Page<Operation> listeOperation(String codeCompte , Pageable pageable);
    public void verser(String codeCompte, double montant);

    OperationDTO convertEntityToDto(Operation operation);
    Operation convertDtoToEntity(OperationDTO operationDTO);
}

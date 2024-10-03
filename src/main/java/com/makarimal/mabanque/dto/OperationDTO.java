package com.makarimal.mabanque.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationDTO {    private String codeCompte;
    private String typeOperation;
    private Double montant;
    private String modif;
    private LocalDate dateOperation;
    private String codeCompte2;

}

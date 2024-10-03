package com.makarimal.mabanque;

import com.makarimal.mabanque.dao.ClientRepository;
import com.makarimal.mabanque.dao.CompteRepository;
import com.makarimal.mabanque.dao.OperationRepository;
import com.makarimal.mabanque.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    public static void main(String[] args) {
      SpringApplication.run(MaBanqueApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
      /*  Client c1 = clientRepository.save(new Client("Massamba", "masamba@gmail.com"));
        Client c2 = clientRepository.save(new Client("Khady", "khady@gmail.com"));

        Compte cp1 = compteRepository.save(new CompteCourant("c1", LocalDate.of(2024, 07, 01), 90000, c1, 6000));
        Compte cp2 = compteRepository.save(new CompteEpargne("c2", LocalDate.of(2024, 07, 01), 6000, c2, 5.5));

        operationRepository.save(new Versement(LocalDate.now(), 9000, cp1));
        operationRepository.save(new Versement(LocalDate.now(), 6000, cp1));
        operationRepository.save(new Versement(LocalDate.now(), 2300, cp1));
        operationRepository.save(new Retrait(LocalDate.now(), 2300, cp1));


        operationRepository.save(new Versement(LocalDate.now(), 900, cp2));
        operationRepository.save(new Versement(LocalDate.now(), 1500, cp2));
        operationRepository.save(new Versement(LocalDate.now(), 4500, cp2));
        operationRepository.save(new Retrait(LocalDate.now(), 9300, cp2));

       */
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}

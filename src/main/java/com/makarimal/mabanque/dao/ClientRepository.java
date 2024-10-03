package com.makarimal.mabanque.dao;

import com.makarimal.mabanque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

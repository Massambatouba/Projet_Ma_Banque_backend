package com.makarimal.mabanque.dao;

import com.makarimal.mabanque.entities.Compte;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {

}

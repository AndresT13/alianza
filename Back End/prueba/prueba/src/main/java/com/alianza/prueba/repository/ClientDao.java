package com.alianza.prueba.repository;

import com.alianza.prueba.model.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientDao extends JpaRepository<ClientEntity, String> {

}

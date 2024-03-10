package com.alianza.prueba.repository;

import com.alianza.prueba.model.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface ClientDao extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findBySharedKey(String sharedKey);


}

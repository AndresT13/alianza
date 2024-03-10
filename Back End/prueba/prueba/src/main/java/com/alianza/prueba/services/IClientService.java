package com.alianza.prueba.services;

import com.alianza.prueba.Mappers.MappersObjectsClients;
import com.alianza.prueba.model.dto.ClientDto;
import com.alianza.prueba.model.entities.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IClientService extends MappersObjectsClients {

    List<ClientDto> findAll();
    ClientDto getSharedKey(String sharedKey);
    ClientDto save(ClientDto clientDto);

    public Page<ClientEntity> findAll(Pageable pageable);

}

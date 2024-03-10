package com.alianza.prueba.services;

import com.alianza.prueba.Mappers.MappersObjectsClients;
import com.alianza.prueba.model.dto.ClientDto;


public interface IClientService extends MappersObjectsClients {

    ClientDto findBySharedKey(String id);
    ClientDto save(ClientDto clientDto);

    boolean existsById(String id);
}

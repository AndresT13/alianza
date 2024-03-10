package com.alianza.prueba.services;

import com.alianza.prueba.Mappers.MappersObjectsClients;
import com.alianza.prueba.model.dto.ClientDto;

import java.util.List;


public interface IClientService extends MappersObjectsClients {

    List<ClientDto> findAll();
    ClientDto getSharedKey(String sharedKey);
    ClientDto save(ClientDto clientDto);

}

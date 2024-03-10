package com.alianza.prueba.services.impl;

import com.alianza.prueba.Mappers.MappersObjectsClients;
import com.alianza.prueba.model.dto.ClientDto;
import com.alianza.prueba.model.entities.ClientEntity;
import com.alianza.prueba.repository.ClientDao;
import com.alianza.prueba.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class IClientServiceImpl implements IClientService {

    private final ClientDao clientRepository;

    @Autowired
    public IClientServiceImpl(ClientDao clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto findBySharedKey(String id) {
        Optional<ClientEntity> entity = clientRepository.findById(id);
        ClientDto clientDto = null;

        if(entity.isPresent())
            clientDto = MappersObjectsClients.clientEntityToClientDto(entity.get());

        return clientDto;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        Optional<ClientEntity> entity = clientRepository.findById(clientDto.getSharedKey());
        ClientEntity clientEntity = null;
        if(!entity.isPresent()){
            clientEntity = clientRepository.save(MappersObjectsClients.clientDtoToClientEntity(clientDto));
        }
        return MappersObjectsClients.clientEntityToClientDto(clientEntity);

    }

    @Override
    public boolean existsById(String id) {
        return clientRepository.existsById(id);
    }


}

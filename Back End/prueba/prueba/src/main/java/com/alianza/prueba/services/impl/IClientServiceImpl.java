package com.alianza.prueba.services.impl;

import com.alianza.prueba.Mappers.MappersObjectsClients;
import com.alianza.prueba.model.dto.ClientDto;
import com.alianza.prueba.model.entities.ClientEntity;
import com.alianza.prueba.repository.ClientDao;
import com.alianza.prueba.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IClientServiceImpl implements IClientService {

    private final ClientDao clientRepository;

    @Autowired
    public IClientServiceImpl(ClientDao clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<ClientDto> findAll() {
        List<ClientEntity> clienttEntityList = clientRepository.findAll();
        List<ClientDto> clientDtoList = new ArrayList<>();

        for (ClientEntity entity : clienttEntityList) {
            clientDtoList.add(MappersObjectsClients.clientEntityToClientDto(entity));
        }
        return clientDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public ClientDto getSharedKey(String sharedKey) {
        Optional<ClientEntity> clientEntity = clientRepository.findBySharedKey(sharedKey);
        ClientDto clientDto = null;

        if (clientEntity.isPresent())
            clientDto = MappersObjectsClients.clientEntityToClientDto(clientEntity.get());
        return clientDto;

    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        Optional<ClientEntity> entity = clientRepository.findBySharedKey(clientDto.getSharedKey());
        ClientEntity client = null;
        if(!entity.isPresent()){
            client = clientRepository.save(MappersObjectsClients.clientDtoToClientEntity(clientDto));
        }
        return MappersObjectsClients.clientEntityToClientDto(client);

    }


}

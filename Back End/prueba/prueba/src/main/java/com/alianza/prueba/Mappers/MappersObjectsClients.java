package com.alianza.prueba.Mappers;

import com.alianza.prueba.model.dto.ClientDto;
import com.alianza.prueba.model.entities.ClientEntity;

public interface MappersObjectsClients {

    public static ClientDto clientEntityToClientDto(ClientEntity clientEntity){
        return ClientDto.builder()
                .sharedKey(clientEntity.getSharedKey())
                .name(clientEntity.getName())
                .email(clientEntity.getEmail())
                .phone(clientEntity.getPhone())
                .startDate(clientEntity.getStartDate())
                .build();
    }

    public static ClientEntity clientDtoToClientEntity(ClientDto clientDto) {
        return ClientEntity.builder()
                .sharedKey(clientDto.getSharedKey())
                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .startDate(clientDto.getStartDate())
                .build();
    }
}

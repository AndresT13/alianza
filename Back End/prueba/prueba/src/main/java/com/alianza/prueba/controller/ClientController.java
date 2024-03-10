package com.alianza.prueba.controller;

import com.alianza.prueba.exception.BadRequestException;
import com.alianza.prueba.exception.ResourceNotFoundException;
import com.alianza.prueba.model.dto.ClientDto;
import com.alianza.prueba.model.payload.MessageResponse;
import com.alianza.prueba.services.IClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api/v1")
@Slf4j
public class ClientController {

    public static IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;

    }

        @GetMapping(path ="/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getClientBySharedKey(@PathVariable String id){

            ClientDto client = clientService.findBySharedKey(id);
            log.info("Se encontró el Shared key del cliente"+ clientService.findBySharedKey(id));


            if (client == null) {
                log.warn("No se encontro el Shared Key del cliente "+ clientService.findBySharedKey(id));
                throw new ResourceNotFoundException("Cliente", "id", id);

            }

            log.info("Se concontró correctamente el Shared Key: ");

            return new ResponseEntity<>(
                    MessageResponse.builder()
                            .message("")
                            .object(ClientDto.builder()
                                    .sharedKey(client.getSharedKey())
                                    .name(client.getName())
                                    .email(client.getEmail())
                                    .phone(client.getPhone())
                                    .startDate(client.getStartDate())
                                    .build())
                            .build()
                    , HttpStatus.OK);

        }


    @PostMapping(path = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> createClient(@RequestBody @Valid ClientDto clientDto) {

            ClientDto clientSave = null;

            try {
                clientSave = clientService.save(clientDto);
                log.info("Se creó correctamente el cliente");
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Guardado correctamente")
                        .object(clientDto.builder()
                                .sharedKey(sharedKey(clientDto))
                                .name(clientDto.getName())
                                .email(clientSave.getEmail())
                                .phone(clientDto.getPhone())
                                .startDate(clientDto.getStartDate())
                                .build())
                        , HttpStatus.CREATED);



            } catch (DataAccessException exDt) {
                log.warn("Se creó correctamente el cliente");
                throw new BadRequestException(exDt.getMessage());
            }
            }

    public String sharedKey(ClientDto clientDto){

        char characterOne;
        String lastName= null;
        lastName = clientDto.getName();

        characterOne =   clientDto.getSharedKey().charAt(0);


        String character =  String.valueOf(characterOne);
        char[] chars = character.toCharArray();



        String[] sharedKey = lastName.split(" ");

        String s = sharedKey[1];
        String[] Shared = new String[]{s};


        String Result =  String.valueOf(chars) + String.valueOf(s);


        return Result;
    }

}

















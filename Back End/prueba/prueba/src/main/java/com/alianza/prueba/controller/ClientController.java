package com.alianza.prueba.controller;

import com.alianza.prueba.exception.BadRequestException;
import com.alianza.prueba.exception.ResourceNotFoundException;
import com.alianza.prueba.model.dto.ClientDto;
import com.alianza.prueba.model.payload.MessageResponse;
import com.alianza.prueba.services.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("api/v1")
@Slf4j
public class ClientController {

    public static IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;

    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClients() {
        List<ClientDto> getList = clientService.findAll();
        if (getList == null || getList.isEmpty()) {
            throw new ResourceNotFoundException("clientes");
        }

        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(getList)
                        .build()
                , HttpStatus.OK);

    }

    @GetMapping(path="/sharedKey/{sharedKey}")
    public ResponseEntity<?> getNumberDocument(@PathVariable String sharedKey) {
        List<ClientDto> getList = clientService.findAll();
        ClientDto cliente = clientService.getSharedKey(sharedKey);

        if (cliente == null) {
            throw new ResourceNotFoundException("cliente", "número de documento: ", sharedKey);
        }
        return new ResponseEntity<>(
                MessageResponse.builder()
                        .message("")
                        .object(ClientDto.builder()

                                .sharedKey(cliente.getSharedKey())
                                .name(cliente.getName())
                                .email(cliente.getEmail())
                                .phone(cliente.getPhone())
                                .startDate(cliente.getStartDate())
                                .endDate(cliente.getEndDate())
                                .build())
                        .build()
                , HttpStatus.OK);
    }

    @RequestMapping(path = "/client/create" , method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto) {

            ClientDto clientSave = null;
            try {
                clientSave = clientService.save(clientDto);
                log.info("Se creó correctamente el cliente");
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("Guardado correctamente")
                        .object(ClientDto.builder()
                                .sharedKey(clientSave.getSharedKey())
                                .name(clientSave.getName())
                                .email(clientSave.getEmail())
                                .phone(clientSave.getPhone())
                                .startDate(clientSave.getStartDate())
                                .endDate(clientSave.getEndDate())
                                .build())
                        .build()
                        , HttpStatus.CREATED);

            } catch (DataAccessException exDt) {
                log.warn("Se creó correctamente el cliente");
                throw new BadRequestException(exDt.getMessage());
            }
            }

//    public String sharedKey(){
//    ClientDto clientDto = null;
//        char characterOne;
//        String lastName;
//        lastName = clientDto.getName();
//        characterOne =   clientDto.getSharedKey().charAt(0);
//        String character =  String.valueOf(characterOne);
//        char[] chars = character.toCharArray();
//        String[] sharedKey = lastName.split(" ");
//        String s = sharedKey[1];
//        System.out.println("Apellido: "+s);
//        String[] Shared = new String[]{s};
//
//        return  String.valueOf(chars) + String.valueOf(s);
//
//    }

}

















package com.alianza.prueba.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class ClientDto implements Serializable {

    private String sharedKey;
    private String name;
    private String phone;
    private String email;
    private Date startDate;
}

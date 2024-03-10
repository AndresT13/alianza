package com.alianza.prueba.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@ToString
public class ClientDto   implements Serializable{


    private String sharedKey;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private static final long serialVersionUID = 5462223600L;

}

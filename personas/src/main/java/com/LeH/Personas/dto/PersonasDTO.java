package com.LeH.personas.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonasDTO {
   // private String id;
    private Integer idpersona;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String genero;
    private String status;
    private List<CosasDTO> cosas;
}

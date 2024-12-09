package com.LeH.personas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CosasDTO {
    private Integer idcosa;
    private String tipo;
    private String nombre;
    private String descripcion;
    private Integer propietario;
    private String status;
}

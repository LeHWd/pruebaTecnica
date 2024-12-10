package com.LeH.cosas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CosasDetalladoDTO {
    private int idcosa;
    private String tipo;
    private String nombre;
    private String descripcion;
 
    private String status;
    private PersonasDTO propietario; 

    
}

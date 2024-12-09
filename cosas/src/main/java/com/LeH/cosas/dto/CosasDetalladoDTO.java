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
    private Integer propietario; 
    private String status;
    private String nombresPropietario;
    private String apellidosPropietario;
    private Integer edadPropietario;
    private String generoPropietario;
    private String statusPropietario;
    
}

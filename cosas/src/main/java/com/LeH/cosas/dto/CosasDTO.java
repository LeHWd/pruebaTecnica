package com.LeH.cosas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CosasDTO {
    public int idcosa;
    public String tipo;
    public String nombre;
    public String descripcion;
    public Integer propietario;
    public String status;
}

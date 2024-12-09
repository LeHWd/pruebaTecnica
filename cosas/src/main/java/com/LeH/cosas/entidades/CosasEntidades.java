package com.LeH.cosas.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CosasEntidades {
    @Id
    public Integer idcosa;
    public String tipo;
    public String nombre;
    public String descripcion;
    public Integer propietario;
    public String status;
}

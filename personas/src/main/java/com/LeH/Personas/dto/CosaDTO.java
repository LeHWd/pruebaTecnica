package com.LeH.Personas.dto;


public class CosaDTO {
    private int idCosa;
    private String tipo;
    private String nombre;
    private String descripcion;
    private String status;

    public int getIdCosa() {
        return idCosa;
    }

    public void setIdCosa(int idCosa) {
        this.idCosa = idCosa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

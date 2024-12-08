package com.LeH.Personas.dto;



import java.util.List;


public class PersonasDTO {

    private int idPersona;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String genero;
    private String status;
    private List<CosaDTO> cosas;


    //Constructor
    public PersonasDTO(int idPersona, String nombres, String apellidos, Integer edad, String genero, String status, List<CosaDTO> cosas){
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.status = status;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CosaDTO> getCosas() {
        return cosas;
    }

    public void setCosas(List<CosaDTO> cosas) {
        this.cosas = cosas;
    }
}

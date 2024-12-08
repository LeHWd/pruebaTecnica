package dto;

import java.util.List;

public class CosasDTO {

    private int idcosa;
    private String tipo;
    private String nombre;
    private String descripcion;
    private String propietario;
    private String status;
    private List<PersonasDTO> personas;


public CosasDTO(int idcosa, String tipo, String nombre, String descripcion, String propietario, String status, List<PersonasDTO> personas) {
    this.idcosa = idcosa;
    this.tipo = tipo;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.propietario = propietario;
    this.status = status;
}

}

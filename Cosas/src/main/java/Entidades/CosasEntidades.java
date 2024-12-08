package Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class CosasEntidades {

    @Id
    private int idcosa;
    private String tipo;
    private String nombre;
    private String descripcion;
    private String propietario;
    private String status;
}

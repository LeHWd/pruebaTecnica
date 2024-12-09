
package com.LeH.personas.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Getter;
import lombok.Setter;

@Document(collection="personas")
@Getter
@Setter
public class PersonaEntities {
    @Id
    private ObjectId id;
    private Integer idpersona;
    private String nombres;
    private String apellidos;
    private Integer edad;
    private String genero;
    private String status;
   
}


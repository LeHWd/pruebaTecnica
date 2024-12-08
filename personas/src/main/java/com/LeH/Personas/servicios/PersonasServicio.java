package com.LeH.Personas.servicios;

import com.LeH.Personas.dto.PersonasDTO;
import com.LeH.Personas.entidades.Personas;
import com.LeH.Personas.repositorios.PersonasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonasServicio {

    private final PersonasRepositorio personasRepositorio;

    @Autowired
    public PersonasServicio(PersonasRepositorio personasRepositorio) {
        this.personasRepositorio = personasRepositorio;
    }

    public List<PersonasDTO> obtenerPersonas() {
        List<Personas> personas = personasRepositorio.findAll(); // Obtener todas las personas de MongoDB
         if (personas == null) {
            return Collections.emptyList();  // Devuelve una lista vacía si el resultado es null
        }else {
        return personas.stream()
                .map(persona -> new PersonasDTO(persona.getIdPersona(), persona.getNombres(), persona.getApellidos(),
                        persona.getEdad(), persona.getGenero(), persona.getStatus(), null)) // No se incluyen cosas
                .collect(Collectors.toList());
        }
    }

    public PersonasDTO obtenerPersonaPorId(Integer idPersona) {
        Personas persona = personasRepositorio.findByIdPersona(idPersona); // Buscar por ID en MongoDB
        if (persona == null) {
            return null; // O lanzar una excepción si prefieres
        }
        return new PersonasDTO(persona.getIdPersona(), persona.getNombres(), persona.getApellidos(),
                persona.getEdad(), persona.getGenero(), persona.getStatus(), null); // No se incluyen cosas
    }
}

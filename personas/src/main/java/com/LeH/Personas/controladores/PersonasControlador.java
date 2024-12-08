package com.LeH.Personas.controladores;

import com.LeH.Personas.dto.PersonasDTO;
import com.LeH.Personas.servicios.PersonasServicio;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonasControlador {

    private final PersonasServicio personasServicio;

    public PersonasControlador(PersonasServicio personasServicio){
        this.personasServicio = personasServicio;
    }

    @GetMapping("/personas")
    public List<PersonasDTO> obtenerPersonas(){
        return personasServicio.obtenerPersonas();
    }

    @GetMapping("personas/{idPersona}")
    public PersonasDTO obtenerPersonaPorId(@PathVariable Integer idPersona){
        return personasServicio.obtenerPersonaPorId(idPersona);
    }
}

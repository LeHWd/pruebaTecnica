package com.LeH.personas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LeH.personas.Servicies.PersonasService;
import com.LeH.personas.dto.PersonasDTO;

@RestController
@RequestMapping("/personas")
public class PersonasController {

    @Autowired
    private PersonasService personasService;

    // Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<PersonasDTO>> obtenerTodasLasPersonas() {
        List<PersonasDTO> personas = personasService.obtenerPersonas();
        return ResponseEntity.ok(personas);
    }

    // Obtener una persona por ID
    @GetMapping("/{idpersona}")
    public ResponseEntity<PersonasDTO> obtenerPersonaPorId(@PathVariable Integer idpersona) {
        Optional<PersonasDTO> persona = personasService.obtenerPersonaPorId(idpersona);
        return persona.isPresent() ? ResponseEntity.ok(persona.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cosas/{propietario}")
    public ResponseEntity<List<PersonasDTO>> obtenerCosasPorPropietario(@PathVariable Integer propietario) {
        List<PersonasDTO> personas = personasService.obtenerCosasPorPropietario(propietario);
        return personas != null ? ResponseEntity.ok(personas) : ResponseEntity.notFound().build();
    }
}
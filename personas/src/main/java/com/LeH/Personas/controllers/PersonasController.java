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
import com.LeH.personas.dto.CosasDTO;
import com.LeH.personas.dto.PersonasDTO;
import com.LeH.personas.dto.PersonasDetalladoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;



@RestController
@RequestMapping("/personas")
public class PersonasController {

    @Autowired
    private PersonasService personasService;

    //////// Obtener todas las personas ////////
    @Operation(summary = "Obtiene todas las personas", description = "Devuelve una lista de todas las personas registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonasDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<PersonasDTO>> obtenerTodasLasPersonas() {
        List<PersonasDTO> personas = personasService.obtenerPersonas();
        return ResponseEntity.ok(personas);
    }

    ////////  Obtener una persona por ID ////////
    @Operation(summary = "Obtiene una Personas por ID", description = "Devuelve los detalles de una persona, especificada por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles de la cosa obtenidos correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonasDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cosa no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idpersona}")
    public ResponseEntity<PersonasDTO> obtenerPersonaPorId(@PathVariable Integer idpersona) {
        Optional<PersonasDTO> persona = personasService.obtenerPersonaPorId(idpersona);
        return persona.isPresent() ? ResponseEntity.ok(persona.get()) : ResponseEntity.notFound().build();
    }

    //////// Obtiene detallado de todas las personas con sus Cosas ////////
    @Operation(summary = "Obtiene el detalle de todas las Personas con sus Cosas", description = "Devuelve una lista detallada de todas las personas con todas sus Cosas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles obtenidos correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonasDetalladoDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/detallado")
    public ResponseEntity<List<PersonasDetalladoDTO>> obtenerTodasLasPersonasDetalladas() {
    List<PersonasDetalladoDTO> personasDetalladas = personasService.obtenerTodasLasPersonasDetalladas();
    return !personasDetalladas.isEmpty() ? ResponseEntity.ok(personasDetalladas) : ResponseEntity.noContent().build();
}

    //////// Obtiene detallado de persona por Id con sus Cosas ////////
    @Operation(summary = "Obtiene el detalle de cosas por propietario", description = "Devuelve una lista detallada de cosas asociadas a un propietario específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles obtenidos correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonasDetalladoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Propietario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/detallado/{idpersona}")
    public ResponseEntity<PersonasDetalladoDTO> obtenerPersonaDetallada(@PathVariable Integer idpersona) {
        PersonasDetalladoDTO personaDetallada = personasService.obtenerPersonaDetallada(idpersona);
        return personaDetallada != null ? ResponseEntity.ok(personaDetallada) : ResponseEntity.notFound().build();
    }
}
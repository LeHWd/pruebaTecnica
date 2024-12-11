package com.LeH.cosas.controladores;

import org.springframework.web.bind.annotation.RestController;

import com.LeH.cosas.dto.CosasDTO;
import com.LeH.cosas.dto.CosasDetalladoDTO;
import com.LeH.cosas.servicios.CosasServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/cosas")
public class CosasControlador {

    @Autowired
    private CosasServicio cosasServicio;

    //Obtiene todas las cosas
    @Operation(summary = "Obtiene todas las cosas", description = "Devuelve una lista de todas las cosas registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CosasDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<CosasDTO>> obtenerCosas() {
        List<CosasDTO> cosas = cosasServicio.obtenerCosas();
        return ResponseEntity.ok(cosas);
    }

    //Obtiene cosas por ID
    @Operation(summary = "Obtiene una cosa por ID", description = "Devuelve los detalles de una cosa, especificada por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles de la cosa obtenidos correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CosasDTO.class))),
        @ApiResponse(responseCode = "404", description = "Cosa no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idcosa}")
    public ResponseEntity<List<CosasDTO>> cosasPorId(@PathVariable Integer idcosa) {
    List<CosasDTO> cosa = cosasServicio.cosasPorId(idcosa);
    return cosa != null ? ResponseEntity.ok(cosa) : ResponseEntity.notFound().build();
  }

    //Obtiene Cosas por propietario
    @Operation(summary = "Obtiene cosas por propietario", description = "Devuelve una lista de cosas asociadas a un propietario específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CosasDTO.class))),
        @ApiResponse(responseCode = "404", description = "Propietario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
     @GetMapping("/por-propietario/{propietario}")
    public ResponseEntity<List<CosasDTO>> obtenerCosasPorPropietario(@PathVariable Integer propietario) {
        List<CosasDTO> cosas = cosasServicio.obtenerCosasPorPropietario(propietario);
        return cosas != null ? ResponseEntity.ok(cosas) : ResponseEntity.notFound().build();
    }

    //Obtiene detallado de Cosas con datos de propietario
    @Operation(summary = "Obtiene el detalle de todas las cosas", description = "Devuelve una lista detallada de todas las cosas con sus propietarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles obtenidos correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CosasDetalladoDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/detallado")
    public ResponseEntity<List<CosasDetalladoDTO>> cosasDetalladas() {
        List<CosasDetalladoDTO> cosas = cosasServicio.cosasDetalladas();
        return cosas != null && !cosas.isEmpty() ? ResponseEntity.ok(cosas) : ResponseEntity.notFound().build();
    }
    
    //Obtiene detallado de Cosas con datos por ID propietario
    @Operation(summary = "Obtiene el detalle de cosas por propietario", description = "Devuelve una lista detallada de cosas asociadas a un propietario específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalles obtenidos correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = CosasDetalladoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Propietario no encontrado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/detallado/{propietario}")
        public ResponseEntity<List<CosasDetalladoDTO>> cosasDetallado(@PathVariable Integer propietario) {
        List<CosasDetalladoDTO> cosas = cosasServicio.cosasDetallado(propietario); // Pasas el propietario al servicio
        return cosas != null && !cosas.isEmpty() ? ResponseEntity.ok(cosas) : ResponseEntity.notFound().build();
}

}

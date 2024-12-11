package com.LeH.personas.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.LeH.personas.dto.CosasDTO;

@FeignClient(name = "Personas-Cosas", url = "http://localhost:8082")
public interface PersonasFeign {

    @GetMapping("/cosas/por-propietario/{propietario}")
    List<CosasDTO> obtenerCosasPorPropietario(@PathVariable Integer propietario);
}

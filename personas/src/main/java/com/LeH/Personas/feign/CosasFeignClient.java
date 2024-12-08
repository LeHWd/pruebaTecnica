/*package com.LeH.Personas.feign;

import com.LeH.Personas.dto.CosaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Personas", url= "${feign.client.url.cosas}", fallback = CosasFeignClienteFallback.class) // Nombre del servicio registrado en Eureka
public interface CosasFeignClient {
    @GetMapping("/cosas/{idPersona}")
   List<CosaDTO> obtenerCosasPorIdPersona(@PathVariable("idPersona") Integer idPersona);

}*/

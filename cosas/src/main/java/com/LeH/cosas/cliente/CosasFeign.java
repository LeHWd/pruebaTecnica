package com.LeH.cosas.cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.LeH.cosas.dto.PersonasDTO;

@FeignClient(name = "persona-service", url = "http://localhost:8081")
public interface CosasFeign {

    @GetMapping("/personas/{idpersona}")
    PersonasDTO obtenerPersonaPorId(@PathVariable("idpersona") Integer idpersona);
}

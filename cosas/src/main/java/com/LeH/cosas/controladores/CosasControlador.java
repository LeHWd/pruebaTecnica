package com.LeH.cosas.controladores;

import org.springframework.web.bind.annotation.RestController;

import com.LeH.cosas.dto.CosasDTO;
import com.LeH.cosas.servicios.CosasServicio;
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

    @GetMapping
    public ResponseEntity<List<CosasDTO>> obtenerCosas(){
        List<CosasDTO> cosas = cosasServicio.obtenerCosas();
        return ResponseEntity.ok(cosas);
    }

    @GetMapping("/{idcosa}")
    public ResponseEntity<List<CosasDTO>> cosasPorId(@PathVariable Integer idcosa) {
    List<CosasDTO> cosa = cosasServicio.cosasPorId(idcosa);
    return cosa != null ? ResponseEntity.ok(cosa) : ResponseEntity.notFound().build();
}
    
}

package com.LeH.cosas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.LeH.cosas.entidades.CosasEntidades;

@Repository
public interface CosasRepositorio extends JpaRepository <CosasEntidades, Integer>{

    @Query(value = "select * from cosas", nativeQuery = true)
    List<CosasEntidades> encontrarCosas();

    @Query(value = "select * from cosas where idcosa = :idcosa", nativeQuery = true)
    List<CosasEntidades> CosaPorId(Integer idcosa);
}

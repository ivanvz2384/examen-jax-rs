package com.gapsi.examenjaxrs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gapsi.examenjaxrs.model.Articulo;


@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, String> {

}

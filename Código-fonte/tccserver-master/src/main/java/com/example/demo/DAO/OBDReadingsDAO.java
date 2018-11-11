package com.example.demo.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OBDReading;

/*Implementa um repositorio - Data access object- Facilita os CRUDS extendendo o CrudRepository :) */

public interface OBDReadingsDAO extends CrudRepository <OBDReading, Integer>  {
	


}
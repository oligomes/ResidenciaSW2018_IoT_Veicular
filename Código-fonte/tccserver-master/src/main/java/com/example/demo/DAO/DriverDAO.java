package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.model.Driver;

/*Implementa um repositorio - Data access object- Facilita os CRUDS extendendo o CrudRepository*/

public interface DriverDAO extends JpaRepository <Driver, Integer>  {

    @Query("SELECT d FROM Driver d WHERE LOWER(d.cpf) = LOWER(:cpf)")
	public Driver findByCpf (@Param("cpf") String cpf) ;
}

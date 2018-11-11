package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.VehicleDAO;
import com.example.demo.model.Drive;
import com.example.demo.model.Vehicle;

/*Serviço*/
@Service
public class VehicleService {
	
	/*This annotation allows Spring to resolve and inject collaborating beans into your bean.*/
	@Autowired
	private VehicleDAO vehicledao;
	

	/*Add vehicle*/
	public void createNewVehicle (Vehicle vehicle) {
		this.vehicledao.save(vehicle);
	}

	/*Get all vehicles*/
	public List<Vehicle> getAllVehicles(){
		return this.vehicledao.findAll();
	}
	
	/*Get all drives */
	public List<Drive> getAllDrives(String vin){
		List<Drive> drives = new ArrayList<>();
		return vehicledao.findByVin(vin).getDrives();
	}
	
	public Vehicle findByVin (String vin) {
		return vehicledao.findByVin(vin);
	}
}

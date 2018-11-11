package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.DriverDAO;
import com.example.demo.model.Drive;
import com.example.demo.model.Driver;

@Service
public class DriverService {

	// This annotation allows Spring to resolve and inject collaborating beans into your bean.
	@Autowired
	private DriverDAO driverdao;
	
	// Add Driver
	public void createNewDriver(Driver driver) {
		this.driverdao.save(driver);
	}
	
	// Get all Drivers
	public List<Driver> getAllDrivers() {
		return this.driverdao.findAll();
	}
	
	// Get all Drives
	public List<Drive> getAllDrives(String cpf) {
		return driverdao.findByCpf(cpf).getDrives();
	}
	
	public Driver findByCpf(String cpf) {
		return driverdao.findByCpf(cpf);
	}
}

package com.example.demo.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Drive;
import com.example.demo.model.Driver;
import com.example.demo.model.OBDReading;
import com.example.demo.model.Vehicle;
import com.example.demo.service.DriveService;
import com.example.demo.service.DriverService;
import com.example.demo.service.OBDReadingService;
import com.example.demo.service.VehicleService;

/**
 * @author José Suen
 */

@RestController


public class RESTApiController {
	/*Autowired wires beans seamlessly*/
	
	@Autowired
	private DriveService driveservice;

	@Autowired
	private VehicleService vehicleservice;
	
	@Autowired
	private OBDReadingService obdreadingservice;
	
	@Autowired
	private DriverService driverservice;
	
	//VEHICLE OPERATIONS
	@RequestMapping(value = "/api/vehicle", method = RequestMethod.GET)
	@ResponseBody
    public List<Vehicle> returnAllVehicles() {
        return vehicleservice.getAllVehicles();
    }
	
	//DRIVE OPERATIONS
	@PostMapping("/api/vehicle/{vin}/drive")
    public Drive createdrive(@PathVariable (value = "vin") String vin, @Valid @RequestBody Drive drive) {
		drive.setVehicle(vehicleservice.findByVin(vin));
		driveservice.createNewDrive(drive);
		return drive;
		
	}
	
	//OBDREADING OPERATIONS
	@PostMapping("/api/drive/{driveid}/obdreading")
    public void createreading(@PathVariable (value = "driveid") int driveid, @Valid @RequestBody OBDReading reading) {
		reading.setDrive(driveservice.findById(driveid).get());
		obdreadingservice.createNewReading(reading);
	}
	


}

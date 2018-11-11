package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle {
	
	/*Attributes*/
	@Id
	String vin;
	private String brand;
	private String model;
	private int year;
	private double power;
	private double torque;
	private double eng_size;
	
	@OneToMany(mappedBy = "vehicle")
	@JsonIgnore
	private List<Drive> drives = new ArrayList<Drive>();
	

	/*Constructors */
	
	public Vehicle() {
		
	}
	
	/*Getters and setters*/
	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		this.power = power;
	}
	public double getTorque() {
		return torque;
	}
	public void setTorque(double torque) {
		this.torque = torque;
	}
	public double getEng_size() {
		return eng_size;
	}
	public void setEng_size(double eng_size) {
		this.eng_size = eng_size;
	}
	
	
	public List<Drive> getDrives() {
		return drives;
	}

	public void setDrives(List<Drive> drives) {
		this.drives = drives;
	}

	public Drive map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}

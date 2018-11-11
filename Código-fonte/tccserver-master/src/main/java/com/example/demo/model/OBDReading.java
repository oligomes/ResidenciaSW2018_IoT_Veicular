package com.example.demo.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*Anotaçao entity traz a ideia de "BD". Cada atributo da classe vira uma coluna*/
@Entity
public class OBDReading implements Serializable {
	
	
	/*Id eh a chave primaria*/
	@Id
	@GeneratedValue
	int id; 
	
    double latitude, longitude;
    long timestamp;
    
    @ManyToOne
    @JoinColumn(name = "drive_id")
    private Drive drive;
    
    public Map<String, String> getReadings() {
		return readings;
	}

	public void setReadings(Map<String, String> readings) {
		this.readings = readings;
	}

	@ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> readings ;

    /*generic construtor*/
    public OBDReading() {
    }
    
    /*Getters e Setters*/
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	public Drive getDrive() {
		return drive;
	}

	public void setDrive(Drive drive) {
		this.drive = drive;
	}


	
}

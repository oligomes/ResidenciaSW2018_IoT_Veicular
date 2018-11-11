package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.OBDReadingsDAO;
import com.example.demo.model.OBDReading;

/*Serviço*/
@Service
public class OBDReadingService {
	
	/*This annotation allows Spring to resolve and inject collaborating beans into your bean.*/
	@Autowired
	private OBDReadingsDAO obdreadingdao;
	
	/*Get all readings*/
	public List<OBDReading> getAllReadings(){
		List<OBDReading> obdreadings = new ArrayList<>();
		this.obdreadingdao.findAll().forEach(obdreadings::add);
		return obdreadings;
	}

	/*Adicionar reading*/
	public void createNewReading(OBDReading reading) {
		this.obdreadingdao.save(reading);
	}
}



/*Codiog velho antes de usar repositorio*/
/*public Collection<OBDReading> getAllReadings(){
return obdreadingdao.getAllReadings();
}

public OBDReading getReadingById(int id) {
return this.obdreadingdao.getReadingById(id);
}


*/
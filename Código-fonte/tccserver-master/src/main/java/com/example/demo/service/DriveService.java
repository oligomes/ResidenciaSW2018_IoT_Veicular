package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.DriveDAO;
import com.example.demo.model.Drive;

@Service
public class DriveService {
	
	/*This annotation allows Spring to resolve and inject collaborating beans into your bean.*/
	@Autowired
	private DriveDAO drivedao;

	/*Add drive*/
	public void createNewDrive (Drive drive) {
		this.drivedao.save(drive);
	}
	
	public Optional<Drive> findById(int driveid) {
		return drivedao.findById(driveid);
	}
}

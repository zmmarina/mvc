package com.gft.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.challenge.entities.Danceclass;
import com.gft.challenge.repositories.DanceclassRepository;

@Service
public class DanceclassService {
	
	@Autowired
	private DanceclassRepository danceclassRepository;
	
	
	public Danceclass saveDanceclass(Danceclass danceclass) {
		
		return danceclassRepository.save(danceclass);
	}
		
	public List<Danceclass> danceclassList() {
		
		return danceclassRepository.findAll();
	}
	
	public Danceclass getDanceclass(Long id) throws Exception {
		
		Optional<Danceclass> danceclass = danceclassRepository.findById(id);
		
		if(danceclass.isEmpty()) {
			throw new Exception("Class not found!");
		}
		
		return danceclass.get();
	}
	
	public void deleteDanceclass(Long id) {
		danceclassRepository.deleteById(id);
	}
}

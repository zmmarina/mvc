package com.gft.challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.challenge.entities.Student;
import com.gft.challenge.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;	
	
	public Student saveStudent(Student student) {
		
		return studentRepository.save(student);
	}
	
	public Student getStudent(Long id) throws Exception {
		
		Optional<Student> student = studentRepository.findById(id);
		
		if(student.isEmpty()) {
			throw new Exception("Student not found!");		
		
		}
		
		return student.get();
	}
	
	public void deleteStudent(Long id) {
		
		studentRepository.deleteById(id);
	}
	
	public List<Student> studentList(){
		
		return studentRepository.findAll();
	}
	
	public List<Student> findStudentByName(String name){
		
		return studentRepository.findStudentByName(name);
	}

	
}

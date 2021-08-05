package com.gft.challenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.challenge.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query(value="select * from Student s where s.name like %:name%", nativeQuery=true)
	List<Student> findStudentByName(@Param("name") String name);

}

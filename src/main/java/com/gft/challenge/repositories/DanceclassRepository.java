package com.gft.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.challenge.entities.Danceclass;

@Repository
public interface DanceclassRepository extends JpaRepository<Danceclass, Long> {

}

package com.example.microservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservice.demo.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}

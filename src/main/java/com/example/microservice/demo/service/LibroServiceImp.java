package com.example.microservice.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.demo.model.Libro;
import com.example.microservice.demo.repository.LibroRepository;

@Service
public class LibroServiceImp implements ILibroService {

	@Autowired
	private LibroRepository libroRepository;
	
	@Override
	public void registarLibro(Libro libro) {
		libroRepository.save(libro);
	}

	@Override
	public Libro obtenerLibro(Long idLibro) {
		Libro libro = null;
		Optional<Libro> libroTemp = libroRepository.findById(idLibro); 
		if (libroTemp.isPresent()) {
			return libro = libroTemp.get();
		}
		return libro;
	}

	@Override
	public List<Libro> obtenerListadoLibro() {
		return libroRepository.findAll();
	}

	@Override
	public void actualizarLibro(Libro libro) {
		libroRepository.save(libro);
	}

	@Override
	public void eliminarLibro(Long idLibro) {
		Libro libro = null;
		libro = obtenerLibro(idLibro);
		if (libro != null) {
			libroRepository.delete(libro);
		}
	}

}

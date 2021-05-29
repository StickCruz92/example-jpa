package com.example.microservice.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.demo.model.Autor;
import com.example.microservice.demo.repository.AutorRepository;

@Service
public class AutorServiceImp implements IAutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public void registarAutor(Autor autor) {
		autorRepository.save(autor);
	}

	@Override
	public Autor obtenerAutor(Long id) {
		Autor autor = null;
		Optional<Autor> autorTemp = autorRepository.findById(id);
		if (autorTemp.isPresent()) {
			return autor = autorTemp.get();
		}
		return autor;
	}

	@Override
	public List<Autor> obtenerListadoAutor() {
		return autorRepository.findAll();
	}

	@Override
	public void actualizarAutor(Autor autor) {
		autorRepository.save(autor);
	}

	@Override
	public void eliminarAutor(Autor autor) {
	     autorRepository.delete(autor);
	}

}

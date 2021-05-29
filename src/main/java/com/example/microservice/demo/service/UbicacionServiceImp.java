package com.example.microservice.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.demo.model.Ubicacion;
import com.example.microservice.demo.repository.UbicacionRepository;

@Service
public class UbicacionServiceImp implements IUbicacionService {

	@Autowired
	private UbicacionRepository ubicacionRepository;
	
	@Override
	public List<Ubicacion> obtenerListaUbicaciones() {
		return ubicacionRepository.findAll();
	}

	@Override
	public Ubicacion obtenerUbicacion(Long id) {
		Ubicacion ubicacion = null;
		Optional<Ubicacion> ubiTem = ubicacionRepository.findById(id);
		if (ubiTem.isPresent()) {
			ubicacion = ubiTem.get();
		}
		return ubicacion;
	}

	@Override
	public void registarUbicacion(Ubicacion ubicacion) {
		ubicacionRepository.save(ubicacion);
	}

	@Override
	public Ubicacion actualizarUbicacion(Ubicacion ubicacion) {
		ubicacionRepository.save(ubicacion);
		Ubicacion ubicacionActualizado = ubicacionRepository.getById(ubicacion.getIdUbicacion());
		return ubicacionActualizado;
	}

	@Override
	public void eliminarUbicacion(Long id) {
		Ubicacion ubicacion = ubicacionRepository.getById(id);
		ubicacionRepository.delete(ubicacion);
	}

}

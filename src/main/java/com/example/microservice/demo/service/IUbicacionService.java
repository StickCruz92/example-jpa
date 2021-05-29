package com.example.microservice.demo.service;

import java.util.List;

import com.example.microservice.demo.model.Ubicacion;

public interface IUbicacionService {

	public List<Ubicacion> obtenerListaUbicaciones();
	public Ubicacion obtenerUbicacion(Long id);
	public void registarUbicacion(Ubicacion ubicacion);
	public Ubicacion actualizarUbicacion(Ubicacion ubicacion);
	public void eliminarUbicacion(Long id);
	
}

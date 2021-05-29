package com.example.microservice.demo.service;

import java.util.List;

import com.example.microservice.demo.model.Libro;

public interface ILibroService {
	
	public void registarLibro(Libro libro);
	public Libro obtenerLibro(Long idLibro);
	public List<Libro> obtenerListadoLibro();
	public void actualizarLibro(Libro libro);
	public void eliminarLibro(Long idLibro);
	
}

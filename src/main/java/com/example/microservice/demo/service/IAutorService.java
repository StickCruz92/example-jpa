package com.example.microservice.demo.service;

import java.util.List;

import com.example.microservice.demo.model.Autor;

public interface IAutorService {

	public void registarAutor(Autor autor);
	public Autor obtenerAutor(Long idAutor);
	public List<Autor> obtenerListadoAutor();
	public void actualizarAutor(Autor autor);
	public void eliminarAutor(Autor autor);
}

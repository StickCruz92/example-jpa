package com.example.microservice.demo.viewmodel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AutorLibro {

    private Long idAutor;
	private String nombre;	
	private String nacionalidad;
	private List<LibroAutor> libros = new ArrayList<>();
			
}

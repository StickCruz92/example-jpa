package com.example.microservice.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.microservice.demo.model.Autor;
import com.example.microservice.demo.model.Libro;
import com.example.microservice.demo.service.IAutorService;
import com.example.microservice.demo.service.ILibroService;
import com.example.microservice.demo.viewmodel.LibroAutor;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/libro")
@Slf4j
public class LibroController {
	
	@Autowired
	private IAutorService autorService;
	
	@Autowired
	private ILibroService libroService;

	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Libro>> getListLibros() {
		  log.info("Finding all autores");
		List<Libro> libros = new ArrayList<Libro>();
		libros = libroService.obtenerListadoLibro();
		if (libros.isEmpty()) {
			return new ResponseEntity<List<Libro>>(libros, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Libro>>(libros, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Libro> getAutor(@PathVariable("id") Long idLibro) {
		Libro libro = null;
		log.info("Buscando id autor" + idLibro);
		libro = libroService.obtenerLibro(idLibro);
		if (libro != null) {
			 log.info("Encontrado libro" + libro.getNombre());
			return new ResponseEntity<Libro>(libro, HttpStatus.OK);
		} 
		return new ResponseEntity<Libro>(libro, HttpStatus.NO_CONTENT); 
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> createAutor(@RequestBody LibroAutor libroAut,
			UriComponentsBuilder ucBuilder) {
		
		Libro libro = new Libro();
		Autor autor = null;
		autor = autorService.obtenerAutor(libroAut.getIdAutor());

		if (autor != null) {
			libro.setNombre(libroAut.getNombre());
			libro.setAutor(autor);
			libroService.registarLibro(libro);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/v1/libro/{id}")
				.buildAndExpand(libro.getIdLibro()).toUri());
		log.info("Url del libro nuevo es: " + headers);

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}
	
	@PutMapping("{id}")
	public @ResponseBody ResponseEntity<Libro> updateAutor(@PathVariable("id") Long idLibro,
			@RequestBody LibroAutor libroAut) {
		
		Libro libro = libroService.obtenerLibro(idLibro);

		if (libro != null) {
			libro.setNombre(libroAut.getNombre());
			libroService.actualizarLibro(libro);
			return new ResponseEntity<Libro>(HttpStatus.OK);
		}

		return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("{id}")
	public @ResponseBody ResponseEntity<?> deleteAutor(@PathVariable("id") Long idLibro) {
		Libro libro = libroService.obtenerLibro(idLibro);
		if (libro != null) {
			libroService.eliminarLibro(libro.getIdLibro());
		}
		return new ResponseEntity<Libro>(HttpStatus.OK);
	}
	
}

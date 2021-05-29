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
//import com.example.microservice.demo.service.ILibroService;
import com.example.microservice.demo.viewmodel.AutorLibro;
import com.example.microservice.demo.viewmodel.LibroAutor;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/autor")
@Slf4j
public class AutorController {
	
	@Autowired
	private IAutorService autorService;
	
	//@Autowired
	//private ILibroService libroService;

	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Autor>> getListAutores() {
		  log.info("Finding all autores");
		List<Autor> autores = new ArrayList<Autor>();
		autores = autorService.obtenerListadoAutor();
		if (autores.isEmpty()) {
			return new ResponseEntity<List<Autor>>(autores, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Autor>>(autores, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Autor> getAutor(@PathVariable("id") Long idAuror) {
		Autor autor = null;
		log.info("Buscando id autor" + idAuror);
		autor = autorService.obtenerAutor(idAuror);
		if (autor != null) {
			//log.info("Encontrado autor" + autor.getNombre());
			return new ResponseEntity<Autor>(autor, HttpStatus.OK);
		} 
		return new ResponseEntity<Autor>(autor, HttpStatus.NO_CONTENT); 
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> createAutor(@RequestBody AutorLibro autlibro, UriComponentsBuilder ucBuilder) {
		
		Autor autor = new Autor();
		autor.setNombre(autlibro.getNombre());
		autor.setNacionalidad(autlibro.getNacionalidad());
		
		if (!autlibro.getLibros().isEmpty()) {
			for (LibroAutor lb : autlibro.getLibros()) {
				Libro libro= new Libro();
				libro.setNombre(lb.getNombre());
				//libroService.registarLibro(libro);
				autor.addLibro(libro);
				autorService.registarAutor(autor);
			}
		} 
		else 
		{
			autorService.registarAutor(autor);
		}
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/autor/{id}")
        		.buildAndExpand(autor.getIdAutor()).toUri());
        log.info("Url del autor nuevo es: " + headers);
		
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Autor> updateAutor(@PathVariable("id") Long idAutor,
			@RequestBody AutorLibro autlibro) {
		 log.info("Actualizar el autor de: " + idAutor);
		 Autor autTemp = null;
		 autTemp = autorService.obtenerAutor(idAutor);
		
		if (autTemp != null) {
			 log.info("Entramos al dato: " + autTemp.getNombre());
			 autTemp.setNombre(autlibro.getNombre());
			 autTemp.setNacionalidad(autlibro.getNacionalidad());
			 autorService.actualizarAutor(autTemp);
			return new ResponseEntity<Autor>(HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> deleteAutor(@PathVariable("id") Long idAutor) {
		 log.info("Eliminar Autor el dato de: " + idAutor);
		 Autor autorTemp = null;
		 autorTemp = autorService.obtenerAutor(idAutor);
		if (autorTemp != null) {
			autorService.eliminarAutor(autorTemp);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}

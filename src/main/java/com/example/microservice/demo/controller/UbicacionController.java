package com.example.microservice.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.demo.model.Ubicacion;
import com.example.microservice.demo.service.IUbicacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/ubicacion")
public class UbicacionController {

	@Autowired
	private IUbicacionService ubicacionService;
	
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Ubicacion>> obtenerListadoEmpleados() {
		log.info("Traer todas las ubicaciones");
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones = ubicacionService.obtenerListaUbicaciones();
		if (ubicaciones.isEmpty()) {
			return new ResponseEntity<List<Ubicacion>>(ubicaciones, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Ubicacion>>(ubicaciones, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Ubicacion> obtenerEmpleado(@PathVariable("id") Long idUbicacion) {
		log.info("Traer la ubicación "+ idUbicacion);
		Ubicacion ubicacion = null;
		ubicacion = ubicacionService.obtenerUbicacion(idUbicacion);
		if (ubicacion != null) {
			log.info("Encontre la ubicación " + ubicacion.getDireccion());
			return new ResponseEntity<Ubicacion>(ubicacion, HttpStatus.OK);
		} else {
			return new ResponseEntity<Ubicacion>(ubicacion, HttpStatus.NO_CONTENT);
		}
	}
}

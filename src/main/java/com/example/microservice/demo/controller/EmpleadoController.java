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
import lombok.extern.slf4j.Slf4j;

import com.example.microservice.demo.model.Empleado;
import com.example.microservice.demo.model.Ubicacion;
import com.example.microservice.demo.service.IEmpleadoService;
import com.example.microservice.demo.viewmodel.EmpleadoDireccion;


@RestController
@RequestMapping("/v1/empleado")
@Slf4j
public class EmpleadoController {

	@Autowired
	private IEmpleadoService empleadoService; 
	
	@GetMapping("/all")
	public @ResponseBody ResponseEntity<List<Empleado>> getListEmpleados() {
		  log.info("Finding all empleados");
		List<Empleado> empleados = new ArrayList<Empleado>();
		empleados = empleadoService.obtenerListaEmpleados();
		if (empleados.isEmpty()) {
			return new ResponseEntity<List<Empleado>>(empleados, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Empleado>>(empleados, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Empleado> getEmpleado(@PathVariable("id") Long idEmpleado) {
		Empleado empleado = null;
		log.info("Buscando id empleado" + idEmpleado);
		empleado = empleadoService.obtenerEmpleado(idEmpleado);
		if (empleado != null) {
			log.info("Encontrado empleado" + empleado.getNombre());
			return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
		} 
		return new ResponseEntity<Empleado>(empleado, HttpStatus.NO_CONTENT); 
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> createEmpleado(@RequestBody EmpleadoDireccion empDir, UriComponentsBuilder ucBuilder) {
		
		Empleado empleado = new Empleado(empDir.getNombre(), empDir.getApellido(), empDir.getFechaNacimiento());
		Ubicacion ubi = new Ubicacion(empDir.getCiudad(), empDir.getDepartamento(), empDir.getDireccion());
		empleado.setUbicacion(ubi);
		ubi.setEmpleado(empleado);
		empleadoService.registarEmpleado(empleado);
		//ubicacionService.registarUbicacion(ubi);
 
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/empleado/{id}")
        		.buildAndExpand(empleado.getIdEmpleado()).toUri());
        log.info("Url " + headers);

        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Empleado> updateEmpleado(@PathVariable("id") Long idEmpleado,
			@RequestBody EmpleadoDireccion empDir) {
		 log.info("Actualizar el dato de: " + idEmpleado);
		Empleado emplTemp = null;
		emplTemp = empleadoService.obtenerEmpleado(idEmpleado);
		
		if (emplTemp != null) {
			 log.info("Entramos al dato: " + emplTemp.getNombre());
			emplTemp.setNombre(empDir.getNombre());
			emplTemp.setApellido(empDir.getApellido());
			emplTemp.setFechaNacimiento(empDir.getFechaNacimiento());
			emplTemp.getUbicacion().setCiudad(empDir.getCiudad());
			emplTemp.getUbicacion().setDepartamento(empDir.getDepartamento());
			emplTemp.getUbicacion().setDireccion(empDir.getDireccion());
			empleadoService.actualizarEmpleado(emplTemp);
			return new ResponseEntity<Empleado>(HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Empleado>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> deleteEmpleado(@PathVariable("id") Long idEmpleado) {
		 log.info("Eliminar el dato de: " + idEmpleado);
		Empleado emplTemp = null;
		emplTemp = empleadoService.obtenerEmpleado(idEmpleado);
		if (emplTemp != null) {
			empleadoService.eliminarEmpleado(emplTemp);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
}

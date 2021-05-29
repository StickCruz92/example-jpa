package com.example.microservice.demo.viewmodel;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmpleadoDireccion {

	private String nombre;
	
	private String apellido;
	
	private LocalDate fechaNacimiento;
	
    private Long idUbicacion;
	
	private String ciudad;
	
	private String departamento;
	
	private String direccion;

}

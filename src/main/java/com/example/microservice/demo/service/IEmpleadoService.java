package com.example.microservice.demo.service;

import java.util.List;

import com.example.microservice.demo.model.Empleado;

public interface IEmpleadoService {

	public List<Empleado> obtenerListaEmpleados();
	public Empleado obtenerEmpleado(Long id);
	public List<Empleado> obtenerEmpleadoNombre(String nombre);
	public void registarEmpleado(Empleado empleado);
	public Empleado actualizarEmpleado(Empleado empleado);
	public void eliminarEmpleado(Empleado empleado);
	

}

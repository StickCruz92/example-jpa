package com.example.microservice.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservice.demo.model.Empleado;
import com.example.microservice.demo.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceEmp implements IEmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public List<Empleado> obtenerListaEmpleados() {
		return empleadoRepository.findAll();
	}

	@Override
	public Empleado obtenerEmpleado(Long id) {
		Empleado empleado = null;
		Optional<Empleado> empTem = empleadoRepository.findById(id);
		if (empTem.isPresent()) {
			empleado = empTem.get();
		}
		return empleado;
	}

	@Override
	public void registarEmpleado(Empleado empleado) {
		empleadoRepository.save(empleado);
	}

	@Override
	public Empleado actualizarEmpleado(Empleado empleado) {
		  empleadoRepository.save(empleado);
		  Empleado empleadoActualizado = obtenerEmpleado(empleado.getIdEmpleado());
		return empleadoActualizado;
	}

	@Override
	public void eliminarEmpleado(Empleado empleado) {
		 empleadoRepository.delete(empleado);
	}

	@Override
	public List<Empleado> obtenerEmpleadoNombre(String nombre) {
		return empleadoRepository.findByNombre(nombre);
	}
	


}

package com.example.microservice.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UBICACION")
public class Ubicacion implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_UBICACION")
	private Long idUbicacion;
	
	@Column(name = "CIUDAD")
	private String ciudad;
	
	@Column(name = "DEPARTAMENTO")
	private String departamento;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@JsonIgnore
	@OneToOne(cascade = {CascadeType.ALL},  optional = false)
	@JoinColumn(name = "ID_EMPLEADO", nullable = false)
	private Empleado empleado;

	
	@Override
	public String toString() {
		return "Ubicacion [idUbicacion=" + idUbicacion + ", ciudad=" + ciudad + ", departamento=" + departamento
				+ ", direccion=" + direccion + "]";
	}

	
	public Ubicacion(String ciudad, String departamento, String direccion) {
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.direccion = direccion;
	}
	
	

	public Ubicacion(String ciudad, String departamento, String direccion, Empleado empleado) {
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.direccion = direccion;
		this.empleado = empleado;
	}
	
	
}

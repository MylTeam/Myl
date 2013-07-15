package com.myl.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Carta")
public class Carta {
		
	private Integer id;
	private String nombre;
	private String efecto;
	private String leyenda;
	private String numero;
	private String tipo;
	private String raza;
	private String Frecuencia;
	private Integer Coste;
	private Integer Fuerza;
	private Integer idExposicion;
		
	private Edicion edicion;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "CartaId")	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "CartaNb")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name = "CartaEf")
	public String getEfecto() {
		return efecto;
	}
	public void setEfecto(String efecto) {
		this.efecto = efecto;
	}
	@Column(name = "CartaLy")
	public String getLeyenda() {
		return leyenda;
	}
	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}
	@Column(name = "CartaNo")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	@Column(name = "CartaTp")
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Column(name = "CartaRz")
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	@Column(name = "CartaFr")
	public String getFrecuencia() {
		return Frecuencia;
	}
	public void setFrecuencia(String frecuencia) {
		Frecuencia = frecuencia;
	}
	@Column(name = "CartaCt")
	public Integer getCoste() {
		return Coste;
	}
	public void setCoste(Integer coste) {
		Coste = coste;
	}
	@Column(name = "CartaFz")
	public Integer getFuerza() {
		return Fuerza;
	}
	public void setFuerza(Integer fuerza) {
		Fuerza = fuerza;
	}		
	@Column(name = "EdicionId")
	public Integer getIdExposicion() {
		return idExposicion;
	}
	public void setIdExposicion(Integer idExposicion) {
		this.idExposicion = idExposicion;
	}
	
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "EdicionId", referencedColumnName = "EdicionId", insertable = false, updatable = false) })
	public Edicion getEdicion() {
		return edicion;
	}
	public void setEdicion(Edicion edicion) {
		this.edicion = edicion;
	}
	
	
		
}
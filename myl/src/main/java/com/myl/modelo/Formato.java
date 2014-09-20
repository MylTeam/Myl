package com.myl.modelo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "formato_tbl")
public class Formato {
	private Integer id;
	private String nombre;
	
	private List<Deck> decks;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "FormatoId")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "FormatoNb")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@OneToMany(mappedBy = "formato")
	public List<Deck> getDecks() {
		return decks;
	}
	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}			
}
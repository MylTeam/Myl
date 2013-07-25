package com.myl.controller;

import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Usuario;
import com.myl.modelo.Deck;
import com.myl.negocio.DeckNegocio;
import com.myl.negocio.UsuarioNegocio;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {"actionName", "usuario" })
})
public class UsuarioController extends ActionSupport implements ModelDriven<Usuario> {

	private static final long serialVersionUID = 8585016072024421730L;
	private Integer idSel;
	
	private Usuario model=null;
	private Usuario usuario;
	private UsuarioNegocio usuarioNegocio;
	private DeckNegocio deckNegocio;
	private List<Deck> decks;
	
	@SkipValidation
	public HttpHeaders index() {
		
		usuario=usuarioNegocio.findById(1);
		idSel=1;
		
		Deck deckAux=new Deck();
		deckAux.setUsuario(usuario);
		
		decks=deckNegocio.findByExample(deckAux);
		
		
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	
	public Integer getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Deck> getDecks() {
		return decks;
	}


	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}


	public DeckNegocio getDeckNegocio() {
		return deckNegocio;
	}


	public void setDeckNegocio(DeckNegocio deckNegocio) {
		this.deckNegocio = deckNegocio;
	}


	@Override
	public Usuario getModel() {
		if (model == null) {
			model = new Usuario();
		}
		return model;
	}

	public void setModel(Usuario model) {
		this.model = model;
	}

	public UsuarioNegocio getUsuarioNegocio() {
		return usuarioNegocio;
	}


	public void setUsuarioNegocio(UsuarioNegocio usuarioNegocio) {
		this.usuarioNegocio = usuarioNegocio;
	}


		

}


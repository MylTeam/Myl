package com.myl.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myl.modelo.DeckCarta;
import com.myl.modelo.Usuario;
import com.myl.modelo.Deck;
import com.myl.negocio.DeckNegocio;
import com.myl.negocio.UsuarioNegocio;
import com.myl.util.NombreObjetosSesion;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Named
@Results({ 
	@Result(name = "success", type = "redirectAction", params = {"actionName", "usuario" })	
})
public class UsuarioController extends ActionSupport implements ModelDriven<Usuario> {

	private static final long serialVersionUID = 1L;

	private Integer idSel;
	
	private Usuario model=null;
	private Usuario usuario;
	private UsuarioNegocio usuarioNegocio;
	private DeckNegocio deckNegocio;
	private List<Deck> lista;
	private Deck deck;
	
	private String confirmPass;
	private Integer deckId;
	
	
	@SkipValidation
	public HttpHeaders index() {
		
		usuario=(Usuario) ActionContext.getContext().getSession().get(NombreObjetosSesion.USUARIO);		
		idSel=usuario.getIdUsuario();
		
		Deck deckAux=new Deck();
		deckAux.setUsuarioId(usuario.getIdUsuario());
		
		lista=deckNegocio.findByExample(deckAux);
		
		
		
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	
	@SkipValidation
	public String edit() {
		
		
		return "edit";
	}
	
	public void validateUpdate(){
		
	}
		
	@SkipValidation
	public String update() {
	
		return "success";
	}

	@SkipValidation
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public void validateDestroy() {

	}

	
	@SkipValidation
	public String destroy() {
		
		return "success";
	}
	
	
	public void setDeckPredeterminado(){
		usuario=(Usuario) ActionContext.getContext().getSession().get(NombreObjetosSesion.USUARIO);
		
		usuario.setDeckPred(deckId);
		
		usuarioNegocio.save(usuario);
		
	}
	
	
	public Integer getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {		
		this.idSel = idSel;
		if (idSel != null) {
			model = usuarioNegocio.findById(idSel);
		}
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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


	public List<Deck> getLista() {
		return lista;
	}


	public void setLista(List<Deck> lista) {
		this.lista = lista;
	}


	public Deck getDeck() {
		return deck;
	}


	public void setDeck(Deck deck) {
		this.deck = deck;
	}


	public String getConfirmPass() {
		return confirmPass;
	}


	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}


	public Integer getDeckId() {
		return deckId;
	}


	public void setDeckId(Integer deckId) {
		this.deckId = deckId;
	}


		

}


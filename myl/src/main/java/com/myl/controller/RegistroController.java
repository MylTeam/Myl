package com.myl.controller;

import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Deck;
import com.myl.modelo.Usuario;
import com.myl.negocio.DeckNegocio;
import com.myl.negocio.UsuarioNegocio;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Named
@Results({ @Result(name = "registered", type = "redirectAction", params = {
		"actionName", "login" }) })
public class RegistroController extends ActionSupport implements
		ModelDriven<Usuario> {

	private static final long serialVersionUID = 1L;

	private Integer idSel;

	private Usuario model = null;
	private Usuario usuario;
	private UsuarioNegocio usuarioNegocio;
	private DeckNegocio deckNegocio;
	private List<Deck> lista;
	private Deck deck;

	private String confirmPass;

	@SkipValidation
	public String editNew() {

		return "editNew";
	}

	public void validateCreate() {
		if (!model.getPassword().equals(confirmPass)) {
			addActionError("Las contraseñas no son iguales");
		}

		Usuario aux = new Usuario();
		aux.setLogin(model.getLogin());

		if (!usuarioNegocio.findByExample(aux).isEmpty()) {
			addActionError("Nombre de usuario no disponible");
		}
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "model.login", type = ValidatorType.FIELD, key = "Introduce un nombre de usuario"),			
			@RequiredStringValidator(fieldName = "model.password", type = ValidatorType.FIELD, key = "Introduce la contraseña"),
			@RequiredStringValidator(fieldName = "confirmPass", type = ValidatorType.FIELD, key = "Confirma la contraseña")},
			regexFields = {
			@RegexFieldValidator(fieldName = "model.login", type = ValidatorType.FIELD, key = "Nombre de usuario no válido", expression = "[A-Z[a-z][0-9]]+")
			}
			)
	public HttpHeaders create() {
		model.setDeckPred(0);
		model = usuarioNegocio.save(model);

		return new DefaultHttpHeaders("registered").setLocationId(model
				.getIdUsuario());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public List<Deck> getLista() {
		return lista;
	}

	public void setLista(List<Deck> lista) {
		this.lista = lista;
	}
	
	public DeckNegocio getDeckNegocio() {
		return deckNegocio;
	}

	public void setDeckNegocio(DeckNegocio deckNegocio) {
		this.deckNegocio = deckNegocio;
	}

	public UsuarioNegocio getUsuarioNegocio() {
		return usuarioNegocio;
	}

	public void setUsuarioNegocio(UsuarioNegocio usuarioNegocio) {
		this.usuarioNegocio = usuarioNegocio;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

}

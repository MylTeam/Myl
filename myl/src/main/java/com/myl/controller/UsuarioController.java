package com.myl.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;

import ch.qos.logback.core.Context;

import com.myl.modelo.Deck;
import com.myl.modelo.Pais;
import com.myl.modelo.Usuario;
import com.myl.negocio.CartaNegocio;
import com.myl.negocio.DeckNegocio;
import com.myl.negocio.EdicionNegocio;
import com.myl.negocio.PaisNegocio;
import com.myl.negocio.UsuarioNegocio;
import com.myl.util.IssueMail;
import com.myl.util.NombreObjetosSesion;
import com.myl.util.Spoiler;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

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
	
	private CartaNegocio cartaNegocio;
	private List<Pais> listPaises;
	private PaisNegocio paisNegocio;
	
	@SkipValidation
	public HttpHeaders index() {
				
		usuario=(Usuario) ActionContext.getContext().getSession().get(NombreObjetosSesion.USUARIO);		
		idSel=usuario.getIdUsuario();

		Deck deckAux=new Deck();
		deckAux.setUsuarioId(usuario.getIdUsuario());
		lista=deckNegocio.findByExample(deckAux);
		
		if(usuario.getEmail()==null || usuario.getIdPais()==null){
			addActionError("Favor de actualizar tus datos");
		}
		
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	@SkipValidation
	public String edit() {
		String result="edit";
		listPaises=paisNegocio.findAll();
		
		usuario=(Usuario) ActionContext.getContext().getSession().get(NombreObjetosSesion.USUARIO);
		
		if(!usuario.getIdUsuario().equals(idSel)){
			System.out.println("usuario: "+usuario.getIdUsuario()+" idsel "+idSel);
			result="success";
		}
		
		return result;
	}
	
	public void validateUpdate(){
		Usuario aux=new Usuario();
		aux.setEmail(model.getEmail());
		List<Usuario> usuariosAux=usuarioNegocio.findByExample(aux);
		
		if (!usuariosAux.isEmpty()) {
			if(!usuariosAux.get(0).getIdUsuario().equals(model.getIdUsuario())){
			addActionError("El correo electrónico ingresado ya está registrado");
			}
		}
		
		if (hasFieldErrors() || hasActionErrors()) {
			listPaises=paisNegocio.findAll();
		}
	}
		
	@Validations(requiredStrings = {			
			@RequiredStringValidator(fieldName = "model.email", type = ValidatorType.FIELD, key = "Introduce tu correo electrónico")},			
			intRangeFields={
			@IntRangeFieldValidator(fieldName="model.idPais", type = ValidatorType.FIELD, message="Selecciona tu pais", min = "1")},
			emails={
			@EmailValidator(fieldName="model.email", type=ValidatorType.FIELD, message="Correo electrónico no válido")})
	public String update() {
		model = usuarioNegocio.save(model);
		ActionContext.getContext().getSession().put(NombreObjetosSesion.USUARIO, model);
		return "success";
	}

	@SkipValidation
	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public void validateDestroy() {
		throw new UnsupportedOperationException();
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

	public CartaNegocio getCartaNegocio() {
		return cartaNegocio;
	}

	public void setCartaNegocio(CartaNegocio cartaNegocio) {
		this.cartaNegocio = cartaNegocio;
	}

	public List<Pais> getListPaises() {
		return listPaises;
	}

	public void setListPaises(List<Pais> listPaises) {
		this.listPaises = listPaises;
	}

	public PaisNegocio getPaisNegocio() {
		return paisNegocio;
	}

	public void setPaisNegocio(PaisNegocio paisNegocio) {
		this.paisNegocio = paisNegocio;
	}



}


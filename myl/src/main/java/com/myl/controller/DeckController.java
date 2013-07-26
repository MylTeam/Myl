package com.myl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.google.gson.Gson;
import com.myl.messages.MessageInfoMessage;
import com.myl.modelo.Carta;
import com.myl.modelo.Deck;
import com.myl.modelo.Edicion;
import com.myl.modelo.Usuario;
import com.myl.negocio.CartaNegocio;
import com.myl.negocio.EdicionNegocio;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {"actionName", "deck" })
,@Result(name="res", type="json", params={"includeProperties","resultado.*"})
})
public class DeckController extends ActionSupport implements ModelDriven<Deck>{
	
	private Integer idSel;
	private Deck model;
	
	private List<Carta> resultado;
	private List<Edicion> ediciones;
	private EdicionNegocio edicionNegocio;
	private CartaNegocio cartaNegocio;
	private List<String> razas;
	
	private String criterioJson;
	
	private Gson jsonProcessor;
	
	@SkipValidation
	public HttpHeaders index() {
				
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	@SkipValidation
	public String editNew() {
		ediciones=edicionNegocio.findAll();		
		razas=cartaNegocio.findByCriteria();
		
		return "editNew";
	}
	
	public void validateCreate() {
		
	}
	
	public HttpHeaders create() {
		
		return new DefaultHttpHeaders("success").setLocationId(model.getDeckId());
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
	
	
	public String search(){
		jsonProcessor = new Gson();				
		Carta cartaAux=jsonProcessor.fromJson(criterioJson, Carta.class);		
		resultado= new ArrayList<Carta>();
		
		
		for(Carta carta:cartaNegocio.findByExample(cartaAux)){									
						
				Carta aux=new Carta();
				aux.setId(carta.getId());
				aux.setIdTemp(carta.getNumero()+carta.getEdicion().getSiglas());
				aux.setNombre(carta.getNombre());
				aux.setNumero(carta.getNumero());
				aux.setEfecto(carta.getEfecto());				
				aux.setTipo(carta.getTipo());
				aux.setFrecuencia(carta.getFrecuencia());
				aux.setCoste(carta.getCoste());
				aux.setFuerza(carta.getFuerza());
				aux.setSiglas(carta.getEdicion().getSiglas());				
				
				resultado.add(aux);
		}
		
			
		return "res";
	}
	
	public Integer getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
	}


	@Override
	public Deck getModel() {
		if (model == null) {
			model = new Deck();
		}
		return model;
	}

	public void setModel(Deck model) {
		this.model = model;
	}

	public List<Carta> getResultado() {
		return resultado;
	}

	public void setResultado(List<Carta> resultado) {
		this.resultado = resultado;
	}

	public EdicionNegocio getEdicionNegocio() {
		return edicionNegocio;
	}

	public void setEdicionNegocio(EdicionNegocio edicionNegocio) {
		this.edicionNegocio = edicionNegocio;
	}

	public CartaNegocio getCartaNegocio() {
		return cartaNegocio;
	}

	public void setCartaNegocio(CartaNegocio cartaNegocio) {
		this.cartaNegocio = cartaNegocio;
	}

	public List<Edicion> getEdiciones() {
		return ediciones;
	}

	public void setEdiciones(List<Edicion> ediciones) {
		this.ediciones = ediciones;
	}

	public List<String> getRazas() {
		return razas;
	}

	public void setRazas(List<String> razas) {
		this.razas = razas;
	}

	public String getCriterioJson() {
		return criterioJson;
	}

	public void setCriterioJson(String criterioJson) {
		this.criterioJson = criterioJson;
	}

	public Gson getJsonProcessor() {
		return jsonProcessor;
	}

	public void setJsonProcessor(Gson jsonProcessor) {
		this.jsonProcessor = jsonProcessor;
	}	

}

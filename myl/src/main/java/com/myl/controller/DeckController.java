package com.myl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Carta;
import com.myl.modelo.Deck;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {"actionName", "deck" })
,@Result(name="res", type="json", params={"includeProperties","resultado.*"})
})
public class DeckController extends ActionSupport implements ModelDriven<Deck>{

	private static final long serialVersionUID = 8585016072024421730L;
	private Integer idSel;
	private Deck model;
	
	private List<Carta> resultado;
	
	@SkipValidation
	public HttpHeaders index() {
		
		
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	public String search(){
		String aux2="res";
		
			
		return aux2;
	}
	
	public Integer getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
	}


	@Override
	public Deck getModel() {
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

}

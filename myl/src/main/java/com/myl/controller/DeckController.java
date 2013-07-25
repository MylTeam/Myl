package com.myl.controller;

import javax.inject.Named;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Deck;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {"actionName", "deck" })
})
public class DeckController extends ActionSupport implements ModelDriven<Deck>{

	private static final long serialVersionUID = 8585016072024421730L;
	private Integer idSel;
	private Deck model;
	
	
	@SkipValidation
	public HttpHeaders index() {
		
		
		return new DefaultHttpHeaders("index").disableCaching();
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

}

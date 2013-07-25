package com.myl.controller;

import javax.inject.Named;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ActionSupport;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {"actionName", "deck" })
})
public class DeckController extends ActionSupport {

	private static final long serialVersionUID = 8585016072024421730L;
	private Integer idSel;
	
	
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

}

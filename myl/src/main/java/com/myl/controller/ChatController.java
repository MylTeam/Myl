package com.myl.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Carta;
import com.myl.modelo.Tema;
import com.myl.negocio.CartaNegocio;
import com.myl.negocio.TemaNegocio;
import com.myl.util.Spoiler;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {
		"actionName", "chat" }) })
public class ChatController extends ActionSupport {

	private Integer idSel;
	private CartaNegocio cartaNegocio;

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

	public CartaNegocio getCartaNegocio() {
		return cartaNegocio;
	}

	public void setCartaNegocio(CartaNegocio cartaNegocio) {
		this.cartaNegocio = cartaNegocio;
	}

}

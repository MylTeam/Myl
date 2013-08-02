package com.myl.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.myl.modelo.Carta;
import com.myl.modelo.Deck;
import com.myl.modelo.Usuario;
import com.myl.negocio.DeckNegocio;
import com.myl.negocio.UsuarioNegocio;
import com.myl.util.NombreObjetosSesion;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {"actionName", "usuario" })
})
public class HelpController extends ActionSupport {

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

package com.myl.controller;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Usuario;
import com.myl.negocio.CartaNegocio;
import com.myl.util.NombreObjetosSesion;
import com.myl.util.Spoiler;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Named
@Results({ @Result(name = "success", type = "redirectAction", params = {"actionName", "lobby" })
,@Result(name = "nodeck", type = "redirectAction", params = {"actionName", "usuario" })
})
public class LobbyController extends ActionSupport {

	private static final long serialVersionUID = 8585016072024421730L;
	private Integer idSel;
	private Usuario usuario;
	private String username;
	
	private CartaNegocio cartaNegocio;
		
	@SkipValidation
	public HttpHeaders index() {
		usuario=(Usuario) ActionContext.getContext().getSession().get(NombreObjetosSesion.USUARIO);
		setUsername(usuario.getLogin());
		
		
//		Spoiler.loadData(cartaNegocio);
		if(usuario.getDeckPred()==0){					
			return new DefaultHttpHeaders("nodeck").disableCaching();
		}else{
			return new DefaultHttpHeaders("index").disableCaching();
		}
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public CartaNegocio getCartaNegocio() {
		return cartaNegocio;
	}


	public void setCartaNegocio(CartaNegocio cartaNegocio) {
		this.cartaNegocio = cartaNegocio;
	}


		

}

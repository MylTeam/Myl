package com.myl.controller;


import java.util.List;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.myl.modelo.Usuario;
import com.myl.negocio.UsuarioNegocio;
import com.myl.util.NombreObjetosSesion;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Named
@Results({
		@Result(name = "error", location = "login.jsp"),
		@Result(name = "acceso", type = "redirectAction", params = {
				"actionName", "usuario" })
		 }

)
public class LoginController extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = -7475211274962357078L;
	private String userId;
	private String password;
	private Usuario usuarioSel;
	private UsuarioNegocio service;	
	private HttpServletRequest request;

	@Override
	public String execute() throws Exception {

		usuarioSel = new Usuario();
		List<Usuario> usuarios = null;
		usuarioSel.setLogin(userId);
		usuarioSel.setPassword(password);		
				
		try {
			usuarios = service.findByExample(usuarioSel);
						
			if (usuarios.isEmpty()) {
				addActionError("Usuario y/o contraseña incorrectos");
			}

			else {
				clearActionErrors();
				ActionContext.getContext().getSession().put(NombreObjetosSesion.USUARIO, usuarios.get(0));
				return "next";
			}

		} catch (Exception e) {
			return "error";
		}
		return "error";
	}
	
	public LoginController(UsuarioNegocio loginService) {
		service = loginService;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String create() throws Exception {
		return execute();
	}

	public String index() throws Exception {
		usuarioSel = (Usuario) ActionContext.getContext().getSession().get(NombreObjetosSesion.USUARIO);
		return "index";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setLoginService(UsuarioNegocio s) {
		service = s;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}
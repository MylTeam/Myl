package com.myl.controller;

import java.util.List;

import javax.inject.Named;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.myl.modelo.Usuario;
import com.myl.negocio.UsuarioNegocio;
import com.myl.util.IssueMail;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Named
@Results({ @Result(name = "restored", type = "redirectAction", params = {
		"actionName", "login" }) })
public class RecuperarPassController extends ActionSupport implements
		ModelDriven<Usuario> {

	private static final long serialVersionUID = 1L;

	private Integer idSel;

	private Usuario model = null;
	private Usuario usuario;
	private UsuarioNegocio usuarioNegocio;
	private IssueMail mailSender;
	private String email;
	private List<Usuario> listUsuarios;
	private Long cd;
	private String confirmPass;

	@SkipValidation
	public String editNew() {

		return "editNew";
	}

	public void validateCreate() {
		Usuario aux = new Usuario();
		aux.setEmail(model.getEmail());
		listUsuarios = usuarioNegocio.findByExample(aux);

		if (listUsuarios.isEmpty()) {
			addActionError("No se ha encontrado un usuario con el email ingresado");
		}
	}

	@Validations(requiredStrings = { @RequiredStringValidator(fieldName = "email", type = ValidatorType.FIELD, key = "Introduce tu correo electrónico"), }, emails = { @EmailValidator(fieldName = "email", type = ValidatorType.FIELD, message = "Correo electrónico no válido") })
	public HttpHeaders create() {
		usuario = listUsuarios.get(0);
		enviarCorreoPass(usuario.getIdUsuario(), usuario.getCodigo(),
				usuario.getEmail());
		return new DefaultHttpHeaders("restored").setLocationId(model
				.getIdUsuario());
	}

	public String edit() {
		if (cd != null && cd.equals(model.getCodigo())) {
			return "edit";
		}
		return "restored";
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "model.password", type = ValidatorType.FIELD, key = "Introduce la contraseña", shortCircuit = true),
			@RequiredStringValidator(fieldName = "confirmPass", type = ValidatorType.FIELD, key = "Introduce la confirmacion de la contraseña", shortCircuit = true) }, stringLengthFields = { @StringLengthFieldValidator(fieldName = "model.password", type = ValidatorType.FIELD, key = "La contraseña debe ser menos a 80 carácteres", maxLength = "80", trim = true, shortCircuit = true) }, fieldExpressions = { @FieldExpressionValidator(fieldName = "confirmPass", expression = "model.password eq confirmPass", key = "Las contraseñas no son iguales") })
	public String update() {
		if (cd != null && cd.equals(model.getCodigo())) {
			usuarioNegocio.update(model);
			addActionMessage("El cambio de contraseña se ha realizado exitósamente.");
		} else {
			addActionError("Error al cambiar intentar cambiar contraseña");
		}
		return "restored";
	}

	public void enviarCorreoPass(Integer id, Long codigo, String email) {
		String msg = "Hola "
				+ "<p>De acuerdo a la solicitud para recuperar tu contraseña es necesario que ingreses a la siguiente liga:</p>"
				+ "<p><a href='http://50.62.23.86:8080/myl/recuperar-pass/"
				+ id + "/edit?cd=" + codigo + "'>Confirmar</a></p>"
				+ "<p>MyL Team</p>";
		if (mailSender.sendMailTo(email, "Myl - recuperar contraseña",
				msg)) {
			addActionMessage("Se ha enviado una liga a tu correo electrónico para restablecer tu contraseña");
		} else {
			addActionError("Por el momento no se te puede enviar el correo de verificación por favor inténtalo mas tarde desde tu perfil.");
		}
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

	public UsuarioNegocio getUsuarioNegocio() {
		return usuarioNegocio;
	}

	public void setUsuarioNegocio(UsuarioNegocio usuarioNegocio) {
		this.usuarioNegocio = usuarioNegocio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IssueMail getMailSender() {
		return mailSender;
	}

	public void setMailSender(IssueMail mailSender) {
		this.mailSender = mailSender;
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public Long getCd() {
		return cd;
	}

	public void setCd(Long cd) {
		this.cd = cd;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

}

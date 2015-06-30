package com.myl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import javax.inject.Named;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.myl.modelo.Carta;
import com.myl.modelo.Edicion;
import com.myl.negocio.CartaNegocio;
import com.myl.negocio.EdicionNegocio;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

@Results({ @Result(name = "input", location = "${pageErrorResult}", type = "dispatcher") })
@Named
public class EdicionController extends ActionSupport implements
		ModelDriven<Edicion> {

	private static final long serialVersionUID = 1L;

	private Integer idSel;

	private Edicion model = null;

	private File fileSpl = null;
	private String filenameSpl;
	private String contentTypeSpl;

	private File fileImg = null;
	private String filenameImg;
	private String contentTypeImg;

	private List<Carta> cartas;
	private List<Edicion> ediciones;
	private CartaNegocio cartaNegocio;
	private EdicionNegocio edicionNegocio;

	private Boolean errores;

	private String pageErrorResult;
	
	public String index() {

		return "index";
	}

	public String loadTable() {
		ediciones = edicionNegocio.findAll();
		return "edicionesTbl";
	}

	@SkipValidation
	public String editNew() {

		return "editNew";
	}

	public void validateCreate() {
		String msgError = edicionNegocio.existe(model);
		if (msgError != null) {
			addActionError(msgError);
		}

		errores = hasActionErrors() || hasFieldErrors();
		pageErrorResult = "edicion!editNew";
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(fieldName = "model.nombre", type = ValidatorType.FIELD, key = "Introduce el nombre de la edición."),
			@RequiredStringValidator(fieldName = "model.siglas", type = ValidatorType.FIELD, key = "Introduce las siglas de la edición.") }, regexFields = {
			@RegexFieldValidator(fieldName = "model.nombre", type = ValidatorType.FIELD, key = "Nombre no válido.", regexExpression = "[A-Z[a-z][0-9]\\s]+"),
			@RegexFieldValidator(fieldName = "model.siglas", type = ValidatorType.FIELD, key = "Siglas no válidas.", regexExpression = "[A-Z[a-z][0-9]]+") })
	public String create() {

		return "editNew";
	}

	public String addSpoiler() {

		return "spoiler";
	}

	public void validateSaveSpoiler() {
		cartas = cartaNegocio.getCardsFromExcel(fileSpl);
		cartaNegocio.unzipImages(fileImg,
				"C:\\Users\\Carlos\\Downloads\\Rebelion\\testUnzip");
	}

	public String saveSpoiler() {
		return SUCCESS;
	}

	public String edit() {

		return "edit";
	}

	public void validateUpdate() {

	}

	public String update() {

		return SUCCESS;
	}

	@Override
	public Edicion getModel() {
		if (model == null) {
			model = new Edicion();
		}
		return model;
	}

	public void setModel(Edicion model) {
		this.model = model;
	}

	public Integer getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		this.idSel = idSel;
	}

	public File getFileSpl() {
		return fileSpl;
	}

	public void setFileSpl(File fileSpl) {
		this.fileSpl = fileSpl;
	}

	public String getFilenameSpl() {
		return filenameSpl;
	}

	public void setFilenameSpl(String filenameSpl) {
		this.filenameSpl = filenameSpl;
	}

	public String getContentTypeSpl() {
		return contentTypeSpl;
	}

	public void setContentTypeSpl(String contentTypeSpl) {
		this.contentTypeSpl = contentTypeSpl;
	}

	public File getFileImg() {
		return fileImg;
	}

	public void setFileImg(File fileImg) {
		this.fileImg = fileImg;
	}

	public String getFilenameImg() {
		return filenameImg;
	}

	public void setFilenameImg(String filenameImg) {
		this.filenameImg = filenameImg;
	}

	public String getContentTypeImg() {
		return contentTypeImg;
	}

	public void setContentTypeImg(String contentTypeImg) {
		this.contentTypeImg = contentTypeImg;
	}

	public void setArchivoSpoiler(File file) {
		this.fileSpl = file;
	}

	public void setArchivoSpoilerContentType(String contentType) {
		this.contentTypeSpl = contentType;
	}

	public void setArchivoSpoilerFileName(String filename) {
		this.filenameSpl = filename;
	}

	public void setArchivoImagen(File file) {
		this.fileImg = file;
	}

	public void setArchivoImagenContentType(String contentType) {
		this.contentTypeImg = contentType;
	}

	public void setArchivoImagenFileName(String filename) {
		this.filenameImg = filename;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
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

	public EdicionNegocio getEdicionNegocio() {
		return edicionNegocio;
	}

	public void setEdicionNegocio(EdicionNegocio edicionNegocio) {
		this.edicionNegocio = edicionNegocio;
	}

	public Boolean getErrores() {
		return errores;
	}

	public void setErrores(Boolean errores) {
		this.errores = errores;
	}

	public String getPageErrorResult() {
		return pageErrorResult;
	}

	public void setPageErrorResult(String pageErrorResult) {
		this.pageErrorResult = pageErrorResult;
	}

}

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
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.myl.modelo.Carta;
import com.myl.modelo.Edicion;
import com.myl.negocio.CartaNegocio;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

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
	
	private CartaNegocio cartaNegocio;

	public String index(){
		
		
		return "index";
	}
	
	
	@SkipValidation
	public String editNew() {

		return "editNew";
	}

	public void validateCreate() {
		
		cartas=cartaNegocio.getCardsFromExcel(fileSpl);
		cartaNegocio.unzipImages(fileImg, "C:\\Users\\Carlos\\Downloads\\Rebelion\\testUnzip");
	}
	
	public String create() {
		
		
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

}

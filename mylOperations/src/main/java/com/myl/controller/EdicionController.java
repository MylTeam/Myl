package com.myl.controller;

import java.io.File;
import java.util.List;

import javax.inject.Named;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.myl.modelo.Edicion;
import com.myl.modelo.Usuario;
import com.myl.negocio.GenericBs;
import com.myl.negocio.UsuarioNegocio;
import com.myl.util.IssueMail;
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

	public String index(){
		
		
		return "index";
	}
	
	
	@SkipValidation
	public String editNew() {

		return "editNew";
	}

	public void validateCreate() {
System.out.println("here");
	}
	
	public String create() {
		System.out.println(model.getNombre()+" "+model.getSiglas());
		System.out.println("°°° "+fileSpl.getAbsolutePath());
		System.out.println("°°° "+fileImg.getAbsolutePath());
		
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

}

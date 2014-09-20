package com.myl.negocio;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.FormatoDao;
import com.myl.modelo.Formato;


@Singleton
@Named("formatoNegocio")
public class FormatoNegocio {
	private FormatoDao formatoDao;	
	
	@Transactional
	public List<Formato> findAll() {
		return formatoDao.findAll();
	}

	@Transactional
	public Formato findById(Integer id) {
		return formatoDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Formato save(Formato entidad) {
		Formato modelo = formatoDao.save(entidad);		
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Formato entidad) {
		formatoDao.delete(entidad);
	}

	@Transactional
	public List<Formato> findByExample(Formato formato) {
		return formatoDao.findByExample(formato);
	}
	
     
	public FormatoDao getFormatoDao() {
		return formatoDao;
	}

	public void setFormatoDao(FormatoDao formatoDao) {
		this.formatoDao = formatoDao;
	}

	
	
}

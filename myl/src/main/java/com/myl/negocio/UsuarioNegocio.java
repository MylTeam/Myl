package com.myl.negocio;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.UsuarioDao;
import com.myl.modelo.Usuario;


@Singleton
@Named("usuarioNegocio")
public class UsuarioNegocio {

	private UsuarioDao usuarioDao;
	
	@Transactional
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Transactional
	public Usuario findById(Integer id) { 
		return usuarioDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Usuario save(Usuario entidad) {
		Usuario modelo = usuarioDao.save(entidad);
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Usuario entidad) { 
		usuarioDao.delete(entidad);
	}

	@Transactional
	public List<Usuario> findByExample(Usuario usuario) {		
		return usuarioDao.findByExample(usuario);
	}
	
	@Transactional
	public List<Usuario> findByName(String string) {		
		return usuarioDao.findByName(string);
	}
	
	@Transactional
	public List<Usuario> findFirstX(Integer i) {		
		return usuarioDao.findFirstX(i);
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	
}

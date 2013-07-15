package com.myl.negocio;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.CartaDao;
import com.myl.modelo.Carta;
import com.myl.negocio.CartaNegocio;


@Singleton
@Named("cartaNegocio")
public class CartaNegocio {
	private CartaDao CartaDao;	
	
	@Transactional
	public List<Carta> findAll() {
		return CartaDao.findAll();
	}

	@Transactional
	public Carta findById(Integer id) {
		return CartaDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Carta save(Carta entidad) {
		Carta modelo = CartaDao.save(entidad);		
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Carta entidad) {
		CartaDao.delete(entidad);
	}

	@Transactional
	public List<Carta> findByExample(Carta Carta) {
		return CartaDao.findByExample(Carta);
	}
	   
	public CartaDao getCartaDao() {
		return CartaDao;
	}

	public void setCartaDao(CartaDao CartaDao) {
		this.CartaDao = CartaDao;
	}

	
	
}

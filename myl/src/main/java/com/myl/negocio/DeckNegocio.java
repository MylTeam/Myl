package com.myl.negocio;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.DeckDao;
import com.myl.modelo.Deck;



@Singleton
@Named("deckNegocio")
public class DeckNegocio {
	private DeckDao deckDao;	
	
	@Transactional
	public List<Deck> findAll() {
		return deckDao.findAll();
	}

	@Transactional
	public Deck findById(Integer id) {
		return deckDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Deck save(Deck entidad) {
		Deck modelo = deckDao.save(entidad);		
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Deck entidad) {
		deckDao.delete(entidad);
	}

	@Transactional
	public List<Deck> findByExample(Deck Deck) {
		return deckDao.findByExample(Deck);
	}
	   
	public DeckDao getDeckDao() {
		return deckDao;
	}

	public void setDeckDao(DeckDao deckDao) {
		this.deckDao = deckDao;
	}

	
	
}

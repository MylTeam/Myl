package com.myl.negocio;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.DeckCartaDao;
import com.myl.modelo.DeckCarta;



@Singleton
@Named("deckCartaNegocio")
public class DeckCartaNegocio {
	private DeckCartaDao deckCartaDao;	
	
	@Transactional
	public List<DeckCarta> findAll() {
		return deckCartaDao.findAll();
	}

	@Transactional
	public DeckCarta findById(Integer id) {
		return deckCartaDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public DeckCarta save(DeckCarta entidad) {
		DeckCarta modelo = deckCartaDao.save(entidad);		
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(DeckCarta entidad) {
		deckCartaDao.delete(entidad);
	}

	@Transactional
	public List<DeckCarta> findByExample(DeckCarta deckCarta) {
		return deckCartaDao.findByExample(deckCarta);
	}
	 
	@Transactional
	public void insertCard(Integer deckId,Integer CartaId,Integer CartaQt){
		deckCartaDao.insertCard(deckId, CartaId, CartaQt);
	}
	
	@Transactional
	public void deleteCardsFromDeck(Integer deckId){
		deckCartaDao.deleteCardsFromDeck(deckId);
	}
	
	public DeckCartaDao getDeckCartaDao() {
		return deckCartaDao;
	}

	public void setDeckCartaDao(DeckCartaDao deckCartaDao) {
		this.deckCartaDao = deckCartaDao;
	}

	
	
}

package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.myl.modelo.DeckCarta;

@Singleton
@Named("deckCartaDao")
public class DeckCartaDao extends HibernateDaoSupport {
	public List<DeckCarta> findAll() {		
		return getHibernateTemplate().loadAll(DeckCarta.class);
	}

	public DeckCarta findById(Integer id) {
		return getHibernateTemplate().get(DeckCarta.class, id);
	}

	public DeckCarta save(DeckCarta entity) {
		if (entity.getDeckCartaId() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(DeckCarta entity) {
		entity = getHibernateTemplate().merge(entity);
//		getHibernateTemplate().refresh(entity);
		getSession().delete(entity);
	}
	
	public void insertCard(Integer deckId,Integer CartaId,Integer CartaQt){
		Query query = getSession().createSQLQuery("insert into deckcarta values(:deckId,:cartaId,:cartaQt)")
				.addEntity(DeckCarta.class)
				.setParameter("deckId", deckId).setParameter("cartaId", CartaId).setParameter("cartaQt", CartaQt);
		
		query.executeUpdate();
	}
	
	public void deleteCardsFromDeck(Integer deckId){
		Query query = getSession().createSQLQuery("delete from deckcarta where DeckId=:deckId")		
				.addEntity(DeckCarta.class)
				.setParameter("deckId", deckId);
		
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<DeckCarta> findByExample(DeckCarta deckCarta) { 
		return getHibernateTemplate().findByExample(deckCarta);
	}
}

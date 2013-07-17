package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.myl.modelo.Deck;

@Singleton
@Named("deckDao")
public class DeckDao extends HibernateDaoSupport {
	public List<Deck> findAll() {		
		return getHibernateTemplate().loadAll(Deck.class);
	}

	public Deck findById(Integer id) {
		return getHibernateTemplate().get(Deck.class, id);
	}

	public Deck save(Deck entity) {
		if (entity.getDeckId() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(Deck entity) {
		getHibernateTemplate().refresh(entity);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Deck> findByExample(Deck deck) { 
		return getHibernateTemplate().findByExample(deck);
	}
}

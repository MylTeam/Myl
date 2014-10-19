package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myl.modelo.DeckCarta;
import com.myl.modelo.Duelo;

@Singleton
@Named("dueloDao")
public class DueloDao extends HibernateDaoSupport {
	public List<Duelo> findAll() {		
		return getHibernateTemplate().loadAll(Duelo.class);
	}

	public Duelo findById(Integer id) {
		return getHibernateTemplate().get(Duelo.class, id);
	}

	public Duelo save(Duelo entity) {
		if (entity.getDueloId() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(Duelo entity) {
		entity = getHibernateTemplate().merge(entity);
		getSession().delete(entity);
	}
	
	public void insertDuel(Integer winner,Integer loser,Integer cant){		
		
		Query query = getSession().createSQLQuery("insert into duelo_tbl values(:winner,:loser,:cant)")
				.addEntity(Duelo.class)
				.setParameter("winner", winner).setParameter("loser", loser).setParameter("cant", cant);
		
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Duelo> findByExample(Duelo duelo) { 
		return getHibernateTemplate().findByExample(duelo);
	}
}

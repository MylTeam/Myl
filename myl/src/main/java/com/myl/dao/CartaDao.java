package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.myl.modelo.Carta;

@Singleton
@Named("cartaDao")
public class CartaDao extends HibernateDaoSupport {
	public List<Carta> findAll() {		
		return getHibernateTemplate().loadAll(Carta.class);
	}

	public Carta findById(Integer id) {
		return getHibernateTemplate().get(Carta.class, id);
	}

	public Carta save(Carta entity) {
		if (entity.getId() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(Carta entity) {
		getHibernateTemplate().refresh(entity);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Carta> findByExample(Carta carta) { 
		return getHibernateTemplate().findByExample(carta);
	}
	
	public List<String> findByCriteria() { 		
		DetachedCriteria dCriteria = DetachedCriteria.forClass(Carta.class); ;		
		dCriteria.setProjection(Projections.distinct(Projections.property("raza")));
		return getHibernateTemplate().findByCriteria(dCriteria);
	}
}

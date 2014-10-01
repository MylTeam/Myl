package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myl.modelo.Carta;
import com.myl.modelo.Pais;

@Singleton
@Named("paisDao")
public class PaisDao extends HibernateDaoSupport {
	public List<Pais> findAll() {		
		return getHibernateTemplate().loadAll(Pais.class);
	}

	public Pais findById(Integer id) {
		return getHibernateTemplate().get(Pais.class, id);
	}

	public Pais save(Pais entity) {
		if (entity.getId() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);		
		return entity;
	}

	public void delete(Pais entity) {
		getHibernateTemplate().refresh(entity);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Pais> findByExample(Pais pais) { 
		return getHibernateTemplate().findByExample(pais);
	}
	
}

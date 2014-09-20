package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myl.modelo.Edicion;
import com.myl.modelo.Formato;


@Singleton
@Named("formatoDao")
public class FormatoDao extends HibernateDaoSupport {
	public List<Formato> findAll() {		
		return getHibernateTemplate().loadAll(Formato.class);
	}

	public Formato findById(Integer id) {
		return getHibernateTemplate().get(Formato.class, id);
	}

	public Formato save(Formato entity) {
		if (entity.getId() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(Formato entity) {
		getHibernateTemplate().refresh(entity);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> findByExample(Formato formato) { 
		return getHibernateTemplate().findByExample(formato);
	}
}

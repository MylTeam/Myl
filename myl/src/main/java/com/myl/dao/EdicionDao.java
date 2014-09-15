package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.myl.modelo.Edicion;


@Singleton
@Named("edicionDao")
public class EdicionDao extends HibernateDaoSupport {
	public List<Edicion> findAll() {		
		return getHibernateTemplate().loadAll(Edicion.class);
	}

	public Edicion findById(Integer id) {
		return getHibernateTemplate().get(Edicion.class, id);
	}

	public Edicion save(Edicion entity) {
		if (entity.getId() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(Edicion entity) {
		getHibernateTemplate().refresh(entity);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Edicion> findByExample(Edicion Edicion) { 
		return getHibernateTemplate().findByExample(Edicion);
	}
}

package com.myl.dao;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.myl.modelo.Usuario;

@Singleton
@Named("usuarioDao")
public class UsuarioDao extends HibernateDaoSupport {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Usuario> findAll() {
		return getHibernateTemplate().loadAll(Usuario.class);
	}

	public Usuario findById(Integer id) {
		return getHibernateTemplate().get(Usuario.class, id);
	}

	public Usuario save(Usuario entity) {
		if (entity.getIdUsuario() != null) {
			entity = getHibernateTemplate().merge(entity);
		}
		getSession().saveOrUpdate(entity);
		return entity;
	}

	public void delete(Usuario entity) {
		entity = getHibernateTemplate().merge(entity);
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findByExample(Usuario usuario) {
		return getHibernateTemplate().findByExample(usuario);
	}

	public List<Usuario> findByName(String s) {
		Query query = getSession()
				.createSQLQuery("select * from usuario where UsuarioNb=:s")
				.addEntity(Usuario.class).setParameter("s", s);
		return query.list();
	}
	
	public List<Usuario> findFirstX(Integer i) {
		Query query = getSession()
				.createSQLQuery("select * from usuario order by dl_won desc")
				.addEntity(Usuario.class).setMaxResults(i);
		return query.list();
	}
	
	

}

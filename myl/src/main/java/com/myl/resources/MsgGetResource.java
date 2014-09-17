package com.myl.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.UsuarioDao;
import com.myl.modelo.Usuario;

@Path("/msgGet")
@Component("MsgGetResource")
public class MsgGetResource {
	UsuarioDao usuarioDao;

	@GET
	@Transactional
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Usuario getUsuarios() throws SQLException {
		Usuario usuarios = usuarioDao.findById(1);
		return usuarios;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}

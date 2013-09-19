package com.myl.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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

		Usuario usuarios=usuarioDao.findById(1);
//		List<Usuario> usuarios = usuarioDao.findAll();		
//		for(Usuario u:usuarios){
//			if(u.getDecks().isEmpty()){
//				u.setTieneDeck(false);				
//			}else{
//				u.setTieneDeck(true);
//			}			
//		}
		
		return usuarios;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
}

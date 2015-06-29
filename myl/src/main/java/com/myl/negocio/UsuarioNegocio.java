package com.myl.negocio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.UsuarioDao;
import com.myl.exception.BusinessException;
import com.myl.modelo.Usuario;

@Service("usuarioNegocio")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class UsuarioNegocio {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UsuarioNegocio.class);

	private UsuarioDao usuarioDao;

	@Transactional
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Transactional
	public Usuario findById(Integer id) {
		return usuarioDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Usuario save(Usuario entidad) {
		Usuario modelo = usuarioDao.save(entidad);
		return modelo;
	}

	@Transactional
	public List<Usuario> findByExample(Usuario usuario) {
		return usuarioDao.findByExample(usuario);
	}

	@Transactional
	public List<Usuario> findByName(String string) {
		return usuarioDao.findByName(string);
	}

	@Transactional
	public List<Usuario> findFirstX(Integer i) {
		return usuarioDao.findFirstX(i);
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	/**
	 * realiza el cifrado del password
	 * 
	 * @author cdt-escom-ipn
	 * @param password
	 * @return
	 * @throws BusinessException
	 */
	public String digestPassword(String password) {
		String digestPassword = "";
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest mda = MessageDigest.getInstance("SHA-256", "BC");
			digestPassword = new String(Hex.encodeHex(mda.digest(password
					.getBytes()))).toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("No existe el algoritmo", e);
			// throw new BusinessException();
		} catch (NoSuchProviderException e) {
			LOGGER.error("No existe el provedor", e);
			// throw new BusinessException();
		}
		return digestPassword;
	}

}

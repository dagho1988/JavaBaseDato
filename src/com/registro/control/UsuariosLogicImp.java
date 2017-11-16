package com.registro.control;

import java.util.List;

import org.hibernate.HibernateException;

import com.dao.general.FabricaDao;
import com.dao.general.UsuariosDao;
import com.registro.Usuarios;

public class UsuariosLogicImp implements UsuariosLogic {
	public static UsuariosLogicImp instance;
	public static String tipo;
	
	private UsuariosLogicImp(String tipo) {
		this.tipo = tipo;
	}
	
	public static UsuariosLogicImp getCurrentInstance(String tipo) {
		if(instance == null) {
			instance = new UsuariosLogicImp(tipo);
		}
		return instance;
	}

	@Override
	public Usuarios get(Long id) throws Exception {
		validarTipoConexion();
		UsuariosDao dao = FabricaDao.create(tipo);
		return dao.get(id);
	}

	@Override
	public boolean save(Usuarios instance) throws Exception {
		validarTipoConexion();
		UsuariosDao dao = FabricaDao.create(tipo);
		return dao.save(instance);
	}

	@Override
	public boolean update(Usuarios instance) throws Exception {
		validarTipoConexion();
		UsuariosDao dao = FabricaDao.create(tipo);
		return dao.update(instance);
	}

	@Override
	public List<Usuarios> get() throws Exception {
		validarTipoConexion();
		UsuariosDao dao = FabricaDao.create(tipo);
		return dao.get();
	}

	public void validarTipoConexion() throws Exception {
		if(tipo == null || tipo.trim().equals("")) {
			throw new Exception("No se indicó el tipo de conexión a la base de datos");
		}
	}
}

package com.registro.control;

import java.util.List;
import com.registro.Usuarios;

/**
 * Esta Interface permite acceder a el dao de Usuarios.
 * 
 * @author dagho
 *
 */
public interface UsuariosLogic {

	/**
	 * Este metodo permite acceder al metodo para obtener el usuario con este userId.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Usuarios get(Long id) throws Exception;

	/**
	 * Este metodo permite acceder al metodo para guardar un nuevo usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public boolean save(Usuarios instance) throws Exception;

	/**
	 * Este metodo permite acceder al metodo para actualizar la informacion del usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public boolean update(Usuarios instance) throws Exception;
	
	/**
	 * Este metodo permite acceder al metodo para obtener la lista de usuarios.
	 * 
	 * @return Lista de Usuarios
	 * @throws Exception
	 */
	public List<Usuarios> get() throws Exception;
	
}

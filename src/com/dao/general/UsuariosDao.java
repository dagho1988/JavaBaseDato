package com.dao.general;

import java.util.List;
import com.registro.Usuarios;

/**
 * Esta Interface permite acceder a los metodos para modificar los registros de
 * la entidad USUARIOS.
 * 
 * @author dagho
 *
 */
public interface UsuariosDao {

	/**
	 * Este metodo permite obtener el registro del usuario con este userId.
	 * 
	 * @param id
	 * @return  
	 * @throws Exception
	 */
	public Usuarios get(Long id) throws Exception;

	/**
	 * Este metodo permite guardar un nuevo usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public boolean save(Usuarios instance) throws Exception;

	/**
	 * Este metodo permite actualizar la informacion del usuario.
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public boolean update(Usuarios instance) throws Exception;
	
	/**
	 * Este metodo permite obtener la lista de usuarios.
	 * 
	 * @return Lista de Usuarios
	 * @throws Exception
	 */
	public List<Usuarios> get() throws Exception;
	
}

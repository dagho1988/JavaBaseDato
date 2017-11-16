package com.pruebas;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.registro.Usuarios;
import com.registro.control.UsuariosLogic;
import com.registro.control.UsuariosLogicImp;

public class UsuariosUnit {

	String[] tipos = {"store_procedure", "hibernate", "sql", "jpa"};
	int index = 3;
	
	@Test
	public void testGetLong() throws Exception {
		UsuariosLogic logic = UsuariosLogicImp.getCurrentInstance(tipos[index]);
		assertNotNull(logic.get(new Long(1)));
	}

	@Test
	public void testSave() throws Exception {
		UsuariosLogic logic = UsuariosLogicImp.getCurrentInstance(tipos[index]);
		Usuarios user = new Usuarios();
		user.setNombreCompleto("Alexander Giraldo 222");
		user.setEmail("alex@gmail.com");
		user.setDireccion("Av. Simpre Viva 123");
		user.setTelefono("55555555");
		user.setEstado(new BigDecimal(1));
		user.setLogin("alex");
		user.setPassword("123");
		assertTrue(logic.save(user));
	}

	@Test
	public void testUpdate() throws Exception {
		UsuariosLogic logic = UsuariosLogicImp.getCurrentInstance(tipos[index]);
		Usuarios user =logic.get(new Long(1));
		user.setNombreCompleto("Diego Alexander Giraldo Hoyos");
		assertTrue(logic.update(user));		
	}

	@Test
	public void testGet() throws Exception {
		UsuariosLogic logic = UsuariosLogicImp.getCurrentInstance(tipos[index]);
		logic.get();
	}

}

package com.dao.general;

public class FabricaDao {

	public FabricaDao() {
		
	}

	public static UsuariosDao create(String tipo) {
		UsuariosDao dao = null;
		
		switch (tipo) {
		case "hibernate":
			dao = new com.dao.hibernate.UsuariosDaoImp();
			break;

		case "store_procedure":
			dao = new com.dao.storeprocedures.UsuariosDaoImp();
			break;

		case "sql":
			dao = new com.dao.sql.UsuariosDaoImp();
			break;

		case "jpa":
			dao = new com.dao.jpa.UsuariosDaoImp();
			break;
			
		default:
			break;
		}
		
		return dao;
	}
	
}

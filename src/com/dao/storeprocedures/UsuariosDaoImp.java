package com.dao.storeprocedures;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dao.general.UsuariosDao;
import com.registro.Usuarios;

import oracle.jdbc.OracleTypes;

public class UsuariosDaoImp implements UsuariosDao {

	public UsuariosDaoImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuarios get(Long id) throws SQLException {
		Usuarios instance = null;

		try {
			BD.conectar();
			String sql = "{CALL SP_CONSULTAR_USUARIO(?,?)}";
			CallableStatement cs = BD.con.prepareCall(sql);

			cs.setLong(1, id);
			cs.registerOutParameter(2, OracleTypes.CURSOR);

			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(2);

			while (rs.next()) {
				instance = new Usuarios();
				instance.setUserId(rs.getLong("USER_ID"));
				instance.setNombreCompleto(rs.getString("NOMBRE_COMPLETO"));
				instance.setDireccion(rs.getString("DIRECCION"));
				instance.setTelefono(rs.getString("TELEFONO"));
				instance.setLogin(rs.getString("LOGIN"));
				instance.setPassword(rs.getString("PASSWORD"));
				instance.setEmail(rs.getString("EMAIL"));
				instance.setEstado(rs.getBigDecimal("ESTADO"));
				instance.setFechaRegistro(rs.getTimestamp("FECHA_REGISTRO"));
			}
			
			cs.close();
			
		} catch (Exception e) {
			BD.close();
			throw e;
		}

		return instance;
	}

	@Override
	public boolean save(Usuarios instance) throws SQLException {
		boolean respuesta = false;

		try {
			BD.conectar();
			String sql = "{CALL SP_NUEVO_USUARIO(?, ?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = BD.con.prepareCall(sql);

			cs.setString(1, instance.getNombreCompleto());
			cs.setString(2, instance.getEmail());
			cs.setString(3, instance.getDireccion());
			cs.setString(4, instance.getTelefono());
			cs.setString(5, instance.getLogin());
			cs.setString(6, instance.getPassword());
			cs.setBigDecimal(7, instance.getEstado());
			cs.registerOutParameter(8, OracleTypes.NUMBER);

			cs.execute();
			Long rpt = cs.getLong(8);
			
			if(rpt.intValue() == 1) {
				respuesta = true;
			}
			
			cs.close();
			
		} catch (Exception e) {
			BD.close();
			throw e;
		}
		

		return respuesta;
	}

	@Override
	public boolean update(Usuarios instance) throws SQLException {
		boolean respuesta = false;

		try {
			BD.conectar();
			String sql = "{CALL SP_ACTUALIZAR_USUARIO(?, ?, ?, ?, ?, ?, ?)}";
			CallableStatement cs = BD.con.prepareCall(sql);

			cs.setLong(1, instance.getUserId());
			cs.setString(2, instance.getNombreCompleto());
			cs.setString(3, instance.getEmail());
			cs.setString(4, instance.getDireccion());
			cs.setString(5, instance.getTelefono());
			cs.setBigDecimal(6, instance.getEstado());
			cs.registerOutParameter(7, OracleTypes.NUMBER);

			cs.execute();
			Long rpt = cs.getLong(7);

			if(rpt.intValue() == 1) {
				respuesta = true;
			}
			
			cs.close();
			
		} catch (Exception e) {
			BD.close();
			throw e;
		}
		
		return respuesta;
	}

	@Override
	public List<Usuarios> get() throws SQLException {
		List<Usuarios> listaUsuarios = null;

		try {
			BD.conectar();
			String sql = "{CALL SP_CONSULTAR_USUARIOS(?)}";
			CallableStatement cs = BD.con.prepareCall(sql);

			cs.registerOutParameter(1, OracleTypes.CURSOR);

			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);

			listaUsuarios = new ArrayList<Usuarios>();
			
			while (rs.next()) {
				Usuarios instance = new Usuarios();
				instance.setUserId(rs.getLong("USER_ID"));
				instance.setNombreCompleto(rs.getString("NOMBRE_COMPLETO"));
				instance.setDireccion(rs.getString("DIRECCION"));
				instance.setTelefono(rs.getString("TELEFONO"));
				instance.setLogin(rs.getString("LOGIN"));
				instance.setPassword(rs.getString("PASSWORD"));
				instance.setEmail(rs.getString("EMAIL"));
				instance.setEstado(rs.getBigDecimal("ESTADO"));
				instance.setFechaRegistro(rs.getTimestamp("FECHA_REGISTRO"));
				listaUsuarios.add(instance);
			}
			
			cs.close();
			
		} catch (Exception e) {
			BD.close();
			throw e;
		}

		return listaUsuarios;
	}

}

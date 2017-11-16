package com.dao.sql;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dao.general.UsuariosDao;
import com.registro.Usuarios;

import oracle.jdbc.OraclePreparedStatement;
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

			String sql = "SELECT 	* " + 
						 "FROM		USUARIOS " + 
						 "WHERE 	USER_ID = ?";

			PreparedStatement ps = BD.con.prepareStatement(sql);

			ps.setLong(1, id);

			ResultSet rs = (ResultSet) ps.executeQuery();

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

			ps.close();

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
			String cols[] = {"USER_ID"};
			String sql = "INSERT INTO USUARIOS VALUES (SQ_USUARIOS.nextval,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = BD.con.prepareStatement(sql, cols);

			ps.setString(1, instance.getNombreCompleto());
			ps.setString(2, instance.getDireccion());
			ps.setString(3, instance.getTelefono());
			ps.setString(4, instance.getEmail());
			ps.setString(5, instance.getLogin());
			ps.setString(6, instance.getPassword());
			ps.setDate(7, new Date(instance.getFechaRegistro().getTime()));
			ps.setBigDecimal(8, instance.getEstado());

			Long retorno = (long) ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();

			while (rs.next()) {
				retorno = rs.getLong(1);
			}
			
			if (retorno > 0) {
				respuesta = true;
			}

			ps.close();

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
			String sql = "UPDATE	USUARIOS " +
						 "SET 		NOMBRE_COMPLETO = ?, " +
						 "			EMAIL = ?, " +
						 "			DIRECCION = ?, " +
						 "			TELEFONO = ?, " +
						 "			ESTADO = ? " +						  
						 "WHERE		USER_ID = ?";
			
			PreparedStatement ps = BD.con.prepareStatement(sql);

			ps.setString(1, instance.getNombreCompleto());
			ps.setString(2, instance.getEmail());
			ps.setString(3, instance.getDireccion());
			ps.setString(4, instance.getTelefono());
			ps.setBigDecimal(5, instance.getEstado());
			ps.setLong(6, instance.getUserId());

			int retorno = ps.executeUpdate();
			
			if (retorno >= 0) {
				respuesta = true;
			}

			ps.close();

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

			String sql = "SELECT 	* " + 
						 "FROM		USUARIOS";

			PreparedStatement ps = BD.con.prepareStatement(sql);

			ResultSet rs = (ResultSet) ps.executeQuery();

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

			ps.close();

		} catch (Exception e) {
			BD.close();
			throw e;
		}

		return listaUsuarios;
	}

}

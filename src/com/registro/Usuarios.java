package com.registro;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */  
@Entity   
@Table(name="USUARIOS")
@NamedQuery(name="Usuarios.findAll", query="SELECT u FROM Usuarios u")
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy= GenerationType.SEQUENCE)  //sin secuencia cambiar a GenerationType.AUTO y eliminar la siguiente linea.
	@SequenceGenerator(name="SQ_USUARIOS", sequenceName="SQ_USUARIOS")
	private long userId;

	private String direccion;

	private String email;

	private BigDecimal estado;

	@Column(name="FECHA_REGISTRO")
	private Timestamp fechaRegistro;

	private String login;

	@Column(name="NOMBRE_COMPLETO")
	private String nombreCompleto;

	private String password;

	private String telefono;

	public Usuarios() {
		this.fechaRegistro = new Timestamp((new java.util.Date()).getTime());
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getEstado() {
		return this.estado;
	}

	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
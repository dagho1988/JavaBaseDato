package com.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import com.dao.general.UsuariosDao;
import com.registro.Usuarios;

public class UsuariosDaoImp implements UsuariosDao {
	private EntityManagerFactory emfactory;
	private EntityManager entitymanager;
	
	public UsuariosDaoImp() {
		// TODO Auto-generated constructor stub
	}

	private void iniciaOperacion() throws Exception {
	      emfactory = Persistence.createEntityManagerFactory( "RegistroBD" );
	      entitymanager = emfactory.createEntityManager( );
	}
	
	private void manejaExcepcion(Exception he) throws Exception {
		entitymanager.getTransaction( ).rollback();
	    throw new Exception("Ocurrió un error en la capa de acceso a datos", he);
	}

	public Usuarios get(Long id) throws Exception {
		Usuarios instance = null;
		try {
			iniciaOperacion();
			instance = entitymanager.find(Usuarios.class, id);
			entitymanager.close();
		} catch (Exception e) {
			manejaExcepcion(e);
		} finally {
			emfactory.close();
		}
        return instance; 
	}

	public boolean save(Usuarios instance) throws Exception {
		boolean respuesta = false;
		try {
			iniciaOperacion();
			entitymanager.getTransaction().begin();

			entitymanager.persist(instance);
			entitymanager.getTransaction().commit();

			entitymanager.close();

			if(instance.getUserId() > 0) {
				respuesta = true;
			}
			
		} catch (Exception e) {
			manejaExcepcion(e);
		} finally {
			emfactory.close();
		}
		return respuesta;
	}

	public boolean update(Usuarios instance) throws Exception {
		boolean respuesta = false;
		Usuarios usuario = null;
		try {
			iniciaOperacion();
			entitymanager.getTransaction().begin();

			usuario = entitymanager.find(Usuarios.class, instance.getUserId());

			usuario.setNombreCompleto(instance.getNombreCompleto());
			usuario.setDireccion(instance.getDireccion());
			usuario.setTelefono(instance.getTelefono());
			usuario.setEmail(instance.getEmail());
			usuario.setEstado(instance.getEstado());
			
			entitymanager.getTransaction().commit();

			entitymanager.close();

			if(instance.getUserId() > 0) {
				respuesta = true;
			}
			
		} catch (Exception e) {
			manejaExcepcion(e);
		} finally {
			emfactory.close();
		}  
		return respuesta;
	}

	public List<Usuarios> get() throws Exception {
		List<Usuarios> listaTsgUsuarios = null;
 
        iniciaOperacion(); 
        Query query = entitymanager.createQuery("Select u from Usuarios u");
        List<Usuarios> list = (List<Usuarios>) query.getResultList( );
		
        return listaTsgUsuarios; 
	}

}

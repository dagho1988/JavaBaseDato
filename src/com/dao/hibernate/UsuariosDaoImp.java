package com.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.general.UsuariosDao;
import com.registro.Usuarios;

public class UsuariosDaoImp implements UsuariosDao {
	private Session sesion;
	private Transaction tx;
	
	public UsuariosDaoImp() {
		// TODO Auto-generated constructor stub
	}

	private void iniciaOperacion() throws HibernateException {
	    sesion = HibernateUtil.getSessionFactory().openSession();
	    System.out.println(sesion.toString());
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException {
	    tx.rollback();
	    throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}

	@Override
	public Usuarios get(Long id) throws HibernateException {
		Usuarios instance = null;  
        iniciaOperacion(); 
        instance = (Usuarios) sesion.get(Usuarios.class, id); 
        return instance; 
	}

	@Override
	public boolean save(Usuarios instance) throws HibernateException {
		boolean respuesta = false;
		try {
			iniciaOperacion();
			tx = sesion.beginTransaction();
			sesion.save(instance); 
            tx.commit();	
			respuesta = true;
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			sesion.close();
		}
		return respuesta;
	}

	@Override
	public boolean update(Usuarios instance) throws HibernateException {
		boolean respuesta = false;
		Usuarios usuario = null;

        try { 
            iniciaOperacion();
            usuario = get(instance.getUserId());
            
            usuario.setNombreCompleto(instance.getNombreCompleto());
            usuario.setEmail(instance.getEmail());
            usuario.setDireccion(instance.getDireccion());
            usuario.setTelefono(instance.getTelefono());
            usuario.setEstado(instance.getEstado());
            
            tx = sesion.beginTransaction();
            sesion.update(usuario); 
            tx.commit();	
			respuesta = true;
        } catch(HibernateException he) { 
            manejaExcepcion(he);
        } finally { 
            sesion.close(); 
        }  
		return respuesta;
	}

	@Override
	public List<Usuarios> get() throws HibernateException {
		List<Usuarios> listaTsgUsuarios = null;  
        iniciaOperacion(); 
        listaTsgUsuarios = sesion.createQuery("from Usuarios").list(); 
        return listaTsgUsuarios; 
	}

}

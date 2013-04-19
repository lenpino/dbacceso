package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Directorio;
import cl.altair.acceso.modelo.UsuarioInmueble;

/**
 	* A data access object (DAO) providing persistence and search support for Directorio entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Directorio
  * @author MyEclipse Persistence Tools 
 */

public class DirectorioDAO  {
	//property constants
	public static final String MIEMBROS = "miembros";
	public static final String NOMBRE = "nombre";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Directorio entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   DirectorioDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Directorio entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Directorio entity) {
    				EntityManagerHelper.log("saving Directorio instance", Level.INFO, null);
	        try {
            getEntityManager().persist(entity);
            			EntityManagerHelper.log("save successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("save failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Delete a persistent Directorio entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   DirectorioDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Directorio entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Directorio entity) {
    				EntityManagerHelper.log("deleting Directorio instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Directorio.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Directorio entity and return it or a copy of it to the sender. 
	 A copy of the Directorio entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = DirectorioDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Directorio entity to update
	 @return Directorio the persisted Directorio entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Directorio update(Directorio entity) {
    				EntityManagerHelper.log("updating Directorio instance", Level.INFO, null);
	        try {
            Directorio result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Directorio findById( Integer id) {
    				EntityManagerHelper.log("finding Directorio instance with id: " + id, Level.INFO, null);
	        try {
            Directorio instance = getEntityManager().find(Directorio.class, id);
            return instance;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("find failed", Level.SEVERE, re);
	            throw re;
        } finally{
        	EntityManagerHelper.closeEntityManager();
        }
    }    
    

/**
	 * Find all Directorio entities with a specific property value.  
	 
	  @param propertyName the name of the Directorio property to query
	  @param value the property value to match
	  	  @return List<Directorio> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Directorio> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Directorio instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Directorio model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.closeEntityManager();
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		} finally{
			EntityManagerHelper.closeEntityManager();
		}
	}			
	public List<Directorio> findByMiembros(Object miembros) {
		return findByProperty(MIEMBROS, miembros);
	}
	
	public List<Directorio> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	
	/**
	 * Find all Directorio entities.
	  	  @return List<Directorio> all Directorio entities
	 */
	@SuppressWarnings("unchecked")
	public List<Directorio> findAll() {
					EntityManagerHelper.log("finding all Directorio instances", Level.INFO, null);
			try {
			final String queryString = "select model from Directorio model";
							Query query = getEntityManager().createQuery(queryString);
					return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.closeEntityManager();
						EntityManagerHelper.log("find all failed", Level.SEVERE, re);
				throw re;
		} finally{
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	/**
	 * Agrega un usuario inmueble a al directorio indicado.
	 */
	public void addUsuarioInmueble(Directorio elDirectorio, UsuarioInmueble usuario) {
		EntityManagerHelper.log("Agregando un usuario inmueble al directorio", Level.INFO, null);
		try {
			elDirectorio.addMiembro(usuario);
			this.update(elDirectorio);
		} catch (RuntimeException re) {
			EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("addUsuarioInmueble fallo", Level.SEVERE, re);
			throw re;
		} finally{
			EntityManagerHelper.closeEntityManager();
		}
	}

}
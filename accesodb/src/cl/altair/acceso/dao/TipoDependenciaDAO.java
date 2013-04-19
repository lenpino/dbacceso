package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.TipoDependencia;

/**
 	* A data access object (DAO) providing persistence and search support for TipoDependencia entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.TipoDependencia
  * @author MyEclipse Persistence Tools 
 */

public class TipoDependenciaDAO  {
	//property constants
	public static final String TIPODEPENDENCIA = "tipoDependencia";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved TipoDependencia entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   TipoDependenciaDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity TipoDependencia entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(TipoDependencia entity) {
    				EntityManagerHelper.log("saving TipoDependencia instance", Level.INFO, null);
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
	 Delete a persistent TipoDependencia entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   TipoDependenciaDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity TipoDependencia entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(TipoDependencia entity) {
    				EntityManagerHelper.log("deleting TipoDependencia instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(TipoDependencia.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved TipoDependencia entity and return it or a copy of it to the sender. 
	 A copy of the TipoDependencia entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = TipoDependenciaDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity TipoDependencia entity to update
	 @return TipoDependencia the persisted TipoDependencia entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public TipoDependencia update(TipoDependencia entity) {
    				EntityManagerHelper.log("updating TipoDependencia instance", Level.INFO, null);
	        try {
            TipoDependencia result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public TipoDependencia findById( Integer id) {
    				EntityManagerHelper.log("finding TipoDependencia instance with id: " + id, Level.INFO, null);
	        try {
            TipoDependencia instance = getEntityManager().find(TipoDependencia.class, id);
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
	 * Find all TipoDependencia entities with a specific property value.  
	 
	  @param propertyName the name of the TipoDependencia property to query
	  @param value the property value to match
	  	  @return List<TipoDependencia> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<TipoDependencia> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding TipoDependencia instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from TipoDependencia model where model." 
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
	public List<TipoDependencia> findByNombre(Object nombre
	) {
		return findByProperty(TIPODEPENDENCIA, nombre);
	}
	
	
	/**
	 * Find all TipoDependencia entities.
	  	  @return List<TipoDependencia> all TipoDependencia entities
	 */
	@SuppressWarnings("unchecked")
	public List<TipoDependencia> findAll() {
					EntityManagerHelper.log("finding all TipoDependencia instances", Level.INFO, null);
			try {
			final String queryString = "select model from TipoDependencia model";
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
	
	
	public static void creaTipoDependencias(){
		Integer[] ids = {
				1, 
				2, 
				3, 
				4, 
				5, 
				6			
		};
		String[] dependencias = {"Oficina",
							"Estacionamiento",
							"Local",
							"Salon de Eventos",
							"Casa",
							"Piso"				
		};
		try {
			final String queryString = "INSERT INTO \"APP\".tipodependencia VALUES (?, ?)";
			EntityManagerHelper.beginTransaction();
			for(int i=0;i<ids.length;i++){
				Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
				query.setParameter(1,ids[i]);
				query.setParameter(2, dependencias[i]);
				query.executeUpdate();
			}
	    	EntityManagerHelper.commit();
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

}
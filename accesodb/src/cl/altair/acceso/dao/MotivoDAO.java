package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Motivo;

/**
 	* A data access object (DAO) providing persistence and search support for Motivo entities.
 	 		* Transaction control of the save(), update() and delete() operations must be handled externally by senders of these methods 
 		  or must be manually added to each of these methods for data to be persisted to the JPA datastore.	
 	 * @see com.acepta.perfilamiento.eclipselink.Motivo
  * @author MyEclipse Persistence Tools 
 */

public class MotivoDAO  {
	//property constants
	public static final String MOTIVO = "motivo";





	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}	
	
		/**
	 Perform an initial save of a previously unsaved Motivo entity. 
	 All subsequent persist actions of this entity should use the #update() method.
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist} operation.
	 	 
	 * <pre> 
	 *   EntityManagerHelper.beginTransaction();
	 *   MotivoDAO.save(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Motivo entity to persist
	  @throws RuntimeException when the operation fails
	 */
    public void save(Motivo entity) {
    				EntityManagerHelper.log("saving Motivo instance", Level.INFO, null);
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
	 Delete a persistent Motivo entity.
	  This operation must be performed 
	 within the a database transaction context for the entity's data to be
	 permanently deleted from the persistence store, i.e., database. 
	 This method uses the {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete} operation.
	 	  
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   MotivoDAO.delete(entity);
	 *   EntityManagerHelper.commit();
	 *   entity = null;
	 * </pre>
	   @param entity Motivo entity to delete
	 @throws RuntimeException when the operation fails
	 */
    public void delete(Motivo entity) {
    				EntityManagerHelper.log("deleting Motivo instance", Level.INFO, null);
	        try {
        	entity = getEntityManager().getReference(Motivo.class, entity.getId());
            getEntityManager().remove(entity);
            			EntityManagerHelper.log("delete successful", Level.INFO, null);
	        } catch (RuntimeException re) {
	        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("delete failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    /**
	 Persist a previously saved Motivo entity and return it or a copy of it to the sender. 
	 A copy of the Motivo entity parameter is returned when the JPA persistence mechanism has not previously been tracking the updated entity. 
	 This operation must be performed within the a database transaction context for the entity's data to be permanently saved to the persistence
	 store, i.e., database. This method uses the {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge} operation.
	 	 
	 * <pre>
	 *   EntityManagerHelper.beginTransaction();
	 *   entity = MotivoDAO.update(entity);
	 *   EntityManagerHelper.commit();
	 * </pre>
	   @param entity Motivo entity to update
	 @return Motivo the persisted Motivo entity instance, may not be the same
	 @throws RuntimeException if the operation fails
	 */
    public Motivo update(Motivo entity) {
    				EntityManagerHelper.log("updating Motivo instance", Level.INFO, null);
	        try {
            Motivo result = getEntityManager().merge(entity);
            			EntityManagerHelper.log("update successful", Level.INFO, null);
	            return result;
        } catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
        				EntityManagerHelper.log("update failed", Level.SEVERE, re);
	            throw re;
        }
    }
    
    public Motivo findById( Integer id) {
    				EntityManagerHelper.log("finding Motivo instance with id: " + id, Level.INFO, null);
	        try {
            Motivo instance = getEntityManager().find(Motivo.class, id);
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
	 * Find all Motivo entities with a specific property value.  
	 
	  @param propertyName the name of the Motivo property to query
	  @param value the property value to match
	  	  @return List<Motivo> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Motivo> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Motivo instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Motivo model where model." 
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
	public List<Motivo> findByMotivo(Object nombre) {
		return findByProperty(MOTIVO, nombre);
	}
	
	
	/**
	 * Find all Motivo entities.
	  	  @return List<Motivo> all Motivo entities
	 */
	@SuppressWarnings("unchecked")
	public List<Motivo> findAll() {
					EntityManagerHelper.log("finding all Motivo instances", Level.INFO, null);
			try {
			final String queryString = "select model from Motivo model";
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
	
	public static void creaMotivos(){
		Integer[] ids = {
				1, 
				2, 
				3				
		};
		String[] motivos = {"Reuni—n",
							"Visita",
							"Personal"			
		};
		try {
			final String queryString = "INSERT INTO \"APP\".motivo VALUES (?, ?)";
			EntityManagerHelper.beginTransaction();
			for(int i=0;i<ids.length;i++){
				Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
				query.setParameter(1,ids[i]);
				query.setParameter(2, motivos[i]);
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
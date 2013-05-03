package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Provincia;

/**
 * A data access object (DAO) providing persistence and search support for Provincia
 * entities. Transaction contProvincia of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Provincia
 * @author MyEclipse Persistence Tools
 */

public class ProvinciaDAO {
	// property constants
	public static final String PROVINCIAID = "provinciaid";
	public static final String PROVINCIANOMBRE = "provincianombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Provincia entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProvinciaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Provincia entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Provincia entity) {
		EntityManagerHelper.log("saving Provincia instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Provincia entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ProvinciaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Provincia entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Provincia entity) {
		EntityManagerHelper.log("deleting Provincia instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Provincia.class, entity.getProvinciaId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Provincia entity and return it or a copy of it to
	 * the sender. A copy of the Provincia entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ProvinciaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Provincia entity to update
	 * @return Provincia the persisted Provincia entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Provincia update(Provincia entity) {
		EntityManagerHelper.log("updating Provincia instance", Level.INFO, null);
		try {
			Provincia result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Provincia findById(Integer id) {
		EntityManagerHelper.log("finding Provincia instance with id: " + id,
				Level.INFO, null);
		try {
			Provincia instance = getEntityManager().find(Provincia.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Provincia entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Provincia property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Provincia> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Provincia> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Provincia instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Provincia model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<Provincia> findByNombreProvincia(Object provincianombre, int... rowStartIdxAndCount) {
		return findByProperty(PROVINCIANOMBRE, provincianombre, rowStartIdxAndCount);
	}

	/**
	 * Find all Provincia entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Provincia> all Provincia entities
	 */
	@SuppressWarnings("unchecked")
	public List<Provincia> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Provincia instances", Level.INFO, null);
		try {
			final String queryString = "select model from Provincia model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}
	
	public static void creaProvicias(){
		Integer[] ids = {
				11,  
				14,  
				21,  
				22,  
				23,
				31,  
				32,  
				33,  
				41,  
				42,  
				43,  
				51,  
				52,  
				53,  
				54,  
				55,  
				56,  
				57,  
				58,  
				61,  
				62,  
				63,  
				71,  
				72,  
				73,  
				74,  
				81,  
				82,  
				83,  
				84,  
				91,  
				92,  
				101,  
				102,  
				103,  
				104,  
				111,  
				112,  
				113,  
				114,  
				121,  
				122,  
				123,  
				124,  
				131,  
				132,  
				133,  
				134,  
				135,  
				136,  
				141,  
				142,
				151,  
				152
		};
		Integer[] idsR = {
				  1,
				  1,
				  2,
				  2,
				  2,
				  3,
				  3,
				  3,
				  4,
				  4,
				  4,
				  5,
				  5,
				  5,
				  5,
				  5,
				  5,
				  5,
				  5,
				  6,
				  6,
				  6,
				  7,
				  7,
				  7,
				  7,
				  8,
				  8,
				  8,
				  8,
				  9,
				  9,
				  10,
				  10,
				  10,
				  10,
				  11,
				  11,
				  11,
				  11,
				  12,
				  12,
				  12,
				  12,
				  13,
				  13,
				  13,
				  13,
				  13,
				  13,
				  14,
				  14,
				  15,
				  15
		}; 
		String[] nombres = {
				 "Iquique", 
				 "Tamarugal", 
				 "Antofagasta", 
				 "El Loa", 
				 "Tocopilla",
				 "Copiapó", 
				 "Chañaral", 
				 "Huasco", 
				 "Elqui", 
				 "Choapa", 
				 "Limarí", 
				 "Valparaíso", 
				 "Isla de Pascua", 
				 "Los Andes", 
				 "Petorca", 
				 "Quillota", 
				 "San Antonio", 
				 "San Felipe de Aconcagua", 
				 "Marga Marga", 
				 "Cachapoal", 
				 "Cardenal Caro", 
				 "Colchagua", 
				 "Talca", 
				 "Cauquenes", 
				 "Curicó", 
				 "Linares", 
				 "Concepción", 
				 "Arauco", 
				 "Biobío", 
				 "Ñuble", 
				 "Cautín", 
				 "Malleco", 
				 "Llanquihue", 
				 "Chiloé", 
				 "Osorno", 
				 "Palena", 
				 "Coihaique", 
				 "Aisén", 
				 "Capitán Prat", 
				 "General Carrera", 
				 "Magallanes", 
				 "Antártica Chilena", 
				 "Tierra del Fuego", 
				 "última Esperanza", 
				 "Santiago", 
				 "Cordillera", 
				 "Chacabuco", 
				 "Maipo", 
				 "Melipilla", 
				 "Talagante", 
				 "Valdivia", 
				 "Ranco",
				 "Arica", 
				 "Parinacota"
		};
		try {
			final String queryString = "INSERT INTO \"APP\".provincia VALUES (?, ?, ?)";
			EntityManagerHelper.beginTransaction();
			for(int i=0;i<ids.length;i++){
				Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
				query.setParameter(1,ids[i]);
				query.setParameter(2, nombres[i]);
				query.setParameter(3, idsR[i]);
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
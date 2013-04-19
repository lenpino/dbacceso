package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Region;

/**
 * A data access object (DAO) providing persistence and search support for Region
 * entities. Transaction contRegion of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Region
 * @author MyEclipse Persistence Tools
 */

public class RegionDAO {
	// property constants
	public static final String REGIONID = "regionid";
	public static final String REGIONNOMBRE = "regionnombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Region entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RegionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Region entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Region entity) {
		EntityManagerHelper.log("saving Region instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Region entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RegionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Region entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Region entity) {
		EntityManagerHelper.log("deleting Region instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Region.class, entity.getRegionId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Region entity and return it or a copy of it to
	 * the sender. A copy of the Region entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RegionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Region entity to update
	 * @return Region the persisted Region entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Region update(Region entity) {
		EntityManagerHelper.log("updating Region instance", Level.INFO, null);
		try {
			Region result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Region findById(Integer id) {
		EntityManagerHelper.log("finding Region instance with id: " + id,
				Level.INFO, null);
		try {
			Region instance = getEntityManager().find(Region.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Region entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Region property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Region> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Region> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Region instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Region model where model."
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

	public List<Region> findByNombreregion(Object regionnombre, int... rowStartIdxAndCount) {
		return findByProperty(REGIONNOMBRE, regionnombre, rowStartIdxAndCount);
	}

	/**
	 * Find all Region entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Region> all Region entities
	 */
	@SuppressWarnings("unchecked")
	public List<Region> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Region instances", Level.INFO, null);
		try {
			final String queryString = "select model from Region model";
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

	public static void creaRegiones(){
		Integer[] ids = {
				1, 
				2, 
				3, 
				4, 
				5, 
				6,
				7, 
				8, 
				9, 
				10, 
				11,
				12, 
				13, 
				14, 
				15 				
		};
		String[] regiones = {"Región de Tarapacá",
							"Región de Antofagasta",
							"Región de Atacama",
							"Región de Coquimbo",
							"Región de Valparaíso",
							"Región del Libertador Gral. Bernardo O’Higgins", 
							"Región del Maule",
							"Región del Biobío",
							"Región de la Araucanía",
							"Región de Los Lagos",
							"Región Aisén del Gral. Carlos Ibáñez del Campo",
							"Región de Magallanes y de la Antártica Chilena",
							"Región Metropolitana de Santiago",
							"Región de Los Ríos",
							"Región de Arica y Parinacota"				
		};
		try {
			final String queryString = "INSERT INTO \"APP\".region VALUES (?, ?)";
			EntityManagerHelper.beginTransaction();
			for(int i=0;i<ids.length;i++){
				Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
				query.setParameter(1,ids[i]);
				query.setParameter(2, regiones[i]);
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
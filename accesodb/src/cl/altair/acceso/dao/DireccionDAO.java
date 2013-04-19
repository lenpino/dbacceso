package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Direccion;

/**
 * A data access object (DAO) providing persistence and search support for
 * Direccion entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Direccion
 * @author MyEclipse Persistence Tools
 */

public class DireccionDAO {
	// property constants
	public static final String DIRECCION1 = "direccion1";
	public static final String DIRECCION2 = "direccion2";
	public static final String COMUNA = "comuna";
	public static final String CIUDAD = "ciudad";
	public static final String PAIS = "pais";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Direccion entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DireccionDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Direccion entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Direccion entity) {
		EntityManagerHelper.log("saving Direccion instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Direccion entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DireccionDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Direccion entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Direccion entity) {
		EntityManagerHelper
				.log("deleting Direccion instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Direccion.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Direccion entity and return it or a copy of it
	 * to the sender. A copy of the Direccion entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DireccionDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Direccion entity to update
	 * @return Direccion the persisted Direccion entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Direccion update(Direccion entity) {
		EntityManagerHelper
				.log("updating Direccion instance", Level.INFO, null);
		try {
			Direccion result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Direccion findById(Integer id) {
		EntityManagerHelper.log("finding Direccion instance with id: " + id,
				Level.INFO, null);
		try {
			Direccion instance = getEntityManager().find(Direccion.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Direccion entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Direccion property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Direccion> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Direccion> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Direccion instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Direccion model where model."
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

	public List<Direccion> findByDireccion1(Object direccion1,
			int... rowStartIdxAndCount) {
		return findByProperty(DIRECCION1, direccion1, rowStartIdxAndCount);
	}

	public List<Direccion> findByDireccion2(Object direccion2,
			int... rowStartIdxAndCount) {
		return findByProperty(DIRECCION2, direccion2, rowStartIdxAndCount);
	}

	public List<Direccion> findByComuna(Object comuna,
			int... rowStartIdxAndCount) {
		return findByProperty(COMUNA, comuna, rowStartIdxAndCount);
	}

	public List<Direccion> findByCiudad(Object ciudad,
			int... rowStartIdxAndCount) {
		return findByProperty(CIUDAD, ciudad, rowStartIdxAndCount);
	}

	public List<Direccion> findByPais(Object pais, int... rowStartIdxAndCount) {
		return findByProperty(PAIS, pais, rowStartIdxAndCount);
	}

	/**
	 * Find all Direccion entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Direccion> all Direccion entities
	 */
	@SuppressWarnings("unchecked")
	public List<Direccion> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Direccion instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Direccion model";
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

}
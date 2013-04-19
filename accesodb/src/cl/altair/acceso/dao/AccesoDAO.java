package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Acceso;

/**
 * A data access object (DAO) providing persistence and search support for
 * Acceso entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Acceso
 * @author MyEclipse Persistence Tools
 */

public class AccesoDAO {
	// property constants
	public static final String IDDEPENDENCIA = "iddependencia";
	public static final String IDVISITA = "idvisita";
	public static final String IDRECEPTOR = "idreceptor";
	public static final String IDRESERVA = "idreserva";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Acceso entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccesoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Acceso entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Acceso entity) {
		EntityManagerHelper.log("saving Acceso instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Acceso entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * AccesoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Acceso entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Acceso entity) {
		EntityManagerHelper.log("deleting Acceso instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Acceso.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Acceso entity and return it or a copy of it to
	 * the sender. A copy of the Acceso entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = AccesoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Acceso entity to update
	 * @return Acceso the persisted Acceso entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Acceso update(Acceso entity) {
		EntityManagerHelper.log("updating Acceso instance", Level.INFO, null);
		try {
			Acceso result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Acceso findById(Integer id) {
		EntityManagerHelper.log("finding Acceso instance with id: " + id,
				Level.INFO, null);
		try {
			Acceso instance = getEntityManager().find(Acceso.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Acceso entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Acceso property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Acceso> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Acceso> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Acceso instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Acceso model where model."
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

	public List<Acceso> findByIddependencia(Object iddependencia,
			int... rowStartIdxAndCount) {
		return findByProperty(IDDEPENDENCIA, iddependencia, rowStartIdxAndCount);
	}

	public List<Acceso> findByIdvisita(Object idvisita,
			int... rowStartIdxAndCount) {
		return findByProperty(IDVISITA, idvisita, rowStartIdxAndCount);
	}

	public List<Acceso> findByIdreceptor(Object idreceptor,
			int... rowStartIdxAndCount) {
		return findByProperty(IDRECEPTOR, idreceptor, rowStartIdxAndCount);
	}

	public List<Acceso> findByIdreserva(Object idreserva,
			int... rowStartIdxAndCount) {
		return findByProperty(IDRESERVA, idreserva, rowStartIdxAndCount);
	}

	/**
	 * Find all Acceso entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Acceso> all Acceso entities
	 */
	@SuppressWarnings("unchecked")
	public List<Acceso> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Acceso instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Acceso model";
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

	/**
	 *
	 * @return: Listado de accesos del dia de hoy
	 */
	@SuppressWarnings("unchecked")
	public List<Acceso> getTodayList() {
		EntityManagerHelper.log("Listando los accesos de hoy: ", Level.INFO, null);
		try {
			final String queryString = "select * from acceso where date(fechaingreso) = current_date";
			Query query = getEntityManager().createNativeQuery(queryString, Acceso.class);
			List<Acceso> losAccesos = (List<Acceso>)query.getResultList();
			return losAccesos;
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

}
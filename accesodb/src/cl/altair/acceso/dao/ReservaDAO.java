package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Reserva;

/**
 * A data access object (DAO) providing persistence and search support for
 * Reserva entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Reserva
 * @author MyEclipse Persistence Tools
 */

public class ReservaDAO {
	// property constants
	public static final String IDCONTACTO = "idcontacto";
	public static final String IDDEPENDENCIA = "iddependencia";
	public static final String FECHAEMISION = "fechaemision";
	public static final String FECHARESERVA = "fechareserva";
	public static final String ESTADO = "estado";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Reserva entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ReservaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Reserva entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Reserva entity) {
		EntityManagerHelper.log("saving Reserva instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Reserva entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ReservaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Reserva entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Reserva entity) {
		EntityManagerHelper.log("deleting Reserva instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Reserva.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Reserva entity and return it or a copy of it
	 * to the sender. A copy of the Reserva entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ReservaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Reserva entity to update
	 * @return Reserva the persisted Reserva entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Reserva update(Reserva entity) {
		EntityManagerHelper.log("updating Reserva instance", Level.INFO, null);
		try {
			Reserva result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Reserva findById(Integer id) {
		EntityManagerHelper.log("finding Reserva instance with id: " + id,
				Level.INFO, null);
		try {
			Reserva instance = getEntityManager().find(Reserva.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Reserva entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Reserva property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Reserva> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Reserva> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Reserva instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Reserva model where model."
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

	public List<Reserva> findByIdcontacto(Object idcontacto,
			int... rowStartIdxAndCount) {
		return findByProperty(IDCONTACTO, idcontacto, rowStartIdxAndCount);
	}

	public List<Reserva> findByIddependencia(Object iddependencia,
			int... rowStartIdxAndCount) {
		return findByProperty(IDDEPENDENCIA, iddependencia, rowStartIdxAndCount);
	}

	public List<Reserva> findByFechaemision(Object fechaemision,
			int... rowStartIdxAndCount) {
		return findByProperty(FECHAEMISION, fechaemision, rowStartIdxAndCount);
	}

	public List<Reserva> findByFechareserva(Object fechareserva,
			int... rowStartIdxAndCount) {
		return findByProperty(FECHARESERVA, fechareserva, rowStartIdxAndCount);
	}

	public List<Reserva> findByEstado(Object estado, int... rowStartIdxAndCount) {
		return findByProperty(ESTADO, estado, rowStartIdxAndCount);
	}

	/**
	 * Find all Reserva entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Reserva> all Reserva entities
	 */
	@SuppressWarnings("unchecked")
	public List<Reserva> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Reserva instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Reserva model";
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
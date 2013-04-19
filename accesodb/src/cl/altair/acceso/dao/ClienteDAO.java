package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Cliente;

/**
 * A data access object (DAO) providing persistence and search support for
 * Cliente entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Cliente
 * @author MyEclipse Persistence Tools
 */

public class ClienteDAO {
	// property constants
	public static final String IDEMPRESA = "idempresa";
	public static final String IDFACTURAR = "idfacturar";
	public static final String NUMEROUSUARIOS = "numerousuarios";
	public static final String ESTADO = "estado";
	public static final String FECHAINCORPORACION = "fechaincorporacion";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Cliente entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ClienteDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cliente entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Cliente entity) {
		EntityManagerHelper.log("saving Cliente instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Cliente entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ClienteDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Cliente entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Cliente entity) {
		EntityManagerHelper.log("deleting Cliente instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Cliente.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Cliente entity and return it or a copy of it
	 * to the sender. A copy of the Cliente entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ClienteDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Cliente entity to update
	 * @return Cliente the persisted Cliente entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Cliente update(Cliente entity) {
		EntityManagerHelper.log("updating Cliente instance", Level.INFO, null);
		try {
			Cliente result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Cliente findById(Integer id) {
		EntityManagerHelper.log("finding Cliente instance with id: " + id,
				Level.INFO, null);
		try {
			Cliente instance = getEntityManager().find(Cliente.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Cliente entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Cliente property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Cliente> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Cliente instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Cliente model where model."
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

	public List<Cliente> findByIdempresa(Object idempresa,
			int... rowStartIdxAndCount) {
		return findByProperty(IDEMPRESA, idempresa, rowStartIdxAndCount);
	}

	public List<Cliente> findByIdfacturar(Object idfacturar,
			int... rowStartIdxAndCount) {
		return findByProperty(IDFACTURAR, idfacturar, rowStartIdxAndCount);
	}

	public List<Cliente> findByNumerousuarios(Object numerousuarios,
			int... rowStartIdxAndCount) {
		return findByProperty(NUMEROUSUARIOS, numerousuarios,
				rowStartIdxAndCount);
	}

	public List<Cliente> findByEstado(Object estado, int... rowStartIdxAndCount) {
		return findByProperty(ESTADO, estado, rowStartIdxAndCount);
	}

	public List<Cliente> findByFechaincorporacion(Object fechaincorporacion,
			int... rowStartIdxAndCount) {
		return findByProperty(FECHAINCORPORACION, fechaincorporacion,
				rowStartIdxAndCount);
	}

	/**
	 * Find all Cliente entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Cliente> all Cliente entities
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Cliente instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Cliente model";
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
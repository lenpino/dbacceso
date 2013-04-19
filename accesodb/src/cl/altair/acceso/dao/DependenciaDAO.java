package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Dependencia;

/**
 * A data access object (DAO) providing persistence and search support for
 * Dependencia entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Dependencia
 * @author MyEclipse Persistence Tools
 */

public class DependenciaDAO {
	// property constants
	public static final String IDEDIFICIO = "idedificio";
	public static final String IDUSUARIOINMUEBLE = "idusuarioinmueble";
	public static final String IDENTIFICADOR = "identificador";
	public static final String TIPO = "tipo";
	public static final String TIPOUSUARIO = "tipousuario";
	public static final String PISO = "piso";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Dependencia entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DependenciaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Dependencia entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Dependencia entity) {
		EntityManagerHelper
				.log("saving Dependencia instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Dependencia entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * DependenciaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Dependencia entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Dependencia entity) {
		EntityManagerHelper.log("deleting Dependencia instance", Level.INFO,
				null);
		try {
			entity = getEntityManager().getReference(Dependencia.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Dependencia entity and return it or a copy of
	 * it to the sender. A copy of the Dependencia entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = DependenciaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Dependencia entity to update
	 * @return Dependencia the persisted Dependencia entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Dependencia update(Dependencia entity) {
		EntityManagerHelper.log("updating Dependencia instance", Level.INFO,
				null);
		try {
			Dependencia result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Dependencia findById(Integer id) {
		EntityManagerHelper.log("finding Dependencia instance with id: " + id,
				Level.INFO, null);
		try {
			Dependencia instance = getEntityManager().find(Dependencia.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Dependencia entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Dependencia property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Dependencia> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Dependencia> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Dependencia instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Dependencia model where model."
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

	public List<Dependencia> findByIdedificio(Object idedificio,
			int... rowStartIdxAndCount) {
		return findByProperty(IDEDIFICIO, idedificio, rowStartIdxAndCount);
	}

	public List<Dependencia> findByIdusuarioinmueble(Object idusuarioinmueble,
			int... rowStartIdxAndCount) {
		return findByProperty(IDUSUARIOINMUEBLE, idusuarioinmueble,
				rowStartIdxAndCount);
	}

	public List<Dependencia> findByIdentificador(Object identificador,
			int... rowStartIdxAndCount) {
		return findByProperty(IDENTIFICADOR, identificador, rowStartIdxAndCount);
	}

	public List<Dependencia> findByTipo(Object tipo, int... rowStartIdxAndCount) {
		return findByProperty(TIPO, tipo, rowStartIdxAndCount);
	}

	public List<Dependencia> findByTipousuario(Object tipousuario,
			int... rowStartIdxAndCount) {
		return findByProperty(TIPOUSUARIO, tipousuario, rowStartIdxAndCount);
	}

	public List<Dependencia> findByPiso(Object piso, int... rowStartIdxAndCount) {
		return findByProperty(PISO, piso, rowStartIdxAndCount);
	}

	/**
	 * Find all Dependencia entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Dependencia> all Dependencia entities
	 */
	@SuppressWarnings("unchecked")
	public List<Dependencia> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Dependencia instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from Dependencia model";
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
	 * Find all Dependencia entities.
	  	  @return List<Dependencia> all Dependencia entities
	 */
	@SuppressWarnings("unchecked")
	public List<Dependencia> findAll() {
					EntityManagerHelper.log("finding all Dependencia instances", Level.INFO, null);
			try {
			final String queryString = "select model from Dependencia model";
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
	
}
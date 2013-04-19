package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Usuario;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usuario entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Usuario
 * @author MyEclipse Persistence Tools
 */

public class UsuarioDAO {
	// property constants
	public static final String IDCONTACTO = "idcontacto";
	public static final String LOGIN = "login";
	public static final String CLAVE = "clave";
	public static final String FECHAINGRESO = "fechaingreso";
	public static final String ESTADO = "estado";
	public static final String IDUSUARIOINMUEBLE = "idusuarioinmueble";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Usuario entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsuarioDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Usuario entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Usuario entity) {
		EntityManagerHelper.log("saving Usuario instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Usuario entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsuarioDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Usuario entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Usuario entity) {
		EntityManagerHelper.log("deleting Usuario instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Usuario.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Usuario entity and return it or a copy of it
	 * to the sender. A copy of the Usuario entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UsuarioDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Usuario entity to update
	 * @return Usuario the persisted Usuario entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Usuario update(Usuario entity) {
		EntityManagerHelper.log("updating Usuario instance", Level.INFO, null);
		try {
			Usuario result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Usuario findById(Integer id) {
		EntityManagerHelper.log("finding Usuario instance with id: " + id,
				Level.INFO, null);
		try {
			Usuario instance = getEntityManager().find(Usuario.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Usuario entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Usuario property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Usuario> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Usuario instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Usuario model where model."
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

	public List<Usuario> findByIdcontacto(Object idcontacto,
			int... rowStartIdxAndCount) {
		return findByProperty(IDCONTACTO, idcontacto, rowStartIdxAndCount);
	}

	public List<Usuario> findByLogin(Object login, int... rowStartIdxAndCount) {
		return findByProperty(LOGIN, login, rowStartIdxAndCount);
	}

	public List<Usuario> findByClave(Object clave, int... rowStartIdxAndCount) {
		return findByProperty(CLAVE, clave, rowStartIdxAndCount);
	}

	public List<Usuario> findByFechaingreso(Object fechaingreso,
			int... rowStartIdxAndCount) {
		return findByProperty(FECHAINGRESO, fechaingreso, rowStartIdxAndCount);
	}

	public List<Usuario> findByEstado(Object estado, int... rowStartIdxAndCount) {
		return findByProperty(ESTADO, estado, rowStartIdxAndCount);
	}

	public List<Usuario> findByIdusuarioinmueble(Object idusuarioinmueble,
			int... rowStartIdxAndCount) {
		return findByProperty(IDUSUARIOINMUEBLE, idusuarioinmueble,
				rowStartIdxAndCount);
	}

	/**
	 * Find all Usuario entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Usuario> all Usuario entities
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Usuario instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Usuario model";
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
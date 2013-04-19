package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Edificio;

/**
 * A data access object (DAO) providing persistence and search support for
 * Edificio entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Edificio
 * @author MyEclipse Persistence Tools
 */

public class EdificioDAO {
	// property constants
	public static final String IDDIRECCION = "iddireccion";
	public static final String IDCLIENTE = "idcliente";
	public static final String IDPROPIETARIO = "idpropietario";
	public static final String TIPO = "tipo";
	public static final String NOMBRE = "nombre";
	public static final String PISOS = "pisos";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Edificio entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EdificioDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Edificio entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Edificio entity) {
		EntityManagerHelper.log("saving Edificio instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Edificio entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EdificioDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Edificio entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Edificio entity) {
		EntityManagerHelper.log("deleting Edificio instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Edificio.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Edificio entity and return it or a copy of it
	 * to the sender. A copy of the Edificio entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = EdificioDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Edificio entity to update
	 * @return Edificio the persisted Edificio entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Edificio update(Edificio entity) {
		EntityManagerHelper.log("updating Edificio instance", Level.INFO, null);
		try {
			Edificio result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Edificio findById(Integer id) {
		EntityManagerHelper.log("finding Edificio instance with id: " + id,
				Level.INFO, null);
		try {
			Edificio instance = getEntityManager().find(Edificio.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Edificio entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Edificio property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Edificio> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Edificio> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Edificio instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Edificio model where model."
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

	/**
	 * Find all Edificio entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Edificio property to query
	 * @param value
	 *            the property value to match
	 * @return List<Edificio> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Edificio> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log("finding Edificio instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Edificio model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}

	public List<Edificio> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}
	
	public List<Edificio> findByIddireccion(Object iddireccion,
			int... rowStartIdxAndCount) {
		return findByProperty(IDDIRECCION, iddireccion, rowStartIdxAndCount);
	}

	public List<Edificio> findByIdcliente(Object idcliente,
			int... rowStartIdxAndCount) {
		return findByProperty(IDCLIENTE, idcliente, rowStartIdxAndCount);
	}

	public List<Edificio> findByIdpropietario(Object idpropietario,
			int... rowStartIdxAndCount) {
		return findByProperty(IDPROPIETARIO, idpropietario, rowStartIdxAndCount);
	}

	public List<Edificio> findByTipo(Object tipo, int... rowStartIdxAndCount) {
		return findByProperty(TIPO, tipo, rowStartIdxAndCount);
	}

	public List<Edificio> findByNombre(Object nombre,
			int... rowStartIdxAndCount) {
		return findByProperty(NOMBRE, nombre, rowStartIdxAndCount);
	}

	public List<Edificio> findByPisos(Object nombre,
			int... rowStartIdxAndCount) {
		return findByProperty(PISOS, nombre, rowStartIdxAndCount);
	}

	/**
	 * Find all Edificio entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Edificio> all Edificio entities
	 */
	@SuppressWarnings("unchecked")
	public List<Edificio> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Edificio instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Edificio model";
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
	 * Find all Edificio entities.
	 * @return List<Edificio> all Edificio entities
	 */
	@SuppressWarnings("unchecked")
	public List<Edificio> findAll() {
		EntityManagerHelper.log("finding all Edificio instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Edificio model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}
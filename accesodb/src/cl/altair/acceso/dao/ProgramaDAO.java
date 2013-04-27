package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Persona;

/**
 * A data access object (DAO) providing persistence and search support for
 * Persona entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Persona
 * @author MyEclipse Persistence Tools
 */

public class ProgramaDAO {
	// property constants
	public static final String RUT = "rut";
	public static final String DV = "dv";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDO = "apellido";
	public static final String NOMBREAD = "nombread";
	public static final String APELLIDOMAT = "apellidomat";
	public static final String FECHANAC = "fechanac";
	public static final String EMAIL = "email";
	public static final String CELULAR = "celular";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Persona entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Persona entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Persona entity) {
		EntityManagerHelper.log("saving Persona instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Persona entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PersonaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Persona entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Persona entity) {
		EntityManagerHelper.log("deleting Persona instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Persona.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Persona entity and return it or a copy of it
	 * to the sender. A copy of the Persona entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PersonaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Persona entity to update
	 * @return Persona the persisted Persona entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Persona update(Persona entity) {
		EntityManagerHelper.log("updating Persona instance", Level.INFO, null);
		try {
			Persona result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Persona findById(Integer id) {
		EntityManagerHelper.log("finding Persona instance with id: " + id,
				Level.INFO, null);
		try {
			Persona instance = getEntityManager().find(Persona.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Persona entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Persona property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Persona> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Persona instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Persona model where model."
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

	public List<Persona> findByRut(Object rut, int... rowStartIdxAndCount) {
		return findByProperty(RUT, rut, rowStartIdxAndCount);
	}

	public List<Persona> findByDv(Object dv, int... rowStartIdxAndCount) {
		return findByProperty(DV, dv, rowStartIdxAndCount);
	}

	public List<Persona> findByNombre(Object nombre, int... rowStartIdxAndCount) {
		return findByProperty(NOMBRE, nombre, rowStartIdxAndCount);
	}

	public List<Persona> findByApellido(Object apellido,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDO, apellido, rowStartIdxAndCount);
	}

	public List<Persona> findByNombread(Object nombread,
			int... rowStartIdxAndCount) {
		return findByProperty(NOMBREAD, nombread, rowStartIdxAndCount);
	}

	public List<Persona> findByApellidomat(Object apellidomat,
			int... rowStartIdxAndCount) {
		return findByProperty(APELLIDOMAT, apellidomat, rowStartIdxAndCount);
	}

	public List<Persona> findByFechanac(Object fechanac,
			int... rowStartIdxAndCount) {
		return findByProperty(FECHANAC, fechanac, rowStartIdxAndCount);
	}

	public List<Persona> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<Persona> findByCelular(Object celular,
			int... rowStartIdxAndCount) {
		return findByProperty(CELULAR, celular, rowStartIdxAndCount);
	}

	/**
	 * Find all Persona entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Persona> all Persona entities
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Persona instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Persona model";
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
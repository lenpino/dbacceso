package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Contacto;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contacto entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Contacto
 * @author MyEclipse Persistence Tools
 */

public class ContactoDAO {
	// property constants
	public static final String IDPERSONA = "idpersona";
	public static final String IDUNIDAD = "idunidad";
	public static final String TELEFONOOF = "telefonoof";
	public static final String CELULAR = "celular";
	public static final String CARGO = "cargo";
	public static final String EMAIL = "email";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Contacto entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ContactoDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Contacto entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Contacto entity) {
		EntityManagerHelper.log("saving Contacto instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Contacto entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ContactoDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Contacto entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Contacto entity) {
		EntityManagerHelper.log("deleting Contacto instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Contacto.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Contacto entity and return it or a copy of it
	 * to the sender. A copy of the Contacto entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ContactoDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Contacto entity to update
	 * @return Contacto the persisted Contacto entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Contacto update(Contacto entity) {
		EntityManagerHelper.log("updating Contacto instance", Level.INFO, null);
		try {
			Contacto result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Contacto findById(Integer id) {
		EntityManagerHelper.log("finding Contacto instance with id: " + id,
				Level.INFO, null);
		try {
			Contacto instance = getEntityManager().find(Contacto.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Contacto entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Contacto property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Contacto> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Contacto> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Contacto instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Contacto model where model."
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

	public List<Contacto> findByIdpersona(Object idpersona,
			int... rowStartIdxAndCount) {
		return findByProperty(IDPERSONA, idpersona, rowStartIdxAndCount);
	}

	public List<Contacto> findByIdunidad(Object idunidad,
			int... rowStartIdxAndCount) {
		return findByProperty(IDUNIDAD, idunidad, rowStartIdxAndCount);
	}

	public List<Contacto> findByTelefonoof(Object telefonoof,
			int... rowStartIdxAndCount) {
		return findByProperty(TELEFONOOF, telefonoof, rowStartIdxAndCount);
	}

	public List<Contacto> findByCelular(Object celular,
			int... rowStartIdxAndCount) {
		return findByProperty(CELULAR, celular, rowStartIdxAndCount);
	}

	public List<Contacto> findByCargo(Object cargo, int... rowStartIdxAndCount) {
		return findByProperty(CARGO, cargo, rowStartIdxAndCount);
	}

	public List<Contacto> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	/**
	 * Find all Contacto entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Contacto> all Contacto entities
	 */
	@SuppressWarnings("unchecked")
	public List<Contacto> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Contacto instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Contacto model";
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
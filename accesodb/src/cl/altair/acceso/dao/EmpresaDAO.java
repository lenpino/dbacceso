package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Empresa;

/**
 * A data access object (DAO) providing persistence and search support for
 * Empresa entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Empresa
 * @author MyEclipse Persistence Tools
 */

public class EmpresaDAO {
	// property constants
	public static final String RUT = "rut";
	public static final String DV = "dv";
	public static final String RAZONSOCIAL = "razonSocial";
	public static final String FANTASIA = "nombreFantasia";
	public static final String TELEFONO = "telefono";
	public static final String ESTADO = "estado";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Empresa entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EmpresaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Empresa entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Empresa entity) {
		EntityManagerHelper.log("saving Empresa instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Empresa entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EmpresaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Empresa entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Empresa entity) {
		EntityManagerHelper.log("deleting Empresa instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Empresa.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Empresa entity and return it or a copy of it
	 * to the sender. A copy of the Empresa entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = EmpresaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Empresa entity to update
	 * @return Empresa the persisted Empresa entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Empresa update(Empresa entity) {
		EntityManagerHelper.log("updating Empresa instance", Level.INFO, null);
		try {
			Empresa result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Empresa findById(Integer id) {
		EntityManagerHelper.log("finding Empresa instance with id: " + id,
				Level.INFO, null);
		try {
			Empresa instance = getEntityManager().find(Empresa.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Empresa entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Empresa property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Empresa> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Empresa> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Empresa instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Empresa model where model."
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

	public List<Empresa> findByRut(Object rut, int... rowStartIdxAndCount) {
		return findByProperty(RUT, rut, rowStartIdxAndCount);
	}

	public List<Empresa> findByDv(Object dv, int... rowStartIdxAndCount) {
		return findByProperty(DV, dv, rowStartIdxAndCount);
	}

	public List<Empresa> findByRazonsocial(Object razonsocial,
			int... rowStartIdxAndCount) {
		return findByProperty(RAZONSOCIAL, razonsocial, rowStartIdxAndCount);
	}

	public List<Empresa> findByFantasia(Object fantasia,
			int... rowStartIdxAndCount) {
		return findByProperty(FANTASIA, fantasia, rowStartIdxAndCount);
	}

	public List<Empresa> findByTelefono(Object fantasia,
			int... rowStartIdxAndCount) {
		return findByProperty(TELEFONO, fantasia, rowStartIdxAndCount);
	}

	public List<Empresa> findByEstado(Object fantasia,
			int... rowStartIdxAndCount) {
		return findByProperty(ESTADO, fantasia, rowStartIdxAndCount);
	}

	/**
	 * Find all Empresa entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Empresa> all Empresa entities
	 */
	@SuppressWarnings("unchecked")
	public List<Empresa> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Empresa instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from Empresa model";
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
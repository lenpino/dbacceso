package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.UsuarioInmueble;

/**
 * A data access object (DAO) providing persistence and search support for
 * UsuarioInmueble entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see cl.mycompany.acceso.modelo.UsuarioInmueble
 * @author MyEclipse Persistence Tools
 */

public class UsuarioinmuebleDAO {
	// property constants
	public static final String RUT = "rut";
	public static final String DV = "dv";
	public static final String NOMBRE = "nombre";
	public static final String TIPO = "tipo";
	public static final String TIPOUSO = "tipouso";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved UsuarioInmueble entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsuarioInmuebleDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UsuarioInmueble entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UsuarioInmueble entity) {
		EntityManagerHelper.log("saving UsuarioInmueble instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent UsuarioInmueble entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UsuarioInmuebleDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UsuarioInmueble entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UsuarioInmueble entity) {
		EntityManagerHelper.log("deleting UsuarioInmueble instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(UsuarioInmueble.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved UsuarioInmueble entity and return it or a copy
	 * of it to the sender. A copy of the UsuarioInmueble entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UsuarioInmuebleDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UsuarioInmueble entity to update
	 * @return UsuarioInmueble the persisted UsuarioInmueble entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UsuarioInmueble update(UsuarioInmueble entity) {
		EntityManagerHelper.log("updating UsuarioInmueble instance",
				Level.INFO, null);
		try {
			UsuarioInmueble result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public UsuarioInmueble findById(Integer id) {
		EntityManagerHelper.log("finding UsuarioInmueble instance with id: "
				+ id, Level.INFO, null);
		try {
			UsuarioInmueble instance = getEntityManager().find(
					UsuarioInmueble.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all UsuarioInmueble entities with a specific property value.  
	 
	  @param propertyName the name of the UsuarioInmueble property to query
	  @param value the property value to match
	  	  @return List<UsuarioInmueble> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<UsuarioInmueble> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding UsuarioInmueble instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from UsuarioInmueble model where model." 
			 						+ propertyName + "= :propertyValue";
								Query query = getEntityManager().createQuery(queryString);
					query.setParameter("propertyValue", value);
					return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.closeEntityManager();
						EntityManagerHelper.log("find by property name failed", Level.SEVERE, re);
				throw re;
		} finally{
			EntityManagerHelper.closeEntityManager();
		}
	}			

	public List<UsuarioInmueble> findByRut(Object rut) {
		return findByProperty(RUT, rut);
	}
	
	public List<UsuarioInmueble> findByDv(Object dv) {
		return findByProperty(DV, dv);
	}

	public List<UsuarioInmueble> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<UsuarioInmueble> findByTipo(Object tipo) {
		return findByProperty(TIPO, tipo);
	}

	public List<UsuarioInmueble> findByTipouso(Object tipouso) {
		return findByProperty(TIPOUSO, tipouso);
	}
	/**
	 * Find all UsuarioInmueble entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UsuarioInmueble property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<UsuarioInmueble> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioInmueble> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding UsuarioInmueble instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from UsuarioInmueble model where model."
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

	public List<UsuarioInmueble> findByRut(Object rut,
			int... rowStartIdxAndCount) {
		return findByProperty(RUT, rut, rowStartIdxAndCount);
	}

	public List<UsuarioInmueble> findByDv(Object dv, int... rowStartIdxAndCount) {
		return findByProperty(DV, dv, rowStartIdxAndCount);
	}

	public List<UsuarioInmueble> findByNombre(Object nombre,
			int... rowStartIdxAndCount) {
		return findByProperty(NOMBRE, nombre, rowStartIdxAndCount);
	}

	public List<UsuarioInmueble> findByTipo(Object tipo,
			int... rowStartIdxAndCount) {
		return findByProperty(TIPO, tipo, rowStartIdxAndCount);
	}

	public List<UsuarioInmueble> findByTipouso(Object tipouso,
			int... rowStartIdxAndCount) {
		return findByProperty(TIPOUSO, tipouso, rowStartIdxAndCount);
	}

	/**
	 * Find all UsuarioInmueble entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UsuarioInmueble> all UsuarioInmueble entities
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioInmueble> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all UsuarioInmueble instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from UsuarioInmueble model";
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
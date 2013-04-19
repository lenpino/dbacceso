package cl.altair.acceso.dao;

import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import cl.altair.acceso.modelo.Comuna;

/**
 * A data access object (DAO) providing persistence and search support for Comuna
 * entities. Transaction contComuna of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see cl.mycompany.acceso.modelo.Comuna
 * @author MyEclipse Persistence Tools
 */

public class ComunaDAO {
	// property constants
	public static final String COMUNAID = "comunaId";
	public static final String COMUNANOMBRE = "comunaNombre";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved Comuna entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ComunaDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Comuna entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Comuna entity) {
		EntityManagerHelper.log("saving Comuna instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Comuna entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ComunaDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Comuna entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Comuna entity) {
		EntityManagerHelper.log("deleting Comuna instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(Comuna.class, entity.getComunaId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Comuna entity and return it or a copy of it to
	 * the sender. A copy of the Comuna entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ComunaDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Comuna entity to update
	 * @return Comuna the persisted Comuna entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Comuna update(Comuna entity) {
		EntityManagerHelper.log("updating Comuna instance", Level.INFO, null);
		try {
			Comuna result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Comuna findById(Integer id) {
		EntityManagerHelper.log("finding Comuna instance with id: " + id,
				Level.INFO, null);
		try {
			Comuna instance = getEntityManager().find(Comuna.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Comuna entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Comuna property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Comuna> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Comuna> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding Comuna instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Comuna model where model."
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

	public List<Comuna> findByNombreComuna(Object Comunanombre, int... rowStartIdxAndCount) {
		return findByProperty(COMUNANOMBRE, Comunanombre, rowStartIdxAndCount);
	}

	/**
	 * Find all Comuna entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Comuna> all Comuna entities
	 */
	@SuppressWarnings("unchecked")
	public List<Comuna> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all Comuna instances", Level.INFO, null);
		try {
			final String queryString = "select model from Comuna model";
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
	 * Find all Comuna entities with a specific property value.  
	 
	  @param propertyName the name of the Comuna property to query
	  @param value the property value to match
	  	  @return List<Comuna> found by query
	 */
    @SuppressWarnings("unchecked")
    public List<Comuna> findByProperty(String propertyName, final Object value
        ) {
    				EntityManagerHelper.log("finding Comuna instance with property: " + propertyName + ", value: " + value, Level.INFO, null);
			try {
			final String queryString = "select model from Comuna model where model." 
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
	public List<Comuna> findByNombre(Object nombre
	) {
		return findByProperty(COMUNANOMBRE, nombre
		);
	}
	
	public static void creaComunas(){
		Integer[] ids = {
				1101,  
				1107,  
				1401,  
				1402,  
				1403,  
				1404,  
				1405,  
				2101,  
				2102,  
				2103,  
				2104,  
				2201,  
				2202,  
				2203,  
				2301,  
				2302,  
				3101,  
				3102,  
				3103,  
				3201,  
				3202,  
				3301,  
				3302,  
				3303,  
				3304,  
				4101,  
				4102,  
				4103,  
				4104,  
				4105,  
				4106,  
				4201,  
				4202,  
				4203,  
				4204,  
				4301,  
				4302,  
				4303,  
				4304,  
				4305,  
				5101,  
				5102,  
				5103,  
				5104,  
				5105,  
				5107,  
				5109,  
				5201,  
				5301,  
				5302,  
				5303,  
				5304,  
				5401,  
				5402,  
				5403,  
				5404,  
				5405,  
				5501,  
				5502,  
				5503,  
				5504,  
				5506,  
				5601,  
				5602,  
				5603,  
				5604,  
				5605,  
				5606,  
				5701,  
				5702,  
				5703,  
				5704,  
				5705,  
				5706,  
				5801,  
				5802,  
				5803,  
				5804,  
				6101,  
				6102,  
				6103,  
				6104,  
				6105,  
				6106,  
				6107,  
				6108,  
				6109,  
				6110,  
				6111,  
				6112,  
				6113,  
				6114,  
				6115,  
				6116,  
				6117,  
				6201,  
				6202,  
				6203,  
				6204,  
				6205,  
				6206,  
				6301,  
				6302,  
				6303,  
				6304,  
				6305,  
				6306,  
				6307,  
				6308,  
				6309,  
				6310,  
				7101,  
				7102,  
				7103,  
				7104,  
				7105,  
				7106,  
				7107,  
				7108,  
				7109,  
				7110,  
				7201,  
				7202,  
				7203,  
				7301,  
				7302,  
				7303,  
				7304,  
				7305,  
				7306,  
				7307,  
				7308,  
				7309,  
				7401,  
				7402,  
				7403,  
				7404,  
				7405,  
				7406,  
				7407,  
				7408,  
				8101,  
				8102,  
				8103,  
				8104,  
				8105,  
				8106,  
				8107,  
				8108,  
				8109,  
				8110,  
				8111,  
				8112,  
				8201,  
				8202,  
				8203,  
				8204,  
				8205,  
				8206,  
				8207,  
				8301,  
				8302,  
				8303,  
				8304,  
				8305,  
				8306,  
				8307,  
				8308,  
				8309,  
				8310,  
				8311,  
				8312,  
				8313,  
				8314,  
				8401,  
				8402,  
				8403,  
				8404,  
				8405,  
				8406,  
				8407,  
				8408,  
				8409,  
				8410,  
				8411,  
				8412,  
				8413,  
				8414,  
				8415,  
				8416,  
				8417,  
				8418,  
				8419,  
				8420,  
				8421,  
				9101,  
				9102,  
				9103,  
				9104,  
				9105,  
				9106,  
				9107,  
				9108,  
				9109,  
				9110,  
				9111,  
				9112,  
				9113,  
				9114,  
				9115,  
				9116,  
				9117,  
				9118,  
				9119,  
				9120,  
				9121,  
				9201,  
				9202,  
				9203,  
				9204,  
				9205,  
				9206,  
				9207,  
				9208,  
				9209,  
				9210,  
				9211,  
				10101,  
				10102,  
				10103,  
				10104,  
				10105,  
				10106,  
				10107,  
				10108,  
				10109,  
				10201,  
				10202,  
				10203,  
				10204,  
				10205,  
				10206,  
				10207,  
				10208,  
				10209,  
				10210,  
				10301,  
				10302,  
				10303,  
				10304,  
				10305,  
				10306,  
				10307,  
				10401,  
				10402,  
				10403,  
				10404,  
				11101,  
				11102,  
				11201,  
				11202,  
				11203,  
				11301,  
				11302,
				11303,  
				11401,  
				11402,  
				12101,  
				12102,  
				12103,  
				12104,  
				12201,  
				12202,  
				12301,  
				12302,  
				12303,  
				12401,  
				12402,  
				13101,  
				13102,  
				13103,  
				13104,  
				13105,  
				13106,  
				13107,  
				13108,  
				13109,  
				13110,  
				13111,  
				13112,  
				13113,  
				13114,  
				13115,  
				13116,  
				13117,  
				13118,  
				13119,  
				13120,  
				13121,  
				13122,  
				13123,  
				13124,  
				13125,  
				13126,  
				13127,  
				13128,  
				13129,  
				13130,  
				13131,  
				13132,  
				13201,  
				13202,  
				13203,  
				13301,  
				13302,  
				13303,  
				13401,  
				13402,  
				13403,  
				13404,  
				13501,  
				13502,  
				13503,  
				13504,  
				13505,  
				13601,  
				13602,  
				13603,  
				13604,  
				13605,  
				14101,  
				14102,  
				14103,  
				14104,  
				14105,  
				14106,  
				14107,  
				14108,  
				14201,  
				14202,  
				14203,  
				14204,  
				15101,  
				15102,  
				15201,  
				15202			
		};
		String[] nombres = {
			    "Iquique",   
			    "Alto Hospicio",   
			    "Pozo Almonte",   
			    "Camiña",   
			    "Colchane",   
			    "Huara",   
			    "Pica",   
			    "Antofagasta",   
			    "Mejillones",   
			    "Sierra Gorda",   
			    "Taltal",   
			    "Calama",   
			    "Ollagüe",   
			    "San Pedro de Atacama",   
			    "Tocopilla",   
			    "María Elena",   
			    "Copiapó",   
			    "Caldera",   
			    "Tierra Amarilla",   
			    "Chañaral",   
			    "Diego de Almagro",   
			    "Vallenar",   
			    "Alto del Carmen",   
			    "Freirina",   
			    "Huasco",   
			    "La Serena",   
			    "Coquimbo",   
			    "Andacollo",   
			    "La Higuera",   
			    "Paihuano",   
			    "Vicuña",   
			    "Illapel",   
			    "Canela",   
			    "Los Vilos",   
			    "Salamanca",   
			    "Ovalle",   
			    "Combarbalá",   
			    "Monte Patria",   
			    "Punitaqui",   
			    "Río Hurtado",   
			    "Valparaíso",   
			    "Casablanca",   
			    "Concón",   
			    "Juan Fernández",   
			    "Puchuncaví",   
			    "Quintero",   
			    "Viña del Mar",   
			    "Isla de Pascua",   
			    "Los Andes",   
			    "Calle Larga",   
			    "Rinconada",   
			    "San Esteban",   
			    "La Ligua",   
			    "Cabildo",   
			    "Papudo",   
			    "Petorca",   
			    "Zapallar",   
			    "Quillota",   
			    "La Calera",   
			    "Hijuelas",   
			    "La Cruz",   
			    "Nogales",   
			    "San Antonio",   
			    "Algarrobo",   
			    "Cartagena",   
			    "El Quisco",   
			    "El Tabo",   
			    "Santo Domingo",   
			    "San Felipe",   
			    "Catemu",   
			    "Llay Llay",   
			    "Panquehue",   
			    "Putaendo",   
			    "Santa María",   
			    "Quilpué",   
			    "Limache",   
			    "Olmué",   
			    "Villa Alemana",   
			    "Rancagua",   
			    "Codegua",   
			    "Coinco",   
			    "Coltauco",   
			    "Doñihue",   
			    "Graneros",   
			    "Las Cabras",   
			    "Machalí",   
			    "Malloa",   
			    "Mostazal",   
			    "Olivar",   
			    "Peumo",   
			    "Pichidegua",   
			    "Quinta de Tilcoco",   
			    "Rengo",   
			    "Requínoa",   
			    "San Vicente",   
			    "Pichilemu",   
			    "La Estrella",   
			    "Litueche",   
			    "Marchihue",   
			    "Navidad",   
			    "Paredones",   
			    "San Fernando",   
			    "Chépica",   
			    "Chimbarongo",   
			    "Lolol",   
			    "Nancagua",   
			    "Palmilla",   
			    "Peralillo",   
			    "Placilla",   
			    "Pumanque",   
			    "Santa Cruz",   
			    "Talca",   
			    "Constitución",   
			    "Curepto",   
			    "Empedrado",   
			    "Maule",   
			    "Pelarco",   
			    "Pencahue",   
			    "Río Claro",   
			    "San Clemente",   
			    "San Rafael",   
			    "Cauquenes",   
			    "Chanco",   
			    "Pelluhue",   
			    "Curicó",   
			    "Hualañé",   
			    "Licantén",   
			    "Molina",   
			    "Rauco",   
			    "Romeral",   
			    "Sagrada Familia",   
			    "Teno",   
			    "Vichuquén",   
			    "Linares",   
			    "Colbún",   
			    "Longaví",   
			    "Parral",   
			    "Retiro",   
			    "San Javier",   
			    "Villa Alegre",   
			    "Yerbas Buenas",   
			    "Concepción",   
			    "Coronel",   
			    "Chiguayante",   
			    "Florida",   
			    "Hualqui",   
			    "Lota",   
			    "Penco",   
			    "San Pedro de la Paz",   
			    "Santa Juana",   
			    "Talcahuano",   
			    "Tomé",   
			    "Hualpén",   
			    "Lebu",   
			    "Arauco",   
			    "Cañete",   
			    "Contulmo",   
			    "Curanilahue",   
			    "Los Álamos",   
			    "Tirúa",   
			    "Los Ángeles",   
			    "Antuco",   
			    "Cabrero",   
			    "Laja",   
			    "Mulchén",   
			    "Nacimiento",   
			    "Negrete",   
			    "Quilaco",   
			    "Quilleco",   
			    "San Rosendo",   
			    "Santa Bárbara",   
			    "Tucapel",   
			    "Yumbel",   
			    "Alto Biobío",   
			    "Chillán",   
			    "Bulnes",   
			    "Cobquecura",   
			    "Coelemu",   
			    "Coihueco",   
			    "Chillán Viejo",   
			    "El Carmen",   
			    "Ninhue",   
			    "Ñiquén",   
			    "Pemuco",   
			    "Pinto",   
			    "Portezuelo",   
			    "Quillón",   
			    "Quirihue",   
			    "Ránquil",   
			    "San Carlos",   
			    "San Fabián",   
			    "San Ignacio",   
			    "San Nicolás",   
			    "Treguaco",   
			    "Yungay",   
			    "Temuco",   
			    "Carahue",   
			    "Cunco",   
			    "Curarrehue",   
			    "Freire",   
			    "Galvarino",   
			    "Gorbea",   
			    "Lautaro",   
			    "Loncoche",   
			    "Melipeuco",   
			    "Nueva Imperial",   
			    "Padre las Casas",   
			    "Perquenco",   
			    "Pitrufquén",   
			    "Pucón",   
			    "Saavedra",   
			    "Teodoro Schmidt",   
			    "Toltén",   
			    "Vilcún",   
			    "Villarrica",   
			    "Cholchol",   
			    "Angol",   
			    "Collipulli",   
			    "Curacautín",   
			    "Ercilla",   
			    "Lonquimay",   
			    "Los Sauces",   
			    "Lumaco",   
			    "Purén",   
			    "Renaico",   
			    "Traiguén",   
			    "Victoria",   
			    "Puerto Montt",   
			    "Calbuco",   
			    "Cochamó",   
			    "Fresia",   
			    "Frutillar",   
			    "Los Muermos",   
			    "Llanquihue",   
			    "Maullín",   
			    "Puerto Varas",   
			    "Castro",   
			    "Ancud",   
			    "Chonchi",   
			    "Curaco de Vélez",   
			    "Dalcahue",   
			    "Puqueldón",   
			    "Queilén",   
			    "Quellón",   
			    "Quemchi",   
			    "Quinchao",   
			    "Osorno",   
			    "Puerto Octay",   
			    "Purranque",   
			    "Puyehue",   
			    "Río Negro",   
			    "San Juan de la Costa",   
			    "San Pablo",   
			    "Chaitén",   
			    "Futaleufú",   
			    "Hualaihué",   
			    "Palena",   
			    "Coyhaique",   
			    "Lago Verde",   
			    "Aysén",   
			    "Cisnes",   
			    "Guaitecas",   
			    "Cochrane",   
			    "O'Higgins",   
			    "Tortel",   
			    "Chile Chico",   
			    "Río Ibáñez",   
			    "Punta Arenas",   
			    "Laguna Blanca",   
			    "Río Verde",   
			    "San Gregorio",   
			    "Cabo de Hornos",   
			    "Antártica",   
			    "Porvenir",   
			    "Primavera",   
			    "Timaukel",   
			    "Natales",   
			    "Torres del Paine",   
			    "Santiago",   
			    "Cerrillos",   
			    "Cerro Navia",   
			    "Conchalí",   
			    "El Bosque",   
			    "Estación Central",   
			    "Huechuraba",   
			    "Independencia",   
			    "La Cisterna",   
			    "La Florida",   
			    "La Granja",   
			    "La Pintana",   
			    "La Reina",   
			    "Las Condes",   
			    "Lo Barnechea",   
			    "Lo Espejo",   
			    "Lo Prado",   
			    "Macul",   
			    "Maipú",   
			    "Ñuñoa",   
			    "Pedro Aguirre Cerda",   
			    "Peñalolén",   
			    "Providencia",   
			    "Pudahuel",   
			    "Quilicura",   
			    "Quinta Normal",   
			    "Recoleta",   
			    "Renca",   
			    "San Joaquín",   
			    "San Miguel",   
			    "San Ramón",   
			    "Vitacura",   
			    "Puente Alto",   
			    "Pirque",   
			    "San José de Maipo",   
			    "Colina",   
			    "Lampa",   
			    "Tiltil",   
			    "San Bernardo",   
			    "Buin",   
			    "Calera de Tango",   
			    "Paine",   
			    "Melipilla",   
			    "Alhué",   
			    "Curacaví",   
			    "María Pinto",   
			    "San Pedro",   
			    "Talagante",   
			    "El Monte",   
			    "Isla de Maipo",   
			    "Padre Hurtado",   
			    "Peñaflor",   
			    "Valdivia",   
			    "Corral",   
			    "Lanco",   
			    "Los Lagos",   
			    "Máfil",   
			    "Mariquina",   
			    "Paillaco",   
			    "Panguipulli",   
			    "La Unión",   
			    "Futrono",   
			    "Lago Ranco",   
			    "Río Bueno",   
			    "Arica",   
			    "Camarones",   
			    "Putre",   
			    "General Lagos"				
		};
		Integer[] idsP = {
				  11,
				  11,
				  14,
				  14,
				  14,
				  14,
				  14,
				  21,
				  21,
				  21,
				  21,
				  22,
				  22,
				  22,
				  23,
				  23,
				  31,
				  31,
				  31,
				  32,
				  32,
				  33,
				  33,
				  33,
				  33,
				  41,
				  41,
				  41,
				  41,
				  41,
				  41,
				  42,
				  42,
				  42,
				  42,
				  43,
				  43,
				  43,
				  43,
				  43,
				  51,
				  51,
				  51,
				  51,
				  51,
				  51,
				  51,
				  52,
				  53,
				  53,
				  53,
				  53,
				  54,
				  54,
				  54,
				  54,
				  54,
				  55,
				  55,
				  55,
				  55,
				  55,
				  56,
				  56,
				  56,
				  56,
				  56,
				  56,
				  57,
				  57,
				  57,
				  57,
				  57,
				  57,
				  58,
				  58,
				  58,
				  58,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  61,
				  62,
				  62,
				  62,
				  62,
				  62,
				  62,
				  63,
				  63,
				  63,
				  63,
				  63,
				  63,
				  63,
				  63,
				  63,
				  63,
				  71,
				  71,
				  71,
				  71,
				  71,
				  71,
				  71,
				  71,
				  71,
				  71,
				  72,
				  72,
				  72,
				  73,
				  73,
				  73,
				  73,
				  73,
				  73,
				  73,
				  73,
				  73,
				  74,
				  74,
				  74,
				  74,
				  74,
				  74,
				  74,
				  74,
				  81,
				  81,
				  81,
				  81,
				  81,
				  81,
				  81,
				  81,
				  81,
				  81,
				  81,
				  81,
				  82,
				  82,
				  82,
				  82,
				  82,
				  82,
				  82,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  83,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  84,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  91,
				  92,
				  92,
				  92,
				  92,
				  92,
				  92,
				  92,
				  92,
				  92,
				  92,
				  92,
				  101,
				  101,
				  101,
				  101,
				  101,
				  101,
				  101,
				  101,
				  101,
				  102,
				  102,
				  102,
				  102,
				  102,
				  102,
				  102,
				  102,
				  102,
				  102,
				  103,
				  103,
				  103,
				  103,
				  103,
				  103,
				  103,
				  104,
				  104,
				  104,
				  104,
				  111,
				  111,
				  112,
				  112,
				  112,
				  113,
				  113,
				  113,
				  114,
				  114,
				  121,
				  121,
				  121,
				  121,
				  122,
				  122,
				  123,
				  123,
				  123,
				  124,
				  124,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  131,
				  132,
				  132,
				  132,
				  133,
				  133,
				  133,
				  134,
				  134,
				  134,
				  134,
				  135,
				  135,
				  135,
				  135,
				  135,
				  136,
				  136,
				  136,
				  136,
				  136,
				  141,
				  141,
				  141,
				  141,
				  141,
				  141,
				  141,
				  141,
				  142,
				  142,
				  142,
				  142,
				  151,
				  151,
				  152,
				  152				
		};
		try {
			final String queryString = "INSERT INTO \"APP\".comuna VALUES (?, ?, ?)";
			EntityManagerHelper.beginTransaction();
			for(int i=0;i<ids.length;i++){
				Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
				query.setParameter(1,ids[i]);
				query.setParameter(2, nombres[i]);
				query.setParameter(3, idsP[i]);
				query.executeUpdate();
			}
	    	EntityManagerHelper.commit();
		} catch (RuntimeException re) {
        	EntityManagerHelper.closeEntityManager();
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		} finally{
        	EntityManagerHelper.closeEntityManager();
		}
	}
	
	/**
	 * Metodo Utilitario para encontrar las comunas asociadas a una region en particular
	 * @param idRegion: El ID de la region seleccionada
	 * @return: Listado de comunas asociadas a esa region
	 */
	public static List<Comuna> getComunasRegion(Integer idRegion){
		final String queryString = "select c.COMUNA_ID, c.COMUNA_NOMBRE, c.COMUNA_PROVINCIA_ID from provincia p, comuna c where p.PROVINCIA_REGION_ID = ? and c.COMUNA_PROVINCIA_ID = p.PROVINCIA_ID";
		Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString, Comuna.class);
		query.setParameter(1,idRegion.intValue());
		@SuppressWarnings("unchecked")
		List<Comuna> lasComunas = (List<Comuna>)query.getResultList();
		return lasComunas;
	}
	//select p.PROVINCIA_NOMBRE, c.COMUNA_NOMBRE from provincia p, comuna c where p.PROVINCIA_REGION_ID = 8 and c.COMUNA_PROVINCIA_ID = p.PROVINCIA_ID
}
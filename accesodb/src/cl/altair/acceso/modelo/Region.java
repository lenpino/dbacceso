package cl.altair.acceso.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="region_id")
	private Integer regionId;

	@Column(name="region_nombre")
	private String regionNombre;

	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="region", fetch=FetchType.EAGER)
	private Set<Provincia> provincias;

	public Region() {
	}
	
	/** minimal constructor */
    public Region(Integer id, String nombre) {
        this.regionId = id;
        this.regionNombre = nombre;;
    }

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionNombre() {
		return this.regionNombre;
	}

	public void setRegionNombre(String regionNombre) {
		this.regionNombre = regionNombre;
	}

	public Set<Provincia> getProvincias() {
		return this.provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}

}
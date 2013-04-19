package cl.altair.acceso.modelo;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the provincia database table.
 * 
 */
@Entity
public class Provincia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="provincia_id")
	private Integer provinciaId;

	@Column(name="provincia_nombre")
	private String provinciaNombre;

	//bi-directional many-to-one association to Comuna
	@OneToMany(mappedBy="provincia", fetch=FetchType.EAGER)
	private Set<Comuna> comunas;

	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="provincia_region_id")
	private Region region;

	public Provincia() {
	}

	public Integer getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvinciaNombre() {
		return this.provinciaNombre;
	}

	public void setProvinciaNombre(String provinciaNombre) {
		this.provinciaNombre = provinciaNombre;
	}

	public Set<Comuna> getComunas() {
		return this.comunas;
	}

	public void setComunas(Set<Comuna> comunas) {
		this.comunas = comunas;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
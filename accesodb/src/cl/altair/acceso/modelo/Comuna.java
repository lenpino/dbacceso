package cl.altair.acceso.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comuna database table.
 * 
 */
@Entity
public class Comuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comuna_id")
	private Integer comunaId;

	@Column(name="comuna_nombre")
	private String comunaNombre;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="comuna_provincia_id")
	private Provincia provincia;

	public Comuna() {
	}

	public Integer getComunaId() {
		return this.comunaId;
	}

	public void setComunaId(Integer comunaId) {
		this.comunaId = comunaId;
	}

	public String getComunaNombre() {
		return this.comunaNombre;
	}

	public void setComunaNombre(String comunaNombre) {
		this.comunaNombre = comunaNombre;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
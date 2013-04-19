package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Unidad
 *
 */
@Entity

public class Unidad implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="unidad_padre_id")
	private Unidad unidadPadre;
	
	@OneToMany(mappedBy="unidadPadre", fetch=FetchType.EAGER)
	private Set<Unidad> lasUnidades;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	private Empresa empresa;
	
	@OneToOne(mappedBy="unidad", fetch=FetchType.EAGER)
	private Contacto contacto;

	private static final long serialVersionUID = 1L;

	public Unidad() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Contacto getContactos() {
		return this.contacto;
	}
	public void setContactos(Contacto contactos) {
		this.contacto = contactos;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Set<Unidad> getLasUnidades() {
		return lasUnidades;
	}
	public void setLasUnidades(Set<Unidad> lasUnidades) {
		this.lasUnidades = lasUnidades;
	}
	public Unidad getUnidadPadre() {
		return unidadPadre;
	}
	public void setUnidadPadre(Unidad unidadPadre) {
		this.unidadPadre = unidadPadre;
	}
	public Contacto getContacto() {
		return contacto;
	}
	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

}

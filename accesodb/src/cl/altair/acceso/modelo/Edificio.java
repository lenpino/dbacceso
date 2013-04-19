package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import cl.altair.acceso.modelo.Edificio;

/**
 * Entity implementation class for Entity: Edificio
 *
 */
@Entity

public class Edificio implements Serializable {

	   
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String tipo;
	private String nombre;
	private Integer pisos;
	
    @OneToOne(optional=false, cascade=CascadeType.PERSIST)
    @JoinColumn(name="DIRECCION_ID", unique=true, nullable=false)
	private Direccion direccion;
    
	@OneToOne(optional=false, cascade=CascadeType.ALL) 
	@JoinColumn(name="DIRECTORIO_ID", unique=true)
	private Directorio directorio;
	
    @OneToOne(optional=false, cascade=CascadeType.PERSIST)
    @JoinColumn(name="PROPIETARIO_ID", unique=true)
	private Empresa propietario;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="EDIFICIO_ID")
	private Set<Dependencia> dependencias = new HashSet<Dependencia>();
	
	private static final long serialVersionUID = 1L;
	public Edificio() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public Set<Dependencia> getDependencias() {
		return dependencias;
	}
	public void setDependencias(Set<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}
	public Empresa getPropietario() {
		return propietario;
	}
	public void setPropietario(Empresa propietario) {
		this.propietario = propietario;
	}
	public Directorio getDirectorio() {
		return directorio;
	}
	public void setDirectorio(Directorio directorio) {
		this.directorio = directorio;
	}
	public void agregaDependencia(Dependencia unaDependencia){
		this.dependencias.add(unaDependencia);
	}
	public Integer getPisos() {
		return pisos;
	}
	public void setPisos(Integer pisos) {
		this.pisos = pisos;
	}
}

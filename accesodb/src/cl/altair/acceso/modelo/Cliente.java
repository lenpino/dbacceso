package cl.altair.acceso.modelo;

import cl.altair.acceso.modelo.Empresa;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cliente
 *
 */
@Entity

public class Cliente implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String estado;
	private Date fechaincorporacion;
	private Integer numerousuarios;
	
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="EMPRESA_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Empresa empresa;
	
	@OneToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="EMPRESA_FACTURAR_ID", unique= true, nullable=true, insertable=true, updatable=true)
	private Empresa empresaafacturar;
	
    @ManyToMany
    @JoinTable(name="CLIENTE_APLICACION",
    joinColumns=
        @JoinColumn(name="CLIENTE_ID", referencedColumnName="ID"),
    inverseJoinColumns=
        @JoinColumn(name="APLICACIONES_ID", referencedColumnName="ID")
    )
	private Set<Aplicacion> aplicaciones;
    
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="CLIENTE_ID")
	private Set<Usuario> usuarios;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
	private Set<Edificio> edificios;
	
	private static final long serialVersionUID = 1L;

	public Cliente() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}   
	public Date getFechaincorporacion() {
		return this.fechaincorporacion;
	}

	public void setFechaincorporacion(Date fechaincorporacion) {
		this.fechaincorporacion = fechaincorporacion;
	}   
	public Integer getNumerousuarios() {
		return this.numerousuarios;
	}

	public void setNumerousuarios(Integer numerousuarios) {
		this.numerousuarios = numerousuarios;
	}
	public Empresa getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	} 
	public Empresa getEmpresaafacturar() {
		return this.empresaafacturar;
	}

	public void setEmpresaafacturar(Empresa empresaafacturar) {
		this.empresaafacturar = empresaafacturar;
	}
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Set<Edificio> getEdificios() {
		return edificios;
	}
	public void setEdificios(Set<Edificio> edificios) {
		this.edificios = edificios;
	}
 
    public Set<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}
	public void setAplicaciones(Set<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}
   
}

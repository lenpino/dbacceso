package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empresa
 *
 */
@Entity

public class Empresa implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer rut;
	private String dv;
	private String razonSocial;
	private String nombreFantasia;
	private String telefono;
	private String estado; //activa, creada, eliminada
	
	@OneToMany(mappedBy="empresa", fetch=FetchType.EAGER)
	private Set<Unidad> unidades;
/*
	@ManyToOne
	@JoinColumn(name="HOLDING_ID")
	private Holding holding;
	
	public Holding getHolding() {
		return holding;
	}
	public void setHolding(Holding holding) {
		this.holding = holding;
	}
*/
	public Set<Unidad> getUnidades() {
		return unidades;
	}
	public void setUnidades(Set<Unidad> unidades) {
		this.unidades = unidades;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNombreFantasia() {
		return nombreFantasia;
	}
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	private static final long serialVersionUID = 1L;

	public Empresa() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Integer getRut() {
		return this.rut;
	}

	public void setRut(Integer rut) {
		this.rut = rut;
	}   
	public String getDv() {
		return this.dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
   
}

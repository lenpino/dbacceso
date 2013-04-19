package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ItemDirectorio
 *
 */
@Entity

public class UsuarioInmueble implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	// Arriendo o propietario
	private String tipouso;
	// Persona o Empresa
	private String tipoUsuario;
	// RUT del usuario
	private Integer rut;
	private String dv;
	
	//bi-directional many-to-one association to Provincia
	@OneToMany(mappedBy="usuarioInmueble", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Dependencia> dependencias = new HashSet<Dependencia>(0);;
	
	private static final long serialVersionUID = 1L;

	public UsuarioInmueble() {
		super();
	}
	
	public UsuarioInmueble(String rut, String dv, String nombre){
		this.setRut(Integer.valueOf(rut));
		this.setDv(dv);
		this.setNombre(nombre);
	}
	
	public UsuarioInmueble(String rut, String dv, String nombre, String tipoUso, String tipoUsr){
		this.setRut(Integer.valueOf(rut));
		this.setDv(dv);
		this.setNombre(nombre);
		this.setTipouso(tipoUso);
		this.setTipoUsuario(tipoUsr);
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
	public Set<Dependencia> getDependencias() {
		return dependencias;
	}
	public void setDependencias(Set<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}
	public String getTipouso() {
		return tipouso;
	}
	public void setTipouso(String tipouso) {
		this.tipouso = tipouso;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Integer getRut() {
		return rut;
	}
	public void setRut(Integer rut) {
		this.rut = rut;
	}
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
   
}

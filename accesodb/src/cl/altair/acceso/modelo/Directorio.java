package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Directorio
 *
 */
@Entity

public class Directorio implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="DIRECTORIO_ID")
	private Set<UsuarioInmueble> miembros = new HashSet<UsuarioInmueble>(0);
	
	private static final long serialVersionUID = 1L;

	public Directorio() {
		super();
	} 
	
	public Directorio(String name){
		this.nombre = name;
	}
	
	public Directorio (String nombre, Set<UsuarioInmueble> miembros){
		this.nombre = nombre;
		this.miembros = miembros;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Set<UsuarioInmueble> getMiembros() {
		return miembros;
	}
	public void setMiembros(Set<UsuarioInmueble> item) {
		this.miembros = item;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void addMiembro(UsuarioInmueble usuario){
		this.miembros.add(usuario);
	}
	public void deleteMiembro(UsuarioInmueble usuario){
		this.miembros.remove(usuario);
	}
}

package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rol
 *
 */
@Entity

public class Rol implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@ManyToMany
    @JoinTable(name="ROL_USUARIO",
    joinColumns=
         @JoinColumn(name="ROL_ID"),
    inverseJoinColumns=
         @JoinColumn(name="USUARIOS_ID")
    )
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	private static final long serialVersionUID = 1L;

	public Rol() {
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
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
   
}

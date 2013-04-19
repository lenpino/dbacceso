package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Aplicacion
 *
 */
@Entity

public class Aplicacion implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String nombrecontexto;
    @ManyToMany
    @JoinTable(name="APLICACION_CLIENTE",
    joinColumns=
        @JoinColumn(name="APLICACION_ID", referencedColumnName="ID"),
    inverseJoinColumns=
        @JoinColumn(name="CLIENTES_ID", referencedColumnName="ID")
    )
	private Set<Cliente> clientes;
	private static final long serialVersionUID = 1L;

	public Aplicacion() {
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
	public String getNombrecontexto() {
		return this.nombrecontexto;
	}

	public void setNombrecontexto(String nombrecontexto) {
		this.nombrecontexto = nombrecontexto;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
   
}

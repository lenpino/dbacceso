package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Contacto
 *
 */
@Entity

public class Contacto implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cargo;
	private String email;
	private String telefono;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="UNIDAD_ID")
	private Unidad unidad;
	
	private static final long serialVersionUID = 1L;

	public Contacto() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	
	public Unidad getUnidades() {
		return this.unidad;
	}
	public void setUnidades(Unidad unidad) {
		this.unidad = unidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Unidad getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
}

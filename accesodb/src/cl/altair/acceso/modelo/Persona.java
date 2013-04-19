package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity

public class Persona implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer rut;
	private String dv;
	private String nombre;
	private String apellido;
	private String apellidomat;
	private String nombread;
	private Date fechanac;
	private String email;
	private String celular;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="persona_id")
	private Set<Contacto> infoContacto = new HashSet<Contacto>();
	
	private static final long serialVersionUID = 1L;

	public Persona() {
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
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}   
	public String getNombread() {
		return this.nombread;
	}

	public void setNombread(String nombread) {
		this.nombread = nombread;
	}   
	public Date getFechanac() {
		return this.fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Set<Contacto> getInfoContacto() {
		return infoContacto;
	}
	public void setInfoContacto(HashSet<Contacto> infoContacto) {
		this.infoContacto = infoContacto;
	}
	public String getApellidomat() {
		return apellidomat;
	}
	public void setApellidomat(String apellidomat) {
		this.apellidomat = apellidomat;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public void setInfoContacto(Set<Contacto> infoContacto) {
		this.infoContacto = infoContacto;
	}
   
}

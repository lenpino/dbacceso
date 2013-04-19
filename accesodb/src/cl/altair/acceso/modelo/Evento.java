package cl.altair.acceso.modelo;

import cl.altair.acceso.modelo.Dependencia;
import cl.altair.acceso.modelo.UsuarioInmueble;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Evento
 *
 */
@Entity

public class Evento implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Timestamp inicio;
	private Timestamp fin;
	private String nombre;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USUARIOINMUEBLE_ID")
	private UsuarioInmueble organizador;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="LUGAR_ID")
	private Dependencia lugar;
	private static final long serialVersionUID = 1L;

	public Evento() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Timestamp getInicio() {
		return this.inicio;
	}

	public void setInicio(Timestamp inicio) {
		this.inicio = inicio;
	}   
	public Timestamp getFin() {
		return this.fin;
	}

	public void setFin(Timestamp fin) {
		this.fin = fin;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public UsuarioInmueble getOrganizador() {
		return this.organizador;
	}

	public void setOrganizador(UsuarioInmueble organizador) {
		this.organizador = organizador;
	}   
	public Dependencia getLugar() {
		return this.lugar;
	}

	public void setLugar(Dependencia lugar) {
		this.lugar = lugar;
	}
   
}

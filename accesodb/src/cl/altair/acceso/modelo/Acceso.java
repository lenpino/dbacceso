package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Acceso
 *
 */
@Entity

public class Acceso implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne (fetch = FetchType.EAGER, cascade=CascadeType.ALL) 
	@JoinColumn (name = "VISITA_ID", nullable= false)
	private Persona visita;
	
	@ManyToOne (fetch = FetchType.EAGER, cascade=CascadeType.ALL) 
	@JoinColumn (name = "ANFITRION_ID")
	private Persona anfitrion;

	@ManyToOne (fetch = FetchType.EAGER, cascade=CascadeType.ALL) 
	@JoinColumn (name = "DESTINO_ID", nullable= false)
	private UsuarioInmueble destino;

	
	public UsuarioInmueble getDestino() {
		return destino;
	}
	public void setDestino(UsuarioInmueble destino) {
		this.destino = destino;
	}
	private Timestamp fechaingreso;
	private Timestamp fechasalida;
	
	@ManyToOne (fetch = FetchType.EAGER, cascade=CascadeType.ALL) 
	@JoinColumn (name = "DEPENDENCIA_ID", nullable= false)
	private Dependencia dependencia;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MOTIVO_ID")
	private Motivo elMotivo;
	
	private static final long serialVersionUID = 1L;

	public Acceso() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   

	public Persona getVisita() {
		return this.visita;
	}

	public void setVisita(Persona visita) {
		this.visita = visita;
	}   
	public Persona getAnfitrion() {
		return this.anfitrion;
	}

	public void setAnfitrion(Persona anfitrion) {
		this.anfitrion = anfitrion;
	}    
	public Timestamp getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Timestamp fechaingreso) {
		this.fechaingreso = fechaingreso;
	}   
	public Timestamp getFechasalida() {
		return this.fechasalida;
	}

	public void setFechasalida(Timestamp fechasalida) {
		this.fechasalida = fechasalida;
	}
	public Dependencia getDependencia() {
		return dependencia;
	}
	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}
	public Motivo getElMotivo() {
		return elMotivo;
	}
	public void setElMotivo(Motivo elMotivo) {
		this.elMotivo = elMotivo;
	}
   
}

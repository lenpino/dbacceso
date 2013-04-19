package cl.altair.acceso.modelo;

import cl.altair.acceso.modelo.Dependencia;
import cl.altair.acceso.modelo.Usuario;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reserva
 *
 */
@Entity

public class Reserva implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date fechaemision;
	private Date fechareserva;
	private String estado;
	
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "DEPENDENCIA_ID", nullable= false)
	private Dependencia dependencia;
	
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "EMISOR_ID", nullable= false)
	private Usuario emisor;
	
	@ManyToOne (fetch = FetchType.EAGER) 
	@JoinColumn (name = "VISITA_ID", nullable= false)
	private Persona visita;
	
	private static final long serialVersionUID = 1L;

	public Reserva() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Date getFechaemision() {
		return this.fechaemision;
	}

	public void setFechaemision(Date fechaemision) {
		this.fechaemision = fechaemision;
	}   
	public Date getFechareserva() {
		return this.fechareserva;
	}

	public void setFechareserva(Date fechareserva) {
		this.fechareserva = fechareserva;
	}   
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Dependencia getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}  
	public Usuario getEmisor() {
		return this.emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}
	public Persona getVisita() {
		return visita;
	}
	public void setVisita(Persona visita) {
		this.visita = visita;
	}
   
}

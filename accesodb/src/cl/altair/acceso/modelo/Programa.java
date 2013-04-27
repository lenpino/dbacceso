package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.math.BigInteger;
import java.sql.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Programa
 *
 */
@Entity

public class Programa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date activacion;
	private BigInteger serial;
	private String estado;
	private String version;
	private Integer idcliente;
	private static final long serialVersionUID = 1L;

	public Programa() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public Date getActivacion() {
		return this.activacion;
	}

	public void setActivacion(Date activacion) {
		this.activacion = activacion;
	}   
	public BigInteger getSerial() {
		return this.serial;
	}

	public void setSerial(BigInteger serial) {
		this.serial = serial;
	}   
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}   
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}   
	public Integer getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}
   
}

package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoDependencia
 *
 */
@Entity

public class TipoDependencia implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String tipoDependencia;
	private static final long serialVersionUID = 1L;

	public TipoDependencia() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getTipoDependencia() {
		return this.tipoDependencia;
	}

	public void setTipoDependencia(String tipoDependencia) {
		this.tipoDependencia = tipoDependencia;
	}
   
}

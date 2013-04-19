package cl.altair.acceso.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Dependencia
 *
 */
@Entity

public class Dependencia implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String identificador;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TIPODEPENDENCIA_ID")
	private TipoDependencia tipo;
	
	private Integer piso;
	//Arrendatario o propietario
	private String tipousuario;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="USUARIOINMUEBLE_ID")	
	private UsuarioInmueble usuarioInmueble;

	private boolean reservable = true;
	
	@ManyToOne
	@JoinColumn(name="dependencia_padre_id")
	private Dependencia dependenciaPadre;
	
	@OneToMany(mappedBy="dependenciaPadre", fetch=FetchType.EAGER)
	private Set<Dependencia> dependencias;
	
	private static final long serialVersionUID = 1L;

	public Dependencia() {
		super();
	}   
	public Dependencia(String identificador, TipoDependencia tipo, Integer piso, boolean reservable){
		this.identificador = identificador;
		this.tipo = tipo;
		this.piso = piso;
		this.reservable = reservable;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getIdentificador() {
		return this.identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}   
	public TipoDependencia getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoDependencia tipo) {
		this.tipo = tipo;
	}   
	public Integer getPiso() {
		return this.piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}   
	public String getTipousuario() {
		return this.tipousuario;
	}

	public void setTipousuario(String tipousuario) {
		this.tipousuario = tipousuario;
	}
	public Set<Dependencia> getDependencias() {
		return dependencias;
	}
	public void setDependencias(Set<Dependencia> dependencias) {
		this.dependencias = dependencias;
	}
	public boolean isReservable() {
		return reservable;
	}
	public void setReservable(boolean reservable) {
		this.reservable = reservable;
	}
	public UsuarioInmueble getUsuarioInmueble() {
		return usuarioInmueble;
	}
	public void setUsuarioInmueble(UsuarioInmueble usuarioInmueble) {
		this.usuarioInmueble = usuarioInmueble;
	}
}

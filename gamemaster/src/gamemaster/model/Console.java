package gamemaster.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity(name = "Console")
@Table(name = "console")
public class Console implements EntityGenerica {
	private static final long serialVersionUID = 1811504869542638523L;
	
	private long id;
	private String titulo;
	private Date lancJP = new Date();
	private Date lancUS = new Date();
	private String empresa;
	
	@Id
	@GeneratedValue
	@Column(name = "idCon")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique=true, nullable=false, length=100, name="titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column(name="lancjp")
	public Date getLancJP() {
		return lancJP;
	}
	public void setLancJP(Date lancJP) {
		this.lancJP = lancJP;
	}
	
	@Column(name="lancus")
	public Date getLancUS() {
		return lancUS;
	}
	public void setLancUS(Date lancUS) {
		this.lancUS = lancUS;
	}
	
	@Column(length=30, name="empresa")
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

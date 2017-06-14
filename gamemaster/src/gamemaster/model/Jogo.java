package gamemaster.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Jogo")
@Table(name = "jogo")
public class Jogo implements EntityGenerica {
	private static final long serialVersionUID = 2997902059320643562L;
	
	private Long id;
	private String titulo;
	private Console console;
	private Date lancJP = new Date();
	private Date lancUS = new Date();
	private String empresa;
	private byte[] imagem;
	
	@Id
	@GeneratedValue
	@Column(name="idJo")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	@ManyToOne(targetEntity=Console.class)
	@JoinColumn(name = "idCon")
	public Console getConsole() {
		return console;
	}
	public void setConsole(Console console) {
		this.console = console;
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
	
	@Column(name="imagem")
	@Lob
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
}

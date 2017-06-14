package gamemaster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Dica")
@Table(name = "dica")
public class Dica implements EntityGenerica {	

	private static final long serialVersionUID = 5095836706346730574L;
	
	private Long id;
	private String titulo;
	private String descr;
	private Jogo jogo;
	private Console console;

	@Id
	@GeneratedValue
	@Column(name="idDi")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false, length = 100, name = "titulo")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(length = 100, name = "descricao")
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@ManyToOne(targetEntity = Jogo.class)
	@JoinColumn(name = "idJo")
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	@ManyToOne(targetEntity = Console.class)
	@JoinColumn(name = "idCon")
	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

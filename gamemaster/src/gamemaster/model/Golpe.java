package gamemaster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Golpe")
@Table(name = "golpe")
public class Golpe implements EntityGenerica {	


	private static final long serialVersionUID = -4330944126559543255L;
	
	private Long id;
	private String titulo;
	private String personagem;
	private String descr;
	private Jogo jogo;
	private Console console;

	@Id
	@GeneratedValue
	@Column(name="idGo")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {	
	}

	@Column(unique = true, nullable = false, length = 100, name = "titulo")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column(length = 30, name = "personagem")
	public String getPersonagem() {
		return personagem;
	}

	public void setPersonagem(String personagem) {
		this.personagem = personagem;
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

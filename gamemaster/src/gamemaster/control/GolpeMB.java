package gamemaster.control;

import gamemaster.dao.DAO;
import gamemaster.dao.DAOException;
import gamemaster.dao.DAOImpl;
import gamemaster.model.Console;
import gamemaster.model.Golpe;
import gamemaster.model.Jogo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GolpeMB implements Serializable {
	
	private static final long serialVersionUID = -4048609238034346307L;
	
	private List<Jogo> listaJogos = new ArrayList<Jogo>();
	private List<Golpe> listaPesquisa = new ArrayList<Golpe>();
	
	private Golpe golpeAtual;
	private Jogo jogoAtual;
	private Console consoleAtual;
	private DAO<Golpe> dao;
	private DAO<Jogo> daoJogo;
	
	public GolpeMB() {
		consoleAtual = new Console();
		golpeAtual = new Golpe();
		jogoAtual = new Jogo();
		dao = new DAOImpl<Golpe>(Golpe.class, "titulo");
		daoJogo = new DAOImpl<Jogo>(Jogo.class, "titulo");
	}
	
	public String adicionar() {
		String msg = "Erro ao adicionar o console";
		golpeAtual.setConsole(consoleAtual);
		golpeAtual.setJogo(jogoAtual);
		try {
			dao.adicionar(golpeAtual);
			msg = "Console adicionado com sucesso";
			golpeAtual = new Golpe();
			pesquisar();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_golpe";
	}

	public String editar(Golpe golpe) {
		golpeAtual = new Golpe();
		return "new_golpe";
	}

	public String excluir(Golpe j) {
		String msg = "Erro ao excluir o console";
		try {
			dao.remover(j.getId());
			msg = "Golpe excluido com sucesso";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_golpe";
	}

	public String pesquisar() {
		String msg = "Erro ao pesquisar golpes";
		try {
			listaPesquisa = dao.pesquisarGolpes(jogoAtual.getId());
			msg = "Foram encontrados " + listaPesquisa.size() + " golpes";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_golpe";
	}
	
	public String pesquisarJogos() {
		String msg = "Erro ao pesquisar jogos";
		try {
			listaJogos = daoJogo.pesquisarJogos(consoleAtual.getId());
			msg = "Foram encontrados " + listaPesquisa.size() + " jogos";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "";
	}
	
	public Console getConsoleAtual() {
		return consoleAtual;
	}

	public void setConsoleAtual(Console consoleAtual) {
		this.consoleAtual = consoleAtual;
	}
	
	public Jogo getJogoAtual() {
		return jogoAtual;
	}

	public void setJogoAtual(Jogo consoleAtual) {
		this.jogoAtual = consoleAtual;
	}

	public List<Golpe> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(List<Golpe> listaPesquisa) {
		this.listaPesquisa = listaPesquisa;
	}

	public Golpe getGolpeAtual() {
		return golpeAtual;
	}

	public void setGolpeAtual(Golpe golpeAtual) {
		this.golpeAtual = golpeAtual;
	}

	public List<Jogo> getListaJogos() {
		return listaJogos;
	}

	public void setListaJogos(List<Jogo> listaJogos) {
		this.listaJogos = listaJogos;
	}

}

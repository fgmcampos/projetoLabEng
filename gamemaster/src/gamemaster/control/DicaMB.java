package gamemaster.control;

import gamemaster.dao.DAO;
import gamemaster.dao.DAOException;
import gamemaster.dao.DAOImpl;
import gamemaster.model.Console;
import gamemaster.model.Dica;
import gamemaster.model.Jogo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class DicaMB implements Serializable {
	
	private static final long serialVersionUID = -4048609238034346307L;
	
	private List<Jogo> listaJogos = new ArrayList<Jogo>();
	private List<Dica> listaPesquisa = new ArrayList<Dica>();
	
	private Dica dicaAtual;
	private Jogo jogoAtual;
	private Console consoleAtual;
	private DAO<Dica> dao;
	private DAO<Jogo> daoJogo;
	
	public DicaMB() {
		consoleAtual = new Console();
		dicaAtual = new Dica();
		jogoAtual = new Jogo();
		dao = new DAOImpl<Dica>(Dica.class, "titulo");
		daoJogo = new DAOImpl<Jogo>(Jogo.class, "titulo");
	}
	
	public String adicionar() {
		String msg = "Erro ao adicionar o console";
		dicaAtual.setConsole(consoleAtual);
		dicaAtual.setJogo(jogoAtual);
		try {
			dao.adicionar(dicaAtual);
			msg = "Console adicionado com sucesso";
			dicaAtual = new Dica();
			pesquisar();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_dica";
	}

	public String editar(Dica dica) {
		dicaAtual = new Dica();
		return "new_dica";
	}

	public String excluir(Dica j) {
		String msg = "Erro ao excluir o console";
		try {
			dao.remover(j.getId());
			msg = "Dica excluido com sucesso";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_dica";
	}

	public String pesquisar() {
		String msg = "Erro ao pesquisar dicas";
		try {
			listaPesquisa = dao.pesquisarDicas(jogoAtual.getId());
			msg = "Foram encontrados " + listaPesquisa.size() + " dicas";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_dica";
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

	public List<Dica> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(List<Dica> listaPesquisa) {
		this.listaPesquisa = listaPesquisa;
	}

	public Dica getDicaAtual() {
		return dicaAtual;
	}

	public void setDicaAtual(Dica dicaAtual) {
		this.dicaAtual = dicaAtual;
	}

	public List<Jogo> getListaJogos() {
		return listaJogos;
	}

	public void setListaJogos(List<Jogo> listaJogos) {
		this.listaJogos = listaJogos;
	}

}

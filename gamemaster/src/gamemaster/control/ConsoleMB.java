package gamemaster.control;
import gamemaster.dao.DAO;
import gamemaster.dao.DAOException;
import gamemaster.dao.DAOImpl;
import gamemaster.model.Console;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ConsoleMB implements Serializable {
	private static final long serialVersionUID = 4666862815107320487L;
	private List<Console> listaPesquisa = new ArrayList<Console>();
	
	private Console consoleAtual;
	private DAO<Console> dao;
	public ConsoleMB() { 
		consoleAtual = new Console();
		dao = new DAOImpl<Console>(Console.class, "titulo");
		pesquisarTudo();
	}
	
	public String adicionar() { 
		String msg = "Erro ao adicionar o console";
		try {
			dao.adicionar( consoleAtual );
			msg = "Console adicionado com sucesso";
			consoleAtual = new Console();
			pesquisarTudo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage( "", new FacesMessage( msg ) );
		return "consoles";
	}
	
	public String editar(Console console){
		consoleAtual = console;
		return "new_console";
	}
	
	public String excluir(Console c) { 
		String msg = "Erro ao excluir o console";
		try {
			dao.remover(c.getId() );
			msg = "Console excluido com sucesso";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage( "", new FacesMessage( msg ) );
		return "consoles";
	}
	
	
	public String pesquisar() { 
		String msg = "Erro ao pesquisar consoles";
		try {
			listaPesquisa = dao.pesquisar( consoleAtual.getTitulo() );
			msg = "Foram encontrados " + listaPesquisa.size() + " consoles";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage( "", new FacesMessage( msg ) );
		return "consoles";
	}
	
	public String pesquisarTudo() { 
		String msg = "Erro ao pesquisar consoles";
		try {
			listaPesquisa = dao.pesquisar( "" );
			msg = "Foram encontrados " + listaPesquisa.size() + " consoles";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage( "", new FacesMessage( msg ) );
		return "consoles";
	}

	public Console getConsoleAtual() {
		return consoleAtual;
	}
	public void setConsoleAtual(Console consoleAtual) {
		this.consoleAtual = consoleAtual;
	}

	public List<Console> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(List<Console> listaPesquisa) {
		this.listaPesquisa = listaPesquisa;
	}
	
}

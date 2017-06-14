package gamemaster.control;

import gamemaster.dao.DAO;
import gamemaster.dao.DAOException;
import gamemaster.dao.DAOImpl;
import gamemaster.model.Console;
import gamemaster.model.Jogo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
public class JogoMB implements Serializable {
	private static final long serialVersionUID = 4666862815107320487L;
	private List<Jogo> listaPesquisa = new ArrayList<Jogo>();

	private Jogo jogoAtual;
	private Console consoleAtual;
	private DAO<Jogo> dao;
	private byte[] imagemAtual;

	public JogoMB() {
		consoleAtual = new Console();
		jogoAtual = new Jogo();
		dao = new DAOImpl<Jogo>(Jogo.class, "titulo");
	}
	
	public String adicionar() {
		String msg = "Erro ao adicionar o console";
		jogoAtual.setImagem(imagemAtual);
		jogoAtual.setConsole(consoleAtual);
		try {
			dao.adicionar(jogoAtual);
			msg = "Console adicionado com sucesso";
			jogoAtual = new Jogo();
			pesquisar();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_jogo";
	}

	public String editar(Jogo jogo) {
		jogoAtual = new Jogo();
		return "new_jogo";
	}

	public String excluir(Jogo j) {
		String msg = "Erro ao excluir o console";
		try {
			dao.remover(j.getId());
			msg = "Jogo excluido com sucesso";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_jogo";
	}

	public String pesquisar() {
		String msg = "Erro ao pesquisar jogos";
		try {
			listaPesquisa = dao.pesquisarJogos(consoleAtual.getId());
			msg = "Foram encontrados " + listaPesquisa.size() + " jogos";
		} catch (DAOException e) {
			e.printStackTrace();
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage("", new FacesMessage(msg));
		return "view_jogo";
	}
	
	public void upload(FileUploadEvent event) {  
        try {
            copyFile(event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	
	public void copyFile(InputStream in) {
		try {

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();
			
			byte[] imagem =out.toByteArray();
			imagemAtual = imagem;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Console getConsoleAtual() {
		return consoleAtual;
	}

	public void setConsoleAtual(Console consoleAtual) {
		this.consoleAtual = consoleAtual;
	}

	public List<Jogo> getListaPesquisa() {
		return listaPesquisa;
	}

	public void setListaPesquisa(List<Jogo> listaPesquisa) {
		this.listaPesquisa = listaPesquisa;
	}

	public Jogo getJogoAtual() {
		return jogoAtual;
	}

	public void setJogoAtual(Jogo jogoAtual) {
		this.jogoAtual = jogoAtual;
	}

	public byte[] getImagemAtual() {
		return imagemAtual;
	}
	
	public void setImagemAtual(byte[] imagemAtual) {
		this.imagemAtual = imagemAtual;
	}
}

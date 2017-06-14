package gamemaster.dao;

import gamemaster.model.Dica;
import gamemaster.model.Golpe;
import gamemaster.model.Jogo;

import java.util.List;

public interface DAO<T> {
	public void adicionar(T obj) throws DAOException;
	public List<T> pesquisar(String nome) throws DAOException;
	public List<Jogo> pesquisarJogos(long id) throws DAOException;
	public List<Dica> pesquisarDicas(long id) throws DAOException;
	public List<Golpe> pesquisarGolpes(long id) throws DAOException;
	public void remover(long id) throws DAOException ;
	public void atualizar(long id, T obj) throws DAOException;
}

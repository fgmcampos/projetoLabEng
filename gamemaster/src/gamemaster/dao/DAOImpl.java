package gamemaster.dao;

import gamemaster.model.Console;
import gamemaster.model.Dica;
import gamemaster.model.Golpe;
import gamemaster.model.Jogo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DAOImpl<T> implements DAO<T> {

	private Class<T> tipo;
	private String campoBusca;

	public DAOImpl(Class<T> tipo, String campoBusca) {
		this.tipo = tipo;
		this.campoBusca = campoBusca;
	}

	@Override
	public void adicionar(T obj) throws DAOException {
		try {
			EntityManager em = JPAUtil.getInstance().getEM()
					.createEntityManager();
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<T> pesquisar(String texto) throws DAOException {
		System.out.println("AQUI");
		System.out.println("NOME: " + tipo.getName());
		List<T> lista = new ArrayList<T>();
		try {
			EntityManager em = JPAUtil.getInstance().getEM()
					.createEntityManager();

			String sql = "select a from " + tipo.getName() + " a where a."
					+ campoBusca + " like :campo";
			TypedQuery<T> qry = em.createQuery(sql, tipo);
			qry.setParameter("campo", "%" + texto + "%");
			lista = qry.getResultList();

			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public List<Jogo> pesquisarJogos(long id) throws DAOException {
		List<Jogo> lista = new ArrayList<Jogo>();
		try {
			EntityManager em = JPAUtil.getInstance().getEM()
					.createEntityManager();

			String sql = "select a from Jogo as a where a.console.id = :campo";
			TypedQuery<Jogo> qry = em.createQuery(sql, Jogo.class);
			qry.setParameter("campo", id);
			lista = qry.getResultList();

			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return lista;
	}
	
	@Override
	public List<Dica> pesquisarDicas(long id) throws DAOException {
		List<Dica> lista = new ArrayList<Dica>();
		try {
			EntityManager em = JPAUtil.getInstance().getEM()
					.createEntityManager();

			String sql = "select a from Dica as a where a.jogo.id = :campo";
			TypedQuery<Dica> qry = em.createQuery(sql, Dica.class);
			qry.setParameter("campo", id);
			lista = qry.getResultList();

			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public List<Golpe> pesquisarGolpes(long id) throws DAOException {
		List<Golpe> lista = new ArrayList<Golpe>();
		try {
			EntityManager em = JPAUtil.getInstance().getEM()
					.createEntityManager();

			String sql = "select a from Golpe as a where a.jogo.id = :campo";
			TypedQuery<Golpe> qry = em.createQuery(sql, Golpe.class);
			qry.setParameter("campo", id);
			lista = qry.getResultList();

			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public void remover(long id) throws DAOException {
		try {
			EntityManager em = JPAUtil.getInstance().getEM()
					.createEntityManager();
			Console c = em.find(Console.class, id);
			em.getTransaction().begin();
			em.remove(c);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void atualizar(long id, T obj) throws DAOException {
		try {
			EntityManager em = JPAUtil.getInstance().getEM()
					.createEntityManager();
			// obj.setId( id ) ;
			em.getTransaction().begin();
			em.merge(obj);
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}

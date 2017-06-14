package gamemaster.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private EntityManagerFactory em;
	private static JPAUtil instancia;
	private JPAUtil() { 
		em = Persistence.createEntityManagerFactory("gamemaster");
	}
	public static JPAUtil getInstance() {
		if (instancia == null) { 
			instancia = new JPAUtil();
		}
		return instancia;
	}
	public EntityManagerFactory getEM() { 
		return em;
	}
}

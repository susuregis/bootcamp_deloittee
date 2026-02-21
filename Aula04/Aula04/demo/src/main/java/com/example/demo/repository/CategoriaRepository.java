package com.example.demo.repository;

import com.example.demo.model.Categoria;
import com.example.demo.ProjectCrudApplication;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class CategoriaRepository {
	
	private EntityManager getEntityManager() {
		return ProjectCrudApplication.getEntityManagerFactory().createEntityManager();
	}

	public Categoria save(Categoria categoria) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			if (categoria.getId() == null) {
				em.persist(categoria);
			} else {
				categoria = em.merge(categoria);
			}
			em.getTransaction().commit();
			return categoria;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}

	public Optional<Categoria> findById(Long id) {
		EntityManager em = getEntityManager();
		try {
			Categoria categoria = em.find(Categoria.class, id);
			return Optional.ofNullable(categoria);
		} finally {
			em.close();
		}
	}

	public List<Categoria> findAll() {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Categoria> query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public Optional<Categoria> findByNome(String nome) {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Categoria> query = em.createQuery(
				"SELECT c FROM Categoria c WHERE c.nome = :nome", Categoria.class);
			query.setParameter("nome", nome);
			List<Categoria> results = query.getResultList();
			return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
		} finally {
			em.close();
		}
	}

	public boolean existsByNome(String nome) {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery(
				"SELECT COUNT(c) FROM Categoria c WHERE c.nome = :nome", Long.class);
			query.setParameter("nome", nome);
			return query.getSingleResult() > 0;
		} finally {
			em.close();
		}
	}

	public List<Categoria> findByAtivaTrue() {
		EntityManager em = getEntityManager();
		try {
			TypedQuery<Categoria> query = em.createQuery(
				"SELECT c FROM Categoria c WHERE c.ativa = true", Categoria.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public void deleteById(Long id) {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			Categoria categoria = em.find(Categoria.class, id);
			if (categoria != null) {
				em.remove(categoria);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw e;
		} finally {
			em.close();
		}
	}
}
